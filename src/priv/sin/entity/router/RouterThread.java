package priv.sin.entity.router;

import java.awt.Color;
import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.concurrent.TimeUnit;

import priv.sin.entity.data.DataPackage;
import priv.sin.entity.global.Global;

public class RouterThread implements Runnable {
	private int tid;
	private int connectIP;
	private Socket socket;
	private ObjectInputStream inputStream;
	private ObjectOutputStream outputStream;

	public RouterThread(int tid, int connectIP, Socket socket) throws IOException {
		this.tid = tid;
		this.connectIP = connectIP;
		this.socket = socket;
		outputStream = new ObjectOutputStream(socket.getOutputStream());
		inputStream = new ObjectInputStream(new BufferedInputStream(socket.getInputStream()));
	}

	public void send(DataPackage dataPackage) throws IOException {
		outputStream.writeObject(dataPackage);
		outputStream.flush();
	}

	public void allHostsBoardcast(DataPackage dataPackage) throws IOException {
		for (RouterThread r : Router.routerThreads) {
			if (r != this) {
				r.send(dataPackage);
			}
		}
	}

	private void netBoardcast(int dstip, DataPackage dataPackage) throws IOException {
		int netid = dstip & Global.ipMask;
		for (RouterThread r : Router.routerThreads) {
			if ((r.connectIP & Global.ipMask) == netid)
				r.send(dataPackage);
		}
	}

	private boolean directDeliery(DataPackage dataPackage) throws IOException {
		int dstip = dataPackage.getDstIP();
		// int srcip = dataPackage.getSrcIP();
		for (RouterThread r : Router.routerThreads) {
			// if (((r.connectIP & Global.ipMask) == (srcip & Global.ipMask)) && r.connectIP
			// == dstip)
			if (r.connectIP == dstip) {
				r.send(dataPackage);
				return true;
			}
		}
		return false;
	}

	private void indirectDeliery(DataPackage dataPackage) throws IOException {
		int dstip = dataPackage.getDstIP();
		int srcip = dataPackage.getSrcIP();
		for (RouterThread r : Router.routerThreads) {
			if ((r.connectIP & Global.ipMask) == (dstip & Global.ipMask)) {
				r.send(dataPackage);
			}
		}
	}

	private boolean isDirect(DataPackage dataPackage) {
		int dstip = dataPackage.getDstIP();
		int srcip = dataPackage.getSrcIP();
		for (RouterThread r : Router.routerThreads) {
			if ((r.connectIP & Global.ipMask) == (srcip & Global.ipMask) && r.connectIP == dstip)
				return true;
		}
		return false;
	}

	@Override
	public void run() {
		try {
			Global.printLog("Routerlog", "Client port = " + socket.getPort() + " is connected.");
		} catch (Exception e) {
			e.printStackTrace();
		}
		while (true) {
			synchronized (this) {
				try {
					DataPackage dataPackage = (DataPackage) inputStream.readObject();
					Global.printLog("Routerlog",
							"RouterThread " + tid + " get msg: " + dataPackage.datas[0].toString());

					if (isDirect(dataPackage) == false) {
						Router.memory.copyDatas(dataPackage);
						Router.routerJFrame.changeMemoryState(Color.RED);
						TimeUnit.SECONDS.sleep(3);
						directDeliery(dataPackage);
						Router.routerJFrame.changeMemoryState(Color.YELLOW);
						Router.memory.getDatas(dataPackage.getPackageSize());
					} else {
						directDeliery(dataPackage);
					}

				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
	}

}
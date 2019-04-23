package priv.sin.entity.router;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

import priv.sin.entity.data.DataPackage;
import priv.sin.entity.global.Global;

public class RouterThread implements Runnable{
	private int tid;
	private int connectIP;
	private Socket socket;
	private ObjectInputStream inputStream;
	private ObjectOutputStream outputStream;
	
	public RouterThread(int tid, int connectIP, Socket socket) throws IOException 
	{
		this.tid = tid;
		this.connectIP = connectIP;
		this.socket = socket;
		outputStream = new ObjectOutputStream(socket.getOutputStream());
		inputStream = new ObjectInputStream(new BufferedInputStream(socket.getInputStream()));
	}
	
	public void send(DataPackage dataPackage) throws IOException
	{
		outputStream.writeObject(dataPackage);
		outputStream.flush();
	}
	
	public void allHostsBoardcast(DataPackage dataPackage) throws IOException
	{
		for (RouterThread r: Router.routerThreads)
		{
			if(r!=this)
			{
				r.send(dataPackage);
			}
		}
	}
	
	private void netBoardcast(int dstip, DataPackage dataPackage) throws IOException 
	{
		int netid = dstip & Global.ipMask;
		for (RouterThread r: Router.routerThreads)
		{
			if ((r.connectIP & Global.ipMask) == netid)
				r.send(dataPackage);
		}
	}
	
	private void send2host(int hostip, DataPackage dataPackage) throws IOException
	{
		for (RouterThread r: Router.routerThreads)
		{
			if (r.connectIP == hostip)
				r.send(dataPackage);
		}
	}
	
	private void dispatchMsg(DataPackage dataPackage) throws IOException
	{
		int dstip = dataPackage.datas[0].getDstIP();
		send2host(dstip, dataPackage);
	}
	
	@Override
	public void run() {
		try
		{
			Global.printLog("Routerlog", "Client port = " + socket.getPort() + " is connected.");
		}
		catch (Exception e) 
		{
			e.printStackTrace();
		}
		while (true)
		{
			try
			{
				DataPackage dataPackage =  (DataPackage)inputStream.readObject();
				Global.printLog("Routerlog","RouterThread " + tid + " get msg: " + dataPackage.datas[0].toString());
				dispatchMsg(dataPackage);
				
			}
			catch (Exception e)
			{
				e.printStackTrace();
			}
		}
	}
	

}
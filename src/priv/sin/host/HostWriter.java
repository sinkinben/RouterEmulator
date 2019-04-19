package priv.sin.host;

import java.io.ObjectOutputStream;
import java.util.concurrent.TimeUnit;

import priv.sin.data.DataPackage;
import priv.sin.global.Global;

public class HostWriter implements Runnable{
	private ObjectOutputStream outputStream;
	private int socketPort;
	private static int dataMsgOrder = 0;
	private static final int sleepSecs = 10;
	
	public HostWriter(ObjectOutputStream outputStream, int socketPort) {
		this.outputStream = outputStream;
		this.socketPort = socketPort;
	}
	@Override
	public void run() {
		// TODO Auto-generated method stub
		while (true)
		{
			try
			{
				System.out.println(Host.pid + " Writer is running.");
				DataPackage dataPackage = new DataPackage(dataMsgOrder, Host.pid, socketPort, Global.dataContent);
				dataMsgOrder += 10;
				Global.printLog(String.valueOf(Host.pid), "Host pid="+Host.pid + " send package "+dataPackage.toString() + " to Router");
				outputStream.writeObject(dataPackage);
				outputStream.flush();
				TimeUnit.SECONDS.sleep(sleepSecs);
			}
			catch (Exception e)
			{
				e.printStackTrace();
			}
		}
	}

}

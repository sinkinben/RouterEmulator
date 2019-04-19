package priv.sin.host;

import java.io.ObjectInputStream;

import priv.sin.data.DataPackage;
import priv.sin.global.Global;

public class HostReader implements Runnable{

	private ObjectInputStream inputStream;
	private int socketPort;
	private static final int sleepSecs = 10;
	
	public HostReader(ObjectInputStream inputStream, int socketPort) {
		super();
		this.inputStream = inputStream;
		this.socketPort = socketPort;
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		while (true)
		{
			try
			{
				System.out.println(Host.pid + " Reader running.");
				DataPackage dataPackage = (DataPackage)inputStream.readObject();
				Global.printLog(String.valueOf(Host.pid), "Host pid="+Host.pid + " get package "+dataPackage.toString());
			}
			catch (Exception e)
			{
				e.printStackTrace();
			}
		}
	}

}

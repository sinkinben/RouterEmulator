package priv.sin.entity.host;

import java.io.ObjectInputStream;

import priv.sin.entity.data.DataPackage;

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
	public void run()
	{
		while (true)
		{
			try 
			{
				DataPackage dataPackage = (DataPackage)inputStream.readObject();
				Host.hostJFrame.addMsgRecvPackage(dataPackage);
			}
			catch (Exception e)
			{
				e.printStackTrace();
			}
			
		}
	}

}

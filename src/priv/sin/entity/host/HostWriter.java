package priv.sin.entity.host;

import java.io.ObjectOutputStream;
import java.util.concurrent.TimeUnit;

import priv.sin.entity.data.DataPackage;
import priv.sin.entity.global.Global;

public class HostWriter implements Runnable{
	private ObjectOutputStream outputStream;
	private int socketPort;
	private static int dataMsgOrder = 0;
	private static final int sleepSecs = 5;
	private static boolean lock = true;
	public HostWriter(ObjectOutputStream outputStream, int socketPort) {
		this.outputStream = outputStream;
		this.socketPort = socketPort;
	}
	public static void setLock(boolean b)
	{
		lock = b;
	}
	
	@Override
	public void run()
	{
		while (true)
		{
			try 
			{
				if (lock)
					TimeUnit.SECONDS.sleep(sleepSecs);
				else
				{
					String dstIpStr = Host.hostJFrame.getActionIp();
					String msg = Host.hostJFrame.getActionMsg();
					DataPackage dataPackage = new DataPackage(Host.hostip, Global.string2ipv4(dstIpStr), dataMsgOrder, Host.pid, Host.socketPort, msg);
					dataMsgOrder+=10;
					outputStream.writeObject(dataPackage);
					outputStream.flush();
					Host.hostJFrame.addMsgSendPackage(dataPackage);
					setLock(true);
				}
			} 
			catch (Exception e) 
			{
				e.printStackTrace();
			}
		}
	}
	
	/*
	 * default run
	 * do not combine with gui-input
	 */
//	@Override
//	public void run() {
//		while (true)
//		{
//			try
//			{
//				System.out.println(Host.pid + " Writer is running.");
//				DataPackage dataPackage = new DataPackage(Host.hostip, getDstIp(), dataMsgOrder, Host.pid, Host.socketPort, Global.dataContent);
//				dataMsgOrder += 10;
//				Global.printLog(Host.ipString, "Host pid="+Host.pid + " send 10 items "+dataPackage.datas[0].toString());
//				outputStream.writeObject(dataPackage);
//				outputStream.flush();
//				TimeUnit.SECONDS.sleep(sleepSecs);
//			}
//			catch (Exception e)
//			{
//				e.printStackTrace();
//			}
//		}
//	}
	
	public int getDstIp()
	{
		int index = Global.getRandom(0, Global.ipPool.length-1);
		while (Global.ipPool[index].equals(Host.ipString))
			index = Global.getRandom(0,  Global.ipPool.length - 1);
		return Global.string2ipv4(Global.ipPool[index]);
		
	}
}

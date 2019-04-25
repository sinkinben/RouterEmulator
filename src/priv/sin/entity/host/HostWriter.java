package priv.sin.entity.host;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

import priv.sin.entity.data.DataPackage;
import priv.sin.entity.global.FileHelper;
import priv.sin.entity.global.Global;

public class HostWriter implements Runnable{
	private ObjectOutputStream outputStream;
	private int socketPort;
	private static int dataMsgOrder = 0;
	private static final int sleepAuto = 10;
	private static final int sleepLock = 5;
	private static boolean lock = true;
	private static boolean autoModel = false;
	
	public HostWriter(ObjectOutputStream outputStream, int socketPort) {
		this.outputStream = outputStream;
		this.socketPort = socketPort;
	}
	public static void setLock(boolean b)
	{
		lock = b;
	}
	public static void switchAutoModel()
	{
		autoModel = !autoModel;
	}
	@Override
	public void run()
	{
		while (true)
		{
			try 
			{
				if (autoModel == false)
				{
					if (lock)
						TimeUnit.SECONDS.sleep(sleepLock);
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
				else 
				{
					String dstIpStr = getDstIpString();
					String msg = "161630230";
					DataPackage dataPackage = new DataPackage(Host.hostip, Global.string2ipv4(dstIpStr), dataMsgOrder, Host.pid, Host.socketPort, msg);
					dataMsgOrder+=10;
					outputStream.writeObject(dataPackage);
					outputStream.flush();
					Host.hostJFrame.addMsgSendPackage(dataPackage);
					TimeUnit.SECONDS.sleep(sleepAuto);
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
	
	public String getDstIpString() throws IOException
	{
		ArrayList<String> ipList = new ArrayList<>();
		BufferedReader reader = new BufferedReader(new FileReader(FileHelper.currentIP));
		String string;
		while ((string = reader.readLine())!=null)
		{
			ipList.add(string);
		}
		reader.close();
		int index = Global.getRandom(0, ipList.size()-2);
		return ipList.get(index);
	}
}

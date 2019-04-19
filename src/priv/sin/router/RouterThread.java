package priv.sin.router;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

import junit.framework.Assert;
import priv.sin.data.Data;
import priv.sin.data.DataPackage;
import priv.sin.global.Global;

public class RouterThread implements Runnable{
	private int tid;
	private Socket socket;
	private ObjectInputStream inputStream;
	private ObjectOutputStream outputStream;
	private RouterMemory memory;
	
	public RouterThread(int tid, Socket socket) throws IOException 
	{
		this.tid = tid;
		this.socket = socket;
		outputStream = new ObjectOutputStream(socket.getOutputStream());
		inputStream = new ObjectInputStream(new BufferedInputStream(socket.getInputStream()));
		memory = new RouterMemory();
	}
	
	public void send(DataPackage dataPackage) throws IOException
	{
		outputStream.writeObject(dataPackage);
		outputStream.flush();
	}
	
	public void boardcast(DataPackage dataPackage) throws IOException
	{
		for (RouterThread r: Router.routerThreads)
		{
			if(r!=this)
			{
				r.send(dataPackage);
			}
		}
	}
	
	@SuppressWarnings("deprecation")
	private void checkRoutingTable(Data message)
	{
		System.err.println("Check Routing Table Not Finish.");
		Assert.fail("Check Routing Table Not Finish.");
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
				Global.printLog("Routerlog","RouterThread " + tid + " get msg: " + dataPackage.toString());
				memory.copyDatas(dataPackage);
				//checkRoutingTable(message);
				boardcast(dataPackage);
			}
			catch (Exception e)
			{
				e.printStackTrace();
			}
		}
	}
	

}
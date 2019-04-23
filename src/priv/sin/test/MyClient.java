package priv.sin.test;


import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.UnknownHostException;
import java.text.SimpleDateFormat;
import java.util.concurrent.TimeUnit;

import priv.sin.entity.global.Global;

public class MyClient {
	public static final String datePattern = "yyyy-MM-dd HH:mm:ss SSS";
	public static final SimpleDateFormat dateFormat = new SimpleDateFormat(datePattern);
	public static void main(String[] args)
	throws UnknownHostException, IOException, ClassNotFoundException, InterruptedException 
	{
		Socket socket = new Socket("localhost", 18824);
		ObjectInputStream inputStream = new ObjectInputStream(new BufferedInputStream(socket.getInputStream()));
		ObjectOutputStream outputStream = new ObjectOutputStream(socket.getOutputStream());
		
		new Thread(new WriterThread(outputStream)).start();
		new Thread(new ReaderThread(inputStream)).start();
		
	}
};

class WriterThread implements Runnable{
	ObjectOutputStream outputStream;
	int order = 0;
	public WriterThread(ObjectOutputStream outputStream) {
		// TODO Auto-generated constructor stub
		this.outputStream = outputStream;
	}
	@Override
	public void run() 
	{
		// TODO Auto-generated method stub
		while(true)
		{
			try 
			{
				System.out.println(Global.getPid() + " Writer");
				Data data = new Data("161630230",order++, Global.getPid());
				outputStream.writeObject(data);
				TimeUnit.SECONDS.sleep(5);
			} 
			catch (Exception e) 
			{
				e.printStackTrace();
			}
		}
	}
	
}

class ReaderThread implements Runnable{
	ObjectInputStream inputStream;
	public ReaderThread(ObjectInputStream inputStream)
	{
		this.inputStream = inputStream;
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		while(true)
		{
			try
			{
				Data data = (Data)inputStream.readObject();
				if(data != null)
					System.out.println(Global.getPid() + " Reader get " + data.toString());
			}
			catch (Exception e) 
			{
				e.printStackTrace();
			}
		}
	}
}

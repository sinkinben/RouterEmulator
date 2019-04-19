package priv.sin.host;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.ArrayList;

import priv.sin.data.Data;
import priv.sin.global.Global;

/*
 * Client
 */
public class Host {
	public static final int pid = Global.getPid();
	private static int socketPort;
	private static final int netid = Global.getNetid();
	private static ArrayList<Data> msgList = new ArrayList<>();
	private static int hostip = 123456789;
	
	
	public static int counter = 0;
	public static void main(String[] args)
	throws UnknownHostException, IOException, InterruptedException 
	{
		Socket socket = new Socket(Global.hostName, Global.port);
		ObjectOutputStream outputStream = new ObjectOutputStream(socket.getOutputStream());
		ObjectInputStream inputStream = new ObjectInputStream(new BufferedInputStream(socket.getInputStream()));
		
		socketPort = socket.getPort();
		Host.printAttr();
		
		new Thread(new HostWriter(outputStream, socket.getPort())).start();
		new Thread(new HostReader(inputStream, socket.getPort())).start();
		
	}
	
	public static void printAttr() throws IOException
	{
		Global.printLog(String.valueOf(Host.pid), "Host pid: " + Host.pid);
		Global.printLog(String.valueOf(Host.pid), "Host netid: " + Host.netid);
		Global.printLog(String.valueOf(Host.pid), "Host socket port: "+ Host.socketPort);
	}
}

package priv.sin.host;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.ArrayList;

import priv.sin.data.Data;
import priv.sin.global.FileHelper;
import priv.sin.global.Global;

/*
 * Client
 */
public class Host {
	public static final int pid = Global.getPid();
	public static int socketPort;
	public static ArrayList<Data> msgList = new ArrayList<>();
	public static int hostip = 0;
	public static String ipString = "";
	
	public static int counter = 0;
	public static void main(String[] args)
	throws UnknownHostException, IOException, InterruptedException 
	{
		//HostJFrame hostWindow = new HostJFrame(Host.pid, Host.netid, Host.hostip);
		Host.hostip = FileHelper.recvIP();
		Host.ipString = Global.ipv4String(hostip);
		System.out.println(Host.ipString);
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
		Global.printLog(Host.ipString, "Host pid: " + Host.pid);
		Global.printLog(Host.ipString, "Host socket port: "+ Host.socketPort);
		Global.printLog(Host.ipString, "Host ip: " + ipString);
		Global.printLog(Host.ipString, "Host net-id: " + Global.ipv4String(hostip & Global.ipMask));
	}
}

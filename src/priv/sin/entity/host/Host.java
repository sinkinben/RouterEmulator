package priv.sin.entity.host;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.ArrayList;

import priv.sin.entity.data.Data;
import priv.sin.entity.global.FileHelper;
import priv.sin.entity.global.Global;
import priv.sin.gui.host.HostJFrame;

/*
 * Client
 */
public class Host {
	public static final int pid = Global.getPid();
	public static int socketPort;
	public static ArrayList<Data> msgList = new ArrayList<>();
	public static int hostip = 0;
	public static String ipString = "";
	
	public static HostJFrame hostJFrame;
	
	public static void main(String[] args)
	throws UnknownHostException, IOException, InterruptedException 
	{
		
		Host.hostip = FileHelper.recvIP();
		Host.ipString = Global.ipv4String(hostip);
		
		
		Socket socket = new Socket(Global.hostName, Global.port);
		ObjectOutputStream outputStream = new ObjectOutputStream(socket.getOutputStream());
		ObjectInputStream inputStream = new ObjectInputStream(new BufferedInputStream(socket.getInputStream()));
		
		socketPort = socket.getPort();
		Host.printAttr();
		
		hostJFrame = new HostJFrame(pid, hostip & Global.ipMask, hostip);
		
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

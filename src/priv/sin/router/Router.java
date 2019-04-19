package priv.sin.router;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

import priv.sin.global.Global;
import priv.sin.global.Helper;
/*
 * Router Server
 */
public class Router {
	private static final int pid = Global.getPid();
	private static int tid = 0;
	public static ArrayList<RouterThread> routerThreads = new ArrayList<>();
	public static RoutingTable routingTable = new RoutingTable();
	public static void main(String[] args) throws IOException
	{
		Helper.clearLog();
		System.out.println("Router pid: " + Router.pid);
		ServerSocket serverSocket = new ServerSocket();
		InetSocketAddress address = new InetSocketAddress(Global.hostName, Global.port);
		serverSocket.bind(address);
		while (true)
		{
			Socket socket = serverSocket.accept();
			RouterThread routerThread = new RouterThread(tid++, socket);
			routerThreads.add(routerThread);
			Thread thread = new Thread(routerThread);
			thread.start();
		}
	}

}

package priv.sin.entity.router;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

import priv.sin.entity.global.FileHelper;
import priv.sin.entity.global.Global;
import priv.sin.gui.router.RouterJFrame;
/*
 * Router Server
 */
public class Router {
	private static final int pid = Global.getPid();
	private static int tid = 0;
	public static ArrayList<RouterThread> routerThreads = new ArrayList<>();
	public static RoutingTable routingTable = new RoutingTable();
	public static ArrayList<Integer> ipList = new ArrayList<>(); 
	public static RouterMemory memory = new RouterMemory();
	public static RouterJFrame routerJFrame;
	public static void main(String[] args) throws IOException, InterruptedException
	{
		FileHelper.init();
		System.out.println("Router pid: " + Router.pid);
		
		ServerSocket serverSocket = new ServerSocket();
		InetSocketAddress address = new InetSocketAddress(Global.hostName, Global.port);
		serverSocket.bind(address);
		
		routerJFrame = new RouterJFrame(pid, Global.port, routingTable);
		

		while (true)
		{
			int ip = Global.allocateIP();
			FileHelper.sendIP(ip);
			
			Socket socket = serverSocket.accept();
			RouterThread routerThread = new RouterThread(tid++, ip, socket);
			routerThreads.add(routerThread);
			
			
			Thread thread = new Thread(routerThread);
			thread.start();
			
			routerJFrame.addRouterThreadItem(tid, ip, socket.getPort());
			
			TimeUnit.SECONDS.sleep(2);
		}
	}

}

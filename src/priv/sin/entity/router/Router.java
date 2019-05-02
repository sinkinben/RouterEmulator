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

	public static void main(String[] args) throws IOException, InterruptedException {
		FileHelper.init();
		System.out.println("Router pid: " + Router.pid);

		ServerSocket serverSocket = new ServerSocket();//新建服务socket
		InetSocketAddress address = new InetSocketAddress(Global.hostName, Global.port);
		serverSocket.bind(address);//绑定端口

		routerJFrame = new RouterJFrame(pid, Global.port, routingTable);//新建router窗体

		while (true) {//循环接收连接请求
			int ip = Global.allocateIP();//分配ip
			FileHelper.sendIP(ip);
			System.out.println("Set up ok!");
			Socket socket = serverSocket.accept();//接收请求
			RouterThread routerThread = new RouterThread(tid++, ip, socket);//新建线程
			routerThreads.add(routerThread);

			Thread thread = new Thread(routerThread);//启动线程
			thread.start();

			routerJFrame.addRouterThreadItem(tid, ip, socket.getPort());//添加当前主机到窗体的“主机列表”

			TimeUnit.SECONDS.sleep(2);//等待2秒，完成通信与连接
		}
	}

}

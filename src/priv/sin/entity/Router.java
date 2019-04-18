package priv.sin.entity;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;

import priv.sin.global.Global;
import priv.sin.socket.SocketHandler;
/*
 * Router Server
 */
public class Router {
	private static final int pid = Global.getPid();
	public static void main(String[] args) throws IOException
	{
		System.out.println("Router " + Router.pid);
		ServerSocket serverSocket = new ServerSocket();
		InetSocketAddress address = new InetSocketAddress(Global.hostName, Global.port);
		serverSocket.bind(address);
		while (true)
		{
			Socket socket = serverSocket.accept();
			new Thread(new SocketHandler(socket)).start();
		}
	}

}

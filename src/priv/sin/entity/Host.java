package priv.sin.entity;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

import priv.sin.global.Global;

/*
 * Client
 */
public class Host {
	private static final int pid = Global.getPid();
	private static final int netid = Global.getNetid();
	private static ArrayList<DataMessage> msgList = new ArrayList<>();
	private static int hostip = 123456789;
	
	
	public static int counter = 0;
	public static void main(String[] args) throws UnknownHostException, IOException, InterruptedException {
		Host.printAttr();
		Socket socket = new Socket(Global.hostName, Global.port);
		
		BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
		PrintWriter writer = new PrintWriter(socket.getOutputStream(),true);
		while (true)
		{
			writer.write("[" + pid + " " + counter + "]\n");
			counter++;
			TimeUnit.SECONDS.sleep(10);
		}
	}
	
	public static void printAttr()
	{
		System.out.println("Host pid: " + Host.pid);
		System.out.println("Host netid: " + Host.netid);
	}
	
	public static void send(DataMessage[] messages)
	{
		
	}
	
	public static void receive(DataMessage message)
	{
		
	}
}

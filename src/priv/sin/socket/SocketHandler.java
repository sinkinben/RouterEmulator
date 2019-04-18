package priv.sin.socket;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

import priv.sin.global.Global;

public class SocketHandler implements Runnable{
	private Socket socket;
	
	public SocketHandler(Socket socket) {
		this.socket = socket;
	}
	@Override
	public void run() {
		try
		{
			Global.printLog(socket.getPort() + " is connected.");
			BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			PrintWriter writer = new PrintWriter(socket.getOutputStream(),true);
		}
		catch (Exception e) 
		{
			
		}
		
	}
	

}
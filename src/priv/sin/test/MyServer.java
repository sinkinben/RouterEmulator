package priv.sin.test;



import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.text.SimpleDateFormat;
import java.util.ArrayList;


public class MyServer {
	public static int counter = 0;
	public static ArrayList<Socket> socketsList = new ArrayList<>();
	public static void main(String[] args) throws IOException 
	{
		ServerSocket serverSocket = new ServerSocket();
		InetSocketAddress address = new InetSocketAddress("localhost", 18824);
		serverSocket.bind(address);
		
		while(true)
		{
			Socket socket = serverSocket.accept();
			SocketThread socketHandler = new SocketThread(socket, counter++, socketsList);
			new Thread(socketHandler).start();
		}
	}
}

class SocketThread implements Runnable{
	public static final String datePattern = "yyyy-MM-dd HH:mm:ss SSS";
	public static final SimpleDateFormat dateFormat = new SimpleDateFormat(datePattern);
	private Socket socket;
	private int order;
	private ArrayList<Socket> socketsList;
	
	public SocketThread(Socket socket, int order, ArrayList<Socket> socketsList) {
		// TODO Auto-generated constructor stub
		this.socket = socket;
		this.order = order;
		this.socketsList = socketsList;
	}
	private void send(Data data) throws IOException
	{
		for (Socket s : socketsList)
		{
			if (s == socket)
				continue;
			ObjectOutputStream outputStream = new ObjectOutputStream(new BufferedOutputStream(s.getOutputStream()));
			data.content+="after server";
			outputStream.writeObject(data);
			outputStream.flush();
			outputStream.close();
		}
	}
	@Override
	public void run() {
		// TODO Auto-generated method stub
		System.out.println("Client " + socket.getPort() + "连接成功！");
		try {
			ObjectInputStream  inputStream  = new ObjectInputStream(new BufferedInputStream(socket.getInputStream()));
			
			Object object = inputStream.readObject();
			Data data = (Data)object;
			System.out.println("Server " + order + " get from" + socket.getPort() +" ["+ data.toString() + "]");
			send(data);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	public int getSocket()
	{
		return socket.getPort();
	}
}

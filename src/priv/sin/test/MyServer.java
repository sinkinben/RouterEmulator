package priv.sin.test;



import java.io.BufferedInputStream;
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
	public static ArrayList<RouterThread> routerThreads = new ArrayList<>();
	public static void main(String[] args) throws IOException 
	{
		ServerSocket serverSocket = new ServerSocket();
		InetSocketAddress address = new InetSocketAddress("localhost", 18824);
		serverSocket.bind(address);
		
		while(true)
		{
			Socket socket = serverSocket.accept();
			RouterThread thread = new RouterThread(socket, counter++, routerThreads);
			routerThreads.add(thread);
			new Thread(thread).start();
		}
	}
}

class RouterThread implements Runnable{
	public static final String datePattern = "yyyy-MM-dd HH:mm:ss SSS";
	public static final SimpleDateFormat dateFormat = new SimpleDateFormat(datePattern);
	private Socket socket;
	private int order;
	private ArrayList<RouterThread> routerThreads;
	private ObjectInputStream inputStream;
	private ObjectOutputStream outputStream; 
	public RouterThread(Socket socket, int order, ArrayList<RouterThread> routerThreads) throws IOException 
	{
		// TODO Auto-generated constructor stub
		this.socket = socket;
		this.order = order;
		this.routerThreads = routerThreads;
		outputStream = new ObjectOutputStream(socket.getOutputStream());
		inputStream = new ObjectInputStream(new BufferedInputStream(socket.getInputStream()));
	}
	public void send(Data data) throws IOException
	{
		outputStream.writeObject(data);
		outputStream.flush();
	}
	
	public void boardcast(Data data) throws IOException
	{
		for (RouterThread r : routerThreads)
		{
			if (r!=this)
			{
				r.send(data);
			}
		}
	}
	@Override
	public void run() {
		// TODO Auto-generated method stub
		System.out.println("Client " + socket.getPort() + "连接成功！");
		while(true)
		{
			try {
				Object object = inputStream.readObject();
				Data data = (Data)object;
				System.out.println("Server " + order + " get from " + socket.getPort() + data.toString() );
				boardcast(data);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
	}
}

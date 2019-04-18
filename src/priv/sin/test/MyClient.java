package priv.sin.test;


import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.UnknownHostException;
import java.text.SimpleDateFormat;

public class MyClient {
	public static final String datePattern = "yyyy-MM-dd HH:mm:ss SSS";
	public static final SimpleDateFormat dateFormat = new SimpleDateFormat(datePattern);
	public static void main(String[] args) throws UnknownHostException, IOException, ClassNotFoundException {
		Socket socket = new Socket("localhost", 18824);
		ObjectOutputStream outputStream = new ObjectOutputStream(new BufferedOutputStream(socket.getOutputStream()));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in)); 
		String content = br.readLine();
		System.out.println(content);
		Data data = new Data(content, 0, 0);
		outputStream.writeObject(data);
		outputStream.flush();

		System.out.println("writefin");
		ObjectInputStream inputStream = new ObjectInputStream(new BufferedInputStream(socket.getInputStream()));
		data = (Data)inputStream.readObject();
		System.out.println("Client" + data.toString());
		
		inputStream.close();
		//socket.close();
	}
};

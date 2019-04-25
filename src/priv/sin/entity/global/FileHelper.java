package priv.sin.entity.global;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import org.junit.Test;

public class FileHelper {
	private static final String ipBuffer = "IP-buffer.txt";
	public static final String currentIP = "CurrentIP.txt";
	public static void clearLog()
	{
		File dir = new File(Global.logPath);
		if (dir.exists())
		{
			File[] files = dir.listFiles();
			for (int i = 0; i < files.length; i++)
			{
				if (files[i].isFile())
					files[i].delete();
			}
		}
	}
	public static void clearCurrentIP() throws IOException
	{
		FileWriter fileWriter = new FileWriter(new File(currentIP),false);
		fileWriter.flush();
		fileWriter.close();
	}
	
	public static void sendIP(int ip) throws IOException
	{
		FileWriter fileWriter = new FileWriter(new File(ipBuffer),false);
		fileWriter.write(Global.ipv4String(ip));
		fileWriter.flush();
		fileWriter.close();
		
		fileWriter = new FileWriter(new File(currentIP), true);
		fileWriter.write(Global.ipv4String(ip)+"\n");
		fileWriter.flush();
		fileWriter.close();
	}
	
	public static int recvIP() throws IOException
	{
		BufferedReader bufferedReader = new BufferedReader(new FileReader("IP-buffer.txt"));
		String ipStr = bufferedReader.readLine();
		bufferedReader.close();
		return Global.string2ipv4(ipStr);
	}
	@Test
	public void init() throws IOException
	{
		clearLog();
		clearCurrentIP();
	}
}

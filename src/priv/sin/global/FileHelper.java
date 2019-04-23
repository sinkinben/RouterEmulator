package priv.sin.global;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class FileHelper {
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
	
	public static void sendIP(int ip) throws IOException
	{
		FileWriter fileWriter = new FileWriter(new File("IP-buffer.txt"),false);
		fileWriter.write(Global.ipv4String(ip));
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
	
	public static void init()
	{
		clearLog();
	}
}

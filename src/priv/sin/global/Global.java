package priv.sin.global;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.management.ManagementFactory;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Global {
	public static final String datePattern = "yyyy-MM-dd HH:mm:ss";
	public static final SimpleDateFormat dateFormat = new SimpleDateFormat(datePattern);
	
	public static final String hostName = "localhost";
	public static final int port = 18824;
	public static int hostCounter = 0;
	
	public static final String dataContent = "161630230";
	
	public static final String logPath = "log/";
	
	private static final int NETID_MAX = 3;
	private static final int NETID_MIN = 1;	
	
	
	public static String getTime()
	{
		return dateFormat.format(new Date());
	}
	
	public static void printLog(String filename, String msg) throws IOException
	{
		FileWriter fileWriter = new FileWriter(new File(Global.logPath + filename), true);
		System.err.println("[" + getTime() + " " + msg + "]");
		fileWriter.write("[" + getTime() + " " + msg + "]\n");
		fileWriter.flush();
		fileWriter.close();
	}
	
	public static int getPid()
	{
		String name = ManagementFactory.getRuntimeMXBean().getName();
		String pid = name.split("@")[0];
		return Integer.valueOf(pid);
	}
	
	public static int getRandom(int min, int max)
	{
		return (int)(min + Math.random() * (max - min + 1));
	}
	public static int getNetid()
	{
		return getRandom(NETID_MIN, NETID_MAX);
	}
	
}

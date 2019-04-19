package priv.sin.global;

import java.io.File;

public class Helper {
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
}

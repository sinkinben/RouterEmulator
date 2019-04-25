package priv.sin.gui.global;

import java.awt.Font;
import java.util.Enumeration;

import javax.swing.UIManager;
import javax.swing.plaf.FontUIResource;

public class GuiGlobal {
	public static final String icon = "icons/";
	public static final String hostIcon = "icons/host.png";
	public static final String routerIcon = "icons/router.png";
	
	public static final Font fontEN = new Font("ËÎÌו", Font.BOLD, 16);
	
	public static void initGlobalFont()
	{
		FontUIResource fontUIResource = new FontUIResource(fontEN);
		for (Enumeration<Object> keys = UIManager.getDefaults().keys();keys.hasMoreElements();)
		{
			Object key = keys.nextElement();
			Object val = UIManager.get(key);
			if (val instanceof FontUIResource)
				UIManager.put(key, fontUIResource);
		}
	}
	
	
}

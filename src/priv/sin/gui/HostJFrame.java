package priv.sin.gui;

import java.awt.HeadlessException;

import javax.swing.JFrame;
import javax.swing.JSplitPane;

public class HostJFrame extends JFrame{
	private int hostPid;
	private int hostNetid;
	private int hostip;
	
	private JSplitPane upSplitPane = new JSplitPane(JSplitPane.VERTICAL_SPLIT);
	private JSplitPane downSplitPane = new JSplitPane(JSplitPane.VERTICAL_SPLIT);
	
	private HostTopJPanel hostTopJPanel;
	private HostBotJPanel hostBotJPanel;
	private HostMidJPanel hostMidJPanel;
	public HostJFrame(int hostPid, int hostNetid, int hostip) throws HeadlessException {
		super();
		this.hostPid = hostPid;
		this.hostNetid = hostNetid;
		this.hostip = hostip;
		
		hostTopJPanel = new HostTopJPanel(hostPid, hostNetid, hostip);
		hostMidJPanel = new HostMidJPanel();
		hostBotJPanel = new HostBotJPanel();
		
		setTitle("Host " + hostPid);
		setVisible(true);
		setSize(500, 500);
		
		upSplitPane.setDividerLocation(getHeight()/4);
		upSplitPane.setTopComponent(hostTopJPanel);
		
		downSplitPane.setTopComponent(hostMidJPanel);
		downSplitPane.setBottomComponent(hostBotJPanel);
		downSplitPane.setDividerLocation(upSplitPane.getDividerLocation());
		upSplitPane.setBottomComponent(downSplitPane);
		
		setContentPane(upSplitPane);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
	}
	
}


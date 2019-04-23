package priv.sin.gui.router;

import java.awt.HeadlessException;

import javax.swing.JFrame;
import javax.swing.JSplitPane;

public class RouterJFrame extends JFrame{
	private int routerPID;
	private int socketPort;
	
	private JSplitPane mainSplitPane = new JSplitPane(JSplitPane.VERTICAL_SPLIT);
	private JSplitPane upSplitPane = new JSplitPane(JSplitPane.VERTICAL_SPLIT);
	private JSplitPane downSplitPane = new JSplitPane(JSplitPane.VERTICAL_SPLIT);
	
	private RouterInfoJPanel routerInfoJPanel;
	private RouterThreadsTable routerThreadsTable;
	private RouterMemoryJPanel routerMemoryJPanel;
	public RouterJFrame(int routerPID, int socketPort) throws HeadlessException {
		super();
		this.routerPID = routerPID;
		this.socketPort = socketPort;
		routerInfoJPanel = new RouterInfoJPanel(routerPID, socketPort);
		routerThreadsTable = new RouterThreadsTable();
		routerMemoryJPanel = new RouterMemoryJPanel();
		setTitle("Router " + routerPID);
		setVisible(true);
		setSize(600, 600);
		
		
		
		mainSplitPane.setDividerLocation(getHeight()/2);
		mainSplitPane.setTopComponent(upSplitPane);
		mainSplitPane.setBottomComponent(downSplitPane);
		
		upSplitPane.setDividerLocation(getHeight()/4);
		upSplitPane.setTopComponent(routerInfoJPanel);
		upSplitPane.setBottomComponent(routerThreadsTable);
		
		//downSplitPane.setDividerLocation(upSplitPane.getDividerLocation());
		downSplitPane.setTopComponent(routerMemoryJPanel);
		//downSplitPane.setBottomComponent(new JPanel());
		
		
		setContentPane(mainSplitPane);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		
	}
	
	

}

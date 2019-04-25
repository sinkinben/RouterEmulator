package priv.sin.gui.router;

import java.awt.Color;
import java.awt.HeadlessException;

import javax.swing.JFrame;
import javax.swing.JSplitPane;

import priv.sin.entity.global.Global;
import priv.sin.entity.router.RouterMemory;
import priv.sin.entity.router.RoutingTable;

public class RouterJFrame extends JFrame{
	private int routerPID;
	private int socketPort;
	
	private JSplitPane mainSplitPane = new JSplitPane(JSplitPane.VERTICAL_SPLIT);
	private JSplitPane upSplitPane = new JSplitPane(JSplitPane.VERTICAL_SPLIT);
	private JSplitPane downSplitPane = new JSplitPane(JSplitPane.VERTICAL_SPLIT);
	
	private RouterInfoJPanel routerInfoJPanel;
	private RouterThreadsJPanel routerThreadsTable;
	private RouterTableJPanel routerTableJPanel;
	private RouterMemoryJPanel routerMemoryJPanel;
	public RouterJFrame(int routerPID, int socketPort, RoutingTable routingTable) throws HeadlessException {
		super();
		this.routerPID = routerPID;
		this.socketPort = socketPort;
		
		routerInfoJPanel = new RouterInfoJPanel(routerPID, socketPort);
		routerThreadsTable = new RouterThreadsJPanel();
		routerTableJPanel = new RouterTableJPanel();
		routerMemoryJPanel = new RouterMemoryJPanel();
		
		setTitle("Router " + routerPID);
		setVisible(true);
		setSize(600, 1000);
		
		initRouterTable(routingTable);
		
		mainSplitPane.setDividerLocation(getHeight()/2);
		mainSplitPane.setTopComponent(upSplitPane);
		mainSplitPane.setBottomComponent(downSplitPane);
		
		upSplitPane.setDividerLocation(getHeight()/4);
		upSplitPane.setTopComponent(routerInfoJPanel);
		upSplitPane.setBottomComponent(routerThreadsTable);
		
		downSplitPane.setDividerLocation(upSplitPane.getDividerLocation());
		downSplitPane.setTopComponent(routerTableJPanel);
		downSplitPane.setBottomComponent(routerMemoryJPanel);
		
		
		setContentPane(mainSplitPane);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		
	}
	
	public void addRouterThreadItem(int tid, int ip, int port)
	{
		routerThreadsTable.addItem(tid, Global.ipv4String(ip), port);
	}
	
	
	public void initRouterTable(RoutingTable routingTable)
	{
		routerTableJPanel.initTable(routingTable);
	}
	
	public void changeMemoryState(Color color)
	{
		int in = RouterMemory.getIn();
		int out = RouterMemory.getOut();
		routerMemoryJPanel.setColor(in, out, color);
		
	}
	
	
	

	
	

}

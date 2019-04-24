package priv.sin.gui.host;

import java.awt.HeadlessException;

import javax.swing.JFrame;
import javax.swing.JSplitPane;

import priv.sin.entity.data.Data;
import priv.sin.entity.data.DataPackage;
import priv.sin.entity.global.Global;


public class HostJFrame extends JFrame{
	private int hostPid;
	private int hostNetid;
	private int hostip;
	
	private JSplitPane mainSplitPane = new JSplitPane(JSplitPane.VERTICAL_SPLIT);
	private JSplitPane upSplitPane = new JSplitPane(JSplitPane.VERTICAL_SPLIT);
	private JSplitPane downSplitPane = new JSplitPane(JSplitPane.VERTICAL_SPLIT);
	
	private HostInfoJPanel hostInfoJPanel;
	private HostTableJPanel hostMsgSendJPanel;
	private HostTableJPanel hostMsgRecvJPanel;
	private HostSendActionJPanel hostSendActionJPanel;
	public HostJFrame(int hostPid, int hostNetid, int hostip) throws HeadlessException {
		super();
		this.hostPid = hostPid;
		this.hostNetid = hostNetid;
		this.hostip = hostip;
		
		hostInfoJPanel = new HostInfoJPanel(hostPid, hostNetid, hostip);
		hostMsgRecvJPanel = new HostTableJPanel("接收报文信息");
		hostMsgSendJPanel = new HostTableJPanel("发送报文信息");
		hostSendActionJPanel = new HostSendActionJPanel();
		
		setTitle("Host " + hostPid);
		setVisible(true);
		setSize(500, 500);
		
		mainSplitPane.setDividerLocation(getHeight()/2);
		mainSplitPane.setTopComponent(upSplitPane);
		mainSplitPane.setBottomComponent(downSplitPane);
		
		upSplitPane.setDividerLocation(getHeight()/4);
		upSplitPane.setTopComponent(hostInfoJPanel);
		upSplitPane.setBottomComponent(hostSendActionJPanel);
		
		downSplitPane.setTopComponent(hostMsgSendJPanel);
		downSplitPane.setBottomComponent(hostMsgRecvJPanel);
		downSplitPane.setDividerLocation(upSplitPane.getDividerLocation());
		
		
		
		setContentPane(mainSplitPane);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
	}
	
	public String getActionIp()
	{
		return hostSendActionJPanel.getDstIpStr();
	}
	
	public String getActionMsg()
	{
		return hostSendActionJPanel.getMsgStr();
	}
	
	public void addMsgSendItem(Data data)
	{
		hostMsgSendJPanel.addItem(Global.getTime(), data.getSrcIPString(), data.getDstIPString(), 
				                  data.getSendOrder(), hostPid, data.getMsgContent().getMsg());
	}
	
	public void addMsgSendPackage(DataPackage dataPackage)
	{
		System.out.println("addMsgSendPackage");
		int size = dataPackage.getPackageSize();
		for (int i=0;i<size;i++)
		{
			addMsgSendItem(dataPackage.datas[i]);
		}
		hostMsgSendJPanel.updateUI();
	}
	
}


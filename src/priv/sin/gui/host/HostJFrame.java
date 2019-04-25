package priv.sin.gui.host;

import java.awt.HeadlessException;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import javax.swing.JFrame;
import javax.swing.JSplitPane;

import priv.sin.entity.data.Data;
import priv.sin.entity.data.DataPackage;
import priv.sin.entity.global.FileHelper;
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
		setSize(600, 800);
		
		mainSplitPane.setDividerLocation(getHeight()/3);
		mainSplitPane.setTopComponent(upSplitPane);
		mainSplitPane.setBottomComponent(downSplitPane);
		
		upSplitPane.setDividerLocation(mainSplitPane.getDividerLocation()/2);
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
	private int checkItemStatus(Data data) throws IOException
	{
		int status = 0;
		int dstVal = data.getDstIP();
		String dstString = data.getDstIPString();
		
		BufferedReader reader = new BufferedReader(new FileReader(FileHelper.currentIP));
		String ipString;
		while ((ipString = reader.readLine()) != null)
		{
			if (ipString.equals(dstString))
			{
				status = 1;
				break;
			}
		}
		reader.close();
		return status;
	}

	private void addMsgSendItem(Data data) throws IOException
	{
		int status = checkItemStatus(data);
		hostMsgSendJPanel.addItem(Global.getTime(), data.getSrcIPString(), data.getDstIPString(), 
				                  data.getSendOrder(), hostPid, data.getMsgContent().getMsg(), status);
	}
	
	public void addMsgSendPackage(DataPackage dataPackage) throws IOException
	{
		System.out.println("addMsgSendPackage");
		int size = dataPackage.getPackageSize();
		for (int i=0;i<size;i++)
		{
			addMsgSendItem(dataPackage.datas[i]);
		}
		hostMsgSendJPanel.repaint();
	}
	
	private void addMsgRecvItem(Data data) throws IOException
	{
		int status = checkItemStatus(data);
		hostMsgRecvJPanel.addItem(Global.getTime(), data.getSrcIPString(), data.getDstIPString(), 
				     			  data.getSendOrder(), data.getMsgContent().getSrcPid(), data.getMsgContent().getMsg(), status);
	}
	
	public void addMsgRecvPackage(DataPackage dataPackage) throws IOException 
	{
		int size = dataPackage.getPackageSize();
		for (int i = 0; i < size; i++)
		{
			addMsgRecvItem(dataPackage.datas[i]);
		}
		hostMsgRecvJPanel.repaint();
	}

}


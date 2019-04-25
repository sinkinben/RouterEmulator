package priv.sin.gui.host;

import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

import priv.sin.entity.global.Global;
import priv.sin.gui.global.GuiGlobal;

/*
 * left is icon
 * right is info of host
 */
public class HostInfoJPanel extends JPanel{
	private JPanel rightJPanel;
	
	public HostInfoJPanel(int pid, int netid, int ip)
	{
		setBorder(BorderFactory.createTitledBorder("主机信息"));
		setLayout(new GridLayout(1, 3));
		add(new JLabel(new ImageIcon(GuiGlobal.hostIcon)));
		initRightJPanel(pid, netid, ip);
		add(rightJPanel);
	}
	
	private void initRightJPanel(int pid, int netid, int ip)
	{
		rightJPanel = new JPanel(new GridLayout(3, 1));
		JLabel pidLabel = new JLabel("    ·Host pid: " + pid);
		JLabel netLabel = new JLabel("    ·Host net-id: " + Global.ipv4String(netid));
		JLabel ipLabel  = new JLabel("    ·Host ip: " + Global.ipv4String(ip));
		rightJPanel.add(pidLabel);
		rightJPanel.add(netLabel);
		rightJPanel.add(ipLabel);
	}
}

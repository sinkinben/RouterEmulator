package priv.sin.gui;

import java.awt.BorderLayout;
import java.awt.GridLayout;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

import priv.sin.global.Global;

/*
 * left is icon
 * right is info of host
 */
public class HostTopJPanel extends JPanel{
	private JPanel rightJPanel;
	
	public HostTopJPanel(int pid, int netid, int ip)
	{
		setLayout(new BorderLayout());
		add(new JLabel(new ImageIcon(GuiGlobal.hostIcon)), BorderLayout.WEST);
		initRightJPanel(pid, netid, ip);
		add(rightJPanel, BorderLayout.CENTER);
	}
	
	private void initRightJPanel(int pid, int netid, int ip)
	{
		rightJPanel = new JPanel(new GridLayout(3, 1));
		JLabel pidLabel = new JLabel("    ¡¤Host pid: " + pid);
		JLabel netLabel = new JLabel("    ¡¤Host net-id: " + netid);
		JLabel ipLabel  = new JLabel("    ¡¤Host ip: " + Global.ipv4String(ip));
		rightJPanel.add(pidLabel);
		rightJPanel.add(netLabel);
		rightJPanel.add(ipLabel);
	}
}

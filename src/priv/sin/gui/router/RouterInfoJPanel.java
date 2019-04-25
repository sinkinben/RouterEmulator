package priv.sin.gui.router;

import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

import priv.sin.gui.global.GuiGlobal;

public class RouterInfoJPanel extends JPanel{
	private JPanel rightPanel;
	private JLabel label1, label2, label3;
	public RouterInfoJPanel(int routerpid, int socketport)
	{
		initRightPanel(routerpid, socketport);
		setBorder(BorderFactory.createTitledBorder("路由器信息"));
		setLayout(new GridLayout(1, 3));
		add(new JLabel(new ImageIcon(GuiGlobal.routerIcon)));
		
		add(rightPanel);
		
	}
	private void initRightPanel(int pid, int port) {
		// TODO Auto-generated method stub
		rightPanel = new JPanel(new GridLayout(3,1));
		label1 = new JLabel("    ·Router PID: " + pid);
		label2 = new JLabel("    ·Router port: " + port);
		rightPanel.add(label1);
		rightPanel.add(label2);
	}
}

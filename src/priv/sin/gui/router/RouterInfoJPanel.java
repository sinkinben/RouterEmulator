package priv.sin.gui.router;

import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

import priv.sin.gui.host.GuiGlobal;

public class RouterInfoJPanel extends JPanel{
	private JPanel rightPanel;
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
		JLabel label = new JLabel("    ·Router pid: "+ pid);
		JLabel label2= new JLabel("    ·Socket Port: " + port);
		rightPanel.add(label);
		rightPanel.add(label2);
	}
}

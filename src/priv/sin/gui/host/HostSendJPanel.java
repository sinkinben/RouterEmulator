package priv.sin.gui.host;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class HostSendJPanel extends JPanel{
	private JTextField dstIpJtf = new JTextField("127.0.0.1");
	private JTextField msgJtf = new JTextField("161630230");
	private JButton confirmJbt = new JButton("确认发送");
	public HostSendJPanel()
	{
		initListener();
		setBorder(BorderFactory.createTitledBorder("发送信息"));
		setLayout(new GridLayout(3,2));
		JLabel label1 = new JLabel("目的IP地址：");
		JLabel label2 = new JLabel("报文内容：");
		
		label1.setPreferredSize(new Dimension(100, 30));
		label2.setPreferredSize(new Dimension(100, 30));
		dstIpJtf.setPreferredSize(new Dimension(300, 30));
		msgJtf.setPreferredSize(new Dimension(300,30 ));
		add(label1);
		add(dstIpJtf);
		add(label2);
		add(msgJtf);
		add(new JLabel(""));
		JPanel jPanel = new JPanel(new FlowLayout());
		jPanel.add(confirmJbt);
		add(jPanel);
	}
	
	private void initListener()
	{
		confirmJbt.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				System.out.println("Confirm Button Clicked");
			}
		});
	}
	
	
}

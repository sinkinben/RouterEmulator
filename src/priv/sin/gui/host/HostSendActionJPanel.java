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

import priv.sin.entity.host.HostWriter;

public class HostSendActionJPanel extends JPanel{
	private JTextField dstIpJtf = new JTextField("202.119.64.0");
	private JTextField msgJtf = new JTextField("161630230");
	private JButton confirmJbt = new JButton("确认发送");
	private JButton switchModel = new JButton("手动模式");
	private String dstIpStr = null;
	private String msgStr = null;
	private boolean isAuto = false;
	public HostSendActionJPanel()
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
		
		JPanel p0 = new JPanel(new FlowLayout());
		p0.add(switchModel);
		add(p0);
		
		JPanel p1 = new JPanel(new FlowLayout());
		p1.add(confirmJbt);
		add(p1);
	}
	
	private void initListener()
	{
		confirmJbt.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				System.out.println("Confirm Button Clicked");
				dstIpStr = dstIpJtf.getText();
				msgStr = msgJtf.getText();
				HostWriter.setLock(false);
			}
		});
		switchModel.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				System.out.println("Switch Model");
				isAuto = !isAuto;
				String string = isAuto? "自动模式":"手动模式";
				switchModel.setText(string);
				HostWriter.switchAutoModel();
			}
		});
	}

	public String getDstIpStr() {
		return dstIpStr;
	}

	public String getMsgStr() {
		return msgStr;
	}





	
	
	
}

package priv.sin.gui.router;

import java.awt.BorderLayout;

import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;

import priv.sin.gui.host.GuiGlobal;

public class RouterThreadsJPanel extends JPanel{
	private static final int MAX_ITEMS = 20;
	private String[] colStrings = {"线程TID", "主机IP", "Socket端口"};
	private String[][] rowDatas = new String[MAX_ITEMS][colStrings.length];
	private int NR_ITEMS = 0;
	private JTable table;
	
	public RouterThreadsJPanel()
	{
		setFont(GuiGlobal.fontEN);
		setBorder(BorderFactory.createTitledBorder("当前连接主机"));
		setLayout(new BorderLayout());
		table = new JTable(rowDatas, colStrings);
		DefaultTableCellRenderer tcr = new DefaultTableCellRenderer();// 设置table内容居中
		tcr.setHorizontalAlignment(SwingConstants.CENTER);
		table.setDefaultRenderer(Object.class,tcr);
		((DefaultTableCellRenderer)table.getTableHeader().getDefaultRenderer()).setHorizontalAlignment(SwingConstants.CENTER);
		
		JScrollPane jScrollPane = new JScrollPane(table);
		jScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
		jScrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		
		add(jScrollPane, BorderLayout.CENTER);
	}
	
	public void addItem(int tid, String ipString, int port)
	{
		rowDatas[NR_ITEMS][0] = tid+"";
		rowDatas[NR_ITEMS][1] = ipString;
		rowDatas[NR_ITEMS][2] = port+"";
		NR_ITEMS++;
		table.repaint();
	}
}

package priv.sin.gui.router;

import java.awt.BorderLayout;

import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;

import priv.sin.gui.host.GuiGlobal;

public class RouterTableJPanel extends JPanel {
	private static final int MAX_ITEMS = 20;
	private String[] colNames = {"目的网络/IP地址", "下一跳"};
	private String[][] rowDatas = new String[MAX_ITEMS][colNames.length];
	private int NR_ITEM = 0;
	private JTable table;
	
	public RouterTableJPanel()
	{
		setLayout(new BorderLayout());
		setFont(GuiGlobal.fontEN);
		setBorder(BorderFactory.createTitledBorder("路由表信息"));
		
		table = new JTable(rowDatas, colNames);
		DefaultTableCellRenderer tcr = new DefaultTableCellRenderer();// 设置table内容居中
		tcr.setHorizontalAlignment(SwingConstants.CENTER);
		table.setDefaultRenderer(Object.class,tcr);
		((DefaultTableCellRenderer)table.getTableHeader().getDefaultRenderer()).setHorizontalAlignment(SwingConstants.CENTER);
		
		JScrollPane jScrollPane = new JScrollPane(table);
		jScrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		jScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
		
		add(jScrollPane, BorderLayout.CENTER);
	}
	
	public void addItem(String target, String next)
	{
		rowDatas[NR_ITEM][0] = target;
		rowDatas[NR_ITEM][1] = next;
		NR_ITEM++;
		table.repaint();
	}
}

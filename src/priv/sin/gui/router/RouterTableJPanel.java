package priv.sin.gui.router;

import java.awt.BorderLayout;

import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;

import priv.sin.entity.global.Global;
import priv.sin.entity.router.RoutingTable;
import priv.sin.gui.global.GuiGlobal;

public class RouterTableJPanel extends JPanel {
	private static final int MAX_ITEMS = 20;
	private String[] colNames = {"目的网络/IP地址", "下一跳", "交付方式"};
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
	
	public void addItem(String target, String next, MsgDeliveryType type)
	{
		rowDatas[NR_ITEM][0] = target;
		rowDatas[NR_ITEM][1] = next;
		rowDatas[NR_ITEM][2] = type == MsgDeliveryType.DIRECTLY ? "直接交付":"间接交付";
		NR_ITEM++;
		
	}
	
	public void initTable(RoutingTable routingTable)
	{
		int[] netids = routingTable.getNetids();
		int[] nextAddrs = routingTable.getNextAddrs();
		MsgDeliveryType type;
		int size = routingTable.getNR_TABLE();
		for (int i = 0; i < size; i++)
		{
			type = ((netids[i] & Global.ipMask) == nextAddrs[i]) ? MsgDeliveryType.DIRECTLY : MsgDeliveryType.INDIRECTLY;
			addItem(Global.ipv4String(netids[i]), Global.ipv4String(nextAddrs[i]), type);
		}
		table.repaint();
	}
}

enum MsgDeliveryType{
	DIRECTLY,
	INDIRECTLY,
};

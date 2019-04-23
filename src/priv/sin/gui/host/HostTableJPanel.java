package priv.sin.gui.host;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;

import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;

public class HostTableJPanel extends JPanel{
	private static final int MAX_ITEMS = 1024;
	private String[] colStrings = {"表格序号", "源地址", "目的地址", "发送序号", "源进程PID", "内容", "状态"};
	private String[][] rowDatas = new String[MAX_ITEMS][colStrings.length];
	private int NR_ITEMS = 0;
	private JTable table;
	public HostTableJPanel(String borderTitle)
	{
		setFont(GuiGlobal.fontEN);
		setBorder(BorderFactory.createTitledBorder(borderTitle));
		setLayout(new BorderLayout());
		table = new JTable(rowDatas, colStrings);
		DefaultTableCellRenderer tcr = new DefaultTableCellRenderer();// 设置table内容居中
		tcr.setHorizontalAlignment(SwingConstants.CENTER);
		table.setDefaultRenderer(Object.class,tcr);
		((DefaultTableCellRenderer)table.getTableHeader().getDefaultRenderer()).setHorizontalAlignment(SwingConstants.CENTER);
		
		
		JScrollPane jScrollPane = new JScrollPane(table);
		jScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
		jScrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		
		add(jScrollPane);
		addItem(1, "1", "2", 1, 123, "sssss");
		setOneRowBackgroundColor(table, 0, Color.RED);
	}
	
	public void addItem(int order, String srcIP, String dstIP, int sendOrder, int srcPID, String msg)
	{
		rowDatas[NR_ITEMS][0] = order+"";
		rowDatas[NR_ITEMS][1] = srcIP;
		rowDatas[NR_ITEMS][2] = dstIP;
		rowDatas[NR_ITEMS][3] = sendOrder+"";
		rowDatas[NR_ITEMS][4] = srcIP+"";
		rowDatas[NR_ITEMS][5] = msg;
		NR_ITEMS++;
		table.repaint();
	}
	public static void setOneRowBackgroundColor(JTable table, int rowIndex, Color color) 
	{		
		try 
		{			
			DefaultTableCellRenderer tcr = new DefaultTableCellRenderer() 
			{ 				
				public Component getTableCellRendererComponent(JTable table,Object value, boolean isSelected, boolean hasFocus,	int row, int column)
				{					
					if (row == rowIndex) 
					{						
						setBackground(color);						
						setForeground(Color.WHITE);					
					}
					else if(row > rowIndex)
					{		
						setBackground(Color.white);		
						setForeground(Color.WHITE);			
					}
					else
					{						
						setBackground(Color.white);						
						setForeground(Color.WHITE);					
					} 					
					return super.getTableCellRendererComponent(table, value,isSelected, hasFocus, row, column);				
				}			
			};
			tcr.setHorizontalAlignment(SwingConstants.CENTER);
			((DefaultTableCellRenderer)table.getTableHeader().getDefaultRenderer()).setHorizontalAlignment(SwingConstants.CENTER);
			int columnCount = table.getColumnCount();			
			for (int i = 0; i < columnCount; i++) 
			{				
				table.getColumn(table.getColumnName(i)).setCellRenderer(tcr);			
			}		
		} 
		catch (Exception ex) 
		{		
			ex.printStackTrace();		
		}	
	}

}
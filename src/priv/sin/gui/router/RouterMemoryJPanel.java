package priv.sin.gui.router;

import java.awt.Color;
import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JPanel;

import priv.sin.entity.router.RouterMemory;

public class RouterMemoryJPanel extends JPanel{
	private static final int cols = 32;
	private static final int rows = 32;
	private JButton[][] buttons = new JButton[rows][cols];
	public RouterMemoryJPanel()
	{
		setBorder(BorderFactory.createTitledBorder("存储器使用情况"));
		setLayout(new GridLayout(rows, cols));
		for (int i = 0; i < rows; i++)
		{
			for (int j = 0; j < cols; j++)
			{
				buttons[i][j] = getRectangle(Color.GREEN);
				add(buttons[i][j]);
			}
		}
	}
	
	private JButton getRectangle(Color color)
	{
		JButton button = new JButton("");
		button.setBackground(color);
		return button;
	}
	
	public void setColor(int in, int out, Color color)
	{
		int r,c;
		for (int i = out; i!=in; i = (i+1) % RouterMemory.MEMORY_SIZE)
		{
			r = (i/rows);
			c = (i%rows);
			buttons[r][c].setBackground(color);
		}
	}

}

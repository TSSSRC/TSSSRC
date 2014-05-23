package net.util;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;

public class Keypad extends JPanel

{
	public static Object selected;
	int _xPos;
	int _yPos;
	int _width;
	int _height;
	
	Object outputLocation;
	
	public Keypad(int width, int height, Object outputLocation)
	{
		_xPos = (width) * 2 / 3;
		_yPos = (height) / 3;
		_width = (width - 55) / 3;
		_height = (height - 60) * 2 / 3;
		this.setBounds(_xPos, _yPos, _width, _height);
		this.outputLocation = outputLocation;
		// this.setBorder(new LineBorder(new Color(0, 0, 0)));
		
		/*
		 * JButton btnOne = new JButton("1"); JButton btnTwo = new JButton("2");
		 * JButton btnThree = new JButton("3"); JButton btnFour = new
		 * JButton("4"); JButton btnFive = new JButton("5"); JButton btnSix =
		 * new JButton("6"); JButton btnSeven = new JButton("7"); JButton
		 * btnEight = new JButton("8"); JButton btnNine = new JButton("9");
		 * JButton btnZero = new JButton("0");
		 * 
		 * // LHS btnOne.setBounds(5, 5, (this.getBounds().width - 15) / 2,
		 * (this.getBounds().height - 30) / 5); btnThree.setBounds(5, (10 +
		 * (this.getBounds().height - 30) / 5), (this.getBounds().width - 15) /
		 * 2, (this.getBounds().height - 30) / 5); btnFive.setBounds(5, (15 +
		 * (this.getBounds().height - 30) * 2 / 5), (this.getBounds().width -
		 * 15) / 2, (this.getBounds().height - 30) / 5); btnSeven.setBounds(5,
		 * (20 + (this.getBounds().height - 30) * 3 / 5),
		 * (this.getBounds().width - 15) / 2, (this.getBounds().height - 30) /
		 * 5); btnNine.setBounds(5, (25 + (this.getBounds().height - 30) * 4 /
		 * 5), (this.getBounds().width - 15) / 2, (this.getBounds().height - 30)
		 * / 5); // RHS btnTwo.setBounds((10 + (this.getBounds().width - 15) /
		 * 2), 5, (this.getBounds().width - 15) / 2, (this.getBounds().height -
		 * 30) / 5); btnFour.setBounds((10 + (this.getBounds().width - 15) / 2),
		 * (10 + (this.getBounds().height - 30) / 5), (this.getBounds().width -
		 * 15) / 2, (this.getBounds().height - 30) / 5); btnSix.setBounds((10 +
		 * (this.getBounds().width - 15) / 2), (15 + (this.getBounds().height -
		 * 30) * 2 / 5), (this.getBounds().width - 15) / 2,
		 * (this.getBounds().height - 30) / 5); btnEight.setBounds((10 +
		 * (this.getBounds().width - 15) / 2), (20 + (this.getBounds().height -
		 * 30) * 3 / 5), (this.getBounds().width - 15) / 2,
		 * (this.getBounds().height - 30) / 5); btnZero.setBounds((10 +
		 * (this.getBounds().width - 15) / 2), (25 + (this.getBounds().height -
		 * 30) * 4 / 5), (this.getBounds().width - 15) / 2,
		 * (this.getBounds().height - 30) / 5);
		 * 
		 * this.add(btnOne); this.add(btnTwo); this.add(btnThree);
		 * this.add(btnFour); this.add(btnFive); this.add(btnSix);
		 * this.add(btnSeven); this.add(btnEight); this.add(btnNine);
		 * this.add(btnZero);
		 */
	}
	
	public void addButton(String content, String tag)
	{
		JButton newBtn = new JButton(content);
		newBtn.addActionListener(new ActionListener()
		{
			
			@Override
			public void actionPerformed(ActionEvent arg0)
			{
				if (arg0.getSource() instanceof JButton)
				{
					Keypad.output((JButton) arg0.getSource(), outputLocation);
				}
			}
		});
		this.add(newBtn);
		this.resizePanel(this.getWidth(), this.getHeight());
	}
	
	public static void output(JButton but, Object outputLocation)
	{
		if (selected != null && selected instanceof JLabel)
		{
			((JLabel) selected).setText(but.getText());
			((JLabel)selected).setBorder(null);
			selected = null;
			return;
		}
		JLabel label = new JLabel(but.getText());
		label.addMouseListener(new MouseListener()
		{
			
			@Override
			public void mouseReleased(MouseEvent arg0)
			{
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mousePressed(MouseEvent arg0)
			{
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseExited(MouseEvent arg0)
			{
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseClicked(MouseEvent arg0)
			{
				selected = arg0.getSource();
				((JLabel)arg0.getSource()).setBorder(new LineBorder(new Color(0, 0, 0)));
				
			}
			
			@Override
			public void mouseEntered(MouseEvent e)
			{
				// TODO Auto-generated method stub
				
			}
		});
		label.setBounds(0, 0, 100, 100);
		if (outputLocation instanceof JPanel)
		{
			((JPanel) outputLocation).add(label);
			((JPanel) outputLocation).getParent().getParent().getParent().validate();
		}
		
		if (outputLocation instanceof JLabel)
		{
			outputLocation = label;
			((JPanel) outputLocation).getParent().getParent().getParent().validate();
			
		}
	}
	
	/**
	 * @author Fabio
	 * @param width
	 * @param height
	 */
	public void resizePanel(int width, int height)
	{
		try
		{
			_xPos = (width) * 2 / 3;
			_yPos = (height) / 3;
			_width = (width - 55) / 3;
			_height = (height - 60) * 2 / 3;
			setBounds(_xPos, _yPos, _width, _height);
			int n = 0;
			for (int row = 0; row < 5; row++)
			{
				for (int col = 0; col < 2; col++)
				{
					int x = 0;
					int y = 0;
					int widthBtn = 0;
					int heightBtn = 0;
					if (col == 0)
					{
						x = 5;
						y = ((5 * (row + 1) + (_height - 30) * row / 5));
					}
					else
					{
						x = (10 + (_width - 15) / 2);
						y = ((5 * (row + 1) + (_height - 30) * row / 5));
					}
					widthBtn = (_width - 15) / 2;
					heightBtn = (_height - 30) / 5;
					
					this.getComponent(n).setBounds(x, y, widthBtn, heightBtn);
					n++;
				}
			}
		}
		catch (Exception e)
		{
		}
		
		/*
		 * this.setBounds(this.getX(), this.getY(), this.getWidth() + width,
		 * this.getHeight() + height); int butWidth = (this.getWidth() - 15) /
		 * 3; int butHeight = (this.getHeight() - 20) / 4; int n = 0; for(int
		 * row = 0; row < 3; row++) { for(int col = 0; col < 3; col++) { int x =
		 * 5 + (5 * col) + (butWidth * col); int y = 5 + (5 * row) + (butHeight*
		 * row); this.getComponent(n).setBounds(x, y, butWidth, butHeight); n++;
		 * } }
		 * 
		 * int x = 5 + (5 * 1) + (butWidth * 1); int y = 5 + (5 * 3) +
		 * (butHeight* 3); this.getComponent(n).setBounds(x, y, butWidth,
		 * butHeight);
		 */
	}
	
	public static Keypad getJudgesKeypad(int width, int height, Object outputLocation)
	{
		Keypad keypad = new Keypad(width, height, outputLocation);
		keypad.addButton("0.1", "");
		keypad.addButton("0.2", "");
		keypad.addButton("0.3", "");
		keypad.addButton("0.4", "");
		keypad.addButton("0.5", "");
		return keypad;
	}
	
}

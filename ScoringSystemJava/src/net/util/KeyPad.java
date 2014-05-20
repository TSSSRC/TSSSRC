package net.util;

import javax.swing.JButton;
import javax.swing.JPanel;

public class KeyPad extends JPanel
{
	public KeyPad()
	{
		JButton btnNewButton = new JButton("New button1");
		JButton btnNewButton2 = new JButton("New button2");
		
		btnNewButton.setBounds(58, 196, 52, 23);
		btnNewButton2.setBounds(58, 56, 152, 23);
		this.add(btnNewButton);
		this.add(btnNewButton2);
		this.setBounds(311, 141, 239, 160);
	}
}

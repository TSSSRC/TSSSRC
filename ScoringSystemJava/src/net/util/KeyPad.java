package net.util;

import javax.swing.JButton;
import javax.swing.JPanel;

public class KeyPad extends JPanel
{
	public KeyPad()
	{
		JButton btnNewButton = new JButton("New button1");
		
		btnNewButton.setBounds(58, 196, 52, 23);
		this.add(btnNewButton);
		this.setBounds(311, 141, 239, 160);
	}
}

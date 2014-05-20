package net.util;

import javax.swing.JButton;
import javax.swing.JPanel;

public class KeyPad1 extends JPanel
{
	public KeyPad1()
	{
		JButton btnNewButton = new JButton("New button1");
		
		btnNewButton.setBounds(58, 196, 52, 23);
		this.add(btnNewButton);
		this.setBounds(311, 141, 239, 160);
	}
}

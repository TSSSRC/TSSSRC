package net.util;

import javax.swing.JLabel;

public class JLabelExt extends JLabel{
	private int tag;
	JLabelExt(String txt, int t){
		super(txt);
		tag = t;
	}
	public int getTag(){
		return tag;
	}

}

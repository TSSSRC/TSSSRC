package net.window.frames.admin;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import net.util.KeyPad;

public class AdminFrame extends JFrame
{
	
	/**
	 * 
	 */
    private static final long serialVersionUID = 4638510630518996374L;
	private JPanel contentPane;
	private int width=680;
	private int height = 480;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args)
	{
		EventQueue.invokeLater(new Runnable()
		{
			public void run()
			{
				try
				{
					AdminFrame frame = new AdminFrame();
					frame.setVisible(true);
				}
				catch (Exception e)
				{
					e.printStackTrace();
				}
			}
		});
	}
	
	/**
	 * Create the frame.
	 */
	public AdminFrame()
	{

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		setBounds(100, 100, width, height);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		final JPanel keypad = new KeyPad(414, 25, 300, 200);
		keypad.setSize(320, 316);
		keypad.setLocation(334, 114);
		
		
		contentPane.add(keypad);
		keypad.setLayout(null);
		
		JLabel lblAdmin = new JLabel("Admin");
		lblAdmin.setBounds(75, 0, 471, 91);
		contentPane.add(lblAdmin);
		lblAdmin.setHorizontalAlignment(SwingConstants.CENTER);
		lblAdmin.setFont(new Font("Tahoma", Font.PLAIN, 24));

		addComponentListener(new ComponentAdapter() {
			@Override
			public void componentResized(ComponentEvent e) {
				if(e.getComponent() instanceof JFrame)
				{
					int width = contentPane.getWidth() - e.getComponent().getWidth();
					int height = contentPane.getHeight() - e.getComponent().getHeight();
					((KeyPad)keypad).resizeButtons(width, height);
				}
			}
		});
		
	}
}

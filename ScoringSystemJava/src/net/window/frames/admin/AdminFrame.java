package net.window.frames.admin;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import net.util.Keypad;
import java.awt.FlowLayout;

public class AdminFrame extends JFrame
{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 4638510630518996374L;
	private JPanel contentPane;
	static AdminFrame frame;
	private int width = 680;
	private int height = 480;
	int xChange = 0;
	int yChange = 0;
	
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
					frame = new AdminFrame();
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
		
		JPanel panel = new JPanel();
		panel.setBounds(224, 104, 21, 208);
		contentPane.add(panel);
		
		final JPanel keypad = Keypad.getJudgesKeypad(width, height, panel);
		panel.setLayout(new FlowLayout(FlowLayout.LEADING, 5, 5));
		keypad.setSize(277, 329);
		keypad.setLocation(350, 87);
		
		contentPane.add(keypad);
		keypad.setLayout(null);
		
		JLabel lblAdmin = new JLabel("Admin");
		lblAdmin.setBounds(82, 0, 471, 91);
		contentPane.add(lblAdmin);
		lblAdmin.setHorizontalAlignment(SwingConstants.CENTER);
		lblAdmin.setFont(new Font("Tahoma", Font.PLAIN, 24));
		
		JButton btnNewButton = new JButton("New button");
		
		btnNewButton.setBounds(67, 116, 89, 23);
		contentPane.add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("New button");
		btnNewButton_1.setBounds(67, 218, 89, 23);
		contentPane.add(btnNewButton_1);
		
		addComponentListener(new ComponentAdapter()
		{
			@Override
			public void componentResized(ComponentEvent e)
			{
				
				width = frame.getWidth();
				height = frame.getHeight();
				// System.out.println("Width: "+ width+ " Height: " + height);
				((Keypad) (keypad)).resizePanel(width, height);
			}
		});
		
	}
}

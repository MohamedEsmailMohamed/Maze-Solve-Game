package PacManGit;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import java.awt.Color;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Font;
import javax.swing.ImageIcon;

public class MainMenu {

	public JFrame frmMainmenu;
	public JButton easyBtn = new JButton("");
	public JButton Hard = new JButton("");
	public JButton Option = new JButton("");
	public JButton normalBtn = new JButton("");
	public JPanel mainMenu = new JPanel();
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainMenu window = new MainMenu();
					window.frmMainmenu.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public MainMenu() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmMainmenu = new JFrame();
		frmMainmenu.setTitle("MainMenu");
		frmMainmenu.setBounds(0, 0, 1000,735 );
		frmMainmenu.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmMainmenu.getContentPane().setLayout(null);
		
		
		mainMenu.setBackground(new Color(0, 255, 0));
		mainMenu.setBounds(0, 0, 1000, 735);
		frmMainmenu.getContentPane().add(mainMenu);
		mainMenu.setLayout(null);
		easyBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		
		easyBtn.setForeground(new Color(0, 0, 0,0));
		easyBtn.setOpaque(false);
		easyBtn.setBorderPainted(false);
		easyBtn.setContentAreaFilled(false);
		easyBtn.setPressedIcon(new ImageIcon("pictures\\Btns\\Easy_HoveUnfill.png"));
		easyBtn.setSelectedIcon(new ImageIcon("pictures\\Btns\\Easy_HoveUnfill.png"));
		easyBtn.setIcon(new ImageIcon("pictures\\Btns\\EasyUnFill.png"));
		easyBtn.setBackground(new Color(0, 0, 0,0));
		
		easyBtn.setFont(new Font("Arial Black", Font.BOLD, 14));

		easyBtn.setBounds(442, 85, 149, 146);
		mainMenu.add(easyBtn);
		
		
		normalBtn.setIcon(new ImageIcon("pictures\\Btns\\NomalUnfill.png"));
		normalBtn.setPressedIcon(new ImageIcon("pictures\\Btns\\normalHove_1.png"));
		normalBtn.setOpaque(false);
		normalBtn.setForeground(new Color(0, 0, 0, 0));
		normalBtn.setFont(new Font("Arial Black", Font.BOLD, 14));
		normalBtn.setContentAreaFilled(false);
		normalBtn.setBorderPainted(false);
		normalBtn.setBackground(new Color(0, 0, 0, 0));
		normalBtn.setBounds(442, 299, 149, 139);
		mainMenu.add(normalBtn);
		

		Hard.setIcon(new ImageIcon("pictures\\Btns\\Hard.png"));
		Hard.setPressedIcon(new ImageIcon("pictures\\Btns\\HardHove.png"));
		Hard.setOpaque(false);
		Hard.setForeground(new Color(0, 0, 0, 0));
		Hard.setFont(new Font("Arial Black", Font.BOLD, 14));
		Hard.setContentAreaFilled(false);
		Hard.setBorderPainted(false);
		Hard.setBackground(new Color(0, 0, 0, 0));
		Hard.setBounds(442, 489, 149, 139);
		mainMenu.add(Hard);

		Option.setIcon(new ImageIcon("pictures\\Btns\\Option.png"));
		Option.setPressedIcon(new ImageIcon("pictures\\Btns\\OptionHove.png"));
		Option.setOpaque(false);
		Option.setForeground(new Color(0, 0, 0, 0));
		Option.setFont(new Font("Arial Black", Font.BOLD, 14));
		Option.setContentAreaFilled(false);
		Option.setBorderPainted(false);
		Option.setBackground(new Color(0, 0, 0, 0));
		Option.setBounds(830, 26, 96, 98);
		mainMenu.add(Option);
		JLabel backGround = new JLabel(new ImageIcon("pictures\\Btns\\Maze.jpg"));
		backGround.setBounds(0, 0, 1000,735);
		mainMenu.add(backGround);
	}
}

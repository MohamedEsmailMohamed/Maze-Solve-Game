package PacManGit;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;


import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JSeparator;
import java.awt.Color;
import javax.swing.SwingConstants;

@SuppressWarnings("serial")
public class Options extends JFrame {
ImageFlyWeight fly =ImageFlyWeight.getinstance();
	private JPanel contentPane;
	private JLabel bombimage;
	private JLabel bigBombimage;
	private JLabel treeimage;
	private JLabel spaceimage;
	private JLabel wallimage;
	private JLabel giftimage;
	private JLabel ammoimage;
	public JButton back_BTN = new JButton("BACK");


	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Options frame = new Options();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Options() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(0, 0, 1000, 735);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		contentPane.setBackground(Color.ORANGE);
		
		JLabel bombname = new JLabel("BOMB");
		bombname.setHorizontalAlignment(SwingConstants.CENTER);
		bombname.setFont(new Font("Forte", Font.BOLD, 18));
		bombname.setBounds(94, 20, 111, 49);
		contentPane.add(bombname);
		
		 bombimage = new JLabel("", fly.getImageIcon("bomb"), JLabel.CENTER);
		bombimage.setBounds(140, 83,22, 22);
		contentPane.add(bombimage);

		
		JLabel giftname = new JLabel("GIFT");
		giftname.setHorizontalAlignment(SwingConstants.CENTER);
		giftname.setFont(new Font("Forte", Font.BOLD, 18));		
		giftname.setBounds(449, 20, 111, 49);
		contentPane.add(giftname);
		
		 giftimage = new JLabel("", fly.getImageIcon("gift"), JLabel.CENTER);
		giftimage.setBounds(491, 83,22, 22);
		contentPane.add(giftimage);
		
		JLabel treename = new JLabel("TREE");
		treename.setHorizontalAlignment(SwingConstants.CENTER);
		treename.setFont(new Font("Forte", Font.BOLD, 18));	
		treename.setBounds(757, 20, 111, 49);
		contentPane.add(treename);
		
		 treeimage = new JLabel("", fly.getImageIcon("tree"), JLabel.CENTER);
		treeimage.setBounds(811, 83,22, 22);
		contentPane.add(treeimage);
		
		JLabel bigbombname = new JLabel("BIGBOMB");
		bigbombname.setHorizontalAlignment(SwingConstants.CENTER);
		bigbombname.setFont(new Font("Forte", Font.BOLD, 18));
		bigbombname.setBounds(94, 223, 111, 49);
		contentPane.add(bigbombname);
		
		 bigBombimage = new JLabel("", fly.getImageIcon("bigBomb"), JLabel.CENTER);
		bigBombimage.setBounds(140, 299,22, 22);
		contentPane.add(bigBombimage);
		
		
		JLabel spacename = new JLabel("SPACE");
		spacename.setHorizontalAlignment(SwingConstants.CENTER);
		spacename.setFont(new Font("Forte", Font.BOLD, 18));
		spacename.setBounds(449, 223, 111, 49);
		contentPane.add(spacename);
		
		 spaceimage = new JLabel("", fly.getImageIcon("space"), JLabel.CENTER);
		spaceimage.setBounds(491, 299,22, 22);
		contentPane.add(spaceimage);
		
		JLabel wallname = new JLabel("WALL");
		wallname.setHorizontalAlignment(SwingConstants.CENTER);
		wallname.setFont(new Font("Forte", Font.BOLD, 18));
		wallname.setBounds(769, 223, 111, 49);
		contentPane.add(wallname);
		
		 wallimage = new JLabel("", fly.getImageIcon("wall"), JLabel.CENTER);
		wallimage.setBounds(811, 299,22, 22);
		contentPane.add(wallimage);
		
		
		
		JLabel ammoname = new JLabel("AMMO");
		ammoname.setHorizontalAlignment(SwingConstants.CENTER);
		ammoname.setFont(new Font("Forte", Font.BOLD, 18));		
		ammoname.setBounds(94, 444, 111, 49);
		contentPane.add(ammoname);
		
		 ammoimage = new JLabel("", fly.getImageIcon("ammo"), JLabel.CENTER);
		ammoimage.setBounds(140, 506,22, 22);
		contentPane.add(ammoimage);
		
		
		JButton giftbtn = new JButton("Replace");
		giftbtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				JFileChooser chooser= new JFileChooser("pictures");
			    chooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
			    chooser.showOpenDialog(getParent());
			    fly.setImageIcon("gift", chooser.getSelectedFile().getAbsolutePath());
			 //  System.out.println(fly.getImageIcon("gift"));
			    contentPane.remove(giftimage);
			    giftimage= new JLabel("", fly.getImageIcon("gift"), JLabel.CENTER);
			    giftimage.setBounds(491, 83,22, 22);
			    contentPane.add(giftimage);
			    contentPane.revalidate();
			    contentPane.repaint();
			}
		});
		giftbtn.setBounds(437, 118, 123, 49);
		contentPane.add(giftbtn);
		
		
		JButton treebtn = new JButton("Replace");
		treebtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				JFileChooser chooser= new JFileChooser("pictures");
			    chooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
			    chooser.showOpenDialog(getParent());
			    fly.setImageIcon("tree", chooser.getSelectedFile().getAbsolutePath());
				 //  System.out.println(fly.getImageIcon("gift"));
				    contentPane.remove(treeimage);
				    treeimage= new JLabel("", fly.getImageIcon("tree"), JLabel.CENTER);
				    treeimage.setBounds(811, 83,22, 22);
				    contentPane.add(treeimage);
				    contentPane.revalidate();
				    contentPane.repaint();			    
			    

			}
		});
		treebtn.setBounds(757, 118, 123, 49);
		contentPane.add(treebtn);

		JButton bombbtn = new JButton("Replace");
		bombbtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				JFileChooser chooser= new JFileChooser("pictures");
			    chooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
			    chooser.showOpenDialog(getParent());
			   // System.out.println(chooser.getSelectedFile());
			    fly.setImageIcon("bomb", chooser.getSelectedFile().getAbsolutePath());
			    contentPane.remove(bombimage);
			    bombimage= new JLabel("", fly.getImageIcon("bomb"), JLabel.CENTER);
			    bombimage.setBounds(140, 83,22, 22);
			    contentPane.add(bombimage);
			    contentPane.revalidate();
			    contentPane.repaint();	
			    

			}
		});

		bombbtn.setBounds(94, 120, 123, 45);
		contentPane.add(bombbtn);
		JButton bigbombbtn = new JButton("Replace");
		bigbombbtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				JFileChooser chooser= new JFileChooser("pictures");
			    chooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
			    chooser.showOpenDialog(getParent());
			   // System.out.println(chooser.getSelectedFile());
			    fly.setImageIcon("bigBomb", chooser.getSelectedFile().getAbsolutePath());
			    contentPane.remove(bigBombimage);
			    bigBombimage= new JLabel("", fly.getImageIcon("bigBomb"), JLabel.CENTER);
			    bigBombimage.setBounds(140, 299,22, 22);
			    contentPane.add(bigBombimage);
			    contentPane.revalidate();
			    contentPane.repaint();	
			    

			    

			}
		});
		bigbombbtn.setBounds(94, 334, 123, 45);
		contentPane.add(bigbombbtn);

		
		
		
		

		JButton spacebtn = new JButton("Replace");
		spacebtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				JFileChooser chooser= new JFileChooser("pictures");
			    chooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
			    chooser.showOpenDialog(getParent());
			   // System.out.println(chooser.getSelectedFile());
			    fly.setImageIcon("space", chooser.getSelectedFile().getAbsolutePath());
			    contentPane.remove(spaceimage);
			    spaceimage= new JLabel("", fly.getImageIcon("space"), JLabel.CENTER);
			    spaceimage.setBounds(491, 299,22, 22);
			    contentPane.add(spaceimage);
			    contentPane.revalidate();
			    contentPane.repaint();	
			    

			}
		});

		spacebtn.setBounds(437, 332, 123, 49);
		contentPane.add(spacebtn);
		

		JButton wallbtn = new JButton("Replace");
		wallbtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				JFileChooser chooser= new JFileChooser("pictures");
			    chooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
			    chooser.showOpenDialog(getParent());
			   // System.out.println(chooser.getSelectedFile());
			    fly.setImageIcon("wall", chooser.getSelectedFile().getAbsolutePath());
			    contentPane.remove(wallimage);
			    wallimage= new JLabel("", fly.getImageIcon("wall"), JLabel.CENTER);
			    wallimage.setBounds(811, 299,22, 22);
			    contentPane.add(wallimage);
			    contentPane.revalidate();
			    contentPane.repaint();	
			    

			}
		});

		wallbtn.setBounds(757, 332, 123, 49);
		contentPane.add(wallbtn);
		
		
		
		
		
		JButton ammobtn = new JButton("Replace");
		ammobtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				JFileChooser chooser= new JFileChooser("pictures");
			    chooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
			    chooser.showOpenDialog(getParent());
			   // System.out.println(chooser.getSelectedFile());
			    fly.setImageIcon("ammo", chooser.getSelectedFile().getAbsolutePath());
			    contentPane.remove(ammoimage);
			    ammoimage= new JLabel("", fly.getImageIcon("ammo"), JLabel.CENTER);
			    ammoimage.setBounds(140, 506,22, 22);
			    contentPane.add(ammoimage);
			    contentPane.revalidate();
			    contentPane.repaint();	
			    

			}
		});

		ammobtn.setBounds(94, 541, 123, 49);
		contentPane.add(ammobtn);
		back_BTN.setIcon(new ImageIcon(Options.class.getResource("/javax/swing/plaf/metal/icons/ocean/close-pressed.gif")));
		
		back_BTN.setBounds(757, 506, 123, 49);
		contentPane.add(back_BTN);
		
		JSeparator separator = new JSeparator();
		separator.setOrientation(SwingConstants.VERTICAL);
		separator.setForeground(Color.BLACK);
		separator.setBackground(Color.BLACK);
		separator.setBounds(321, 13, 5, 600);
		contentPane.add(separator);
		
		JSeparator separator_1 = new JSeparator();
		separator_1.setOrientation(SwingConstants.VERTICAL);
		separator_1.setForeground(Color.BLACK);
		separator_1.setBackground(Color.BLACK);
		separator_1.setBounds(660, 13, 5, 600);
		contentPane.add(separator_1);
		
		
		JSeparator separator_2 = new JSeparator();
		separator_2.setForeground(Color.BLACK);
		separator_2.setBackground(Color.BLACK);
		separator_2.setBounds(12, 200, 958, 12);
		contentPane.add(separator_2);
		
		JSeparator separator_3 = new JSeparator();
		separator_3.setForeground(Color.BLACK);
		separator_3.setBackground(Color.BLACK);
		separator_3.setBounds(12, 400, 958, 12);
		contentPane.add(separator_3);
	}
}

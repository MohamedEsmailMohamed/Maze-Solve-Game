package newVergien;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.KeyListener;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import Objects.Ammo;
import Objects.Interactables;
import PacManGit.GUI;
import PacManGit.GamingEngine;
import PacManGit.ImageFlyWeight;
import mazeGenerate.EasyGame;
import sun.net.www.content.text.plain;

public class Observer2 {
	private GamingEngine subject;
	Gui2 UI = new Gui2();

	private int k = 3, n = 22, imagewidth = 60;
	public boolean flag = true;
	public ImageFlyWeight fly = ImageFlyWeight.getinstance();
	String direction = "Down";
	
	public Observer2(GamingEngine g,KeyListener key) {
		this.subject = g;
		UI.gui.setBounds(0, 0, n* 9, n * 9);
		UI.gui.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		UI.gui.setFocusable(true);
		UI.gui.addKeyListener(key);	
		UI.gui.setVisible(true);
	}	
	public Observer2(GamingEngine g) {
		this.subject = g;
		UI.gui.setBounds(0, 0, 1000, 735);
		UI.gui.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		UI.gui.setFocusable(true);
		/*UI.gui.addKeyListener(key);	*/
		UI.gui.setVisible(true);
	}	
	/*******************Update************************/

	public void update() {
		UI.gui.getContentPane().removeAll();
		updatePlayer();
		updateMaze();
		UI.gui.getContentPane().revalidate();
		UI.gui.getContentPane().repaint();
	}

	
	private void updateMaze() {
		UI.mazePanel.setBounds(0-subject.cameraX, 0-subject.cameraY, n * 10, n * 10);
		UI.mazePanel.setLayout(null);
		UI.mazePanel.removeAll();
		Interactables[][] maze = subject.getArr();
		int j = 0,i = 0;
		for (i = subject.player.getIL()-1; i < 10+subject.player.getIL()-1 && i<31; i++) {
			for (j = subject.player.getJL()-1; j < 10+subject.player.getJL()-1&& j<31; j++) {
				JLabel label = null;
				if (i == 31 - 2 && j == 31 - 2) {
					label = new JLabel("", fly.getImageIcon("door"), JLabel.CENTER);
				} else if (maze[i][j].getClass().getSimpleName().equals("Stones")) {
					label = new JLabel("", fly.getImageIcon("wall"), JLabel.CENTER);
				} else if (maze[i][j].getClass().getSimpleName().equals("FullBomb")) {
					label = new JLabel("", fly.getImageIcon("bigBomb"), JLabel.CENTER);

				} else if (maze[i][j].getClass().getSimpleName().equals("HalfBomb")) {
					label = new JLabel("", fly.getImageIcon("bomb"), JLabel.CENTER);

				} else if (maze[i][j].getClass().getSimpleName().equals("Tree")) {
					label = new JLabel("", fly.getImageIcon("tree"), JLabel.CENTER);
				} else if (maze[i][j].getClass().getSimpleName().equals("AmmoGift")
						|| maze[i][j].getClass().getSimpleName().equals("Health")
						|| maze[i][j].getClass().getSimpleName().equals("Spirit")) {
					label = new JLabel("", fly.getImageIcon("gift"), JLabel.CENTER);
				} else {
					label = new JLabel("", fly.getImageIcon("space"), JLabel.CENTER);
				}
				label.setBounds(j* n-subject.player.getindexX()+n,i * n -subject.player.getindexY()+n, n, n);
				UI.mazePanel.add(label);
			}
		}
		UI.mazePanel.revalidate();
		UI.mazePanel.repaint();		
		UI.gui.getContentPane().add(UI.mazePanel);		
	}
	private void updatePlayer() {		
		
		UI.player = new JPanel(new BorderLayout());
		UI.player.setLayout(null);
		UI.player.setBounds(0, 0, 682, 735);
		UI.player.setBackground(new Color(0, 0, 0, 0));
		if (subject.player.getOldDirection().equals("Stop")) {
			if (!subject.player.getRotationDir().equals("Stop")){
				direction = subject.player.getRotationDir();				
			}
			k--;
		} else{
			direction = subject.player.getOldDirection();
		}		
		UI.pl = fly.getImageIconList(direction);
		JLabel pacman = new JLabel("", UI.pl.get(k % 16), JLabel.CENTER);
		pacman.setBackground(new Color(0, 0, 0, 0));
		pacman.setBounds(22, 22,20,20);
		k++;
		UI.player.add(pacman);
		UI.gui.getContentPane().add(UI.player);
	}
	
	/*public static void main(String[] args) {
		GamingEngine x = new GamingEngine();
		x.map.getMazeGenerator().setLevel(new EasyGame());
		Observer2 o = new Observer2(x);
		o.update();		
	}*/

	
}

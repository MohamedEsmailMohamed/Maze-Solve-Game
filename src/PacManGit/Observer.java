package PacManGit;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import Objects.Ammo;
import Objects.Interactables;


public class Observer {

	public GUI gui= new GUI();
	private GamingEngine gameEngine;
	private int k = 0, n = 22, imagewidth = 60;
	public boolean flag = true;
	public ImageFlyWeight fly = ImageFlyWeight.getinstance();
	public Observer(GamingEngine x) {
		gameEngine = x;
		gui.setBounds(0, 0, 1000, 735);
		gui.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		gui.setFocusable(true);
		// gui.addKeyListener(Controller.keyLi);
		gui.contentPane.setBorder(new EmptyBorder(0, 0, 0, 0));
		gui.contentPane.setLayout(null);
		gui.setContentPane(gui.contentPane);
	}
	/*******************Update************************/

	public void update() {
		/*
		 * if(k==16) showredPanel(); else if(flag) hideredPanel();
		 */
		if (flag) {
			hideredPanel();
			updateArray(gameEngine.updatedI,gameEngine.updatedJ);
			updatePlayer();
			updateAmmos();
			updateBoard();
			gui.contentPane.revalidate();
			gui.contentPane.repaint();
		}
	}

	private void updateBoard() {
		// update score
		gui.scorePanel.removeAll();
		//add back button
		gui.scorePanel.add(gui.home);
		
		gui.highscore = new JLabel("HighScore: " + gameEngine.getHighScore());
		gui.highscore.setLocation(35, 10);
		gui.highscore.setSize(200, 32);		
		gui.highscore.setFont(new Font("Comic Sans MS", Font.BOLD, 25));
		gui.scorePanel.add(gui.highscore);

		// update lives
		gui.lives.clear();
		int hearts = gameEngine.player.getSpirit() / 2;
		for (int i = 0; i < hearts; i++) {
			gui.lives.add(new JLabel("", fly.getImageIcon("fullHeart"), JLabel.CENTER));
			gui.lives.get(i).setBounds(10 + imagewidth * i, 90, imagewidth, imagewidth);
			gui.scorePanel.add(gui.lives.get(i));
		}
		if (gameEngine.player.getSpirit() % 2 != 0 && hearts >=0) {
			gui.lives.add(new JLabel("", fly.getImageIcon("halfHeart"), JLabel.CENTER));
			gui.lives.get(gui.lives.size() - 1).setBounds(10 + imagewidth * (gui.lives.size() - 1), 90, imagewidth, imagewidth);
			gui.scorePanel.add(gui.lives.get(gui.lives.size() - 1));
		}

		// update ammos
		gui.ammos.clear();
		int ammoN = gameEngine.player.getAmmo();
		for (int i = 0; i < ammoN; i++) {
			gui.ammos.add(new JLabel("", fly.getImageIcon("bullet"), JLabel.CENTER));
			gui.ammos.get(i).setBounds(10 + 40 * i, 160, 40, imagewidth);
			gui.scorePanel.add(gui.ammos.get(i));
		}
		
		// update time
		gui.time = new JLabel("time: " +gameEngine.getMin() + " : " +gameEngine.getSec());
		gui.time.setLocation(35, 50);
		gui.time.setSize(180, 25);
		gui.time.setFont(new Font("Comic Sans MS", Font.BOLD, 25));
		gui.scorePanel.add(gui.time);
		
		gui.scorePanel.revalidate();
		gui.scorePanel.repaint();
	}

	public void showredPanel() {
		//startbBombSound();
		gui.redPanel.setVisible(true);
	}
	/*******************sounds************************/
	public void bombSound()
	{
		gui.clip= fly.getclip("bomb");
		gui.clip.setMicrosecondPosition(0);
		gui.clip.start();
	}
	public void shotSound()
	{
		gui.clip= fly.getclip("shot");
		gui.clip.setMicrosecondPosition(0);
		gui.clip.start();	
	}
	public void giftSound()
	{
		gui.clip= fly.getclip("gift");
		gui.clip.setMicrosecondPosition(0);
		gui.clip.start();	
	}
	public void winSound()
	{
		gui.clip= fly.getclip("win");
		gui.clip.setMicrosecondPosition(0);
		gui.clip.start();
	}
	public void loseSound()
	{
		gui.clip= fly.getclip("lose");
		gui.clip.setMicrosecondPosition(0);
		gui.clip.start();
	}
	
	/*******************sounds end************************/

	public void hideredPanel() {
		gui.redPanel.setVisible(false);
	}

	private void updatePlayer() {
		String direction = null;
		if (gameEngine.player.getOldDirection().equals("Stop")) {
			if (gameEngine.player.getRotationDir().equals("Stop"))
				return;
			direction = gameEngine.player.getRotationDir();
			k--;
		} else
			direction = gameEngine.player.getOldDirection();
		gui.pl = fly.getImageIconList(direction);
		gui.playerPanel.remove(gui.player);
		gui.player.setLocation(gameEngine.player.getindexX(), gameEngine.player.getindexY());
		JLabel pacman = new JLabel("", gui.pl.get(k % 16), JLabel.CENTER);
		pacman.setBackground(new Color(0, 0, 0, 0));
		k++;
		gui.player.removeAll();
		gui.player.add(pacman);
		gui.playerPanel.add(gui.player);
		gui.playerPanel.revalidate();
		gui.playerPanel.repaint();
	}

	private void updateAmmos() {
		gui.bulletPanel.removeAll();
		gui.bullets.clear();
		int i = 0;
		while (i < gameEngine.CellIterator.size()) {
			gui.bullets.add(new JLabel("", fly.getImageIcon("ammo"), JLabel.CENTER));
			Ammo amos = (Ammo) gameEngine.CellIterator.get(i);
			gui.bullets.get(i).setBounds(amos.getindexX(), amos.getindexY(), 20, 20);
			gui.bulletPanel.add(gui.bullets.get(i));
			i++;
		}
		gui.bulletPanel.revalidate();
		gui.bulletPanel.repaint();
	}

	public void draw() {
		drawWinPanel();
		drawRedPanel();
		drawPlayer();
		drawBullet();
		drawMaze();
		drawBoard();

		gui.setVisible(true);
	}

	private void drawWinPanel() {
		gui.win.setBackground(new Color(0, 255, 0));
		gui.win.setBounds(0, 0, 1000, 735);
		gui.win.setLayout(null);
		gui.home.setIcon(new ImageIcon("pictures\\Btns\\NewGame1.png"));
		gui.home.setPressedIcon(new ImageIcon("pictures\\Btns\\newGameHove.png"));
		gui.home.setOpaque(false);
		gui.home.setFont(new Font("Arial Black", Font.BOLD, 14));
		gui.home.setContentAreaFilled(false);
		gui.home.setBorderPainted(false);
		gui.home.setBackground(new Color(0, 0, 0, 0));
		gui.home.setSize(200,200);
		gui.score.setFont(new Font("Arial Black", Font.BOLD, 38));
		gui.score.setBounds(572, 299, 263, 38);
		gui.score.setText(gameEngine.player.getScore()+"");
		gui.backGround.setBounds(0, 0, 1000, 735);
		gui.win.add(gui.score);
		gui.win.add(gui.backGround);
		gui.win.setVisible(false);
		gui.contentPane.add(gui.win);
	}
	private void drawBoard() {
		// draw score
		gui.scorePanel.setBounds(682, 0, 382, 735);
		gui.scorePanel.setLayout(null);
		gui.home.setLocation(20,459);
		gui.scorePanel.add(gui.home);
		//System.out.println(home.getParent());
		gui.highscore = new JLabel("High Score:"+ gameEngine.getHighScore());
		gui.time = new JLabel("time  :00:00");
		gui.highscore.setLocation(35, 10);
		gui.highscore.setSize(200, 25);
		gui.highscore.setFont(new Font("Comic Sans MS", Font.BOLD, 25));
		gui.scorePanel.add(gui.highscore);

		// draw lives
		for (int i = 0; i < 4; i++) {
			gui.lives.add(new JLabel("", fly.getImageIcon("fullHeart"), JLabel.CENTER));
			gui.lives.get(i).setBounds(10 + imagewidth * i, 90, imagewidth, imagewidth);
			gui.scorePanel.add(gui.lives.get(i));
		}
		// draw time
		gui.time.setLocation(35, 50);
		gui.time.setSize(180, 25);
		gui.time.setFont(new Font("Comic Sans MS", Font.BOLD, 25));
		gui.scorePanel.add(gui.time);

		// draw bullets
		for (int i = 0; i < 6; i++) {
			gui.ammos.add(new JLabel("", fly.getImageIcon("bullet"), JLabel.CENTER));
			gui.ammos.get(i).setBounds(10 + 40 * i, 160, 40, imagewidth);
			gui.scorePanel.add(gui.ammos.get(i));
		}
		gui.contentPane.add(gui.scorePanel);
	}


	public void win() {
		winSound();
		gui.scorePanel.remove(gui.home);
		gui.scorePanel.remove(gui.score);
		gui.scorePanel.remove(gui.highscore);
		gui.home.setLocation(410,459);
		gui.score.setFont(new Font("Arial Black", Font.BOLD, 38));
		gui.score.setBounds(572, 299, 263, 38);
		gui.score.setText(gameEngine.player.getScore()+" ");
		gui.highscore.setFont(new Font("Arial Black", Font.BOLD, 38));
		gui.highscore.setBounds(572, 330, 500, 100);
		gui.highscore.setText(gameEngine.getHighScore()+"");
		gui.win.add(gui.highscore,0);
		gui.win.add(gui.score,0);
		gui.win.add(gui.home, 0);
		
		gui.win.setVisible(true);
		gui.win.repaint();
		gui.player.setVisible(false);
		gui.contentPane.repaint();
		flag = false;
	}

	public void lose() {
		loseSound();
		gui.redPanel.setBackground(new Color(255, 26, 26, 120));
		gui.gameOver = new JLabel("", new ImageIcon("pictures\\gameover.png"), JLabel.CENTER);
		gui.gameOver.setLocation(40, 90);
		gui.gameOver.setSize(600, 300);
		gui.redPanel.add(gui.gameOver);
		gui.score = new JLabel("Score: " + gameEngine.player.getScore());
		gui.score.setLocation(0, 300);
		gui.score.setSize(682, 70);
		gui.score.setHorizontalAlignment(SwingConstants.CENTER);
		gui.score.setForeground(new Color(77, 0, 0));
		gui.score.setFont(new Font("Comic Sans MS", Font.BOLD, 50));
		gui.redPanel.add(gui.score);
		gui.redPanel.repaint();
		showredPanel();
		flag = false;
	}

	private void drawRedPanel() {
	
		gui.redPanel.setBounds(0, 0, 682, 735);
		gui.redPanel.setLayout(null);
		gui.redPanel.setBackground(new Color(255, 26, 26, 120));
		gui.redPanel.setVisible(false);
		gui.contentPane.add(gui.redPanel);
	}

	private void drawBullet() {
		gui.bulletPanel.setBounds(0, 0, 1000, 1000);
		gui.bulletPanel.setLayout(null);
		gui.bulletPanel.add(gui.bullet);
		gui.bulletPanel.setBackground(new Color(0, 0, 0, 0));
		gui.contentPane.add(gui.bulletPanel);
	}

	
	private void drawMaze() {
		gui.mazePanel.setBounds(0, 0, 682, 735);
		Interactables[][] maze = gameEngine.map.getCharMaze();
		gui.mazepics = new JPanel[maze.length][maze[0].length];
		for (int i = 0; i < maze.length; i++) {
			for (int j = 0; j < maze[i].length; j++) {
				JLabel label = null;
				if (i == maze.length - 2 && j == maze[i].length - 2) {
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
				gui.mazepics[i][j] = new JPanel(new BorderLayout());
				gui.mazepics[i][j].add(label, BorderLayout.CENTER);
				gui.mazepics[i][j].setBounds(n * j + 1, n * i + 1, n, n);
				gui.mazePanel.add(gui.mazepics[i][j]);
			}
		}
		gui.contentPane.add(gui.mazePanel);
		gui.mazePanel.setLayout(null);
	}

	private void drawPlayer() {
		gui.pl = fly.getImageIconList("Down");
		JLabel pacman = new JLabel("", gui.pl.get(k), JLabel.CENTER);
		k++;
		gui.player = new JPanel(new BorderLayout());
		gui.player.setBounds(n, n, n, n);
		pacman.setBackground(new Color(0, 0, 0, 0));
		gui.player.add(pacman);
		gui.player.setBackground(new Color(0, 0, 0, 0));
		gui.playerPanel.setBounds(0, 0, 1000, 1000);
		gui.playerPanel.setLayout(null);
		gui.playerPanel.add(gui.player);
		gui.playerPanel.setBackground(new Color(0, 0, 0, 0));
		gui.contentPane.add(gui.playerPanel);

	}

	private void updateArray(int i, int j) {
		gui.mazepics[i][j].removeAll();
		JLabel label = new JLabel("", fly.getImageIcon("space"), JLabel.CENTER);
		gui.mazepics[i][j].revalidate();
		gui.mazepics[i][j].add(label);
		gui.mazepics[i][j].repaint();
	}

	

	
}

package PacManGit;



import java.util.ArrayList;

import javax.sound.sampled.Clip;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;


@SuppressWarnings("serial")
public class GUI extends JFrame {
//	public JFrame gui = new JFrame();
	public JPanel player = new JPanel();
	public JPanel bullet = new JPanel();
	public JPanel redPanel = new JPanel();
	public JPanel win = new JPanel();
	public JPanel contentPane = new JPanel();
	public JButton home = new JButton("");
	public JLabel backGround = new JLabel(new ImageIcon("pictures\\victoryBackground.jpg"));
	public JLabel gameOver= new JLabel();
	public Clip clip;
	public ArrayList<ImageIcon> pl = new ArrayList<>();
	public JPanel playerPanel = new JPanel();
	public JPanel bulletPanel = new JPanel();
	public JPanel mazePanel = new JPanel();
	public JPanel scorePanel = new JPanel();
	public JPanel[][] mazepics = null;
	// private boolean flag = false;
	public JLabel time = new JLabel();
	public JLabel score = new JLabel();
	public JLabel highscore = new JLabel();

	public ArrayList<JLabel> lives = new ArrayList<>();

	public ArrayList<JLabel> ammos = new ArrayList<>();
	public ArrayList<JLabel> bullets = new ArrayList<>();

}
package PacManGit;

import java.awt.Image;
import java.io.File;
import java.util.ArrayList;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.ImageIcon;

public class ImageFlyWeight {
	private ImageIcon bomb;
	private ImageIcon bigBomb;
	private ImageIcon tree;
	private ImageIcon space;
	private ImageIcon wall;
	private ImageIcon gift;
	private ImageIcon fullHeart;
	private ImageIcon halfHeart;
	private ImageIcon ammo;
	private ImageIcon door;
	private ImageIcon bullet;
	private AudioInputStream tada;
	private Clip win;
	private Clip lose; 
	private Clip boomb;
	private Clip giftSound;
	private Clip shot;



	private static ImageFlyWeight obj = null;
	ArrayList<ImageIcon> playerleft;
	ArrayList<ImageIcon> playerright;
	ArrayList<ImageIcon> playerup;
	ArrayList<ImageIcon> playerdown;

	public static ImageFlyWeight getinstance() {
		if (obj == null) {
			obj = new ImageFlyWeight();
		}
		return obj;
	}

	private ImageFlyWeight() {
		wall = new ImageIcon("pictures\\wall.png");
		bomb = new ImageIcon("pictures\\bomb.png");
		bigBomb = new ImageIcon("pictures\\bigbomb.png");
		tree = new ImageIcon("pictures\\tree.png");
		space = new ImageIcon("pictures\\space.png");
		gift = new ImageIcon("pictures\\gift1.png");
		fullHeart = new ImageIcon("pictures\\FullHeart.png");
		halfHeart = new ImageIcon("pictures\\HalfHeart.png");
		ammo = new ImageIcon("pictures\\FireBall.png");
		door = new ImageIcon("pictures\\door.png");
		bullet = new ImageIcon("pictures\\bullet.png");
		playerdown = new ArrayList<>();
		playerleft = new ArrayList<>();
		playerright = new ArrayList<>();
		playerup = new ArrayList<>();
		try {
			tada = AudioSystem.getAudioInputStream(new File("Resources\\tada.wav").getAbsoluteFile());
			win = AudioSystem.getClip();
			win.open(tada);
			tada= AudioSystem.getAudioInputStream(new File("Resources\\sad.wav").getAbsoluteFile());
			lose = AudioSystem.getClip();
			lose.open(tada);
			tada = AudioSystem.getAudioInputStream(new File("Resources\\bomb.wav").getAbsoluteFile());
			boomb = AudioSystem.getClip();
			boomb.open(tada);
			tada = AudioSystem.getAudioInputStream(new File("Resources\\gift.wav").getAbsoluteFile());
			giftSound = AudioSystem.getClip();
			giftSound.open(tada);
			tada = AudioSystem.getAudioInputStream(new File("Resources\\shot.wav").getAbsoluteFile());
			shot = AudioSystem.getClip();
			shot.open(tada);
			
		} catch (Exception e) {
		}
		// System.out.println((System.getProperty("user.dir"))+"\\Resources\\left\\player2"
		// + 0 + ".png");
		for (int i = 0; i < 16; i++) {
			playerleft.add(new ImageIcon("Resources\\left\\player2" + i + ".png"));
		}
		for (int i = 0; i < 16; i++) {
			playerright.add(new ImageIcon("Resources\\right\\player1" + i + ".png"));
		}
		for (int i = 0; i < 16; i++) {
			playerup.add(new ImageIcon("Resources\\up\\player0" + i + ".png"));
		}
		for (int i = 0; i < 16; i++) {
			playerdown.add(new ImageIcon("Resources\\down\\player3" + i + ".png"));
		}
	}

	public ArrayList<ImageIcon> getImageIconList(String direction) {
		switch (direction) {
		case "Left":
			return playerleft;
		case "Right":
			return playerright;
		case "Up":
			return playerup;
		case "Down":
			return playerdown;
		default:
			return null;

		}
	}
public Clip getclip(String name)
{
	switch (name) {
	case "win":
		return win;
	case "lose":
		return lose;
	case "bomb":
		return boomb;
	case "shot":
		return shot;
	case "gift":
		return giftSound;
	default:
		return null;
	}
}
	public ImageIcon getImageIcon(String type) {
		switch (type) {
		case "bomb":
			return bomb;
		case "tree":
			return tree;
		case "wall":
			return wall;
		case "bigBomb":
			return bigBomb;
		case "space":
			return space;
		case "gift":
			return gift;
		case "fullHeart":
			return fullHeart;
		case "halfHeart":
			return halfHeart;
		case "ammo":
			return ammo;
		case "door":
			return door;
		case "bullet":
			return bullet;
		default:
			return null;
		}
	}

	public void setImageIcon(String type, String path) {
		ImageIcon image = new ImageIcon(path);
		Image t = image.getImage();
		t = t.getScaledInstance(22, 22, Image.SCALE_DEFAULT);
		switch (type) {
		case "bomb":
			bomb = new ImageIcon(t);
			break;
		case "tree":
			tree = new ImageIcon(t);
			break;
		case "wall":
			wall = new ImageIcon(t);
			break;
		case "bigBomb":
			bigBomb = new ImageIcon(t);
			break;
		case "space":
			space = new ImageIcon(t);
			break;
		case "gift":
			gift = new ImageIcon(t);
			break;
		case "fullHeart":
			fullHeart = new ImageIcon(t);
			break;
		case "halfHeart":
			halfHeart = new ImageIcon(t);
			break;
		case "ammo":
			ammo = new ImageIcon(t);
			break;
		case "door":
			door = new ImageIcon(t);
			break;
		case "bullet":
			bullet = new ImageIcon(t);
			break;
		default:
			;
		}
	}
}

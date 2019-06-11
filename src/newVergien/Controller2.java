package newVergien;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.List;

import javax.swing.SwingWorker;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

import PacManGit.GamingEngine;
import PacManGit.MainMenu;
import PacManGit.Options;
import mazeGenerate.EasyGame;
import mazeGenerate.HardGame;
import mazeGenerate.NormalGame;

public class Controller2 {
	private GamingEngine gamingEngine;
	private Thread playerTherad;
	private Observer2 obs ;
	public MainMenu menu;
	private Options option;
	private boolean endGame;
	private SwingWorker<Boolean, Integer> worker;
	private int levelSelect = 1;
	public final static Logger logger = Logger.getLogger(Controller2.class);
	
	
	public Controller2() {
		logger.info("Game is started");
		gamingEngine = new GamingEngine();
		playerTherad = new Thread(new movePlayer());
		menu = new MainMenu();
		option = new Options();
		endGame = false;
		worker = new  myWorker();
		obs = new Observer2(gamingEngine,keyLi);
		gamingEngine.setObserver(obs);
		initializeMenuButtons();
	//	initializeBackObserverButton();
	}
	public KeyListener keyLi = new KeyListener() {

		@Override
		public void keyTyped(KeyEvent e) {
			// TODO Auto-generated method stub

		}

		@Override
		public void keyReleased(KeyEvent e) {
			// TODO Auto-generated method stub

		}

		@Override
		public void keyPressed(KeyEvent e) {
			int keyCode = e.getKeyCode();
			String temp = KeyEvent.getKeyText(keyCode);
			if (temp.equals("Up") || temp.equals("Down") || temp.equals("Right") || temp.equals("Left")) {
				gamingEngine.setPlayerNewDirection(temp);
			}else if (temp.equals("Space")) {
				int index = gamingEngine.createAmmo();
				if(index >= 0){
					logger.info("Player is shoting Ammo");
					moveShot ammoThread = new moveShot();
					ammoThread.ammoIndex = index;
					Thread thread = new Thread(ammoThread);
					thread.start();
				}			
			}else if(temp.equals("W") || temp.equals("D") || temp.equals("S") || temp.equals("A")){
				gamingEngine.setPlayerRoutationDirection(temp);
			}
		}
	};

	private class movePlayer implements Runnable {
		public void run() {
			try {
				for (;!endGame;) {
					Thread.sleep(10);
					gamingEngine.movePlayer();
					boolean win =gamingEngine.isPlayerWin();
					if(win){
						logger.info("Player wins");
						int MyScore = (int) (((gamingEngine.timeMilile -(System.currentTimeMillis()- gamingEngine.start)))/1000);
						int Ammosc = 1;
						if (gamingEngine.player.getAmmo()==6){
							Ammosc = 1;
						}else{
							Ammosc = 6-(gamingEngine.player.getAmmo()); 
						}
						int FinalScore = (MyScore*levelSelect*(gamingEngine.player.getSpirit()/2))/(Ammosc);
						gamingEngine.player.setScore(FinalScore);
						gamingEngine.setHighScore();
						//obs.win();
					}
					boolean lose = gamingEngine.isPlayerLose();
					if(lose){
						logger.info("Player is loser xD");
						//obs.lose();
					}
					if(!endGame){
						endGame = win || lose;
					}
				}
			} catch (InterruptedException e) {
				logger.warn("Interrupting threads Exception");
			}
		}

	}
	
	
	private class moveShot implements Runnable {
		public int ammoIndex;
		public void run() {
			try {
				boolean stop = false;
				//obs.shotSound();
				for (; !stop && !endGame;) {
					Thread.sleep(5);
					try{
						gamingEngine.moveShot(ammoIndex);
					}catch(NullPointerException e){
						stop = true;
						logger.warn("Null Pointer Exception - No more Ammo");
					}
				}				
			} catch (InterruptedException e) {
				logger.warn("Interrupting threads Exception");
			}
		}
	}
	
	public class myWorker extends SwingWorker<Boolean, Integer>{
		@Override
		   protected Boolean doInBackground() throws Exception {
		    // Simulate doing something useful.
		    for (;!endGame;) {
		     Thread.sleep(5);
		     int i = 0;
		     publish(i);
		    }
			return endGame;			
		}
		   @Override
		   protected void process(List<Integer> chunks) {
			  if(!endGame)
			   gamingEngine.notfy();
		   }
	};

	
	private  void initializeMenuButtons() {
		menu.easyBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				logger.info("Played selected Easy Game");
				levelSelect=1;
				gamingEngine.map.getMazeGenerator().setLevel(new EasyGame());
				//menu.frmMainmenu.dispose();
				menu.frmMainmenu.setVisible(false);
				initializeGame();
			}
		});
		
		menu.normalBtn.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				logger.info("Played selected Normal Game");
				levelSelect=2;
				gamingEngine.map.getMazeGenerator().setLevel(new NormalGame());
				//menu.frmMainmenu.dispose();
				menu.frmMainmenu.setVisible(false);
				initializeGame();
			}
		});
		menu.Hard.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				logger.info("Played selected Hard Game");
				levelSelect=3;
				gamingEngine.map.getMazeGenerator().setLevel(new HardGame());
				//menu.frmMainmenu.dispose();
				menu.frmMainmenu.setVisible(false);
				initializeGame();
			}
		});
		menu.Option.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				option.setVisible(true);
			}
		});
		
		option.back_BTN.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				option.setVisible(false);
			}
		});
		
	}
	/*private void initializeBackObserverButton() {
		obs.gui.home.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				obs.UI.gui.dispose();
				endGame = true;
				resetVariables();
				menu.frmMainmenu.setVisible(true);				
			}
		});
	}*/
	private void initializeGame() {
		endGame = false;
		gamingEngine.map.getCharMaze();	
		//obs.gui.addKeyListener(keyLi);
		//gamingEngine.drawMaze();
		gamingEngine.notfy();
		gamingEngine.start = System.currentTimeMillis();
		playerTherad.start();		
		worker.execute();		
	}
	private void resetVariables(){		
		gamingEngine = new GamingEngine();
		playerTherad = new Thread(new movePlayer());
		worker = new  myWorker();
		obs = new Observer2(gamingEngine,keyLi);
		gamingEngine.setObserver(obs);
		//initializeBackObserverButton();
	}


	public static void main(String args[]) throws InterruptedException {
		String log4jConfPath = "log4j.properties";
		PropertyConfigurator.configure(log4jConfPath);
		Controller2 control = new Controller2();
		control.menu.frmMainmenu.setVisible(true);
	}

}

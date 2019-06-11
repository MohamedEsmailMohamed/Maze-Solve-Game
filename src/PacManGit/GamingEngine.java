package PacManGit;


import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.PrintWriter;
import java.util.LinkedList;

import org.apache.log4j.Logger;

import Objects.Ammo;

import Objects.Interactables;
import Objects.InteractablesFactory;
import Objects.Player;
import newVergien.Observer2;


public class GamingEngine {
	
	public Player player = new Player();
	public FlyWeight map = new FlyWeight();
	public LinkedList<Ammo> CellIterator = new LinkedList<>();
	private AmmoPool ammoPool = new AmmoPool();
	public Observer observer = new Observer(this);
	public Observer2 observer2;
	private InteractablesFactory actFactory = new InteractablesFactory();
	
	//initialize   with the start point of the player
	public int updatedI = 1;
	public int updatedJ = 1;
	public final static Logger logger = Logger.getLogger(GamingEngine.class);
	
	//time
	public long timeMilile = 120000;
	public long start = System.currentTimeMillis();
	private int HighScore = 0;
	
	public int cameraX = 0;
	public int cameraY = 0;
	
	public GamingEngine() {
		setHighScore();
	}
	public int createAmmo() {
		int ammosN = player.getAmmo();
		if ( ammosN > 0){
			logger.info("Ammo is created");
			Ammo ammo =  ammoPool.create();
			int index = ammo.getOrderIndex();
			CellIterator.add(ammo);		
			player.setAmmo(--ammosN);
			ammo.setindexX(player.getindexX());
			ammo.setindexY(player.getindexY());
			if(!player.getOldDirection().equals("Stop")){
				ammo.setOldDirection(player.getOldDirection());
			}else{
				ammo.setOldDirection(player.getRotationDir());
			}			
			return index;
		}
		return -1;
	}
	private boolean isPlayerStoped(String dir){
		boolean B1 = false;
		boolean B2 = false;
		int ID = player.getIR();
		int JR = player.getJD();
		int IL = player.getIL();
		int JL = player.getJL();
		int ISmall = player.getISmall();
		int JSmall = player.getJSmall();
		
		switch(dir){
		case "Up":
			if (ISmall == 0) {
				B2 = isThereBlock(IL - 1, JR);
				B1 = isThereBlock(IL - 1, JL);
			}
			break;
		case "Down":
			if (ISmall == 0) {
				B2 = isThereBlock(IL + 1, JR);
				B1 = isThereBlock(IL + 1, JL);
			}
			break;
		case "Right":
			if (JSmall == 0) {
				B2 = isThereBlock(IL, JL + 1);
				B1 = isThereBlock(ID, JL + 1);
			}
			break;
		case "Left":
			if (JSmall == 0) {
				B2 = isThereBlock(IL, JL - 1);
				B1 = isThereBlock(ID, JL - 1);
			}
			break;
		}	
		return B1 || B2;
	}
	public boolean movePlayer(){
		String oldDir = player.getOldDirection();
		String newDir = player.getNewDirection();
		if (newDir.equals("Stop")) {
			if (isPlayerStoped(oldDir)) {				
				player.setRotationDir(oldDir);
				oldDir = "Stop";
				player.setOldDirection("Stop");
			}
		} else {
			if (!(isPlayerStoped(newDir))) {
				oldDir = newDir;
				newDir = "Stop";
				player.setOldDirection(oldDir);
				player.setNewDirection(newDir);
			} else {
				if (isPlayerStoped(oldDir)) {					
					player.setRotationDir(oldDir);
					oldDir = "Stop";
					player.setOldDirection("Stop");
					player.setNewDirection("Stop");
				}
				if(oldDir.equals("Stop")){
					player.setNewDirection("Stop");
				}
			}
		}
		int positionY = player.getindexY();
		int positionX = player.getindexX();
		switch (oldDir) {
		case "Up":
			positionY -= 1;
			cameraY -= 1;
			break;
		case "Down":
			positionY += 1;
			cameraY += 1;
			break;
		case "Right":
			positionX += 1;
			cameraX += 1;
			break;
		case "Left":
			positionX -= 1;
			cameraX -= 1;
			break;
		}
		player.setindexX(positionX);
		player.setindexY(positionY);
		
		//i check only upper left square
		actWithComponent (player.getIL(),player.getJL());	
		if(player.getIR() != player.getIL()){
			actWithComponent (player.getIR(),player.getJL());
		}
		if(player.getJD() != player.getJL()){
			actWithComponent (player.getIL(),player.getJD());
		}
		if(player.getIR() != player.getIL()&& player.getJD() != player.getJL()){
			actWithComponent (player.getIR(),player.getJD());
		}
		
		//check time
		isTimeEnd();
		return true;
	}
	
	
	private void isTimeEnd() {
		if((timeMilile -(System.currentTimeMillis()- start)) < 0){
			int sp = player.getSpirit();
			logger.info("Player is dead");
			if(sp%2 == 0){
				player.setSpirit(sp-2);
			}else{
				player.setSpirit(sp-1);
			}
			timeMilile = 120000;
			start = System.currentTimeMillis();
			player.resetPosition();			
			observer.showredPanel();
			observer.loseSound();
		}		
	}
	public Ammo moveShot(int ammoIndex){		
		Ammo myAmmo = getAmmo(ammoIndex);
		String Dir = myAmmo.getOldDirection();
		int ID = myAmmo.getIR();
		int JR = myAmmo.getJD();
		int IL = myAmmo.getIL();
		int JL = myAmmo.getJL();
		boolean B1 = false;
		boolean B2 = false;
		int positionY = myAmmo.getindexY();
		int positionX = myAmmo.getindexX();
		
		switch (Dir) {
		case "Up":
			B1 = AmmoisDestroyed(IL, JR);
			B2 = AmmoisDestroyed(IL, JL);
			positionY -= 1;
			break;
		case "Down":
			B1 = AmmoisDestroyed(IL, JR);
			B2 = AmmoisDestroyed(IL, JL);
			positionY += 1;
			break;
		case "Right":
			B1 = AmmoisDestroyed(IL, JL);
			B2 = AmmoisDestroyed(ID, JL);
			positionX += 1;
			break;
		case "Left":
			B1 = AmmoisDestroyed(IL, JL);
			B2 = AmmoisDestroyed(ID, JL);
			positionX -= 1;
			break;
		}	
		myAmmo.setindexX(positionX);
		myAmmo.setindexY(positionY);
		if(B1 || B2){
			//stop = true;
			myAmmo.setExistance(false);	
			CellIterator.remove(myAmmo);
			myAmmo.setOldDirection("Stop");
			ammoPool.expire(myAmmo);
		}		
		return myAmmo;		
	}

	public Ammo getAmmo(int ammoIndex) {
		// TODO Auto-generated method stub
		int i = 0;
		while(i <CellIterator.size()){
			 Ammo x = CellIterator.get(i);
			if(x.getOrderIndex()==ammoIndex){
				return x;
			}
			i++;
		}
		return null;
	}
	
	public void actWithComponent (int i , int j){
		Interactables[][] x =map.getCharMaze();
		String c = x[i][j].getClass().getSimpleName().toString();
		
		if(c.equals("HalfBomb")){
			int sp = player.getSpirit();
			logger.info("Health is cut off by half");
			if(sp%2 != 0){
				player.resetPosition();
				timeMilile = 120000;
				start = System.currentTimeMillis();
			}
			player.setSpirit(sp -1);
			x[i][j] = actFactory.getinstance("PathFree");
			updatedI = i;
			updatedJ = j;
			observer.showredPanel();
			observer.bombSound();
		}else if (c.equals("FullBomb")){
			int sp = player.getSpirit();
			logger.info("Player is dead");
			if(sp%2 == 0){
				player.setSpirit(sp-2);
			}else{
				player.setSpirit(sp-1);
			}
			timeMilile = 120000;
			start = System.currentTimeMillis();
			player.resetPosition();
			x[i][j] = actFactory.getinstance("PathFree");
			updatedI = i;
			updatedJ = j;
			observer.showredPanel();
			observer.bombSound();
			//map.resetMaze();
			//observer.draw();
		}else if (c.equals("Health")){
			player.setSpirit(player.getSpirit()+1);
			logger.info("Player gets Half Spirit Gift");
			x[i][j] = actFactory.getinstance("PathFree");
			updatedI = i;
			updatedJ = j;
			observer.giftSound();
		}else if (c.equals("Spirit")){
			logger.info("Player gets Spirit Gift");
			player.setSpirit(player.getSpirit()+1);
			x[i][j] = actFactory.getinstance("PathFree");
			updatedI = i;
			updatedJ = j;
			observer.giftSound();
		}else if (c.equals("AmmoGift")){
			logger.info("Player gets Ammo Gift");
			player.setAmmo(player.getAmmo()+1);
			x[i][j] = actFactory.getinstance("PathFree");
			updatedI = i;
			updatedJ = j;
			observer.giftSound();
		}else {
			
		}
		
	}
	public boolean isThereBlock(int iR, int jL) {
		// TODO Auto-generated method stub
		Interactables[][] x =map.getCharMaze();
		String c = x[iR][jL].getClass().getSimpleName().toString();
		if(c.equals("Stones")||c.equals("Tree")){
			logger.info("There is Block");
			return true;
		}
		return false;
	}
	
	public boolean AmmoisDestroyed(int x, int y) {
		Interactables[][] xd = map.getCharMaze();
		String temp = xd[x][y].getClass().getSimpleName().toString();
		if(temp.equals("Spirit") || temp.equals("Tree") || temp.equals("AmmoGift") ||
				temp.equals("HalfBomb") || temp.equals("FullBomb") || temp.equals("Health")){
			xd[x][y] = actFactory.getinstance("PathFree");
			logger.info("Ammo destroyed an Object");
			updatedI = x;
			updatedJ = y;			
			return true;
		}else if(temp.equals("Stones")){
			return true;
		}else{
			return false;
		}
	}	
	
	public void setObserver(Observer x){
		observer = x ;
	}
	public void drawMaze(){
		observer.draw();
	} 
	public void notifyObserver(){
		
		observer.update();
	}
	public boolean isPlayerWin() {
		// TODO Auto-generated method stub
		
		return player.getIL() == 29 && player.getJL() == 29;
	}
	public boolean isPlayerLose() {
		// TODO Auto-generated method stub
		return player.getSpirit() < 0 ;
	}
	public long getMin() {
		long x =(timeMilile -(System.currentTimeMillis()- start))/60000;
		if(x< 0){
			return 0;
		}else{
			return x;
		}
				
	}
	public long getSec() {
		long x = ((timeMilile -(System.currentTimeMillis()- start))%60000)/1000;
		if(x< 0){
			return 0;
		}else{
			return x;
		}	
	}
	public void setPlayerNewDirection(String temp) {
		player.setNewDirection(temp);
	}
	public void setPlayerRoutationDirection(String temp) {
		String nd = player.getNewDirection();
		String od = player.getOldDirection();
		if(nd.equals("Stop") && od.equals("Stop")){
			String rd = player.getRotationDir();
			switch(temp){
			case "W":
				rd = "Up";
				break;
			case "D":
				rd = "Right";
				break;
			case "A":
				rd = "Left";
				break;
			case "S":
				rd = "Down";
				break;						
			}
			player.setRotationDir(rd);
		}
	}
	
	public void setHighScore() {
		int score = player.getScore();
		File highScore = new File("HighScore.maze");
		if (highScore.exists()) {
			try {
				BufferedReader in = new BufferedReader(new FileReader(highScore));
				int currentHighScore = Integer.parseInt(in.readLine());
				int maxScore = Math.max(score, currentHighScore);
				PrintWriter out = new PrintWriter(highScore);
				out.print(maxScore);
				out.flush();
				out.close();
				in.close();
				HighScore = maxScore;
			} catch (Exception e) {
				logger.warn("Writing Score Exception");
				HighScore = score;
			}
		} else {
			try {
				PrintWriter out = new PrintWriter(highScore);
				out.print(score);
				out.flush();
				out.close();
				HighScore = score;
			} catch (FileNotFoundException e) {
				logger.warn("File Not Found Exception");
				HighScore = score;
			}
		}
	}

	public int getHighScore() {
		return HighScore ;
	}
	public Interactables[][] getArr() {
		// TODO Auto-generated method stub
		int x = 10;
		int y = 10;
		Interactables[][] m = map.getCharMaze();
		Interactables[][] mSmall = new Interactables[10][10];
		for(int i = 0;i < 10;i++){
			for(int j = 0;j < 10;j++){
				mSmall[i][j] = m[y-10+i][x-10+j];
			}
		}
		/*return mSmall;*/
		return m;
	}
	public void setObserver(Observer2 obs) {
		// TODO Auto-generated method stub
		observer2 = obs;
	}
	public void notfy(){
		observer2.update();
	}
	
}

package PacManGit;

import org.apache.log4j.Logger;

import Objects.Interactables;

public class FlyWeight {
	private Interactables[][] charMaze;
	//private Interactables[][] oldcharMaze;
	private MazeGenerator getMaze = MazeGenerator.getInstance();
	private ConvertIterator converter = new ConvertIterator();
	private char[][] m ;
	public final static Logger logger = Logger.getLogger(FlyWeight.class);

	public FlyWeight() {
		
	}

	public Interactables[][] getCharMaze() {
		if(charMaze == null){
			m = getMaze.generate(30, 30);
			logger.info("Maze is Generated");
			charMaze = converter.convert(m);
			//oldcharMaze = charMaze;
		}
		return charMaze;
	}
	public void resetMaze(){
		charMaze=converter.convert(m);
	}
	/*public Interactables[][] getOldCharMaze() {
		if(oldcharMaze == null){
			char[][] m = getMaze.generate(30, 30);
			charMaze = converter.convert(m);
			oldcharMaze = charMaze;
		}
		return oldcharMaze;
	}*/
	public void  setCharMaze(Interactables[][] x) {
		charMaze=x;
	}

	public MazeGenerator getMazeGenerator() {
		return getMaze;
	}
	
	
}

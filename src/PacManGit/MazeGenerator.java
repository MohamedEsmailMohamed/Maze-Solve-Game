package PacManGit;

import java.awt.Point;
import java.util.Random;
import java.util.Stack;

import mazeGenerate.IDifficulty;

public class MazeGenerator {
	private static MazeGenerator instance;
	public char[][] maze;
	private char[][] extendedmaze;
	private char[][] BorderedMaze;
	private boolean[][] visit;
	private String[][] directionVisited;
	private Random x = new Random();
	Stack<Point> navigator = new Stack<>();
	private int direction;
	private int cellTovisit;
	private int cellVisited;
	private static Object NO_TREESR;
	private static Object NO_TREESW;
	private static Object NO_SBOMBS;
	private static Object NO_BBOMBS;
	private static Object NO_AGIFTS;
	private static Object NO_HGIFTS;
	private static Object NO_UGIFTS;
	
	private final static int ZERO = 0;
	private final static int ONE = 1;
	private final static int TWO = 2;
	private MazeGenerator() {
		
	}
	public char[][] generate(int height, int width) {
		System.out.println("I generate");
		x.setSeed((long) (Math.random() * 10000));
		maze = new char[(height + ONE) / TWO][(width + ONE) / TWO];
		cellTovisit = maze.length * maze[ZERO].length;
		cellVisited = 0;
		visit = new boolean[maze.length][maze[ZERO].length];
		directionVisited = new String[maze.length][maze[ZERO].length];
		fillMaze();
		navigateMaze();
		extendedmaze = new char[height][width];
		putWalls();
		destructWallLevels();
		setBorderedMaze();
		distributeInteractable();
		return BorderedMaze;
	}

	private void fillMaze() {
		for (int i = 0; i < maze.length; i++) {
			for (int j = 0; j < maze[i].length; j++) {
				maze[i][j] = '.';
			}
		}
		maze[ZERO][ZERO] = 'S';
		navigator.push(new Point(ZERO, ZERO));
		visit[ZERO][ZERO] = true;
		cellVisited++;
		maze[maze.length - ONE][maze[ZERO].length - ONE] = 'E';
	}

	private void navigateMaze() {

		if (navigator.isEmpty() || cellVisited == cellTovisit) {

		} else if (checkSrround()) {
			navigateMaze();
		} else {

			direction = x.nextInt(4);
			Point nextPoint;
			switch (direction) {
			case (0):
				nextPoint = (Point) navigator.peek().clone();
				nextPoint.translate(ZERO, ONE);
				validatePoint(nextPoint, navigator.peek(), direction);
				break;
			case (1):
				nextPoint = (Point) navigator.peek().clone();
				nextPoint.translate(ONE, ZERO);
				validatePoint(nextPoint, navigator.peek(), direction);
				break;
			case (2):
				nextPoint = (Point) navigator.peek().clone();
				nextPoint.translate(ZERO, -ONE);
				validatePoint(nextPoint, navigator.peek(), direction);
				break;
			case (3):
				nextPoint = (Point) navigator.peek().clone();
				nextPoint.translate(-ONE, ZERO);
				validatePoint(nextPoint, navigator.peek(), direction);
				break;

			}
			navigateMaze();

		}

	}

	private void validatePoint(Point newPoint, Point oldPoint, int direction) {
		if (validIndexFunc(newPoint.x, newPoint.y) && !visit[newPoint.x][newPoint.y]) {
			visit[newPoint.x][newPoint.y] = true;
			cellVisited++;
			markDirAt(oldPoint.x, oldPoint.y, direction);
			navigator.push(newPoint);
		} else {
			Point anotherPoint = provideAnotherPoint();

			int newDirection = getNewDirection(anotherPoint);
			visit[anotherPoint.x][anotherPoint.y] = true;
			cellVisited++;
			markDirAt(oldPoint.x, oldPoint.y, newDirection);
			navigator.push(anotherPoint);
		}
	}

	private boolean checkSrround() {
		for (int i = navigator.peek().x - ONE; i <= navigator.peek().x + ONE; i++) {
			for (int j = navigator.peek().y - ONE; j <= navigator.peek().y + ONE; j++) {
				if (validIndexLoop(i, j, navigator.peek().x, navigator.peek().y) && !visit[i][j]) {
					return false;
				}
			}
		}
		navigator.pop();
		return true;
	}

	private Point provideAnotherPoint() {
		for (int i = navigator.peek().x - ONE; i <= navigator.peek().x + ONE; i++) {
			for (int j = navigator.peek().y - ONE; j <= navigator.peek().y + ONE; j++) {
				if (validIndexLoop(i, j, navigator.peek().x, navigator.peek().y) && !visit[i][j]) {
					return new Point(i, j);
				}
			}
		}
		throw null;
	}

	private int getNewDirection(Point anotherPoint) {

		if (anotherPoint.x != navigator.peek().x) {
			return anotherPoint.x > navigator.peek().x ? 1 : 3;
		} else if (anotherPoint.y != navigator.peek().y) {
			return anotherPoint.y > navigator.peek().y ? 0 : 2;
		}
		throw null;
	}

	private boolean validIndexLoop(int i, int j, int io, int jo) {
		return i >= ZERO && i < maze.length && j >= ZERO && j < maze[ZERO].length && io - jo != i - j
				&& io + jo != i + j;
	}

	private boolean validIndexFunc(int i, int j) {
		return i >= ZERO && i < maze.length && j >= ZERO && j < maze[ZERO].length;
	}

	private void markDirAt(int i, int j, int direction) {
		switch (direction) {
		case (0):
			if (directionVisited[i][j] == null) {
				directionVisited[i][j] = new String();
			}
			directionVisited[i][j] += "r";
			break;
		case (1):
			if (directionVisited[i][j] == null) {
				directionVisited[i][j] = new String();
			}
			directionVisited[i][j] += "d";
			break;
		case (2):
			if (directionVisited[i][j] == null) {
				directionVisited[i][j] = new String();
			}
			directionVisited[i][j] += "l";
			break;
		case (3):
			if (directionVisited[i][j] == null) {
				directionVisited[i][j] = new String();
			}
			directionVisited[i][j] += "u";
			break;
		}
	}

	private void putWalls() {
		for (int i = ONE; i < extendedmaze.length; i += TWO) {
			for (int j = ZERO; j < extendedmaze.length; j++) {
				extendedmaze[i][j] = '#';
			}
		}
		for (int j = ONE; j < extendedmaze.length; j += TWO) {
			for (int i = ZERO; i < extendedmaze.length; i++) {
				extendedmaze[i][j] = '#';
			}
		}
	}

	private void destructWallLevels() {
		int level = 0;
		while (destructWalls(level)) {
			level++;
		}
	}

	private boolean destructWalls(int level) {
		boolean change = false;
		for (int i = 0; i < extendedmaze.length; i += 2) {
			for (int j = 0; j < extendedmaze[i].length; j += 2) {
				Point wallElliminated = getWall(i / 2, j / 2, i, j, level);
				if (wallElliminated != null) {
					change = true;
					extendedmaze[wallElliminated.x][wallElliminated.y] = ' ';
				}
			}
		}
		return change;
	}

	private Point getWall(int i, int j, int reali, int realj, int level) {
		String wallDirection = directionVisited[i][j];
		if (wallDirection != null && level < wallDirection.length()) {
			switch (wallDirection.charAt(level)) {
			case 'r':
				return new Point(reali, realj + ONE);
			case 'd':
				return new Point(reali + ONE, realj);
			case 'l':
				return new Point(reali, realj - ONE);
			case 'u':
				return new Point(reali - ONE, realj);
			default:
				return null;
			}
		}
		return null;
	}

	private void setBorderedMaze() {
		BorderedMaze = new char[((extendedmaze.length + 1) / 2) * 2 + 1][((extendedmaze[0].length + 1) / 2) * 2 + 1];
		for (int j = 0; j < BorderedMaze[ZERO].length; j++) {
			BorderedMaze[ZERO][j] = '#';
		}
		for (int i = 0; i < BorderedMaze[ZERO].length; i++) {
			BorderedMaze[i][ZERO] = '#';
		}
		for (int i = 0; i < extendedmaze.length; i++) {
			for (int j = 0; j < extendedmaze[i].length; j++) {
				BorderedMaze[i + ONE][j + ONE] = extendedmaze[i][j];
			}
		}
		if (BorderedMaze.length - extendedmaze.length == TWO) {
			for (int j = 0; j < BorderedMaze[ZERO].length; j++) {
				BorderedMaze[BorderedMaze.length - ONE][j] = '#';
			}
		}

		if (BorderedMaze[ZERO].length - extendedmaze[ZERO].length == TWO) {
			for (int i = 0; i < BorderedMaze.length; i++) {
				BorderedMaze[i][BorderedMaze.length - ONE] = '#';
			}
		}

	}

	private void distributeInteractable() {
		distribute((int) NO_TREESR, ' ', 'T');
		distribute((int) NO_TREESW, '#', 'T');
		distribute((int) NO_SBOMBS, ' ', 'b');
		distribute((int) NO_BBOMBS, '#', 'B');
		distribute((int) NO_AGIFTS, ' ', 'A');
		distribute((int) NO_HGIFTS, ' ', 'H');
		distribute((int) NO_UGIFTS, ' ', 'U');
	}
	private void distribute(int max,char sub,char with) {
		int temp = ZERO;
		int i;
		int j;
		while (temp != max) {
			i = x.nextInt(BorderedMaze.length - 2) + 1;
			j = x.nextInt(BorderedMaze[ZERO].length - 2) + 1;
			if (BorderedMaze[i][j] == sub) {
				if (checkExistance(i, j, with)) {
					BorderedMaze[i][j] = with;
					temp++;
				}
			}
		}
	}
	private boolean checkExistance(int row, int column, char checkValue) {
		for (int i = row - ONE; i <= row + ONE; i++) {
			for (int j = column - ONE; j <= column + ONE; j++) {
				if (BorderedMaze[i][j] == checkValue) {
					return false;
				}
			}
		}
		return true;
	}

	public void showMaze() {
		for (int i = 0; i < maze.length; i++) {
			for (int j = 0; j < maze[i].length; j++) {
				System.out.print(maze[i][j] + " ");
			}
			System.out.println();
		}
	}

	public void showExtendedMaze() {
		for (int i = 0; i < extendedmaze.length; i++) {
			for (int j = 0; j < extendedmaze[i].length; j++) {
				System.out.print(extendedmaze[i][j] + " ");
			}
			System.out.println();
		}
	}

	public void showBorderedMaze() {
		for (int i = 0; i < BorderedMaze.length; i++) {
			for (int j = 0; j < BorderedMaze[i].length; j++) {
				System.out.print(BorderedMaze[i][j] + " ");
			}
			System.out.println();
		}
	}

	public void showDir() {
		for (int i = 0; i < directionVisited.length; i++) {
			for (int j = 0; j < directionVisited[i].length; j++) {
				System.out.print((directionVisited[i][j] == null ? "." : directionVisited[i][j]) + " ");
			}
			System.out.println();
		}
	}
	public void setLevel(IDifficulty diff) {
		System.out.println("i level");
		NO_TREESR = diff.getTreeSR();
		NO_TREESW = diff.getTreeSW();
		NO_SBOMBS = diff.getSBombs();
		NO_BBOMBS = diff.getBBombs();
		NO_AGIFTS = diff.getAGifts();
		NO_HGIFTS = diff.getHGifts();
		NO_UGIFTS = diff.getUGifts();
	}
	public static MazeGenerator getInstance() {
		if(instance == null) {
			instance = new MazeGenerator();
		}
		return instance;
	}
}

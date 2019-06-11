package PacManGit;


import Objects.Interactables;
import Objects.InteractablesFactory;


public class ConvertIterator {
	private final static char SBOMS = 'b';
	private final static char BBOMS = 'B';
	private final static char TREE = 'T';
	private final static char HEALTH = 'H';
	private final static char AMMO = 'A';
	private final static char SPIRIT = 'U';
	private final static char STONES = '#';
	private final static int XSIZE = 31;
	private final static int YSIZE = 31;
	private InteractablesFactory actFactory = new InteractablesFactory();

	public Interactables[][] convert(char[][] x) {
		Interactables[][] c = new Interactables[XSIZE][YSIZE];
		for (int i = 0; i < x.length; i++) {
			for (int j = 0; j < x[0].length; j++) {
				if (x[i][j] == SBOMS) {
					c[i][j] = actFactory.getinstance("HalfBomb");
				} else if (x[i][j] == BBOMS) {
					c[i][j] = actFactory.getinstance("FullBomb");
				} else if (x[i][j] == TREE) {
					c[i][j] = actFactory.getinstance("Tree");
				} else if (x[i][j] == HEALTH) {
					c[i][j] = actFactory.getinstance("Health");
				} else if (x[i][j] == AMMO) {
					c[i][j] = actFactory.getinstance("AmmoGift");
				} else if (x[i][j] == SPIRIT) {
					c[i][j] = actFactory.getinstance("Spirit");
				} else if (x[i][j] == STONES) {
					c[i][j] = actFactory.getinstance("Stones");
				} else {
					c[i][j] = actFactory.getinstance("PathFree");
				}

			}
		}
		return c;

	}

}

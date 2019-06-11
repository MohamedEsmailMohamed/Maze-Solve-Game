package Objects;


public class Player extends State implements Interactables {
		private String newDirection = "Stop";
		private String oldDirection = "Stop";
		private String rotationDir = "Down" ;
	 	private int Spirit = 4;
	 	private int Ammo = 6;
		private int Score = 0;
	 	private int IndexY = 22;
		private int IndexX = 22;
		
	 	private Boolean Winner = false;
	 	private Boolean Loser = false;

	 	private Boolean Existance = false;
	 	@Override
	 	public boolean isWinner() {
	 	// TODO Auto-generated method stub
	 	super.isWinner();
	 	return Winner;
	 	}
	 	@Override
	 	public boolean isLoser() {
	 	// TODO Auto-generated method stub
	 	super.isLoser();
	 	return Loser;
	 	}
		
		@Override
		public int getScore() {
			super.getScore();
			return Score;
		}
	 
	 	@Override
	 	public int getAmmo() {
	 		// TODO Auto-generated method stub
	 		super.getAmmo();
	 		return Ammo;
	 	}
	 
	 	
	 	@Override
	 	public int getIR() {
			return ((IndexY+21) / 22);
	
	 
	 	}
	 	@Override
	 	public String getRotationDir() {
	 	// TODO Auto-generated method stub
	 	return rotationDir;
	 	}
	 
	 	@Override
	 	public int getJD() {
			return ((IndexX+21) / 22);
	
	 	}
	 
	 	@Override
	 	public int getIL() {
			return IndexY/ 22;
	 
	 	}
	 
	 	@Override
	 	public int getJL() {
			return  (IndexX) / 22;
	
	 
	 	}
	 
	 	@Override
	 	public int getindexX() {
	 		return IndexX;
	 
	 	}
	 
	 	@Override
	 	public int getindexY() {
	 		return IndexY;
	 
	 	}
	 
	 	@Override
	 	public String getNewDirection() {
	 		// TODO Auto-generated method stub
	 		super.getNewDirection();
	 		return newDirection;
	 	}
	 
	 	@Override
	 	public String getOldDirection() {
	 		// TODO Auto-generated method stub
	 		super.getOldDirection();
	 		return oldDirection;
	 	}
	 
	 	@Override
	 	public int getSpirit() {
	 		// TODO Auto-generated method stub
	 		super.getSpirit();
	 		return Spirit;
	 	}
	 	@Override
	 	public int getISmall(){
			return IndexY % 22;		
	
	 	}
	 	@Override
	 	public int getJSmall(){
			return IndexX % 22;		
			
	 	}
	 	@Override
	 	public boolean isExisted() {
	 		super.isExisted();
	 		return Existance;
	 		// TODO Auto-generated method stub
	 	}
	 
	 	@Override
	 	public void setAmmo(int x) {
	 		super.setAmmo(x);
	 		
	 		Ammo = x;
	 		if (Ammo > 6){
	 			Ammo = 6;
	 		}	 
	 	}
		@Override
		public void setScore(int y) {
			// TODO Auto-generated method stub
			super.setScore(y);
			Score=y;
		}
	 	
	 
	 	@Override
	 	public void setNewDirection(String x) {
	 		// TODO Auto-generated method stub
	 		super.setNewDirection(x);
	 		newDirection = x;
	 	}
        @Override
	 	public void setRotationDir(String x) {
	 	// TODO Auto-generated method stub
	 	super.setRotationDir(x);
	 	 rotationDir=x;
	 	}
	 
	 	@Override
	 	public void setOldDirection(String x) {
	 		// TODO Auto-generated method stub
	 		super.setOldDirection(x);
	 		oldDirection = x;
	 	}
	 
	 	@Override
	 	public void setSpirit(int x) {
	 		// TODO Auto-generated method stub
	 		super.setSpirit(x);
	 		Spirit = x;
	 		if(Spirit > 8){
	 			Spirit = 8;
	 		}
	 	}
	 
	 	@Override
	 	public void setindexX(int x) {
	 		// TODO Auto-generated method stub
	 		super.setindexX(x);
	 		IndexX = x;
	 	}
	 
	 	@Override
	 	public void setindexY(int y) {
	 		// TODO Auto-generated method stub
	 		super.setindexY(y);
	 		IndexY = y;
	 	}
	 	@Override
	 	public void setWinner(boolean e) {
	 	// TODO Auto-generated method stub
	 		super.setWinner(e);
	 		Winner = e;
	 	}
	 	@Override
	 	public void setLoser(boolean e) {
	 	// TODO Auto-generated method stub
	 		super.setLoser(e);
	 		Loser = e;
	 	}
	 	
	 	
	 	public	void resetPosition(){
	 		setindexX(22);
			setindexY(22);
			setAmmo(6);
			setNewDirection("Stop");
			setOldDirection("Stop");
			setRotationDir("Down");
	 	}
	 
	 }



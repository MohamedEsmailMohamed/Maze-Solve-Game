package PacManGit;



import Objects.Ammo;

public class AmmoPool {
	  private final static int SIZE =6; 
	  private Iterator AmmoList;
	 
	  public AmmoPool() {
		  AmmoList = new Iterator();
		  for(int i=0;i<SIZE;i++){
			  AmmoList.add(new Ammo(i));
		  }		  
	  }

	
	protected Ammo create() {
		// TODO Auto-generated method stub
		AmmoList.ResetPointer();
		Ammo x  = null;
		if(AmmoList.hasNext()){
			x = AmmoList.next();
			AmmoList.remove(x);
		}		
		return x;
	}


	public void expire(Ammo o) {
		AmmoList.add(o);		
	}

}

package Objects;

public class AmmoGift  extends State implements Interactables{
	private  boolean Existance = true;
	@Override
	public void setExistance(boolean e) {
		// TODO Auto-generated method stub
		super.setExistance(e);
		Existance = e;
	}
	@Override
	public boolean isExisted() {
		// TODO Auto-generated method stub
		super.isExisted();
		return Existance;
	}

}

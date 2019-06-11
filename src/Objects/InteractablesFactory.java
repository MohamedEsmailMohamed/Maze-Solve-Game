package Objects;


public class InteractablesFactory {
	
	
	
	public Interactables getinstance(String type){
		switch(type){
		case "AmmoGift":
			return new AmmoGift();
		case "Health":
			return  new Health();
		case "HalfBomb":
			return  new HalfBomb();
		case "FullBomb":
			return  new FullBomb();
		case "Stones":
			return  new Stones();
		case "PathFree":
			return  new PathFree();
		case "Tree":
			return  new Tree();
		case "Spirit":
			return  new Spirit();
		}
		return null;
	}
}

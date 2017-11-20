package projet;

public abstract class Entite {

	protected static int posX;
	protected static int posY;
	
	public static int getPosX() {
		return posX;
	}
	public void setPosX(int posX) {
		Entite.posX = posX;
	}
	public static int getPosY() {
		return posY;
	}
	public void setPosY(int posY) {
		Entite.posY = posY;
	}

	
	
}

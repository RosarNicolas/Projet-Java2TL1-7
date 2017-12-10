/**
 * 
 */
package testMVC;


/**
 * @author he201460
 *
 */
public class Position 
{
	private int posX;
	private int posY;
	
	/**
	 * Constructeur de la classe Position
	 * @param posX : int
	 * @param posY : int
	 */
	public Position(int posX, int posY)
	{
		this.posX = posX;
		this.posY = posY;
	}
	
	/**
	 * compare 2 position pour voir si elle sont a "portee" distance l'une de l'autre et sur une ligne de vue (meme X ou meme Y)
	 * @param x : Position
	 * @param portee : int (portee d'une arme)
	 * @return : true si elle sont a "portee" distance et en ligne de vue, false sinon
	 */
	public boolean verifierDistance(Position x, int portee)
	{
		if(this.getPosX() == x.getPosX())
		{
			return Math.abs(this.getPosY() - x.getPosY()) <= portee;
		}
		else if(this.getPosY() == x.getPosY())
		{
			return Math.abs(this.getPosX() - x.getPosX()) <= portee;
		}
		else
		{
			return false;
		}
	}
	
	public int getPosX() {
		return posX;
	}

	public void setPosX(int posX) {
		this.posX = posX;
	}

	public int getPosY() {
		return posY;
	}

	public void setPosY(int posY) {
		this.posY = posY;
	}
	
	public boolean equals(Position x)
	{
		return this.posX == x.getPosX() && this.posY == x.getPosY();
	}
}
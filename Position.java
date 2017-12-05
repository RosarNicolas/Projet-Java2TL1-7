/**
 * 
 */
package main;

/**
 * @author he201460
 *
 */
public class Position 
{
	private int posX;
	private int posY;
	
	public Position(int posX, int posY)
	{
		this.posX = posX;
		this.posY = posY;
	}
	
	public boolean verifierDistance(Position x, int portee)
	{
		if(this.getPosX() == x.getPosX())
		{
			return Math.abs(this.getPosY() - x.getPosY()) <= 2;
		}
		else if(this.getPosY() == x.getPosY())
		{
			return Math.abs(this.getPosX() - x.getPosX()) <= 2;
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

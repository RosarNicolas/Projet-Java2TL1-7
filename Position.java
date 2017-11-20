/**
 * 
 */
package main1;

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

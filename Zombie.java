/**
 * 
 */
package main1;

/**
 * @author he201460
 *
 */
public class Zombie extends Entite
{
	public Zombie(String nom, int id, int pointsDeVie, int pointsDAction, Position emplacement)
	{
		super(nom,id,pointsDeVie, pointsDAction,emplacement);
	}

	@Override
	public void deplacer(String direction)
	{
		//iujgzuibzr
	}
	
	public void deplacer(Personnage perso)
	{
		//jrzegiuer
	}
	@Override
	public int attaquer() 
	{
		//attaque de zombie ?????
		return 1;
	}
	
	public int attaquer(Personnage perso) 
	{
		attaquer();
		//attaque de zombie ?????
		return 1;
	}

	@Override
	public void apparition(int x, int y)
	{
		//apparition de zombie ????
	}
	
	public static int apparition()
	{
		return 1;
	}
}

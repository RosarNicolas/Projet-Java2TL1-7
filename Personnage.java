/**
 * 
 */
package main;

import java.util.HashMap;
import java.util.Scanner;

/**
 * @author he201460
 *
 */
public class Personnage extends Entite
{
	private Arme armeGauche;
	private Arme armeDroite;
	Scanner sc = new Scanner(System.in);
	public Personnage(String nom, int id, int pointsDeVie, int pointsDAction, Position emplacement)
	{
		super(nom,id,pointsDeVie, pointsDAction,emplacement);
	}
	
	
	
	public void deplacer(String direction, Carte carte) 
	{
		int couranteY = this.getEmplacement().getPosY();
		int couranteX = this.getEmplacement().getPosX();
		
		if(direction.equals("haut") && verification(carte, new Position(couranteX, couranteY - 1)))
		{
			couranteY --;
			Position nouvelle = new Position(couranteX, couranteY);
			
			
			this.setEmplacement(nouvelle);
		}
		else if(direction.equals("bas") && verification(carte, new Position(couranteX, couranteY + 1)))
		{
			couranteY ++;
			//peut etre change le setPosition
			Position nouvelle = new Position(couranteX, couranteY);
			this.setEmplacement(nouvelle);
		}
		else if(direction.equals("droite") && verification(carte, new Position(couranteX + 1, couranteY )))
		{
			couranteX ++;
			//peut etre change le setPosition
			Position nouvelle = new Position(couranteX, couranteY);
			this.setEmplacement(nouvelle);
		}
		else if(direction.equals("gauche") && verification(carte, new Position(couranteX - 1, couranteY)))
		{
			couranteX --;
			//peut etre change le setPosition
			Position nouvelle = new Position(couranteX, couranteY);
			this.setEmplacement(nouvelle);
		}
		else
		{
			System.out.println("Vous ne pouvez pas aller par la");
		}
	}

	
	public int attaquer(int choixDeLarme) 
	{
		if(choixDeLarme == 1)
		{
			for(int i = 0 ; i<armeGauche.getNombreDeFrappe() ; i++)
			{
				if(armeGauche.tentativeDAttaque())
				{
					return armeGauche.getDegats();
				}
			}
		
		}
		else // else if
		{
			for(int i = 0 ; i<armeDroite.getNombreDeFrappe() ; i++)
			{
				if(armeDroite.tentativeDAttaque())
				{
					return armeDroite.getDegats();
				}
			}
		}
		//else
		return 1;
	}

	
	public void fouille(HashMap<Integer,Arme> armes)
	{
		int iDArme = -5;
		iDArme = (int) (Math.random() * (11));
		iDArme++;
		if(armeGauche == null) 
		{
			armeGauche = armes.get(iDArme);
			System.out.println("Vous possédez maintenant l'arme "+ armeGauche.getNomDeLarme() +" dans la main gauche" );
		}
		else if(armeDroite == null)
		{
			armeDroite = armes.get(iDArme);
			System.out.println("Vous possédez maintenant l'arme " +  armeDroite.getNomDeLarme() + " dans la main doite");

		}
		else
		{
			System.out.println("Vous n'avez plus de place");
		}
				

	}
	
	public void jeterUneArme(int x)
	{
		if(x == 1)
		{
			this.armeGauche = null;
		}
		else
		{
			this.armeDroite = null;
		}
	}


	public Arme getArmeGauche() {
		return armeGauche;
	}


	public void setArmeGauche(Arme armeGauche) {
		this.armeGauche = armeGauche;
	}


	public Arme getArmeDroite() {
		return armeDroite;
	}


	public void setArmeDroite(Arme armeDroite) {
		this.armeDroite = armeDroite;
	}


	@Override
	public int attaquer() {
		// TODO Auto-generated method stub
		return 0;
	}


	public boolean verification(Carte carte, Position z) 
	{
		String debug  = carte.getTab()[z.getPosY()][z.getPosX()];
		String ddebug = carte.getCarPossible();
		boolean debugz  = debug.equals(ddebug); 
		return (carte.getTab()[z.getPosY()][z.getPosX()].equals(carte.getCarPossible()));
	}
}

/**
 * 
 */
package main1;

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
	
	
	@Override
	public void deplacer(String direction) 
	{
		int couranteY = this.getEmplacement().getPosY();
		int couranteX = this.getEmplacement().getPosX();
		
		if(direction.equals("haut"))
		{
			couranteY --;
			//peut etre change le setPosition
			Position nouvelle = new Position(couranteX, couranteY);
			this.setEmplacement(nouvelle);
		}
		else if(direction.equals("bas"))
		{
			couranteY ++;
			//peut etre change le setPosition
			Position nouvelle = new Position(couranteX, couranteY);
			this.setEmplacement(nouvelle);
		}
		else if(direction.equals("droite"))
		{
			couranteX ++;
			//peut etre change le setPosition
			Position nouvelle = new Position(couranteX, couranteY);
			this.setEmplacement(nouvelle);
		}
		else
		{
			couranteX --;
			//peut etre change le setPosition
			Position nouvelle = new Position(couranteX, couranteY);
			this.setEmplacement(nouvelle);
		}
	}

	@Override
	public int attaquer() 
	{
		
		System.out.println("Quelle arme voulez vous utiliser ?(1 arme gauche /// 2 arme droite");
		int choixDeLarme = Integer.parseInt(sc.next());
		//if() methode iterative ne cas de mauvais entree ?????
		
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

	@Override
	public void apparition(int x, int y) 
	{
		Position courante = new Position(x, y);
		this.setEmplacement(courante);
		//perso.setVisibility(true);
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
}

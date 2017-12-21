/**
 * 
 */
package main;

import java.util.HashMap;
import java.util.LinkedList;
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
	
	/**
	 * constructeur de la classe Personnage
	 * @param nom : String
	 * @param id : int
	 * @param pointsDeVie : int
	 * @param pointsDAction : int
	 * @param emplacement : Position
	 */
	public Personnage(String nom, int id, int pointsDeVie, int pointsDAction, Position emplacement)
	{
		super(nom,id,pointsDeVie, pointsDAction,emplacement);
	}
	
	
	/**
	 * update la position du joueur
	 * si la position vers laquelle le joueur veut aller n'est pas atteignable alors fais en sorte de lui redonner un point d'action
	 * 
	 * direction est un String valable (haut, bas , gauche, droite)
	 * carte est la carte de jeu
	 * @param String direction, Carte carte
	 */
	public boolean deplacer(String direction, Carte carte,LinkedList<Zombie> zombiesSurCase) 
	{
		int couranteY = this.getEmplacement().getPosY();
		int couranteX = this.getEmplacement().getPosX();
		
		if(direction.equals("haut") && verification(carte, new Position(couranteX, couranteY - 1),zombiesSurCase))
		{
			couranteY --;
			Position nouvelle = new Position(couranteX, couranteY);
			this.setEmplacement(nouvelle);
			return true;
		}
		else if(direction.equals("bas") && verification(carte, new Position(couranteX, couranteY + 1),zombiesSurCase))
		{
			couranteY ++;
			//peut etre change le setPosition
			Position nouvelle = new Position(couranteX, couranteY);
			this.setEmplacement(nouvelle);
			return true;
		}
		else if(direction.equals("droite") && verification(carte, new Position(couranteX + 1, couranteY ),zombiesSurCase))
		{
			couranteX ++;
			//peut etre change le setPosition
			Position nouvelle = new Position(couranteX, couranteY);
			this.setEmplacement(nouvelle);
			return true;
		}
		else if(direction.equals("gauche") && verification(carte, new Position(couranteX - 1, couranteY),zombiesSurCase))
		{
			couranteX --;
			//peut etre change le setPosition
			Position nouvelle = new Position(couranteX, couranteY);
			this.setEmplacement(nouvelle);
			return true;
		}
		else
		{
			this.setPointsDAction(this.getPointsDAction() + 1);
			return false;
		}
	}

	/**
	 * permet de renvoyer les degats de l'arme si elle touche 
	 * @param choixDeLarme : int (1 = gauche, 2 = droite)
	 * @return les degats de l'arme si elle touche : int
	 */
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
			return 0;
		
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
		return 0;
	}

	/**
	 * renvoie une arme parmis celle de la liste du jeu au hasard
	 * si le joueur n'a plus de place fais en sorte de ne pas lui retirer un points d'action
	 * @param armes : LinkedList
	 */
	public int fouille(HashMap<Integer,Arme> armes)
	{
		int iDArme = -5;
		iDArme = (int) (Math.random() * (11));
		iDArme++;
		if(armeGauche == null) 
		{
			armeGauche = armes.get(iDArme);
			return 1;
		}
		else if(armeDroite == null)
		{
			armeDroite = armes.get(iDArme);
			return 2;

		}
		else
		{
			this.setPointsDAction(this.getPointsDAction() + 1);
			return 0;
		}
				

	}
	
	/**
	 * permet de rendre une des armes du joueur null pour pouvoir en recuperer une autre
	 * @param x : int (1 = gauche, 2 = droite)
	 */
	public void jeterUneArme(int x)
	{
		//verifier si arme a jeter
		 if(x == 1 && this.armeGauche == null)
		{
			 
		}
		else if(x == 2 && this.armeDroite == null)
		{
			;
		}
		else if(x == 1)
		{
			this.armeGauche = null;
		}
		else if(x == 2)
		{
			this.armeDroite = null;
		}
	
		else
		{

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
	public int attaquer() 
	{
		return 0;
	}

	/**
	 * verifie si le joueur peut se deplacer a une certaine coordonnée
	 * @param carte : Carte
	 * @param z : Position
	 * @return true si le joueur peut se deplacer a la position z, false sinon
	 */
	public boolean verification(Carte carte, Position z,LinkedList<Zombie> zombiesSurCase) 
	{
		try 
		{
			return (carte.getTab()[z.getPosY()][z.getPosX()].equals(carte.getCarPossible()) && zombiesSurCase.isEmpty());
		}
		catch(ArrayIndexOutOfBoundsException e)
		{
			e.getMessage();
			return false;
		}
	}	
}
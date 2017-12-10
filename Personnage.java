/**
 * 
 */
package testMVC;

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
		
		//MVC
		/*else
		{
			this.setPointsDAction(this.getPointsDAction() + 1);
			System.out.println("Vous ne pouvez pas aller par là");
		}*/
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
			//MVC
			//System.out.println("Vous possédez maintenant l'arme "+ armeGauche.getNomDeLarme() +" dans la main gauche" );
		}
		else if(armeDroite == null)
		{
			armeDroite = armes.get(iDArme);
			return 2;
			//MVC
			//System.out.println("Vous possédez maintenant l'arme " +  armeDroite.getNomDeLarme() + " dans la main doite");

		}
		else
		{
			//MVC
			//System.out.println("Vous n'avez plus de place");
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
			 //MVC
			//System.out.println("Vous n'avez pas d'arme à jeter dans la main gauche");
		}
		else if(x == 2 && this.armeDroite == null)
		{
			//MVC
			//System.out.println("Vous n'avez pas d'arme à jeter dans la main droite");
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
			//MVC
			//System.out.println("Mauvais numero pour choix de l'arme");
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
		return 0;
	}

	/**
	 * verifie si le joueur peut se deplacer a une certaine coordonnée
	 * @param carte : Carte
	 * @param z : Position
	 * @return true si le joueur peut se deplacer a la position z, false sinon
	 */
	public boolean verification(Carte carte, Position z) 
	{
		try 
		{
			return (carte.getTab()[z.getPosY()][z.getPosX()].equals(carte.getCarPossible()));
		}
		catch(ArrayIndexOutOfBoundsException e)
		{
			e.getMessage();
			return false;
		}
	}


	
}
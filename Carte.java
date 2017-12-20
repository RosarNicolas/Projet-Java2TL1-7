/**
 * 
 */
package testMVC;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.LinkedList;

/**
 * @author he201460
 *
 */
public class Carte 
{
	/**
	 * 
	 * String carPossible : contient le caract�re rempr�sentant une case accessible par les entit�s.
	 * String carImpossible : contient le caract�re rempr�sentant une case inpratiquable par les entit�s.
	 */
	private int longueur;
	private int largeur;
	private String[][] tab;
	private Position sortie;
	private Position apparition;
	private String carPossible;
	private String carImpossible;
	private String pionJoueur = "j";
	private String pionZombie = "z";
	/**
	 * constructeur de la classe carte
	 * se construit a partir d'un fichier texte
	 * premiere ligne vide (important vis a vis de l'encodage)
	 * 2eme ligne hauteur et largeur de la map
	 * 3eme ligne position de l'apparition du joueur
	 * 4eme ligne position de la sortie de la carte
	 * 5eme ligne un caractere sur lequel les joueurs et zombies peuvent marcher et un autre ou ils ne peuvent pas
	 * @param path
	 */
	public Carte(String path)
	{
		try 
		{
			Position courante = new Position(0,0);
			String [] tabCourant = new String[50];
			String line;
			BufferedReader test = new BufferedReader(new FileReader(path));
			line = test.readLine();
			
			
			line = test.readLine();
			tabCourant = line.split(" ");
			this.longueur = Integer.parseInt(tabCourant[0]);
			this.largeur = Integer.parseInt(tabCourant[1]);
			tab = new String[this.longueur][this.largeur];
			
			line = test.readLine();
			tabCourant = line.split(" ");
			courante = new Position(Integer.parseInt(tabCourant[0]), Integer.parseInt(tabCourant[1]));
			this.apparition = courante;
			
			line = test.readLine();
			tabCourant = line.split(" ");
			courante = new Position(Integer.parseInt(tabCourant[0]), Integer.parseInt(tabCourant[1]));
			this.sortie = courante;
			
			line = test.readLine();
			tabCourant = line.split("\\|");
			carPossible = tabCourant[1];
			carImpossible = tabCourant[2];
			
			for(int i = 0;i<longueur;i++)
			{
				line = test.readLine();
				line = test.readLine();
				tabCourant = line.split("\\|");
				/*for(int z =  0; z<tabCourant.length; z++)
				{
					if(tabCourant[z] != null)
					{
						tabCourant[z].trim();
					}
				}*/
				
				for(int y = 1 ; y<=this.largeur && tabCourant[y] != null ; y++)
				{
					tab[i][y-1] = tabCourant[y];
				}
				
			}
			test.close();
		} 
		catch (Exception e) 
		{
			e.getMessage();
		}
	}
	
	/**
	 * permet de generer la carte en console en fonction du fichiers texte et des entites presentes sur la carte
	 * un J represente le joueurs
	 * un Z represente 1 zombie
	 * un X represente un joueur avec 1 ou plusieurs zombies sur la emme case
	 * un chiffre represente un nombre de zombie sur la meme case
	 * @param ent
	 */
/*	public void generer(LinkedList<Entite> ent)
	{
		for(int i = 0;i<tab.length;i++)
		{
			genererLigne(this.largeur);
			for(int j = 0;j<tab[0].length;j++)
			{
				boolean dejaEcrit = false;
				int counter = 0;
				boolean caseJoueur = false;
				/////////////
				for(Entite perso : ent)
				{
				/////////////
					
					if(perso.getEmplacement().getPosY() == i && perso.getEmplacement().getPosX() == j)
					{
						String a = perso.getClass().toString();
						if(a.equals("class testMVC.Personnage"))
						{
							caseJoueur = true;
							
						}
						else
						{
							counter++;
						}
					}
				/////////////
				}
				
				if(caseJoueur && counter == 0)
				{
					System.out.print("| "+ pionJoueur +" ");
					dejaEcrit = true;
				}
				if(caseJoueur && counter > 0)
				{
					System.out.print("| x ");
					dejaEcrit = true;
				}
				else if((!caseJoueur) && counter > 1)
				{
					System.out.print("| " + counter + " ");
					dejaEcrit = true;
				}
				else if((!caseJoueur) && counter == 1)
				{
					System.out.print("| z ");
					dejaEcrit = true;
				}
				////////////
				if(!dejaEcrit)
				{
					System.out.print("|" + tab[i][j] );
				}
			}
			System.out.println("|");
		}
		genererLigne(this.largeur);
	}*/
	
	/**
	 * methode necessaire a la generation des murs de la carte en fonction de la largeur de la carte
	 * @param x
	 */
	/*public void genererLigne(int x)
	{
		 
      for(int w = 0 ; w < largeur ; w++ ) 
      {
    	  System.out.print("||||");
	  }
      System.out.println("|");

	}*/
	
	//getters and setters
	/** @return longueur : renvoie la longeur de la carte.
	 */
	public int getLongueur() {
		return longueur;
	}

	/**
	 * @param longueur : d�finir la longeur de la carte.
	 */
	public void setLongueur(int longueur) {
		this.longueur = longueur;
	}

	/**@return largeur : renvoie la largeur de la carte.
	 */
	public int getLargeur() {
		return largeur;
	}

	/**@param largeur : d�finir la largeur de la carte.
	 */
	public void setLargeur(int largeur) {
		this.largeur = largeur;
	}

	/**
	 * @return tab : renvoie le tableau � double entr�e
	 * contenant les cases de la carte.
	 */
	public String[][] getTab() {
		return tab;
	}

	/**
	 * @param tab : instancier le tableau qui
	 * contiendra les caract�res repr"sentant la carte.
	 */
	public void setTab(String[][] tab) {
		this.tab = tab;
	}

	/**
	 * @return sortie : renvoie la position de sortie
	 * que le personnage doit atteindre pour finir le jeu.
	 */
	public Position getSortie() {
		return sortie;
	}

	/**
	 * @param sortie : D�finir la position de sortie
	 * pour terminer le jeu.
	 */
	public void setSortie(Position sortie) {
		this.sortie = sortie;
	}

	/**
	 * @return apparition : renvoie la position d'apparition.
	 */
	public Position getApparition() {
		return apparition;
	}

	/**
	 * 
	 * @param apparition : d�finis la position d'apparition
	 * d'une entit�.
	 */
	/*public void setApparition(Position apparition) {
		this.apparition = apparition;
	}*/

	public String getCarPossible() {
		return carPossible;
	}

	public void setCarPossible(String carPossible) {
		this.carPossible = carPossible;
	}

	public String getCarImpossible() {
		return carImpossible;
	}

	public void setCarImpossible(String carImpossible) {
		this.carImpossible = carImpossible;
	}

	public  String getPionJoueur() {
		return this.pionJoueur;
	}

	public void setPionJoueur(String pionJoueur) {
		this.pionJoueur = pionJoueur;
	}

	public String getPionZombie() {
		return this.pionZombie;
	}

	public void setPionZombie(String pionZombie) {
		this.pionZombie = pionZombie;
	}
	
}
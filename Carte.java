/**
 * 
 */
package main;

import java.io.BufferedReader;
import java.io.FileReader;

/**
 * 
 * String carPossible : contient le caractère remprésentant une case accessible par les entités.
 * String carImpossible : contient le caractère remprésentant une case inpratiquable par les entités.
 */
public class Carte 
{
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
	
	//getters and setters
	public int getLongueur() {
		return longueur;
	}

	public void setLongueur(int longueur) {
		this.longueur = longueur;
	}

	public int getLargeur() {
		return largeur;
	}

	public void setLargeur(int largeur) {
		this.largeur = largeur;
	}

	public String[][] getTab() {
		return tab;
	}

	public void setTab(String[][] tab) {
		this.tab = tab;
	}

	public Position getSortie() {
		return sortie;
	}

	public void setSortie(Position sortie) {
		this.sortie = sortie;
	}

	public Position getApparition() {
		return apparition;
	}

	public void setApparition(Position apparition) {
		this.apparition = apparition;
	}

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
/**
 * 
 */
package main1;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;

/**
 * @author he201460
 *
 */
public class Carte 
{
	private int longueur;
	private int largeur;
	private String[][] tab;
	private Position sortie;
	private Position apparition;
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
			
			
			line = test.readLine();
			tabCourant = line.split(" ");
			courante = new Position(Integer.parseInt(tabCourant[0]), Integer.parseInt(tabCourant[1]));
			this.apparition = courante;
			
			line = test.readLine();
			tabCourant = line.split(" ");
			courante = new Position(Integer.parseInt(tabCourant[0]), Integer.parseInt(tabCourant[1]));
			this.sortie = courante;
			
			
			for(int i = 0;i<longueur;i++)
			{
				line = test.readLine();
				tabCourant = line.split("");
				for(int j = 3, k = 0;j<largeur;j+=4,k++)
				{
					tab[i][k] = tabCourant[3+(j*4)];
				}
			}
		} 
		catch (Exception e) 
		{
			e.getMessage();
		}
	}
	
	public void generer()
	{
		for(int i = 0;i<this.largeur;i++)
		{
			System.out.println("---");
		}
		for(int i = 0;i<this.longueur;i++)
		{
			for(int j = 0; j<this.largeur;j++)
			{
				System.out.print("| "+tab[i][j]);
			}
			System.out.println(" |");
			for(int j = 0 ; j<=this.largeur;j++)
			{
				System.out.print("---");
			}
		}
		
	}

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
}

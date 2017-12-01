/**
 * 
 */
package main;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.LinkedList;

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
			tab = new String[this.longueur][this.largeur];
			
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
				line = test.readLine();
				tabCourant = line.split("\\|");
				for(int z =  0; z<tabCourant.length; z++)
				{
					if(tabCourant[z] != null)
					{
						tabCourant[z].trim();
					}
				}
				
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
	
	public void generer(LinkedList<Entite> ent)
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
						if(a.equals("class main.Personnage"))
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
					System.out.print("| j ");
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
	}
	public void genererLigne(int x)
	{
		 
      for(int w = 0 ; w < largeur ; w++ ) 
      {
    	  System.out.print("||||");
	  }
      System.out.println("|");

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

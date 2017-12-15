package testMVC;

import java.util.LinkedList;
import java.util.Observable;
import java.util.Observer;
import java.util.Scanner;

public class JeuVueConsole extends JeuVue implements Observer
{

	protected Scanner sc = new Scanner(System.in);
	
	public JeuVueConsole(Jeu modele, JeuController controle)
	{
		super(modele, controle);
		//main();
	}
	
	
	public void presentation()
	{
		System.out.println("Vous voilà dans un sale pétrin " + modele.getPerso().getNom() +" ! Votre mission ? Atteindre la postion (" + modele.getCarte().getSortie().getPosX() + ";" + modele.getCarte().getSortie().getPosY() + ") pour vous échapper de ce massacre."
		    			+ "\nPour cela vous devrez traverser ce bâtiment rempli de zombies..."
				  		+ "\nVoici votre position actuelle (" + modele.getPerso().getEmplacement().getPosX()+";"+modele.getPerso().getEmplacement().getPosY() 
						+ ")\nVous êtes représenté sur la carte par le pion \""+modele.getCarte().getPionJoueur()+"\""
						+ "\nLes zombies sont représentés par un \"" + modele.getCarte().getPionZombie() + "\""
						+ "\nVous pouvez effectuer plusieurs actions : "
						+ "\n	- Fouillez (entrez 1);"
						+ "\n	- Attaquer (entrez 2);"
						+ "\n	- Vous deplacez (entrez 3);"
						+ "\n	- Attendre (entrez 4);"
						+ "\n	- Consultez vos infos (entrez 5. Cela ne consomme pas de point d'action);"
						+ "\n	- Jeter une arme (entrez 6 puis le numéro de l'arme à jeter => 1 : gauche, 2 : droite);");
	}
	
	
	public void main()
	{
		System.out.println("Bonjour survivant ! Quel est votre pseudo ?");
		String nom = sc.next();
		controle.nouveauPerso(nom);
		presentation();
		update(null, null);
		while(!modele.getPerso().getEmplacement().equals(modele.getCarte().getSortie()))
		{
			while(modele.getPerso().getPointsDAction() > 0 && !modele.getPerso().getEmplacement().equals(modele.getCarte().getSortie()))
			{
				actionPossible();
				int action  = 0;
				try
				{
					action = sc.nextInt();
				}
				catch(NumberFormatException e)
				{
					affiche("Vous n'avez pas entré un chiffre réessayer !");
				}
				controle.tourPerso(action,0,"");
			}
			affiche("tour zombie appuyer blabla");
			sc.next();
			if(modele.tourZombie())
			{
				affiche("Vous avez ete mordu");
			}
			update(null, null);
			if(modele.getPerso().getPointsDeVie() <= 0)
			{
				affiche("vous etes mort et perdez la partie");
				break;
			}
			if(modele.getCompteurTour()%2 == 0)
			{
				//System.out.println("Tour pair, apparition de zombie");
				modele.zombieApparition();
			}
			modele.getPerso().setPointsDAction(3);
			modele.setCompteurTour(modele.getCompteurTour() + 1);
		}
		if(modele.getPerso().getPointsDeVie() > 0)
		{
			affiche("Vous avez gagne la partie");
		}
		
	}
	
	public void actionPossible()
	{
		affiche("Que voulez vous faire ?");
		affiche(	"\n- Fouillez (entrez 1);"
				+ "\n- Attaquer (entrez 2);"
				+ "\n- Vous deplacez (entrez 3);"
				+ "\n- Attendre (entrez 4);"
				+ "\n- Consultez vos infos (entrez 5 cela ne consomme pas d'action);"
				+ "\n- Jeter une arme (entrez 6 puis le numero de l'arme a jeter 1 = gauche, 2 = droite);");
	}
	
	@Override
	public void update(Observable arg0, Object arg1) 
	{
		String [][] tab = modele.getCarte().getTab();
		LinkedList <Entite> ent = modele.getEntiteSurCarte();
		for(int i = 0;i<tab.length;i++)
		{
			genererLigne(modele.getCarte().getLargeur());
			for(int j = 0;j<tab[0].length;j++)
			{
				boolean dejaEcrit = false;
				int counter = 0;
				boolean caseJoueur = false;
				
				for(Entite perso : ent)
				{
				
					
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
					System.out.print("| "+ modele.getCarte().getPionJoueur() +" ");
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
		genererLigne(modele.getCarte().getLargeur());
	}

	public void genererLigne(int largeur)
	{
		  for(int w = 0 ; w < largeur ; w++ ) 
	      {
	    	  System.out.print("||||");
		  }
	      System.out.println("|");
	}

	public int choixArme()
	{
		int noArme = 0;
		try
		{
			noArme = Integer.parseInt(sc.next());
			
			if (noArme != 1 && noArme != 2) {
				affiche("Mauvaise entrée arme !");
				return 0;
			}
		}
		catch(NumberFormatException e)
			{
				affiche("Mauvaise entrée arme !");
				return 0;
			}
		return noArme;
	}
	
	public Position choixAttaque()
	{
		String [] courant;
		char zero;
		char un;
		String vise;
		do
		{
			affiche("Ou voulez vous attaquer");
			do 
			{
				vise = sc.next();
				courant = vise.split("");
				
				//a voir si suppression
				zero =  courant[0].charAt(0);
				un = courant[1].charAt(0);	
			}while(!Character.isDigit(zero) || !Character.isDigit(un) || courant.length > 2 );
		}while( !(Integer.parseInt(zero+"") < modele.getCarte().getLargeur()) || !(Integer.parseInt(un+"") < modele.getCarte().getLongueur()) || Integer.parseInt(zero+"") < 0 && Integer.parseInt(un+"") < 0 );
		return new Position(Integer.parseInt(courant[0]),Integer.parseInt(courant[1]));
	}

	public String deplacement()
	{
		String deplacement;
		do
		{
			affiche("Vers où voulez-vous aller ?");
			deplacement = sc.next();
		}while((!deplacement.equals("bas")) && (!deplacement.equals("haut")) && (!deplacement.equals("gauche")) && (!deplacement.equals("droite")));
		return deplacement;
	}

	@Override
	public void affiche(String string) 
	{
		System.out.println(string);
	}


	@Override
	public void afficheArme1(String string) {
		affiche(string);
		
	}


	@Override
	public void afficheArme2(String string) {
		affiche(string);
		
	}
	
}

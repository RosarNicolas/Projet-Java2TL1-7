/**
 * Groupe 7 JAVA PROJECT
 * MAJ 17/11/2017
 * classe fonctionelle mais pas parfaite 
 * verifier les entrees des joueurs (exception)
 * verifier les comportement des zombies 
 * verifier les attaque des joueurs
 */
package main;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Scanner;

/**
 * @author groupe 7
 *verifier input choix d'arme
 */
public class Jeu {
	
	static Carte carte;
	static Personnage perso;
	static HashMap<Integer, Zombie> zombies = new HashMap<>();
	static HashMap<Integer, Arme> armes = new HashMap<>();
	static LinkedList<Entite> entiteSurCarte = new LinkedList<Entite>();
	static LinkedList<Zombie> zombiesSurCarte = new LinkedList<Zombie>();
	static LinkedList<Zombie> zombiesSurCase = new LinkedList<Zombie>();
	static Scanner sc = new Scanner(System.in);
	static int compteurTour = 1;
	/**
	 * @param args
	 * methode main dans laquelle se deroule la presentation et la boucle qui fera tourner le jeu jusqu'a la fin
	 * si les points de vie du joueurs descendent en dessous de 1 alors la game est finie 
	 * apparition de zombie tous les tours pair
	 * le joueur gagne si a la fin de son tour il se trouve l'emplacement de la sortie
	 */
	public static void main(String[] args)
	{
		
		init();
		System.out.println("Bonjour survivant ! Quel est votre pseudo ?");
		perso = new Personnage(sc.next(), 1, 2, 3, carte.getApparition());
		
		System.out.println("Vous voilà dans un sale pétrin " + perso.getNom() +" ! Votre mission ? Atteindre la postion (" + carte.getSortie().getPosX() + ";" + carte.getSortie().getPosY() + ") pour vous échapper de ce massacre."
				+ "\nPour cela vous devrez traverser ce bâtiment rempli de zombies..."
				+ "\nVoici votre position actuelle (" + perso.getEmplacement().getPosX()+";"+perso.getEmplacement().getPosY() 
				+ ")\nVous êtes représenté sur la carte par le pion \""+Carte.getPionJoueur()+"\""
				+ "\nLes zombies sont représentés par un \"" + Carte.getPionZombie() + "\""
				+ "\nVous pouvez effectuer plusieurs actions : "
				+ "\n	- Fouillez (entrez 1);"
				+ "\n	- Attaquer (entrez 2);"
				+ "\n	- Vous deplacez (entrez 3);"
				+ "\n	- Attendre (entrez 4);"
				+ "\n	- Consultez vos infos (entrez 5. Cela ne consomme pas de point d'action);"
				+ "\n	- Jeter une arme (entrez 6 puis le numéro de l'arme à jeter => 1 : gauche, 2 : droite);");
		updateEntiteListe();
		carte.generer(entiteSurCarte);
		while(!perso.getEmplacement().equals(carte.getSortie()))
		{
			System.out.println("Tour numéro : " +compteurTour);
			System.out.println();
			System.out.println();
			tourPerso();
			tourZombie();
			if(perso.getPointsDeVie() <= 0)
			{
				System.out.println("Vous êtes mort...et perdez la partie");
				break;
			}
			if(compteurTour%2 == 0)
			{
				System.out.println("Tour pair, apparition de zombie");
				zombieApparition();
    			updateEntiteListe();
				carte.generer(entiteSurCarte);
			}
			perso.setPointsDAction(3);
			compteurTour++;
		}
		System.out.println();
		System.out.println("Vous vous êtes échappé bravo !! Le jeu est terminé ");
	}
	
	/**
	 * methode qui initialise les LinkedList avec tous les types de zombies et d'armes differents
	 * fais apparaitre un zombie (a detreminer en fonction de la difficulté futur MAJ)
	 */
	public static void init()
	{
		zombies.put(1,new Zombie("Walker", 1, 1, 1, new Position(0,0)));

		
		armes.put(1,new Arme(1, 1, 1, 1, 0.66, 0.125,0.05, "Arc"));
		armes.put(2,new Arme(2, 1, 0, 3, 0.5, 0.125,0.05, "Epée"));
		armes.put(3,new Arme(3, 1, 2, 2, 0.5, 0.125,0.05, "Ak47"));
		armes.put(4,new Arme(4, 2, 0, 1, 0.5, 0.125,0.05, "Hache"));
		armes.put(5,new Arme(5, 2, 0, 5, 0.33, 0.125,0.05, "Tronçonneuse"));
		armes.put(6,new Arme(6, 1, 1, 1, 0.66, 0.125,0.05, "Pistolet"));
		armes.put(7,new Arme(7, 1, 0, 2, 0.5, 0.125,0.05, "Batte"));
		armes.put(8,new Arme(8, 2, 1, 2, 0.5, 0.125,0.05, "Fusil à pompe"));
		armes.put(9,new Arme(9, 0, 0, 0, 0.0, 0.125,0.05, "Détritus"));
		armes.put(10,new Arme(10, 0, 0, 0, 0.0, 0.125,0.05, "Détritus"));
		armes.put(11,new Arme(11, 0, 0, 0, 0.0, 0.125,0.05, "Détritus"));
		armes.put(12,new Arme(12, 0, 0, 0, 0.0, 0.125,0.05, "Détritus"));
		
		carte = new Carte("res/carte4.txt");
		
		for(int i = 0 ; i < 1;i++)
		{
			zombieApparition();
		}
	}
	
	
	/** 
	 * action possible lors d'un tour du personnage 
	 * recuperation via un scanner
	 * 1 permet de fouiller
	 * 2 de se battre en choisissant une arme et un endroit ou tirer si le joueur possede une arme a distance
	 * 3 permet de se deplacer en choisissant une direction
	 * 4 permet d'attendre
	 * 5 donne les informations sur le joueur
	 * 6 permet de jeter une arme
	 */
	public static void tourPerso()
	{
		
		System.out.println();
		System.out.println();
		
		 
		while( perso.getPointsDAction() > 0 && !perso.getEmplacement().equals(carte.getSortie()))
		{
			System.out.println("Que voulez-vous faire ?");
			explication();
			int action = 0;
			try
			{
				action = Integer.parseInt(sc.next());
			}
			catch(NumberFormatException e)
			{
				System.out.println("Vous n'avez pas entré un chiffre réessayer !");
			}
			
			if(action == 1)
			{
				perso.fouille(armes);
				perso.setPointsDAction(perso.getPointsDAction() - 1);
				updateEntiteListe();
				carte.generer(entiteSurCarte);
			}
			else if(action == 2)
			{
				int noArme = 0;
				System.out.println("Avec quelle arme voulez-vous attaquer ?(1 ou 2)");
						
						try
						{
							noArme = Integer.parseInt(sc.next());
							
							if (noArme != 1 && noArme != 2) {
								System.out.println("Mauvaise entrée arme !");
								continue;
							}
						}
						catch(NumberFormatException e)
							{
								System.out.println("Mauvaise entrée arme !");
								continue;
							}
						if(noArme == 1 && perso.getArmeGauche() == null)
						{
							System.out.println("Vous n'avez pas d'arme en main gauche (1)");
							continue;
						}
						else if(noArme == 2 && perso.getArmeDroite() == null)
						{
							System.out.println("Vous n'avez pas d'arme en main droite (2)");
							continue;
						}
				
				String [] courant;
				char zero;
				char un;
				String vise;
				do
				{
					System.out.println("Où voulez-vous attaquer ?");
					do 
					{
						vise = sc.next();
						courant = vise.split("");
						
						//a voir si suppression
						zero =  courant[0].charAt(0);
						un = courant[1].charAt(0);	
					}while(!Character.isDigit(zero) || !Character.isDigit(un) || courant.length > 2 );
				}while( !(Integer.parseInt(zero+"") < carte.getLargeur()) || !(Integer.parseInt(un+"") < carte.getLongueur()) || Integer.parseInt(zero+"") < 0 && Integer.parseInt(un+"") < 0 );
				
				Position endroitDeLattaque = new Position(Integer.parseInt(courant[0]),Integer.parseInt(courant[1]));
				updateZombieSurCase(endroitDeLattaque);
				if(zombiesSurCase.isEmpty())
				{
					System.out.println("Il n'y pas de zombie à cet endroit");
					continue;
				}
				//trouver un moyen pour eviter la redondance du code !!!!!!!!
				if(noArme == 1 )
				{
					if(perso.getEmplacement().verifierDistance(endroitDeLattaque,perso.getArmeGauche().getPortee()))
					{
						zombiesSurCarte.removeAll(zombiesSurCase);
						Zombie cibleAttaque = zombiesSurCase.pop();
						if(!(perso.attaquer(noArme)>=cibleAttaque.getPointsDeVie()))
						{
							zombiesSurCase.addFirst(cibleAttaque);
							System.out.println("Vous n'avez pas réussi a tuer le zombie cible !");
						}
						else
						{
							System.out.println("Vous avez tué un zombie !");
							updateEntiteListe();
							carte.generer(entiteSurCarte);
						}
						zombiesSurCarte.addAll(zombiesSurCase);
					}
					else
					{
						System.out.println("Vous n'avez pas la portée ou la ligne de vue pour tirer a cet endroit ");
					}
				}
				else 
				{
					if(perso.getEmplacement().verifierDistance(endroitDeLattaque,perso.getArmeDroite().getPortee()))
					{
						zombiesSurCarte.removeAll(zombiesSurCase);
						Zombie cibleAttaque = zombiesSurCase.pop();
						if(!(perso.attaquer(noArme)>=cibleAttaque.getPointsDeVie()))
						{
							zombiesSurCase.addFirst(cibleAttaque);
							System.out.println("Vous n'avez pas réussi a tuer le zombie cible !");
						}
						else
						{
							System.out.println("Vous avez tué un zombie ! ");
							updateEntiteListe();
							carte.generer(entiteSurCarte);
						}
						zombiesSurCarte.addAll(zombiesSurCase);
					}
					else
					{
						System.out.println("Vous n'avez pas la portée ou la ligne de vue pour tirer là !");
					}
				}
				perso.setPointsDAction(perso.getPointsDAction() - 1);
				updateEntiteListe();
				carte.generer(entiteSurCarte);
			}
			else if(action == 3)
			{
				String deplacement;
				do
				{
					System.out.println("Vers où voulez-vous aller ?");
					deplacement = sc.next();
				}while((!deplacement.equals("bas")) && (!deplacement.equals("haut")) && (!deplacement.equals("gauche")) && (!deplacement.equals("droite")));
					
				perso.deplacer(deplacement, carte);
				System.out.println("Voici votre position actuelle  "+ perso.getEmplacement().getPosX()+";"+perso.getEmplacement().getPosY());
				updateEntiteListe();
				carte.generer(entiteSurCarte);
				perso.setPointsDAction(perso.getPointsDAction() - 1);
			}
			else if(action == 4)
			{
				perso.setPointsDAction(perso.getPointsDAction() - 1);
				updateEntiteListe();
				carte.generer(entiteSurCarte);
			}
			else if(action == 5)
			{
				System.out.println("Vous vous trouvez en " + perso.getEmplacement().getPosX() + "," + perso.getEmplacement().getPosY()
								  +"\nLa sortie se trouve en " +carte.getSortie().getPosX() + "," +carte.getSortie().getPosY()
								  +"\nVous avez encore " + perso.getPointsDeVie() + " points de vie"
								  +"\nVous pouvez encore effectuer " + perso.getPointsDAction() + " actions ce tour-ci"
								  );
				
				if(perso.getArmeDroite() == null && perso.getArmeGauche() == null)
				{
					System.out.println("Vous n'avez pas d'arme");
				}
				else if(perso.getArmeDroite() == null)
				{
					System.out.println("Vous n'avez pas d'arme en main droite (2)"
									  +"\nVotre arme en main gauche (1) est un "+ perso.getArmeGauche().getNomDeLarme());
					
				}
				else if(perso.getArmeGauche() == null)
				{
					System.out.println("Vous n'avez pas d'arme en main gauche (1)"
							  +"\nVotre arme en main droite (2) est un "+ perso.getArmeDroite().getNomDeLarme());
			
				}
				else
				{
					System.out.println("Votre arme en main gauche (1) est un "+perso.getArmeGauche().getNomDeLarme()
							  +"\nVotre arme en main droite (2) est un "+ perso.getArmeDroite().getNomDeLarme());
				}
				updateEntiteListe();
				carte.generer(entiteSurCarte);
				
			}
			else if(action == 6)
			{
				int armeAjeter = 0;
				System.out.println("Quelle arme voulez-vous jeter ?");
				try
				{
					armeAjeter = Integer.parseInt(sc.next());
				}
				catch(NumberFormatException e)
				{
					System.out.println("Veuillez entrer un chiffre (1 ou 2)");
				}
				perso.jeterUneArme(armeAjeter);
				updateEntiteListe();
				carte.generer(entiteSurCarte);
			}
			else
			{
				System.out.println("Mauvaise entrée !");
			}
		}
	}
	
	/**
	 * action des zombies lors de leur tour
	 * les zombie jouent tous en meme temps 
	 * soit ils attaquent soit ils se deplacent
	 */
	public static void tourZombie()
	{
		System.out.println("C'est au tour des zombies (entrez une touche quelconque)");
		String test = sc.next();
		for(Zombie z : zombiesSurCarte)
		{
			if(z.getEmplacement().equals(perso.getEmplacement()))
			{
				int degat = z.attaquer();
				perso.setPointsDeVie(perso.getPointsDeVie() - degat);
				//degat toujours egal a 1
				System.out.println("Un zombie vous a mordu, vous êtes blessé !");
			}
			else
			{
				z.deplacer(perso, carte);
				updateEntiteListe();
				carte.generer(entiteSurCarte);
			}
		
			
		}
		
		
	}
	
	/**
	 * fais apparaitre un zombie sur la carte de maniere aléatoire
	 * jamais la ou le joueur se trouve
	 */
	public static void zombieApparition()
	{
		String [][] courant = carte.getTab();
		int posX = 0;
		int posY = 0;
		String debug2 = "";
		String signeCasePratiquable = courant[carte.getApparition().getPosX()][carte.getApparition().getPosY()];
		do {
			posX = (int) Math.floor((Math.random() * carte.getLargeur() ));
			posY = (int) Math.floor((Math.random() * carte.getLongueur()));
			debug2  = courant[posY][posX];
		   }while(!debug2.equals(signeCasePratiquable) || (posX == 0 && posY == 0));
		//1 seul de type de zombie
		//int a = (int) Math.ceil(Math.random() * 4);
		
		//a optimiser (liste d'un seul element
		zombiesSurCarte.add(new Zombie(zombies.get(1).getNom(),zombies.get(1).getId(),zombies.get(1).getPointsDeVie(),zombies.get(1).getPointsDAction(),new Position(posX,posY)));
		updateEntiteListe();
		if(compteurTour != 1)
		{
			carte.generer(entiteSurCarte);
		}
	}
	
	/**
	 * met a jour la linkedList en fonction des entites sur la map
	 */
	public static void updateEntiteListe()
	{
		entiteSurCarte.removeAll(entiteSurCarte);
		if(perso != null)
		{
			Entite ent = new Personnage(perso.getNom(),perso.getId(), perso.getPointsDeVie(), perso.getPointsDAction(),  perso.getEmplacement() );
			entiteSurCarte.add(ent);
		}
		entiteSurCarte.addAll(zombiesSurCarte);
		
	}
	
	/**
	 *  permet de recuperer les zombie sur une case precise
	 * @param x Position sur laquelle on veut recuperer les zombies
	 */
	public static void updateZombieSurCase(Position x)
	{
		zombiesSurCase.removeAll(zombiesSurCase);
		for(Zombie z : zombiesSurCarte)
		{
			if(z.getEmplacement().equals(x))
			{
				zombiesSurCase.add(z);
			}
		}
	}
	
	/**
	 * permet d'afficher les commande possible
	 */
	public static void explication()
	{
		System.out.println(	"\n- Fouillez (entrez 1);"
				+ "\n- Attaquer (entrez 2);"
				+ "\n- Vous deplacez (entrez 3);"
				+ "\n- Attendre (entrez 4);"
				+ "\n- Consultez vos infos (entrez 5 cela ne consomme pas d'action);"
				+ "\n- Jeter une arme (entrez 6 puis le numero de l'arme a jeter 1 = gauche, 2 = droite);");
	}

}
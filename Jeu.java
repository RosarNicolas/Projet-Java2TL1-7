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
import java.util.ListIterator;
import java.util.Map;
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
	 * methode main dans laquelle se deroule la presentation et la boucle qui fera tournee le jeu jusqu'a la fin
	 */
	public static void main(String[] args)
	{
		
		init();
		System.out.println("Quel est votre nom ?");
		perso = new Personnage(sc.next(), 1, 2, 3, carte.getApparition());
		
		System.out.println("Vous voila dans un sale petrin vous devez vous sortir d'ici la sortie se trouve ici (" + carte.getSortie().getPosX() + ";" + carte.getSortie().getPosY() 
				+ ")\nPour cela vous devrez traversez ce batiment rempli de Zombies..."
				+ "\nVoici votre position actuelle (" + perso.getEmplacement().getPosX()+";"+perso.getEmplacement().getPosY() 
				+ ")\nVous pouvez effectuer plusieurs action : "
				+ "\nFouillez (entrez 1)"
				+ "\nAttaquer (entrez 2)"
				+ "\nVous deplacez (entrez 3)"
				+ "\nAttendre (entrez 4)"
				+ "\nConsultez vos infos (entrez 5 cela ne consomme pas d'action)"
				+ "\nJeter une arme (entrez 6 puis le numero de l'arme a jeter 1 = gauche, 2 = droite)");
		updateEntiteListe();
		carte.generer(entiteSurCarte);
		while(!perso.getEmplacement().equals(carte.getSortie()))
		{
			System.out.println("Tour numero : " +compteurTour);
			System.out.println();
			System.out.println();
			tourPerso();
			tourZombie();
			if(perso.getPointsDeVie() <= 0)
			{
				System.out.println("Vous etes mort...et perdez la partie");
				break;
			}
			if(compteurTour%2 == 0 && compteurTour > 0)
			{
				System.out.println("Tour pair apparition de zombie");
				zombieApparition();
//				updateEntiteListe();
//				carte.generer(entiteSurCarte);
			}
			perso.setPointsDAction(3);
			compteurTour++;
		}
	}
	
	/**
	 * methode qui initialise les HashMap avec tous les types de zombies et d'armes differents
	 */
	public static void init()
	{
		zombies.put(1,new Zombie("Walker", 1, 1, 1, new Position(0,0)));
		zombies.put(2,new Zombie("Runner", 2, 1, 2, new Position(0,0)));
		zombies.put(3,new Zombie("Fatty", 3, 2, 1, new Position(0,0)));
		zombies.put(4,new Zombie("Crawler", 4, 1, 1, new Position(0,0)));
		
		armes.put(1,new Arme(1, 1, 1, 1, 0.66, 0.125,0.05, "Arc"));
		armes.put(2,new Arme(2, 1, 0, 3, 0.5, 0.125,0.05, "Epee"));
		armes.put(3,new Arme(3, 1, 2, 2, 0.5, 0.125,0.05, "Ak47"));
		armes.put(4,new Arme(4, 2, 0, 1, 0.5, 0.125,0.05, "Hache"));
		armes.put(5,new Arme(5, 2, 0, 5, 0.33, 0.125,0.05, "Tronconneuse"));
		armes.put(6,new Arme(6, 1, 1, 1, 0.66, 0.125,0.05, "Pistolet"));
		armes.put(7,new Arme(7, 1, 0, 2, 0.5, 0.125,0.05, "Batte"));
		armes.put(8,new Arme(8, 2, 1, 2, 0.5, 0.125,0.05, "Fusil a pompe"));
		armes.put(9,new Arme(9, 0, 0, 0, 0.0, 0.125,0.05, "Detritus"));
		armes.put(10,new Arme(10, 0, 0, 0, 0.0, 0.125,0.05, "Detritus"));
		armes.put(11,new Arme(11, 0, 0, 0, 0.0, 0.125,0.05, "Detritus"));
		armes.put(12,new Arme(12, 0, 0, 0, 0.0, 0.125,0.05, "Detritus"));
		
		carte = new Carte("C:\\Users\\Nicolas\\Downloads\\carte4.txt");
		
		for(int i = 0 ; i < 1;i++)
		{
			zombieApparition();
		}
	}
	
	
	/** 
	 * action possible lors d'un tour du personnage 
	 */
	public static void tourPerso()
	{
		
		System.out.println();
		System.out.println();
		
		 
		while( perso.getPointsDAction() > 0 && !perso.getEmplacement().equals(carte.getSortie()))
		{
			System.out.println("Que voulez vous faire ?");
			explication();
			int action = 0;
			try
			{
				action = Integer.parseInt(sc.next());
			}
			catch(NumberFormatException e)
			{
				System.out.println("Vous n'avez pas entrer un bon chiffre reessayer !");
			}
			
			if(action == 1)
			{
				if(perso.getArmeDroite() != null && perso.getArmeGauche() != null) {
					perso.setPointsDAction(perso.getPointsDAction() + 1);
				}
				perso.fouille(armes);
				perso.setPointsDAction(perso.getPointsDAction() - 1);
				updateEntiteListe();
				carte.generer(entiteSurCarte);
			}
			else if(action == 2)
			{
				int noArme = 0;
				System.out.println("Avec quelle arme voulez vous attaquer ?(1 ou 2)");
						
						try
						{
							noArme = Integer.parseInt(sc.next());
							
							if (noArme != 1 || noArme != 2) {
								System.out.println("Mauvaise entree arme !");
								continue;
							}
						}
						catch(NumberFormatException e)
							{
								System.out.println("Mauvaise entree arme !");
								continue;
							}
				
				String [] courant;
				char zero;
				char un;
				String vise;
				do
				{
					System.out.println("Ou voulez vous attaquer ?");
					do 
					{
						vise = sc.next();
						courant = vise.split("");
						
						//a voir si suppression
						zero =  courant[0].charAt(0);
						un = courant[1].charAt(0);	
					}while(!Character.isDigit(zero) || !Character.isDigit(un) || courant.length > 2 );
				}while( !(Integer.parseInt(zero+"") < carte.getLargeur()) || !(Integer.parseInt(un+"") < carte.getLongueur()) || Integer.parseInt(zero+"") < 0 && Integer.parseInt(un+"") < 0 );
				System.out.println(" deuxieme reussi");
				
				Position endroitDeLattaque = new Position(Integer.parseInt(courant[0]),Integer.parseInt(courant[1]));
				updateZombieSurCase(endroitDeLattaque);
				
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
							System.out.println("Vous n'avez pas reussi a tuer le zombie cible !");
						}
						else
						{
							System.out.println("Vous avez tuer un zombie !");
							updateEntiteListe();
							carte.generer(entiteSurCarte);
						}
						zombiesSurCarte.addAll(zombiesSurCase);
					}
					else
					{
						System.out.println("Vous n'avez pas la portee ou la ligne de vue pour tirer a cete endroit ");
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
							System.out.println("Vous n'avez pas reussi a tuer le zombie cible !");
						}
						else
						{
							System.out.println("Vous avez tuer un zombie ! ");
							updateEntiteListe();
							carte.generer(entiteSurCarte);
						}
						zombiesSurCarte.addAll(zombiesSurCase);
					}
					else
					{
						System.out.println("Vous n'avez pas la portee ou la ligne de vue pour tirer là !");
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
					System.out.println("Vers ou voulez vous aller ?");
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
								  +"\nLa sortie se trouve en " +carte.getSortie().getPosX() + "," +carte.getSortie().getPosY());
				
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
				updateEntiteListe();
				carte.generer(entiteSurCarte);
				
			}
			else if(action == 6)
			{
				System.out.println("Quelle arme voulez vous jeter ?");
				int armeAjeter = Integer.parseInt(sc.next());
				perso.jeterUneArme(armeAjeter);
				updateEntiteListe();
				carte.generer(entiteSurCarte);
			}
			else
			{
				System.out.println("Mauvais entree !");
			}
		}
	}
	
	/**
	 * action des zombies lors de leur tour
	 * les zombie jouent tous en meme temps 
	 */
	public static void tourZombie()
	{
		System.out.println("C'est au tour des zombies (appuyer sur une touche)");
		String test = sc.next();
		for(Zombie z : zombiesSurCarte)
		{
			if(z.getEmplacement().equals(perso.getEmplacement()))
			{
				int degat = z.attaquer();
				perso.setPointsDeVie(perso.getPointsDeVie() - degat);
				//degat toujours egal a 1
				System.out.println("Un zombie vous a mordu vous etes blessé !");
			}
			else
			{
				z.deplacer(perso, carte);
				updateEntiteListe();
				carte.generer(entiteSurCarte);
			}
		
			
		}
		
		
	}
	
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
		int a = (int) Math.ceil(Math.random() * 4);
		zombiesSurCarte.add(new Zombie(zombies.get(a).getNom(),zombies.get(a).getId(),zombies.get(a).getPointsDeVie(),zombies.get(a).getPointsDAction(),new Position(posX,posY)));
		updateEntiteListe();
		if(compteurTour != 1)
		{
			carte.generer(entiteSurCarte);
		}
	}
	
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
	
	public static void explication()
	{
		System.out.println(	"\nFouillez (entrez 1)"
				+ "\nAttaquer (entrez 2)"
				+ "\nVous deplacez (entrez 3)"
				+ "\nAttendre (entrez 4)"
				+ "\nConsultez vos infos (entrez 5 cela ne consomme pas d'action)"
				+ "\nJeter une arme (entrez 6 puis le numero de l'arme a jeter 1 = gauche, 2 = droite)");
	}

}

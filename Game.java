package projet;

import java.util.Scanner;

public class Game 
{
	public static Characters[] players = new Characters[256];
	public static Weapons [] armes = new Weapons[256];
	public static  Zombies [] zombie = new Zombies[10];
	
	private static int countSortie;
	public static int counterDeZombie;
	private static Scanner sc = new Scanner(System.in);
	
	public Game()
	{
		
	}
	
	public static void test()
	{
		System.out.println("Bienvenue dans zombicide votre objectif atteindre la sortie en bottant le cula  des zombie, pas compliqué\n");
		
		System.out.println("Quel est le nom de votre personnage ?");
		Scanner sc = new Scanner(System.in);
		players[0] = new Characters(sc.next(),1,10,3,5,0,0);
		
		countSortie = 5;
		while(countSortie>0)
		{
			tourPlayer();
			//tourZombie();
		}
		sc.close();
	}
	
	
	public static void tourPlayer()
	{
		
		//int count = 0;
		int action = 0;
		
		
		System.out.println("Que voulez vous faire ? (1 se deplacer 2 fouiller 3 attaquer)");
		action = sc.nextInt();
		if(action == 1)
		{
			
			if(counterDeZombie == 0)
			{
			//players[0].deplacement();
			countSortie--;
			for(int i = 0;i<zombie.length;i++)
				{
					if(zombie[i] == null)
					{
						zombie[i] = players[0].rentre();
						i = 100;
					}
				}
			}
			else if(counterDeZombie != 0)
			{
				System.out.println("Vous ne pouvez pas sortir il y a un zombie qui vous barre la route !");
				
			}
			
		}
		else if(action == 2)
		{
			if(players[0].getGauche() != null && players[0].getDroite() != null)
			{
				System.out.println("Vous avez deja 2 armes");
			}
			else if(players[0].getGauche() == null)
			{
				players[0].setGauche(Characters.fouille());
			}
			else if(players[0].getDroite() == null)
			{
				players[0].setDroite(Characters.fouille());
			}
			else
			{
				System.out.println("Vous avez les mains pleines d'armes, a la baston !");
			}
		}
		else if(action == 3) //peut etre utiliser une liste pour pop et add les zombies plus facilement;
		{
			if(counterDeZombie == 0)
			{
				System.out.println("il n'y a pas de zombie a combattre camarades !");
			}
			else
			{
				int noDeZombie = isZombie();
				int vieZ = zombie[noDeZombie].getPointsDeVie();
				while(vieZ>0)
				{
					System.out.println("Quelle arme voulez vous utiliser ? (1 main gauche 2 main droite)");
					
				
					zombie[noDeZombie].setPointsDeVie(vieZ - players[0].attaque(sc.nextInt()));
					vieZ = zombie[noDeZombie].getPointsDeVie();
				}
				System.out.println("Bravo vous l'avez bien défoncé !!! Il y des giclures partout sur votre vest maintenant...");
				zombie[noDeZombie] = null;
				counterDeZombie--;
			}
			
		}
		
		
	}
	
	public static int isZombie() //useless atm
	{
		for(int i = 0 ;i<zombie.length;i++)
		{
			if(zombie[i] != null)
			{
				return i;
			}
		}
		return 0; // a changer car tab[0] peut contenir des zombies
	}
	
}

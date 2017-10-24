package main;

import java.util.Scanner;

public class Game 
{
	public static Characters[] players = new Characters[256];
	public static Weapons [] armes = new Weapons[256];
	public static  Zombies [] zombie = new Zombies[256];
	
	public Game()
	{
		
	}
	
	public static void test()
	{
		players[0] = new Characters("jordan",1,10,3,5,0,0);
		
		int count = 0;
		Scanner sc = new Scanner(System.in);
		int jouer = 1; //1 = fouiller , 2 = avancer et 3 = attaquer
		while(jouer == 1 && count < 2 )
		{
			System.out.println("Que voulez vous faire ?");
			jouer =sc.nextInt();
			if(jouer == 1 )
			{
				armes[count] = drop();
				
				if(players[0].getGauche() == null)
				{
					players[0].setGauche(armes[count]);
				}
				else if(players[0].getDroite() == null)
				{
					players[0].setDroite(armes[count]);
				}
				
			}
			count++;
		}
		System.out.println("Un Zombie sauvage apparait");
		
		zombie[0] = new Walker();
		
		while(zombie[0].getPointsDeVie()>0)
		{
			System.out.println("Voulez vous attaquer ?(entrer 2)");
			jouer = sc.nextInt();
			while(jouer == 2)
			{
				System.out.println("Quelle arme utilisé " + players[0].getGauche().getNomDeLarme() + " ou " + players[0].getDroite().getNomDeLarme());
				int a = sc.nextInt();
				if (a == 1)
				{
					zombie[0].setPointsDeVie(zombie[0].getPointsDeVie()-players[0].getGauche().getDegats());
					System.out.println("il reste "+ zombie[0].getPointsDeVie() +" points de vie au zombie (stopper taper 3)");
				}
				else if(a == 2)
				{
					zombie[0].setPointsDeVie(zombie[0].getPointsDeVie()-players[0].getDroite().getDegats());
					System.out.println("il reste "+ zombie[0].getPointsDeVie() +" points de vie au zombie (stopper taper 3)");
				}
				jouer = sc.nextInt();
				
			}
		}
		
		
	}
	
	public static Weapons drop()
	{
		
		double a = Math.random()*1000;
		int b = (int)a%11;
		System.out.println("Vous avez trouvé un "+Weapons.armes[b].getNomDeLarme() +" !"
				  +"\nSes dégats sont de : " + Weapons.armes[b].getDegats()
				  +"\nSa portée est de :  "+ Weapons.armes[b].getRange()
				  +"\nVous pouvez attaquer "+Weapons.armes[b].getNombreDeFrappe()+" fois avec par action"
				  +"\nVotre précision est de "+ ((int)(Weapons.armes[b].getChanceDeToucher()*100))+"%");
		return Weapons.armes[b];
	}
}

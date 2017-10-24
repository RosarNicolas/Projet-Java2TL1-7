package main;

import java.util.Scanner;

public class Game 
{
	//public Player[] players;
	public static Weapons [] armes = new Weapons[256];
	//public  Zombie [] zombie;
	
	public Game()
	{
		
	}
	
	public static void test()
	{
		int count = 0;
		Scanner sc = new Scanner(System.in);
		int jouer = 1; //1 = fouiller , 2 = avancer et 3 = attaquer
		while(jouer == 1  )
		{
			System.out.println("Que voulez vous faire ?");
			jouer =sc.nextInt();
			if(jouer == 1 && count < 2)
			{
				armes[count] = drop();
				count++;
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

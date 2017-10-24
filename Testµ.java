package main;

public class Testµ
{
	private static  Weapons arme1;
	public static void  main(String args[])
	{
		Game.test();
		//test();
		
	}
	public static void test()
	{
		arme1 = new Hache();
		System.out.println("Vous avez trouvé un "+arme1.getNomDeLarme() +" !"
						  +"\nSes dégats sont de : " + arme1.getDegats()
						  +"\nSa portée est de :  "+ arme1.getRange()
						  +"\nVous pouvez attaquer "+arme1.getNombreDeFrappe()+" fois avec par action"
						  +"\nVotre précision est de "+ ((int)(arme1.getChanceDeToucher()*100))+"%");
						  
						  

	}
}

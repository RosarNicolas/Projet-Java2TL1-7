package main;

public class Test�
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
		System.out.println("Vous avez trouv� un "+arme1.getNomDeLarme() +" !"
						  +"\nSes d�gats sont de : " + arme1.getDegats()
						  +"\nSa port�e est de :  "+ arme1.getRange()
						  +"\nVous pouvez attaquer "+arme1.getNombreDeFrappe()+" fois avec par action"
						  +"\nVotre pr�cision est de "+ ((int)(arme1.getChanceDeToucher()*100))+"%");
						  
						  

	}
}

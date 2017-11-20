package projet;
/**
 * Crée par Loïc Persyn 2TL1
 * Date de création : 15-11-17
 */
import tp1.Calculatrice;

public class Personnage extends Entite{

	private int armeGauche = 0;
	private int armeDroite = 0;
	public String deplacement;
	
	
	public void seDeplacer(String deplacement) {
		
		if(deplacement.toLowerCase().equals("haut"))
		{
			int monter = Entite.getPosY();
			Entite.posY = monter++;
		}
		else if(deplacement.toLowerCase().equals("bas"))
		{
			int descendre = Entite.getPosY();
			Entite.posY = descendre--;
		}
		else if(deplacement.toLowerCase().equals("gauche"))
		{
			int allerGauche = Entite.getPosX();
			Entite.posX = allerGauche--;
		}
		else if(deplacement.toLowerCase().equals("droite"))
		{
			int allerDroite = Entite.getPosX();
			Entite.posX = allerDroite++;
		}
	}
	
	public void fouille() {
		
		int iDArme = 0;
		iDArme = (int) (Math.random() * (12-1));
		
		if(armeGauche == 0) 
		{
			armeGauche = iDArme;
			System.out.printf("Vous possédez maintenant l'arme %s dans la main gauche", Weapons.getNomDeLarme());
		}
		else if(armeDroite == 0)
		{
			armeDroite = iDArme;
			System.out.printf("Vous possédez maintenant l'arme %s dans la main doite",Weapons.getNomDeLarme());

		}
		else
		{
			System.out.println("Vous n'avez plus de place");
		}
				
	}
				
		
	public Zombies rentre() {
		return null;
		
		//Comprend pas cette méthode
	}
	
		
}

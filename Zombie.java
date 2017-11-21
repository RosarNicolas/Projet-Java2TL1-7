package main1;

/**
 * @author he201460
 * modified Rosar Nicolas & Persyn Loïc
 */
public class Zombie extends Entite
{
	public Zombie(String nom, int id, int pointsDeVie, int pointsDAction, Position emplacement)
	{
		super(nom,id,pointsDeVie, pointsDAction,emplacement);
	}

	@Override
	public void deplacer(String direction)
	{
		//à quoi sert-elle ??
		//voir méthode déplacer suivante
	}
	
	public void deplacer(Personnage perso, Carte carte)
	{
		int couranteX = this.getEmplacement().getPosX();
		int couranteY = this.getEmplacement().getPosY();
		int persPosX = perso.getEmplacement().getPosX();
		int persPosY = perso.getEmplacement().getPosY();
		
		if(couranteX > persPosX)
		{
			couranteX--;
			this.verification(carte, couranteX);
		}
		else if(couranteX < persPosX)
		{
			couranteX++;
			this.verification(carte, couranteX);
		}
		else if(couranteY > persPosY)
		{
			couranteY--;
			this.verification(carte, couranteY);
		}		
		else if(couranteY < persPosY)
		{
			couranteY++;
			this.verification(carte, couranteY);
		}
		
	}
	/**
	 * 
	 * @param carte
	 * @param z : peut prendre la position X ou la position Y
	 */
	//cette méthode devra p-e se retrouver dans carte (ou ailleurs)
	public void verification(Carte carte, int z) 
	{
		int couranteX = this.getEmplacement().getPosX();
		int couranteY = this.getEmplacement().getPosY();
		String[][] map = carte.getTab();
		
		
		if (map[couranteX][couranteY] == "/") 
		{
	        int rand = (int) (Math.random() * 4 );
	        switch(rand)
	        {
	        	case 1 : couranteX++;
	        			 if (map[couranteX][couranteY] == "/") 
	        			 {
	        				 System.out.println("Le zombie ne peut pas se déplacer. A votre tour !");
	        			 }
	        			 break;
	        	case 2 : couranteX--;
	        			 if (map[couranteX][couranteY] == "/") 
	        			 {
	        				 System.out.println("Le zombie ne peut pas se déplacer. A votre tour !");
	        			 }
	        			 break;
	        	case 3 : couranteY++;
	        			 if (map[couranteX][couranteY] == "/") 
	        			 {
	        				 System.out.println("Le zombie ne peut pas se déplacer. A votre tour !");
	        			 }
	        			 break;
	        	case 4 : couranteY--;
	        			 if (map[couranteX][couranteY] == "/") 
	        			 {
	        				 System.out.println("Le zombie ne peut pas se déplacer. A votre tour !");
	        			 }
	        			 break;
	        }
	        
	    }
	}
	
	@Override
	public int attaquer() 
	{
		//attaque de zombie ?????
		return 1;
	}
	
	public int attaquer(Personnage perso) 
	{
		attaquer();
		//attaque de zombie ?????
		return 1;
	}

	@Override
	public void apparition(int x, int y)
	{
		//apparition de zombie ????
	}
	
	public static int apparition()
	{
		return 1;
	}
}

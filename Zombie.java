package main;

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

	/**
	 * permet au zombie de se deplacer en fonction de la position du joueur
	 * se deplace en priorite sur l'axe X puis sur l'axe Y
	 * @param perso : Personnage
	 * @param carte : Carte
	 */
	public void deplacer(Personnage perso, Carte carte)
	{
		int couranteX = this.getEmplacement().getPosX();
		int couranteY = this.getEmplacement().getPosY();
		int persPosX = perso.getEmplacement().getPosX();
		int persPosY = perso.getEmplacement().getPosY();
		
		if(couranteX > persPosX && this.verification(carte,new Position(couranteX - 1,couranteY)))
		{
			this.setEmplacement(new Position(couranteX - 1,couranteY));
		}
		else if(couranteX < persPosX && this.verification(carte,new Position(couranteX + 1,couranteY)))
		{
			this.setEmplacement(new Position(couranteX + 1,couranteY));
		}
		else if(couranteY > persPosY && this.verification(carte,new Position(couranteX,couranteY - 1)))
		{
			this.setEmplacement(new Position(couranteX,couranteY - 1));
		}		
		else if(couranteY < persPosY && this.verification(carte,new Position(couranteX,couranteY + 1)))
		{
			this.setEmplacement(new Position(couranteX,couranteY + 1));
			
		}
		
	}
	/**
	 * verifie si le zombie peut se deplacer a une certaine coordonnée
	 * @param carte : Carte
	 * @param z : Position
	 * @return true si le joueur peut se deplacer a la position z, false sinon
	 */
	public boolean verification(Carte carte, Position z) 
	{
		try 
		{
			return (carte.getTab()[z.getPosY()][z.getPosX()].equals(carte.getCarPossible()));
		}
		catch(ArrayIndexOutOfBoundsException e)
		{
			e.getMessage();
			return false;
		}
	}
	
	@Override
	/**
	 * renvoie 1 car le zombie n'a qu'un point d'action pour le moment (MAJ)
	 */
	public int attaquer() 
	{
		return 1;
	}
	
	public int attaquer(Personnage perso) 
	{
		attaquer();
		return 1;
	}



	@Override
	public void deplacer(String direction, Carte carte) {
		// TODO Auto-generated method stub
		
	}
}
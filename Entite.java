/**
 * 
 */
package main;
/**
 * @author he201460
 *
 */
public abstract class Entite
{
	private String nom;
	private int id;
	private int pointsDeVie;
	private int pointsDAction;
	private Position emplacement;
	
	/**
	 * constructeur d'entite 
	 * @param nom : String
	 * @param id : int
	 * @param pointsDeVie : int
	 * @param pointsDAction : int
	 * @param emplacement : Position
	 */
	public Entite(String nom, int id, int pointsDeVie, int pointsDAction, Position emplacement)
	{
		this.nom = nom;
		this.id = id;
		this.pointsDeVie = pointsDeVie;
		this.pointsDAction = pointsDAction;
		this.emplacement = emplacement;
	}
		
	public abstract int attaquer();
	
	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getPointsDeVie() {
		return pointsDeVie;
	}

	public void setPointsDeVie(int pointsDeVie) {
		this.pointsDeVie = pointsDeVie;
	}

	public int getPointsDAction() {
		return pointsDAction;
	}

	public void setPointsDAction(int pointsDAction) {
		this.pointsDAction = pointsDAction;
	}

	public Position getEmplacement() {
		return emplacement;
	}

	public void setEmplacement(Position emplacement) {
		this.emplacement = emplacement;
	}

}
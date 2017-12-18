/**
 * 
 */
package testMVC;


/**
 * @author he201460
 * @author he201426
 * ajout d'informations
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
	
	public abstract void deplacer(String direction, Carte carte);
	
	public abstract int attaquer();
	
	/**
	 * @return nom : renvoie le nom de l'entité. 
	 */
	public String getNom() {
		return nom;
	}

	/**
	 * Définit le nom de l'entité.
	 */
	public void setNom(String nom) {
		this.nom = nom;
	}

	/**
	 * @return id : identifiant de l'entité. 
	 */
	public int getId() {
		return id;
	}
	
	/**
	 * Définit l'id de l'entité.
	 */
	public void setId(int id) {
		this.id = id;
	}

	/**
	 * @return pointsDeVie : renvoie les points de vie de l'entité.
	 */
	public int getPointsDeVie() {
		return pointsDeVie;
	}

	/** définit les points de vie de l'entité.*/
	public void setPointsDeVie(int pointsDeVie) {
		this.pointsDeVie = pointsDeVie;
	}

	/** @return pointsDAction : renvoie les points d'action de l'entité.*/
	public int getPointsDAction() {
		return pointsDAction;
	}

	/** définit les points d'action de l'entité.*/
	public void setPointsDAction(int pointsDAction) {
		this.pointsDAction = pointsDAction;
	}

	/** @return emplacement : renvoie la position de l'entité.*/
	public Position getEmplacement() {
		return emplacement;
	}

	/** définit l'emplacement de l'entité.*/
	public void setEmplacement(Position emplacement) {
		this.emplacement = emplacement;
	}

}
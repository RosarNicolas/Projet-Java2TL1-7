//classe pour le personnage zombie

package projet;

/**
 * @author Loïc Persyn
 * dernière modif : 23-10-17
 */
public abstract class Zombies {

	private int pointsDeVie;
	private int pointsDactions;
	private int id;
	private String nom;
	
	public static Zombies [] zombies = new Zombies [10];
	public static Zombies walker = new Walker();
	public static Zombies runner = new Runner();
	public static Zombies fatty = new Fatty();
	public static Zombies crawler = new Crawler();
	
	
	public Zombies(int pdv, int pda, int id, String n) {
		this.pointsDeVie = pdv;
		this.pointsDactions = pda;
		this.id = id;
		this.nom = n;
		
		zombies[id] = this;
	}


	
	public int getPointsDeVie() {
		return pointsDeVie;
	}

	public void setPointsDeVie(int pdv) {
		this.pointsDeVie = pdv;
	}

	public int getPointsDactions() {
		return pointsDactions;
	}

	public void setPointsDactions(int pda) {
		this.pointsDactions = pda;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String n) {
		this.nom = n;
	}
	
}

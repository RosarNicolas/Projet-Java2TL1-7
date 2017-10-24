package main;

public class Characters {
	private int numJoueur;
	private int sante;
	private int pointDAction;
	private Weapons gauche;// à null au début puis donner arme par défaut
	private Weapons droite; // à null au début
	private int chance;
	private int abscisse;
	private int ordonnee;
	private String nom;
	
	/** CONSTRUCTEUR */
	public Characters(String nom, int numJ, int sante, int pointAction, int chance, int x, int y) {
		this.numJoueur = numJ;
		this.sante = sante;
		this.pointDAction = pointAction;
		this.chance = chance;
		this.abscisse = x;
		this.ordonnee = y;
		this.nom = nom;
	}
	
	
	/** ACTIONS */
	public void deplacement() {
		
		pointDAction--;
		estFinTour();
	}
	
	public void fouille( int chance/*paramètre représentant la CHANCE de loot */){
		
		if(cherche(/*chance*/)) {
			System.out.println("Vous avez trouvé quelque chose!");
			randomArme();
			pointDAction--;
			estFinTour();
		}
		else {
			System.out.println("Vous repartez les mains vides ...");
			pointDAction--;
			estFinTour();
		}
	}
	
	public void attaque(Weapons x){ //l'arme dans l'inventaire
		if( x == null ) { // donner le chiffre au-dessus de la position dans le tableau
			System.out.println("Vous n'avez pas d'arme à cet emplacement");
			// !!!!!! vérifier si possède arme 
		}
		else {
			System.out.println("Attaque avec : " + x);
			// Methode ENNEMI.sante - methode ARME.degats
			pointDAction--;
			estFinTour();
		}
	}
	
	/** METHODES */
	// calcul pour déterminer si trouvé qq chose
	public boolean cherche(/*chance*/) {
		
		return false;
	}
	
	// terminer tour si point d'action = 0
	public boolean estFinTour() {
		if(pointDAction == 0) {
			// fin du tour
			return true;
		}
		else{
			
			System.out.println("Il vous reste " + pointDAction + " points d'action.");
			return false;
		}
		
	}
	
	/** GETTERS SETTERS */
	public int getNumJoueur() {
		return numJoueur;
	}
	public void setNumJoueur(int numJoueur) {
		this.numJoueur = numJoueur;
	}

	public int getSante() {
		return sante;
	}
	public void setSante(int sante) {
		this.sante = sante;
	}

	public int getPointDAction() {
		return pointDAction;
	}
	public void setPointDAction(int pointDAction) {
		this.pointDAction = pointDAction;
	}

	public Weapons getGauche() {
		return gauche;
	}
	public void setGauche(Weapons gauche) {
		this.gauche = gauche;
	}

	public Weapons getDroite() {
		return droite;
	}
	public void setDroite(Weapons droite) {
		this.droite = droite;
	}

	public int getChance() {
		return chance;
	}
	public void setChance(int chance) {
		this.chance = chance;
	}

	public int getAbscisse() {
		return abscisse;
	}
	public void setAbscisse(int abscisse) {
		this.abscisse = abscisse;
	}

	public int getOrdonnee() {
		return ordonnee;
	}
	public void setOrdonnee(int ordonnee) {
		this.ordonnee = ordonnee;
	}
	
	
	// renvoie une arme aléatoire
	public String randomArme() {
		
		return "Arme au pif";
	}
	

	public static void main(String[] args) {
		

	}

}
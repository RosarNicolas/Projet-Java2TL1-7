package entit�s;

public class Characters {
	private int numJoueur;
	private int sante;
	private int pointDAction;
	private Armes gauche;// � null au d�but puis donner arme par d�faut
	private Armes droite; // � null au d�but
	private int chance;
	private int abscisse;
	private int ordonnee;
	
	/** CONSTRUCTEUR */
	public Characters(int numJ, int sante, int pointAction, Armes gauche, Armes droite, int chance, int x, int y) {
		this.numJoueur = numJ;
		this.sante = sante;
		this.pointDAction = pointAction;
		this.gauche = new Armes() gauche;
		this.droite = new Armes() droite;
		this.chance = chance;
		this.abscisse = x;
		this.ordonnee = y;
	}
	
	
	/** ACTIONS */
	public void deplacement() {
		
		pointDAction--;
		estFinTour();
	}
	
	public void fouille( int chance/*param�tre repr�sentant la CHANCE de loot */){
		
		if(cherche(/*chance*/)) {
			System.out.println("Vous avez trouv� quelque chose!");
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
	
	public void attaque(Armes x){ //l'arme dans l'inventaire
		if( x == null ) { // donner le chiffre au-dessus de la position dans le tableau
			System.out.println("Vous n'avez pas d'arme � cet emplacement");
			// !!!!!! v�rifier si poss�de arme 
		}
		else {
			System.out.println("Attaque avec : " + x);
			// Methode ENNEMI.sante - methode ARME.degats
			pointDAction--;
			estFinTour();
		}
	}
	
	/** METHODES */
	// calcul pour d�terminer si trouv� qq chose
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

	public Armes getGauche() {
		return gauche;
	}
	public void setGauche(Armes gauche) {
		this.gauche = gauche;
	}

	public Armes getDroite() {
		return droite;
	}
	public void setDroite(Armes droite) {
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
	
	
	// renvoie une arme al�atoire
	public String randomArme() {
		
		return "Arme au pif";
	}
	

	public static void main(String[] args) {
		

	}

}
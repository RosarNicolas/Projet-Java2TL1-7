package main1;
/**
 * Crée par VKBJ
 * Modifié par PL et NR
 */
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
	public void deplacement() 
	{
		
		pointDAction--;
		estFinTour();
	}
	
	public static Weapons fouille()//a generaliser car meme methode que l'apparition des zombies
	{
		double a = Math.random()*1000;
		int b = (int)a%11;
		System.out.println("Vous avez trouvé un "+Weapons.armes[b].getNomDeLarme() +" !"
				  +"\nSes dégats sont de : " + Weapons.armes[b].getDegats()
				  +"\nSa portée est de :  "+ Weapons.armes[b].getRange()
				  +"\nVous pouvez attaquer "+Weapons.armes[b].getNombreDeFrappe()+" fois avec par action"
				  +"\nVotre précision est de "+ ((int)(Weapons.armes[b].getChanceDeToucher()*100))+"%\n\n");
			return Weapons.armes[b];
	}
	
	public static Zombies rentre()
	{
		
		Game.counterDeZombie++;
		double a = Math.random()*1000;
		int b = (int)a%4;
		System.out.println("Un zombie " + Zombies.zombies[b].getNom()+" apparait !\n"
							+"Il possède "+Zombies.zombies[b].getPointsDeVie() +" points de vie "
							+"et "+Zombies.zombies[b].getPointsDactions()+" point d'action !");
			if(b == 0)
			{
				Zombies aRenvoyer = new Crawler();
				return aRenvoyer;
			}
			if(b == 1)
			{
				Zombies aRenvoyer = new Walker();
				return aRenvoyer;
			}
			if(b == 2)
			{
				Zombies aRenvoyer = new Runner();
				return aRenvoyer;
			}
			else
			{
				Zombies aRenvoyer = new Fatty();
				return aRenvoyer;
			}
	}
	
	
	public int attaque(int noArme){ //l'arme dans l'inventaire
		if( this.gauche == null && this.droite == null ) { // donner le chiffre au-dessus de la position dans le tableau
			System.out.println("Vous n'avez pas d'arme, fuyez !");
			// !!!!!! vérifier si possède arme 
			return 0;
		}
		else if(noArme == 1)
		{
			int degatsTot = 0;
			int countNbreDeCoups = this.gauche.getNombreDeFrappe();
			System.out.println("Attaque avec : " + this.gauche.getNomDeLarme());
			for(int i = 0; i < countNbreDeCoups ; i++)
			{
			
			//rand
			if(this.gauche.random())
			{
				degatsTot += this.gauche.getDegats();
			}
			
			//pointDAction--;
			}	
			System.out.println("Vous infligez "+ degatsTot + " points de degats au zombie !");
			return degatsTot;
		}
		else if(noArme == 2)
		{
			int degatsTot = 0;
			int countNbreDeCoups = this.gauche.getNombreDeFrappe();
			System.out.println("Attaque avec : " + this.droite.getNomDeLarme());
			for(int i = 0; i < countNbreDeCoups ; i++)
			{
			
			//rand
			if(this.droite.random())
			{
				degatsTot += this.droite.getDegats();
			}
			
			//pointDAction--;
			}	
			System.out.println("Vous infligez "+ degatsTot + "points de degats au zombie !");
			return degatsTot;
		}
		return 0;
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
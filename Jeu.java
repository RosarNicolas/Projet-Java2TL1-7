/**
 * Groupe 7 JAVA PROJECT
 * MAJ 17/11/2017
 * classe fonctionelle mais pas parfaite 
 * verifier les entrees des joueurs (exception)
 * verifier les comportement des zombies 
 * verifier les attaque des joueurs
 */
package main;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Observable;

/**
 * @author groupe 7
 */
public class Jeu extends Observable {
	
	private Carte carte;
	private Personnage perso;
	private HashMap<Integer, Arme> armes = new HashMap<>();
	private LinkedList<Entite> entiteSurCarte = new LinkedList<Entite>();
	private LinkedList<Zombie> zombiesSurCarte = new LinkedList<Zombie>();
	private LinkedList<Zombie> zombiesSurCase = new LinkedList<Zombie>();
	private int compteurTour = 1;
	private int fin = 0;
	
	public Jeu()
	{
		init();
	}
	
	/**
	 * methode qui initialise les LinkedList avec tous les types de zombies et d'armes differents
	 * fais apparaitre un zombie (a determiner en fonction de la difficulté futur MAJ)
	 */
	public void init()
	{
		armes.put(1,new Arme(1, 1, 1, 1, 0.66, 0.125,0.05, "Arc"));
		armes.put(2,new Arme(2, 1, 0, 3, 0.5, 0.125,0.05, "Epée"));
		armes.put(3,new Arme(3, 1, 2, 2, 0.5, 0.125,0.05, "Ak47"));
		armes.put(4,new Arme(4, 2, 0, 1, 0.5, 0.125,0.05, "Hache"));
		armes.put(5,new Arme(5, 2, 0, 5, 0.33, 0.125,0.05, "Tronçonneuse"));
		armes.put(6,new Arme(6, 1, 1, 1, 0.66, 0.125,0.05, "Pistolet"));
		armes.put(7,new Arme(7, 1, 0, 2, 0.5, 0.125,0.05, "Batte Cloutée"));
		armes.put(8,new Arme(8, 2, 1, 2, 0.5, 0.125,0.05, "Fusil à pompe"));
		armes.put(9,new Arme(9, 0, 0, 1, 0.0, 0.125,0.05, "Détritus"));
		armes.put(10,new Arme(10, 0, 0, 1, 0.0, 0.125,0.05, "Détritus"));
		armes.put(11,new Arme(11, 0, 0, 1, 0.0, 0.125,0.05, "Détritus"));
		armes.put(12,new Arme(12, 0, 0, 1, 0.0, 0.125,0.05, "Détritus"));
		
		carte = new Carte("res/carte4.txt");
		zombieApparition();
		setChanged();
		notifyObservers();
	}
	public int actionPerso(int actionPossible, String deplacement, int armeAjeter, int attaque, Zombie z)
	{
		if(actionPossible != 0 )
		{
			int fouille = perso.fouille(armes);
			updateEntiteListe();
			setChanged();
			notifyObservers();
			return fouille;
		}
		else if(!deplacement.equals(""))
		{
			perso.deplacer(deplacement, this.carte);
			updateEntiteListe();
			setChanged();
			notifyObservers();
		}
		else if(armeAjeter != 0)	
		{
			perso.jeterUneArme(armeAjeter);
			updateEntiteListe();
			setChanged();
			notifyObservers();
		}
		else if(attaque != 0)
		{
			int degats = perso.attaquer(attaque);
			return degats;
			
		}
		return 0;
	}
	
	public void notifier()
	{
		updateEntiteListe();
		setChanged();
		notifyObservers();
	}
	
	/**
	 * action des zombies lors de leur tour
	 * les zombie jouent tous en meme temps 
	 * soit ils attaquent soit ils se deplacent
	 */
	public boolean tourZombie()
	{
		boolean mordu = false;
		for(Zombie z : zombiesSurCarte)
		{
			if(z.getEmplacement().equals(perso.getEmplacement()))
			{
				int degat = z.attaquer();
				perso.setPointsDeVie(perso.getPointsDeVie() - degat);
				mordu = true;
			}
			else
			{
				z.deplacer(perso, carte);
				updateEntiteListe();
			}
		}
		setChanged();
		notifyObservers();
		return mordu;
	}
	
	/**
	 * fais apparaitre un zombie sur la carte de maniere aléatoire
	 * jamais la ou le joueur se trouve
	 */
	public void zombieApparition()
	{
		String [][] courant = carte.getTab();
		int posX = 0;
		int posY = 0;
		String debug2 = "";
		String signeCasePratiquable = courant[carte.getApparition().getPosX()][carte.getApparition().getPosY()];
		do {
			posX = (int) Math.floor((Math.random() * carte.getLargeur() ));
			posY = (int) Math.floor((Math.random() * carte.getLongueur()));
			debug2  = courant[posY][posX];
		   }while(!debug2.equals(signeCasePratiquable) || (posX == 0 && posY == 0));		
		//a optimiser (liste d'un seul element)
		zombiesSurCarte.add(new Zombie(new Position(posX,posY)));
		updateEntiteListe();
		if(compteurTour != 1)
		{
			setChanged();
			notifyObservers();
		}
	}
	
	/**
	 * met a jour la linkedList en fonction des entites sur la map
	 */
	public void updateEntiteListe()
	{
		entiteSurCarte.removeAll(entiteSurCarte);
		if(perso != null)
		{
			Entite ent = new Personnage(perso.getNom(),perso.getId(), perso.getPointsDeVie(), perso.getPointsDAction(),  perso.getEmplacement() );
			entiteSurCarte.add(ent);
		}
		entiteSurCarte.addAll(zombiesSurCarte);
		
	}
	
	/**
	 *  permet de recuperer les zombie sur une case precise
	 * @param x Position sur laquelle on veut recuperer les zombies
	 */
	public void updateZombieSurCase(Position x)
	{
		zombiesSurCase.removeAll(zombiesSurCase);
		for(Zombie z : zombiesSurCarte)
		{
			if(z.getEmplacement().equals(x))
			{
				zombiesSurCase.add(z);
			}
		}
	}
	
	public Carte getCarte() {
		return carte;
	}
	public void setCarte(Carte carte) {
		this.carte = carte;
	}
	public Personnage getPerso() {
		return perso;
	}
	public void setPerso(Personnage perso) {
		this.perso = perso;
	}
	public HashMap<Integer, Arme> getArmes() {
		return armes;
	}
	public void setArmes(HashMap<Integer, Arme> armes) {
		this.armes = armes;
	}
	public LinkedList<Entite> getEntiteSurCarte() {
		return entiteSurCarte;
	}
	public void setEntiteSurCarte(LinkedList<Entite> entiteSurCarte) {
		this.entiteSurCarte = entiteSurCarte;
	}
	public LinkedList<Zombie> getZombiesSurCarte() {
		return zombiesSurCarte;
	}
	public void setZombiesSurCarte(LinkedList<Zombie> zombiesSurCarte) {
		this.zombiesSurCarte = zombiesSurCarte;
	}
	public LinkedList<Zombie> getZombiesSurCase() {
		return zombiesSurCase;
	}
	public void setZombiesSurCase(LinkedList<Zombie> zombiesSurCase) {
		this.zombiesSurCase = zombiesSurCase;
	}
	public int getCompteurTour() {
		return compteurTour;
	}
	public void setCompteurTour(int compteurTour) {
		this.compteurTour = compteurTour;
	}

	public int getFin() {
		return fin;
	}

	public void setFin(int fin) {
		this.fin = fin;
	}

}
/**
 * 
 */
package main;

/**
 * @author he201460
 *
 */
public class Arme 
{
	private int id;
	private int degats;
	private int portee;
	private int nombreDeFrappe;
	private double chanceDeToucher;
	private double coupsCritique;
	private double echecCritique;
	private String nomDeLarme;
	
	public Arme(int id, int degats, int portee, int nombreDeFrappe, double chanceDeToucher,
				double coupsCritique, double echecCritique, String nomDeLArme)
	{
		this.id = id;
		this.degats = degats;
		this.portee = portee;
		this.nombreDeFrappe = nombreDeFrappe;
		this.chanceDeToucher = chanceDeToucher;
		this.coupsCritique = coupsCritique;
		this.echecCritique = echecCritique;
		this.nomDeLarme = nomDeLArme;
	}
	
	/**
	 * chance de toucher avec une arme pour un coup
	 * @return true si l'arme touche false sinon
	 */
	public boolean tentativeDAttaque()
	{
		//return true;
		
		int a = (int) (Math.random() * 100);
		int b = (int) (this.chanceDeToucher * 100.0);
		return(a<b);
	}

	/*
	 *getters and setters
	 */
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getDegats() {
		return degats;
	}

	public void setDegats(int degats) {
		this.degats = degats;
	}

	public int getPortee() {
		return portee;
	}

	public void setPortee(int portee) {
		this.portee = portee;
	}

	public int getNombreDeFrappe() {
		return nombreDeFrappe;
	}

	public void setNombreDeFrappe(int nombreDeFrappe) {
		this.nombreDeFrappe = nombreDeFrappe;
	}

	public double getChanceDeToucher() {
		return chanceDeToucher;
	}

	public void setChanceDeToucher(double chanceDeToucher) {
		this.chanceDeToucher = chanceDeToucher;
	}

	public double getCoupsCritique() {
		return coupsCritique;
	}

	public void setCoupsCritique(double coupsCritique) {
		this.coupsCritique = coupsCritique;
	}

	public double getEchecCritique() {
		return echecCritique;
	}

	public void setEchecCritique(double echecCritique) {
		this.echecCritique = echecCritique;
	}

	public String getNomDeLarme() {
		return nomDeLarme;
	}

	public void setNomDeLarme(String nomDeLarme) {
		this.nomDeLarme = nomDeLarme;
	}
}

package projet;

public abstract class Weapons
{
	private static String nomDeLarme;
	private int nombreDeFrappe;
	private double chanceDeToucher;
	private int degats;
	private double coupsCritique;
	private double echecCritique;
	private int range;
	private int id;
	
	public static Weapons [] armes = new Weapons [256];
	public static Weapons arc = new Arc();
	public static Weapons ak47 = new Ak47();
	public static Weapons carabine = new Carabine();
	public static Weapons epee = new Epee();
	public static Weapons fusilapompe = new FusilAPompe();
	public static Weapons hache = new Hache();
	public static Weapons masseurbaine = new MasseUrbaine();
	public static Weapons pieddebiche = new PiedDeBiche();
	public static Weapons pistolet = new Pistolet();
	public static Weapons tronconeuse = new Tronconeuse();
	public static Weapons mp5 = new Mp5();
	
	
	public Weapons(String nomDeLarme, int nombreDeFrappe, double chanceDeToucher, int degats, double coupsCritique, double echecCritique, int range, int id)
	{	
		this.nomDeLarme = nomDeLarme;
		this.nombreDeFrappe = nombreDeFrappe;
		this.chanceDeToucher = chanceDeToucher;
		this.degats = degats;
		this.coupsCritique = coupsCritique;
		this.echecCritique = echecCritique;
		this.range = range;
		this.id = id;
		
		armes[id] = this;
		
	}
	
	public boolean random()
	{
		int a =(int) (Math.random() * 100);
		int chance = (int) (this.chanceDeToucher *100) ;
		return a<chance;
	}
	
	public double getChanceDeToucher() 
	{
		return chanceDeToucher;
	}
	public void setChanceDeToucher(double chanceDeToucher)
	{
		this.chanceDeToucher = chanceDeToucher;
	}
	public int getDegats()
	{
		return degats;
	}
	public void setDegats(int degats)
	{
		this.degats = degats;
	}
	public double getCoupsCritique() 
	{
		return coupsCritique;
	}
	public void setCoupsCritique(double coupsCritique)
	{
		this.coupsCritique = coupsCritique;
	}
	public double getEchecCritique()
	{
		return echecCritique;
	}
	public void setEchecCritique(double echecCritique)
	{
		this.echecCritique = echecCritique;
	}
	public int getRange() 
	{
		return range;
	}
	public void setRange(int range)
	{
		this.range = range;
	}
	public int getId()
	{
		return id;
	}
	public void setId(int id)
	{
		this.id = id;
	}

	public int getNombreDeFrappe()
	{
		return nombreDeFrappe;
	}

	public void setNombreDeFrappe(int nombreDeFrappe)
	{
		this.nombreDeFrappe = nombreDeFrappe;
	}

	public static String getNomDeLarme() {
		return nomDeLarme;
	}
		
		
}



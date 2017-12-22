package def;

import java.util.Observer;

public abstract class JeuVue implements Observer
{
	protected Jeu modele;
	protected JeuController controle;
	
	public JeuVue(Jeu modele, JeuController controle)
	{
		this.modele = modele;
		this.controle = controle;
		
		this.modele.addObserver(this);
	}
	
	public abstract void affiche(String string) ;
	
	public abstract int choixArme();
	
	public abstract Position choixAttaque();
	
	public abstract String deplacement();
}

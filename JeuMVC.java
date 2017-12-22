package main;

public class JeuMVC 
{
	/**
	 * modele MVC class a lance pour lancer le jeu
	 */
	public JeuMVC()
	{
		Jeu modele = new Jeu();
		
		JeuController console = new JeuController(modele);
		JeuController GUI = new JeuController(modele);
		
		JeuVueGUI vueGUI = new JeuVueGUI(modele, GUI);
		JeuVueConsole vueConsole = new JeuVueConsole(modele, console);
		
		console.addView(vueConsole);
		GUI.addView(vueGUI);
		
		
	}
	public static void main(String[] args)
	{
		ServeurThread serveur = new ServeurThread();
		serveur.start();
		
		JeuThread jeu = new JeuThread();
		jeu.start();
	}

}

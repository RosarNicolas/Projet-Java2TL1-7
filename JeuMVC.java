package def;

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
		javax.swing.SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				new JeuMVC();
			}
		});
	}

}

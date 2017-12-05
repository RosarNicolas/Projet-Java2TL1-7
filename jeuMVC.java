package test;

import java.util.Observable;

import controller.jeuController;
import main.Jeu;
import vues.jeuVueConsole;

public class jeuMVC extends Observable {

	public jeuMVC() {
		
		// cr�ation model
		Jeu partie = new Jeu();
		
		// cr�ation controller
		jeuController controllerVueConsole = new jeuController( partie );
		
		// cr�ation de la vue
		jeuVueConsole vueConsole = new jeuVueConsole( partie, controllerVueConsole);
		
		// ajout de la vue au controller
		controllerVueConsole.addView( vueConsole );
	
	}
	
	public static void main(String[] args) {
		
		javax.swing.SwingUtilities.invokeLater(
			new Runnable() {
				public void run() {
					
					new jeuMVC();
			
				}
			});
	}
	
}

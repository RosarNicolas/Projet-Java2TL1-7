package test;

import java.util.Observable;

import controller.jeuController;
import main.Jeu;
import vues.jeuVueConsole;

public class jeuMVC extends Observable {

	public jeuMVC() {
		
		// création model
		Jeu partie = new Jeu();
		
		// création controller
		jeuController controllerVueConsole = new jeuController( partie );
		
		// création de la vue
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

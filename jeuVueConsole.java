package vues;

import java.util.InputMismatchException;
import java.util.Observable;
import java.util.Scanner;

import controller.jeuController;
import main.Jeu;

public class jeuVueConsole extends jeuVue{
protected Scanner sc;
	
	public jeuVueConsole( Jeu model, jeuController controller ) {
		
		super(model, controller);
		// update(null,null) ?????
		
		sc = new Scanner( System.in );
		new Thread (new ReadInput()).start();
	}
	
	
	@Override
	public void update(Observable o, Object arg) {
		
		// ce qu'il faut faire lors d'un changememnt
		// réimprimer la carte.
		afficher("");
		
	}
	
	public void bienvenue() {
		
		afficher("Bienvenue dans zombicide votre objectif, atteindre la sortie en bottant le cul a  des zombies. Pas compliqué");
		afficher("Quel est le nom de votre personnage ?");
		
	}
	
	
	private class ReadInput implements Runnable{
		public void run() {
			
			while(true) {
				try {
					String s = sc.next();
					if ( s.equals(1) ) {
						
					}
					
					
					
					
				}
				catch(InputMismatchException e) {
					afficher("Vous ne pouvez pas effectuer cette action.");
				}
				
			}
			
		}
	}
}

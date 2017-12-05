package vues;

import java.util.Observer;

import controller.jeuController;
import main.Jeu;

public abstract class jeuVue implements Observer {
	protected Jeu model;
	protected jeuController controller;
	
	jeuVue ( Jeu model, jeuController controller ){
		
		this.model = model;
		this.controller = controller;
		
		// connection vue modèle
		this.model.addObserver(this);
		
	}
	
	public void afficher( String message ) {
		System.out.println(message);
	}
}

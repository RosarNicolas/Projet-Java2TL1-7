package controller;

import main.Jeu;
import vues.jeuVue;

public class jeuController {
	Jeu model;
	jeuVue vue;
	
	public jeuController( Jeu model ) {
		this.model = model;
	}
	
	public void addView( jeuVue vue ) {
		this.vue = vue;
	}
	
	
	
	
}

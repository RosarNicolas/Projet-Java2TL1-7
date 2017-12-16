package testMVC;

import java.util.Scanner;

import javax.swing.*;

public class Test {

	public static void main(String[] args) 
	{
		/*Jeu jeu = new Jeu();
		JeuController controle = new JeuController(jeu);
		JeuVueConsole vue = new JeuVueConsole(jeu, controle);
		controle.addView(vue);
		vue.main();*/
		Jeu jeu1 = new Jeu();
		JeuController jeu2 = new JeuController(jeu1);
		JeuVueGUI jeu = new JeuVueGUI(jeu1, jeu2);
		jeu2.addView(jeu);
		
		
	}

}

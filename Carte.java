package model;
/**
 * @author Nicolas
 * Dernière modification : 16/11/17
 */
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import main.Characters;

/**
 * Classe pour l'affichage d'une carte en console.
 * Cette carte doit être modifiée selon le déplacement d'un personnage
 */
public class Carte {
	
	/**
	 * l : variable pour contenir les lignes du fichier lu
	 * compteur : variable pour changer de place dans le tableau
	 * 			à double entrée
	 * a : variable pour contenir la longueur (en nombre de caractères) d'une ligne
	 * 			de la carte.
	 * tab2 : tableau pour contenir les lignes lues dans le fichier
	 * 			pour manipuler celle-ci.
	 * tab : tableau à double entrée contenant la carte en caractères.
	 */
	private static String l = "";
	private static int compteur = 0;
	private static int a = 0;
	private static String[] tab2 = new String[50];
	private static String[][] tab = new String[13][17];
	
	// METHODE Lecture carte
	/**
	 * Méthode faisant un lecture dans un fichier
	 * pour en ramener un affichage de carte dans la console.
	 */
	public void lectureCarte() {
		try {
			BufferedReader reader = new BufferedReader( new FileReader("C:\\Users\\Nicolas\\Downloads\\carte 4.txt")  );
			l = reader.readLine(); // passer la première ligne contenant des caractères invisibles
			l = reader.readLine();
			
			// obtention des longueurs et largeur de la carte
			// a contient la longueur d'une ligne
			tab2 = l.trim().split(" ");
			a = Integer.parseInt(tab2[0]);
		
			// lecture tant que pas à la fin du fichier
			while( (l = reader.readLine()) != null) {
				tab2 = l.split("");
			
				for(int i = 0 ; i < a ; i++) {
					tab[compteur][i] = tab2[i]; // Mise des lignes dans le tableau
				
				}
				compteur++;
			}
			reader.close();
		
		}
		catch (IOException e) {
			
			System.err.println("Erreur durant la lecture du fichier");
			
		}
		
		// test pour l'affichage
		for(int i = 0 ; i < tab.length ; i++) {
			System.out.println("");
			for(int j = 0 ; j < tab[0].length ; j ++){
				System.out.print(tab[i][j]);
			}
		}
	
	}
	
	/*
	 * Mémo
	tab[1][2] = "g";
	tab[3][2] = "h";	// saut de 2 VERTICAL
	tab[3][6] = "d";	// saut de 4 HORIZONTAL
	*/
	
	/**
	 * Cette méthode permet de gérer le déplacement
	 * visuel en Console.
	 * Cette méthode sera peut-être modifée selon l'implémentation 
	 * de la classe characters mais son fonctionnement devrait rester le même.
	 * ( pour ne pas modifier Abscisse et ordonnée avant vérification )
	 * 
	 * @param c : Objet de type Characers/Personnages
	 * @param a : valeur de l'ancienne position en abscisse
	 * @param b : valeur de l'ancienne position en ordonnée
	 */
	// METHODE déplacement avec vérification
	public void deplacement(Characters c, int a, int b) {
		// conserver les anciennes positions du personnages avec a et b
		if (tab[a][b] == "/") { 
			
			System.out.println("Vous ne pouvez pas aller par là.");
			c.setAbscisse(a);
			c.setOrdonnee(b);
			
		}
		// déplacement si pas d'erreur
		else {
			
			tab[c.getAbscisse()][c.getOrdonnee()] = "☺";
			tab[a][b] = "#";
			
		
		}
	}
	// catch au cas ou en dehors du tableau
	
	public static void main(String args[]) {
		
		Carte c = new Carte();
		c.lectureCarte();
		
	}
}


		


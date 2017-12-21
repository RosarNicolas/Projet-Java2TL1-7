package main;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class TestGlobaux {

	@Test
	void test() 
	{
		//Test de classe indépendamment des autres
				//Position
				Position testPosition = new Position(1,3);
				assertEquals(1,testPosition.getPosX());
				assertEquals(3,testPosition.getPosY());
				//Personnage
				Personnage testPerso = new Personnage("X", 1,3,3,testPosition);
				assertEquals("X",testPerso.getNom());
				assertEquals(1,testPerso.getId());
				assertEquals(3,testPerso.getPointsDAction());
				assertEquals(3,testPerso.getPointsDeVie());
				assertTrue(testPerso.getEmplacement().getPosX() == 1);
				assertTrue(testPerso.getEmplacement().getPosY() == 3);
				//Carte
				Carte testCarte = new Carte("res/carte4.txt");
				assertEquals(3,testCarte.getSortie().getPosX());
				assertEquals(5,testCarte.getSortie().getPosY());
				assertEquals(0,testCarte.getApparition().getPosX());
				assertEquals(0,testCarte.getApparition().getPosY());
				//Zombie
				Zombie testZombie =  new Zombie(new Position(0,2));
				assertEquals(1, testZombie.attaquer());
				assertTrue(testZombie.getEmplacement().getPosX() == 0);
				assertTrue(testZombie.getEmplacement().getPosY() == 2);
				//Test du deplacement du zombie
				testZombie.deplacer(testPerso, testCarte); 
				assertTrue(testZombie.getEmplacement().getPosX() == 0);
				assertTrue(testZombie.getEmplacement().getPosY() == 3);
				//Test du deplacement du Zombie en cas limite (bloque par element)
				testZombie.setEmplacement(new Position(2,0));
				testPerso.setEmplacement(new Position(0,0));
				testZombie.deplacer(testPerso,  testCarte);
				assertTrue(testZombie.getEmplacement().getPosX() == 2);
				assertTrue(testZombie.getEmplacement().getPosY() == 0);
				
				//Jeu
				Jeu jeu = new Jeu();
				jeu.setPerso(new Personnage("X", 1,3,3,jeu.getCarte().getApparition()));
					//Test apparition du personnage
					assertEquals("X",jeu.getPerso().getNom());
					assertEquals(1,jeu.getPerso().getId());
					assertEquals(3,jeu.getPerso().getPointsDAction());
					assertEquals(3,jeu.getPerso().getPointsDeVie());
					assertTrue(jeu.getPerso().getEmplacement().getPosX() == 0);
					assertTrue(jeu.getPerso().getEmplacement().getPosY() == 0);
					//Test apparition d'un zombie
					assertEquals(1,jeu.getZombiesSurCarte().size());
					//Test personnage
						//Test fouille
						jeu.getPerso().fouille(jeu.getArmes());
						assertFalse(jeu.getPerso().getArmeGauche() == null);
						assertTrue(jeu.getPerso().getArmeDroite() == null);
						jeu.getPerso().fouille(jeu.getArmes());
						assertFalse(jeu.getPerso().getArmeGauche() == null);
						assertFalse(jeu.getPerso().getArmeDroite() == null);
						//Test deplacement
						jeu.updateZombieSurCase(jeu.getPerso().getEmplacement());
						jeu.getPerso().deplacer("bas", jeu.getCarte(),jeu.getZombiesSurCase());
						assertTrue(jeu.getPerso().getEmplacement().getPosX() == 0);
						assertTrue(jeu.getPerso().getEmplacement().getPosY() == 1);
						//Test deplacement limite
						jeu.updateZombieSurCase(jeu.getPerso().getEmplacement());
						jeu.getPerso().deplacer("gauche", jeu.getCarte(),jeu.getZombiesSurCase());//le joueur essaye de sortir de la carte mais le jeu l'en empeche et sa position n'est pas modifiée
						assertTrue(jeu.getPerso().getEmplacement().getPosX() == 0);
						assertTrue(jeu.getPerso().getEmplacement().getPosY() == 1);
						//Test jeter une arme
						jeu.getPerso().jeterUneArme(1);
						assertTrue(jeu.getPerso().getArmeGauche() == null);
						assertFalse(jeu.getPerso().getArmeDroite() == null);
						jeu.getPerso().jeterUneArme(2);
						assertTrue(jeu.getPerso().getArmeGauche() == null);
						assertTrue(jeu.getPerso().getArmeDroite() == null);
					//Test zombie
						//Apparition zombie
						jeu.zombieApparition();
						assertEquals(2,jeu.getZombiesSurCarte().size());
						//Deplacement zombie
						jeu.getZombiesSurCarte().removeAll(jeu.getZombiesSurCarte());
						jeu.getPerso().setEmplacement(new Position(0,0));
						Zombie zombieTest = new Zombie(new Position(0,1));
						jeu.getZombiesSurCarte().add(zombieTest);
						jeu.tourZombie();
						assertTrue(zombieTest.getEmplacement().getPosX() == 0);
						assertTrue(zombieTest.getEmplacement().getPosY() == 0);
						//Attaque zombie
						jeu.tourZombie();
						assertEquals(2,jeu.getPerso().getPointsDeVie());
					//Test arme
						jeu.getPerso().setArmeGauche(jeu.getArmes().get(1));
						jeu.getPerso().setArmeDroite(jeu.getArmes().get(2));
						assertTrue(jeu.getPerso().getArmeGauche().getNomDeLarme().equals("Arc"));
						assertTrue(jeu.getPerso().getArmeDroite().getNomDeLarme().equals("Epée"));
						
						
						
						
	}

}

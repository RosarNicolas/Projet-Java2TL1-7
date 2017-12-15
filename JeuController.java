package testMVC;

public class JeuController
{
	JeuVue vue;
	Jeu modele;
	public JeuController(Jeu modele)
	{
		this.modele = modele;
	}
	
	public void nouveauPerso(String nom)
	{
		Personnage perso = new Personnage(nom, 1, 2, 3, modele.getCarte().getApparition());
		modele.setPerso(perso);
		modele.updateEntiteListe();
	}
	
	public void tourPerso(int action, int choixArme, String deplacement)
	{
		if(action == 1)
		{
			int fouilleAction = modele.getPerso().fouille(modele.getArmes());
			if(fouilleAction == 1)
			{
				vue.afficheArme1("Vous possédez maintenant l'arme "+ modele.getPerso().getArmeGauche().getNomDeLarme() +" dans la main gauche" );
			}
			else if(fouilleAction == 2)
			{
				vue.afficheArme2("Vous possédez maintenant l'arme "+ modele.getPerso().getArmeDroite().getNomDeLarme() +" dans la main droite" );
			}
			else if(fouilleAction == 0)
			{
				vue.affiche("Vous n'avez plus de place");
			}
			modele.updateEntiteListe();
			//vue.update(null, null); afficher la carte apres avoir fouiller oui ? non ?
			modele.getPerso().setPointsDAction(modele.getPerso().getPointsDAction() - 1);
		}
		else if(action == 2)
		{
			int noArme = 0;
			if(choixArme == 0)
			{
				vue.affiche("Avec quelle arme voulez vous attaquer ?");
				noArme = vue.choixArme();
			}
			
			if(noArme  == 0 )
			{
				return;
			}
			if(noArme == 1 && modele.getPerso().getArmeGauche() == null)
			{
				vue.affiche("Vous n'avez pas d'arme en main gauche (1)");
				return;
			}
			else if(noArme == 2 && modele.getPerso().getArmeDroite() == null)
			{
				vue.affiche("Vous n'avez pas d'arme en main droite (2)");
				return;
			}
			Position endroitDeLattaque = vue.choixAttaque();
			
			modele.updateZombieSurCase(endroitDeLattaque);
			if(modele.getZombiesSurCase().isEmpty())
			{
				vue.affiche("Il n'y pas de zombie à cet endroit");
				return;
			}
			
			if(noArme == 1 )
			{
				if(modele.getPerso().getEmplacement().verifierDistance(endroitDeLattaque,modele.getPerso().getArmeGauche().getPortee()))
				{
					modele.getZombiesSurCarte().removeAll(modele.getZombiesSurCase());
					Zombie cibleAttaque = modele.getZombiesSurCase().pop();
					if(!(modele.getPerso().attaquer(noArme)>=cibleAttaque.getPointsDeVie()))
					{
						modele.getZombiesSurCase().addFirst(cibleAttaque);
						vue.affiche("Vous n'avez pas réussi a tuer le zombie cible !");
					}
					else
					{
						System.out.println("Vous avez tué un zombie !");
						modele.updateEntiteListe();
						//vue.update(null,  null); sinon double affichage de map
					}
					modele.getZombiesSurCarte().addAll(modele.getZombiesSurCase());
				}
				else
				{
					vue.affiche("Vous n'avez pas la portée ou la ligne de vue pour tirer a cet endroit ");
				}
			}
			else 
			{
				if(modele.getPerso().getEmplacement().verifierDistance(endroitDeLattaque,modele.getPerso().getArmeDroite().getPortee()))
				{
					modele.getZombiesSurCarte().removeAll(modele.getZombiesSurCase());
					Zombie cibleAttaque = modele.getZombiesSurCase().pop();
					if(!(modele.getPerso().attaquer(noArme)>=cibleAttaque.getPointsDeVie()))
					{
						modele.getZombiesSurCase().addFirst(cibleAttaque);
						System.out.println("Vous n'avez pas réussi a tuer le zombie cible !");
					}
					else
					{
						System.out.println("Vous avez tué un zombie ! ");
						modele.updateEntiteListe();
						//vue.update(null,  null);
					}
					modele.getZombiesSurCarte().addAll(modele.getZombiesSurCase());
				}
				else
				{
					vue.affiche("Vous n'avez pas la portée ou la ligne de vue pour tirer là !");
				}
			}
			
			modele.getPerso().setPointsDAction(modele.getPerso().getPointsDAction() - 1);
			modele.updateEntiteListe();
			vue.update(null,  null);	
		}
		else if(action == 3)
		{
			if(deplacement.isEmpty())
			{
				deplacerVueConsole();
			}
			else
			{
				deplacerVueGUI(deplacement);
			}
		}
		else if(action == 4)
		{
			modele.updateEntiteListe();
			vue.update(null, null);
			modele.getPerso().setPointsDAction(modele.getPerso().getPointsDAction() - 1);
		}
		else if(action == 5)
		{
			vue.affiche("Vous vous trouvez en " + modele.getPerso().getEmplacement().getPosX() + "," + modele.getPerso().getEmplacement().getPosY()
					  +"\nLa sortie se trouve en " +modele.getCarte().getSortie().getPosX() + "," + modele.getCarte().getSortie().getPosY()
					  +"\nVous avez encore " + modele.getPerso().getPointsDeVie() + " points de vie"
					  +"\nVous pouvez encore effectuer " + modele.getPerso().getPointsDAction() + " actions ce tour-ci"
					  );
			if(modele.getPerso().getArmeDroite() == null && modele.getPerso().getArmeGauche() == null)
			{
				vue.affiche("Vous n'avez pas d'arme");
			}
			else if(modele.getPerso().getArmeDroite() == null)
			{
				vue.affiche("Vous n'avez pas d'arme en main droite (2)"
								  +"\nVotre arme en main gauche (1) est un "+ modele.getPerso().getArmeGauche().getNomDeLarme());
				
			}
			else if(modele.getPerso().getArmeGauche() == null)
			{
				vue.affiche("Vous n'avez pas d'arme en main gauche (1)"
						  +"\nVotre arme en main droite (2) est un "+ modele.getPerso().getArmeDroite().getNomDeLarme());
		
			}
			else
			{
				vue.affiche("Votre arme en main gauche (1) est un "+ modele.getPerso().getArmeGauche().getNomDeLarme()
						  +"\nVotre arme en main droite (2) est un "+ modele.getPerso().getArmeDroite().getNomDeLarme());
			}
			modele.updateEntiteListe();
			vue.update(null, null);
		}
		else if(action == 6)
		{
			if(choixArme == 0)
			{
				jeterUneArmeConsole();
			}
			else
			{
				jeterUneArmeGUI(choixArme);
			}
		}
		
	}
	public void deplacerVueConsole()
	{
		String deplacement  =  vue.deplacement();
		modele.getPerso().deplacer(deplacement, modele.getCarte());
		vue.affiche("Voici votre position actuelle  "+ modele.getPerso().getEmplacement().getPosX()+";"+ modele.getPerso().getEmplacement().getPosY());
		modele.updateEntiteListe();
		vue.update(null, null);
		modele.getPerso().setPointsDAction(modele.getPerso().getPointsDAction() - 1);
	}
	public void deplacerVueGUI(String deplacement)
	{
		modele.getPerso().deplacer(deplacement, modele.getCarte());
		modele.updateEntiteListe();
		vue.update(null, null);
		modele.getPerso().setPointsDAction(modele.getPerso().getPointsDAction() - 1);
	}
	public void jeterUneArmeConsole()
	{
		vue.affiche("quelle arme jeter ? (1 gauche, 2 droite)");
		int armeAjeter = vue.choixArme();
		modele.getPerso().jeterUneArme(armeAjeter);
		modele.updateEntiteListe();
		vue.update(null, null);
	}
	public void jeterUneArmeGUI(int a)
	{
		modele.getPerso().jeterUneArme(a);
		modele.updateEntiteListe();
		vue.update(null, null);
	}
	public void addView(JeuVue vue)
	{
		this.vue = vue;
	}
	
}

package main;
public class JeuController
{
	JeuVue vue;
	Jeu modele;
	public JeuController(Jeu modele)
	{
		this.modele = modele;
	}
		/**
		 * action possible pour le joueur lors de son tour
		 * @param action determine quelle action faire : int 
		 * @param choixArme complement de action = 2 choix de l'arme a jeter 1 = gauche, 2 = droite, 0 = pas cette action qui est effectuée : int
		 * @param deplacement complement de action = 3, indique la direction dans laquelle se deplacer, si mis a "" alors pas cette action effectuée : String
		 * @param endroitAttaque  complement de action = 2, designe l'endroit de l'attaque, si null alors aps cette action effectuée : Position
		 */
	public void tourPerso(int action, int choixArme, String deplacement, Position endroitAttaque)
	{
		if(action == 1)
		{
			int fouilleAction = modele.getPerso().fouille(modele.getArmes());
			if(fouilleAction == 1)
			{
				vue.affiche("Vous possédez maintenant l'arme "+ modele.getPerso().getArmeGauche().getNomDeLarme() +" dans la main gauche");
			}
			else if(fouilleAction == 2)
			{
				vue.affiche("Vous possédez maintenant l'arme "+ modele.getPerso().getArmeDroite().getNomDeLarme() +" dans la main droite");
			}
			else if(fouilleAction == 0)
			{
				vue.affiche("Vous n'avez plus de place");
			}
			modele.updateEntiteListe();
			modele.getPerso().setPointsDAction(modele.getPerso().getPointsDAction() - 1);
			modele.notifier();
			
		}
		else if(action == 2)
		{
			int noArme = 0;
			if(choixArme == 0)
			{
				vue.affiche("Choisissez une arme");
				noArme = vue.choixArme();
				// !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!mettre variables estCombat à false et choixArme à 0
			}
			//reduire a voir ???
			else if(choixArme == 1)
			{
				vue.affiche("Vous attaquez avec l'arme de gauche.");
				noArme = choixArme;
				// !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!mettre variables estCombat à false et choixArme à 0
			}
			else if(choixArme == 2)
			{
				vue.affiche("Vous attaquez avec l'arme de droite.");
				noArme = choixArme;
				// !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!mettre variables estCombat à false et choixArme à 0
			}
			
			
			
			if(noArme == 0 )
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
			
			Position endroitDeLattaque;
			if(endroitAttaque == null)
			{
				endroitDeLattaque = vue.choixAttaque();
			}
			else
			{
				endroitDeLattaque = endroitAttaque;
			}
			
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
						vue.affiche("Vous avez tué un zombie !");
						modele.updateEntiteListe();
					}
					modele.getZombiesSurCarte().addAll(modele.getZombiesSurCase());
				}
				else
				{
					vue.affiche("Vous n'avez pas la portée ou la ligne de vue pour tirer à cet endroit !");
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
						vue.affiche("Vous n'avez pas réussi a tuer le zombie cible !");
					}
					else
					{
						vue.affiche("Vous avez tué un zombie ! ");
						modele.updateEntiteListe();
					}
					modele.getZombiesSurCarte().addAll(modele.getZombiesSurCase());
				}
				else
				{
					vue.affiche("Vous n'avez pas la portée ou la ligne de vue pour tirer à cet endroit !");
				}
			}
			
			modele.getPerso().setPointsDAction(modele.getPerso().getPointsDAction() - 1);
			modele.updateEntiteListe();
			modele.notifier();	
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
			modele.getPerso().setPointsDAction(modele.getPerso().getPointsDAction() - 1);
			modele.notifier();
			
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
			modele.notifier();
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
	
	/**
	 * Permet d'instancier un nouveau Personnage avec le nom choisi.
	 * @param s nom du personnage : String
	 */
	public void setPerso(String s)
	{
		modele.setPerso(new Personnage(s,1 ,3,3,modele.getCarte().getApparition()));
	}
	
	/**
	 * complement de la methode tourPerso deplacement vue console
	 */
	public void deplacerVueConsole()
	{
		String deplacement  =  vue.deplacement();
		modele.updateZombieSurCase(modele.getPerso().getEmplacement());
		if(modele.getPerso().deplacer(deplacement, modele.getCarte(),modele.getZombiesSurCase()))
		{
			vue.affiche("Voici votre position actuelle  "+ modele.getPerso().getEmplacement().getPosX()+";"+ modele.getPerso().getEmplacement().getPosY());
		}
		else
		{
			vue.affiche("Vous ne pouvez pas aller par la");
		}
		modele.updateEntiteListe();
		modele.getPerso().setPointsDAction(modele.getPerso().getPointsDAction() - 1);
		modele.notifier();
		
	}
	/**
	 * complement de la methode tourPerso deplacement vue GUI
	 * @param deplacement : String
	 */
	public void deplacerVueGUI(String deplacement)
	{
		modele.updateZombieSurCase(modele.getPerso().getEmplacement());
		if(!modele.getPerso().deplacer(deplacement, modele.getCarte(),modele.getZombiesSurCase()))
		{
			vue.affiche("Vous ne pouvez pas aller par la");
		}
		else
		{
			vue.affiche("Vous vous êtes déplacer avec succes");
		}
		modele.updateEntiteListe();
		modele.getPerso().setPointsDAction(modele.getPerso().getPointsDAction() - 1);
		modele.notifier();
	}
	/**
	 * complement de la methode tourPerso jeter arme vue console
	 */
	public void jeterUneArmeConsole()
	{
		vue.affiche("Quelle arme désirez-vous jeter ? (1 gauche, 2 droite)");
		int armeAjeter = vue.choixArme();
		modele.getPerso().jeterUneArme(armeAjeter);
		modele.updateEntiteListe();
		modele.notifier();
	}
	/**
	 * complement de la methode jeter arme deplacement vue GUI
	 * @param armeAjeter : int
	 */
	public void jeterUneArmeGUI(int armeAjeter)
	{
		modele.getPerso().jeterUneArme(armeAjeter);
		modele.updateEntiteListe();
		modele.notifier();
		
	}
	/**
	 * methode metant la variable fin du modele a 1 pour notifier les vue que la partie est terminée
	 */
	public void fin()
	{
		modele.setFin(1);
		modele.notifier();
	}
	
	/**
	 * methode du modele MVC apelle la methode update des vues
	 */
	public void addView(JeuVue vue)
	{
		this.vue = vue;
	}
	
}

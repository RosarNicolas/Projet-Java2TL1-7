package testMVC;
//utiliser un tableau pour dessiner la map;
import java.awt.BorderLayout;
import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Observable;
import java.util.Scanner;

import javax.swing.*;
import javax.swing.border.Border;

public class JeuVueGUI extends JeuVue implements ActionListener
{
	JFrame fen;
	private Graphics g;
	
	//JPanel carte = new JPanel();
	
	
	JPanel texte = new JPanel();
	JLabel texteConsole = new JLabel("texteConsole");
	
	JPanel jeu = new JPanel();
	JPanel info = new JPanel();
	JPanel carte = new JPanel();
	JPanel bouton = new JPanel();
	
	JToggleButton boutonJoueur = new JToggleButton();//new ImageIcon("res/joueur.jpg")
	
	JLabel joueur = new JLabel("joueur");
	JLabel arme1 = new JLabel("arme1");
	JLabel arme2 = new JLabel("arme2");
	
	JButton fouiller = new JButton("Fouiller");
	JButton attaquer = new JButton("Attaquer");
	JButton deplacer = new JButton("Deplacer");
	JButton attendre = new JButton("Attendre");
	
	
	
	JLabel player = new JLabel();
	
	//Canvas canvas;
	
	public JeuVueGUI(Jeu modele, JeuController controle)
	{
		super(modele, controle);
		this.modele = modele;
		//MVC
		Border blackline = BorderFactory.createLineBorder(Color.black);
		
		fen = new JFrame("ZOMBICIDE");
		
		//partie gauche
		texte.setLayout(new BoxLayout(texte, BoxLayout.X_AXIS));
		texte.add(texteConsole);
		texte.setBackground(Color.BLUE);
		texte.setPreferredSize(new Dimension(395, 2));
		fen.add(texte, BorderLayout.WEST);
		
		
		//partie droite
		jeu.setBorder(blackline);
		jeu.setLayout(new BoxLayout(jeu, BoxLayout.Y_AXIS));
		jeu.add(carte, BorderLayout.NORTH);
		jeu.add(bouton, BorderLayout.CENTER);
		jeu.add(info, BorderLayout.SOUTH);
		jeu.setPreferredSize(new Dimension(395, 2));
		fen.add(jeu, BorderLayout.EAST);		
		
		//carte
		carte.setLayout(null);
		carte.setPreferredSize(new Dimension(0,600));
		bougeJoueur(10,10);
		genererCarte();
		//boutonJoueur.setEnabled(false);
		//pas meilleur methode
		carte.setBorder(blackline);
		
		
		//info
		info.setLayout(new BoxLayout(info, BoxLayout.X_AXIS));
		info.setBorder(blackline);;
		info.setBackground(Color.RED);
		info.setPreferredSize(new Dimension(0,200));
		info.add(joueur);
		info.add(arme1);
		info.add(arme2);
		//carte.add(boutonJoueur);
		
		bouton.setLayout(new BoxLayout(bouton, BoxLayout.X_AXIS));
		bouton.add(fouiller);
		bouton.add(attaquer);
		bouton.add(deplacer);
		bouton.add(attendre);
		bouton.setBorder(blackline);
		
		
		fen.pack();
		fen.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		fen.setSize(800,1000);
		fen.setLocation(300, 400);
		fen.setVisible(true);
		
		boutonJoueur.addActionListener(this);
		
	}
	
	public void genererCarte()
	{
		String tab [][] = modele.getCarte().getTab();
		int longueur = modele.getCarte().getLongueur();
		int largeur = modele.getCarte().getLargeur();
		String carTrou = modele.getCarte().getCarImpossible();
		String carCoul = modele.getCarte().getCarPossible();
		//
		for(int i = 0 ; i<longueur;i++)
		{
			for(int j = 0 ;j<largeur;j++)
			{
				if(tab[i][j].equals(carTrou))
				{
					DessinerImage image = new DessinerImage("res/troutile.jpg");
					image.setBounds(j*100, i*100, 100, 100);
					carte.add(image);
				}
				else
				{
					DessinerImage image = new DessinerImage("res/couloirHopital.jpg");
					image.setBounds(j*100, i*100, 100, 100);
					carte.add(image);
				}
			}
		}
		
	}
	
	public void bougeJoueur(int largeur, int hauteur)
	{
		boutonJoueur.setBounds(largeur, hauteur, 40, 60);
		carte.add(boutonJoueur);
	}
	
	
	@Override
	public void actionPerformed(ActionEvent e) 
	{
		Object courant = e.getSource();
		if(courant == fouiller)
		{
			
		}
		else if(courant == attaquer)
		{
			
		}
		else if(courant == deplacer)
		{
			
		}
		else if(courant == attendre)
		{
			
		}
		else if(courant == boutonJoueur)
		{
			
		}
		
		/* carte.removeAll();
		 * bougeJoueur(110,110); definir les coordonnee !!!!!!!!!!!
		genererCarte();*/
	}

	@Override
	public void update(Observable arg0, Object arg1) {
		// TODO Auto-generated method stub
		
	}



	@Override
	public void affiche(String string) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public int choixArme() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public Position choixAttaque() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String deplacement() {
		// TODO Auto-generated method stub
		return null;
	}
	
}

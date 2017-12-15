package testMVC;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.util.Observable;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JToggleButton;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;

public class JeuVueGUI extends JeuVue implements ActionListener
{
	
	Boolean estCombat = false;
	int choixArme = 0;
	int debug = 0;
	
	
	JFrame fen;
	
	
	JPanel gauche1 = new JPanel();
	JPanel droit1 = new JPanel();
	
	JPanel debug1 = new JPanel();
	
	JPanel texte = new JPanel();
	JLabel texteConsole = new JLabel("texteConsole");
	
	
	JPanel carte = new JPanel();
	JPanel bouton = new JPanel();
	
	JLabel boutonJoueur = new JLabel(new ImageIcon("res/persoM.png"));
	
	
	JLabel joueur = new JLabel("joueur");
	JLabel arme1 = new JLabel("arme1");
	JLabel arme2 = new JLabel("arme2");
	
	JButton fouiller = new JButton("Fouiller");
	JButton attaquer = new JButton("Attaquer");
	JButton deplacer = new JButton("Deplacer");
	JButton attendre = new JButton("Attendre");
	
	JButton gauche = new JButton("Gauche");
	JButton bas = new JButton("Bas");
	JButton haut = new JButton("Haut");
	JButton droite = new JButton("Droite");
	
	JButton armeGauche1 = new JButton("arme gauche");
	JButton armeDroite2 = new JButton("arme droite");
	JButton jeter = new JButton("Jeter");
	
	//NICO
	private JPanel contentPane;
	private Box top;
	private JPanel panel_1;
	private JPanel panel_2;
	private Box bottom;
	private JPanel panel_3;
	private JPanel panel_4;
	
	Image coeurs;
	Image actions;
	Image armeGauche;
	Image armeDroite;
	
	//Canvas canvas;
	
	public JeuVueGUI(Jeu modele, JeuController controle)
	{
		super(modele, controle);
		//this.modele = modele;
		modele.setPerso(new Personnage("test",1,3,3, modele.getCarte().getApparition()));//MVC
		Border blackline = BorderFactory.createLineBorder(Color.black);
		
		fen = new JFrame("ZOMBICIDE");
		
		//NICO
		contentPane = new Panneau();
		contentPane.setPreferredSize(new Dimension(400,1));
		contentPane.setLayout( new BoxLayout( contentPane, BoxLayout.Y_AXIS) );
		contentPane.setBorder( new EmptyBorder(50, 5, 5, 5) );
		
		// Seconde couche : TOP
		top = Box.createHorizontalBox();
		top.setPreferredSize(new Dimension(0,100));
		top.setOpaque(false);
		
		// TOP gauche
		panel_1 = new JPanel();
		panel_1.setOpaque(false);
		panel_1.setPreferredSize( new Dimension(130,40) );
		
		// TOP droite
		panel_2 = new JPanel();
		panel_2.setOpaque(false);
		panel_2.setPreferredSize( new Dimension(130,40) );
		
		// Seconde couche BOTTOM
		bottom = Box.createHorizontalBox();
		bottom.setPreferredSize(new Dimension(0,150));
		bottom.setOpaque(false);
		
		// BOTTOM gauche
		panel_3 = new JPanel();
		panel_3.setOpaque(false);
		panel_3.setPreferredSize( new Dimension(130,40) );
		
		// BOTTOM droite
		panel_4 = new JPanel();
		panel_4.setOpaque(false);
		panel_4.setPreferredSize( new Dimension(130,40) );
		
		
		try {
			
			int w = modele.getPerso().getPointsDeVie();
			// switch points de vie
			switch( w ) { 
				case 3 : coeurs = ImageIO.read(new File("res/3coeurs.png"));
					break;
				case 2 : coeurs = ImageIO.read(new File("res/2coeurs.png"));
					break;
				case 1 : coeurs = ImageIO.read(new File("res/1coeurs.png"));
					break;
				case 0 : coeurs = ImageIO.read(new File("res/0coeurs.png"));
					break;
			}
			
			int x = modele.getPerso().getPointsDAction();
			// switch points d'action
			switch( x ) {
				case 3 : actions = ImageIO.read(new File("res/3épées.png"));
					break;
				case 2 : actions = ImageIO.read(new File("res/2épées.png"));
					break;
				case 1 : actions = ImageIO.read(new File("res/1épées.png"));
					break;
				case 0 : actions = ImageIO.read(new File("res/0épées.png"));
					break;
			}
			
			/*int IdArmeGauche = modele.getPerso().getArmeGauche().getId();
			int IdArmeDroite = modele.getPerso().getArmeDroite().getId();
			SwitchArmesGD( armeGauche, IdArmeGauche );
			SwitchArmesGD( armeDroite, IdArmeDroite );*/
			
			JLabel vueCoeurs = new JLabel( new ImageIcon( coeurs ));
			panel_1.add( vueCoeurs );
			
			JLabel vueAction = new JLabel( new ImageIcon( actions ));
			panel_2.add( vueAction );
			
			JButton vueArmeGauche = new JButton( new ImageIcon( "res/rien.png" ));
			vueArmeGauche.setBounds(0, 0, 100, 130);
			vueArmeGauche.setContentAreaFilled(false);
            vueArmeGauche.setBorderPainted(false);
			panel_3.add( vueArmeGauche );
			
			JButton vueArmeDroite = new JButton( new ImageIcon( "res/rien.png" ));
			vueArmeDroite.setBounds(0, 0, 100, 130);
			vueArmeDroite.setContentAreaFilled(false);
            vueArmeDroite.setBorderPainted(false);
			panel_4.add( vueArmeDroite );
			
			
		} 
		catch (IOException e) { e.printStackTrace(); }
		
		top.add(panel_1);
		top.add(panel_2);
		
		bottom.add(panel_3);
		bottom.add(panel_4);
		
		contentPane.add(top);
		contentPane.add(bottom);
		
		
		
		//NICO
		
		//partie gauche
		fen.add(gauche1,BorderLayout.WEST);
		fen.add(droit1,BorderLayout.EAST);
		
		gauche1.setLayout(new BoxLayout(gauche1, BoxLayout.Y_AXIS));
		droit1.setLayout(new BoxLayout(droit1, BoxLayout.Y_AXIS));
		
		
		
		//a enlever
		gauche1.setBorder(blackline);
		droit1.setBorder(blackline);
		//a enlever
		 gauche1.add(bouton, BorderLayout.NORTH);
		 gauche1.add(texte,BorderLayout.SOUTH);
		 
		
		 droit1.add(carte, BorderLayout.NORTH);
		 droit1.add(contentPane, BorderLayout.SOUTH);
						
		//carte
		carte.setLayout(null);
		carte.setPreferredSize(new Dimension(0,426));
		setJoueur(modele.getCarte().getApparition().getPosX(),modele.getCarte().getApparition().getPosY());
		genererCarte();
		carte.setBorder(blackline);
		
	
		
		//texte
		texte.setLayout(null);
		texte.setBorder(blackline);
		texte.setBackground(Color.RED);
		texte.setPreferredSize(new Dimension(380,1000));
		texte.add(texteConsole);
		
		bouton.setLayout(new GridLayout(2,4));
		bouton.add(fouiller);
		bouton.add(attaquer);
		bouton.add(deplacer);
		bouton.add(attendre);
		bouton.add(gauche);
		bouton.add(bas);
		bouton.add(haut);
		bouton.add(droite);
		bouton.setPreferredSize(new Dimension(380,200));
		gauche.setEnabled(false);
		droite.setEnabled(false);
		haut.setEnabled(false);
		bas.setEnabled(false);
		bouton.setBorder(blackline);
		
		fen.pack();
		fen.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		fen.setSize(800,1000);
		fen.setLocation(100, 100);
		fen.setVisible(true);
		
		
		deplacer.addActionListener(this);
		attendre.addActionListener(this);
		fouiller.addActionListener(this);
		attaquer.addActionListener(this);
		haut.addActionListener(this);
		bas.addActionListener(this);
		gauche.addActionListener(this);
		droite.addActionListener(this);
		
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
	
	public void setJoueur(int largeur, int hauteur)
	{
		boutonJoueur.setBounds(largeur*100+10, hauteur*100+20, 40, 60);
		carte.add(boutonJoueur);
	}
	
	
	@Override
	public void actionPerformed(ActionEvent e) 
	{
		Object courant = e.getSource();
		if(courant == fouiller)
		{
			controle.tourPerso(1,0,"");
			update(null,null);
		}
		else if(courant == attaquer)
		{
			debug = 1;
		}
		else if(courant == deplacer)
		{
			gauche.setEnabled(true);
			droite.setEnabled(true);
			haut.setEnabled(true);
			bas.setEnabled(true);
			texteConsole.setText("Reussi deplacer");
			
		}
		else if(courant == attendre)
		{
			//controle.tourPerso(4,0,"");
			boutonJoueur.setLocation(0,400);
		}
		else if(courant == gauche)
		{
			controle.tourPerso(3,1,"gauche");
		}
		else if(courant == droite)
		{
			controle.tourPerso(3,1,"droite");
		}
		else if(courant == bas)
		{
			controle.tourPerso(3,1,"bas");
			System.out.println(modele.getPerso().getEmplacement().getPosX() + " " + modele.getPerso().getEmplacement().getPosY());
		}
		else if(courant == haut)
		{
			controle.tourPerso(3,1,"haut");
		}
		else if(courant == jeter)
		{
			armeGauche1.setEnabled(true);
			armeDroite2.setEnabled(true);
		}
		else if(courant == armeGauche)
		{
			if(estCombat)
			{
				controle.tourPerso(2,1,"");
			}
			else
			{
				controle.tourPerso(6,1,"");
				armeGauche1.setEnabled(false);
				armeDroite2.setEnabled(false);
			}
		}
		else if(courant == armeDroite)
		{
			if(estCombat)
			{
				controle.tourPerso(2,2,"");
			}
			else
			{
				controle.tourPerso(6,2,"");
				armeGauche1.setEnabled(false);
						}armeDroite2.setEnabled(false);
	
		}
		
		/* carte.removeAll();
		 * bougeJoueur(110,110); definir les coordonnee !!!!!!!!!!!
		genererCarte();*/
	}

	@Override
	public void update(Observable arg0, Object arg1)
	{
		//carte.removeAll();
		boutonJoueur.setLocation(modele.getPerso().getEmplacement().getPosX()*100+10,modele.getPerso().getEmplacement().getPosY()*100+10);
		//setJoueur(modele.getPerso().getEmplacement().getPosX()*100,modele.getPerso().getEmplacement().getPosY()*100);
		//genererCarte();
		setZombies();
		//genererCarte();
		genererInfo();
		
		
	}
	
	public void setZombies()
	{
		
	}

	public void genererInfo()
	{
		
	}


	@Override
	public void affiche(String string)
	{
		texteConsole.setText(string);
	}

	@Override
	public int choixArme() 
	{
		
		return choixArme;
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

	@Override
	public void afficheArme1(String string) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void afficheArme2(String string) {
		// TODO Auto-generated method stub
		
	}
	
	
	//GUI
	
	public void SwitchArmesGD( Image armeGauche2, int IdArme ) throws IOException {
		switch( IdArme ) {
			case 12 : armeGauche2 = ImageIO.read(new File("res/persoM.png"));
				break;
			case 11 : armeGauche2 = ImageIO.read(new File("res/persoM.png"));
				break;
			case 10 : armeGauche2 = ImageIO.read(new File("res/persoM.png"));
				break;
			case 9 :  armeGauche2 = ImageIO.read(new File("res/persoM.png"));
				break;
			case 8 :  armeGauche2 = ImageIO.read(new File("res/fusil a pompe.png"));
				break;
			case 7 :  armeGauche2 = ImageIO.read(new File("res/batte cloutee.png"));
				break;
			case 6 :  armeGauche2 = ImageIO.read(new File("res/pistolet.png"));
				break;
			case 5 :  armeGauche2 = ImageIO.read(new File("res/Tronconneuse.png"));
				break;
			case 4 :  armeGauche2 = ImageIO.read(new File("res/hache.png"));
				break;
			case 3 :  armeGauche2 = ImageIO.read(new File("res/ak-47.png"));
				break;
			case 2 :  armeGauche2 = ImageIO.read(new File("res/épée.png"));
				break;
			case 1 :  armeGauche2 = ImageIO.read(new File("res/arc.png"));
				break;
			default : armeGauche2 = ImageIO.read(new File("res/persoF.png")); 
		}
	
	}
}

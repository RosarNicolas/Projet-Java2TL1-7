package gui;

import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Image;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JToggleButton;
import javax.swing.border.EmptyBorder;


public class WBgui extends JFrame {
	
	/**
	 * contentPane : JPanel principale.
	 * top : box contenant les 2 premiers éléments ( coeurs et épees (points d'action) )
	 * 		Les coeurs représentent les points de vie du joueur.
	 * 		Les épées représentent les points d'action du joueur.
	 * 	panel_1 : premier élément de top (coeurs)
	 * 	panel_2 : deuxième élément de top ( épées )
	 * bottom : box contenant les armes que possède le joueur.
	 * 		Les 2 panels suivant contiennent les images des armes que le joueur possède.
	 * 		Une arme dans la main gauche, une arme dans la main droite.
	 * 	panel_3 : arme gauche du joueur
	 * 	panel_4 : arme droite du joueur
	 */
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

	public WBgui() {
		
		// Première couche : MAIN
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		contentPane = new panneau();
		
		contentPane.setLayout( new BoxLayout( contentPane, BoxLayout.Y_AXIS) );
		contentPane.setBorder( new EmptyBorder(25, 5, 5, 5) );
		
		// Seconde couche : TOP
		top = Box.createHorizontalBox();
		top.setBorder( new EmptyBorder(0, 0, 35, 0));
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
		bottom.setBorder(new EmptyBorder(5, 5, 5, 5));
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
			
			int w = model.getPerso().getPointsDeVie();
			// switch points de vie
			switch( w ) { 
				case 3 : coeurs = ImageIO.read(new File("src/img/3coeurs.png"));
					break;
				case 2 : coeurs = ImageIO.read(new File("src/img/2coeurs.png"));
					break;
				case 1 : coeurs = ImageIO.read(new File("src/img/1coeurs.png"));
					break;
				case 0 : coeurs = ImageIO.read(new File("src/img/0coeurs.png"));
					break;
			}
			
			int x = model.getPerso().getPointsDAction();
			// switch points d'action
			switch( x ) {
				case 3 : actions = ImageIO.read(new File("src/img/3épées.png"));
					break;
				case 2 : actions = ImageIO.read(new File("src/img/2épées.png"));
					break;
				case 1 : actions = ImageIO.read(new File("src/img/1épées.png"));
					break;
				case 0 : actions = ImageIO.read(new File("src/img/0épées.png"));
					break;
			}
			
			int IdArmeGauche = model.getPerso().getArmeGauche().getId();
			int IdArmeDroite = model.getPerso().getArmeDroite().getId();
			SwitchArmesGD( armeGauche, IdArmeGauche );
			SwitchArmesGD( armeDroite, IdArmeDroite );
			
//		 try {
//			coeurs = ImageIO.read(new File("src/img/3épées.png"));
//			actions = ImageIO.read(new File("src/img/3épées.png"));
//			armeGauche = ImageIO.read(new File("src/img/épée.png"));
//			armeDroite = ImageIO.read(new File("src/img/épée.png"));
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
			JLabel vueCoeurs = new JLabel( new ImageIcon( coeurs ));
			panel_1.add( vueCoeurs );
			
			JLabel vueAction = new JLabel( new ImageIcon( actions ));
			panel_2.add( vueAction );
			
			JToggleButton vueArmeGauche = new JToggleButton( new ImageIcon( armeGauche ));
			vueArmeGauche.setBounds(20, 20, 100, 130);
			panel_3.add( vueArmeGauche );
			
			JToggleButton vueArmeDroite = new JToggleButton( new ImageIcon( armeDroite ));
			panel_4.add( vueArmeDroite );
			vueArmeGauche.setBounds(20, 20, 100, 130);
			
		} 
		catch (IOException e) { e.printStackTrace(); }
		
		top.add(panel_1);
		top.add(panel_2);
		
		bottom.add(panel_3);
		bottom.add(panel_4);
		
		contentPane.add(top);
		contentPane.add(bottom);
		
		setContentPane(contentPane);
	}
			
			// switch armes
			public void SwitchArmesGD( Image armeGauche2, int IdArme ) throws IOException {
			switch( IdArme ) {
				case 12 : armeGauche2 = ImageIO.read(new File("src/img/????.png"));
					break;
				case 11 : armeGauche2 = ImageIO.read(new File("src/img/????.png"));
					break;
				case 10 : armeGauche2 = ImageIO.read(new File("src/img/????.png"));
					break;
				case 9 :  armeGauche2 = ImageIO.read(new File("src/img/ ????.png"));
					break;
				case 8 :  armeGauche2 = ImageIO.read(new File("src/img/fusil a pompe.png"));
					break;
				case 7 :  armeGauche2 = ImageIO.read(new File("src/img/batte cloutee.png"));
					break;
				case 6 :  armeGauche2 = ImageIO.read(new File("src/img/pistolet.png"));
					break;
				case 5 :  armeGauche2 = ImageIO.read(new File("src/img/Tronconneuse.png"));
					break;
				case 4 :  armeGauche2 = ImageIO.read(new File("src/img/hache.png"));
					break;
				case 3 :  armeGauche2 = ImageIO.read(new File("src/img/ak-47.png"));
					break;
				case 2 :  armeGauche2 = ImageIO.read(new File("src/img/épée.png"));
					break;
				case 1 :  armeGauche2 = ImageIO.read(new File("src/img/arc.png"));
					break;
			}
			}

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					WBgui frame = new WBgui();
					frame.setSize( 390, 400 );
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
				
				
			}
		});
	}
	
}

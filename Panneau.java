package testMVC;


import java.awt.Graphics;
import java.awt.Image;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

public class Panneau extends JPanel{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String path;
	
	public Panneau(String path) 
	{
		this.path = path;
	}
	
	public void paintComponent(Graphics g) {
		try {
			Image img = ImageIO.read(new File( path ));
			g.drawImage(img, 0, 0, this.getWidth(), this.getHeight(), this);
		}
		catch( IOException e) {
			e.printStackTrace();
		}
	
	}
	
}
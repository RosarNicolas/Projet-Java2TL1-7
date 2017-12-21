package main;

import java.awt.Canvas;
import java.awt.Graphics;
import java.awt.Image;
//http://www.commentcamarche.net/forum/affich-4463443-java-changement-d-image
class DessinerImage extends Canvas
{

	private static final long serialVersionUID = 1L;
	Image img;

	public DessinerImage(String emplacementImage)
	{
		img = getToolkit().getImage(emplacementImage);
		prepareImage(img, this);
	}
  
	@Override
  public void paint(Graphics g)
    {
		super.paint(g);
	  g.drawImage(img, 0, 0, this);
    }	  
}

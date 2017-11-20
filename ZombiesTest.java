package projet;

import org.junit.Test;

public class ZombiesTest {

	private Zombies test; 
	
	@Test
	public void testWalker() {
		test = new Walker();
		
	System.out.println("Voici un zombie WALKER avec " + test.getPointsDeVie() + " point de vie et "  
			           + test.getPointsDactions() + " point d'action." );	
	}
	
	@Test
	public void testRunner() {
		test = new Runner();
		
	System.out.println("Voici un zombie RUNNER avec " + test.getPointsDeVie() + " point de vie et "  
			           + test.getPointsDactions() + " points d'action." );	
	}
	
	@Test
	public void testFatty() {
		test = new Fatty();
		
	System.out.println("Voici un zombie FATTY avec " + test.getPointsDeVie() + " points de vie et "  
			           + test.getPointsDactions() + " point d'action." );	
	}
	
	@Test
	public void testCrawler() {
		test = new Crawler();
		
	System.out.println("Voici un zombie CRAWLER avec " + test.getPointsDeVie() + " point de vie et "  
			           + test.getPointsDactions() + " point d'action." );	
	}
	

}

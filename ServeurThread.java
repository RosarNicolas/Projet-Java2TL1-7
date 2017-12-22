package main;

import java.io.IOException;

public class ServeurThread extends Thread{

	public ServeurThread() {
		
	}
	
	public void run() {
		
		try {
			Serveur serv = new Serveur();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}
	
}

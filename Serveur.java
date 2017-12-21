package main;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class Serveur {
	
	static ServerSocket s;
	static Socket soc;
	static BufferedReader in;
	static PrintWriter out;
	static final int port = 8082;
	
	Scanner sc ;
	
	public static void closeConnection() throws IOException{
		try {
			in.close();
			out.close();
			soc.close();
		} 
		catch (IOException e) {
			System.out.println("Erreur de fermeture!");
			e.printStackTrace();
		}
	}
	
	public void sendMessage( String str ) {
		out.println(str);
	}
	
	public String waitForMessage(){ // aller rechercher le message dans le in
		try {
			while(true) {
				String strRecept = in.readLine();
				System.out.println( "Serveur : echo réception = " + strRecept);
				
				//String str = sc.nextLine();
				
				if( strRecept.equals("END") ) {
					closeConnection();
				}
				if( strRecept.equals("1") ) {
					sendMessage("Votre objectif : atteindre la sortie");
				}
				else if(strRecept.equals("2"))
				{
					sendMessage("Attention les armes ont des portée différentes");
				}
				else
				{
					sendMessage("Mauvaise entrée.");
				}
			}
			}
			catch ( IOException e ) {
				System.out.println("Erreur pour l'attente de message.");
			}
		return "";
	}
	
	public Serveur() throws IOException {
		
			System.out.println("Serveur :");
			sc = new Scanner(System.in);
			
			s = new ServerSocket(port);
			System.out.println("serveur : attente du client.");
			soc = s.accept();
			System.out.println("serveur : connecté.");
			// arrêt ici tant que pas connection
			
			in = new BufferedReader(
					new InputStreamReader(
							soc.getInputStream()));
			out = new PrintWriter(
					new BufferedWriter(
							new OutputStreamWriter(
									soc.getOutputStream())), true);
		
			waitForMessage();
	}
	
	public static void main(String[] args) throws IOException {
		Serveur serv = new Serveur();
	}
	
}
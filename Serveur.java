package def;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;
/**
 * 
 * @author Nicolas
 *
 */
/**
 * Classe permettant d'instancier un serveur qui
 * renvoie des informations � un client selon les
 * entr�es envoy�es par celui-ci.
 *
 */
public class Serveur {
	
	static ServerSocket s;
	static Socket soc;
	static BufferedReader in;
	static PrintWriter out;
	static final int port = 8082;
	
	Scanner sc ;
	
	/**
	 * M�thode envoyant dans le buffer out(vers client) un String.
	 * @param str : string mit dans le buffer out 
	 * pour l'envoyer au client.
	 */
	public void sendMessage( String str ) {
		out.println(str);
	}
	
	/**
	 * M�thode effectuant une boucle pour attendre
	 * des messages en provenance d'un client. Le serveur
	 * envoie une reponse au client selon le message re�u..
	 */
	public String waitForMessage(){
		try {
			while(true) {
				String strRecept = in.readLine();
				System.out.println( "Serveur : echo r�ception = " + strRecept);
				
				if( strRecept.equals("1") ) {
					sendMessage("Votre objectif : atteindre la sortie.");
				}
				else if(strRecept.equals("2"))
				{
					sendMessage("Attention les armes ont des port�e diff�rentes!");
				}
				else if(strRecept.equals("3"))
				{
					sendMessage("Chaque ennemis peut infliger 1 de d�g�t par tour.");
				}
				else
				{
					sendMessage("Mauvaise entr�e.");
				}
			}
			}
			catch ( IOException e ) {
				System.out.println("Erreur pour l'attente de message.");
			}
		return "";
	}
	
	/**
	 * Constructeur de la classe Serveur.
	 * Il instancie les buffer n�cessaire � la communication
	 * et d�clenche la m�thode waitForMessage().
	 * @throws IOException 
	 */
	public Serveur() throws IOException {
		
			System.out.println("Serveur :");
			sc = new Scanner(System.in);
		
			s = new ServerSocket(port);
			System.out.println("serveur : attente du client.");
			soc = s.accept();
			
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
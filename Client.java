package main;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;
/**
 * 
 * @author Nicolas
 *
 */
/**
 * Classe permettant d'instancier un client
 * qui se connecte à un serveur et qui communique
 * avec celui-ci pour obtenir des informations.
 */
public class Client {
	
	JeuVueGUI vueGUI;
	static Socket soc;
	static BufferedReader in;
	static PrintWriter out;
	static int port = 8082;
	static Scanner sc;
	
	/**
	 * Constructeur auquel la vue GUI est passée
	 * en paramètre pour afficher les messages reçus
	 * du serveur dans l'interface GUI.
	 * @param vueGUI : interface graphique.
	 * @throws IOException
	 */
	public Client(JeuVueGUI vueGUI) throws IOException 
	{
		this.vueGUI = vueGUI;
		try {
			connection(port, "localhost");
		}
		catch(IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 
	 * @param port : numéro de port auquel le client se connecte sur le serveur.
	 * @param addr : addresse du serveur auquel le client se connecte.
	 * @throws UnknownHostException
	 * @throws IOException
	 */
	public void connection( int port, String addr ) throws UnknownHostException, IOException {
		soc = new Socket(addr, port);
		
		in = new BufferedReader(
				new InputStreamReader(
						soc.getInputStream()));
		out = new PrintWriter(
				new BufferedWriter(
						new OutputStreamWriter(
								soc.getOutputStream())),true);
	}
	

	/**
	 * Méthode envoyant dans le buffer out (vers serveur) un String.
	 * @param str : string mit dans le buffer out 
	 * pour l'envoyer au serveur.
	 */
	public void sendMessage( String str ) {
		out.println(str);
	}
	
	/**
	 * Méthode qui envoie un message str au serveur et attend
	 * une réponse pour l'afficher dans l'interface graphique.
	 * @param str : message envoyé au serveur.
	 */
	public String waitForMessage(String str) { 
		try {
				sendMessage(str);
				String strRecept = in.readLine();
				vueGUI.chat(strRecept);
		}
		catch ( IOException e ) {
			e.printStackTrace();
		}
		return "";
	}
	
}
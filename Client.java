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

public class Client {
	
	JeuVueGUI vueGUI;
	static Socket soc;
	static BufferedReader in;
	static PrintWriter out;
	
	static Scanner sc;
	
	public Client(JeuVueGUI vueGUI) throws IOException 
	{
		this.vueGUI = vueGUI;
		//System.out.println("Client :");
		try {
			connection(8082, "localhost");
		}
		catch(IOException e) {
			//System.out.println("Erreur d'adresse de socket.");
		}
	}
	
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
	
	public void connection( int port, String addr ) throws UnknownHostException, IOException {
		soc = new Socket(addr, port);
		
		in = new BufferedReader(
				new InputStreamReader(
						soc.getInputStream()));
		out = new PrintWriter(
				new BufferedWriter(
						new OutputStreamWriter(
								soc.getOutputStream())),true);
								// true => flush qui vide buffeur écriture
	}
	

	public void sendMessage( String str ) {
		out.println(str);
	}
	
	public String waitForMessage(String str) { // aller rechercher le message dans le in
		try {
				//String str = sc.nextLine();
				
				if( str.equals("END") ) 
				{
					closeConnection();
				}
				sendMessage(str);
				String strRecept = in.readLine();
				vueGUI.chat(strRecept);
		}
		catch ( IOException e ) {
			//System.out.println("Erreur pour l'attente de message.");
		}
		return "";
	}
	
//	public static void main(String[] args) throws IOException {
//		Client c = new Client();
//	}
	
}
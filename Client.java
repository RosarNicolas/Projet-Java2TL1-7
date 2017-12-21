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
	static int port = 8082;
	static Scanner sc;
	
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
	

	public void sendMessage( String str ) {
		out.println(str);
	}
	
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
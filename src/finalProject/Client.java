package finalProject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.ArrayList;

public class Client {
	//connect to master
	//submit jobs
	
	//job has a type and ID
	
	/**
	 * regular client
	 *NEEDS:
	 *Socket clientSocket = new Socket(hostName, portNumber);
	 */
	
	// Hardcode in IP and Port here if required
	public static void main (String[] args) {
			args = new String[] { "127.0.0.1", "30121" };

			if (args.length != 2) {
				System.err.println("Usage: java EchoClient <host name> <port number>");
				System.exit(1);
			}

			String hostName = args[0];
			int portNumber = Integer.parseInt(args[1]);

			try (Socket clientSocket = new Socket(hostName, portNumber); 
					PrintWriter requestWriter = new PrintWriter(clientSocket.getOutputStream(), true); //talks to server
					BufferedReader responseReader = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));) //reads from server
			{
				
					//actually do things

			} catch (UnknownHostException e) {
				System.err.println("Don't know about host " + hostName);
				System.exit(1);
			} catch (IOException e) {
				System.err.println("Couldn't get I/O for the connection to " + hostName);
				System.exit(1);
			}
		}
	}



package finalProject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class Slave {
	//take jobs
	//sleep
	//alert when done
	
	
	//server socket
	//client socket
	
	/**
	 * server to master
	 * NEEDS:
	 *Socket masterclient = serverSocket.accept();
		 *ServerSocket serverSocket = new ServerSocket(port#)
	 */
	
	public static void main(String[] args) throws IOException {

		// Hard code in port number if necessary:
		args = new String[] { "30121" };

		if (args.length != 1) {
			System.err.println("Usage: java EchoServer <port number>");
			System.exit(1);
		}

		int portNumber = Integer.parseInt(args[0]);
		try (ServerSocket serverSocket = new ServerSocket(Integer.parseInt(args[0]));
				Socket clientSocket = serverSocket.accept();
				PrintWriter responseWriter = new PrintWriter(clientSocket.getOutputStream(), true); //print to master?
				BufferedReader requestReader = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));) {//read from master? {
			//do stuff here
			
			//get job- array list?
			//sleep for a certain amount of time depending on if its job A or B
			//after sleeping, tell master that job was completed
			//go on to the next job
			
			//how will master be able to check what jobs slave has to do to know which job to sent where...?
		} catch (IOException e) {
			System.out.println(
					"Exception caught when trying to listen on port " + portNumber + " or listening for a connection");
			System.out.println(e.getMessage());
		}

	}
}

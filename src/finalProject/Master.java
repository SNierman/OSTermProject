package finalProject;

import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class Master {
	//take in jobs
	//assign it to slave
	//alert client when job is done
	public static void main(String[] args) {
		
		ArrayList<String> jobsFromClient = new ArrayList<String>();
		args = new String[] { "30121" };
		
		MasterFromClientThread masterFromClient = new MasterFromClientThread(jobsFromClient, args);

		masterFromClient.start();
		}
	
	//server to clients
		//Socket clientSocket1 = serverSocket.accept();
		//Socket clientSocket2 = serverSocket.accept();
	
	
	//client to both slaves
		//Socket clientSocket = new Socket(hostName, portNumber);
	
	/**
	 * The master is a server to the two clients.
	 * NEEDS:
	 * //Socket clientSocket1 = serverSocket.accept();
		//Socket clientSocket2 = serverSocket.accept();
		 *ServerSocket serverSocket = new ServerSocket(port#)
		 * 
	*  The master is a Client to Slave A and B
	*  NEEDS:
	*     Socket clientSocket = new Socket(hostName, portNumber);
	*      Socket clientSocket = new Socket(hostName, portNumber);
	 */
	
}

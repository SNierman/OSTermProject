package finalProject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class Master {
	//take in jobs
	//assign it to slave
	//alert client when job is done
	public static void main(String[] args) {
		
		args = new String[] { "30121"};
		
		while(true) {
		try (ServerSocket serverSocket = new ServerSocket(Integer.parseInt(args[0]));
				Socket clientSocket = serverSocket.accept();
				
				PrintWriter writeToClient = new PrintWriter(clientSocket.getOutputStream(), true);
				BufferedReader inFromClient = new BufferedReader(
						new InputStreamReader(clientSocket.getInputStream()));) {
		
		ArrayList<String> jobsFromClient = new ArrayList<String>();
		WorkTimeCounter counterA = new WorkTimeCounter(Type.A);
		WorkTimeCounter counterB = new WorkTimeCounter(Type.B);
		
		ArrayList<String> completedJobs = new ArrayList<String>();
		
		MasterFromClientThread masterFromClient = new MasterFromClientThread(jobsFromClient, inFromClient);
		//MasterToSlave toSlaves = new MasterToSlave(args, counterA, counterB, jobsFromClient);
		//MasterFromSlaves fromSlaves = new MasterFromSlaves(args, completedJobs);
		
		//ArrayLists created in master to determine who to send jobs to
		//MasterToSlave toSlaveA = new MasterToSlave(jobsToSendToSlaveA);
		//MasterToSlave toSlaveB = new MasterToSlave(jobsToSendToSlaveB);
		masterFromClient.start();

		//toSlaves.start();
		//fromSlaves.start();
		
	
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
	
	/**class master
	 *  + class Seconds private amtOFSeconds, String type.
	 *  + slave gets a job if its type +2 , else +10. Same for removal
	 *  
	 *  + array list jobs- same one as masterFromClient
	 * 
	 * mastertToslave tosalveA = new (amntOfSondsvaraible, masterFrom Client, A)
	 * 					--clinet connection tath it will set up will be to a slave A
	 * masterToSlave toslaveB= new (amntOfSondsvaraible, masterFrom Client, B)
	 * 					--cleint connection to a different slave B
	 * 
	 * fromSalve slaveA = new (sceonds, A, jobID/type(string))--> 
	 * 
	 * masterToclient(1)-->tells the client the job completed.
	
	 * class masterToSlave
	 * 
	 * the other option:
	 * masterToSlaves = new(arrayList)
	 * 
	 * connection to both slaves
	 * 
	 * class Slave
	 * 	thread slave to maste--> sends the master the job that completed
	 * 
	 * */

	} catch (IOException e) {
		System.out.println(
				"Exception caught when trying to listen on port or listening for a connection");
		System.out.println(e.getMessage());
	}
	}	}
}

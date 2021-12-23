package finalProject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class MasterFromSlaves extends Thread {

	private String [] args;
	private ArrayList<String> completedJobs;
	
	public MasterFromSlaves(String [] args, ArrayList<String> completedJobs) {
		
		this.args = args;
		this.completedJobs = completedJobs;
		
	}
	
	@Override 
	public void run() {
		
		try(ServerSocket serverSocket = new ServerSocket(Integer.parseInt("65534"));
				Socket clientSocket = serverSocket.accept();
				
				//The master creates a server socket that connects to the clients
				//Socket clientSocket = new Socket(args[0], Integer.parseInt("30122"));
	
				//socket to connect to the client
				//Socket clientSocket = serverSocket.accept();
				//reader/writer from the client
				//PrintWriter outToClient = new PrintWriter(clientSocket.getOutputStream(), true);
				BufferedReader inFromSlaves = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
			){
			
			//maybe add in to check if the threads are alive
			String currJob;
			while((currJob = inFromSlaves.readLine()) != null) {
				
				System.out.println("Master Recieved Completed Job: " + currJob);
				completedJobs.add(currJob);
				
			}
		}
		
		
		
			 catch (IOException e) {
					System.out.println("Exception caught when trying to listen on port or listening for a connection MFS");
					System.out.println(e.getMessage());
				}
	}
	
	
}

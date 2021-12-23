package finalProject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class MasterFromClientThread extends Thread {

	private String[] args;
	private ArrayList<String> jobsFromClient;
	public MasterFromClientThread(ArrayList<String> jobsFromClient, String[] args) {
		this.jobsFromClient = jobsFromClient;
		this.args = args;
	}

	@Override
	public void run() {
		try(//The master creates a server socket that connects to the clients
				ServerSocket serverSocket = new ServerSocket(Integer.parseInt(args[1]));
	
				//socket to connect to the client
				Socket clientSocket = serverSocket.accept();
				//reader/writer from the client
				//PrintWriter outToClient = new PrintWriter(clientSocket.getOutputStream(), true);
				BufferedReader inFromClient = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
			){
		//initiate convo with the client
		//outToClient.println("");
		/*
		ArrayList<String> jobsFromClient = new ArrayList<String>();
		String job;
		String completeJob;
		
		//read the jobs from the client
		while((job = inFromClient.readLine()) != null) {
			
			
			if(job.equalsIgnoreCase("Done")) {
				break;
			}
			
			//add to ArrayList of jobs
			jobsFromClient.add(job);
				
		}*/
			String job;
			while ((job = inFromClient.readLine()) != null) {
				System.out.println("Master received: " + job);
				jobsFromClient.add(job);
			}
	}
		catch (IOException e) {
			System.out.println(
					"Exception caught when trying to listen on port or listening for a connection. MASTER FROM CLIENT");
			System.out.println(e.getMessage());
		}		}
}

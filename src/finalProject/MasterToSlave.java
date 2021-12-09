package finalProject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class MasterToSlave extends Thread {
	
	//we will try to make one thread that will send to both slaves
	
	private final String[] args;
	private ArrayList<String> jobsFromClient = new ArrayList<String>();
	private WorkTimeCounter counterA;
	private WorkTimeCounter counterB;
	//private ArrayList<String> jobsSentToSlave;
	//private Type type;
	
	public MasterToSlave(String[] args, WorkTimeCounter counterA, WorkTimeCounter counterB, ArrayList<String> jobsFromClient) {
		this.args = args;
		this.jobsFromClient = jobsFromClient;
		this.counterA = counterA;
		this.counterB = counterB;
		//this.type = type;
		
	}
	
	public void run() {
		try (ServerSocket serverSocket = new ServerSocket(Integer.parseInt(args[0]));
				Socket clientSocket = serverSocket.accept();
				PrintWriter responseWriter = new PrintWriter(clientSocket.getOutputStream(), true);) {//print to master
			
			String currJob; 
			int timeDifferenceFromOtherSlave;
			while (MasterFromClientThread.currentThread().isAlive() || !jobsFromClient.isEmpty()) {
					currJob = jobsFromClient.get(0);
					
					//if(currJob.substring(0,1).equals(this.type.toString())) {
						
						timeDifferenceFromOtherSlave = Math.abs(counterA.getWorkTimeRemaining() - counterB.getWorkTimeRemaining());
						
						
					}
				
				
			}
			
			
		 catch (IOException e) {
			System.out.println(
					"Exception caught when trying to listen on port or listening for a connection");
			System.out.println(e.getMessage());
		} 
			
	}

}

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
	private BufferedReader inFromSlave;
	
	public MasterFromSlaves( BufferedReader inFromSlave, ArrayList<String> completedJobs) {
		
		this.inFromSlave = inFromSlave;

		this.completedJobs = completedJobs;
		
	}
	
	@Override 
	public void run() {
		
			
			//maybe add in to check if the threads are alive
			String currJob;
			try {
			while(ClientToMasterThread.currentThread().isAlive())	{
				while((currJob = inFromSlave.readLine()) != null) {
					
					System.out.println("Master Recieved Completed Job: " + currJob);
					completedJobs.add(currJob);
					
				}
			}
			
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	
	
	
}

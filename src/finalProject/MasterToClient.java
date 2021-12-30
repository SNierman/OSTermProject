package finalProject;

import java.io.PrintWriter;
import java.util.ArrayList;

public class MasterToClient extends Thread {
	
	private PrintWriter writeToClient;
	private ArrayList<String> completedJobs;
	
	public MasterToClient(PrintWriter writeToClient, ArrayList<String> completedJobs) {
		this.writeToClient = writeToClient;
		this.completedJobs = completedJobs;
	}
	
	@Override
	public void run() {
		
		//keep up connection still slaves still have
		while(MasterFromSlave.currentThread().isAlive()) {
			
			while (!completedJobs.isEmpty()) {
				
				
				writeToClient.println(completedJobs.get(0));
				System.out.println("Master returning completed job " + completedJobs.get(0));
				
				synchronized(completedJobs) {
					completedJobs.remove(0);
				}
			}
		}
		//Tell client all jobs are complete
		writeToClient.println("All jobs completed. Have a great day!");
	}

}

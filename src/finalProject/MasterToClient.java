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
		while(MasterFromSlaves.currentThread().isAlive()) {
			while (!completedJobs.isEmpty()) {
				writeToClient.println(completedJobs.get(0));
				System.out.println("Master returning completed job " + completedJobs.get(0));
				synchronized(completedJobs) {
					completedJobs.remove(0);
				}
			}
			writeToClient.println("All jobs completed. Have a great day!");
		}
	}

}

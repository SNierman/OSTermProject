package finalProject;

import java.io.PrintWriter;
import java.util.ArrayList;

public class ClientToMaster extends Thread {

	ArrayList<String> jobs;
	private PrintWriter writeToMaster;

	public ClientToMaster(ArrayList<String> jobs, PrintWriter writeToMaster) {
		this.jobs = jobs;
		this.writeToMaster = writeToMaster;
	}

	@Override
	public void run() {

		
		//keep the thread alive while user thread is still alive and may send more jobs
		while (UserToClient.currentThread().isAlive()) {
			
			//inner loop so only checks outer condition if currently no more jobs to send
			while (jobs.size() > 0) {
				String currJob = jobs.get(0);
				System.out.println("Sending over job " + currJob);
				writeToMaster.println(currJob);
				
				// when a job from user is sent, 
				// client will remove it from the arrayList as to not send the same job twice
				synchronized(jobs) {
					jobs.remove(currJob);
				}
			}

		}

	}
}

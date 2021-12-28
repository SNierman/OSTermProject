package finalProject;

import java.io.PrintWriter;
import java.util.ArrayList;

public class ClientToMasterThread extends Thread {

	ArrayList<String> jobs;
	private PrintWriter writeToMaster;

	public ClientToMasterThread(ArrayList<String> jobs, PrintWriter writeToMaster) {
		this.jobs = jobs;
		this.writeToMaster = writeToMaster;
	}

	@Override
	public void run() {

		// while jobs are greater than 0- when the job gets finished, user will remove
		// it from the arrayList

		// and while the user is not done giving jobs because he could still give jobs
		// even if they are all completed
		while (UserToClientThread.currentThread().isAlive()) {
			while (jobs.size() > 0) {
				String currJob = jobs.get(0);
				System.out.println("Sending over job " + currJob);
				writeToMaster.println(currJob);
				synchronized(jobs) {
					jobs.remove(currJob);
				}
			}

		}

	}
}

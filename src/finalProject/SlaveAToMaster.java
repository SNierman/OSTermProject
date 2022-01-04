package finalProject;

import java.io.PrintWriter;
import java.util.ArrayList;

public class SlaveAToMaster extends Thread {

	private final Type TYPE = Type.A;
	private PrintWriter aWriteToMaster;
	private ArrayList<String> AJobs;

	public SlaveAToMaster(PrintWriter aWriteToMaster, ArrayList<String> AJobs) {

		this.aWriteToMaster = aWriteToMaster;
		this.AJobs = AJobs;

	}

	@Override
	public void run() {

		String currJob;

		// thread continues while master sends over jobs
		while (MasterToSlave.currentThread().isAlive()) {
			while (!AJobs.isEmpty()) {

				currJob = AJobs.get(0);
				String currJobString = currJob.substring(1);
				
				// decide how long to work on job based on job type
				try {
					if (Type.valueOf(currJob.substring(1, 2).toUpperCase()).equals(TYPE)) {
						Thread.sleep(2000);
					}

					else {
						Thread.sleep(10000);
					}

				} catch (InterruptedException e) {
					System.out.println(e.getMessage());
				}

				// remove jobs so does not send same job twice
				synchronized (AJobs) {
					AJobs.remove(0);
				}

				System.out.println("Slave A completed job: " + currJobString);
				aWriteToMaster.println("A" + currJob);
			}

		}

	}
}

package finalProject;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;

public class MasterFromSlave extends Thread {

	private ArrayList<String> completedJobs;
	private BufferedReader inFromSlave;
	private WorkTimeCounter counterA;
	private WorkTimeCounter counterB;

	public MasterFromSlave(BufferedReader inFromSlave, ArrayList<String> completedJobs, WorkTimeCounter counterA,
			WorkTimeCounter counterB) {

		this.inFromSlave = inFromSlave;
		this.completedJobs = completedJobs;
		this.counterA = counterA;
		this.counterB = counterB;

	}

	@Override
	public void run() {

		String currJob;
		Type slave;
		try {

			// keep up connection while client may still send jobs
			while (ClientToMaster.currentThread().isAlive()) {

				while ((currJob = inFromSlave.readLine()) != null) {

					slave = Type.valueOf(currJob.substring(0, 1));
					currJob = currJob.substring(1);

					//adjust work time based on slave and job type
					if (slave.equals(Type.A)) {
						synchronized (counterA) {
							if (currJob.substring(0, 1).equals(Type.A.toString())) {

								counterA.removeOptimalJob();
							}

							else {
								counterA.removeNonOptimalJob();
							}
						}
					} else {
						synchronized (counterB) {
							if (currJob.substring(0, 1).equals(Type.B.toString())) {

								counterB.removeOptimalJob();
							}

							else {
								counterB.removeNonOptimalJob();
							}
						}
					}

					// add completed jobs to global list to send back to client
					synchronized (completedJobs) {
						completedJobs.add(currJob);
					}

					System.out.println("Master Recieved Completed Job: " + currJob);
				}
			}

		} catch (IOException e) {

			System.out.println(e.getMessage());
		}
	}

}

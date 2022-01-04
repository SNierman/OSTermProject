package finalProject;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;

public class MasterFromSlave extends Thread {

	private ArrayList<String> completedJobsClient1;
	ArrayList<String> completedJobsClient2;
	private BufferedReader inFromSlave;
	private WorkTimeCounter counterA;
	private WorkTimeCounter counterB;

	public MasterFromSlave(BufferedReader inFromSlave, ArrayList<String> completedJobsClient1,
			ArrayList<String> completedJobsClient2, WorkTimeCounter counterA, WorkTimeCounter counterB) {

		this.inFromSlave = inFromSlave;
		this.completedJobsClient1 = completedJobsClient1;
		this.completedJobsClient2 = completedJobsClient2;
		this.counterA = counterA;
		this.counterB = counterB;

	}

	@Override
	public void run() {

		String currJob;
		Type slave;
		String currJobString;
		try {

			// keep up connection while client may still send jobs
			while (ClientToMaster.currentThread().isAlive()) {

				while ((currJob = inFromSlave.readLine()) != null) {

					slave = Type.valueOf(currJob.substring(0, 1));
					currJob = currJob.substring(1);
					currJobString = currJob.substring(1);

					// adjust work time based on slave and job type
					if (slave.equals(Type.A)) {

						if (currJob.substring(1, 2).equals(Type.A.toString())) {
							synchronized (counterA) {
								counterA.removeOptimalJob();
							}
						}

						else {
							synchronized (counterA) {
								counterA.removeNonOptimalJob();
							}
						}

					} else {

						if (currJob.substring(1, 2).equals(Type.B.toString())) {
							synchronized (counterB) {
								counterB.removeOptimalJob();
							}
						}

						else {
							synchronized (counterB) {
								counterB.removeNonOptimalJob();
							}
						}
					}

					// add completed jobs to the right global list to send back to client
					if (Integer.parseInt(currJob.substring(0, 1)) == 1) {
						synchronized (completedJobsClient1) {
							completedJobsClient1.add(currJob);
						}
					} else {
						synchronized (completedJobsClient2) {
							completedJobsClient2.add(currJob);
						}
					}
					System.out.println("Master received completed job: " + currJobString);
				}
			}

		} catch (IOException e) {

			System.out.println(e.getMessage());
		}
	}

}

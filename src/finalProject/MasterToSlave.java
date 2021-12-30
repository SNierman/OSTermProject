package finalProject;

import java.io.PrintWriter;
import java.util.ArrayList;

//one thread connects to both slaves
public class MasterToSlave extends Thread {

	private PrintWriter writeToSlaveA;
	private PrintWriter writeToSlaveB;
	private ArrayList<String> jobsFromClient;
	private WorkTimeCounter counterA;
	private WorkTimeCounter counterB;

	public MasterToSlave(PrintWriter writeToSlaveA, PrintWriter writeToSlaveB, WorkTimeCounter counterA,
			WorkTimeCounter counterB, ArrayList<String> jobsFromClient) {
		this.writeToSlaveA = writeToSlaveA;
		this.writeToSlaveB = writeToSlaveB;
		this.jobsFromClient = jobsFromClient;
		this.counterA = counterA;
		this.counterB = counterB;

	}

	@Override
	public void run() {

		String currJob;
		int timeDifferenceFromOtherSlave;
		Type currJobType;

		// keep connection while master may still receive a job from client
		while (MasterFromClient.currentThread().isAlive()) {

			while (!jobsFromClient.isEmpty()) {
				
				currJob = jobsFromClient.get(0);
				currJobType = Type.valueOf(currJob.substring(0, 1).toUpperCase());
				
				//calculate the time difference of work time between the slaves
				timeDifferenceFromOtherSlave = counterA.getWorkTimeRemaining() - counterB.getWorkTimeRemaining();

				//Calculate where to send the job:
				//there is an 8 second time difference between the optimal and non optimal 
				//slave completing a job so 8 second lag can determine efficiency 
				
				
				// if slave A's work time is greater than B by over 8 seconds, send the job to B
				// if slave B's work time is greater than A by over 8 seconds, send the job to A
				// if difference is less or equal to 8, check the job type and send to correct slave
				if (currJobType.equals(Type.A)) {

					if (timeDifferenceFromOtherSlave > 8) {

						System.out.println("Sending" + currJob + "to slave B");
						writeToSlaveB.println(currJob);
						counterB.addNonOptimalJob();

					}

					else {
						System.out.println("Sending" + currJob + "to slave A");
						writeToSlaveA.println(currJob);
						counterA.addOptimalJob();
					}

				}

				else {

					if (timeDifferenceFromOtherSlave < -8) {
						System.out.println("Sending" + currJob + "to slave A");
						writeToSlaveA.println(currJob);
						counterA.addNonOptimalJob();
					}

					else {
						System.out.println("Sending" + currJob + "to slave B");
						writeToSlaveB.println(currJob);
						counterB.addOptimalJob();

					}

				}
				
				//remove the job from the list once send the job to a slave
				synchronized (jobsFromClient) {
					jobsFromClient.remove(0);
				}

			}

		}
	}
}

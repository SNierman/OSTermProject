package finalProject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class MasterToSlave extends Thread {

	// we will try to make one thread that will send to both slaves

	private PrintWriter writeToSlaveA;
	private PrintWriter writeToSlaveB;
	private ArrayList<String> jobsFromClient;
	private WorkTimeCounter counterA;
	private WorkTimeCounter counterB;
	// private ArrayList<String> jobsSentToSlave;

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
		while (MasterFromClientThread.currentThread().isAlive()) {
			while(!jobsFromClient.isEmpty()) {
			currJob = jobsFromClient.get(0);
			currJobType = Type.valueOf(currJob.substring(0, 1).toUpperCase());
			timeDifferenceFromOtherSlave = counterA.getWorkTimeRemaining() - counterB.getWorkTimeRemaining();

			// if A is greater than B, it has more work to do. If it is behind by > 9
			// seconds, send the job to B
			// if neg, and greater than -9, send job to A
			// both less than 9, check the job type.

			
			if (currJobType.equals(Type.A)) {

				if (timeDifferenceFromOtherSlave >= 9) {

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

				if (timeDifferenceFromOtherSlave <= -9) {
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
			synchronized (jobsFromClient) {
				jobsFromClient.remove(0);
			}

		}

	}
	}
}

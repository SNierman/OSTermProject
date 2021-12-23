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

	private final String[] args;
	private ArrayList<String> jobsFromClient;
	private WorkTimeCounter counterA;
	private WorkTimeCounter counterB;
	// private ArrayList<String> jobsSentToSlave;

	public MasterToSlave(String[] args, WorkTimeCounter counterA, WorkTimeCounter counterB,
			ArrayList<String> jobsFromClient) {
		this.args = args;
		this.jobsFromClient = jobsFromClient;
		this.counterA = counterA;
		this.counterB = counterB;

	}

	@Override
	public void run() {
		try (Socket clientSocket = new Socket(args[0], Integer.parseInt("65534"));
				//Socket clientSocket = serverSocket.accept();
				PrintWriter toSlaveA = new PrintWriter(clientSocket.getOutputStream(), true);
				PrintWriter toSlaveB = new PrintWriter(clientSocket.getOutputStream(), true);) {

			String currJob;
			int timeDifferenceFromOtherSlave;
			Type currJobType;
			while (MasterFromClientThread.currentThread().isAlive() && !jobsFromClient.isEmpty()) {

				currJob = jobsFromClient.get(0);
				currJobType = Type.valueOf(currJob.substring(0,1));
				timeDifferenceFromOtherSlave = counterA.getWorkTimeRemaining() - counterB.getWorkTimeRemaining();

				// if A is greater than B, it has more work to do. If it is behind by > 9
				// seconds, send the job to B
				// if neg, and greater than -9, send job to A
				// both less than 9, check the job type.
				
				System.out.println("Sending" + currJob + "to slave A");
				toSlaveA.println(currJob);
/**
				if(currJobType.equals(Type.A)) {
					
					if (timeDifferenceFromOtherSlave >= 9) {

						toSlaveB.println(currJob);
						counterB.addNonOptimalJob();
						
					}
					
					else {
						toSlaveA.println(currJob);
						counterA.addOptimalJob();
					}
					
				}
				
				else {
					
					if (timeDifferenceFromOtherSlave <= -9) {

						toSlaveA.println(currJob);
						counterA.addNonOptimalJob();
					}
					
					else {
						
						toSlaveB.println(currJob);
						counterB.addOptimalJob();
						
					}
					
				}
				jobsFromClient.remove(0);
		*/	}

		}

		catch (IOException e) {
			System.out.println("Exception caught when trying to listen on port or listening for a connection MASTER TO SLAVE");
			System.out.println(e.getMessage());
		}

	}

}

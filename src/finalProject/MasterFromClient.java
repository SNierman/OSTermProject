package finalProject;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;

public class MasterFromClient extends Thread {

	private BufferedReader inFromClient;
	private ArrayList<String> jobsFromClient;
	private int clientNum;

	public MasterFromClient(ArrayList<String> jobsFromClient, BufferedReader inFromClient, int clientNum) {
		this.jobsFromClient = jobsFromClient;
		this.inFromClient = inFromClient;
		this.clientNum = clientNum;
	}

	@Override
	public void run() {

		String job;
		try {
			
			//keep thread alive while client may send more jobs
			while (ClientToMaster.currentThread().isAlive()) {
				
				System.out.println("Master connected to a client");

				while ((job = inFromClient.readLine()) != null) {

					System.out.println("Master received from client: " + job);

					//ensure do not add the word 'done' as a job to send to a slave
					if (job.equalsIgnoreCase("done")) {
						break;
					}
					
					synchronized (jobsFromClient) {
						
						// add to global ArrayList of jobs
						jobsFromClient.add(clientNum + job);
					}
				}
			}

		} catch (IOException e) {
			System.out.println(e.getMessage());
		}
	}
}

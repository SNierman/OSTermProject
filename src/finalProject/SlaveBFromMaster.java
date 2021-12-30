package finalProject;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;

public class SlaveBFromMaster extends Thread {

	private BufferedReader bReadFromMaster;
	private ArrayList<String> bJobsFromMaster;

	public SlaveBFromMaster(BufferedReader bReadFromMaster, ArrayList<String> bJobsFromMaster) {

		this.bReadFromMaster = bReadFromMaster;
		this.bJobsFromMaster = bJobsFromMaster;
	}

	@Override
	public void run() {
		try {

			String currJob;

			// keep up connection while master may still send more jobs
			while (MasterToSlave.currentThread().isAlive()) {
				while ((currJob = bReadFromMaster.readLine()) != null) {

					synchronized (bJobsFromMaster) {
						bJobsFromMaster.add(currJob);
					}
					
					System.out.println("Slave B Recieved Job: " + currJob);

				}
			}
		}

		catch (IOException e) {
			System.out.println("Exception caught when trying to listen on port or listening for a connection.");
			System.out.println(e.getMessage());
		}
	}
}

package finalProject;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;

public class SlaveAFromMaster extends Thread {

	private BufferedReader aReadFromMaster;
	private ArrayList<String> aJobsFromMaster;

	public SlaveAFromMaster(BufferedReader aReadFromMaster, ArrayList<String> aJobsFromMaster) {
		this.aReadFromMaster = aReadFromMaster;
		this.aJobsFromMaster = aJobsFromMaster;
	}

	@Override
	public void run() {
		String currJob;
		try {

			// keep up connection while master may still send more jobs
			while (MasterToSlave.currentThread().isAlive()) {

				while ((currJob = aReadFromMaster.readLine()) != null) {

					synchronized (aJobsFromMaster) {
						aJobsFromMaster.add(currJob);
					}

					System.out.println("Slave A Recieved Job: " + currJob);

				}
			}
		} catch (IOException e) {

			System.out.println(e.getMessage());
		}
	}
}

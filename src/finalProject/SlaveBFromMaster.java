package finalProject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class SlaveBFromMaster extends Thread {

	private final Type TYPE = Type.B;
	private BufferedReader bReadFromMaster;
	private ArrayList<String> bJobsFromMaster;

	public SlaveBFromMaster(BufferedReader bReadFromMaster, ArrayList<String> bJobsFromMaster) {

		this.bReadFromMaster = bReadFromMaster;
		this.bJobsFromMaster = bJobsFromMaster;
	}

	@Override
	public void run() {
		try {

			String currJob = null;
			while (MasterToSlave.currentThread().isAlive()) {
				while ((currJob = bReadFromMaster.readLine()) != null) {

					bJobsFromMaster.add(currJob);
					System.out.println("Slave B Recieved Job: " + currJob);

				}
			}
		}

		catch (IOException e) {
			System.out.println("Exception caught when trying to listen on port or listening for a connection SBFM");
			System.out.println(e.getMessage());
		}
	}
}

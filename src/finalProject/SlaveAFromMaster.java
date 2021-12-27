package finalProject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class SlaveAFromMaster extends Thread {

	private final Type TYPE = Type.A;
	private BufferedReader aReadFromMaster;
	private ArrayList<String> aJobsFromMaster;

	public SlaveAFromMaster(BufferedReader aReadFromMaster, ArrayList<String> aJobsFromMaster) {
		this.aReadFromMaster = aReadFromMaster;
		this.aJobsFromMaster = aJobsFromMaster;
	}

	@Override
	public void run() {
		String currJob = null;
		try {
			while (MasterToSlave.currentThread().isAlive()) {
				while ((currJob = aReadFromMaster.readLine()) != null) {

					aJobsFromMaster.add(currJob);
					System.out.println("Slave A Recieved Job: " + currJob);

				}
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}

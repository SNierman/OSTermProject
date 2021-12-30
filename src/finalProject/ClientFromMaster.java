package finalProject;

import java.io.BufferedReader;
import java.io.IOException;

public class ClientFromMaster extends Thread {

	private BufferedReader readFromMaster;

	public ClientFromMaster(BufferedReader readFromMaster) {
		this.readFromMaster = readFromMaster;
	}
	
	@Override
	public void run() {
		
		//Keep up connection while master is still sending jobs
		while (MasterToClient.currentThread().isAlive()) {
			
			String jobFromMaster;
			try {
				while ((jobFromMaster = readFromMaster.readLine()) != null) {
					System.out.println("Client received completed job " + jobFromMaster);
				}
			} catch (IOException e) {
				System.out.println(e.getMessage());
			}	
		}
	}
}

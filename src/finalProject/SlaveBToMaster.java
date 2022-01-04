package finalProject;

import java.io.PrintWriter;
import java.util.ArrayList;

public class SlaveBToMaster extends Thread {

	private final Type TYPE = Type.B;
	private PrintWriter bWriteToMaster;
	private ArrayList<String> BJobs;

	public SlaveBToMaster(PrintWriter bWriteToMaster, ArrayList<String> BJobs) {

		this.bWriteToMaster = bWriteToMaster;
		this.BJobs = BJobs;

	}

	@Override
	public void run() {

		String currJob;

		// thread continues while master sends over jobs
		while (MasterToSlave.currentThread().isAlive()) {
			while (!BJobs.isEmpty()) {

				currJob = BJobs.get(0);
				String currJobString = currJob.substring(1);
				// decide how long to work on job based on job type
				try {
					if (Type.valueOf(currJob.substring(1, 2).toUpperCase()).equals(TYPE)) {

						Thread.sleep(2000);

					}

					else {

						Thread.sleep(10000);
					}

				} catch (InterruptedException e) {
					System.out.println(e.getMessage());
				}

				// remove jobs so does not send same job twice
				synchronized (BJobs) {
					BJobs.remove(0);
				}
				
				System.out.println("Slave B completed job: " + currJobString);
				bWriteToMaster.println("B" + currJob);

			}

		}

	}
}

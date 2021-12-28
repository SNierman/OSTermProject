package finalProject;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
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
		System.out.println("SBTM");
		//bWriteToMaster.println("slave b connected");
			String currJob;
			while (MasterToSlave.currentThread().isAlive()) {
				while (!BJobs.isEmpty()) {

					currJob = BJobs.get(0);

					try {
						if (Type.valueOf(currJob.substring(0, 1).toUpperCase()).equals(TYPE)) {

							Thread.sleep(2000);
							
						}

						else {
							
							Thread.sleep(10000);
						}
						
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					
					System.out.println("Slave B Completed Job: " + currJob);
					bWriteToMaster.println(currJob);
					BJobs.remove(0);
				}

			}

	}
}

package finalProject;


	import java.io.IOException;
	import java.io.PrintWriter;
	import java.net.ServerSocket;
	import java.net.Socket;
	import java.util.ArrayList;

	public class SlaveAToMaster extends Thread {

		private final Type TYPE = Type.A;
		private PrintWriter aWriteToMaster;
		private ArrayList<String> AJobs;

		public SlaveAToMaster(PrintWriter aWriteToMaster, ArrayList<String> AJobs) {

			this.aWriteToMaster = aWriteToMaster;
			this.AJobs = AJobs;

		}

		@Override
		public void run() {
			System.out.println("SATM");
				//aWriteToMaster.println("slave a connected");
				String currJob;
				while (MasterToSlave.currentThread().isAlive()) {
					while (!AJobs.isEmpty()) {

						currJob = AJobs.get(0);

						try {
							if (Type.valueOf(currJob.substring(0, 1)).equals(TYPE)) {

								Thread.sleep(2000);
								
							}

							else {
								
								Thread.sleep(10000);
							}
							
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
						
						System.out.println("Slave A Completed Job: " + currJob);
						aWriteToMaster.println(currJob);
						AJobs.remove(0);
					}

				}

		}
	}

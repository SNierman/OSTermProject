package finalProject;


	import java.io.IOException;
	import java.io.PrintWriter;
	import java.net.ServerSocket;
	import java.net.Socket;
	import java.util.ArrayList;

	public class SlaveAToMaster extends Thread {

		private final Type TYPE = Type.A;
		private String[] args;
		private ArrayList<String> AJobs;

		public SlaveAToMaster(String[] args, ArrayList<String> AJobs) {

			this.args = args;
			this.AJobs = AJobs;

		}

		@Override
		public void run() {

			try (ServerSocket serverSocket = new ServerSocket(Integer.parseInt(args[1]));
					Socket clientSocket = serverSocket.accept();
					
					//Socket clientSocket = new Socket(args[0], Integer.parseInt(args[1]));
					//Socket clientSocket = serverSocket.accept();
					PrintWriter toMaster = new PrintWriter(clientSocket.getOutputStream(), true);) {

				String currJob;
				while (MasterToSlave.currentThread().isAlive() && SlaveBToMaster.currentThread().isInterrupted()) {
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
						toMaster.println(currJob);
						AJobs.remove(0);
					}

				}
			} catch (IOException e) {
				System.out.println("Exception caught when trying to listen on port or listening for a connection SATM");
				System.out.println(e.getMessage());
			}

		}
	}

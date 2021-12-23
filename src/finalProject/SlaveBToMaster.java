package finalProject;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class SlaveBToMaster extends Thread {

	private final Type TYPE = Type.B;
	private String[] args;
	private ArrayList<String> BJobs;

	public SlaveBToMaster(String[] args, ArrayList<String> BJobs) {

		this.args = args;
		this.BJobs = BJobs;

	}

	@Override
	public void run() {

		try (ServerSocket serverSocket = new ServerSocket(Integer.parseInt(args[1]));
				Socket clientSocket = serverSocket.accept();
				
				//Socket clientSocket = new Socket(args[0], Integer.parseInt(args[1]));
				//Socket clientSocket = serverSocket.accept();
				PrintWriter toMaster = new PrintWriter(clientSocket.getOutputStream(), true);) {

			String currJob;
			while (MasterToSlave.currentThread().isAlive() && SlaveAToMaster.currentThread().isInterrupted()) {
				while (!BJobs.isEmpty()) {

					currJob = BJobs.get(0);

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
					
					System.out.println("Slave B Completed Job: " + currJob);
					toMaster.println(currJob);
					BJobs.remove(0);
				}

			}
		} catch (IOException e) {
			System.out.println("Exception caught when trying to listen on port or listening for a connection SBTM");
			System.out.println(e.getMessage());
		}

	}
}

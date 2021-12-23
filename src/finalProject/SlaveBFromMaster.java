package finalProject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class SlaveBFromMaster extends Thread {
		
		private final Type TYPE = Type.B;
		String [] args;
		private ArrayList<String> bJobsFromMaster;

		
		public SlaveBFromMaster(String[] args, ArrayList<String> bJobsFromMaster) {
			
			this.args = args;
			this.bJobsFromMaster = bJobsFromMaster;
		}

			
		@Override
		public void run() {
			try(
					ServerSocket serverSocket = new ServerSocket(Integer.parseInt(args[1]));
					Socket clientSocket = serverSocket.accept();
					//Socket clientSocket = new Socket(args[0], Integer.parseInt(args[1]));
					//Socket clientSocket = serverSocket.accept();
					//reader/writer from the client
					BufferedReader inFromMaster = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
				){
				
				String currJob = null;
				while((currJob = inFromMaster.readLine()) != null) {
					
					bJobsFromMaster.add(currJob);
					System.out.println("Slave B Recieved Job: " + currJob);
					
					
				}
				
			}
			

			catch (IOException e) {
				System.out.println("Exception caught when trying to listen on port or listening for a connection SBFM");
				System.out.println(e.getMessage());
			}
		}
}



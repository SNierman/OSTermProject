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
	String [] args;
	private ArrayList<String> aJobsFromMaster;
	
	public SlaveAFromMaster(String[] args, ArrayList<String> aJobsFromMaster) {
		
		this.args = args;
		this.aJobsFromMaster = aJobsFromMaster;
	}

		
	@Override
	public void run() {
		try(
				ServerSocket serverSocket = new ServerSocket(Integer.parseInt(args[1]));
				Socket clientSocket = serverSocket.accept();
				//reader/writer from the client
				BufferedReader inFromMaster = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
			){
			
			String currJob = null;
			while((currJob = inFromMaster.readLine()) != null) {
				
				aJobsFromMaster.add(currJob);
				System.out.println("Slave A Recieved Job: " + currJob);
				
			}
			
		}
		

		catch (IOException e) {
			System.out.println("Exception caught when trying to listen on port or listening for a connection SAFM");
			System.out.println(e.getMessage());
		}
	}
}

package finalProject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class MasterToSlaveA extends Thread {
	
	private final String[] args;
	private ArrayList<String> jobsFromClient = new ArrayList<String>();
	private ArrayList<String> jobsSentToSlaves;
	
	public MasterToSlaveA(ArrayList<String> jobsFromClient, String[] args) {
		this.jobsFromClient = jobsFromClient;
		this.args = args;
	}
	
	public void run() {
		try (ServerSocket serverSocket = new ServerSocket(Integer.parseInt(args[0]));
				Socket clientSocket = serverSocket.accept();
				PrintWriter responseWriter = new PrintWriter(clientSocket.getOutputStream(), true);) {//print to master
			
			while (MasterFromClientThread.currentThread().isAlive()) {
			}
			
		} catch (IOException e) {
			System.out.println(
					"Exception caught when trying to listen on port or listening for a connection");
			System.out.println(e.getMessage());
		} 
			
	}

}

package finalProject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.ArrayList;

public class ClientToMasterThread extends Thread {

	private ArrayList<String> jobsSentToMaster = new ArrayList<>();
	String[] args;
	ArrayList<String> jobs;
	
	
	public ClientToMasterThread(ArrayList<String> jobs, String[] args) {
		this.jobs = jobs;
		this.args = args;
	}
	
	@Override
	public void run() {
		try (Socket clientSocket = new Socket(args[0], Integer.parseInt(args[1]));
				PrintWriter writeToMaster = new PrintWriter(clientSocket.getOutputStream(), true);) // talks to server;) // reads from server
				{
			// while jobs are greater than 0- when the job gets finished, user will remove it from the arrayList
			// and while the user is not done giving jobs because he could still give jobs even if they are all completed
		while (jobs.size() > 0 || UserToClientThread.currentThread().isAlive()) {
			for (int i = 0; i < jobs.size(); i++) {
				if (!jobsSentToMaster.contains(jobs.get(i))) {
					writeToMaster.println(jobs.get(i));
					jobsSentToMaster.add(jobs.get(i));
				}
			}
		}
		}catch (UnknownHostException e) {
			System.err.println("Don't know about host ");
			System.exit(1);
		} catch (IOException e) {
			System.err.println("Couldn't get I/O for the connection");
			System.exit(1);
		}
	}
}

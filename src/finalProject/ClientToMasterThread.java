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
	ArrayList<String> globalJobsList;
	
	
	public ClientToMasterThread(ArrayList<String> globalJobsList, String[] args) {
		this.globalJobsList = globalJobsList;
		this.args = args;
	}
	
	@Override
	public void run() {
		try (Socket clientSocket = new Socket(args[0], Integer.parseInt(args[1]));
				PrintWriter writeToMaster = new PrintWriter(clientSocket.getOutputStream(), true); // talks to server
				BufferedReader responseReader = new BufferedReader(
						new InputStreamReader(clientSocket.getInputStream()));) // reads from server
				{
		while (globalJobsList.size() > 0 /*and the user is not done giving jobs*/) {
			for (int i = 0; i < globalJobsList.size(); i++) {
				if (!jobsSentToMaster.contains(globalJobsList.get(i))) {
					writeToMaster.println(globalJobsList.get(i));
					jobsSentToMaster.add(globalJobsList.get(i));
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

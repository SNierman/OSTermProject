package finalProject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.ArrayList;

public class ClientToMasterThread extends Thread {

	//what is the point of this arrayList?
	private ArrayList<String> jobsSentToMaster = new ArrayList<>();
	ArrayList<String> jobs;
	private PrintWriter writeToMaster;
	
	public ClientToMasterThread(ArrayList<String> jobs, PrintWriter writeToMaster) {
		this.jobs = jobs;
		this.writeToMaster = writeToMaster;
	}
	
	@Override
	public void run() {
		
			// while jobs are greater than 0- when the job gets finished, user will remove it from the arrayList
			
		// and while the user is not done giving jobs because he could still give jobs even if they are all completed
		while (jobs.size() > 0 || UserToClientThread.currentThread().isAlive()) { 
			for (int i = 0; i < jobs.size(); i++) {
				if (!jobsSentToMaster.contains(jobs.get(i))) {
					System.out.println("Sending over job " + jobs.get(i));
					synchronized(writeToMaster){
						// Thread.currentThread().setContextClassLoader( getClass().getClassLoader() );
						//writeToMaster.println();
						writeToMaster.println(jobs.get(i));
					
						//writeToMaster.flush();
						jobsSentToMaster.add(jobs.get(i));
					}
				}
			}
		}

	}
}

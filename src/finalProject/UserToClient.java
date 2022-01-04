package finalProject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class UserToClient extends Thread {

	ArrayList<String> jobs;

	public UserToClient(ArrayList<String> jobs) {
		this.jobs = jobs;
	}

	@Override
	public void run() {
		
		//reads from user in console
		try (BufferedReader readsFromUser = new BufferedReader(new InputStreamReader(System.in));)
		{
			String userInput;

			int id = 0;
			// go until user types done
			while (!(userInput = readsFromUser.readLine()).equalsIgnoreCase("done")) {

				// input validation
				while (!userInput.toUpperCase().equals(Type.A.toString())
						&& !userInput.toUpperCase().equals(Type.B.toString())) {

					System.out.println("Please enter a job of type A or B.");
					userInput = readsFromUser.readLine();
				}

				//add an ID to keep track of the jobs
				String job = userInput.toUpperCase() + String.valueOf(id++);
				
				synchronized(jobs) {
					jobs.add(job);
				}

			}
			
			UserToClient.currentThread().stop();

		} catch (IOException e) {
			System.err.println("Couldn't get I/O for the connection.");
			System.exit(1);
		}
	}
}

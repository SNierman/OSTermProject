package finalProject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.UnknownHostException;
import java.util.ArrayList;

public class UserToClientThread extends Thread {


	ArrayList<String> jobs = new ArrayList<String>();
	
	public UserToClientThread(ArrayList<String> jobs) {
		this.jobs = jobs;
	}

	@Override
	public void run() {
		try(BufferedReader readsFromUser = new BufferedReader(
						new InputStreamReader(System.in));)					   // reads from user)
		{
			String userInput;

			int id = -1;
			// go until user types done
			while (!(userInput = readsFromUser.readLine()).equals("Done")) {

				// input validation
				while (!userInput.toUpperCase().equals("A") && !userInput.toUpperCase().equals("B")) {

					System.out.println("Please enter a job of type A or B.");
					userInput = readsFromUser.readLine();
				}

				String job = userInput + String.valueOf(id++);
				jobs.add(job);
				
				
			}
			this.stop();
		} catch (IOException e) {
			System.err.println("Couldn't get I/O for the connection.");
			System.exit(1);
		}
	}
}
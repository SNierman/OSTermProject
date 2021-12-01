package finalProject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.UnknownHostException;
import java.util.ArrayList;

public class UserToClientThread extends Thread {

	ArrayList<String> jobsSentToClient;

	public UserToClientThread(ArrayList<String> jobsSentToClient) {
		this.jobsSentToClient = jobsSentToClient;
	}

	@Override
	public void run() {
		try(BufferedReader stdIn = new BufferedReader(
						new InputStreamReader(System.in));)					   // reads from user)
		{
			String userInput;

			int id = -1;
			// go until user types done
			while ((userInput = stdIn.readLine()) != "Done") {

				// input validation
				/*while (userInput.toUpperCase() != "A" && userInput.toUpperCase() != "B") {
					System.out.println("Please enter a job of type A or B.");
					userInput = stdIn.readLine();
				}

				String job = userInput + String.valueOf(id++);
				jobsSentToClient.add(job);*/
				
			}
		} catch (IOException e) {
			System.err.println("Couldn't get I/O for the connection.");
			System.exit(1);
		}
	}
}

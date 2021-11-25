package finalProject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Random;

public class Client {
	// connect to master
	// submit jobs

	// job has a type and ID
	//practicing

	/**
	 * regular client NEEDS: Socket clientSocket = new Socket(hostName, portNumber);
	 */

	// Hardcode in IP and Port here if required
	public static void main(String[] args) {
		args = new String[] { "127.0.0.1", "30121" };

		if (args.length != 2) {
			System.err.println("Usage: java EchoClient <host name> <port number>");
			System.exit(1);
		}

		String hostName = args[0];
		int portNumber = Integer.parseInt(args[1]);

		try (Socket clientSocket = new Socket(hostName, portNumber);
				PrintWriter requestWriter = new PrintWriter(clientSocket.getOutputStream(), true); // talks to server
				BufferedReader responseReader = new BufferedReader(
						new InputStreamReader(clientSocket.getInputStream()));) // reads from server
		{
		
			// initialize empty array to the random length decided by rand (between 20 and 40 jobs)
			Random rand = new Random(20);
			int numJobs = rand.nextInt() + 20;
			int[] jobs = new int[numJobs];
			Random randJobType = new Random(2);

			// send each job as a type 1 or type 2 job to the server
			for (int i = 0; i < jobs.length; i++) {
				int jobType = randJobType.nextInt();
				jobs[i] = jobType + 1;
				requestWriter.println(jobs[i]);
			}

			requestWriter.println();
			if (responseReader.readLine() == "Done") {
				System.out.println("Thank you for completing all the jobs.");
				clientSocket.close();
			}

		} catch (UnknownHostException e) {
			System.err.println("Don't know about host " + hostName);
			System.exit(1);
		} catch (IOException e) {
			System.err.println("Couldn't get I/O for the connection to " + hostName);
			System.exit(1);
		}
	}
}

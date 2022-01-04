package finalProject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.ArrayList;

//Initialize twice to create two clients
public class Client {

	public static void main(String[] args) {

		// Hard code the IP Address and Port Number
		args = new String[] { "127.0.0.1", "30121" };

		try (Socket clientSocket = new Socket(args[0], Integer.parseInt(args[1]));
				PrintWriter writeToMaster = new PrintWriter(clientSocket.getOutputStream(), true);
				BufferedReader readFromMaster = new BufferedReader(
						new InputStreamReader(clientSocket.getInputStream()));) {

			if (args.length != 2) {
				System.err.println("Invalid args");
				System.exit(1);
			}

			// global ArrayList of jobs to get from user to clientToMaster Thread
			ArrayList<String> jobs = new ArrayList<String>();

			ClientToMaster clientToMaster = new ClientToMaster(jobs, writeToMaster);
			UserToClient userToClient = new UserToClient(jobs);

			ClientFromMaster clientFromMaster = new ClientFromMaster(readFromMaster);

			userToClient.start();
			clientToMaster.start();
			clientFromMaster.start();

			userToClient.join();
			clientToMaster.join();
			clientFromMaster.join();
			

		} catch (UnknownHostException e) {
			System.err.println("Don't know about host.");
			System.exit(1);
		} catch (IOException e) {
			System.err.println("Couldn't get I/O for the connection to Master.");
			System.exit(1);
		} catch (InterruptedException e) {
			System.out.println(e.getMessage());
		}
	}
}
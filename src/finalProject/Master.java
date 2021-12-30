package finalProject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class Master {

	public static void main(String[] args) {

		//two port numbers, one for the clients and one for the slaves
		args = new String[] { "30121" , "30122" };

		try (ServerSocket serverSocket = new ServerSocket(Integer.parseInt(args[0]));
				ServerSocket serverSocket2 = new ServerSocket(Integer.parseInt(args[1]));
				
				//accept two clients connections
				Socket clientSocket = serverSocket.accept();
				Socket client2Socket = serverSocket.accept();

				//accept two slave connections
				Socket slaveASocket = serverSocket2.accept();
				Socket slaveBSocket = serverSocket2.accept();

				//writers/readers for client connections
				PrintWriter writeToClient1 = new PrintWriter(clientSocket.getOutputStream(), true);
				BufferedReader inFromClient1 = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
				PrintWriter writeToClient2 = new PrintWriter(client2Socket.getOutputStream(), true);
				BufferedReader inFromClient2 = new BufferedReader(
						new InputStreamReader(client2Socket.getInputStream()));

				//writers/readers for slave connections
				PrintWriter writeToSlaveA = new PrintWriter(slaveASocket.getOutputStream(), true);
				BufferedReader inFromSlaveA = new BufferedReader(new InputStreamReader(slaveASocket.getInputStream()));
				PrintWriter writeToSlaveB = new PrintWriter(slaveBSocket.getOutputStream(), true);
				BufferedReader inFromSlaveB = new BufferedReader(
						new InputStreamReader(slaveBSocket.getInputStream()));) {

			//global list to access jobs taken from clients to give them to the slaves
			ArrayList<String> jobsFromClient1 = new ArrayList<String>();
			ArrayList<String> jobsFromClient2 = new ArrayList<String>();
			
			//keeps track of slave's current workload (in seconds)
			WorkTimeCounter counterA = new WorkTimeCounter();
			WorkTimeCounter counterB = new WorkTimeCounter();

			//global list to access completed jobs from slaves to give them to the clients
			ArrayList<String> completedJobsClient1 = new ArrayList<String>();
			ArrayList<String> completedJobsClient2 = new ArrayList<String>();

			//Initialize threads
			MasterFromClient masterFromClient1 = new MasterFromClient(jobsFromClient1, inFromClient1);
			MasterFromClient masterFromClient2 = new MasterFromClient(jobsFromClient2, inFromClient2);
			MasterToSlave toSlavesClient1 = new MasterToSlave(writeToSlaveA, writeToSlaveB, counterA, counterB,
					jobsFromClient1);
			MasterToSlave toSlavesClient2 = new MasterToSlave(writeToSlaveA, writeToSlaveB, counterA, counterB,
					jobsFromClient2);

			MasterFromSlave fromSlaveAClient1 = new MasterFromSlave(inFromSlaveA, completedJobsClient1, counterA, counterB);
			MasterFromSlave fromSlaveBClient1 = new MasterFromSlave(inFromSlaveB, completedJobsClient1, counterA, counterB);
			MasterToClient masterToClient1 = new MasterToClient(writeToClient1, completedJobsClient1);

			MasterFromSlave fromSlaveAClient2 = new MasterFromSlave(inFromSlaveA, completedJobsClient2, counterA, counterB);
			MasterFromSlave fromSlaveBClient2 = new MasterFromSlave(inFromSlaveB, completedJobsClient2, counterA, counterB);
			MasterToClient masterToClient2 = new MasterToClient(writeToClient2, completedJobsClient2);

			masterFromClient1.start();
			toSlavesClient1.start();
			fromSlaveAClient1.start();
			fromSlaveBClient1.start();
			masterToClient1.start();
			masterFromClient2.start();
			toSlavesClient2.start();
			fromSlaveAClient2.start();
			fromSlaveBClient2.start();
			masterToClient2.start();

			masterFromClient1.join();
			toSlavesClient1.join();
			fromSlaveAClient1.join();
			fromSlaveBClient1.join();
			masterToClient2.join();
			masterFromClient2.join();
			toSlavesClient2.join();
			fromSlaveAClient2.join();
			fromSlaveBClient2.join();
			masterToClient2.join();

		} catch (IOException | InterruptedException e) {
			System.out.println("Exception caught when trying to listen on port or listening for a connection");
			System.out.println(e.getMessage());
		}
	}
}

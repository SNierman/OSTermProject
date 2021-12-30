package finalProject;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class Master {
	// take in jobs
	// assign it to slave
	// alert client when job is done
	public static void main(String[] args) {

		args = new String[] { "30121" };

		try (ServerSocket serverSocket = new ServerSocket(Integer.parseInt(args[0]));
				ServerSocket serverSocket2 = new ServerSocket(30122);
				// ServerSocket serverSocket3 = new ServerSocket(30123);
				Socket clientSocket = serverSocket.accept();
				Socket client2Socket = serverSocket.accept();

				Socket slaveASocket = serverSocket2.accept();
				Socket slaveBSocket = serverSocket2.accept();

				PrintWriter writeToClient1 = new PrintWriter(clientSocket.getOutputStream(), true);
				BufferedReader inFromClient1 = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
				PrintWriter writeToClient2 = new PrintWriter(client2Socket.getOutputStream(), true);
				BufferedReader inFromClient2 = new BufferedReader(
						new InputStreamReader(client2Socket.getInputStream()));

				PrintWriter writeToSlaveA = new PrintWriter(slaveASocket.getOutputStream(), true);
				BufferedReader inFromSlaveA = new BufferedReader(new InputStreamReader(slaveASocket.getInputStream()));
				PrintWriter writeToSlaveB = new PrintWriter(slaveBSocket.getOutputStream(), true);
				BufferedReader inFromSlaveB = new BufferedReader(
						new InputStreamReader(slaveBSocket.getInputStream()));) {

			ArrayList<String> jobsFromClient1 = new ArrayList<String>();
			ArrayList<String> jobsFromClient2 = new ArrayList<String>();
			WorkTimeCounter counterA = new WorkTimeCounter(Type.A);
			WorkTimeCounter counterB = new WorkTimeCounter(Type.B);

			ArrayList<String> completedJobsClient1 = new ArrayList<String>();
			ArrayList<String> completedJobsClient2 = new ArrayList<String>();

			MasterFromClientThread masterFromClient1 = new MasterFromClientThread(jobsFromClient1, inFromClient1);
			MasterFromClientThread masterFromClient2 = new MasterFromClientThread(jobsFromClient2, inFromClient2);
			MasterToSlave toSlavesClient1 = new MasterToSlave(writeToSlaveA, writeToSlaveB, counterA, counterB,
					jobsFromClient1);
			MasterToSlave toSlavesClient2 = new MasterToSlave(writeToSlaveA, writeToSlaveB, counterA, counterB,
					jobsFromClient2);

			MasterFromSlaves fromSlaveAClient1 = new MasterFromSlaves(inFromSlaveA, completedJobsClient1);
			MasterFromSlaves fromSlaveBClient1 = new MasterFromSlaves(inFromSlaveB, completedJobsClient1);
			MasterToClient masterToClient1 = new MasterToClient(writeToClient1, completedJobsClient1);

			MasterFromSlaves fromSlaveAClient2 = new MasterFromSlaves(inFromSlaveA, completedJobsClient2);
			MasterFromSlaves fromSlaveBClient2 = new MasterFromSlaves(inFromSlaveB, completedJobsClient2);
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

			// server to clients
			// Socket clientSocket1 = serverSocket.accept();
			// Socket clientSocket2 = serverSocket.accept();

			// client to both slaves
			// Socket clientSocket = new Socket(hostName, portNumber);

			/**
			 * The master is a server to the two clients. NEEDS: //Socket clientSocket1 =
			 * serverSocket.accept(); //Socket clientSocket2 = serverSocket.accept();
			 * ServerSocket serverSocket = new ServerSocket(port#)
			 * 
			 * The master is a Client to Slave A and B NEEDS: Socket clientSocket = new
			 * Socket(hostName, portNumber); Socket clientSocket = new Socket(hostName,
			 * portNumber);
			 */

			/**
			 * class master + class Seconds private amtOFSeconds, String type. + slave gets
			 * a job if its type +2 , else +10. Same for removal
			 * 
			 * + array list jobs- same one as masterFromClient
			 * 
			 * mastertToslave tosalveA = new (amntOfSondsvaraible, masterFrom Client, A)
			 * --clinet connection tath it will set up will be to a slave A masterToSlave
			 * toslaveB= new (amntOfSondsvaraible, masterFrom Client, B) --cleint connection
			 * to a different slave B
			 * 
			 * fromSalve slaveA = new (sceonds, A, jobID/type(string))-->
			 * 
			 * masterToclient(1)-->tells the client the job completed.
			 * 
			 * class masterToSlave
			 * 
			 * the other option: masterToSlaves = new(arrayList)
			 * 
			 * connection to both slaves
			 * 
			 * class Slave thread slave to maste--> sends the master the job that completed
			 * 
			 */

		} catch (IOException | InterruptedException e) {
			System.out.println("Exception caught when trying to listen on port or listening for a connection");
			System.out.println(e.getMessage());
		}
	}
}

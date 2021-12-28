package finalProject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.ArrayList;

public class Slave {
	// take jobs
	// sleep
	// alert when done

	// server socket
	// client socket

	/**
	 * server to master
	 * NEEDS:
	 *Socket masterclient = serverSocket.accept();
		 *ServerSocket serverSocket = new ServerSocket(port#)
	 */
	
	public static void main(String[] args) throws IOException {

		args = new String[] { "127.0.0.1", "30122"};
		
		
		try (Socket slaveSocket = new Socket(args[0], Integer.parseInt(args[1])); 
				PrintWriter aWriteToMaster = new PrintWriter(slaveSocket.getOutputStream(), true); 
				BufferedReader aReadFromMaster = new BufferedReader(new InputStreamReader(slaveSocket.getInputStream()));) {
			
			Socket slaveSocket2 = new Socket(args[0], Integer.parseInt(args[1]));
			PrintWriter bWriteToMaster = new PrintWriter(slaveSocket2.getOutputStream(), true); 
			BufferedReader bReadFromMaster = new BufferedReader(new InputStreamReader(slaveSocket2.getInputStream())); {

			if (args.length != 2) {
				System.err.println("Usage: java EchoClient <host name> <port number>");
				System.exit(1);
			}
		
		ArrayList<String> aJobsFromMaster = new ArrayList<String>();
		ArrayList<String> bJobsFromMaster = new ArrayList<String>();

		SlaveAFromMaster slaveAFromMaster = new SlaveAFromMaster(aReadFromMaster, aJobsFromMaster);
		SlaveBFromMaster slaveBFromMaster = new SlaveBFromMaster(bReadFromMaster, bJobsFromMaster);
		
		SlaveAToMaster slaveAToMaster = new SlaveAToMaster(aWriteToMaster, aJobsFromMaster);
		SlaveBToMaster slaveBToMaster = new SlaveBToMaster(bWriteToMaster, bJobsFromMaster);
		
		slaveAFromMaster.start();
		slaveBFromMaster.start();
		slaveAToMaster.start();
		slaveBToMaster.start();
		
		slaveAFromMaster.join();
		slaveBFromMaster.join();
		slaveAToMaster.join();
		slaveBToMaster.join();
		
	}}catch (UnknownHostException e) {
		System.err.println("Don't know about host ");
		System.exit(1);
	}
	catch (IOException e) {
		 System.err.println("Couldn't get I/O for the connection");
		 System.exit(1); } catch (InterruptedException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	}

}

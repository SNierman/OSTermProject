package finalProject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class Slave {
	//take jobs
	//sleep
	//alert when done
	
	
	//server socket
	//client socket
	
	/**
	 * server to master
	 * NEEDS:
	 *Socket masterclient = serverSocket.accept();
		 *ServerSocket serverSocket = new ServerSocket(port#)
	 */
	
	public static void main(String[] args) throws IOException {


		args = new String[] { "30121" };

		if (args.length != 1) {
			System.err.println("Usage: java EchoServer <port number>");
			System.exit(1);
		}
		
		ArrayList<String> aJobsFromMaster = new ArrayList<String>();
		ArrayList<String> bJobsFromMaster = new ArrayList<String>();

		SlaveAFromMaster slaveAFromMaster = new SlaveAFromMaster(args, aJobsFromMaster);
		SlaveBFromMaster slaveBFromMaster = new SlaveBFromMaster(args, bJobsFromMaster);
		
		SlaveAToMaster slaveAToMaster = new SlaveAToMaster(args, aJobsFromMaster);
		SlaveBToMaster slaveBToMaster = new SlaveBToMaster(args, bJobsFromMaster);
		
		slaveAFromMaster.start();
		slaveBFromMaster.start();
		slaveAToMaster.start();
		slaveBToMaster.start();
		
		
	}

}

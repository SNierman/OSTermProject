package finalProject;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.lang.System.Logger;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class MasterFromClientThread extends Thread {

	private BufferedReader inFromClient;
	private ArrayList<String> jobsFromClient;
	
	public MasterFromClientThread(ArrayList<String> jobsFromClient,  BufferedReader inFromClient) {
		this.jobsFromClient = jobsFromClient;
		this.inFromClient = inFromClient;
	}

	@Override
	public void run() {
		
		//initiate convo with the client
		//outToClient.println("");
		/*
		ArrayList<String> jobsFromClient = new ArrayList<String>();
		String job;
		String completeJob;
		
		//read the jobs from the client
		while((job = inFromClient.readLine()) != null) {
			
			
			if(job.equalsIgnoreCase("Done")) {
				break;
			}
			
			//add to ArrayList of jobs
			jobsFromClient.add(job);
				
		}*/
		String job;
		try {
			while(ClientToMasterThread.currentThread().isAlive()) {
			System.out.print("in MFCT");
			synchronized(inFromClient) {
	        while (((job = inFromClient.readLine()) != null)&&(inFromClient.ready())) {
	        	
	            System.out.println(job);
	            System.out.print("in MFCT- while loop");
	        }
			}
	        }
			

	    } catch (IOException ex) {
	        
			/**
			try {
				String job;
			while(true)  {
				
				
				while (ClientToMasterThread.currentThread().isAlive() && (job = inFromClient.readLine()) != null) {
					
					
					System.out.println("Master received: " + job);
					jobsFromClient.add(job);
				}}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}*/
	}
	}	
}

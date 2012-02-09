import java.net.*;
import java.io.*;


public class Listener implements Runnable {
	private int listenPort;
	
	public Listener(int newPort){
		listenPort=newPort;
		System.out.println("Cheese!");
	}
	
	public void run() {
		
		try {
			ServerSocket listenServerSocket = new ServerSocket(listenPort);
			Socket listenSocket = listenServerSocket.accept();	
			BufferedReader in = new BufferedReader(
				    new InputStreamReader(
				    listenSocket.getInputStream()));
			String input;
			while ((input=in.readLine())!=null)
			{
				System.out.println("Cheese!");
			}
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}

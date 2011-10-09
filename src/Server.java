import java.io.*;
import java.net.*;

public class Server implements Runnable {
	OrderList kitchen;
	
	public Server(OrderList newKitchen) {
		kitchen= newKitchen;
	}

	public void run(){
         
    	ServerSocket serverSocket = null;
        boolean listening = true;
        
        try {
            serverSocket = new ServerSocket(8080);
            
        } catch (IOException e) {
            System.err.println("IO fail");
            System.exit(-1);
        }
 
        try {
        while (listening)
        {
				new ServerThread(serverSocket.accept(),kitchen).start();
		} 
        serverSocket.close();
        	
        }
        catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
        	}
    }
}
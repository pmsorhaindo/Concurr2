import java.io.*;
import java.net.*;

public class Server implements Runnable {

	public Server(OrderList newKitchen) {
	}

	public void run(){
        
    	OrderList kitchen = new OrderList(); 
    	ServerSocket serverSocket = null;
        boolean listening = true;
        
        try {
            serverSocket = new ServerSocket(8080);
            
        } catch (IOException e) {
            System.err.println("Could not listen on port: 8080.");
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
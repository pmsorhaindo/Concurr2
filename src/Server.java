import java.io.*;
import java.net.*;

public class Server implements Runnable {
	OrderList kitchen;
	int listenPort;
	
	public Server(int newListenPort) {
		OrderList kitchenYo = new OrderList();
		kitchen= kitchenYo;
		listenPort = newListenPort;
	}

	public void run(){
         
    	ServerSocket serverSocket = null;
        boolean listening = true;
        
        try {
            serverSocket = new ServerSocket(listenPort);
            
        } catch (IOException e) {
            System.err.println("There was a problem trying to setup a server at this port.");
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
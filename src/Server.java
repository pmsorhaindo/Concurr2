import java.io.*;
import java.net.*;

public class Server {

    public static void main(String[] args) throws IOException {
        
    	ServerSocket serverSocket = null;
        boolean listening = true;
 
        try {
            serverSocket = new ServerSocket(9999);
        } catch (IOException e) {
            System.err.println("Could not listen on port: 9999.");
            System.exit(-1);
        }
 
        while (listening)
        new ServerThread(serverSocket.accept()).start();
 
        serverSocket.close();
    }
}
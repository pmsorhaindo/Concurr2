import java.net.*;
import java.io.*;

public class ServerThread extends Thread {
    
	private Socket socket = null;
	private OrderList kitchen = null;
    
	public ServerThread(Socket socket,OrderList newKitchen) {
	super("ServerThread"); //Names the thread using the Thread class.
	this.socket = socket;
	this.kitchen = newKitchen;

	
    }

    public void run() {
	try {
		
	    PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
	    BufferedReader in = new BufferedReader(
				    new InputStreamReader(
				    socket.getInputStream()));
	    String outputLine;
	    while (true) {
	    KitchenComm com = new KitchenComm(kitchen);
	    outputLine = com.processInput(in.readLine());
	    System.out.println("sending to com: "+ outputLine);
	    out.println(outputLine);

		outputLine = com.processInput(in.readLine());
		out.println(outputLine);
		if (outputLine.equals("Bye"))
		    break;
	    }
	    out.close();
	    in.close();
	    socket.close();

	} catch (IOException e) {
	    e.printStackTrace();
	}
    }
}
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

	    String inputLine, outputLine;
	    KitchenComm com = new KitchenComm(kitchen);
	    outputLine = com.processInput(null);
	    out.println(outputLine);

	    while ((inputLine = in.readLine()) != null) {
		outputLine = com.processInput(inputLine);
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
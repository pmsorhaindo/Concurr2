import java.net.*;
import java.io.*;

public class ServerThread extends Thread {
    
	private Socket socket;
	private OrderList kitchen;
	BufferedReader in;
	PrintWriter out;
	
	public ServerThread(Socket socket,OrderList newKitchen) {
		this.socket = socket;
		this.kitchen = newKitchen;	
    }

    public void run() {
    	
	    KitchenComm com = new KitchenComm(kitchen);
	    BufferedReader in=null;
		PrintWriter out=null;
		String outputLine = null;
		
		try{
			String input;
		    out = new PrintWriter(socket.getOutputStream(), true);
		    in = new BufferedReader(
					    new InputStreamReader(
					    socket.getInputStream()));
			while(true){
				
				while ((input = in.readLine()) != null){
					System.out.println("Go!");
				   	outputLine = com.processInput(in.readLine());
					System.out.println("sending to com: "+ outputLine);
					out.println(outputLine);
					outputLine = com.processInput(in.readLine());
					out.println(outputLine);
					System.out.println("input: "+input);
					System.out.println("input: "+ input);
					if (outputLine.equals("Bye")){
						out.close();
					    in.close();
					    socket.close();
					    break;
					}
				}
			}
		} catch (IOException e) {
		    	e.printStackTrace();
		}
	}   
}
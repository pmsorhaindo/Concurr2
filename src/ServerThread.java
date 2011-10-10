import java.net.*;
import java.io.*;

public class ServerThread extends Thread {
    
	private Socket socket;
	private OrderList kitchen;
	BufferedReader in;
	PrintStream out;
	
	public ServerThread(Socket socket,OrderList newKitchen) {
		this.socket = socket;
		this.kitchen = newKitchen;	
    }

    public void run() {
    	
	    KitchenComm com = new KitchenComm(kitchen);
	    BufferedReader in=null;
		PrintStream out=null;
		
		try{
			String input,output;
		    out = new PrintStream(socket.getOutputStream(), true);
		    in = new BufferedReader(
					    new InputStreamReader(
					    socket.getInputStream()));
			while(true){
				
				while ((input = in.readLine()) != null){
					output = com.processInput(input);
					//out.println(output);
					
					if (output=="Bye"){
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
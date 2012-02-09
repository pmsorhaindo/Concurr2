import java.net.*;
import java.io.*;

public class ServerThread extends Thread {
    
	private Socket socket;
	private OrderList kitchen;
	BufferedReader in;
	PrintStream out;
	String input,output;
	
	public ServerThread(Socket socket,OrderList newKitchen) {
		this.socket = socket;
		this.kitchen = newKitchen;	
    }
    public void run() {
    	
	    KitchenComm com = new KitchenComm(kitchen);
	    BufferedReader in=null;
		PrintStream out=null;
		
		try{
		    //out = new PrintStream(socket.getOutputStream(), true);
			//Thread response = new Thread(new Writer(,),"notListeningActuallyWriting");
		    in = new BufferedReader(
					    new InputStreamReader(
					    socket.getInputStream()));
		    out = new PrintStream(socket.getOutputStream());
			while(true){
				
				while ((input = in.readLine()) != null){
					output = com.processInput(input);
					out.print(output+"\n");
					
					if (output=="Close"){
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
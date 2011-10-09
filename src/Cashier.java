import java.util.*;
import java.net.*;
import java.io.*;

public class Cashier implements Runnable {
	
	private OrderList kitchen;
	private String cashierName;
	private Socket cashierSocket = null;
	private PrintWriter out = null;
	private BufferedReader in = null;
	
	public Cashier(String nameText, OrderList kitchenName){
		cashierName = nameText;
		kitchen = kitchenName;
	}
	
	public void run(){
		try {
		    cashierSocket = new Socket("192.168.0.2", 8080);
		    out = new PrintWriter(cashierSocket.getOutputStream(), true);
		    in = new BufferedReader(new InputStreamReader(cashierSocket.getInputStream()));
		} catch (UnknownHostException e) {
		    System.err.println("Don't know about host: localhost."); //TODO make this error message nice
		    System.exit(1);
		} catch (IOException e) {
		    System.err.println("Couldn't get I/O for the connection to: localhost."); //TODO dynamic error message
		    System.exit(1);
		}

		int i = 0;
		while(i < 30)
		{
			
			
			
			Random r = new Random();
			try {
				Thread.sleep(r.nextInt(4999));
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
				out.print("placeOrder \n");
				//System.out.println("yum!");
				i+=1;
		}
		
		try {
		out.close();
		in.close();
		cashierSocket.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
}
	

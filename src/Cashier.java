import java.util.*;
import java.net.*;
import java.io.*;

public class Cashier implements Runnable {
	
	private OrderList kitchen;
	private String cashierName;
	
	
	public Cashier(String nameText, OrderList kitchenName){
		cashierName = nameText;
		kitchen = kitchenName;
	}
	
	public void run(){
		int i = 0;
		System.out.println("Cashier alive");
		Socket cashierSocket;
		PrintStream cashierPrintStream = null;
		try {
			cashierSocket = new Socket("localhost",9999);
			cashierPrintStream = new PrintStream(cashierSocket.getOutputStream());
			System.out.println("streamattt!");
		} catch (UnknownHostException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		while(i < 30)
		{
			Random r = new Random();
			try {
				Thread.sleep(r.nextInt(4999));
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			/*try {
			Socket cashierSocket = new Socket("localhost", 9999);
			PrintStream cashierPrintStream = new PrintStream(cashierSocket.getOutputStream());
			cashierPrintStream.print("connect");
			} catch (Exception e) {
				
			}*/
			if(cashierPrintStream!=null)cashierPrintStream.print("placeOrder");
			/*Order tempOrder = kitchen.addOrder(cashierName);
			System.out.println("Cashier: " + cashierName + "  " + tempOrder.getOrderID() + "  Placed at: "
			+ tempOrder.getTimePlaced());
			i=i+1;*/
		}
	}
	
}

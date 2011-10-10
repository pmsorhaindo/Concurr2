import java.util.*;
import java.net.*;
import java.io.*;

public class Cashier implements Runnable {
	
	private OrderList kitchen;
	private String cashierName;
	private Socket cashierSocket = null;
	private PrintStream out = null;
	private BufferedReader in = null;
	
	public Cashier(String nameText, OrderList kitchenName){
		setCashierName(nameText);
		setKitchen(kitchenName);
	}
	
	public void run(){
		try {
		    cashierSocket = new Socket("127.0.0.1", 8080);
		    out = new PrintStream(cashierSocket.getOutputStream());
		    in = new BufferedReader(new InputStreamReader(cashierSocket.getInputStream()));
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
					out.println("placeOrder "+ getCashierName() + "\n");
					i+=1;
			}
		} catch (UnknownHostException e) {
		    System.err.println("Finding Host fail"); //TODO make this error message nice
		    System.exit(1);
		} catch (IOException e) {
		    System.err.println("IO fail");
		    System.exit(1);
		}
		try{
			System.out.println("Nooo");
			out.close();
			in.close();
			cashierSocket.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public String getCashierName() {
		return cashierName;
	}

	public void setCashierName(String cashierName) {
		this.cashierName = cashierName;
	}

	public OrderList getKitchen() {
		return kitchen;
	}

	public void setKitchen(OrderList kitchen) {
		this.kitchen = kitchen;
	}
}
	

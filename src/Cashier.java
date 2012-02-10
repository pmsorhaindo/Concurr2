import java.util.*;
import java.net.*;
import java.io.*;

public class Cashier implements Runnable {
	
	private static int nextPort = 9000;
	//private OrderList kitchen;
	private String cashierName;
	private Socket cashierSocket;
	private PrintStream out;
	private BufferedReader in;
	private InetAddress serverAddress;
	private InetAddress cashierAddress;
	private int serverPort;
	private int listenPort;
	private boolean onDuty;
	
	public Cashier(String nameText,String newServerAddress, String newServerPort){
		setCashierName(nameText);
		onDuty=true;
		//setKitchen(kitchenName);
		serverPort=Integer.parseInt(newServerPort);
		try{
		serverAddress = InetAddress.getByName(newServerAddress);
		}
		catch(UnknownHostException e)
		{
		System.err.println("failed to set addresses");
		}
	}
	
	public void run(){
		try {
		    cashierSocket = new Socket(serverAddress, serverPort);
		    out = new PrintStream(cashierSocket.getOutputStream());
		    in = new BufferedReader(new InputStreamReader(cashierSocket.getInputStream()));
		    //listenPort = cashierSocket.getLocalPort();
		    //Thread incoming = new Thread(new Listener(listenPort), "CashierListener");
			//incoming.start();
			
			
			
			while(onDuty)
			{
				Random r = new Random();
				try {
					Thread.sleep(r.nextInt(4999));
				} catch (InterruptedException e) {
					System.out.println("Cashier " + cashierName + " is logging off...");
					onDuty=false;
				}			
					out.print("1*"+ getCashierName() + "\n");

					String input;
					if ((input = in.readLine()) != null){
						System.out.println("Cashier: "+ parseOrderPlacedReturn(input));
					}
			}
		} catch (UnknownHostException e) {
		    System.err.println("Finding Host fail"); //TODO make this error message nice
		    System.exit(1);
		} catch (IOException e) {
		    System.err.println("O fail");
		    System.exit(1);
		}
		try{
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

/*	public OrderList getKitchen() {
		return kitchen;
	}

	public void setKitchen(OrderList kitchen) {
		this.kitchen = kitchen;
	} */
	
	public String parseOrderPlacedReturn(String inputToParse){
		String orderPlaced = "Order ";
		orderPlaced = orderPlaced+ inputToParse.substring(0,getNextStarPos(inputToParse));//order ID
		inputToParse = inputToParse.substring((getNextStarPos(inputToParse)+1),inputToParse.length());
		orderPlaced = orderPlaced + " was placed at " + inputToParse.substring(0,getNextStarPos(inputToParse));// Time Placed
		inputToParse = inputToParse.substring((getNextStarPos(inputToParse)+1),inputToParse.length()); 
		String timeCooked =  inputToParse.substring(0,getNextStarPos(inputToParse));//Time Cooked
		inputToParse = inputToParse.substring((getNextStarPos(inputToParse)+1),inputToParse.length());
		orderPlaced = orderPlaced + " by Cashier "+ inputToParse.substring(0,getNextStarPos(inputToParse));//Cashier
		inputToParse = inputToParse.substring((getNextStarPos(inputToParse)+1),inputToParse.length());
		orderPlaced = orderPlaced+ inputToParse.substring(0,getNextStarPos(inputToParse));//Cook
		inputToParse = inputToParse.substring((getNextStarPos(inputToParse)+1),inputToParse.length());
		return orderPlaced;
   	 }
	
	public int getNextStarPos(String input){
		for (int i = 0; i<input.length(); i++){
   		 if(input.charAt(i) == '*'){
   			 return i;
   		 }
   	 }
		return 0;
	}
}
	

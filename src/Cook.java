import java.util.*;
import java.io.*;
import java.net.*;

public class Cook implements Runnable {
	
	//Private Class Variables
	private String cookName; // Cook name used to identify individual cooks.
	private OrderList kitchen; // The kitchen the cook works in
	private Socket cookSocket;
	private PrintStream out;
	private BufferedReader in;
	private int cookID;
	
	Cook(String textCookName, OrderList kitchenObject){
		cookName = textCookName;
		kitchen=kitchenObject;
	}
	
	public void run(){
		while (true){
			try {
				cookSocket = new Socket("127.0.0.1", 8080);
			    out = new PrintStream(cookSocket.getOutputStream());
			    in = new BufferedReader(new InputStreamReader(cookSocket.getInputStream()));
				this.cookOrder();
				String input = "";
				if ((input = in.readLine()) != null){
					System.out.println("Cook: " + parseOrderCompleteReturn(input));
					//System.out.println("Cook got this back: "+input);
				}
			} catch (Exception e) {
				//TODO Handle exception
			}
		}
	}
	
	public String getCookName() {
		return cookName;
	}

	public void setCookName(String cookNameText) {
		cookName = cookNameText;
	}

	public void cookOrder(){
		out.print("2*" + getCookName() + "\n");
		
		Random r = new Random();
		try{
			Thread.sleep(r.nextInt(6999)); // random int used for its value as milliseconds between 0 and 6999.
		}
		catch (Exception e){
		}
		out.print("3*" + getCookName()+"*"+ "\n");
	}
	
	
	public String parseOrderCompleteReturn(String inputToParse){
		String orderPlaced = "Order ";
		orderPlaced = orderPlaced+ inputToParse.substring(0,getNextStarPos(inputToParse));//order ID
		inputToParse = inputToParse.substring((getNextStarPos(inputToParse)+1),inputToParse.length());
		orderPlaced = orderPlaced + " was placed at " + inputToParse.substring(0,getNextStarPos(inputToParse));// Time Placed
		inputToParse = inputToParse.substring((getNextStarPos(inputToParse)+1),inputToParse.length()); 
		String timeCooked =  inputToParse.substring(0,getNextStarPos(inputToParse));//Time Cooked
		inputToParse = inputToParse.substring((getNextStarPos(inputToParse)+1),inputToParse.length());
		orderPlaced = orderPlaced + " by Cashier "+ inputToParse.substring(0,getNextStarPos(inputToParse));//Cashier
		inputToParse = inputToParse.substring((getNextStarPos(inputToParse)+1),inputToParse.length());
		orderPlaced = orderPlaced + " it was completed by Cook " + inputToParse;//Cook
		orderPlaced = orderPlaced + " at " + timeCooked;
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
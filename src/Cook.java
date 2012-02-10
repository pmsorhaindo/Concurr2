import java.util.*;
import java.io.*;
import java.net.*;

public class Cook implements Runnable {
	
	//Private Class Variables
	private String cookName; // Cook name used to identify individual cooks.
	private Socket cookSocket;
	private PrintStream out;
	private BufferedReader in;
	private int cookID;
	private int serverPort;
	private String serverAddress;
	private boolean onDuty;
	int recievedOrderID;
	
	Cook(String textCookName, String newServerAddress, int newServerPort){
		cookName = textCookName;
		serverPort = newServerPort;
		serverAddress = newServerAddress;
		onDuty = true;
		recievedOrderID = -1; // no order
	}
	
	public void run(){
		while (onDuty){
			try {
				cookSocket = new Socket(serverAddress, serverPort);
			    out = new PrintStream(cookSocket.getOutputStream());
			    in = new BufferedReader(new InputStreamReader(cookSocket.getInputStream()));
				this.cookOrder();
				String input = "";
				Thread.sleep(0);
			} catch(InterruptedException e){
				System.out.println("Cook "+cookName+" is signing off.. (the cook wasn't holding any orders at the time)");
				onDuty=false;
			}			
			catch (Exception e) {
				e.printStackTrace();
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
		try{
			recievedOrderID=-1;
			out.print("2*" + getCookName() + "\n");
			String input;
			if ((input = in.readLine()) != null){
				recievedOrderID =  parseOrderRemoveReturn(input);
				//System.out.println("Cook: " + parseOrderRemoveReturn(input));
				Random r = new Random();
				Thread.sleep(r.nextInt(6999)); // random int used for its value as milliseconds between 0 and 6999.
			}
			out.print("3*" + getCookName()+"*"+ recievedOrderID +"\n");
			if ((input = in.readLine()) != null){
			System.out.println("Cook: " + parseOrderCompleteReturn(input));
			}
		}catch(InterruptedException e)
		{
			if (recievedOrderID>0){
			out.print("4*" + recievedOrderID + "\n");
			System.out.println("Cook "+cookName+" is returning " + +recievedOrderID + " back to the ordersList");
			}
			System.out.println("Cook "+cookName+" is signing off..");
			onDuty=false;
		}
		catch (Exception e){
		}
	}
	
	
	public int parseOrderRemoveReturn(String inputToParse){
		int orderPlaced;
		orderPlaced = Integer.parseInt(inputToParse.substring(0,getNextStarPos(inputToParse)));//order ID
		return orderPlaced;
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
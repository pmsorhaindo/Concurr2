import java.net.*;
import java.io.*;
	
public class KitchenComm {
	private OrderList kitchen;
	
	public KitchenComm(OrderList newKitchen) {
		kitchen = newKitchen;
	}
	
    public String processInput(String theInput) {
        String theOutput = null;
        String author = null;
        String action =null;
        
        for (int i = 0; i<theInput.length(); i++){
	        if (theInput.charAt(i) == ' '){
		        author = theInput.substring(i,theInput.length());
		        action = theInput.substring(0,i);
	        }
	        else
	        {
	        	author = "System";
	        	action = "Invalid Request";
	        }
        }
 
        if (action.equals("placeOrder")){
        	kitchen.addOrder(author);
        }
        
        else if (action.equals("cookOrder")){
        	kitchen.removeOrder(author);
        }
        System.out.println("recieved this action "+ action + " frome this author "+ author);
        return theOutput;
    }
}


	


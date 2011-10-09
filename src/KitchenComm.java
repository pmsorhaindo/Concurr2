
public class KitchenComm {
	private OrderList kitchen;
	
	public KitchenComm(OrderList newKitchen) {
		kitchen = newKitchen;
	}
	
    public String processInput(String theInput) {
    	
        String theOutput = null;
        String author = null;
        String action =null;
        
        System.out.println("Stuff: "+theInput);
        
        for (int i = 0; i<theInput.length(); i++){
	        if (theInput.charAt(i) == ' '){
		        author = theInput.substring(i,theInput.length());
		        action = theInput.substring(0,i);
	        }
        }
        if(action == null)
        {
        	author = "System";
        	action = "Invalid Request";
        	theOutput = "fail";
        }
        
        System.out.println("Action: " +action);
        if (action.equals("placeOrder")){
        	kitchen.addOrder(author);
        	System.out.println("Made it!!");
        	theOutput = "win";
        }
        
        else if (action.equals("cookOrder")){
        	kitchen.removeOrder(author);
        	theOutput = "win";
        }
        System.out.println("recieved this action "+ action + " frome this author "+ author);
        return theOutput;
    }
}


	


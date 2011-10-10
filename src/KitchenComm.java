
public class KitchenComm {
	private OrderList kitchen;
	
	public KitchenComm(OrderList newKitchen) {
		kitchen = newKitchen;
	}
	
    public String processInput(String theInput) {
    	
        String theOutput = null;
        String author = null;
        String action =null;
                
        System.out.println("Comm Input: "+theInput);
       
        for (int i = 0; i<theInput.length(); i++){
	        if (theInput.charAt(i) == ' '){
		        author = theInput.substring(i,theInput.length());
		        //author = "Test";
	        	action = theInput.substring(0,i);
	        }
        }
        if(action == null)
        {
        	author = "System";
        	action = "nothing given";
        	theOutput = "doNothing";
        }
        
        if (action.equals("placeOrder")){
        	kitchen.addOrder(author);
        	theOutput = "win";
        }
        
        else if (action.equals("cookOrder")){
        	kitchen.removeOrder(author);
        	theOutput = "win";
        }
        else
        {
        	theOutput = "doNothing";
        }
        //System.out.println("recieved this action "+ action + ", from this author "+ author+".");
        return theOutput;
    }
}

	


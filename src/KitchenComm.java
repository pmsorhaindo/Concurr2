/**
 * Class for managing communication between employees and the kitchen TODO
 * @author mikey
 *
 */
public class KitchenComm {
	private OrderList kitchen;
	private String input = "";
	private String author = "";
	String theOutput = "";
	/**
	 * TODO
	 */
	int orderID = 0;
	
	/** TODO
	 * Constructor ...
	 * @param newKitchen
	 */
	public KitchenComm(OrderList newKitchen) {
		kitchen = newKitchen;
	}
	
	/**
	 * 
	 * @param theInput
	 * @return
	 */
    public String processInput(String theInput) {
       	input = theInput;
       	//System.out.println(theInput);
        int command = Integer.parseInt(theInput.charAt(0)+"");
        //if(command == 4) {System.out.println("yelle time!!");}
        talkToServer(command);
        
        return theOutput;
    }
    
    /**
     * 
     * @param command
     */
    public void talkToServer(int command){
    	Order tempOrder;
    	switch(command)
    	{
    	case 1:
    		
    		parseAuthorName();
    		tempOrder = kitchen.addOrder(author);
    		//System.out.println("yow! set order : "+author);
    		theOutput = tempOrder.deConstructOrder();
    		//System.out.println("post place order: "+theOutput);
    		break;
    		
    	case 2:
    		
    		parseAuthorName();
        	tempOrder = kitchen.removeOrder(author);
        	//System.out.println("yow! try to cook : "+author);
    		theOutput = tempOrder.deConstructOrder();
    		//System.out.println("post attempt cook: "+theOutput);
    		break;
    		
    	case 3:
    		
    		parseAuthorNameOrderID();
    		tempOrder = kitchen.completeOrder(author,orderID);
    		theOutput = tempOrder.deConstructOrder();
    		        	
        	break;	
    	
    	case 4:
    		
    		parseOrderID();
    		System.out.println(" OrderID returned = " + orderID);
    		tempOrder = kitchen.interruptedOrder(orderID);
    		theOutput = tempOrder.deConstructOrder();
    		
        	break;

    	}
    }
    
    /**
     * Parses orderID and sets the kitchens current orderID to the
     * ID found in the input string. This ID is then assigned to the
     * variable orderID
     */
    private void parseAuthorNameOrderID() {
    	input.substring(0,getNextStarPos(input));//order ID
    	input = input.substring((getNextStarPos(input)+1),input.length());
    	author =  input.substring(0,getNextStarPos(input));
    	input = input.substring((getNextStarPos(input)+1),input.length());
    	orderID = Integer.parseInt(input);
	}

	public void parseAuthorName(){
    	 for (int i = 0; i<input.length(); i++){
    		 if(input.charAt(i) == '*'){
    			 author = input.substring(i+1,input.length());    
    		 }
    	 }
    }
	
	public void parseOrderID() {
		input.substring(0,getNextStarPos(input));
		input = input.substring((getNextStarPos(input)+1),input.length());
		orderID = Integer.parseInt(input);
	}
	
	public int getNextStarPos(String input) {
		for (int i = 0; i<input.length(); i++){
   		 if(input.charAt(i) == '*'){
   			 return i;
   		 }
   	 }
		return 0;
	}
    
    
}

	


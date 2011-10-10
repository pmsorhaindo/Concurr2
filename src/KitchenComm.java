
public class KitchenComm {
	private OrderList kitchen;
	private String input = "";
	private String author = "";
	String theOutput = "";
	
	public KitchenComm(OrderList newKitchen) {
		kitchen = newKitchen;
	}
	
    public String processInput(String theInput) {
       	input = theInput;
       	System.out.println(theInput);
        int command = Integer.parseInt(theInput.charAt(0)+"");
        talkToServer(command);
        
        return theOutput;
    }
    
    public void talkToServer(int command){
    	Order tempOrder;
    	switch(command)
    	{
    	case 1:
    		
    		parseAuthorName();
    		tempOrder = kitchen.addOrder(author);
    		theOutput = tempOrder.deConstructOrder();
    		break;
    		
    	case 2:
    		
    		parseAuthorName();
        	tempOrder = kitchen.removeOrder(author);
    		theOutput = tempOrder.deConstructOrder();
    		break;
    		
    	case 3:
        	
        	break;	
    	
    	case 4:
        	
        	break;

    	}
    }
    
    public void parseAuthorName(){
    	 for (int i = 0; i<input.length(); i++){
    		 if(input.charAt(i) == '*'){
    			 author = input.substring(i,input.length());    
    		 }
    	 }
    }
    
    
}

	


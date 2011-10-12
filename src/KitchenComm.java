
public class KitchenComm {
	private OrderList kitchen;
	private String input = "";
	private String author = "";
	String theOutput = "";
	int orderID = 0;
	
	public KitchenComm(OrderList newKitchen) {
		kitchen = newKitchen;
	}
	
    public String processInput(String theInput) {
       	input = theInput;
       	//System.out.println(theInput);
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
        	
        	break;

    	}
    }
    
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
	
	public int getNextStarPos(String input){
		for (int i = 0; i<input.length(); i++){
   		 if(input.charAt(i) == '*'){
   			 return i;
   		 }
   	 }
		return 0;
	}
    
    
}

	


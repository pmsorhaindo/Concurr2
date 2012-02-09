import java.util.*;


public class Staff {

	 protected HashMap<String,Thread> cashiersList;
	 protected HashMap<String,Thread> cooksList;
	 
	public Staff(){
		cashiersList = new HashMap<String, Thread>();
		cooksList = new HashMap<String, Thread>();
		
	}
	
	synchronized public void logOff(String name,boolean cash){
		
		if(cash == true){
					cashiersList.get(name).interrupt();
				}
				else{
					cooksList.get(name).interrupt();
				}
	}
	
	synchronized public boolean logOn(String name, Thread t,boolean cash){
		//System.out.println("currently the name var is: "+name); 
		if(cash == true){
			if(!cashiersList.containsKey(name)&&!cooksList.containsKey(name))
			{
			cashiersList.put(name, t);
			return true;
			}
		}
		else{
			if(!cashiersList.containsKey(name)&&!cooksList.containsKey(name))
			{
			cooksList.put(name, t);
			return true;
			}
		}
		return false;		
	}

}

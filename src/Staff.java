import java.util.*;


public class Staff {
	
	private static int nextCookID = 0;
	private static int nextCashierID = 0;
	protected ArrayList<Order> cooksList;
	protected ArrayList<Order> cashierList;
	 
	public Staff(){
		
		cooksList = new ArrayList<Order>();
		cashierList = new ArrayList<Order>();
		
	}
	
	public int addNewCashier(){
		Staff.nextCashierID = Staff.nextCashierID+1;
		int newID = nextCashierID;
		return newID;
	}
	
	public int addNewCook(){
		Staff.nextCookID = Staff.nextCookID+1;
		int newID = Staff.nextCashierID;
		return newID;
	}

}

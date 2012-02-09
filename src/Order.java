import java.util.*;

public class Order {
	
	private static int nextOrderID = 0;
	private int orderID;
	private String timePlaced;
	private String timeCooked;
	private String cashierName;
	private String cookName;
	
	public Order(String name){
		Order.nextOrderID = Order.nextOrderID+1;
		orderID = nextOrderID;
		cashierName = name;
		Calendar getTime = Calendar.getInstance();
		timePlaced = timeToString(getTime);
		timeCooked = null;
	}
	
	public Order(int i, String signature)
	{
		
	}
	
	public int getOrderID(){
		return orderID;
	}
	
	public String getOrderIDString(){
		String id = Integer.toString(orderID);
		return id;
	}
	
	public String getCashierName(){
		return cashierName;
	}
	
	public String getCookName(){
		return cookName;
	}
	
	public void setCashierName(String name){
		cashierName = name;
	}
	
	public void setCookName(String name){
		cookName = name;
	}
	
	public String getTimePlaced(){
		return timePlaced;
	}
	
	public String getTimeCooked(){
		return timeCooked;
	}
	
	public void setCookedTime(){
		Calendar stopClock = Calendar.getInstance();
		timeCooked = timeToString(stopClock);
	}
	
	public String doubleDigit(int singleDigit){
		String doubleTime = Integer.toString(singleDigit);
		String zero = "0";
		if (doubleTime.length() == 1){
			zero += doubleTime;
			return zero;
		}
		return doubleTime;
	}
	
	public String tripleDigit(int digit){
		String tripleTime = Integer.toString(digit);
		String zero = "0";
		
		if (tripleTime.length() == 1){
			zero = zero + zero + tripleTime;
			return zero;
		}
		if (tripleTime.length() == 2){
			zero += tripleTime;
			return zero;
		}
		return tripleTime;
	}
	
	public String timeToString(Calendar tempTime){
		String timeReturn = doubleDigit(tempTime.get(11))+":"+doubleDigit(tempTime.get(12))+":"
				+doubleDigit(tempTime.get(13))+":"+tripleDigit(tempTime.get(14));
		return timeReturn;
	}
	
	public String deConstructOrder(){
		String signature = getOrderIDString() + "*" + getTimePlaced() + "*" + getTimeCooked() + "*"
				+ getCashierName() + "*" + getCookName();
		return signature;
	}
	
	public String reBuildOrder(String signature){
		//TODO loop through and string splitting by spaces and build an arraylist of parts check arraylist size if correct assign to new order structure.
		return null;
	}

}

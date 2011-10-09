import java.util.*;

public class Order {
	
	private static int nextOrderID = 0;
	private int orderID;
	private Calendar timePlaced;
	private Calendar timeCooked;
	private String cashierName;
	private String cookName;
	
	public Order(String name){
		Order.nextOrderID = Order.nextOrderID+1;
		orderID = nextOrderID;
		cashierName = name;
		timePlaced = Calendar.getInstance();
		timeCooked = null;
	}
	
	public int getOrderID(){
		return orderID;
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
		String time = doubleDigit(timePlaced.get(11))+":"+doubleDigit(timePlaced.get(12))+":"
		+doubleDigit(timePlaced.get(13))+":"+tripleDigit(timePlaced.get(14));
		return time;
	}
	
	public String getTimeCooked(){
		String time = doubleDigit(timeCooked.get(11))+":"+doubleDigit(timeCooked.get(12))+":"
		+doubleDigit(timeCooked.get(13))+":"+tripleDigit(timeCooked.get(14));;
		return time;
	}
	
	public void setCookedTime(){
		timeCooked=Calendar.getInstance();
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

}

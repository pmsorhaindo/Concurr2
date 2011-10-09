import java.util.ArrayList;
import java.io.*;
import java.net.*;

/**
 * Class for managing orders. The class holds a protected array list of orders.
 * @author 
 *
 */
public class OrderList implements Runnable {
	
		 protected ArrayList<Order> ordersList;
		 protected ArrayList<Order> completedList;
		 
	 /**
	  * Constructor with no arguments, create a new empty array list of orders.
	  */
	public OrderList(){
		ordersList = new ArrayList<Order>();
		completedList = new ArrayList<Order>();
	}
	
	 /**
	  * Add a new order to the list and print out the information. 
	  * method will notify cooks waiting to get orders if the list
	  * is currently empty.
	  * @param name of the cashier
	  */
	synchronized public Order addOrder(String cashierName)
	{
		System.out.println("Ow!");
		Order newOrder = new Order(cashierName);
		ordersList.add(newOrder);
		notifyAll();
		return newOrder;
	}
	 
	 /**
	  * Removing an order from the list. The thread accessing this method  
	  * is put on wait if the current list is empty. It will return information
	  * about the order being removed.
	  * @param name of the cook requesting order
	  * @return formatted String containing the information of the order.
	  */
	synchronized public Order removeOrder(String cookName)
	{
		while (ordersList.isEmpty()) {
			try {
				wait();
			}
			catch(Exception e)
			{
				//TODO
			}
		}
		completedList.add(ordersList.get(0));
		ordersList.remove(0);
		completedList.get(completedList.size()-1).setCookName(cookName);
		Order orderComplete = completedList.get((completedList.size()-1));
		return orderComplete;
	}

	
	public void run() {
		//TODO stuff here
		while(true)
		{
			//read line and parse
		}
		
	}
	
	
	public void parseServerCommand(){
		
		
	}
}

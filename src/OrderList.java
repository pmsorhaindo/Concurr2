import java.util.ArrayList;

/**
 * Class for managing orders. The class holds a protected array list of orders.
 * @author Mikey
 */
public class OrderList{
	
		 protected ArrayList<Order> ordersList;
		 protected ArrayList<Order> pendingList;
		 protected ArrayList<Order> completedList;
		 
	 /**
	  * Constructor with no arguments, create a new empty array list of orders.
	  */
	public OrderList(){
		ordersList = new ArrayList<Order>();
		completedList = new ArrayList<Order>();
		pendingList = new ArrayList<Order>();
	}
	
	 /**
	  * Add a new order to the list and print out the information. 
	  * method will notify cooks waiting to get orders if the list
	  * is currently empty.
	  * @param name of the cashier
	  */
	synchronized public Order addOrder(String cashierName)
	{
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
		pendingList.add(ordersList.get(0));
		ordersList.remove(0);
		pendingList.get(pendingList.size()-1).setCookName(cookName);
		Order orderPending = pendingList.get((pendingList.size()-1));
		return orderPending;
	}
	
	/**
	 * TODO
	 * @param cookName
	 * @param orderID
	 * @return
	 */
	synchronized public Order completeOrder(String cookName, int orderID)
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
		
		Order orderToMove = null;
		for (Order checkOrder:pendingList)
		{
			if(checkOrder.getOrderID()==orderID)
			{
				orderToMove = checkOrder;
				//System.out.println("OrderFOUND!");
			}
		}
			
		completedList.add(orderToMove);
		pendingList.remove(orderToMove);
		//System.out.println("Hi orderlist complete was called and the size of the completed list at the time was: " + completedList.size());
		completedList.get(completedList.size()-1).setCookedTime();
		Order orderComplete = completedList.get((completedList.size()-1));//good
		return orderComplete;
	}
	
	/**
	 * from pending list back to ordersList.. when a cook is interrupted.
	 * @param orderID
	 * @return
	 */
	synchronized public Order interruptedOrder(int orderID)
	{
		Order orderToMove = null;
		for (Order checkOrder:pendingList)
		{
			if(checkOrder.getOrderID()==orderID)
			{
				orderToMove = checkOrder;
				//System.out.println("OrderFOUND!");
			}
		}
			
		ordersList.add(0, orderToMove); // re-inserts it at the front of the queue using index 0. (prioritize interrupted orders)
		pendingList.remove(orderToMove);
		Order interruptedOrder = ordersList.get(0);
		return interruptedOrder;
	}
	
	
	
}

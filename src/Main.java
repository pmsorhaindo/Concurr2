
public class Main {
	
	public static void main(String[] args){
		OrderList kitchenYo = new OrderList();
		Thread server1 = new Thread(new Server(kitchenYo), "server1");	
		Thread cashier1 = new Thread(new Cashier("Bob",kitchenYo), "cashier1");
		Thread cook1 = new Thread(new Cook("Fred",kitchenYo), "cook1");
		
		server1.start();
		cashier1.start();
		cook1.start();		
	}
}
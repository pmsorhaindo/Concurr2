import java.util.*;
import java.net.*;
import java.io.*;

public class Cashier implements Runnable {
	
	private static int nextPort = 9000;
	private OrderList kitchen;
	private String cashierName;
	private Socket cashierSocket;
	private PrintStream out;
	private BufferedReader in;
	private InetAddress serverAddress;
	private InetAddress cashierAddress;
	private int port;
	private int listenPort;
	
	public Cashier(String nameText, OrderList kitchenName){
		setCashierName(nameText);
		setKitchen(kitchenName);
		port=Cashier.nextPort+1;
		try{
		serverAddress = InetAddress.getByName("127.0.0.1");
		cashierAddress = InetAddress.getByName("127.0.0.1");
		}
		catch(UnknownHostException e)
		{
		System.err.println("failed to set addresses");
		}
	}
	
	public void run(){
		try {
		    cashierSocket = new Socket(serverAddress, 8080,cashierAddress,port);
		    out = new PrintStream(cashierSocket.getOutputStream());
		    in = new BufferedReader(new InputStreamReader(cashierSocket.getInputStream()));
		    listenPort = cashierSocket.getLocalPort();
		    Thread incoming = new Thread(new Listener(listenPort), "CashierListener");
			incoming.start();
			int i = 0;
			while(i < 30)
			{
				Random r = new Random();
				try {
					Thread.sleep(r.nextInt(4999));
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}			
					out.print("1*"+ getCashierName() + "\n");
					i+=1;
			}
		} catch (UnknownHostException e) {
		    System.err.println("Finding Host fail"); //TODO make this error message nice
		    System.exit(1);
		} catch (IOException e) {
		    System.err.println("O fail");
		    System.exit(1);
		}
		try{
			out.close();
			in.close();
			cashierSocket.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public String getCashierName() {
		return cashierName;
	}

	public void setCashierName(String cashierName) {
		this.cashierName = cashierName;
	}

	public OrderList getKitchen() {
		return kitchen;
	}

	public void setKitchen(OrderList kitchen) {
		this.kitchen = kitchen;
	}
}
	

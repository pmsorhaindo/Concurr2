import java.io.*;
import java.net.*;


public class Server implements Runnable {
	
	private OrderList kitchen;

	public Server(OrderList newKitchen){
		kitchen = newKitchen;
		this.run();
	}
	
	public void run(){
		
		//needs to loop to allow multiple connections..?
		while(true)
		{
			System.out.println("loop?");
			String parseString;
			try {
				parseString = acceptConnection();
				if(parseString!=null)
				{
					parseString(parseString);
				}
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		
		
		/*if(tempOutputString!=null)
		{
			PrintStream SS_PS = new PrintStream(SS_accept.getOutputStream());
			SS_PS.println("Got something");		
		}*/
	}
	
	public String acceptConnection() throws Exception{
		System.out.println("waiting...");
		ServerSocket mySS = new ServerSocket(9999);
		Socket SS_accept = mySS.accept();
		BufferedReader SS_BR = new BufferedReader(new InputStreamReader
				(SS_accept.getInputStream()));
		String OutputString = SS_BR.readLine();
		System.out.println("output?");
		return OutputString;
	}
	
	public void parseString(String parse){
		String action = null;
		String author = null;
		for (int i = 0; i<parse.length(); i++){
			if (parse.charAt(i) == ' '){
				action = parse.substring(0,i);
				author = parse.substring(i,parse.length());
				}
			else{
				return;
			}
		}
		
		if (action.equals("placeOrder")){
		kitchen.addOrder(author);	
		}
		else if (action.equals("removeOrder")){
		kitchen.removeOrder(author);
		}
		else{
			System.out.println("Requesting foolishness from the server");
		}
		
	}
}

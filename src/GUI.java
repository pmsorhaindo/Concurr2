import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;

import javax.swing.*;

public class GUI implements Runnable, ActionListener{
	
	private JFrame window;
	private JPanel panel;
	private JTextField txtEnterName;
	private JTextField txtEnterAddress;
	private JTextField txtEnterPort;
	private JTextField txtEnterServerPort;
	private boolean serverRunning;
	Thread server;
	Staff kitchenStaff;
	
	
	public GUI(){
	
		serverRunning = false;
		window = new JFrame();
		window.setTitle("Concurrent Restaurant");
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window.setSize(520,340);
		window.setLocation(50,50);
		window.setResizable(false);
		
		panel = new JPanel();
		panel.setLayout(null);
		
		window.add(panel);
		
		createLabels();
		createTextBoxes();	
		createButtons();
		
		panel.setVisible(true);
		window.setVisible(true);
		kitchenStaff = new Staff();
	}
	
	public void run(){
		
	}
	
	private void createLabels() {
	
		JLabel title = new JLabel("Restaurant Simulation");
		title.setText("Restaurant Simulation");
		title.setBounds(80, 10, 140, 25);
		panel.add(title);
		
		JLabel enterClientName = new JLabel("Please enter your Name");
		enterClientName.setText("Please enter your Name: ");
		enterClientName.setBounds(10,65,150,25);
		panel.add(enterClientName);
		
		JLabel enterAddress = new JLabel("Target Address: ");
		enterAddress.setBounds(10,95,95,25);
		panel.add(enterAddress);
		
		JLabel enterPort = new JLabel("Target Port: ");
		enterPort.setBounds(10, 125, 75, 25);
		panel.add(enterPort);
		
		JLabel enterServerPort = new JLabel("Server Port (Launch use): ");
		enterServerPort.setBounds(345, 95, 165, 25);
		panel.add(enterServerPort);
	}
	
	private void createTextBoxes() {
		
		txtEnterName = new JTextField();
		txtEnterName.setBounds(155, 65, 110, 25);
		panel.add(txtEnterName);
		
		txtEnterAddress = new JTextField();
		txtEnterAddress.setBounds(155,95,110,25);
		panel.add(txtEnterAddress);
		
		txtEnterPort = new JTextField();
		txtEnterPort.setBounds(155,125,110,25);
		panel.add(txtEnterPort);
		
		txtEnterServerPort = new JTextField();
		txtEnterServerPort.setBounds(370,125,110,25);
		panel.add(txtEnterServerPort);
	}
	
	private void createButtons(){
		
		JButton btnInAsCashier = new JButton("Sign in as Cashier");
		btnInAsCashier.setBounds(10,200,145,30);
		btnInAsCashier.addActionListener(this);
		panel.add(btnInAsCashier);
		JButton btnOffAsCashier = new JButton("Log off as Cashier");
		btnOffAsCashier.setBounds(10,250,145,30);
		btnOffAsCashier.addActionListener(this);
		panel.add(btnOffAsCashier);
		JButton btnInAsCook = new JButton("Sign in as Cook");
		btnInAsCook.setBounds(180, 200, 145, 30);
		btnInAsCook.addActionListener(this);
		panel.add(btnInAsCook);
		JButton btnOffAsCook = new JButton("Log off as Cook");
		btnOffAsCook.setBounds(180, 250, 145, 30);
		btnOffAsCook.addActionListener(this);
		panel.add(btnOffAsCook);
		JButton btnLaunchServer = new JButton("Launch Server");
		btnLaunchServer.setBounds(350,200,145,30);
		btnLaunchServer.addActionListener(this);
		panel.add(btnLaunchServer);
		
	}

	
	public void actionPerformed(ActionEvent e) {
		if (e.getActionCommand().equals("Sign in as Cashier"))
		{
			System.out.println("go cash");
			String newCashierName = txtEnterName.getText();
			String serverAddress = txtEnterAddress.getText();
			String serverPort = txtEnterPort.getText();
			Thread cashier = new Thread(new Cashier(newCashierName,serverAddress,serverPort));
			cashier.setName(newCashierName);
			boolean success = kitchenStaff.logOn(newCashierName, cashier, true);
			if (success == true){
			cashier.start();
			txtEnterName.setText("");
			}
			else{
				System.err.println("The staff name " +newCashierName +" is already in use. Please try entering a unique staff name by adding a number to your name.");
				txtEnterName.setText("");
			}			
		}
		else if (e.getActionCommand().equals("Sign in as Cook"))
		{
			System.out.println("go cook");
			String newCookName = txtEnterName.getText();
			String serverAddress = txtEnterAddress.getText();
			int serverPort = Integer.parseInt(txtEnterPort.getText());
			Thread cook = new Thread(new Cook(newCookName,serverAddress,serverPort));
			cook.setName(newCookName);
			boolean success = kitchenStaff.logOn(newCookName, cook, false);
			if (success == true){
			cook.start();
			txtEnterName.setText("");
			}
			else{
				System.err.println("The staff name " +newCookName +" is already in use. Please try entering a unique staff name by adding a number to your name.");
				txtEnterName.setText("");
			}
		}
		else if (e.getActionCommand().equals("Launch Server"))
		{
			if (serverRunning!=true)
			{
			System.out.println("go serv");
			int listenPort= Integer.parseInt(txtEnterServerPort.getText());
			server = new Thread(new Server(listenPort), "server");
			server.start();
			serverRunning = true;
			}
			else
			{
				System.err.println("A server is already running on this machine.");
			}
		}
		else if (e.getActionCommand().equals("Log off as Cashier"))
		{
			String logOffName = txtEnterName.getText();
			
			kitchenStaff.logOff(logOffName,true);
		}
		else if (e.getActionCommand().equals("Log off as Cook"))
		{
			String logOffName = txtEnterName.getText();
			kitchenStaff.logOff(logOffName,false);
		}
	}
}

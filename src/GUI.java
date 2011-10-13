import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;
public class GUI implements Runnable, ActionListener{
	
	JFrame window;
	JPanel panel;
	
	JTextField txtEnterName;
	JTextField txtEnterAddress;
	JTextField txtEnterPort;
	JTextField txtEnterServerPort;
	
	public GUI(){
		
	window = new JFrame();
	window.setTitle("GrapeJelly");
	window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	window.setSize(520,300);
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
		JButton btnInAsCook = new JButton("Sign in as Cook");
		btnInAsCook.setBounds(180, 200, 145, 30);
		btnInAsCook.addActionListener(this);
		panel.add(btnInAsCook);
		JButton btnLaunchServer = new JButton("Launch Server");
		btnLaunchServer.setBounds(350,200,145,30);
		btnLaunchServer.addActionListener(this);
		panel.add(btnLaunchServer);
		
	}

	
	public void actionPerformed(ActionEvent e) {
		if (e.getActionCommand().equals("Sign in as Cashier"))
		{
			//System.out.println("go cash");
			txtEnterName.getText();
			txtEnterAddress.getText();
			txtEnterPort.getText();
			
		}
		else if (e.getActionCommand().equals("Sign in as Cook"))
		{
			System.out.println("go cook");	
			txtEnterName.getText();
			txtEnterAddress.getText();
			txtEnterPort.getText();
		}
		else if (e.getActionCommand().equals("Launch Server"))
		{
			System.out.println("go serv");
			txtEnterServerPort.getText();
		}
	}
}

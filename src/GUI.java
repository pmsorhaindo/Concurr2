import javax.swing.*;
public class GUI implements Runnable{
	
	JFrame window;
	JPanel panel;
	
	public GUI(){
		
	window = new JFrame();
	window.setTitle("GrapeJelly");
	window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	window.setSize(300,300);
	window.setLocation(50,50);
	
	panel = new JPanel();
	panel.setLayout(null);
	
	window.add(panel);
	
	createLabels();
	createTextBoxes();	
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
		enterClientName.setBounds(10,45,150,25);
		panel.add(enterClientName);
		
		JLabel enterAddress = new JLabel("Target Address: ");
		enterAddress.setBounds(10,75,95,25);
		panel.add(enterAddress);
		
		JLabel enterPort = new JLabel("Target Port: ");
		enterPort.setBounds(10, 105, 75, 25);
		panel.add(enterPort);
		
	}
	
	private void createTextBoxes() {
		
		JTextField txtEnterName = new JTextField();
		txtEnterName.setBounds(155, 45, 110, 25);
		panel.add(txtEnterName);
		
		JTextField txtEnterAddress = new JTextField();
		txtEnterAddress.setBounds(155,75,110,25);
		panel.add(txtEnterAddress);
		
		JTextField txtEnterPort = new JTextField();
		txtEnterPort.setBounds(155,105,110,25);
		panel.add(txtEnterPort);
	}

}

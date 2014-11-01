package ru.spbstu.telematics.domrachev.lab03;

import java.awt.Color;
import java.awt.EventQueue;
import java.lang.reflect.InvocationTargetException;

import javax.swing.DefaultListModel;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

public class MyGUI extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private JPanel contentPane;
	private JLabel lblNewLabel;
	private JLabel lblNewLabel_1;
	private JLabel lblNewLabel_2;
	public DefaultListModel<String> servedCustomersModel;
	public DefaultListModel<String> customersModel;
	public DefaultListModel<String> waitingCustomersModel;
	public DefaultListModel<String> custWithTicketsModel;
	private JList<String> servedCustomers;
	private JList<String> customers;
	private JList<String> waitingCustomers;
	private JList<String> custWithTickets;
	public JLabel curentNumber;
	static MyGUI frame = null;	
	private JLabel lblWithTickets;

	/**
	 * Launch the application.
	 */
	public static MyGUI Init() {		
		try {
			EventQueue.invokeAndWait(new Runnable() {
				public void run() {
					try {
						frame = new MyGUI();
						frame.setVisible(true);
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			});
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println(frame);
		return frame;
	}
	
	public void threadCreated(String s){
		customersModel.addElement(s);
	}
	
	public void threadGotTicket(String s){
		waitingCustomersModel.removeElement(s);
		customersModel.removeElement(s);
		custWithTicketsModel.addElement(s);
	}
	
	public void threadWaiting(String s){
		customersModel.removeElement(s);
		if(!waitingCustomersModel.contains(s)){
			waitingCustomersModel.addElement(s);
		}
	}
	
	public void threadServed(String s){
		custWithTicketsModel.removeElement(s);
		servedCustomersModel.addElement(s);
	}
	
	public void displayNumber(String n){
		curentNumber.setText("Current number: "+n);
	}

	/**
	 * Create the frame.
	 */
	private MyGUI() {
		setTitle("TicketMachineSimulator");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 438, 521);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		lblNewLabel = new JLabel("Customers");
		lblNewLabel.setBounds(5, 5, 139, 15);
		contentPane.add(lblNewLabel);
		
		lblNewLabel_1 = new JLabel("Waiting customers");
		lblNewLabel_1.setBounds(152, 146, 146, 15);
		contentPane.add(lblNewLabel_1);
		
		lblNewLabel_2 = new JLabel("Served");
		lblNewLabel_2.setBounds(297, 5, 146, 15);
		contentPane.add(lblNewLabel_2);
		
		servedCustomersModel = new DefaultListModel<String>();
		customersModel = new DefaultListModel<String>();
		waitingCustomersModel = new DefaultListModel<String>();
		custWithTicketsModel = new DefaultListModel<String>();
		
		servedCustomers = new JList<String>(servedCustomersModel);
		servedCustomers.setBorder(new LineBorder(new Color(0, 0, 0)));
		servedCustomers.setBounds(297, 33, 129, 423);
		contentPane.add(servedCustomers);
		
		customers = new JList<String>(customersModel);
		customers.setBorder(new LineBorder(new Color(0, 0, 0)));
		customers.setBounds(5, 33, 129, 423);
		contentPane.add(customers);
		
		waitingCustomers = new JList<String>(waitingCustomersModel);
		waitingCustomers.setBorder(new LineBorder(new Color(0, 0, 0)));
		waitingCustomers.setBounds(151, 173, 129, 283);
		contentPane.add(waitingCustomers);
		
		curentNumber = new JLabel("Curent number:");
		curentNumber.setBounds(5, 468, 139, 15);
		contentPane.add(curentNumber);
		
		custWithTickets = new JList<String>(custWithTicketsModel);
		custWithTickets.setBorder(new LineBorder(new Color(0, 0, 0)));
		custWithTickets.setBounds(152, 34, 129, 100);
		contentPane.add(custWithTickets);
		
		lblWithTickets = new JLabel("With tickets");
		lblWithTickets.setBounds(156, 7, 146, 15);
		contentPane.add(lblWithTickets);
	}
}

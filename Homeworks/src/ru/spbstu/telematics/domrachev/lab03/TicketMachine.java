package ru.spbstu.telematics.domrachev.lab03;

import java.util.LinkedList;
import java.util.List;
import java.util.Random;

public class TicketMachine implements Runnable{
	
	//number of tickets in machine and max size of queue
	int MaxNumber = 5;
	int NumberOfTicketsGiven;
	public int LastServed;
	//list of sequence numbers of customers that works as locks
	public List<Integer> customers;
	private MyGUI gui;

	public TicketMachine(MyGUI _gui) {
		restartMachine();
		gui = _gui;
	}
	
	@Override
	public void run() {
		//random service time
		final Random r = new Random();
		while(!Thread.interrupted()){
			//imitation of previous customer service delay
			try {
				Thread.sleep(r.nextInt(3000));
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			//don't even try to serve customer with a ticket number which wasn't taken 
			if(LastServed < NumberOfTicketsGiven){
				synchronized (customers.get(LastServed)) {
					System.out.println("Ready to serve: " + (LastServed+1));
					gui.displayNumber(LastServed+1+"");
					customers.get(LastServed).notify();
				}
			}
			if (NumberOfTicketsGiven == MaxNumber && LastServed == NumberOfTicketsGiven){
				restartMachine();
			}
		}
	}
	
	public int currentNumber(){
		return LastServed;
	}
	
	private void restartMachine() {
		System.out.println("---------Restarting machine----------");
		customers = new LinkedList<Integer>();
		for (int i = 1; i <= MaxNumber; i++){
			customers.add(i);
		}
		NumberOfTicketsGiven = 0;
		LastServed = 0;
		synchronized (this) {
			notifyAll();
		}
	}
	
	public void whatInList(){
		System.out.print("in queue now: ");
		for (int i=0; i<customers.size(); i++){
			System.out.print(customers.get(i)+ " ");
		}
		System.out.println();
	}
	
	public Integer getTicket() {
		synchronized (this) {			
			while (NumberOfTicketsGiven == MaxNumber && LastServed < NumberOfTicketsGiven) {
				try {
					System.out.println(Thread.currentThread().getName()+": Waiting for machine to restart");
					gui.threadWaiting(Thread.currentThread().getName());
					wait();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
			synchronized (customers.get(NumberOfTicketsGiven)) {
				Integer ret = customers.get(NumberOfTicketsGiven); 
				NumberOfTicketsGiven += 1;
				gui.threadGotTicket(Thread.currentThread().getName());
				return ret;
			}
		}
	}
	
	public Integer serve() {
		synchronized (customers.get(LastServed)) {
			Integer el = customers.get(LastServed);
			el.notify();
			LastServed++;
			gui.threadServed(Thread.currentThread().getName());
			return el;
		}
	}

}

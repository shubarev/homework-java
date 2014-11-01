package ru.spbstu.telematics.domrachev.lab03;

import java.util.LinkedList;
import java.util.List;
import java.util.Random;

public class TicketMachine implements Runnable{
	
	int MaxNumber = 5;
	int NumberOfTicketsGiven;
	public int LastServed;
	public List<Integer> customers;

	public TicketMachine() {
		restartMachine();
	}
	
	@Override
	public void run() {
		final Random r = new Random();
		while(!Thread.interrupted()){
			try {
				Thread.sleep(r.nextInt(3000));
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			if(LastServed < NumberOfTicketsGiven){
				synchronized (customers.get(LastServed)) {
					System.out.println("Ready to serve: " + (LastServed+1));
					customers.get(LastServed).notify();
				}
			}
			if (NumberOfTicketsGiven == MaxNumber && LastServed == NumberOfTicketsGiven){
				restartMachine();
			}
		}
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
					wait();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
			synchronized (customers.get(NumberOfTicketsGiven)) {
				Integer ret = customers.get(NumberOfTicketsGiven); 
				NumberOfTicketsGiven += 1;
				return ret;
			}
		}
	}
	
	public Integer serve() {
		synchronized (customers.get(LastServed)) {
			Integer el = customers.get(LastServed);
			el.notify();
			LastServed++;
			return el;
		}
	}

}

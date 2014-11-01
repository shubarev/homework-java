package ru.spbstu.telematics.domrachev.lab03;

import java.util.LinkedList;
import java.util.List;
import java.util.Random;

public class TicketMachine implements Runnable{
	
	int MaxNumber = 20;
	int NumberOfTicketsGiven = 0;
	public int LastServed = 0;
	public List<Integer> customers = new LinkedList<Integer>();

	public TicketMachine() {
		for (int i = 1; i <= 20; i++){
			customers.add(i);
		}
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
		}
	}
	
	private void restartMachine() {
		for (int i = 1; i <= 20; i++){
			customers.add(i);
		}
		NumberOfTicketsGiven = -1;
		LastServed = 0;
	}
	
	public void whatInList(){
		System.out.print("in queue now: ");
		for (int i=0; i<customers.size(); i++){
			System.out.print(customers.get(i)+ " ");
		}
		System.out.println();
	}
	
	public Integer getTicket() {
		synchronized (customers.get(NumberOfTicketsGiven)) {			
			while (customers.isEmpty()) {
				try {
					wait();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
			Integer ret = customers.get(NumberOfTicketsGiven); 
			NumberOfTicketsGiven += 1;
			if (NumberOfTicketsGiven > 20){
				restartMachine();
			}
			return ret;
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

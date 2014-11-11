package ru.spbstu.telematics.domrachev.lab03;

import java.util.Random;

public class Launcher {
	static MyGUI gui = MyGUI.Init();
	static TicketMachine tm = new TicketMachine(gui);

	public static void main(String[] args) {
		final Random r = new Random();
		new Thread(tm).start(); //ticket machine
		for (int i = 0; i < 21; i++){
			new Thread(new Runnable() { //customer
				
				Integer myTicket = 0;
				
				@Override
				public void run() {
						gui.threadCreated(Thread.currentThread().getName());
						//delay before start allows to see thread in list before it tries to get a ticket
						synchronized (this) {
							try {
								Thread.sleep(r.nextInt(4000));
							} catch (InterruptedException e) {
								e.printStackTrace();
							}
						}
						//inside getTicket() thread may be forced to wait for a ticket
						myTicket = tm.getTicket();				
						System.out.println("> "+Thread.currentThread().getName() + ": Got ticket " + myTicket);
						//thread must wait for service after it gets a ticket
						synchronized (myTicket) {
								try {
									myTicket.wait();
								} catch (InterruptedException e) {
									e.printStackTrace();
								}
							}
						System.out.println("* "+Thread.currentThread().getName() +"("+myTicket+")"
							+ ": Got service "+ tm.serve());
				}
			}).start();
		}
	}

}

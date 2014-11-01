package ru.spbstu.telematics.domrachev.lab03;

import java.util.Random;

public class Launcher {
	static TicketMachine tm = new TicketMachine();

	public static void main(String[] args) {
		final Random r = new Random();
		new Thread(tm).start();
		for (int i = 0; i < 21; i++){
			new Thread(new Runnable() { //customer
				
				Integer myTicket = 0;
				
				@Override
				public void run() {
						synchronized (this) {
							try {
								Thread.sleep(r.nextInt(4000));
							} catch (InterruptedException e) {
								e.printStackTrace();
							}
						}
						myTicket = tm.getTicket();
						System.out.println("> "+Thread.currentThread().getName() + ": Got ticket " + myTicket);
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

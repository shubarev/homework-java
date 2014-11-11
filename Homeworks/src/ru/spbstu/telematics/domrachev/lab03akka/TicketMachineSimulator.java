package ru.spbstu.telematics.domrachev.lab03akka;
import java.io.Serializable;

import akka.actor.UntypedActor;

public class TicketMachineSimulator {
	public static class GetTicket implements Serializable{
		public final String ticket;
		public GetTicket(String ticket){
			this.ticket = ticket;
		}
	}
	
	public static class Serve implements Serializable{}

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}

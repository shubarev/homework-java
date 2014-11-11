package ru.spbstu.telematics.domrachev.lab03akka;
import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;

import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.actor.Props;
import akka.actor.UntypedActor;

public class TicketMachineSimulator {
	
	public static class TakeTicket implements Serializable{
		private static final long serialVersionUID = 1L;
		public final String ticket;
		public TakeTicket(String ticket){
			this.ticket = ticket;
		}
	}	
	public static class GiveMeTicket implements Serializable{
		private static final long serialVersionUID = 1L;}
	public static class GiveMeService implements Serializable{
		private static final long serialVersionUID = 1L;}
	public static class TakeService implements Serializable{
		private static final long serialVersionUID = 1L;}
	public static class Service implements Serializable{
		private static final long serialVersionUID = 1L;}
	
	public static class TicketMachine extends UntypedActor {
		List<Integer> queue = new LinkedList<Integer>();
		Integer current = 1;

		@Override
		public void onReceive(Object message) throws Exception {
			if (message instanceof GiveMeTicket){
				queue.add(current);
				current++;
				System.out.println(getSelf()+" sending ticket");
				getSender().tell(new TakeTicket("1"), getSelf());
			}
		}
		
	}
	
	public static class ServiceManager extends UntypedActor {

		@Override
		public void onReceive(Object message) throws Exception {
			if (message instanceof GiveMeService){
				System.out.println(getSelf()+" sending service");
				getSender().tell(new Service(), getSelf());
			}
		}
		
	}
	
	public static class Customer extends UntypedActor {
		private String ticket;
		private ActorRef sv;
		
		public Customer(ActorRef tm, ActorRef sv) {
			System.out.println(getSelf()+" ask for ticket");
			tm.tell(new GiveMeTicket(), getSelf());
			this.sv = sv;
		}

		@Override
		public void onReceive(Object message) throws Exception {
			//if (message instanceof TakeService)
			//	pass;
			
			if (message instanceof TakeTicket){
				ticket = ((TakeTicket)message).ticket;
				System.out.println(getSelf()+" got ticket");
				System.out.println(getSelf()+" ask for service");
				sv.tell(new GiveMeService(), getSelf());
			}
			
			if (message instanceof Service)
				System.out.println(getSelf()+" got service");
		}
		
	}

	public static void main(String[] args) {
		final ActorSystem system = ActorSystem.create("helloakka");
		final ActorRef tm = system.actorOf(Props.create(TicketMachine.class), "tm");
		final ActorRef sm = system.actorOf(Props.create(ServiceManager.class), "sm");
		final ActorRef cust1 = system.actorOf(Props.create(Customer.class,tm,sm), "customer1");
		final ActorRef cust2 = system.actorOf(Props.create(Customer.class,tm,sm), "customer2");
		final ActorRef cust3 = system.actorOf(Props.create(Customer.class,tm,sm), "customer3");
	}

}

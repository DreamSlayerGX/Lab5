package saloonEvent;

import java.util.Random;

import saloonState.EventTypes;
import saloonState.SaloonState;
import simulator.Event;
import simulator.Time;

public abstract class CustomerEvent extends Event {

	protected Customer customer;
	protected SaloonState ss;
	protected EventTypes id;
	protected boolean queueing = false;
	
	public CustomerEvent(Time time, boolean newCustomer, SaloonState ss, EventTypes id) {
		super(time);
		this.ss = ss;
		this.id = id;
		if(newCustomer)
			customer = new Customer();
	}
	
	public String toString(){
		String output = 
						"Customer " + customer.getID() + " " + 
						this.getClass().getSimpleName() + " at " + getTime();

		return output;
	}
	
	public EventTypes getEventType(){
		return id;
	} 
	
	public boolean isQueueing(){
		return queueing;
	}
	
	public Customer getCustomer(){
		return customer;
	}
	
//Generates a new time (double) that is used when creating new events
	protected double randomTime(){
		Random r = new Random();
		return 2*r.nextDouble();
		
	}
	  
	
}
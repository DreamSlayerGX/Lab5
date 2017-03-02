package saloonEvent;

import java.util.Random;

import saloonState.SaloonState;
import simulator.Event;
import simulator.Time;

public abstract class CustomerEvent extends Event {

	protected Customer customer;
	protected SaloonState ss;
	
	public CustomerEvent(Time time, boolean newCustomer) {
		super(time);
		if(newCustomer)
			customer = new Customer();
		
	}
	
//Generates a new time (double) that is used when creating new events
	protected double randomTime(){
		Random r = new Random();
		return 2*r.nextDouble();
		
	}
	  
	
}
package saloonEvent;

import saloonState.SaloonState;
import simulator.Event;
import simulator.State;
import simulator.Store;
import simulator.Time;

public abstract class CustomerEvent extends Event {

	protected Customer customer;
	protected SaloonState ss;
	
	public CustomerEvent(Time time, boolean newCustomer) {
		super(time);
		customer = new Customer(newCustomer);
		
	}

	public abstract void execute(Store store);
	  
	
}

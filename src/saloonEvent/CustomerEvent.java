package saloonEvent;

import simulator.Event;
import simulator.State;
import simulator.Store;
import simulator.Time;

public abstract class CustomerEvent extends Event {

	private Customer customer;

	public CustomerEvent(Time time, boolean newCustomer) {
		super(time);
		customer = new Customer(newCustomer);
	}

	public abstract void execute(State state, Store store);
	
	
}

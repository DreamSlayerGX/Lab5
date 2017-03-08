package saloonEvent;

import java.text.DecimalFormat;

import saloonState.SaloonState;
import simulator.Event;
import simulator.Time;

/**
 * An abstract blueprint of an event.
 * 
 * @author Gustav Strömberg, Anton Dahlin, William Antti
 */
public abstract class CustomerEvent extends Event {

	protected Customer customer;
	protected SaloonState ss;
	private DecimalFormat df = new DecimalFormat("#0.00");

	/**
	 * Sets variable for a specific event
	 * @param time Time
	 * @param newCustomer boolean
	 * @param ss SaloonState
	 */
	public CustomerEvent(Time time, boolean newCustomer, SaloonState ss) {
		super(time);
		this.ss = ss;
		if (newCustomer)
			customer = new Customer(ss.getNextID());
	}

	/**
	 * Creates a string that is used in SaloonState to print test to the user.
	 * 
	 * @return String Contaning uppdated data after an event is triggered
	 */
	public String toString() {
		return  "  " + df.format(getTime().getNumTime()) + "\t" + super.toString()
				+ "\t" + customer.getID() + "\t";
	}

	/**
	 * 
	 * @return Customer The customer class
	 */
	public Customer getCustomer() {
		return customer;
	}

}
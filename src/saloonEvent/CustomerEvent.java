package saloonEvent;

import java.text.DecimalFormat;
import java.util.Random;

import saloonState.EventTypes;
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
	protected EventTypes id;
	protected boolean queueing = false;
	private DecimalFormat df = new DecimalFormat("#0.00");
	
	
	
	public CustomerEvent(Time time, boolean newCustomer, SaloonState ss, EventTypes id) {
		super(time);
		this.ss = ss;
		this.id = id;
		if(newCustomer)
			customer = new Customer(ss.getNextID());
	}
	
	
	/**
	 * Creates a string that is used in SaloonState to print test to the user.
	 * 
	 * @return String Contaning uppdated data after an event is triggered
	 */
	public String toString(){
		
		/*old
		String output = 
						"Customer " + customer.getID() + " " + 
						this.getClass().getSimpleName() + " at " + getTime();
		 */
		String output = "  " +
		df.format(getTime().getNumTime()) +"\t" +
		id + "\t" + 
		customer.getID() + "\t" /*+ 
		ss.getIdleChairs() + "\t" + 
		df.format(ss.getStat().getTimeIdle()) + "\t" +
		df.format(ss.getStat().getTimeQueueing()) + "\t" + 
		(ss.getQueue() + ss.returnGetQueue()) + "\t" +
		ss.getStat().getPeopleCut() + "\t" + 
		ss.getStat().getCustomersLost() + "\t" +
		ss.getStat().getCustomersReturned() + "\t"*/;
		
		return output;
	}
	
	/**
	 * 
	 * @return EventType The identity of an event
	 */
	public EventTypes getEventType(){
		return id;
	} 
	
	/**
	 * 
	 * @return boolean True if the person is queueing, otherwise false
	 */
	public boolean isQueueing(){
		return queueing;
	}
	
	/**
	 * 
	 * @return Customer The customer class
	 */
	public Customer getCustomer(){
		return customer;
	}
	
	  
	
}
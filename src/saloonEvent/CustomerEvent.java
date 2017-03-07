package saloonEvent;

import java.text.DecimalFormat;
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
	private DecimalFormat df = new DecimalFormat("#0.00");
	
	public CustomerEvent(Time time, boolean newCustomer, SaloonState ss, EventTypes id) {
		super(time);
		this.ss = ss;
		this.id = id;
		if(newCustomer)
			customer = new Customer();
	}
	
	public String toString(){
		
		/*old
		String output = 
						"Customer " + customer.getID() + " " + 
						this.getClass().getSimpleName() + " at " + getTime();
		 */
		String output = "  " +
		df.format(getTime().getNumTime()) +"\t" +
		id + "\t" + 
		customer.getID() + "\t" + 
		df.format(ss.getIdleChairs()) + "\t" + 
		df.format(ss.getStat().getTimeIdle()) + "\t" +
		df.format(ss.getStat().getTimeQueueing()) + "\t" + 
		(ss.getQueue() + ss.returnGetQueue()) + "\t" +
		ss.getStat().getPeopleCut() + "\t" + 
		ss.getStat().getCustomersLost() + "\t" +
		ss.getStat().getCustomersReturned() + "\t";
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
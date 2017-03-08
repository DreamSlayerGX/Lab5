package saloonEvent;

import saloonState.EventTypes;
import saloonState.SaloonState;
import simulator.State;
import simulator.Store;
import simulator.Time;

/**
 * Enter is an event that represents when a customer enters the store. Mainly two things
 * will happen: 1) Get sited in a chair; 2) stand in queue.
 * 
 * @author Anton, Gustav, William
 * */
public class Enter extends CustomerEvent {
	
	
	/**
	 * Constructor that calls the parent's constructor to set time and creating a
	 * new customer that is connected to this event.
	 *
	 * @param time to set time to new event
	 * @param ss to be able to send request to SaloonState in order to update data during the simulation
	 * @param id what type of event it is
	 * */
	public Enter(Time time, SaloonState ss, EventTypes id){
		super(time, true, ss, id); //true indicates that this is a new customer
		this.addObserver(ss);
		
	} 
	
	/**
	 * Executes an event. When it executes it also creates a new event of Enter that sets
	 * the next Enter. If there are available chairs (see SaloonState), this method creates
	 * a Ready event.
	 * 
	 * @param Time Time-object to set time to new events
	 * @param Store To store new object in the store-array
	 * */
	
	public void execute(Store store) {
		double nextTime = getTime().getNumTime() + ss.nextEnter();
		if(nextTime < ss.getCloseTime()){
			store.storeEvent(new Enter(
					new Time(nextTime),
					ss, 
					id));
		}
			

		if(ss.getChairs() == 0 && (ss.returnGetQueue()+ss.getQueue() < ss.getQueueSize())){
			queueing = true;
			ss.addToQueue(customer);
			customer.startQueueTime(getTime().getNumTime());
		
		} else if(ss.getChairs() > 0){
				ss.occupyChair();
				customer.setCuttingTime(getTime().getNumTime());
				store.storeEvent(new Ready(
						new Time(getTime().getNumTime() + ss.nextRandCutTime()),
						super.customer,
						ss,
						EventTypes.READY));
		} else {
			customer.setLeavingCustomer(true);
			ss.addCustomerLost();
		}
		setChanged();
		notifyObservers(this);
		
	
	}
	public void execute(Store store, State state) {
		ss = (SaloonState) state;
		execute(store);
	}
	
	


}

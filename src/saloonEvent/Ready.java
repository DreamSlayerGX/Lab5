package saloonEvent;

import simulator.Store;
import simulator.Time;

/**
 * @author A, G, W
 * */
public class Ready extends CustomerEvent{
	
	
	/**
	 * Constructor that calls the parent's constructor to set time and creating a
	 * new customer that is connected to this event.
	 * 
	 * @param Time A Time-object.
	 * */
	public Ready(Time time, Customer customer) {
		super(time, false);
		super.customer = customer;
		
		
	}
	
	
	/**
	 * Executes an event. Removes customer from cutting chair, checks if people are in the queues to replace the customer on the chair, 
	 * this method sometimes randomly creates a return event.
	 * 
	 * @param Time Time-object to set time to new events
	 * @param Store To store new object in the store-array
	 * */
	public void execute(Store store) {
		ss.freeChair();
		
		if(ss.returngetQueue() > 0){
			ss.rmFirstInReturnQueue();
			ss.occupyChair();
		}
		else{
			if(ss.getQueue() > 0){
				ss.rmFirstInQueue();;
				ss.occupyChair();
			}
			
		}
		customer.setCuttingTime(getTime());
		
	
	//20 % chance that the customer will return
		if(randomTime()/2 < 0.2)
			store.storeEvent(new Return(new Time(getTime().getNumTime() + randomTime()/2)),
					super.customer);
			
		
		
		
	}

}

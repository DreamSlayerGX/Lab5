package saloonEvent;

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
	 * @param Time A Time-object.
	 * */
	public Enter(Time time){
		super(time, true);
		
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
		store.storeEvent(new Enter(new Time(getTime().getNumTime() + randomTime())));
		
		//Nu är closetiden i SaloonState
		if(getTime().getNumTime() < ss.getCloseTime()){
			if(ss.getChairs() == 0){
					ss.addToQueue(customer);
					customer.startQueueTime(getTime());
			
			} else {
				ss.occupyChair();
				store.storeEvent(new Ready(new Time(getTime().getNumTime() + randomTime()),
					super.customer));
			}
		}
		else{
			ss.numLostCounter();
		}
	}
	
	


}

package saloonEvent;

import saloonState.SaloonState;
import simulator.State;
import simulator.Store;
import simulator.Time;

/**
 * Marks that a customer is cut and a new chair is available
 * 
 * @author Anton, Gustav, William
 * */
public class Ready extends CustomerEvent {

	/**
	 * Constructor that calls the parent's constructor to set time and creating
	 * a new customer that is connected to this event.
	 * 
	 * @param Time
	 *            a Time-object.
	 * */
	public Ready(Time time, Customer customer, SaloonState ss) {
		super(time, false, ss);
		super.customer = customer;


	}

	/**
	 * Executes an event. Removes customer from cutting chair, checks if people
	 * are in the queues to replace the customer on the chair, this method
	 * sometimes randomly creates a return event.
	 * 
	 * @param Time
	 *            time-object to set time to new events
	 * @param Store
	 *            to store new object in the store-array
	 * */
	public void execute(Store store, State state) {
		
		ss = (SaloonState) state;
		customer.endCutTime(getTime().getNumTime());
		ss.freeChair();
		
		if (customer.getSatisfied()) {
			ss.addPeopleCut();
		}	
		
		
		if(ss.getQueue() + ss.returnGetQueue() > 0){
			Customer nextCustomer;
			
			if(ss.returnGetQueue() > 0){
				nextCustomer = ss.returnqueuearray();
				ss.rmFirstInReturnQueue();
				
			} else {
				nextCustomer = ss.queuearray();
				ss.rmFirstInQueue();
				
			}
			nextCustomer.endQueueTime(getTime().getNumTime());
			nextCustomer.setCuttingTime(getTime().getNumTime());
			
			store.storeEvent(new Ready(
					new Time(getTime().getNumTime() + ss.nextRandCutTime()),
					nextCustomer,
					ss));
			
			ss.occupyChair();
		}


		if (ss.nextRandSatisfied()) {

			store.storeEvent(new Return(
					new Time(getTime().getNumTime() + ss.nextRandReturnTime()),
					customer, ss));

		} else {
			ss.addTimeCutting(customer.getCutTime());
			customer.setSatisfied(true);
		}
	}
	

}

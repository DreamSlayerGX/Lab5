package saloonEvent;

import saloonState.EventTypes;
import saloonState.SaloonState;
import simulator.State;
import simulator.Store;
import simulator.Time;

/**
 * @author Anton, Gustav, William
 * */
public class Ready extends CustomerEvent{
	
	
	/**
	 * Constructor that calls the parent's constructor to set time and creating a
	 * new customer that is connected to this event.
	 * 
	 * @param Time A Time-object.
	 * */
	public Ready(Time time, Customer customer, SaloonState ss, EventTypes id) {
		super(time, false, ss, id);
		super.customer = customer;
		this.addObserver(ss);
		
		
	}
	
	
	/**
	 * Executes an event. Removes customer from cutting chair, checks if people are in the queues to replace the customer on the chair, 
	 * this method sometimes randomly creates a return event.
	 * 
	 * @param Time Time-object to set time to new events
	 * @param Store To store new object in the store-array
	 * */
	public void execute(Store store) {
		//System.out.println("customer "+customer.getID() +" ready at "+getTime());
		customer.endCutTime(getTime().getNumTime());	

		ss.freeChair();
		

		if(ss.returnGetQueue() > 0){
			Customer tmp = super.ss.returnqueuearray();
			tmp.endQueueTime(getTime().getNumTime());
			tmp.setCuttingTime(getTime().getNumTime());

			store.storeEvent(new Ready(
					new Time(getTime().getNumTime() + ss.nextRandCutTime()),
					tmp,
					ss,
					EventTypes.READY));

			ss.rmFirstInReturnQueue();
			ss.occupyChair();

					
			
		} else if(ss.getQueue() > 0){
			Customer tmp = super.ss.queuearray();
			tmp.endQueueTime(getTime().getNumTime());
			tmp.setCuttingTime(getTime().getNumTime());
			store.storeEvent(new Ready(
					new Time(getTime().getNumTime() + ss.nextRandCutTime()),
					tmp,
					ss,
					EventTypes.READY));
			
			ss.rmFirstInQueue();;
			ss.occupyChair();	
			
		}
			

		if(ss.nextRandSatisfied()){

			store.storeEvent(new Return(new Time(getTime().getNumTime() + ss.nextRandReturnTime()),
					super.customer,
					ss,
					EventTypes.RETURN));
			
		} else {
			customer.setSatisfied(true);
		}
		
		setChanged();
		notifyObservers(this);
	}


	
	public void execute(Store store, State state) {
		ss = (SaloonState) state;
		execute(store);
	}

}

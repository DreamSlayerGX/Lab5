package saloonEvent;

import saloonState.EventTypes;
import saloonState.SaloonState;
import simulator.State;
import simulator.Store;
import simulator.Time;


/**
 * Creates a customer that is not satisfied and will be needed to get a haircut again.
 * 
 * @author Gustav Strömberg, Anton Dahlin, William Antti
 *
 */
public class Return extends CustomerEvent{

	
	/**
	 * 
	 * @param time to set time to new event
	 * @param customer to get (and set) information regarding this specific customer
	 * @param ss to be able to send request to SaloonState in order to update data during the simulation
	 * @param id what type of event it is
	 */
	public Return(Time time, Customer customer, SaloonState ss, EventTypes id) {
		super(time, false, ss, id);
		super.customer = customer;
		this.addObserver(ss);
		
	}


	/**
	 * If a chair is free, occupy a chair. If there is space in queue, place customer last in return queue. 
	 * If queue is full of returning customers, take a walk (create new return) and come back.
	 * 
	 * @param store the store object so new event can be created
	 */
	public void execute(Store store) {
		//System.out.println("customer "+customer.getID() +" returns at "+getTime());

		//om ledig stol finns
		if(ss.getChairs() > 0){
			ss.occupyChair();

			customer.setCuttingTime(getTime().getNumTime());

			store.storeEvent(new Ready(
					new Time(getTime().getNumTime() + ss.nextRandCutTime()),
					super.customer,
					ss,
					EventTypes.READY));
			

		}else if(ss.returnGetQueue()+ss.getQueue() < ss.getQueueSize()){//det finns plats i k�n
			ss.addToReturnQueue(customer);
			customer.startQueueTime(getTime().getNumTime());
			
		}else if(ss.returnGetQueue()+ss.getQueue() >= ss.getQueueSize()){//full k�

			if(ss.getQueue() > 0){//det finns någon i den vanliga kön
				ss.rmLastInQueue().setLeavingCustomer(true); // Sets the removed customer to "leaving"
				ss.addToReturnQueue(customer);
				customer.startQueueTime(getTime().getNumTime());
				
			}else{//return kön är full
				store.storeEvent(new Return(
						new Time(getTime().getNumTime() + ss.nextRandReturnTime()),
						ss.rmLastInQueue(),
						ss,
						id));
			}
			
			
		}
		setChanged();
		notifyObservers(this);
	}


	public void execute(Store store, State state) {
		ss = (SaloonState) state;
		execute(store);
	}

}
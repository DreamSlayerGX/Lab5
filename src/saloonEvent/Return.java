package saloonEvent;

import saloonState.SaloonState;
import simulator.State;
import simulator.Store;
import simulator.Time;

public class Return extends CustomerEvent{

	public Return(Time time, Customer customer) {
		super(time, false);
		super.customer = customer;
		
	}


	/**
	 * If a chair is free, occupy a chair. If there is space in queue, place customer last in return queue. If queue is full of returning customers, take a walk(create new return) and come back
	 */
	public void execute(Store store) {
		System.out.println("customer "+customer.getID() +" returns at "+getTime());

		//om ledig stol finns
		if(ss.getChairs() > 0){
			ss.occupyChair();
			store.storeEvent(new Ready(new Time(getTime().getNumTime() + randomTime()), super.customer));
		}else if(ss.returngetQueue()+ss.getQueue() < ss.getQueueSize()){//det finns plats i kön
			ss.addToReturnQueue(customer);
			customer.startQueueTime(getTime());
		}else if(ss.returngetQueue()+ss.getQueue() >= ss.getQueueSize()){//full kö
			if(ss.getQueue() > 0){//det finns någon i den vanliga kön
				ss.rmLastInQueue();
				ss.addToReturnQueue(customer);
				customer.startQueueTime(getTime());
			}else{//return kön är full
				store.storeEvent(new Return(new Time(getTime().getNumTime() + randomTime()), ss.rmLastInQueue()));
			}
		}
	}


	public void execute(Store store, State state) {
		ss = (SaloonState) state;
		execute(store);
	}

}
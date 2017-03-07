package saloonEvent;

import saloonState.EventTypes;
import saloonState.SaloonState;
import simulator.State;
import simulator.Store;
import simulator.Time;

public class Return extends CustomerEvent{

	public Return(Time time, Customer customer, SaloonState ss, EventTypes id) {
		super(time, false, ss, id);
		super.customer = customer;
		this.addObserver(ss);
		
	}


	/**
	 * If a chair is free, occupy a chair. If there is space in queue, place customer last in return queue. If queue is full of returning customers, take a walk(create new return) and come back
	 */
	public void execute(Store store) {
		//System.out.println("customer "+customer.getID() +" returns at "+getTime());

		//om ledig stol finns
		if(ss.getChairs() > 0){
			ss.occupyChair();

			customer.setCuttingTime(getTime().getNumTime());

			store.storeEvent(new Ready(
					new Time(getTime().getNumTime() + randomTime()),
					super.customer,
					ss,
					EventTypes.READY));
			

		}else if(ss.returnGetQueue()+ss.getQueue() < ss.getQueueSize()){//det finns plats i k�n
			ss.addToReturnQueue(customer);
			customer.startQueueTime(getTime().getNumTime());
			
		}else if(ss.returnGetQueue()+ss.getQueue() >= ss.getQueueSize()){//full k�

			if(ss.getQueue() > 0){//det finns n�gon i den vanliga k�n
				ss.rmLastInQueue();
				ss.addToReturnQueue(customer);
				customer.startQueueTime(getTime().getNumTime());
				
			}else{//return k�n �r full
				store.storeEvent(new Return(
						new Time(getTime().getNumTime() + randomTime()),
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
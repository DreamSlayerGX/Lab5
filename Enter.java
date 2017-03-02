package saloonEvent;

import simulator.State;
import simulator.Store;
import simulator.Time;

public class Enter extends CustomerEvent {
	
	
	
	public Enter(Time time){
		super(time, true);
		
	} 

//Enter skapar nya enter ?
	
	public void execute(Store store) {
		store.storeEvent(new Enter(new Time(getTime()+1));
		if(ss.getChairs() == 0){
			ss.addToQueue(customer);
			customer.startQueueTime(getTime());
		} else
			ss.occupyChair();
	
	}

}
package saloonEvent;

import simulator.Store;
import simulator.Time;

public class Ready extends CustomerEvent{
	
	public Ready(Time time, Customer customer) {
		super(time, false);
		super.customer = customer;
		
		
	}

	
	public void execute(Store store) {
		
		customer.setCuttingTime(getTime());
		
	
	//20 % chance that the customer will return
		if(randomTime()/2 < 0.2)
			store.storeEvent(new Return(new Time(getTime().getNumTime() + randomTime()/2)),
					super.customer);
		
		
	}

}

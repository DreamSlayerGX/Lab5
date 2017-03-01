package saloonEvent;

import simulator.State;
import simulator.Store;
import simulator.Time;

public class Ready extends CustomerEvent{
	
	public Ready() {
		super(new Time(), false);
		
		
	}

	
	public void execute(State state, Store store) {
		
	}

}

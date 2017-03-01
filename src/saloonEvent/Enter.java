package saloonEvent;

import simulator.State;
import simulator.Store;
import simulator.Time;

public class Enter extends CustomerEvent {
	
	
	
	public Enter(){
		super(new Time(), true);
		
	}

	
	public void execute(State state, Store store) {
		
	}

}

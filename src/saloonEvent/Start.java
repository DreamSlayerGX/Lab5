package saloonEvent;

import simulator.Event;
import simulator.State;
import simulator.Store;
import simulator.Time;

public class Start extends Event {

	
	public Start(Time time) {
		super(time);
	}

	public void execute(Store store) {

	}

	@Override
	public void execute(Store store, State state) {
		// TODO Auto-generated method stub
		
	}

}
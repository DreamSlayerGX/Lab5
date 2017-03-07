package saloonEvent;

import java.text.DecimalFormat;

import simulator.Event;
import simulator.State;
import simulator.Store;
import simulator.Time;

public class Closing extends Event {
	
	private DecimalFormat df = new DecimalFormat("#0.00");

	public Closing(Time time) {
		super(time);
	}


	public void execute(Store store, State state) {
		System.out.println("  " + df.format(getTime().getNumTime()) +" (CLOSING)");
		
	}

}

package saloonEvent;

import java.text.DecimalFormat;

import saloonState.EventTypes;
import simulator.Event;
import simulator.State;
import simulator.Store;
import simulator.Time;

public class Closing extends Event {
	private EventTypes id;
	private DecimalFormat df = new DecimalFormat("#0.00");

	public Closing(Time time, EventTypes id) {
		super(time);
		this.id = id;
	}


	public void execute(Store store, State state) {
		//System.out.println("  " + df.format(getTime().getNumTime()) +" (CLOSING)");
		
	}
	public String toString(){
		return "  (CLOSING)";
	}
	public EventTypes getEventType(){
		return id;
	}
}

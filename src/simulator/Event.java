package simulator;

import java.util.Observable;

/**
 * Generall event object used for executing the simulation
 * 
 * @author Anton, Gustav, William
 */
public abstract class Event{

	private Time time;

	/**
	 * 
	 * @param time the event's time object
	 */
	public Event(Time time) {
		this.time = time;

	}

	/**
	 * @return Time the event's time object
	 */
	public Time getTime() {
		return time;
	}
	public String toString(){
		return getClass().getSimpleName();
	}

	/**
	 * Executes an event, is defined in subclasses.
	 * 
	 * @param store to store new events
	 * @param state to change the current state of the simulator
	 */
	public abstract void execute(Store store, State state);

}

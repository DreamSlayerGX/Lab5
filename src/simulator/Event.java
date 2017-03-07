package simulator;

import java.util.Observable;

/**
 * 
 * @author Anton, Gustav, William
 *
 */
public abstract class Event extends Observable {
	
	private Time time;
	
	public Event(Time time){
		this.time = time;
		
	}
	
	public Time getTime(){
		return time;
	}
	
	public abstract void execute(Store store, State state);
	


}

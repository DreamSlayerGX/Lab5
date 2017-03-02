package simulator;
/**
 * 
 * @author A, G, W
 *
 */
public abstract class Event {
	
	private Time time;
	
	public Event(Time time){
		this.time = time;
		
	}
	
	public Time getTime(){
		return time;
	}
	
	public abstract void execute(Store store);
	
	/**
	 * 
	 * toString override. Returns the class name
	 */
	public String toString(){
		return this.getClass().getSimpleName();
	}

}

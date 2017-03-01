package simulator;

public abstract class Event {
	
	private Time time;
	
	public Event(Time time){
		this.time = time;
		
	}
	
	public Time getTime(){
		return time;
	}
	
	public abstract void execute(State state, Store store);

}

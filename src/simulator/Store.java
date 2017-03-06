package simulator;

import java.util.LinkedList;
/**
 * Contains a list of events sorted by time, low to high
 * @author anodah-6
 *
 */
public class Store {
	private LinkedList<Event> eventList;
	/**
	 * Initializes
	 */
	public Store(){
		eventList = new LinkedList<Event>();
	}
	/**
	 * adds a new event to the list, placed depenting on time of the event
	 * 
	 * @param Event 
	 */
	public void storeEvent(Event event){
		int place = 0;		
		for(Event e : eventList){
			if(event.getTime().getNumTime() < e.getTime().getNumTime()){
				break;
			}
			place++;
		}
		eventList.add(place, event);
		
	}
	/**
	 * @return Event removes and returns the first event in the list
	 */
	public Event nextEvent(){	
		return eventList.poll();
	}
}
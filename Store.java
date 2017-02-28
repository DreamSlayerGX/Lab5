package simulator;

import java.util.ArrayList;
import java.util.Iterator;
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
		eventList = new LinkedList;
	}
	/**
	 * adds a new event to the list, placed depenting on time of the event
	 */
	public void storeEvent(Event event){
		int place = 0;
		for(Event e : eventList){
			if(event.getTime() < e.getTime()){
				break;
			}
			place++;
		}
		eventList.add(place, event);
	}
	/**
	 * returns and removes the first event in the list
	 */
	public Event nextEvent(){	
		return eventList.poll();
	}
}

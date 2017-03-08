package simulator;

import java.util.LinkedList;

/**
 * Contains a list of events sorted by time, low to high
 * 
 * @author Anton
 * 
 */
public class Store {
	private LinkedList<Event> eventList;

	/**
	 * Initializing a new list to store events
	 */
	public Store() {
		eventList = new LinkedList<Event>();
	}

	/**
	 * adds a new event to the list, placed depenting on time of the event
	 * 
	 * @param Event new events that will be added to the event list
	 */
	public void storeEvent(Event event) {
		int place = 0;
		for (Event e : eventList) {
			if (event.getTime().getNumTime() < e.getTime().getNumTime()) {
				break;
			}
			place++;
		}
		eventList.add(place, event);

	}

	/**
	 * @return Event removes and returns the first event in the list
	 */
	public Event nextEvent() {
		return eventList.poll();
	}
	
	/**
	 * @return int current size of the event list
	 */
	public int getEventListSize(){
		return eventList.size();
	}
}
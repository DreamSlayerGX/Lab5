package saloonState;
import java.util.Observable;

import saloonEvent.CustomerEvent;
import simulator.State;

public class View extends State{
	
	public void output(String output){
		System.out.println(output);
	}
	
	public void update(Observable o, Object arg) {
		output(o.toString());
		CustomerEvent ce = (CustomerEvent) arg;
		
		if(ce.getEventType() == EventTypes.ENTER){
			
			//if()
		
			
			
			
		}
		
		if(ce.getEventType() == EventTypes.READY){
			
			
			
		}
		
		if(ce.getEventType() == EventTypes.RETURN){
			
			
		}
		
	}

}

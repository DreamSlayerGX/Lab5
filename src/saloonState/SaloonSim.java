package saloonState;
import saloonEvent.Enter;
import simulator.Event;
import simulator.Simulator;
import simulator.State;
import simulator.Store;
import simulator.Time;

public class SaloonSim extends Simulator{
	private Store store;
	private SaloonState state;
	
	public static void main(String[] args){	
		System.out.println("start");
		SaloonSim sim = new SaloonSim();
		sim.run();
	}
	public SaloonSim(){
		store = new Store();
		state = new SaloonState(20,20);
	}
	public void run(){
		store.storeEvent(new Enter(new Time(.5), state, EventTypes.ENTER));

		while(!state.getFlag()){
			
			Event nxt = store.nextEvent();
			if(nxt != null){
				nxt.execute(store, state);
			}
			
		}	
	}
}

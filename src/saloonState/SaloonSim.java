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
		SaloonSim sim = new SaloonSim(7.0, 2,2, 1.2, 1.0, 2.0, 1.0, 2.0,0.5,1116);
		
		sim.run();
	}
	public SaloonSim(double closingTime, int chairs, int queue, double enterRate, double hmin, double hmax, double dmin, double dmax, double p, int seed){
		store = new Store();
		state = new SaloonState(queue, chairs);
		printSimInfo();
		System.out.println("Closing time of the day ..............: " + closingTime);
		System.out.println("Total number of chairs ...............: " + chairs);
		System.out.println("Maximum queue size ...................: " + queue);
		System.out.println("Lambda (customers/timeunit entering)..: " + enterRate);
		System.out.println("hmin and hmax (cutting time interval) : [" + hmin + ", " + hmax + "]");
		System.out.println("dmin and dmax (return time interval) .: [" + dmin + ", " + dmax + "]");
		System.out.println("Risk dissatisfied returns: ...........: " + p);
		System.out.println("Seed used in pseudo random generator .: " + seed);
		System.out.println("----------------------------------------------------------------");
	}
	public void run(){
		store.storeEvent(new Enter(new Time(.5), state, EventTypes.ENTER));

		while(!state.getFlag()){
			
			Event nxt = store.nextEvent();
			if(nxt != null){
				nxt.execute(store, state);
			}
			if(store.returnlist().size() == 0){
				state.setFlag(true);
			}
			
		}	
	}
	private void printSimInfo(){
		
	}
	
}

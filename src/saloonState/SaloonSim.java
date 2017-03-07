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
		SaloonSim sim = new SaloonSim(7.0, 1,1, 1.2, 1.0, 2.0, 1.0, 2.0,0.5,1116);
		
		sim.run();
	}
	/**
	 * 
	 * @param closingTime
	 * @param chairs
	 * @param queue
	 * @param enterRate
	 * @param hmin
	 * @param hmax
	 * @param dmin
	 * @param dmax
	 * @param p
	 * @param seed
	 */
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
		System.out.println("-----------------------------------------------------------------------------");
		System.out.println("- Time  Event   Id      Idle    TIdle   TWait   InQ     Cut     Lost    Ret -");
	}
	/**
	 * 
	 */
	public void run(){
		store.storeEvent(new Enter(new Time(.5), state, EventTypes.ENTER));

		while(!state.getFlag()){
			
			Event nxt = store.nextEvent();
			if(nxt != null){
				nxt.execute(store, state);
			}
			if(store.returnlist().size() == 0){
				state.setFlag(true);
				System.out.println("-----------------------------------------------------------------------------");
				System.out.println("Number of customers cut: ......: " + state.getStat().getPeopleCut());
				System.out.println("Average cutting time...........: " );
				System.out.println("Average queueing time: ........: " + state.getStat().getAvrageQueueingTime());
				System.out.println("Largest queue (max NumWaiting) : ");
				System.out.println("Customers not cut (NumLost) ...: ");
				System.out.println("Dissatisfied customers: .......: ");
				System.out.println("Time chairs were idle: ........: ");
				

			}
			
		}	
	}
	private void printSimInfo(){
		
	}
	
}

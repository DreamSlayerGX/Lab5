package saloonState;
import saloonEvent.Closing;
import saloonEvent.Enter;
import simulator.Event;
import simulator.Simulator;
import simulator.Store;
import simulator.Time;

public class SaloonSim extends Simulator{
	private Store store;
	private SaloonState state;
	
	public static void main(String[] args){	
		//SaloonSim sim = new SaloonSim(7.0, 2,2, 1.2, 1.0, 2.0, 1.0, 2.0, 0.5, 1116);
		SaloonSim sim = new SaloonSim(8.0, 2, 5, 2.0, 0.8, 1.2, 1.0, 2.0, 0.1, 1234);
		
		sim.run();
	}
	public SaloonSim(double closingTime, int chairs, 
			int queue, double enterRate, 
			double hmin, double hmax, 
			double dmin, double dmax, 
			double p, long seed){
		store = new Store();
		state = new SaloonState(queue, chairs, enterRate, hmin, hmax, dmin, dmax, p, seed, closingTime);
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
	public void run(){
		store.storeEvent(
				new Enter(new Time(state.nextEnter()),state,
				EventTypes.ENTER));
		store.storeEvent(new Closing(new Time(state.getCloseTime())));

		while(!state.getFlag()){
			
			Event nxt = store.nextEvent();
			if(nxt != null){
				System.out.print(nxt);
				state.updateStats(nxt.getTime().getNumTime());
				System.out.println(state);
				
				nxt.execute(store, state);
			}
			if(store.returnlist().size() == 0){
				state.setFlag(true);
				System.out.println("-----------------------------------------------------------------------------");
				System.out.println("Number of customers cut: ......: " + state.getStat().getPeopleCut());
				System.out.println("Average cutting time...........: ");
				System.out.println("Average queueing time: ........: "+state.getStat().getAvrageQueueingTime());
				System.out.println("Largest queue (max NumWaiting) : ");
				System.out.println("Customers not cut (NumLost) ...: "+state.getStat().getCustomersLost());
				System.out.println("Dissatisfied customers: .......: "+ state.getStat().getCustomersReturned());
				System.out.println("Time chairs were idle: ........: "+state.getStat().getTimeIdle());
			}
			
		}	
	}
	private void printSimInfo(){
		
	}
	
}

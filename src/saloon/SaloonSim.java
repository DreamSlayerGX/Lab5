package saloon;

import java.text.DecimalFormat;

import saloonEvent.Enter;
import saloonState.SaloonState;
import simulator.Event;
import simulator.Simulator;
import simulator.Store;
import simulator.Time;
/**
 * 
 * The simulator for the saloon that extends the regular saloon.
 * @author Gustav Strömberg, Anton Dahlin, William Antti
 * 
 */
public class SaloonSim extends Simulator {
	private Store store;
	private SaloonState state;
	private View view;

	public static void main(String[] args) {
		//SaloonSim sim = new SaloonSim(7.0, 2, 2, 1.2, 1.0, 2.0, 1.0, 2.0, 0.5, 1116);
			
		//SaloonSim sim = new SaloonSim(8.0, 3, 4, 3.0, 0.8, 1.2, 2.0, 3.0, 0.25, 1116);
		
		SaloonSim sim = new SaloonSim(8.0, 2, 5, 2.0, 0.8, 1.2, 1.0, 2.0, 0.0, 1234);

		sim.run();
	}
	
	/**
	 * 
	 * @param closingTime double
	 * @param chairs int, number of cutchairs
	 * @param queue int, size of maxqueue
	 * @param enterRate double 
	 * @param hmin double minimal cuttime
	 * @param hmax double maximal cuttime
	 * @param dmin double minimal returntime
	 * @param dmax double maximal returntime
	 * @param p double percentage of dissatisfied customers
	 * @param seed long seed for randomgenerators
	 */
	public SaloonSim(double closingTime, int chairs, int queue,
			double enterRate, double hmin, double hmax, double dmin,
			double dmax, double p, long seed) {
		store = new Store();
		state = new SaloonState(queue, chairs, enterRate, hmin, hmax, dmin,
				dmax, p, seed, closingTime);
		view = new View();
		view.printinit(closingTime, chairs, queue, enterRate, hmin, hmax, dmin, dmax, p, seed);
	}
	/**
	 * Runs the simulation
	 */
	public void run() {
		store.storeEvent(new Enter(new Time(state.nextEnter()), state));

		boolean closePrint = false;
		while (!state.getFlag()) {

			Event nxt = store.nextEvent();
			if (nxt != null) {
				if (!closePrint
						&& nxt.getTime().getNumTime() > state.getCloseTime()) {
					System.out.println("  " + state.getCloseTime()
							+ "  (CLOSING)");
					closePrint = true;
				}
				System.out.print(nxt);
				state.updateStats(nxt.getTime().getNumTime());
				System.out.println(state);

				nxt.execute(store, state);
			}
			if (store.getEventListSize() == 0) {
				state.setFlag(true);
				view.printresult(state);
			}

		}
	}
}

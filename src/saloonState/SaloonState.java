package saloonState;

import java.util.ArrayList;
import java.util.Observable;

import random.ExponentialRandomStream;
import random.RandomSatisfied;
import random.UniformRandomStream;
import saloonEvent.Customer;
import saloonEvent.CustomerEvent;
import simulator.State;

/**
 * Saloon state takes care of stastistics and the queue and chairs available /
 * occupied.
 * 
 * @author Anton, Gustav, William
 * */

public class SaloonState extends State {

	private ArrayList<Customer> returnQueue;
	private ArrayList<Customer> queue;

	private Statistics stat;

	private int chairs;
	private double previousEventTime;
	private final int MAX_QUEUE, MAX_CHAIRS;
	private final double CLOSETIME;

	private ExponentialRandomStream randEnter;
	private UniformRandomStream randCutTime;
	private UniformRandomStream randReturnTime;
	private RandomSatisfied randSatisfied;

	/**
	 * The constructor sets the max amount of chairs and max size of the queue
	 * of a specific saloon
	 * 
	 * @param queue
	 *            Maximum number of people that fit in the saloon
	 * @param chairs
	 *            The amount of chairs that the saloon has
	 * */
	public SaloonState(int queue, int chairs, double enterRate, double hmin, double hmax, double dmin, double dmax, double p, long seed, double closingTime){
		MAX_QUEUE = queue;
		MAX_CHAIRS = this.chairs = chairs;
		CLOSETIME = closingTime;

		returnQueue = new ArrayList<Customer>();
		this.queue = new ArrayList<Customer>();

		randEnter = new ExponentialRandomStream(enterRate, seed);
		randCutTime = new UniformRandomStream(hmin, hmax, seed);
		randReturnTime = new UniformRandomStream(dmin, dmax, seed);
		randSatisfied = new RandomSatisfied(p, seed);

		stat = new Statistics();
	}

	/**
	 * Returns time TO next enter event, relative time
	 * 
	 * @return double, amount of time to next enter
	 */
	public double nextEnter() {
		return randEnter.next();
	}

	/**
	 * Get a random time for a haircut
	 * 
	 * @return double, amount of time
	 */
	public double nextRandCutTime() {
		return randCutTime.next();
	}

	/**
	 * Get a random time for returning customer
	 * 
	 * @return double, amount of time
	 */
	public double nextRandReturnTime() {
		return randReturnTime.next();
	}

	/**
	 * Returns if satisfied, depending on p
	 * 
	 * @return true if satisfied, false if dissatisfied
	 */
	public boolean nextRandSatisfied() {
		return randSatisfied.next();
	}

	/**
	 * When called returns first customer in queue
	 */
	public Customer queuearray() {
		return queue.get(0);
	}

	/**
	 * When called returns first customer in returnqueue
	 */
	public Customer returnqueuearray() {
		return returnQueue.get(0);
	}

	/**
	 * @return double The time the shop closes.
	 */
	public double getCloseTime() {
		return CLOSETIME;
	}

	/**
	 * When called, decrements the amount of chairs
	 * 
	 * @throws RuntimeException
	 *             If the amount of chairs are less or equal to nil
	 * */
	public void occupyChair() {
		if (chairs <= 0)
			throw new RuntimeException("Stolar <= 0 när metoden anropades");
		chairs--;
	}

	/**
	 * Increases the amout of free chairs
	 * 
	 * @throws RuntimeException
	 *             If it excedes the maximum amout of chairs
	 * */
	public void freeChair() {
		if (chairs >= MAX_CHAIRS)
			throw new RuntimeException(
					"Stolar >= max antal stolar när metoden anropades");
		chairs++;
	}

	/**
	 * Adds customers to the queue for non-returning customers
	 * 
	 * @throws RuntimeException
	 *             If the total size of the queues are larger than the max size
	 *             of the queue
	 * */
	public void addToQueue(Customer customer) {
		if (queue.size() + returnQueue.size() > MAX_QUEUE)
			throw new RuntimeException(
					"Queue:queue.size >= MAX_QUEUE när metoden anropades");
		queue.add(customer);
	}

	/**
	 * Adds customers to the queue for returning customers
	 * 
	 * @throws RuntimeException
	 *             If the total size of the queues are larger than the max size
	 *             of the queue
	 * */
	public void addToReturnQueue(Customer customer) {
		if (queue.size() + returnQueue.size() > MAX_QUEUE)
			throw new RuntimeException(
					"Return:queue.size >= MAX_QUEUE när metoden anropades");
		returnQueue.add(customer);
	}

	/**
	 * Removes the first person in the queue
	 * 
	 * @throws RuntimeException
	 *             If the queue is empty
	 * */
	public void rmFirstInQueue() {
		if (queue.isEmpty())
			throw new RuntimeException(
					"Det finns inga element i kön att ta bort");
		queue.remove(0);
	}

	/**
	 * Removes the first person in the return queue
	 * 
	 * @throws RuntimeException
	 *             If the return queue is empty
	 * */
	public void rmFirstInReturnQueue() {
		if (returnQueue.isEmpty())
			throw new RuntimeException(
					"Det finns inga element i kön att ta bort");
		returnQueue.remove(0);
	}

	/**
	 * Removes and returns the last person in the queue
	 * 
	 * @throws RuntimeException
	 *             If the queue is empty
	 * */
	public Customer rmLastInQueue() {
		if (queue.isEmpty() && returnQueue.isEmpty()) {
			throw new RuntimeException(
					"Det finns inga element i kön att ta bort");
		}
		Customer tmpcustomer;
		if (queue.isEmpty()) {
			tmpcustomer = returnQueue.get(returnQueue.size() - 1);
			returnQueue.remove(returnQueue.size() - 1);
		} else {
			tmpcustomer = queue.get(queue.size() - 1);
			queue.remove(queue.size() - 1);
		}
		return tmpcustomer;
	}

	/**
	 * @return int Number of chairs
	 * */
	public int getChairs() {
		return chairs;
	}

	/**
	 * @return int Total queue size
	 * */
	public int getQueue() {
		return queue.size();
	}

	/**
	 * @return int Total return queue size
	 * */
	public int returnGetQueue() {
		return returnQueue.size();
	}

	/**
	 * 
	 * @return int Maximum size of the queue
	 */
	public int getQueueSize() {
		return MAX_QUEUE;
	}

	/**
	 * Prints the data that comes from an event located in CustomerEvent
	 * 
	 * @param String
	 *            output A string containing data from one event
	 */
	public void output(String output) {
		System.out.println(output);
	}

	/**
	 * @return int The number of idle chairs
	 */
	public int getIdleChairs() {
		return chairs;
	}

	/**
	 * @return Statistics The statistics class
	 */
	public Statistics getStat() {
		return stat;
	}

	/**
	 * Updates the statistics class depending on what event is triggered. The
	 * data comes from CustomerEvent.
	 * 
	 * @param Observable
	 *            o A CustomerEvent object, Object arg The specific event type
	 */
	public void update(Observable o, Object arg) {
		CustomerEvent ce = (CustomerEvent) arg;

		if (ce.getEventType() == EventTypes.ENTER) {

			if (ce.isQueueing()) {
				stat.setTimeQueueing(ce.getCustomer().getQueueTime());

			}

			if (ce.getCustomer().leavingCustomer()) {
				stat.setCustomersLost();
			}

		}

		if (ce.getEventType() == EventTypes.READY) {

			if (ce.getCustomer().getSatisfied()) {
				stat.setPeopleCut();

			}

		}

		if (ce.getEventType() == EventTypes.RETURN) {

			stat.setCustomersReturned();

		}
		
		double deltaTime = ce.getTime().getNumTime() - previousEventTime;
		stat.setTimeIdle(deltaTime * getIdleChairs());
		previousEventTime = ce.getTime().getNumTime();
		output(o.toString());
	}
}

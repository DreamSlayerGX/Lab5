package saloonEvent;

import java.util.Random;

import simulator.Time;
/**
 *  A customer and its awareness of time
 * @author Anton, Gustav, William
 *
 */
public class Customer {

	private int id;
	private double queueTime, cutTime, startQueueTime, startCutTime;
	private boolean satisfied;

	/**
	 * Makes the customer a unique id
	 */
	public Customer(int id) {
		this.id = id;
		satisfied = true;
	}
	
	/**
	 * @return int id of the client
	 */
	public int getID() {
		return id;
	}

// Set methods
	public void startQueueTime(double time) {
		startQueueTime = time;

	}

	public void setCuttingTime(double time) {
		startCutTime = time;

	}

	public void endQueueTime(double time) {
		double tot = time - startQueueTime;
		queueTime += tot;
		
	}

	public void endCutTime(double time) {
		double tot = time - this.startCutTime;
		cutTime += tot;
	}

	public void setSatisfied(boolean yesNo) {
		satisfied = yesNo;
	}


// Get methods
	/**
	 * 
	 * @return double queueTime
	 */
	public double getQueueTime() {
		return queueTime;
	}
	/**
	 * 
	 * @return double cutTime
	 */
	public double getCutTime() {
		return cutTime;
	}
	/**
	 * 
	 * @return boolean satisfied
	 */
	public boolean getSatisfied() {
		return satisfied;
	}


}

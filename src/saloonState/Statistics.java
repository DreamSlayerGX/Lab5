package saloonState;

import java.text.DecimalFormat;

/**
 * Takes care of all statistics while the program is running
 * 
 * @author Gustav Strömberg
 * 
 */
public class Statistics {
	/**
	 * Variables
	 */
	private double timeIdle, timeQueueing, timeCutting;
	private int customersLost, customersReturned, peopleCut;

	// Getters and setters below

	// Idle
	/**
	 * @return double timeIdle
	 */
	public double getTimeIdle() {
		return timeIdle;
	}
	/**
	 * Increases the total amount of timeIdle
	 * @param timeIdle increased amount
	 */
	public void setTimeIdle(double timeIdle) {
		this.timeIdle += timeIdle;
	}

	// Queueing
	/**
	 * @return double TimeQueueing
	 */
	public double getTimeQueueing() {
		return timeQueueing;
	}

	public void setTimeQueueing(double timeQueueing) {
		this.timeQueueing += timeQueueing;
	}

	// Customers lost
	/**
	 * @return double customersLost
	 */
	public int getCustomersLost() {
		return customersLost;
	}

	public void setCustomersLost() {
		customersLost++;
	}

	// Customers returned
	/**
	 * @return int customersReturned
	 */
	public int getCustomersReturned() {
		return customersReturned;
	}

	public void setCustomersReturned() {
		customersReturned++;
	}

	// People cut
	/**
	 * @return int peopleCut
	 */
	public int getPeopleCut() {
		return peopleCut;
	}

	public void setPeopleCut() {
		peopleCut++;
	}

	public void setTimeCutting(double time) {
		timeCutting += time;
	}

	public double getAverageCuttingTime() {
		return (double) (timeCutting / peopleCut);
	}

	// Avrage queueing time
	/**
	 * @return double averge queuetime with timeQueueing/peopleCut
	 */
	public double getAvrageQueueingTime() {
		return  (getTimeQueueing() / getPeopleCut());
	}

}

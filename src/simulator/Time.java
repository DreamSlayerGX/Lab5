package simulator;

/**
 * A time object that keeps track of the time of events
 * 
 * @author Anton
 */

public class Time {
	private double time;

	/**
	 * 
	 * @param time double saves a new time
	 */
	public Time(double time) {
		this.time = time;

	}

	/**
	 * 
	 * @param time creates a new time object with existing time
	 */
	public Time(Time time) {
		this.time = time.time;
	}

	/**
	 * 
	 * @return double the time
	 */
	public double getNumTime() {
		return time;
	}

}
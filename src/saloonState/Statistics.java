package saloonState;


public class Statistics{
	
	private SaloonState ss;
	

	private double timeIdle, timeQueueing;
	private int customersLost, customersReturned, peopleCut;

	
	Statistics(SaloonState ss){
		this.ss = ss;
	}

//Getters and setters below	

//Idle
	public double getTimeIdle() {
		return timeIdle;
	}

	public void setTimeIdle(double timeIdle) {
		this.timeIdle += timeIdle;
	}

//Queueing
	public double getTimeQueueing() {
		return timeQueueing;
	}

	public void setTimeQueueing(double timeQueueing) {
		this.timeQueueing += timeQueueing;
	}

//Customers lost
	public int getCustomersLost() {
		return customersLost;
	}

	public void setCustomersLost() {
		customersLost++;
	}

//Customers returned
	public int getCustomersReturned() {
		return customersReturned;
	}

	public void setCustomersReturned() {
		customersReturned++;
	}

//People cut
	public int getPeopleCut() {
		return peopleCut;
	}

	public void setPeopleCut() {
		peopleCut++;
	}
	


//Avrage queueing time
	public double getAvrageQueueingTime(){
		return (double)(timeQueueing / peopleCut);
	}



	

}

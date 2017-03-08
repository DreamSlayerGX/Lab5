package saloonEvent;

import java.util.Random;

import simulator.Time;

public class Customer {
	
	/**
	 * Variables
	 */
	private int id;

	private double queueTime, cutTime, startQueueTime, startCutTime;
	private boolean satisfied, leavingCustomer;


	
	Random r = new Random();
	
	/**
	 * Makes the customer a unique id
	 */
	public Customer(int id){
		this.id = id;
		satisfied = true;
	}
	
	/**
	 * @return int id
	 */
	public int getID(){
		return id;
	}
	

	
//Set methods
	public void startQueueTime(double time){
		queueTime = time;
	}
	
	public void setCuttingTime(double time){
		startCutTime = time;
		//System.out.println("id:"+id + " start cutting at " + time);

	}
	public void endQueueTime(double time){
		double tot = time-this.startQueueTime;
		this.startQueueTime = 0;
		queueTime += tot;
	}
	public void endCutTime(double time){
		double tot = time-this.startCutTime;
		this.startCutTime = 0;
		cutTime += tot;
	}
	
	public void setSatisfied(boolean yesNo){
		satisfied = yesNo;
	}
	
	public void setLeavingCustomer(boolean yesNo){
		leavingCustomer = yesNo;
	}
	public void resetCutTime(){
		cutTime = 0;
	}
	
	
//Get methods
	public double getQueueTime(){
		return queueTime;
	}
	
	public double getCutTime(){
		return cutTime;
	}
	
	public boolean getSatisfied(){
		return satisfied;
	}
	
	public boolean leavingCustomer(){
		return leavingCustomer;
	}

}

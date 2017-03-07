package saloonEvent;

import java.util.Random;

import simulator.Time;

public class Customer {
	
	private static int counter = 0; 
	private int id;

	private double queueTime, cutTime, startQueueTime, startCutTime;
	private boolean satisfied, leavingCustomer;


	
	Random r = new Random();
	
	public Customer(){
		id = ++counter;
		
	}

	public int getID(){
		return id;
	}
	

	
//Set methods
	public void startQueueTime(double time){
		queueTime = time;
	}
	
	public void setCuttingTime(double time){
		cutTime = time;

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
	
	public void setLeavingCustomer(boolean yesNo){
		leavingCustomer = yesNo;
	}
	
	public boolean leavingCustomer(){
		return leavingCustomer;
	}

}

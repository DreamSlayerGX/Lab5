package saloonEvent;

import java.util.Random;

import simulator.Time;

public class Customer {
	
	private static int counter = 0; 
	private int id;
	private double queueTime, cutTime, startQueueTime, startCutTime;
	private boolean satisfied;
	
	Random r = new Random();
	
	public Customer(){
		id = ++counter;
		
	}

	public int getID(){
		return id;
	}
	
	public void startQueueTime(double time){
		startQueueTime = time;
	}
	
	public void startCutTime(double time){
		startCutTime = time;
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
	
	public boolean getSatisfied(){
		return satisfied;
	}

}

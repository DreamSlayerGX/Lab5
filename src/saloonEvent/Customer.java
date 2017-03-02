package saloonEvent;

import java.util.Random;

import simulator.Time;

public class Customer {
	
	private static int counter = 0; 
	private int id;
	private Time queueTime, cutTime;
	private boolean satisfied;
	
	Random r = new Random();
	
	public Customer(){
		id = ++counter;
		
	}

	public int getID(){
		return id;
	}
	
	public void startQueueTime(Time time){
		queueTime = time;
	}
	
	public void setCuttingTime(Time time){
		cutTime = time;
	}
	
	public void setSatisfied(boolean yesNo){
		satisfied = yesNo;
	}
	
	public boolean getSatisfied(){
		return satisfied;
	}

}
package saloonEvent;

import java.util.Random;

import simulator.Time;

public class Customer {
	
	private static int counter = 0; 
	private int id;
	private Time time;
	private boolean satisfied;
	
	Random r = new Random();
	
	public Customer(boolean newCustomer){
		if(newCustomer)
			id = ++counter;
		
	}

	public int getID(){
		return id;
	}
	
	public void startQueueTime(Time time){
		this.time = time;
	}
	
	public void setSatisfied(boolean x){
		satisfied = x;
	}
	
	public boolean getSatisfied(){
		return satisfied;
	}

}
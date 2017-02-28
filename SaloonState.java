package saloonState;

import java.util.LinkedList;

import saloonEvent.Customer;
import simulator.State;

public class SaloonState extends State {
	
	private LinkedList<Customer> queue;
	private final int MAX_QUEUE, MAX_CHAIRS;
	private int chairs, numReturning, numWaiting;
	private Statistics stat;
	
	
	private SaloonState(int queue, int chairs){
		MAX_QUEUE = queue;
		MAX_CHAIRS = chairs;
		
	}
	
	public void occupyChair(){
		if(chairs <= 0)
			throw new RuntimeException("Stolar <= 0 när metoden anropades");
		chairs--;
	}
	
	public void freeChair(){
		if(chairs >= MAX_CHAIRS)
			throw new RuntimeException("Stolar >= max antal stolar när metoden anropades");
		chairs++;
	}
	
	public void addToQueue(Customer customer){
		if(queue.size() >= MAX_QUEUE)
			throw new RuntimeException("queue.size >= MAX_QUEUE när metoden anropades");
		queue.add(customer);
	}
	
	public void addFirstToQueue(Customer customer){
		if(queue.size() >= MAX_QUEUE)
			rmLastInQueue();
		queue.add(0, customer);	
	}
	
	public void rmFirstInQueue(){
		if(queue.isEmpty())	
			throw new RuntimeException("Det finns inga element i kön att ta bort");
		queue.remove(0);
	}
	
	public void rmLastInQueue(){
		if(queue.isEmpty())	
			throw new RuntimeException("Det finns inga element i kön att ta bort");
		queue.remove(queue.size()-1);
	}
	
	public int getChairs(){
		return chairs;
	}
	
	public int getQueue(){
		return queue.size();
	}
	
//Kan göra till en text-fil för en slut rapport
	public String stats(){
		return "";
	}
	

}

package saloonState;

import java.util.ArrayList;

import saloonEvent.Customer;
import simulator.State;



/**
 * Saloon state takes care of stastistics and the queue and chairs available / occupied.
 * 
 * @author A, G, W
 * */

public class SaloonState extends State {
	
	private ArrayList<Customer> returnQueue;
	private ArrayList<Customer> queue;
	
	private final int MAX_QUEUE, MAX_CHAIRS;
	private int chairs, numReturning , numLost;
	private Statistics stat;
	private final double CLOSETIME;
	private int numWaiting = returnQueue.size()+queue.size(); //för det är antalet som väntar
	
	
	
	
	/**
	 * The constructor sets the max amount of chairs and max size of the queue of a specific saloon
	 * 
	 * @param queue Maximum number of people that fit in the saloon
	 * @param chairs The amount of chairs that the saloon has
	 * */
	public SaloonState(int queue, int chairs){
		MAX_QUEUE = queue;
		MAX_CHAIRS = chairs;
		numReturning = 0;
		numWaiting = 0;
		CLOSETIME = 16.0;
		
	}
	
	/**
	 * When called adds 1 to numLost
	 */
	public void numLostCounter(){
		numLost++;
	}
	
	/**
	 * When called returns the time the shop closes.
	 */
	public double getCloseTime(){
		return CLOSETIME;
	}
	
	
	/**
	 * When called, decrements the amout of chairs
	 * 
	 * @throws RuntimeException If the amount of chairs are less or equal to nil
	 * */
	public void occupyChair(){
		if(chairs <= 0)
			throw new RuntimeException("Stolar <= 0 när metoden anropades");
		chairs--;
	}
	
	/**
	 * Increases the amout of free chairs
	 * 
	 * @throws RuntimeException  If it excedes the maximum amout of chairs
	 * */
	public void freeChair(){
		if(chairs >= MAX_CHAIRS)
			throw new RuntimeException("Stolar >= max antal stolar när metoden anropades");
		chairs++;
	}
	
	/**
	 * Adds customers to the queue for non-returning customers
	 * 
	 * @throws RuntimeException If the total size of the queues are larger than the max size of the queue
	 * */
	public void addToQueue(Customer customer){
		if(queue.size() + returnQueue.size() >= MAX_QUEUE)
			throw new RuntimeException("queue.size >= MAX_QUEUE när metoden anropades");
		queue.add(customer);
	}
	
	/**
	 * Adds customers to the queue for returning customers
	 * 
	 * @throws RuntimeException If the total size of the queues are larger than the max size of the queue
	 * */
	public void addToReturnQueue(Customer customer){
		if(queue.size() + returnQueue.size() >= MAX_QUEUE)
			throw new RuntimeException("queue.size >= MAX_QUEUE när metoden anropades");
		returnQueue.add(customer);
	}
	
	/**
	 * Removes the first person in the queue
	 * 
	 * @throws RuntimeException If the queue is empty
	 * */
	public void rmFirstInQueue(){
		if(queue.isEmpty())	
			throw new RuntimeException("Det finns inga element i kön att ta bort");
		queue.remove(0);
	}
	
	/**
	 * Removes the first person in the return queue
	 * 
	 * @throws RuntimeException If the return queue is empty
	 * */
	public void rmFirstInReturnQueue(){
		if(returnQueue.isEmpty())	
			throw new RuntimeException("Det finns inga element i kön att ta bort");
		returnQueue.remove(0);
	}
	
	
	/**
	 * Removes the last person in the queue
	 * 
	 * @throws RuntimeException If the queue is empty
	 * */
	public void rmLastInQueue(){
		if(queue.isEmpty())	
			throw new RuntimeException("Det finns inga element i kön att ta bort");
		queue.remove(queue.size()-1);
	}
	
	
	/**
	 * @return int Number of chairs
	 * */
	public int getChairs(){
		return chairs;
	}
	
	/**
	 * @return int Total queue size
	 * */
	public int getQueue(){
		return queue.size();
	}
	
	/**
	 * @return int Total return queue size
	 * */
	public int returngetQueue(){
		return returnQueue.size();
	}
	
//Kan göra till en text-fil för en slut rapport
	public String stats(){
		return "";
	}
	

}
package saloonState;

import java.util.ArrayList;
import java.util.Observable;

import saloonEvent.Customer;
import saloonEvent.CustomerEvent;
import simulator.State;



/**
 * Saloon state takes care of stastistics and the queue and chairs available / occupied.
 * 
 * @author Anton, Gustav, William
 * */

public class SaloonState extends State {
	
	private ArrayList<Customer> returnQueue;
	private ArrayList<Customer> queue;
	
	private Statistics stat;
	
	private final int MAX_QUEUE, MAX_CHAIRS;
	private int chairs;
	private final double CLOSETIME;
	private int numWaiting;// = returnQueue.size()+queue.size(); //för det är antalet som väntar
	
	
	
	
	/**
	 * The constructor sets the max amount of chairs and max size of the queue of a specific saloon
	 * 
	 * @param queue Maximum number of people that fit in the saloon
	 * @param chairs The amount of chairs that the saloon has
	 * */
	public SaloonState(int queue, int chairs){
		MAX_QUEUE = queue;
		MAX_CHAIRS = this.chairs = chairs;
		numWaiting = 0;
		CLOSETIME = 16.0;
		
		returnQueue = new ArrayList<Customer>();
		this.queue = new ArrayList<Customer>();
		
		stat = new Statistics(this);
	}
	

	
	/**
	 * @return double The time the shop closes.
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
	 * Removes and returns the last person in the queue
	 * 
	 * @throws RuntimeException If the queue is empty
	 * */
	public Customer rmLastInQueue(){
		if(queue.isEmpty() && returnQueue.isEmpty()){
			throw new RuntimeException("Det finns inga element i kön att ta bort");
		}
		Customer tmpcustomer;
		if(queue.isEmpty()){
			tmpcustomer = returnQueue.get(returnQueue.size()-1);
			returnQueue.remove(returnQueue.size()-1);	
		}else{
			tmpcustomer = queue.get(queue.size()-1);
			queue.remove(queue.size()-1);
		}
		return tmpcustomer;
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
	public int returnGetQueue(){
		return returnQueue.size();
	}
	
//Kan göra till en text-fil för en slut rapport
	public void output(String output){
		System.out.println(output);
	}
	
	public int getQueueSize(){
		return MAX_QUEUE;
	}

	
	public void update(Observable o, Object arg) {
		output(o.toString());
		CustomerEvent ce = (CustomerEvent) arg;
		
		if(ce.getEventType() == EventTypes.ENTER){
			
			if(ce.isQueueing()){
				stat.setTimeQueueing(ce.getCustomer().getQueueTime());
				
			}
			
			if(ce.getCustomer().leavingCustomer()){
				stat.setCustomersLost();
			}
				
			
			
			
		}
		
		if(ce.getEventType() == EventTypes.READY){
			
			if(ce.getCustomer().getSatisfied()){
				stat.setPeopleCut();
				
			}
			
			
		}
		
		if(ce.getEventType() == EventTypes.RETURN){
			
			stat.setCustomersReturned();
		}
		
	}
	
	
	

}

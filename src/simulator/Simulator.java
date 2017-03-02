package simulator;


/**
 * Main part of the simulator where the simulators runs.
 * 
 * @author Gustav Strömberg
 * */
public class Simulator {
	
	private State state;
	private Store store;
	
	/**
	 * Constructor, initiates the run method
	 * */
	public Simulator(){
		run();
	}
	
	/**
	 * Activates the next event while the program is running
	 * */
	public void run(){
		while(!state.getFlag()){
			
		store.nextEvent().execute(store);
			
			
		}
		
	}
	
	public static void main(String[] args){
		Simulator sim = new Simulator();
		
		
		
		
	}

}
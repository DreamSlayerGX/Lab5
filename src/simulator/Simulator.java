package simulator;

public class Simulator {
	
	private State state;
	private Store store;
	
	Simulator(){
		run();
	}
	
	public void run(){
		while(!state.getFlag()){
			
		store.nextEvent();
			
			
		}
		
	}
	
	public static void main(String[] args){
		Simulator sim = new Simulator();
		
		
	}

}

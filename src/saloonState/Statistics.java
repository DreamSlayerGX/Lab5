package saloonState;


public class Statistics{
	
	private SaloonState ss;
	
	private double timeIdle, timeWaiting;
	private int customersReturned, cut, lost;
	
	Statistics(SaloonState ss){
		this.ss = ss;
	}
	
	public double getTIdle(){
		return timeIdle;
	}
	public double getTWait(){
		return timeWaiting;
	}
	public int getReturned(){
		return customersReturned;
	}
	public int getCut(){
		return cut;
	}
	public int getLost(){
		return lost;
	}
	public void addCut(){
		cut++;
	}
	public void addLost(){
		lost++;
	}
	public void addReturned(){
		customersReturned++;
	}
	
	
	

}

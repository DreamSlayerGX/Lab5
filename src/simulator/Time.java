package simulator;

public class Time {
	private final double OPENTIME = 8.0;//ta bort vi kör time 0.0 till infinite
	private double time;
	public Time(double time){
		this.time = time;
		
	}
	public Time(Time time){
		this.time = time.time;
	}
	
	
	public double getNumTime(){
		return time;
	}
	public String toString(){
		int hour = (int)(Math.floor(time+OPENTIME));
		int minute = (int)(Math.round((time-Math.floor(time))*60));
		
		return(hour+":"+minute);
	}

}
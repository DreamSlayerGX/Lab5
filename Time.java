package simulator;

public class Time {
	private double openTime;
	private double time;
	public Time(double time){
		this.time = time;
		this.openTime = 8.0;
	}
	public String toString(){
		int hour = (int)(Math.floor(time+openTime));
		int minute = (int)(Math.round((time-Math.floor(time))*60));
		
		return(hour+":"+minute);
	}

}

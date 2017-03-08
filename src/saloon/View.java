package saloon;

import java.text.DecimalFormat;

import saloonState.SaloonState;

public class View {
	private DecimalFormat df = new DecimalFormat("#0.00");
	
	public void printinit(double closingTime, int chairs, int queue,
			double enterRate, double hmin, double hmax, double dmin,
			double dmax, double p, long seed){
		
		
		System.out.println("Closing time of the day ..............: "
				+ closingTime);
		System.out.println("Total number of chairs ...............: " + chairs);
		System.out.println("Maximum queue size ...................: " + queue);
		System.out.println("Lambda (customers/timeunit entering)..: "
				+ enterRate);
		System.out.println("hmin and hmax (cutting time interval) : [" + hmin
				+ ", " + hmax + "]");
		System.out.println("dmin and dmax (return time interval) .: [" + dmin
				+ ", " + dmax + "]");
		System.out.println("Risk dissatisfied returns: ...........: " + p);
		System.out.println("Seed used in pseudo random generator .: " + seed);
		System.out
				.println("-----------------------------------------------------------------------------");
		System.out
				.println("- Time  Event   Id      Idle    TIdle   TWait   InQ     Cut     Lost    Ret -");
	}
	
	public void printresult(SaloonState state){
		System.out
		.println("-----------------------------------------------------------------------------");
		System.out.println("Number of customers cut: ......: "
		+ state.getStat().getPeopleCut());
		System.out.println("Average cutting time...........: "
		+ df.format(state.getStat().getAverageCuttingTime()));
		System.out.println("Average queueing time: ........: "
		+ df.format(state.getStat().getAvrageQueueingTime()));
		System.out.println("Largest queue (max NumWaiting) : "
		+ state.getMaxQueue());
		System.out.println("Customers not cut (NumLost) ...: "
		+ state.getStat().getCustomersLost());
		System.out.println("Dissatisfied customers: .......: "
		+ state.getStat().getCustomersReturned());
		System.out.println("Time chairs were idle: ........: "
		+ df.format(state.getStat().getTimeIdle()));
	}
}

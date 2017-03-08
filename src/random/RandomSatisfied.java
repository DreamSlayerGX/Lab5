package random;

import java.util.Random;

public class RandomSatisfied {

	private Random rand;
	private double p;

	public RandomSatisfied(double p, long seed) {
		rand = new Random(seed);
		this.p = p;
	}

	public RandomSatisfied(double p) {
		rand = new Random();
		this.p = p;
	}

	// om p är 0.8 så är det 80% chans för true och 20% chans för false
	public boolean next() {
		return rand.nextDouble() < p;
	}
}

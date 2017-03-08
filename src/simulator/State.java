package simulator;

import java.util.Observable;

import java.util.Observer;

/**
 * An abstract state. Keeps / stores the data during the simulation.
 * 
 * @author Gustav , Anton , William 
 */
public abstract class State{

	private boolean flag = false;

	/**
	 * @param value boolean changes the value of the flag
	 */
	public void setFlag(boolean value) {
		flag = value;
	}

	/**
	 * @return boolean false if simulation is running
	 */
	public boolean getFlag() {
		return flag;
	}

}
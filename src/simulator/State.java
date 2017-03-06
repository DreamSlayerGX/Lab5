package simulator;

import java.util.Observable;
import java.util.Observer;

public abstract class State implements Observer {
	
	private boolean flag;
	
	public State(){
		flag = false;
	}
	public void setFlag(boolean value){
		flag = value;
	}
	
	public boolean getFlag(){
		return flag;
	}
	
	
	public abstract void update(Observable o, Object arg);

}
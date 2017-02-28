package saloonEvent;
/*
* Start
*/
public class Start extends Event{
	
	public Start(Time time){
		super(time);
	}
	
	public void execute(State state, Store store){
		state.setFlag(true);
		store.StoreEvent(); //???????
	}
}



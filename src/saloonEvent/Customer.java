package saloonEvent;

public class Customer {
	
	private static int counter = 0; 
	private int id;
	
	public Customer(boolean newCustomer){
		if(newCustomer)
			id = ++counter;
		
		
	}

	public int getID(){
		return id;
	}

}

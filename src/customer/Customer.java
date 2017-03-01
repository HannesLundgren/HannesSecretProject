package customer;
/**
 * Class that represent a customer
 * @author hanneslundgren
 *
 */
public class Customer {
	private int id;

//	private boolean notSatisfied = false;
	private boolean priority = false;

	
	/**
	 * The class constructor
	 * @param id The unique id number of the customer
	 */
	Customer(int id) {
		this.id = id;
	}
	
	//getter

	public int getId() {
		return id;
	}
	public boolean Priority() {
		return priority;
	}
	public void setPriority() {
		priority = true;
	}
	public void removePriority() {
		priority = false;
	}
//	public void setSatisfied() {
//		notSatisfied = false;
//	}
//	public void setNotSatisfied() {
//		notSatisfied = true;
//	}
//	

	
}

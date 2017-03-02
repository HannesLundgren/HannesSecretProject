package customer;
/**
 * Class that represent a customer
 * @author hanneslundgren
 *
 */
public class Customer {
	private int id;
	private double queueEnterTime;
	private boolean priority = false;
	
	
	
	/**
	 * The class constructor
	 * @param id The unique id number of the customer
	 */
	Customer(int id) {
		this.id = id;
	}
	
	
	//FIXA TID NÄR DE STÄLLER SIG I KÖN
	
	public double getQueueEnterTime() {
		return queueEnterTime;
	}
	public void setQueueEnterTime(double currentTime) {
		this.queueEnterTime = currentTime;
	}
	
	
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
	

	
}

package customer;
/**
 * Class that represent a customer
 * @author hanneslundgren
 *
 */
public class Customer {
	private int id;
	
	/**
	 * The class constructor
	 * @param id The unique id number of the customer
	 */
	Customer(int id) {
		this.id = id;
	}
	
	//getter
	public int getCustomerID() {
		return id;
	}
	
}

package customer;
/**
 * Class that creates new customers
 * @author hanneslundgren
 *
 */
public class CustomerGenerator {
	//The id of the customers to create
	int id = 0;
	
	/**
	 * Method that returns a new customer
	 * and then increases the id for the next 
	 * customer by 1.
	 * @return A new customer
	 */
	public Customer generateCustomer(int id) {
		return new Customer(id++);
	}
}

package customer;
/**
 * Class that creates new customers
 * @author Hannes Lundgren, Robert Högberg, Amadeus Johansson
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
	public Customer generateCustomer() {
		return new Customer(id++);
	}
}

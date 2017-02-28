package event;

import customer.Customer;
import simulator.Event;
import state.HairSalonState;
/**
 * General hair salon event
 * @author hanneslundgren
 *
 */
public abstract class HairSalonEvent extends Event{
	
	protected HairSalonState state;
	protected Customer cust;
	
	public Customer getCustomer() {
		return cust;
	}
	


}

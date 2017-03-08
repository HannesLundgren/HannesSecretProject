package event;

import customer.Customer;
import simulator.Event;
import simulator.EventStore;
import state.HairSalonState;
/**
 * General hair salon event
 * @author Hannes Lundgren, Robert Högberg, Amadeus Johansson
 *
 */
public abstract class HairSalonEvent extends Event{
	
	protected HairSalonState state;
	protected Customer cust;
	
	
	public HairSalonEvent(double startTime,HairSalonState state, EventStore store) {
		super(startTime,store);
		this.state = state;
		
	}
	
	public Customer getCustomer() {
		return cust;
	}




	


}

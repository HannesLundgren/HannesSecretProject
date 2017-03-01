package event;

import customer.Customer;
import simulator.EventStore;
import state.HairSalonState;

public class CustomerEntersEvent extends HairSalonEvent {
	
	public CustomerEntersEvent(double startTime, HairSalonState state, EventStore store) {
		super.startTime = startTime;
		super.state = state;
		super.store = store; 
	}

	@Override
	public void execute() {
		state.setTimeForLastEvent(state.getCurrentTime());
		state.setCurrentEvent(this);
		state.setCurrentTime(startTime);
		state.updateIdleTime();
		state.updateQueueTime();
		
		Customer newCust = state.generateCustomer();
		state.setCurrentCustomer(newCust);
		
		state.callChanged();
		
		
		
		
		
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return null;
	}
	
}


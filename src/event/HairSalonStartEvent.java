package event;

import simulator.Event;
import simulator.EventStore;
import simulator.StartEvent;
import simulator.State;
import state.HairSalonState;

public class HairSalonStartEvent extends StartEvent {
	private HairSalonState state;
	
	public HairSalonStartEvent( HairSalonState state, EventStore store) {
		this.state = state;
		super.store = store;
	}
	
	
	@Override
	public void execute() {
		// TODO Auto-generated method stub
		state.setCurrentEvent(this);
		state.setCurrentTime(startTime);
		
		state.callChanged();
		
		
		
		HairSalonEvent firstCustomer = new CustomerEntersEvent(startTime, state, store)
		
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return "Opens";
	}
	
}

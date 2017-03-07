package event;

import simulator.EventStore;
import simulator.StopEvent;
import state.HairSalonState;

public class HairSalonStopEvent extends StopEvent {
	
	public HairSalonStopEvent( EventStore store, HairSalonState state) {
		super(store);
		this.state = state;
		
	}

	HairSalonState state;
	
//	public HairSalonStopEvent(HairSalonState state, EventStore store) {
//		this.state = state;
//		super.store = store;
//	}
	@Override
	public void execute() {
		
		state.setCurrentEvent(this);
		state.updateIdleTime();
		state.updateQueueTime();
		
		state.callChanged();
		state.activateEmergBrake();
		
		
	}
	
	public String toString() {
		return "StopHSS";
	}
}

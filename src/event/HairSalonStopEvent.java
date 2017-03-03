package event;

import simulator.EventStore;
import simulator.StopEvent;
import state.HairSalonState;

public class HairSalonStopEvent extends StopEvent {
	HairSalonState state;
	
	public HairSalonStopEvent(HairSalonState state, EventStore store) {
		this.state = state;
		super.store = store;
	}
	@Override
	public void execute() {
		
		state.setCurrentEvent(this);
		state.updateIdleTime();
		state.updateQueueTime();
		
		state.callChanged();
		state.activateEmergBrake();
		
		
		
		
		
	}
}

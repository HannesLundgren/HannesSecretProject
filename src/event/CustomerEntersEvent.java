package event;

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
		
		
		
		
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return null;
	}
	
}


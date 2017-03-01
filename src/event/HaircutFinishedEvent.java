package event;

import simulator.EventStore;
import state.HairSalonState;

public class HaircutFinishedEvent extends HairSalonEvent {
	
	public HaircutFinishedEvent(double startTime, HairSalonState state, EventStore store) {
		super.startTime = startTime;
		super.state = state;
		super.store = store; 
	}

	@Override
	public void execute() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return null;
	}
}


package event;

import simulator.EventStore;
import simulator.StopEvent;
import state.HairSalonState;
/**
 * This is the Stop event for the Hair salon simulation.
 * @author Robert Högberg
 *
 */
public class HairSalonStopEvent extends StopEvent {
	
	/**
	 * HairSalonEvent constructor
	 * @param store of the type EventStore
	 * @param state of the type HairSalonState
	 */
	public HairSalonStopEvent( EventStore store, HairSalonState state) {
		super(store);
		this.state = state;
		
	}

	HairSalonState state;
	
//	public HairSalonStopEvent(HairSalonState state, EventStore store) {
//		this.state = state;
//		super.store = store;
//	}
	/**
	 * The method execute activates the simulationbreak.
	 * HairSalonState gets updated and the HairSalonView gets
	 * notified of the changes and the event can have an effect.
	 */
	@Override
	public void execute() {
		
		state.setCurrentEvent(this);
		state.setCurrentTime(startTime);
		//state.updateIdleTime();
//		state.updateQueueTime();
		
		state.callChanged();
		state.activateEmergBrake();
		
		
	}
	
	public String toString() {
		return "StopHSS";
	}
}

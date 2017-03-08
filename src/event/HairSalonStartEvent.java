package event;

import simulator.Event;
import simulator.EventStore;
import simulator.StartEvent;
import simulator.State;
import state.HairSalonState;
/**
 * This is the class that represents the hairsalon start event
 * @author Robert Högberg, Hannes Lundgren, Amadeus Johansson
 *
 */
public class HairSalonStartEvent extends StartEvent {
	private HairSalonState state;
	/**
	 * HairSalonEvent constructor
	 * @param state of the type HairSalonState
	 * @param store of the type Event
	 */
	public HairSalonStartEvent(HairSalonState state,EventStore store ) {
		super(store);
		this.state = state;
		
//		super.store = store;
	}
	
	/**
	 * The method execute generates a new CustomerEnetersEvent
	 * and adds it to the event store
	 * HairSalonState gets updated and the HairSalonView gets
	 * notified of the changes and the event can have an effect.
	 */
	@Override
	public void execute() {
		// TODO Auto-generated method stub
		state.setCurrentEvent(this);
		state.setCurrentTime(startTime);
		
		state.callChanged();
		
		
		
		HairSalonEvent firstCustomer = new CustomerEntersEvent(state.getNextArrivalTime(), state, store);
		store.add(firstCustomer);
		
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return "Start HSS";
	}
	
}

package event;

import customer.Customer;
import simulator.EventStore;
import state.HairSalonState;
/**
 * 
 * @author Robert Högberg, Hannes Lundgren, Amadeus Johansson
 *
 */
public class PriorityCustomerEntersEvent extends HairSalonEvent {
	/**
	 * PriorityCustomerEnetersEvent constructor
	 * @param startTime Start time for the event
	 * @param state Of the type HairSalonState
	 * @param store
	 * @param cust
	 */
	public PriorityCustomerEntersEvent(double startTime, HairSalonState state, EventStore store, Customer cust) {
		super(startTime,state,store);
//		super.startTime = startTime;
//		super.state = state;
//		super.store = store; 
		super.cust = cust;
	}
	/**
	 * Execute the event when a priority customer enters. 
	 * HairSalonState gets updated and the HairSalonView gets
	 * notified of the changes and the event can have an effect.
	 */
	@Override
	public void execute() {
		
		state.setTimeForLastEvent(state.getCurrentTime());
		state.setCurrentEvent(this);
		state.setCurrentTime(startTime);
		state.setCurrentCustomer(cust);
	
		
		state.updateIdleTime();
		state.updateQueueTime();
		
		
		state.callChanged();
		
		
//		state.increaseNumReturning(); 
		state.increaseNumReturning();

		
		

		
		if (state.isChairsIdle()) {
			state.decreaseIdleChairs();
			double timeForNext = state.getHairdresserFinishTime();
			HairSalonEvent finished = new HaircutFinishedEvent(timeForNext,state,store, cust);
			store.add(finished);
			state.increaseCuttinTime(timeForNext);
			state.setLatestCustomerFinishTime(timeForNext);

		}else {
			
			if (!state.addPriorityCustomer(cust)){
				double timeForNext = state.getUnsatisfiedCustomerArrivalTime();
				HairSalonEvent returning = new PriorityCustomerEntersEvent(timeForNext,state,store,cust);
				store.add(returning);
			}
		}
		
		
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return "Priority Customer Returns ";
	}



}

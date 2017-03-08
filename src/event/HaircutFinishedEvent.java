package event;



import customer.Customer;
import simulator.EventStore;
import state.HairSalonState;
/**
 * This class handles the event when a haircut is finished
 * @author Robert Högberg
 *
 */
public class HaircutFinishedEvent extends HairSalonEvent {
	/**
	 * HaircutFinishedEvent constructor
	 * @param startTime for the event
	 * @param state of the type HairsalonState
	 * @param store of the type EventStore
	 * @param cust is a customer
	 */
	public HaircutFinishedEvent(double startTime, HairSalonState state, EventStore store, Customer cust) {
		super(startTime,state,store);
		super.cust = cust;
//		super.startTime = startTime;
//		super.state = state;
//		super.store = store; 
//		super.cust = cust;
	}
	/**
	 * The execute method handles customers with a finished haircut. 
	 * Customers with bad haircuts will return before the simulation ends.
	 * HairSalonState gets updated and the HairSalonView gets
	 * notified of the changes and the event can have an effect.
	 */
	@Override
	public void execute() {
		// TODO Auto-generated method stub
		state.setTimeForLastEvent(state.getCurrentTime());
		state.setCurrentEvent(this);
		state.setCurrentTime(startTime);
		state.updateIdleTime();
		state.updateQueueTime();
		
		state.setCurrentCustomer(cust);
		state.callChanged();
		
		
		
		if (state.isQueueEmpty()) {
			state.increaseIdleChairs();
		}else {
			double timeForNext = state.getHairdresserFinishTime();
			HairSalonEvent finished = new HaircutFinishedEvent(timeForNext,state,store, state.getFirst());
			store.add(finished);
			state.removeFirst();
			state.increaseCuttinTime(timeForNext);
			state.setLatestCustomerFinishTime(timeForNext);
			

		}
		if (state.checkHaircut(cust)){
			
			double timeForNext = state.getUnsatisfiedCustomerArrivalTime();
			HairSalonEvent returning = new PriorityCustomerEntersEvent(timeForNext,state,store,cust);
			store.add(returning);
			
			state.increaseNumReturning();
			
		}else {
			state.increaseNumberOfCust();
		}
		
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return "Customer Finished";
	}
}


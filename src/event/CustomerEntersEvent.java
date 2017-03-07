package event;

import customer.Customer;
import simulator.EventStore;
import state.HairSalonState;
/**
 * This class handles customers entering the hair salon
 * @author Robert Högberg
 *
 */
public class CustomerEntersEvent extends HairSalonEvent {
	
	/**
	 * 
	 * @param startTime of the event
	 * @param state HairSalonState
	 * @param store Eventstore
	 */
	public CustomerEntersEvent(double startTime, HairSalonState state, EventStore store) {
		super(startTime,state,store);
//		super.startTime = startTime;
//		super.state = state;
//		super.store = store; 
	}
	/**
	 * The execute method, when called decides if a customer can
	 * get a haircut, wait in the queue or just leave, if the 
	 * chairs and the queue is occupied with other customers.
	 * 
	 * HairSalonState gets updated and the HairSalonView gets
	 * notified of the changes and the event can have an effect.
	 */
	@Override
	public void execute() {
		
//		if(state.getClosingTime()<startTime) {
//			state.setClosed();
//			return;
//		}
		
		state.setTimeForLastEvent(state.getCurrentTime());
		state.setCurrentEvent(this);
		state.setCurrentTime(startTime);
		state.updateIdleTime();
		state.updateQueueTime();
		
		Customer newCust = state.generateCustomer();
		state.setCurrentCustomer(newCust);
		
		state.callChanged();
		//Here the view has been updated and the event can 
		//have an effect
		
//		if (state.isClosed()) {
//			//SKRIV OM?!
//			return;
//		}
		
		if (state.isChairsIdle()){
			state.decreaseIdleChairs();
			double timeForNext = state.getHairdresserFinishTime();
			HairSalonEvent finished = new HaircutFinishedEvent(timeForNext,state,store, newCust);
			store.add(finished);
			state.increaseCuttinTime(timeForNext);
			state.setLatestCustomerFinishTime(timeForNext);

		}else {
			if (state.addToQueue(newCust)) {
				
			}else {
				state.increaseNumLost();
			}
		}
		//Schedule new customer enters event
		
		double nextStartTime = state.getNextArrivalTime();
		
		if(state.getClosingTime()>nextStartTime) {
			HairSalonEvent nextCustomer = new CustomerEntersEvent(nextStartTime, state, store);
			store.add(nextCustomer);
		}else {
			state.setClosed();
		}
		
		
		
		
		
	}


	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return "Customer Enters";
	}
	
}


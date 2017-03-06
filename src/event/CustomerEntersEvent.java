package event;

import customer.Customer;
import simulator.EventStore;
import state.HairSalonState;

public class CustomerEntersEvent extends HairSalonEvent {
	

	public CustomerEntersEvent(double startTime, HairSalonState state, EventStore store) {
		super(startTime,state,store);
//		super.startTime = startTime;
//		super.state = state;
//		super.store = store; 
	}

	@Override
	public void execute() {
		
		if(state.getClosingTime()<startTime) {
			state.setClosed();
			return;
		}
		
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
		HairSalonEvent nextCustomer = new CustomerEntersEvent(state.getNextArrivalTime(), state, store);
		store.add(nextCustomer);
		
		
		
		
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return "Customer Enters";
	}
	
}


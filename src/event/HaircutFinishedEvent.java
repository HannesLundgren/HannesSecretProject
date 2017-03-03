package event;



import customer.Customer;
import simulator.EventStore;
import state.HairSalonState;

public class HaircutFinishedEvent extends HairSalonEvent {
	
	public HaircutFinishedEvent(double startTime, HairSalonState state, EventStore store, Customer cust) {
		super.startTime = startTime;
		super.state = state;
		super.store = store; 
		super.cust = cust;
	}

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


package event;

import customer.Customer;
import simulator.EventStore;
import state.HairSalonState;

public class PriorityCustomerEntersEvent extends HairSalonEvent {

	public PriorityCustomerEntersEvent(double startTime, HairSalonState state, EventStore store, Customer cust) {
		super.startTime = startTime;
		super.state = state;
		super.store = store; 
		super.cust = cust;
	}

	@Override
	public void execute() {
		
		state.setTimeForLastEvent(state.getCurrentTime());
		state.setCurrentEvent(this);
		state.setCurrentTime(startTime);
		state.updateIdleTime();
		state.updateQueueTime();
		
		
		state.callChanged();
		
		if (state.isChairsIdle()) {
			state.decreaseIdleChairs();
			double timeForNext = state.getHairdresserFinishTime();
			HairSalonEvent finished = new HaircutFinishedEvent(timeForNext,state,store, cust);
			store.add(finished);
		}else {
			if (!state.addPriorityCustomer(cust)){ //SKRIV OM ALLA KÖMETODER MED GET ISTÄLLET?
				double timeForNext = state.getUnsatisfiedCustomerArrivalTime();
				HairSalonEvent returning = new PriorityCustomerEntersEvent(timeForNext,state,store,cust);
				store.add(returning);
			}
		}
		
		
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return "Returns";
	}



}

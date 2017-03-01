package event;

public class PriorityCustomerEntersEvent extends HairSalonEvent {

	

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

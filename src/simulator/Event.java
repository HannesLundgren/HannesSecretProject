package simulator;
/**
 * Abstract class that act as a template for
 * events used in the simulator
 * @author hanneslundgren
 *
 */
public abstract class Event {
	//Time when the event is supposed to be happen.
	protected double startTime;
	//The store in which the events are to be placed
	protected EventStore store;
	/**
	 * Event constructor
	 * @param startTime for the Event
	 * @param store of the type EventStore
	 */
	public Event (double startTime, EventStore store) {
		this.startTime = startTime;
		this.store = store;
	}
	
	/**
	 * Method that execute the effect of this event
	 */
	public abstract void execute();
	/**
	 * Method that returns the name of the event
	 * as a String
	 */
	public abstract String toString();
	
	/**
	 * 
	 * @return start time for the event
	 */
	public double getStartTime() {
		return startTime;
	}
	
}

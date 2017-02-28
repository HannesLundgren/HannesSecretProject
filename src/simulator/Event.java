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
	protected EventStore store;
	/**
	 * Method that execute the effect of this event
	 */
	public abstract void execute();
	/**
	 * Method that returns the name of the event
	 * as a String
	 */
	public abstract String toString();
}

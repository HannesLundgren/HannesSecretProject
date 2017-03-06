package simulator;
/**
 * Class that describes a general stopevent of
 * the simulator
 * @author hanneslundgren
 *
 */
public abstract class StopEvent extends Event {
	

	public StopEvent(EventStore store) {
		super(10000000, store);  
		// TODO Auto-generated constructor stub
	}



	// UNDER CONSTRUCTION
	@Override
	/**
	 * Sets the emergency brake var in State as true
	 */
	abstract public void execute();
		
	

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return "StopEvent";
	}

}

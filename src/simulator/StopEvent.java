package simulator;
/**
 * Class that describes a general stopevent of
 * the simulator
 * @author hanneslundgren
 *
 */
public abstract class StopEvent extends Event {

//	protected State state;
//	public StopEvent(State state) {
//		this.state = state;
//	}
	
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

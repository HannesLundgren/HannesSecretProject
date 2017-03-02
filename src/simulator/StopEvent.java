package simulator;
/**
 * Class that describes a general stopevent of
 * the simulator
 * @author hanneslundgren
 *
 */
public class StopEvent extends Event {

	protected State state;
	
	// UNDER CONSTRUCTION
	@Override
	/**
	 * Sets the emergency brake var in State as true
	 */
	public void execute() {
		
		state.emergBrake = true;
		
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return null;
	}

}

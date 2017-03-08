package simulator;
/**
 * Class that describes a general stopevent of
 * the simulator
 * @author Hannes Lundgren, Robert Högberg, Amadeus Johansson
 *
 */
public abstract class StopEvent extends Event {
	
	/**
	 * StopEvent constructor calls the super constructor
	 * 
	 * @param store of the type EventStore
	 */
	public StopEvent(EventStore store) {
		super(999, store);  
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

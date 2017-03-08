package simulator;
/**
 * A general class for simulators
 * @author Robert Högberg
 *
 */
public class Simulator {
	
	private EventStore store;
	private State state;
	/**
	 * Simulator constructor
	 * @param store of the type EventStore
	 * @param state of the type State
	 */
	public Simulator(EventStore store, State state) {
		this.store = store;
		this.state = state;
		
	}
	
	/**
	 * The run method executes the first event, then it loops 
	 * through the event store until it reach the simulation break.
	 * @param start is a StartEvent
	 * @param stop is a StopEvent
	 */
	public void run(StartEvent start, StopEvent stop) {
		start.execute();
	
		do {
			store.getFirst().execute();
			
//			if (store.isEmpty()) {
//				stop.execute();
//			}
			
		} while (!state.emergBrake);
		
		
	}
}		

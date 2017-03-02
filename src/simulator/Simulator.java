package simulator;

public class Simulator {
	
	private EventStore store;
	private State state;
	
	public Simulator(EventStore store, State state) {
		this.store = store;
		
	}
	
	
	public void run() {
		
		do {
			store.getFirst().execute();

		} while (!state.emergBrake);
		
	}
}		

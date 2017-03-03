package simulator;

public class Simulator {
	
	private EventStore store;
	private State state;
	
	public Simulator(EventStore store, State state) {
		this.store = store;
		
	}
	

	public void run() {
	
		do {
			if(!store.isEmpty()) {
				store.getFirst().execute();
			}
		} while (!state.emergBrake);
		
		
	}
}		

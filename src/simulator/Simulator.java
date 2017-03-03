package simulator;

public class Simulator {
	
	private EventStore store;
	private State state;
	
	public Simulator(EventStore store, State state) {
		this.store = store;
		
	}
	

	public void run(StartEvent start, StopEvent stop) {
		start.execute();
	
		do {
			store.getFirst().execute();
			if (store.isEmpty()) {
				stop.execute();
			}
			
		} while (true);
		
		
	}
}		

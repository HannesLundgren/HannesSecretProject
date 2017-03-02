package simulator;

public class Simulator {
	
	private EventStore store;
	public Simulator(EventStore store) {
		this.store = store;
	}
	
	
	public void run() {
		
		do {
			store.getFirst().execute();

		} while (!store.isEmpty());
		
	}
}		

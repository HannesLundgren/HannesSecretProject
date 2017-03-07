package simulatorMain;

import java.util.Random;

import event.HairSalonStartEvent;
import event.HairSalonStopEvent;
import simulator.EventStore;
import simulator.Simulator;
import state.HairSalonState;
import view.HairSalonView;
/**
 * This is the main class for the simulator.
 * The startup of the simulation gets executed here.
 * 
 * @author Robert Högberg
 *
 */
public class SimulatorMain {
	
	/**
	 * The main method creates all the necessary objects and
	 * lets the user specify the HairSalonState.
	 * @param args
	 */
	public static void main(String[] args) {
 
		EventStore store = new EventStore();
		HairSalonState state = new HairSalonState(3, 4, 8.0, 1116);
//		state.setCustomerArrivalDistribution(0.25);
		
		Simulator sim = new Simulator (store, state);
		HairSalonView view = new HairSalonView(state);
		HairSalonStartEvent start = new HairSalonStartEvent(state, store);
		HairSalonStopEvent stop = new HairSalonStopEvent(store, state);
		sim.run(start, stop);
		
		
		
		
	}

}

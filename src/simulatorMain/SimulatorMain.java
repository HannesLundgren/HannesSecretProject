package simulatorMain;

import java.util.Random;

import event.HairSalonStartEvent;
import event.HairSalonStopEvent;
import simulator.EventStore;
import simulator.Simulator;
import state.HairSalonState;
import view.HairSalonView;

public class SimulatorMain {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
 
		EventStore store = new EventStore();
		HairSalonState state = new HairSalonState(5, 5, 500);
//		state.setCustomerArrivalDistribution(0.25);
		Simulator sim = new Simulator (store, state);
		HairSalonView view = new HairSalonView(state);
		HairSalonStartEvent start = new HairSalonStartEvent(state, store);
		HairSalonStopEvent stop = new HairSalonStopEvent(state, store);
		sim.run(start, stop);
		
		
		
		
	}

}

package view;

import java.util.Observable;

import event.HairSalonStartEvent;
import event.HairSalonStopEvent;
import simulator.View;
import state.HairSalonState;
/**
 * Class used for displaying the current state
 * of the Hair Salon Simulator
 * @author hanneslundgren
 *
 */
public class HairSalonView extends View {
	HairSalonState state;

	
	public HairSalonView(HairSalonState state) {
		this.state = state;
		state.addObserver(this);
	}
	
	
	
	@Override
	public void update(Observable o, Object arg) {
		// TODO Auto-generated method stub
		
		//Skriv om med formatverktygen?!?!!!!
		if(state.getCurrentEvent() instanceof HairSalonStartEvent) {
			System.out.println("Number of chairs available: " + state.getIdleChairs());
			System.out.println("Maximum queue size: "+ state.getMaxQueueSize());
			System.out.println("Probability of unsatisfied customer: " + state.getP());
			System.out.println("Lambda: " + state.getLambda());
			System.out.println("Hmin: " + state.getHMin());
			System.out.println("Hmax: " + state.getHMax());
			System.out.println("Dmin: "+ state.getDMin());
			System.out.println("Dmax: " + state.getDMax());
		}
		else if(state.getCurrentEvent() instanceof HairSalonStopEvent) {
			
		}
	}

}

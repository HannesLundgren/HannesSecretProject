package view;

import java.util.Observable;

import simulator.View;
import state.HairSalonState;
/**
 * Class used for displaying the current state
 * of the Hair Salon Simulator
 * @author hanneslundgren
 *
 */
public class HairSalonView extends View {

	
	public HairSalonView(HairSalonState state) {
		state.addObserver(this);
	}
	@Override
	public void update(Observable o, Object arg) {
		// TODO Auto-generated method stub
		
	}

}

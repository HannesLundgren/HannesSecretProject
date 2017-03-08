package simulator;

import java.util.Observable;
/**
 * Abstract class that act as a template for state
 * to be used in this simulator
 * @author Hannes Lundgren, Robert Högberg, Amadeus Johansson
 *
 */
public abstract class State extends Observable{
	//The emergency break
	protected boolean emergBrake = false;
	
	//The Event currently going on
	protected Event currentEvent;
	
	//The previous event
	protected Event previousEvent;
	
	//The current time
	protected double currentTime;
	
	/**
	 * Activates the simulation break
	 * The simulation ends when the method is called
	 * 
	 */
	public void activateEmergBrake() {
		emergBrake = true;
	}
	
	
}

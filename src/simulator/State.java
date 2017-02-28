package simulator;

import java.util.Observable;
/**
 * Abstract class that act as a template for states 
 * to be used in this simulator
 * @author hanneslundgren
 *
 */
public abstract class State extends Observable{
	//The emergency break
	protected boolean emergBrake = false;
	//The Event currently going on
	Event currentEvent;
	
}

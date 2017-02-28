package simulator;

import java.util.Observable;

public abstract class State extends Observable{
	//Handbromsen
	protected boolean emergBrake = false;
	Event currentEvent;
	
}

package simulator;

import java.util.Observable;
import java.util.Observer;
/**
 * Abstract class that act as a template
 * for simulation views
 * @author hanneslundgren
 *
 */
public abstract class View implements Observer {
	
	/**
	 * Every class that extends View, will get an observer update method.
	 */
	@Override
	public abstract void update(Observable o, Object arg);
	

}

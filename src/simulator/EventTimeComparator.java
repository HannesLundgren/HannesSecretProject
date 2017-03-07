package simulator;
import java.util.Comparator;
/**
 * EventTimeComparator class is used when the simulation needs 
 * to compare the start time between two events.
 * @author Robert Högberg
 *
 */
public class EventTimeComparator implements Comparator<Event> {

	
	/**
	 * This method compares the event time 
	 * between two events.
	 * @param arg0 first Event object
	 * @param arg1 second Event object
	 * @return an int depending on the start time 
	 * for each Event
	 */
	@Override
	public int compare(Event arg0, Event arg1) {
		//negative number
		if (arg0.startTime < arg1.startTime) {
			return -1;
		}
		//positive number
		if (arg0.startTime > arg1.startTime) {
			return 1;
		}
		//equal number
		return 0;
	}
	
}

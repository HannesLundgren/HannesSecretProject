package simulator;
import java.util.Comparator;
/**
 * EventTimeComparator class is used when the simulation needs 
 * to compare the event time between two events.
 * @author Robert Högberg
 *
 */
public class EventTimeComparator implements Comparator<Event> {

	
	/**
	 * This method compares the event time between two events.
	 * @param arg0 is an Event object
	 * @param arg1 is an Event object
	 * @return int -1,0,1
	 */
	@Override
	public int compare(Event arg0, Event arg1) {
		if (arg0.startTime < arg1.startTime) {
			return -1;
		}
		if (arg0.startTime > arg1.startTime) {
			return 1;
		}
		return 0;
	}
	
//	public static void main(String[] args) {
//		Comparator<Event> comparator = new EventTimeComparator();
//		Event number1 = new Event();
//		comparator.compare(o1, o2)
		
//	}

}

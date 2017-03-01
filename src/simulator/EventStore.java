package simulator;


import java.util.PriorityQueue;
/**
 * This class represents a store for all Events 
 * in the simulation.
 * @author Robert Högberg
 *
 */
public class EventStore {
<<<<<<< Updated upstream
	PriorityQueue<Event> store;

		
=======
	EventTimeComparator eventTimeComparator = new EventTimeComparator();
	PriorityQueue<Event> store = new PriorityQueue<Event>(10,eventTimeComparator);
	
	
	
>>>>>>> Stashed changes
}

package simulator;


import java.util.PriorityQueue;
/**
 * This class represents an container for simulation events 
 * @author Robert Högberg
 *
 */
public class EventStore {
	EventTimeComparator eventTimeComparator = new EventTimeComparator();
	PriorityQueue<Event> store = new PriorityQueue<Event>(10,eventTimeComparator);
	
	/**
	 * Adds an event to the prorityQueue
	 * @param e the type is Event
	 */
	public void add(Event e) {
		store.add(e);
	}
	/**
	 * 
	 * @return the first event in the queue, then removes it.
	 */
	public Event getFirst() {
		return store.poll();
	}
	/**
	 * 
	 * @return true if the queue is empty, otherwise false.
	 */
	public boolean isEmpty() {
		return store.size()==0;
	}
	
	
	
}

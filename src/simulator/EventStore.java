package simulator;


import java.util.PriorityQueue;

public class EventStore {
	EventTimeComparator eventTimeComparator = new EventTimeComparator();
	PriorityQueue<Event> store = new PriorityQueue<Event>(10,eventTimeComparator);
	
	public void add(Event e) {
		store.add(e);
	}
	
	
	
}

package state;

import java.util.Random;

import customer.Customer;
import customer.CustomerGenerator;
import random.ExponentialRandomStream;
import random.UniformRandomStream;
import simulator.Event;
import simulator.State;
/**
 * Class that represent a state at a HairSalon
 * @author HannesLundgren
 *
 */
public class HairSalonState extends State {
	
	private int totalChairs;
	private int idleChairs;
	private double totalTimeIdle;
	private double totalTimeWaiting;
	private int numLost;
	private int numReturning;
	private Customer currentCustomer;
	private double timeForLastEvent;
	private int totalCustomers;
	private double closingTime;
	private double totalCuttingTime;
	private double latestCustomerFinishTime;
	
	
	
	private int w;
	
	private long seed; // = System.currentTimeMillis();
	private double p = 0.25;
	private double lambda = 3.0;
	private double hMin = 0.8;
	private double hMax = 1.2;
	private double dMin = 2.0;
	private double dMax = 3.0;
	
	private boolean isClosed = false;
	
	private ExponentialRandomStream ERS;
	private UniformRandomStream URSCutting;
	private UniformRandomStream URSReturning;
	
	private FIFO queue;
	private CustomerGenerator custGen = new CustomerGenerator();
	private Random rand;
	
	/**
	 * HairSalonState constructor
	 * @param totalChairs number of chairs in the salon
	 * @param maxQueueSize how many customers that can be in the queue
	 * @param closingTime The closing time for the hair salon 
	 * @param seed 
	 */
	public HairSalonState(int totalChairs, int maxQueueSize, double closingTime, long seed) {
		this.totalChairs = totalChairs;
		this.idleChairs = totalChairs;//HEJ
		this.w = maxQueueSize;
		this.closingTime = closingTime;
		this.seed = seed;
		queue = new FIFO(w);
		setCustomerArrivalDistribution(lambda,seed);
		setCuttingTimeDistribution(hMin,hMax,seed);
		setReturningTimeDistribution(dMin,dMax, seed);
		rand = new Random(seed);
		
		
		
		
	}
	/**
	 * Called when Observers are to be notified.
	 */
	public void callChanged() {
		setChanged();
		notifyObservers();
		
	}
	
	
	/**
	 * Generates customers.
	 * @return Returns a new customer.
	 */
	public Customer generateCustomer() {
		return custGen.generateCustomer();
	}
	
	/**
	 * Sets the finish time for the latest customer.
	 * @param time
	 */
	public void setLatestCustomerFinishTime(double time) {
		latestCustomerFinishTime = time;
	}
	/**
	 * 
	 * @return The finish time for the latest customer.
	 */
	public double getLatestCustomerFinishTime() {
		return latestCustomerFinishTime;
	}
	
	/**
	 * Increasing the total cutting time.
	 * @param time
	 */
	public void increaseCuttinTime(double time) {
		totalCuttingTime += time-currentTime;
	}
	
	/**
	 * 
	 * @return The average cutting time.
	 */
	public double getAverageCuttingTime() {
		return totalCuttingTime/totalCustomers;
	}
	/**
	 * Adds a customer to the queue.
	 * @param c The customer to be added.
	 * @return A truth value depending on if the customer could be added to the queue or not.
	 */
	public boolean addToQueue(Customer c) {
		
		return queue.add(c,currentTime);
	}

	/**
	 * Sets the arrival distribution for a customer 
	 * @param lambda Customer per time unit.
	 * @param seed Sequence variable
	 */
	public void setCustomerArrivalDistribution(double lambda, long seed) {
		this.lambda = lambda;
		this.seed = seed;
		ERS = new ExponentialRandomStream(lambda,seed);
	}
	
	/**
	 * Sets the cutting time distribution
	 * @param hMin Minimum cutting time  
	 * @param hMax Maximum cutting time 
	 * @param seed Sequence variable
	 */
	public void setCuttingTimeDistribution(double hMin, double hMax, long seed) {
		this.hMin = hMin; 
		this.hMax = hMax;
		this.seed = seed;
		URSCutting = new UniformRandomStream(hMin, hMax, seed);

	}
	
	
	/**
	 * Sets the returning time distribution
	 * @param dMin The minimum time for a priority customer to return
	 * @param dMax The maximum time for a priority customer to return
	 * @param seed Sequence variable
	 */
	public void setReturningTimeDistribution(double dMin, double dMax,long seed) {
		this.dMin = dMin;
		this.dMax = dMax;
		this.seed = seed; 
		URSReturning = new UniformRandomStream(dMin, dMax, seed);
	}
	
	/**
	 * Set current event.
	 * @param event The event to be set as the current event.
	 */
	public void setCurrentEvent(Event event) {
		super.currentEvent = event;
	}
	
	/**
	 * Sets the current time.
	 * @param currentTime The time to be set as the current time.
	 */
	public void setCurrentTime(double currentTime) {
		this.currentTime = currentTime;
	}
	
	/**
	 * Sets the timeForLastEvent.
	 * @param time The timeForLastEvent.
	 */
	public void setTimeForLastEvent(double time) {
		timeForLastEvent = time;
	}
	
	/**
	 * 
	 * @return The time for the latest event is returned
	 */
	public double getTimeForLastEvent() {
		return timeForLastEvent;
	}
	
	/**
	 * Sets the current customer.
	 * @param cust The customer to be set as the current customer.
	 */
	public void setCurrentCustomer(Customer cust) {
		currentCustomer = cust;
	}
	
	/**
	 * Decreases the amount of idle chairs.
	 */
	public void decreaseIdleChairs() {
		idleChairs--;
	}
	
	/**
	 * Increases the amount of idle chairs.
	 */
	public void increaseIdleChairs() {
	 
		idleChairs++;
	}
	//Getters
	
	/**
	 * @return The current time is returned.
	 */
	public double getCurrentTime() {
		return currentTime;
	}
	
	/**
	 * @return The current event is returned.
	 */
	public Event getCurrentEvent() {
		return currentEvent;
	}
	
	/**
	 * @return The unique customer id is returned.
	 */
	public int getCustomerId() {
		return 10;
	}
	
	/**
	 * @return The amount of idle chairs is returned.
	 */
	public int getIdleChairs() {
		return idleChairs;
	}
	
	/**
	 * @return The totalTimeIdle
	 */
	public double getTimeIdle() {
		return totalTimeIdle;
	}
	
	/**
	 * @return The totalTimeWaiting
	 */
	public double getTimeWaiting() {
		return totalTimeWaiting;
	}
	public long getSeed() {
		return this.seed;
	}
	
	/**
	 * Gets the number of customers in queue.
	 * @return The size of the queue is returned.
	 */
	public int getNumWaiting() {
		return queue.size();
	}
	
	/**
	 * Checks if the queue is empty.
	 * @return A truth value from the method isEmpty().
	 */
	public boolean isQueueEmpty() {
		return queue.isEmpty();
	}
	
	/**
	 * @return The numLost is returned.
	 */
	public int getNumLost() {
		return numLost;
	}
	
	/**
	 * @return The numReturning is returned.
	 */
	public int getNumReturning() {
		return numReturning;
	}
	
	/**
	 * @return The current customer is returned.
	 */
	public Customer getCurrentCustomer() {
		return currentCustomer;
	}
	
	/**
	 * @return The value of isCloesed is returned.
	 */
	public boolean isClosed() {
		return isClosed;
	}
	
	/**
	 * Checks if there are any available chairs.
	 * @return If the number of chairs available is larger than 0, true is returned. Else false is returned.
	 */
	public boolean isChairsIdle() {
		if (idleChairs>0) {
			return true;
		}
		return false;
	}
	
	/**
	 * @return The time the haircut will be finished.
	 */
	public double getHairdresserFinishTime() {
		return currentTime + URSCutting.next();
	}
	
	/** 
	 * @return The time which the next customer will arrive.
	 */
	public double getNextArrivalTime() {
		return currentTime + ERS.next();
//		return currentTime + 5;
	}
	
	/**
	 * @return The time which the unsatisfied customer arrives.
	 */
	public double getUnsatisfiedCustomerArrivalTime() {
		return currentTime + URSReturning.next();
	}
	
	/**
	 * @return The customer which is in position 1 in the queue.
	 */
	public Customer getFirst() {
		return queue.getFirst();
	}
	//Update Methods
	/**
	 * Updates totalTimeIdle
	 */
	public void updateIdleTime() {
		totalTimeIdle +=(currentTime-timeForLastEvent)*idleChairs;
	}
	
	/**
	 * Updates the time spent in queue.
	 */
	public void updateQueueTime() {
		totalTimeWaiting += (currentTime-timeForLastEvent)*queue.size();
	}
	
	/**
	 * Increases numLost(What is the task of numLost?)
	 */
	public void increaseNumLost() {
		numLost++;
	}
	
	/**
	 * Removes the first element in the queue.
	 */
	public void removeFirst() {
		queue.removeFirst();
	}
	
	/**
	 * Checks if the customer is satisfied with their haircut.
	 * @param c The customer in question.
	 * @return  A truth value depending on if a random double-value between 0.0 and 1.0 is less than 
	 * the double acceptable or not.
	 */
	public boolean checkHaircut(Customer c) {
//		int tal = (rand.nextInt(100)+1);
		
		double tal = rand.nextDouble();
		
		if(tal < p) {
			c.setPriority();
			return true;
		}
		return false;
			
	}
	/**
	 * 
	 * @param c The customer
	 * @return A truth value depending on if the queue is full of priority customers or not
	 */
	public boolean addPriorityCustomer(Customer c) {
		// TODO Auto-generated method stub
		if (queue.size()==queue.getMaxSize()) {
			totalTimeWaiting -= currentTime-queue.getLastQueueEnterTime();
			queue.removeLast();
			increaseNumLost();
			
		}
		return queue.addPriorityCustomer(c);
	}
	/**
	 * 
	 * @return Closing time is returned
	 */
	public double getClosingTime() {
		return closingTime;
		
	}
	/**
	 * Sets the hair salon as closed
	 */
	public void setClosed() {
		isClosed = true;
		
	}
	/**
	 * 
	 * @return The maximum amount of customers in the queue is returned
	 */
	public int getMaxQueueSize() {
		// TODO Auto-generated method stub
		return w;
	}
	
	/**
	 * 
	 * @return The dissatisfied customer variable is returned
	 */
	public double getP() {
		// TODO Auto-generated method stub
		return p;
	}
	/**
	 * 
	 * @return The customer per time unit is returned
	 */
	public double getLambda() {
		// TODO Auto-generated method stub
		return lambda;
	}
	
	/**
	 * 
	 * @return The minimum cutting time is returned
	 */
	public double getHMin(){
		// TODO Auto-generated method stub
		return hMin;
	}
	/**
	 * 
	 * @return The maximum cutting time is returned
	 */
	public double getHMax() {
		// TODO Auto-generated method stub
		return hMax;
	}
	/**
	 * 
	 * @return The minimum time for a priority customer to come back is returned
	 */
	public double getDMin() {
		// TODO Auto-generated method stub
		return dMin;
	}
	/**
	 * 
	 * @return The minimum time for a priority customer to return, is returned
	 */
	public double getDMax() {
		// TODO Auto-generated method stub
		return dMax;
	}
	/**
	 * 
	 * @return The total amount of customers that got a satisfied haircut.
	 */
	public int getTotCutCustomers() {
		return totalCustomers;
	}
	/**
	 * Updates the amount of satisfied customers 
	 */
	public void increaseNumberOfCust() {
		totalCustomers++;
		
	}
	/**
	 * 
	 * @return The largest customer queue in a simulation run
	 */
	public int getLargestQueueSize() {
		return queue.getLargestQueueSize();
	}
	/**
	 * 
	 * @return The average queue time is returned
	 */
	public double getAverageQueueTime() {
		return totalTimeWaiting/totalCustomers;
	}
	/**
	 * Updates the amount of returning customers 
	 */
	public void increaseNumReturning() {
		numReturning++;
		
	}
	
	
	

	
	
	
	
	
	
	
	
	
}

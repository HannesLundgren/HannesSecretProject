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
 * @author hanneslundgren
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
	
	private long seed = 1000;
	private double p = 0;
	private double lambda = 0.0666667;
	private double hMin = 30;
	private double hMax = 60;
	private double dMin = 60;
	private double dMax = 120;
	
	private boolean isClosed = false;
	
	private ExponentialRandomStream ERS;
	private UniformRandomStream URSCutting;
	private UniformRandomStream URSReturning;
	
	private FIFO queue;
	private CustomerGenerator custGen = new CustomerGenerator();
	private Random rand = new Random();
	
	
	public HairSalonState(int totalChairs, int maxQueueSize, double closingTime) {
		this.totalChairs = totalChairs;
		this.idleChairs = totalChairs;//HEJ
		this.w = maxQueueSize;
		this.closingTime = closingTime;
		queue = new FIFO(w);
		
		setCustomerArrivalDistribution(lambda);
		setCuttingTimeDistribution(hMin,hMax);
		setReturningTimeDistribution(dMin,dMax);
		
		
		
		
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

	public void setLatestCustomerFinishTime(double time) {
		latestCustomerFinishTime = time;
	}
	public double getLatestCustomerFinishTime() {
		return latestCustomerFinishTime;
	}
	
	public void increaseCuttinTime(double time) {
		totalCuttingTime += time-currentTime;
	}
	
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
	 * Javadoc pliz
	 * @param lambda
	 * @param seed
	 */
	public void setCustomerArrivalDistribution(double lambda) {
		this.lambda = lambda;
//		this.seed = seed;
		ERS = new ExponentialRandomStream(lambda);
	}
	
	/**
	 * Javadoc pliz
	 * @param hMin
	 * @param hMax
	 * @param seed
	 */
	public void setCuttingTimeDistribution(double hMin, double hMax) {
		this.hMin = hMin;
		this.hMax = hMax;
//		this.seed = seed;
		URSCutting = new UniformRandomStream(hMin, hMax);

	}
	
	/**
	 * Javadoc pliz
	 * @param dMin
	 * @param dMax
	 * @param seed
	 */
	public void setReturningTimeDistribution(double dMin, double dMax) {
		this.dMin = dMin;
		this.dMax = dMax;
//		this.seed = seed; 
		URSReturning = new UniformRandomStream(dMin, dMax);
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
		if(rand.nextDouble() < p) {
			c.setPriority();
			return true;
		}
		return false;
			
	}
	public boolean addPriorityCustomer(Customer c) {
		// TODO Auto-generated method stub
		if (queue.size()==queue.getMaxSize()) {
			totalTimeWaiting -= currentTime-queue.getLastQueueEnterTime();
			queue.removeLast();
			
		}
		return queue.addPriorityCustomer(c);
	}
	public double getClosingTime() {
		return closingTime;
		
	}
	public void setClosed() {
		isClosed = true;
		
	}
	public int getMaxQueueSize() {
		// TODO Auto-generated method stub
		return w;
	}
	public double getP() {
		// TODO Auto-generated method stub
		return p;
	}
	public double getLambda() {
		// TODO Auto-generated method stub
		return lambda;
	}
	public double getHMin(){
		// TODO Auto-generated method stub
		return hMin;
	}
	public double getHMax() {
		// TODO Auto-generated method stub
		return hMax;
	}
	public double getDMin() {
		// TODO Auto-generated method stub
		return dMin;
	}
	public double getDMax() {
		// TODO Auto-generated method stub
		return dMax;
	}
	public int getTotCutCustomers() {
		return totalCustomers;
	}
	public void increaseNumberOfCust() {
		totalCustomers++;
		
	}
	public int getLargestQueueSize() {
		return queue.getLargestQueueSize();
	}
	
	
	

	
	
	
	
	
	
	
	
	
}

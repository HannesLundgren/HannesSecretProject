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
	
	
	private int w;
	
	private long seed = 1000;
	private double p;
	private double lambda = 5;
	private double hMin = 30;
	private double hMax = 60;
	private double dMin = 60;
	private double dMax = 120;
	
	private boolean isCLosed = false;
	
	private ExponentialRandomStream ERS;
	private UniformRandomStream URSCutting;
	private UniformRandomStream URSReturning;
	
	private FIFO queue;
	private CustomerGenerator custGen = new CustomerGenerator();
	private Random rand = new Random();
	
	
	public HairSalonState(int totalChairs, int maxQueueSize) {
		this.totalChairs = totalChairs;
		this.w = maxQueueSize;
		queue = new FIFO(w);
		setCustomerArrivalDistribution(lambda,seed);
		setCuttingTimeDistribution(hMin,hMax,seed);
		setReturningTimeDistribution(dMin,dMax,seed);
		
		
		
		
	}
	public void callChanged() {
		setChanged();
		notifyObservers();
		
	}
	
	public Customer generateCustomer() {
		return custGen.generateCustomer();
	}
	//Setters
	
//	public void SetSatisfied(Customer c) {
//		c.setSatisfied();
//	}
//	public void SetNotSatisfied(Customer c) {
//		c.setNotSatisfied();
//	}
	
	public boolean addToQueue(Customer c) {
		return queue.add(c);
	}

	public void setCustomerArrivalDistribution(double lambda,long seed) {
		this.lambda = lambda;
		this.seed = seed;
		ERS = new ExponentialRandomStream(lambda,seed);
	}
	
	public void setCuttingTimeDistribution(double hMin, double hMax,long seed) {
		this.hMin = hMin;
		this.hMax = hMax;
		this.seed = seed;
		URSCutting = new UniformRandomStream(hMin, hMax, seed);

	}
	public void setReturningTimeDistribution(double dMin, double dMax,long seed) {
		this.dMin = dMin;
		this.dMax = dMax;
		this.seed = seed; 
		URSReturning = new UniformRandomStream(dMin, dMax, seed);
	}
	
	public void setCurrentEvent(Event event) {
		super.currentEvent = event;
	}
	public void setCurrentTime(double currentTime) {
		this.currentTime = currentTime;
	}
	
	public void setTimeForLastEvent(double time) {
		timeForLastEvent = time;
	}
	public void setCurrentCustomer(Customer cust) {
		currentCustomer = cust;
	}
	public void decreaseIdleChairs() {
		idleChairs--;
	}
	public void increaseIdleChairs() {
		idleChairs++;
	}
	//Getters
	
	public double getCurrentTime() {
		return currentTime;
	}
	
	public String getCurrentEvent() {
		return currentEvent.toString();
	}
	
	public int getCustomerId() {
		return 10;
	}
	public int getIdleChairs() {
		return idleChairs;
	}
	public double getTimeIdle() {
		return totalTimeIdle;
	}
	public double getTimeWaiting() {
		return totalTimeWaiting;
	}
	public int getNumWaiting() {
		return queue.size();
	}
	public boolean isQueueEmpty() {
		return queue.isEmpty();
	}
	public int getNumLost() {
		return numLost;
	}
	public int getNumReturning() {
		return numReturning;
	}
	
	public Customer getCurrentCustomer() {
		return currentCustomer;
	}
	public boolean isClosed() {
		return isCLosed;
	}
	public boolean isChairsIdle() {
		if (idleChairs>0) {
			return true;
		}
		return false;
	}
	public double getHairdresserFinishTime() {
		return currentTime + URSCutting.next();
	}
	public double getNextArrivalTime() {
		return currentTime + ERS.next();
	}
	public double getUnsatisfiedCustomerArrivalTime() {
		return currentTime + URSReturning.next();
	}
	public Customer getFirst() {
		return queue.getFirst();
	}
	//Update Methods
	public void updateIdleTime() {
		totalTimeIdle +=(currentTime-timeForLastEvent)*idleChairs;
	}
	public void updateQueueTime() {
		totalTimeWaiting += (currentTime-timeForLastEvent)*queue.size();
	}
	public void increaseNumLost() {
		numLost++;
	}
	public void removeFirst() {
		queue.removeFirst();
	}
	public boolean checkHaircut(Customer c) {
		// TODO Auto-generated method stub
		if(rand.nextDouble()<p) {
			c.setPriority();
			return true;
		}
		return false;
			
	}
	public boolean addPriorityCustomer(Customer c) {
		// TODO Auto-generated method stub
		return queue.addPriorityCustomer(c);
	}
	

	
	
	
	
	
	
	
	
	
}

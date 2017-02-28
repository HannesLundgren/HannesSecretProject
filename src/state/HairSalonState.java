package state;

import random.ExponentialRandomStream;
import random.UniformRandomStream;
import simulator.State;
/**
 * Class that represent a state at a HairSalon
 * @author hanneslundgren
 *
 */
public class HairSalonState extends State {
	
	private int totalChairs;
	private int totalIdleChairs;
	
	
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
	
	
	public HairSalonState(int totalChairs, int maxQueueSize) {
		this.totalChairs = totalChairs;
		this.w = maxQueueSize;
		//SKRIV MASSA?
		
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
		URSCutting = new UniformRandomStream(hMax, hMax, seed);

	}
	public void setReturningTimeDistribution(double dMin, double dMax,long seed) {
		this.dMin = dMin;
		this.dMax = dMax;
		this.seed = seed; 
		URSReturning = new UniformRandomStream(dMax, dMax, seed);
	}
	
	
	
	
	
	
}

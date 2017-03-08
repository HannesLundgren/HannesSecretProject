package state;

import java.util.ArrayList;
import java.util.NoSuchElementException;

import customer.Customer;
/**
 * The FIFO class represents a customer queue.
 * 
 * @author Amadeus Johansson, Hannes Lundgren, Robert Högberg
 *
 */
public class FIFO{
	//fifoList is the customer queue
	private ArrayList<Customer> fifoList = new ArrayList<Customer>();
	private int maxQueueSize;
	
	private int largestQueueSize = 0; 
	
	/**
	 * The FIFO constructor sets the maxsize for the customer queue.
	 * @param maxQueueSize
	 */
	public FIFO(int maxQueueSize) {
		this.maxQueueSize = maxQueueSize;
	}
	/**
	 * 
	 * @return maxQueueSize
	 */
	public int getMaxSize() {
		return maxQueueSize;
	}
	
	/**
	 * Adds a customer to the queue depending on its maxSize.
	 * @param cust A customer
	 * @return true if the queue isn´t full, otherwise false.
	 */
	public boolean add(Customer cust, double currentTime) {
		if(size() < maxQueueSize){
			
			cust.setQueueEnterTime(currentTime);
			fifoList.add(cust);
			if (size()>largestQueueSize) {
				++largestQueueSize;
			}
			
			return true;
		}
		return false;
	}
	/**
	 * 
	 * @return true if the queue is empty
	 */
	public boolean isEmpty() {
		return size() == 0;
	}
	/**
	 * This method gives access to the first customer in the queue.
	 * @return the first customer in the queue if the queue is not empty.
	 * @throws NoSuchElementException
	 */
	public Customer getFirst() throws NoSuchElementException {
		if(!this.isEmpty()){
			return fifoList.get(0);
		} else {
			throw new NoSuchElementException();
		}
	}
	
	/**
	 * Adds a returning customer to the queue 
	 * @param cust
	 * @return true if the queue is empty, or if the queue isn`t 
	 * occipied with other priority customers, otherwise false. 
	 */
	public boolean addPriorityCustomer(Customer cust) {
		if (isEmpty()){
			
			fifoList.add(0,cust);
			return true;
		}else {
			for (int i = 0;i<size();i++) {
				if(!fifoList.get(i).getPriority()) {
					fifoList.add(i, cust);
					if (size()>largestQueueSize) {
						++largestQueueSize;
					}
					return true;
				}
			}
		}
		
		
		return false;
	}
//	public void setFirst(Customer cust){
//		fifoList.add(0, cust);
//		if (size()>=maxQueueSize) {
//			fifoList.remove(size()-1);
//		}
//	}
	
	/**
	 * This method will remove the first customer in the queue, if the queue is not empty.
	 * @throws NoSuchElementException
	 */
	public void removeFirst() throws NoSuchElementException {
		if(!this.isEmpty()){
			fifoList.remove(0);
		} else {
			throw new NoSuchElementException();
		}
	}
	/**
	 * This method will remove the last customer in the queue, if the queue is not empty.
	 * @throws NoSuchElementException
	 */
	public void removeLast() throws NoSuchElementException {
		if(!this.isEmpty()){
			fifoList.remove(maxQueueSize-1);
		} else {
			throw new NoSuchElementException();
		}
	}
	
	/**
	 * 
	 * @return how many customers there is in the queue
	 */
	public int size() {
		return fifoList.size();
	}
	public double getLastQueueEnterTime() {
		return fifoList.get(maxQueueSize-1).getQueueEnterTime();
	}
	/**
	 * 
	 * @return the largest queue size   
	 */
	public int getLargestQueueSize() {
		return largestQueueSize;
	}
	
}

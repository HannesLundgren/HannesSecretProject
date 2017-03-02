package state;

import java.util.ArrayList;
import java.util.NoSuchElementException;

import customer.Customer;
/**
 * 
 * @author Amadeus Johansson
 *
 */
public class FIFO{
	//fifoList is the customer queue
	private ArrayList<Customer> fifoList = new ArrayList<Customer>();
	private int maxQueueSize;
	
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
	 * This method add to the queue depending on its max size.
	 * @param cust A customer
	 * @return true if the queue isn´t full, otherwise false.
	 */
	public boolean add(Customer cust) {
		if(size() < maxQueueSize){
			fifoList.add(cust);
			return true;
		}
		return false;
	}
	/**
	 * 
	 * @return true if the customer queue is empty
	 */
	public boolean isEmpty() {
		return size() == 0;
	}
	/**
	 * This method gives access to the first customer in the customerqueue.
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
	 * 
	 * @param cust
	 * @return
	 */
	public boolean addPriorityCustomer(Customer cust) {
		for (int i = 0;i<size();i++) {
			if(!fifoList.get(i).Priority()) {
				fifoList.add(i, cust);
				return true;
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
			fifoList.remove(-1);
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
	
	/**
	 * This method will clear the queue with all it´s customers
	 */
	public void clearQueue() {
		fifoList.clear();
	}

}

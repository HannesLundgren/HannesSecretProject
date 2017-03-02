package state;

import java.util.ArrayList;
import java.util.NoSuchElementException;
import java.util.Queue;

import customer.Customer;

public class FIFO{
	private ArrayList<Customer> fifoList = new ArrayList<Customer>();
	private int maxQueueSize;
	
	
	public FIFO(int maxQueueSize) {
		this.maxQueueSize = maxQueueSize;
	}
	
	public boolean add(Customer cust, double currentTime) {
		if(size() < maxQueueSize){
			fifoList.add(cust);
			cust.setQueueEnterTime(currentTime);
			return true;
		}
		return false;
	}
	
	public boolean isEmpty() {
		return size() == 0;
	}

	public Customer getFirst() throws NoSuchElementException {
		if(!this.isEmpty()){
			return fifoList.get(0);
		} else {
			throw new NoSuchElementException();
		}
	}
	
	
	public boolean addPriorityCustomer(Customer cust) {
//		if (size() == maxQueueSize) {
//			
//			removeLast();
//		}
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
	public void removeLast() {
		fifoList.remove(maxQueueSize-1); //KANSKE?! -1 eller inte?
	}

	public void removeFirst() throws NoSuchElementException {
		if(!this.isEmpty()){
			fifoList.remove(0);
		} else {
			throw new NoSuchElementException();
		}
	}

	public int size() {
		return fifoList.size();
	}
	public int getMaxSize() {
		return maxQueueSize;
	}
	public double getLastQueueEnterTime() {
		return fifoList.get(maxQueueSize-1).getQueueEnterTime();
	}

}

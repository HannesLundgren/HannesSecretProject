package state;

import java.util.ArrayList;
import java.util.NoSuchElementException;

public class FIFO{
	private ArrayList<Object> fifoList = new ArrayList<Object>();
	private int currentSize = 0;
	
	public void add(Object arg0) {
		fifoList.add(arg0);
		++currentSize;
	}
	
	public boolean isEmpty() {
		return size() == 0;
	}

	public Object getFirst() throws NoSuchElementException {
		if(!this.isEmpty()){
			return fifoList.get(0);
		} else {
			throw new NoSuchElementException();
		}
	}
	
	public void setFirst(Object object){
		Object temp = fifoList.get(0);
		fifoList.set(0, object);
		if(currentSize >= 6){
			return;
		} else {
			fifoList.add(temp);
		}
	}
	

	public void removeFirst() throws NoSuchElementException {
		if(!this.isEmpty()){
			fifoList.remove(0);
			--currentSize;
		} else {
			throw new NoSuchElementException();
		}
	}

	public int size() {
		return currentSize;
	}

}

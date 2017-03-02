package view;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.Observable;

import customer.Customer;
import simulator.Event;
import event.HairSalonStartEvent;
import event.HairSalonStopEvent;
import simulator.View;
import state.HairSalonState;
/**
 * Class used for displaying the current state
 * of the Hair Salon Simulator
 * @author hanneslundgren
 *
 */
public class HairSalonView extends View {
	HairSalonState state;
	Customer cust;

	
	public HairSalonView(HairSalonState state) {
		this.state = state;
		state.addObserver(this);
	}
	
	public void startInfoPrint() {
		System.out.println("Number of chairs available: " + state.getIdleChairs());
		System.out.println("Maximum queue size: "+ state.getMaxQueueSize());
		System.out.println("Probability of unsatisfied customer: " + state.getP());
		System.out.println("Lambda: " + state.getLambda());
		System.out.println("Hmin: " + state.getHMin());
		System.out.println("Hmax: " + state.getHMax());
		System.out.println("Dmin: "+ state.getDMin());
		System.out.println("Dmax: " + state.getDMax());
	}
	// Not done!!!
	public void summaryPrint() {
		System.out.printf("%s %s %s %s %s %s %s %s %n",
				state.getTotCutCustomers(),
				)
	}
	
	
	
	@Override
	public void update(Observable o, Object arg) {
		// TODO Auto-generated method stub
		NumberFormat numbform = new DecimalFormat("#0.00");
		Event event = (Event) arg;
		
		// Good enough for testing
		if(state.getCurrentEvent() instanceof HairSalonStartEvent) {
			System.out.printf("%s %s %s %s %s %s %s %s %s %n",
					"Time: " + numbform.format((state.getCurrentTime())),
					"Event: " + event.toString(),
					"ID: " + cust.getId(),
					"Idle: " + state.getIdleChairs(),
					"TimeIdle: " + numbform.format((state.getTimeIdle())),
					"TimeWating: " + numbform.format(state.getTimeWaiting()),
					"NumWaiting: " + state.getNumWaiting(),
					"NumLost: " + state.getNumLost(),
					"NumReturning: " + state.getNumReturning());
						
		}
				
	}

}

package view;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.Observable;

import customer.Customer;
import simulator.Event;
import event.HairSalonEvent;
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


	
	public HairSalonView(HairSalonState state) {
		this.state = state;
		state.addObserver(this);
	}
	
	
	@Override
	public void update(Observable o, Object arg) {
		// TODO Auto-generated method stub
		NumberFormat numbform = new DecimalFormat("#0.00");
		
		
		// Good enough for testing
		if(state.getCurrentEvent() instanceof HairSalonStartEvent) {
			System.out.printf("%s%n%s%n%s%n%s%n%s%n%s%n%s%n%s%n%n",
					"Number of chairs available: " + state.getIdleChairs(),
					"Maximum queue size: "+ state.getMaxQueueSize(),
					"Probability of unsatisfied customer: " + state.getP(),
					"Lambda: " + state.getLambda(),
					"Hmin: " + state.getHMin(),
					"Hmax: " + state.getHMax(),
					"Dmin: "+ state.getDMin(),
					"Dmax: " + state.getDMax());

						
		}
		
		else if (state.getCurrentEvent() instanceof HairSalonStopEvent) {
			System.out.printf("%s%n%s%n%s%n%s%n%s%n%s%n%s%n%s%n%n",
					"Total customers: " + state.getTotCutCustomers(),
					"Average cutting time: " + numbform.format(state.getAverageCuttingTime()),
					"Average waiting time: " + numbform.format(state.getAverageQueueTime()),
					"Max in queue: " + state.getLargestQueueSize(),
					"Lost customers: " + state.getNumLost(),
					"dissatisfied customers: " + state.getNumReturning(),
					"Total idle chair time: " + numbform.format(state.getTimeIdle()),
					"Time of last haircut: " + numbform.format(state.getLatestCustomerFinishTime()));
			
		

			
		}else {
			System.out.printf("%-15s %-35s %-10s %-10s %-25s %-20s %-20s %-15s %-10s%n %s%n",
					"Time: " + numbform.format((state.getCurrentTime())),
					"Event: " + state.getCurrentEvent().toString(),
					"ID: " + state.getCurrentCustomer().getId()+", ",
					"Idle chairs: " + state.getIdleChairs()+", ",
					"TimeIdle: " + numbform.format((state.getTimeIdle()))+", ",
					"TimeWaiting: " + numbform.format(state.getTimeWaiting())+", ",
					"NumWaiting: " + state.getNumWaiting()+", ",
					"NumLost: " + state.getNumLost()+", ",
					"NumReturning: " + state.getNumReturning(),
					"------------------------------------------------------------------"
					+ "------------------------------------------------------------------"
					+ "-----------------------------------------------"); }
		

		}

						
		

				
}



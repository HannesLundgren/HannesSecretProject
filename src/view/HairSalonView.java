package view;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.Observable;
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


	/**
	 * Constructor: Adds the class HairSalonView as an observer
	 *
	 * @param state 
	 */
	public HairSalonView(HairSalonState state) {
		this.state = state;
		state.addObserver(this);
	}
	
	/**
	 * When the observer is notified, the update method will print out statistics
	 * of simulation in the Console.
	 */
	@Override
	public void update(Observable o, Object arg) {
		NumberFormat numbform = new DecimalFormat("#0.00");
		
		
		
		if(state.getCurrentEvent() instanceof HairSalonStartEvent) {
			System.out.printf("%s%n%s%n%s%n%s%n%s%n%s%n%s%n%s%n%n%-15s%-30s%-10s%-18s%-18s%-18s%-18s%-13s%-10s%n%n",
					"Number of chairs available:............ " + state.getIdleChairs(),
					"Maximum queue size:.................... "+ state.getMaxQueueSize(),
					"Probability of unsatisfied customer:... " + state.getP(),
					"Lambda:................................ " + state.getLambda(),
					"Hmin:.................................. " + state.getHMin(),
					"Hmax:.................................. " + state.getHMax(),
					"Dmin:.................................. "+ state.getDMin(),
					"Dmax:.................................. " + state.getDMax(),
					"Time","Event","ID","Idle chairs","TimeIdle",
					"TimeWaiting","Numwaitning","NumLost","NumReturning");
			


						
		}
		
		else if (state.getCurrentEvent() instanceof HairSalonStopEvent) {
			System.out.printf("%s%n%s%n%s%n%s%n%s%n%s%n%s%n%s%n%n",
					"Total customers:........... " + state.getTotCutCustomers(),
					"Average cutting time:...... " + numbform.format(state.getAverageCuttingTime()),
					"Average waiting time:...... " + numbform.format(state.getAverageQueueTime()),
					"Max in queue:.............. " + state.getLargestQueueSize(),
					"Lost customers:............ " + state.getNumLost(),
					"Dissatisfied customers:.... " + state.getNumReturning(),
					"Total idle chair time:..... " + numbform.format(state.getTimeIdle()),
					"Time of last haircut:...... " + numbform.format(state.getLatestCustomerFinishTime()));
			
		}else {
			System.out.printf("%-15s%-30s%-15s%-15s%-20s%-18s%-17s%-14s%-10s%n%s%n",
					numbform.format((state.getCurrentTime())),
					state.getCurrentEvent().toString(),
					state.getCurrentCustomer().getId(),
					state.getIdleChairs(),
					numbform.format((state.getTimeIdle())),
					numbform.format(state.getTimeWaiting()),
					state.getNumWaiting(),
					state.getNumLost(),
					state.getNumReturning(),
					"------------------------------------------------------------------"
					+ "------------------------------------------------------------------"
					+ "---------------------"); }
		

		}

						
		

				
}



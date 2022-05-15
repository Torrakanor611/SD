package clientSide.entities;

import clientSide.stubs.*;

/**
 *    Chef thread.
 *
 *      It simulates the chef life cycle.
 *      Implementation of a client-server model of type 2 (server replication).
 *      Communication is based on a cbarberommunication channel under the TCP protocol.
 */

public class Chef extends Thread{
	
	/**
	 *	Chef state 
	 */
	private int chefState;
	
	/**
	 * Reference to the kitchen
	 */
	private final KitchenStub kitStub;
	
	/**
	 * Reference to the bar
	 */
	private final BarStub barStub;
	
	
	
	/**
	 * @param chefState new state of chef to be set
	 */
	public void setChefState(int chefState)
	{
		this.chefState = chefState;
	}	
	
	
	/**
	 * 	@return chef state
	 */
	public int getChefState()
	{
		return chefState;
	}
	
	
	/**
	 * Chef Thread instantiation
	 * 
	 * @param name Name of the thread
	 * @param kit Reference to the kitchen
	 * @param bar Reference to the bar
	 */
	public Chef(String name, KitchenStub kitStub, BarStub barStub) {
		super(name);
		this.chefState = ChefStates.WAITING_FOR_AN_ORDER;
		this.kitStub = kitStub;
		this.barStub = barStub;
	}

	
	
	
	/**
	 * 	Life cycle of the chef
	 */
	@Override
	public void run ()
	{
		boolean firstCourse = true;
		
		kit.watchTheNews();
		kit.startPreparation();
		do
		{
			if(!firstCourse)
				kit.continuePreparation();
			else
				firstCourse = false;
			
			kit.proceedPreparation();
			bar.alertWaiter();
			
			while(!kit.haveAllPortionsBeenDelivered())
				kit.haveNextPortionReady();
		}
		while(!kit.hasOrderBeenCompleted());
		
		kit.cleanUp();
	}
}

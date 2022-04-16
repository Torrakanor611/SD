package entities;

import sharedRegions.*;

public class Waiter extends Thread{

	/**
	 * 	Waiter state
	 */
	private int waiterState;
	
	/**
	 * Reference to the kitchen
	 */
	
	private final Kitchen kit;
	
	/**
	 * Reference to the bar
	 */
	
	private final Bar bar;
	
	/**
	 * Reference to the table
	 */
	private final Table tab;
	
	/**
	 * 	@param waiter state
	 */
	
	public void setWaiterState(int waiterState) {
		this.waiterState = waiterState;
	}
	
	/**
	 * 	@return waiter state
	 */

	public int getWaiterState() {
		return waiterState;
	}
	
	/**
	 * 	Instantiation of waiter thread
	 * 
	 * 	@param waiterState
	 */
	
	public Waiter(String name, Kitchen kit, Bar bar, Table tab) {
		super(name);
		this.waiterState = WaiterStates.APRAISING_SITUATION;
		this.kit = kit;
		this.bar = bar;
		this.tab = tab;
	}
	
	/**
	 *	Life cycle of the waiter
	 */

	@Override
	public void run ()
	{
		char request;
		boolean stop = false;
		
		while(true)
		{
			request = bar.lookAround();
			System.out.println("Waiter will do "+request);
			
			switch(request)
			{
				case 'c':	//Client arriving, needs to be presented with the menu
					tab.saluteClient(bar.getStudentBeingAnswered());
					tab.returnBar();
					break;
				case 'o':	//Order will be described to the waiter
					tab.getThePad();
					kit.handNoteToChef();
					kit.returnToBar();
					break;
				case 'p':	//Portions need to be collected and delivered
					while(!tab.haveAllClientsBeenServed()) 
					{
						kit.collectPortion();
						tab.deliverPortion();
					}
					tab.returnBar();
					break;
				case 'b':	//Bill needs to be prepared so it can be payed by the student
					bar.preprareBill();
					tab.presentBill();
					tab.returnBar();
					System.out.println("I FUCKING RECEIVED THE BILL");
					break;
				case 'g':	//Goodbye needs to be said to a student
					stop = bar.sayGoodbye();
					break;
			}
			if (stop)
				break;
		}
	}
}

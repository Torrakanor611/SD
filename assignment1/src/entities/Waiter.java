package entities;

public class Waiter extends Thread{

	/**
	 * 	Waiter state
	 */
	private int waiterState;
	
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
	
	public Waiter(int waiterState) {
		super();
		this.waiterState = waiterState;
	}
	
	/**
	 *	Life cycle of the waiter
	 */

	@Override
	public void run ()
	{
		//
	}
}

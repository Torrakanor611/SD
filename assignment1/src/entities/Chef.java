package entities;

public class Chef extends Thread{
	
	/**
	 *	Chef state 
	 */
	
	private int chefState;
	
	/**
	 * 	@param chef state
	 */
	
	public void setChefState(int chefState) {
		this.chefState = chefState;
	}
	
	/**
	 * 	@return chef state
	 */

	public int getChefState() {
		return chefState;
	}
	
	/**
	 * 	Instantiation of chef thread
	 * 
	 * 	@param chefState
	 */
	public Chef(int chefState) {
		super();
		this.chefState = chefState;
	}

	/**
	 * 	Life cycle of the waiter
	 */

	@Override
	public void run ()
	{
		//
	}
}
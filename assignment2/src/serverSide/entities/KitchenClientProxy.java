package serverSide.entities;

public class KitchenClientProxy extends Thread {
	
	/**
	 * Chef state
	 */
	private int chefState;
	
	/**
	 * Waiter state
	 */
	private int waiterState;
	
	
	
	
	
	/**
	 * Set chef state
	 * @param state state of the chef
	 */
	public void setChefState(int state) { this.chefState = state; }
	
	/**
	 * Get chef state
	 * @return chef state
	 */
	public int getChefState() { return this.chefState; }
	
	/**
	 * Set waiter state
	 * @param state state of the waiter
	 */
	public void setWaiterState(int state) { this.waiterState = state; }
	
	/**
	 * Get waiter state
	 * @return waiter state
	 */
	public int getWaiterState() { return this.waiterState; }
}

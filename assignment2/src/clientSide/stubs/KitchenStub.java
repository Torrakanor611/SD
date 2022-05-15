package clientSide.stubs;

/**
 *  Stub to the kitchen
 *
 *    It instantiates a remote reference to the barber shop.
 *    Implementation of a client-server model of type 2 (server replication).
 *    Communication is based on a communication channel under the TCP protocol.
 */
public class KitchenStub {
	/**
	 * Name of the platform where is located the kitchen server
	 */
	private String serverHostName;
	/**
	 * Port number for listening to service requests
	 */
	private int serverPortNumb;
	
	
	/**
	 * Instantiation of a stub to the kitchen 
	 * 
	 * @param serverHostName name of the platform where is located the kitchen server
	 * @param serverPortNumb port number for listening to service requests
	 */
	public KitchenStub(String serverHostName, int serverPortNumb)
	{
		this.serverHostName = serverHostName;
		this.serverPortNumb = serverPortNumb;
	}
	
	
	
	/**
	 * 	Operation watch the news
	 * 
	 * 	It is called by the chef, he waits for waiter to notify him of the order
	 */
	public synchronized void watchTheNews()	{	}
	
	/**
	 * 	Operation start presentation
	 * 
	 * 	It is called by the chef after waiter has notified him of the order to be prepared 
	 * 	to signal that preparation of the course has started
	 */
	public synchronized void startPreparation() { }
	
	/**
	 * 	Operation proceed presentation
	 * 
	 * 	It is called by the chef when a portion needs to be prepared
	 */
	public synchronized void proceedPreparation() { }
	
	/**
	 * 	Operation have all portions been delivered
	 * 
	 * 	It is called by the chef when he finishes a portion and checks if another one needs to be prepared or not
	 * 	It is also here were the chef blocks waiting for waiter do deliver the current portion
	 * 	@return true if all portions have been delivered, false otherwise
	 */
	public synchronized boolean haveAllPortionsBeenDelivered() { return true; }
	
	/**
	 *	Operation has order been completed
	 * 
	 * 	It is called by the chef when he finishes preparing all courses to check if order has been completed or not
	 * 	@return true if all courses have been completed, false or not
	 */
	public synchronized boolean hasOrderBeenCompleted() { return true; }
	
	/**
	 * 	Operation continue preparation
	 * 
	 * 	It is called by the chef when all portions have been delivered, but the course has not been completed yet
	 */
	public synchronized void continuePreparation() { }
	
	/**
	 * Operation have next portion ready
	 * 
	 * It is called by the chef after a portion has been delivered and another one needs to be prepared
	 */
	public synchronized void haveNextPortionReady() { }
	
	/**
	 * Operation clean up
	 * 
	 * It is called by the chef when he finishes the order, to close service
	 */
	public synchronized void cleanUp() { }
	
	/**
	 * Operation hand note to chef
	 * 
	 * Called by the waiter to wake chef up chef to give him the description of the order
	 */	
	public synchronized void handNoteToChef() { }
	
	/**
	 * Operation return to the bar
	 * 
	 * Called by the waiter when he is the kitchen and returns to the bar
	 */
	public synchronized void returnToBar() { }
	
	/**
	 * Operation collect portion
	 * 
	 * Called by the waiter when there is a portion to be delivered. Collect and signal chef that the portion was delivered
	 */
	public synchronized void collectPortion() { }
}

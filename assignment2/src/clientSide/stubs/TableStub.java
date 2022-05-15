package clientSide.stubs;

/**
*  Stub to the Table
 *
 *    It instantiates a remote reference to the barber shop.
 *    Implementation of a client-server model of type 2 (server replication).
 *    Communication is based on a communication channel under the TCP protocol.
 */
public class TableStub {
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
	public TableStub(String serverHostName, int serverPortNumb)
	{
		this.serverHostName = serverHostName;
		this.serverPortNumb = serverPortNumb;
	}

	
    /**
     * Obtain id of the first student to arrive
     * @return id of the first student to arrive at the restaurant
     */
    public int getFirstToArrive() { return 1; }
    
    /**
     * Obtain id of the last student to arrive
     * @return id of the last student to finish eating a meal
     */
    public int getLastToEat() { return 1; }
    
    /**
     * Set id of the first student to arrive
     * @param firstToArrive id of the first student to arrive
     */
    public void setFirstToArrive(int firstToArrive) { }
    
    /**
     * Set id of the last student to arrive
     * @param lastToArrive if of the last student to arrive to the restaurant
     */
    public void setLastToArrive(int lastToArrive) { }
    
    
    
    /**
     * Operation salute the client
     * 
     * It is called by the waiter when a student enters the restaurant and needs to be saluted
     * Waiter waits for the student to take a seat (if he hasn't done it yet)
     * Waiter waits for student to finish reading the menu
     */
    public synchronized void saluteClient(int studentIdBeingAnswered) { }
    
    /**
     * Operation return to the bar
     * 
     * It is called by the waiter to return to the bar to the appraising situation state
     */
    public synchronized void returnBar() { }
    
    /**
     * Operation get the pad
     * 
     * It is called by the waiter when an order is going to be described by the first student to arrive
     * Waiter Blocks waiting for student to describe him the order
     */
    public synchronized void getThePad() { }
    
    /**
     * Operation have all clients been served
     * 
     * Called by the waiter to check if all clients have been served or not
     * @return true if all clients have been served, false otherwise
     */
    public synchronized boolean haveAllClientsBeenServed() { return true; }
    
    /**
     * Operation deliver portion
     * 
     * Called by the waiter, to deliver a portion
     */
    public synchronized void deliverPortion() { }
    
    /**
     * Operation present the bill
     * 
     * Called by the waiter to present the bill to the last student to arrive
     */
    public synchronized void presentBill() { }
    
    /**
     * Operation siting at the table
     * 
     * Student comes in the table and sits (blocks) waiting for waiter to bring him the menu
     * Called by the student (inside enter method in the bar)
     */
    public synchronized void seatAtTable() { }
    
    /**
     * Operation read the menu
     * 
     * Called by the student to read a menu, wakes up waiter to signal that he already read the menu
     */
    public synchronized void readMenu() { }
    
    /**
     * Operation prepare the order
     * 
     * Called by the student to begin the preparation of the order (options of his companions) 
     */
    public synchronized void prepareOrder() { }
    
    /**
     * Operation everybody has chosen
     * 
     * Called by the first student to arrive to check if all his companions have choose or not
     * Blocks if not waiting to be waker up be a companion to give him his preference
     * @return true if everybody choose their course choice, false otherwise
     */
    public synchronized boolean everybodyHasChosen() { return true; }
    
    /**
     * Operation add up ones choices
     * 
     * Called by the first student to arrive to add up a companions choice to the order
     */
    public synchronized void addUpOnesChoices() { }
    
    /**
     * Operation describe the order
     * 
     * Called by the first student to arrive to describe the order to the waiter
     * Blocks waiting for waiter to come with pad
     * Wake waiter up so he can take the order
     */
    public synchronized void describeOrder() { }
    
    /**
     * Operation join the talk
     * 
     * Called by the first student to arrive so he can join his companions 
     * while waiting for the courses to be delivered
     */
    public synchronized void joinTalk() { }
    
    /**
     * Operation inform companion
     * 
     * Called by a student to inform the first student to arrive about their preferences 
     * Blocks if someone else is informing at the same time
     */
    public synchronized void informCompanion() { }
    
    /**
     * Operation start eating
     * 
     * Called by the student to start eating the meal (During random time)
     */    
    public void startEating() { }
    
	/**
     * Operation end eating
     * 
     * Called by the student to signal that he has finished eating his meal
     */
    public synchronized void endEating() { }
    
    /**
     * Operation has everybody finished eating
     * 
     * Called by the student to wait for his companions to finish eating
     */
    public synchronized boolean hasEverybodyFinishedEating() { return false; }
    
    /**
     * Operation honour the bill
     * 
     * Called by the student to pay the bill
     * Student blocks waiting for bill to be presented and signals waiter when it's time to pay it
     */
    public synchronized void honourBill() { }
    
    /**
     * 	Operation have all courses been eaten
     * 
     * 	Called by the student to check if there are more courses to be eaten
     * 	Student blocks waiting for the course to be served
     * 	@return true if all courses have been eaten, false otherwise
     */
    public synchronized boolean haveAllCoursesBeenEaten() { return true; }
    
    /**
     * Operation should have arrived earlier
     * 
     * Called by the student to check which one was last to arrive
     * @return True if current student was the last to arrive, false otherwise
     */
    public synchronized boolean shouldHaveArrivedEarlier() { return true; }
}

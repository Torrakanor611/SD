package sharedRegions;

/**
 * Table
 *
 */

public class Table {
	
	/**
	 * Id of student first to arrive at restaurant
	 */
	private int firstToArrive;
	
	/**
	 * Id of student last to arrive at restaurant
	 */
	private int lastToArrive;
	
	/**
	 * Number of orders made by students
	 */
	private int numOrders;
	
	/**
	 * Number of students that finished the course
	 */
	private int numStudentsFinishedCourse;
	
	/**
	 * Id of student last to eat
	 */
	private int lastToEat;
	
	/**
	 * Number of courses eaten
	 */
	private int numOfCoursesEaten;
	
	/**
	 * Number of students served
	 */
	private int numStudentsServed;
	
	/**
     * Reference to the General Repository.
     */
    private final GeneralRepos repos;
    
    /**
     * Table instantiation
     */
    public Table(GeneralRepos repos)
    {
    	this.firstToArrive = -1;
    	this.lastToArrive = -1;
    	this.numOrders = 0;
    	this.numStudentsFinishedCourse = 0;
    	this.lastToEat = -1;
    	this.numOfCoursesEaten = 0;
    	this.numStudentsServed = 0;
    	this.repos = repos;
    	
    }
    
    /**
     * Operation salute the client
     * 
     * It is called by the waiter when a student enters the restaurant
     */
    public synchronized void saluteClient()
    {
    	
    }
    
    /**
     * Operation return to the bar
     * 
     * It is called by the waiter ......
     */
    public synchronized void returnBar()
    {
    	
    }
    
    /**
     * Operation get the pad
     * 
     * It is called by the waiter when signaled by the student ?
     */
    public synchronized void getThePad()
    {
    	
    }
    
    /**
     * Operation have all clients been served
     * 
     * 
     */
    public synchronized void haveAllClientsBeenServed()
    {
    	
    }
    
    /**
     * Operation deliver portion
     * 
     * 
     */
    public synchronized void deliverPortion()
    {
    	
    }
    
    /**
     * Operation present the bill
     * 
     * 
     */
    public synchronized void presentBill()
    {
    	
    }
    
    /**
     * Operation prepare the order
     * 
     * 
     */
    public synchronized void prepareOrder()
    {
    	
    }
    
    /**
     * Operation read the menu
     * 
     * 
     */
    public synchronized void readMenu()
    {
    	
    }
    
    /**
     * Operation everybody has chosen
     * 
     * 
     */
    public synchronized void everybodyHasChosen()
    {
    	
    }
    
    /**
     * Operation add up ones choices
     * 
     * 
     */
    public synchronized void addUpOnesChoices()
    {
    	
    }
    
    /**
     * Operation describe the order
     * 
     * 
     */
    public synchronized void describeOrder()
    {
    	
    }
    
    /**
     * Operation join the talk
     * 
     * 
     */
    public synchronized void joinTalk()
    {
    	
    }
    
    /**
     * Operation inform companion
     * 
     * 
     */
    public synchronized void informCompanion()
    {
    	
    }
    
    /**
     * Operation start eating
     * 
     * 
     */
    public synchronized void startEating()
    {
    	
    }
    
    /**
     * Operation end eating
     * 
     * 
     */
    public synchronized void endEating()
    {
    	
    }
    
    /**
     * Operation has everybody finished eating
     * 
     * 
     */
    public synchronized void hasEverybodyFinishedEating()
    {
    	
    }
    
    /**
     * Operation honour the bill
     * 
     * 
     */
    public synchronized void honourBill()
    {
    	
    }
    
    /**
     * Operation have all courses been eaten
     * 
     * 
     */
    public synchronized void haveAllCoursesBeenEaten()
    {
    	
    }
    
    /**
     * Operation should have arrived earlier
     * 
     * 
     */
    public synchronized void shouldHaveArrivedEarlier()
    {
    	
    }
    
    
    
    
    
    
    
}

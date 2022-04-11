package sharedRegions;

/**
 * 	Kitchen
 * 
 * 	It is responsible to keep a continuously trace of waiter state
 *  Is implemented as an implicit monitor.
 *  All public methods are executed in mutual exclusion.
 *
 */

public class Kitchen {
	
	/**
	 *	Number of students present in the restaurant
	 */
	
	private int numberOfStudents;
	
	/**
	 *	Number of portions ready
	 */
	
	private int numberOfPortionsReady;
	
	/**
	 *	Number of courses currently in the order
	 */
	
	private int numberOfCoursesInTheOrder;
	
	/**
	 *	Number of portions delivered in at each course
	 */
	
	private int numberOfPortionsDelivered;
	
	/**
	 *	Number of courses delivered
	 */
	
	private int numberOfCoursesDelivered;
	
	/**
	 * 	Kitchen instantiation
	 * 
	 * 
	 */
	public Kitchen() { }
	
	/**
	 * Operation watch the news
	 * 
	 * It is called by the chef while waiting the order delivered by the waiter
	 */
	public synchronized void watchTheNews()
	{
		
	}
	
	/**
	 * Operation start presentation
	 * 
	 * It is called by the chef if waiter delivers the order
	 */
	public synchronized void startPreparation()
	{
		
	}
	
	/**
	 * Operation proceed presentation
	 * 
	 * It is called by the chef when not all portions of all courses have been delivered
	 */
	public synchronized void proceedPreparation()
	{
		
	}
	
	/**
	 * Operation have all portions been delivered
	 * 
	 * It is called by the chef when he finishes a portion
	 */
	public synchronized void haveAllPortionsBeenDelivered()
	{
		
	}
	
	/**
	 * Operation has order been completed
	 * 
	 * It is called by the chef when he finishes preparing all courses
	 */
	public synchronized void hasOrderBeenCompleted()
	{
		
	}
	
	/**
	 * Operation continue preparation
	 * 
	 * It is called by the chef when not all portions have been delivered
	 */
	public synchronized void hasOrderBeenCompleted()
	{
		
	}
	
	
}

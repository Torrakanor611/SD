package sharedRegions;

import entities.ChefStates;

/**
 * 	Kitchen
 * 
 * 	It is responsible to keep a continuously trace of waiter state
 *  Is implemented as an implicit monitor.
 *  All public methods are executed in mutual exclusion.
 *
 */

public class Kitchen
{	
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
     * Reference to the General Repository.
     */
    private final GeneralRepos repos;

	/**
	 * 	Kitchen instantiation
	 */

	public Kitchen(GeneralRepos repos)
	{
		this.numberOfStudents = 0;
		this.numberOfPortionsReady = 0;
		this.numberOfCoursesInTheOrder = 0;
		this.numberOfPortionsDelivered = 0;
		this.numberOfCoursesDelivered = 0;
		this.repos = repos;
	}

	/**
	 * 	Operation watch the news
	 * 
	 * 	It is called by the chef while waiting the order delivered by the waiter
	 */

	public synchronized void watchTheNews()
	{

	}

	/**
	 * 	Operation start presentation
	 * 
	 * 	It is called by the chef if waiter delivers the order
	 */

	public synchronized void startPreparation()
	{

	}

	/**
	 * 	Operation proceed presentation
	 * 
	 * 	It is called by the chef when not all portions of all courses have been delivered
	 */

	public synchronized void proceedPreparation()
	{

	}

	/**
	 * 	Operation have all portions been delivered
	 * 
	 * 	It is called by the chef when he finishes a portion
	 */

	public synchronized boolean haveAllPortionsBeenDelivered()
	{
		return false;

	}

	/**
	 * 	Operation has order been completed
	 * 
	 * 	It is called by the chef when he finishes preparing all courses
	 */

	public synchronized boolean hasOrderBeenCompleted()
	{
		return false;
	}

	/**
	 * 	Operation continue preparation
	 * 
	 * 	It is called by the chef when not all portions have been delivered
	 */

	public synchronized void continuePreparation()
	{

	}
	
	/**
	 * Operation have next portion ready
	 * 
	 * It is called by the chef when he finishes deliver a portion
	 */

	public synchronized void haveNextPortionReady()
	{	
		
	}
	
	/**
	 * Operation clean up
	 * 
	 * It is called by the chef when he finishes the order
	 */

	public synchronized void cleanUp()
	{	
		this.repos.setChefState(ChefStates.CLOSING_SERVICE);
	}

}

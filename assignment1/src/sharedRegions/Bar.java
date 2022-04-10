package sharedRegions;

import commInfra.*;

/**
 * 
 * Bar
 *
 *	It is responsible for....
 *	..
 */

public class Bar 
{
	/**
	 *	Number of students present in the restaurant
	 */
	
	private int numberOfStudentsAtRestaurant;
	
	/**
	 *  Number of pending requests to be answered by the waiter
	 */
	private int numberOfPendingRequests;
	
	
	/**
	 * 	Bar instantiation
	 * 
	 * 
	 */
	public Bar() { }
	
	
	/**
	 * Operation alert the waiter
	 * 
	 * It is called by the chef to alert the waiter that a portion was dished 
	 */
	public synchronized void alertWaiter()
	{
		
	}
	
	/**
	 * Operation look Around
	 * 
	 * It is called by the waiter, he checks for pending service requests and if not waits for them
	 * 	@return Character the represents the service to be executed
	 */
	public synchronized char lookAround()
	{
		return 'c';
	}
	
	/**
	 * Operation prepare the Bill
	 * 
	 * It is called the waiter to prepare the bill of the meal eaten by the students
	 */
	public synchronized void preprareBill()
	{
		
	}
	
	/**
	 * Operation say Goodbye
	 * 
	 * It is called by the waiter to say goodbye to a student that's leaving the restaurant
	 */
	public synchronized void sayGoodbye()
	{
		
	}
	
	/**
	 * Operation enter the restaurant
	 * 
	 * It is called by the student to signal that he is entering the restaurant
	 * 	@return Number of students present in the restaurant
	 */
	public synchronized int enter()
	{
		return 0;
	}
	
	/**
	 * Operation call the waiter
	 * 
	 * It is called by the first student to arrive the restaurant to call the waiter to describe the order
	 *
	 */
	public synchronized void callWaiter()
	{
		
	}
	
	/**
	 * Operation signal the waiter
	 * 
	 * It is called by the last student to finish eating to signal waiter to bring next course
	 */
	public synchronized void signalWaiter()
	{
		
	}
	
	/**
	 * Operation exit the restaurant
	 * 
	 * It is called by a student when he leaves the restaurant
	 */
	public synchronized void exit()
	{
		
	}
}

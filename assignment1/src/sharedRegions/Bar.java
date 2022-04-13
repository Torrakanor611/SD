package sharedRegions;

import commInfra.*;
import main.*;

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
	 * Queue of pending Requests
	 */
	private MemFIFO<Request> pendingServiceRequestQueue;
	
	
	
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
	 * 	For requests the chef id will be 0 
	 */
	public synchronized void alertWaiter()
	{
		Request r = new Request(0,'p');
		
		//Add a new service request to queue of pending requests (portion to be collected)
		try {
			pendingServiceRequestQueue.write(r);
		} catch (MemException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		//Update number of pending requests
		numberOfPendingRequests++;
		
		//Update chefs state
	}
	
	
	
	/**
	 * Operation look Around
	 * 
	 * It is called by the waiter, he checks for pending service requests and if not waits for them
	 * 	@return Character the represents the service to be executed
	 * 		'c' : means a client has arrived therefore needs to be presented with the menu by the waiter
	 * 		'o' : means that the waiter will hear the order and delivere it to the chef
	 * 		'p' : means that a portion needs to be delivered by the waiter
	 * 		'b' : means that the bill needs to be prepared and presented by the waiter
	 * 		'g' : means that some student wants to leave and waiter needs to say goodbye 
	 */
	public synchronized char lookAround()
	{
		Request r;
		
		//While there are no pending request, waiter blocks
		while(numberOfPendingRequests == 0)
		{
			try	{	
				wait();
			}
			catch(InterruptedException e)
			{	e.printStackTrace();
			}
		}
		

		try 
		{
			//If there is a pending request take it of the queue of pending requests
			r = pendingServiceRequestQueue.read();
			//Update number of pending requests
			numberOfPendingRequests--;
		}
		catch (MemException e)
		{	
			e.printStackTrace();
			return 0;
		}		
		
		return r.type;
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





/**
 * 
 * Request Data type
 *
 */
class Request {
	
	/**
	 * Id of the author of the request
	 */
	public int id;
	
	/**
	 * Request type
	 */
	public char type;
	
	
	/**
	 * Request Instantiation
	 * @param id of the request
	 * @param type of the request
	 */
	public Request(int id, char type)
	{
		this.id = id;
		this.type = type;
	}
}



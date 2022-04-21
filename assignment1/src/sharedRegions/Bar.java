package sharedRegions;

import commInfra.*;
import entities.*;
import main.*;

/**
 * 
 * Bar
 *
 *	It is responsible for keeping track of the several requests that must be fullfilled by the waiter
 *	Implemented as an implicit monitor
 *	Public methods executed in mutual exclusion
 *	Synchronisation points include:
 *		Everytime that the waiter has to wait for a pending request
 *		When a student has to wait for the waiter to say goodbye to him so he can leave the restaurant
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
	 * Boolean variable used to store if a course was finished or not
	 */
	private boolean courseFinished;
	
	/**
	 * Queue of pending Requests
	 */
	private MemFIFO<Request> pendingServiceRequestQueue;
	
	/**
	 * Reference to the student threads
	 */
	private final Student [] students;
	
	/**
	 * Reference to the general repository
	 */
	private final GeneralRepos repos;
	
	/**
	 * Auxiliary variable to keep track of the id of the student whose request is being answered
	 */
	private int studentBeingAnswered;
	
	/**
	 * Array of booleans to keep track of the students which the waiter has already said goodbye
	 */
	private boolean[] studentsGreeted;
	
	/**
	 * Reference to the table
	 */
	private final Table tab;
	
	
	
	/**
	 * Bar instantiation
	 * 
	 * @param repo reference to the general repository 
	 */
	public Bar(GeneralRepos repos, Table tab) 
	{
		//Initizalization of students thread
		students = new Student[ExecuteConst.N];
		for(int i = 0; i < ExecuteConst.N; i++ ) 
			students[i] = null;
		
		//Initialization of the queue of pending requests
		try {
			pendingServiceRequestQueue = new MemFIFO<> (new Request [ExecuteConst.N * ExecuteConst.M]);
		} catch (MemException e) {
			pendingServiceRequestQueue = null;
		    System.exit (1);
		}
	
		this.courseFinished = true;
		this.studentBeingAnswered = -1;
		this.repos = repos;
		this.tab = tab;
		
		this.studentsGreeted = new boolean[ExecuteConst.N];
		for(int i = 0 ;i < ExecuteConst.N; i++)
			studentsGreeted[i] = false;
	}
	
	
	/**
	 * @return Id of the student whose request is being answered
	 */
	public int getStudentBeingAnswered() { return studentBeingAnswered; }
	
	
	
	
	/**
	 * Operation alert the waiter
	 * 
	 * It is called by the chef to alert the waiter that a portion was dished
	 * 	For requests the chef id will be N+1
	 */
	public synchronized void alertWaiter()
	{
		//Chef must not alert Waiter while course is not finished
		while(!courseFinished)
		{
			try {
				wait();
			} catch (InterruptedException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
		
		Request r = new Request(ExecuteConst.N+1,'p');
		
		//Add a new service request to queue of pending requests (portion to be collected)
		try {
			pendingServiceRequestQueue.write(r);
		} catch (MemException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		//Update number of pending requests
		numberOfPendingRequests++;
		courseFinished = false;
		
		//Update chefs state
		((Chef) Thread.currentThread()).setChefState(ChefStates.DELIVERING_THE_PORTIONS);
		repos.setChefState(((Chef) Thread.currentThread()).getChefState());
		
		//Signal waiter that there is a pending request
		notifyAll();
	}
	
	
	
	
	
	
	/**
	 * Operation look Around
	 * 
	 * It is called by the waiter, he checks for pending service requests and if not waits for them
	 * 	@return Character the represents the service to be executed
	 * 		'c' : means a client has arrived therefore needs to be presented with the menu by the waiter
	 * 		'o' : means that the waiter will hear the order and deliver it to the chef
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
			try {
				wait();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
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
		//Register student id in studentBeingAnswered
		studentBeingAnswered = r.id;
		
		return r.type;
	}
	
	
	
	
	
	/**
	 * Operation prepare the Bill
	 * 
	 * It is called the waiter to prepare the bill of the meal eaten by the students
	 */
	public synchronized void preprareBill()
	{
		//Update Waiter state
		((Waiter) Thread.currentThread()).setWaiterState(WaiterStates.PROCESSING_THE_BILL);
		repos.setWaiterState(((Waiter) Thread.currentThread()).getWaiterState());
	}
	
	
	
	
	/**
	 * Operation say Goodbye
	 * 
	 * It is called by the waiter to say goodbye to a student that's leaving the restaurant
	 * @return true if there are no more students at the restaurant, false otherwise
	 */
	public synchronized boolean sayGoodbye()
	{
		//Student was greeted
		studentsGreeted[studentBeingAnswered] = true;
		//Wake up student that is waiting to be greeted by waiter
		notifyAll();
		
		//Update number of students at the restaurant
		numberOfStudentsAtRestaurant--;
		studentBeingAnswered = -1;
		
		repos.setWaiterState(((Waiter) Thread.currentThread()).getWaiterState());
		
		if(numberOfStudentsAtRestaurant == 0)
			return true;
		return false;
		
	}
	
	
	
	
	/**
	 * Operation enter the restaurant
	 * 
	 * It is called by the student to signal that he is entering the restaurant
	 */
	public void enter()
	{		
		synchronized(this)
		{
			int studentId = ((Student) Thread.currentThread()).getStudentId();
			//Update student state
			students[studentId] = ((Student) Thread.currentThread());
			students[studentId].setStudentState(StudentStates.GOING_TO_THE_RESTAURANT);
			
			numberOfStudentsAtRestaurant++;

			//Register first and last to arrive
			if(numberOfStudentsAtRestaurant == 1)
				tab.setFirstToArrive(studentId);
			else if (numberOfStudentsAtRestaurant == ExecuteConst.N)
				tab.setLastToArrive(studentId);
			
			//Add a new pending requests to the queue
			try {
				pendingServiceRequestQueue.write(new Request(studentId, 'c'));
			} catch (MemException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			//Update number of pending requests
			numberOfPendingRequests++;
			
			//Update student state
			students[studentId].setStudentState(StudentStates.TAKING_A_SEAT_AT_THE_TABLE);
			repos.updateStudentState(studentId, ((Student) Thread.currentThread()).getStudentState());
			//register seat at the general repos
			repos.updateSeatsAtTable(numberOfStudentsAtRestaurant-1, studentId);
			
			
			//Signal waiter that there is a pending request
			notifyAll();
		}
		
		//Seat student at table and block it
		tab.seatAtTable();

	}
	
	
	
	
	
	/**
	 * Operation call the waiter
	 * 
	 * It is called by the first student to arrive the restaurant to call the waiter to describe the order
	 *
	 */
	public synchronized void callWaiter()
	{
		int studentId = ((Student) Thread.currentThread()).getStudentId();
		Request r = new Request(studentId,'o');
		
		//Add a new service request to queue of pending requests (portion to be collected)
		try {
			pendingServiceRequestQueue.write(r);
		} catch (MemException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		//Update number of pending requests
		numberOfPendingRequests++;	
		
		//Signal waiter that there is a pending request
		notifyAll();
	}
	
	
	
	
	/**
	 * Operation signal the waiter
	 * 
	 * It is called by the last student to finish eating to signal waiter to bring next course
	 */
	public synchronized void signalWaiter()
	{
		int studentId = ((Student) Thread.currentThread()).getStudentId();

		if(((Student) Thread.currentThread()).getStudentState() == StudentStates.PAYING_THE_BILL)
		{		
			//Add a new pending requests to the queue
			try {
				pendingServiceRequestQueue.write(new Request(studentId, 'b'));
			} catch (MemException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			//Update number of pending requests
			numberOfPendingRequests++;	
			
			//Signal waiter that there is a pending request
			notifyAll();
			
		}
		else
		{
			courseFinished = true;		
			//Wake chef up because he is waiting to tell waiter to collect portion
			// and waiter so he can collect a new portion
			notifyAll();
		}
	}
	
	
	
	
	/**
	 * Operation exit the restaurant
	 * 
	 * It is called by a student when he leaves the restaurant
	 */
	public synchronized void exit()
	{
		int studentId = ((Student) Thread.currentThread()).getStudentId();
		Request r = new Request(studentId,'g');
		
		//Add a new service request to queue of pending requests (portion to be collected)
		try {
			pendingServiceRequestQueue.write(r);
		} catch (MemException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		//Update number of pending requests
		numberOfPendingRequests++;
		//notify waiter that there is a pending request
		notifyAll();
		
		//Update student test
		students[studentId].setStudentState(StudentStates.GOING_HOME);
		repos.updateStudentState(studentId, ((Student) Thread.currentThread()).getStudentState());
	
		
		//Block until waiter greets the student goodbye
		while(studentsGreeted[studentId] == false) {
			try {
				wait();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}	
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



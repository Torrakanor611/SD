package serverSide.objects;

import java.rmi.RemoteException;

import clientSide.entities.*;
import interfaces.*;
import serverSide.main.ExecuteConst;
import serverSide.main.ServerRestaurantTable;

/**
 * 	Table
 * 
 *  It is responsible for keeping track of the courses being eaten.
 *  Implemented as an implicit monitor
 *	Public methods executed in mutual exclusion
 *	Synchronization points for the waiter include:
 *		If saluting a student waiter must wait for him to seat at table and then wait for him to read menu
 *		Waiter has to wait for first student to arrive to describe him the order
 *		Waiter blocks waiting for student to pay the bill
 *	Synchronization points for the student include:	
 *		Student waits for waiter to bring menu specifically to him
 *		First student to arrive blocks if everybody has not chosen yet and while companions are not describing their choices
 *		First student to arrive waits for waiter to come with the pad
 *		If some student is informing about his choice, then a student must wait for his companion to finish telling his preference
 *		Students must wait that everybody is served before they can start eating
 *		When a student finishes his course must wait for his companions to finish
 *		Student that was last to eat must wait for his companions to woken up before he can signal waiter to bring next course
 *		Last student to arrive must wait for waiter to bring him the bill	
 *		
 */

public class Table implements TableInterface
{	
	/**
	 * Id of student first to arrive at restaurant
	 */
	private int firstToArrive;
	
	/**
	 * Id of student last to arrive at restaurant
	 */
	private int lastToArrive;
	
	/**
	 * Used to count number of orders made by students
	 */
	private int numOrders;
	
	/**
	 * Used to count number of students that finished the course
	 */
	private int numStudentsFinishedCourse;
	
	/**
	 * Id of last student to eat
	 */
	private int lastToEat;
	
	/**
	 * Used to count number of courses eaten
	 */
	private int numOfCoursesEaten;
	
	/**
	 * Used to count number of students served
	 */
	private int numStudentsServed;	
	
	/**
	 * Id of the student whose request the waiter is taking care of
	 */
	private int studentBeingAnswered;
	
	/**
	 * Boolean variable to check if waiter is presenting the menu or not
	 */
	private boolean presentingTheMenu;
	
	/**
	 * Boolean variable to check if waiter is taking the order
	 */
	private boolean takingTheOrder;
	
	/**
	 * Boolean variable to check if a student is informing his companion about the order
	 */
	private boolean informingCompanion;
	
	/**
	 * Used to count number of students that woke up after last student to eat has signalled them to
	 */
	private int numStudentsWokeUp;
	
	/**
	 * Boolean variable to check if waiter is processing the bill
	 */
	private boolean processingBill;
	
	/**
	 * Boolean array to check which students have seated already
	 */
	private boolean studentsSeated[];
	
	/**
	 * Boolean array to check which students have already read the menu 
	 */
	private boolean studentsReadMenu[];
	
	/**
	 * Reference to the student states
	 */
	private final int [] studentsState;
	
	/**
     * Reference to the stub of the General Repository.
     */
    private final GeneralReposInterface reposStub;
    
    /**
     * Number of entities that must make shutdown
     */
    private int nEntities;  
	
	public Table(GeneralReposInterface reposStub) {
		//Initialization of attributes
    	this.firstToArrive = -1;
    	this.lastToArrive = -1;
    	this.numOrders = 0;
    	this.numStudentsFinishedCourse = 0;
    	this.lastToEat = -1;
    	this.numOfCoursesEaten = 0;
    	this.numStudentsServed = 0;
    	this.studentBeingAnswered = 0;
    	this.numStudentsWokeUp = 0;
    	this.presentingTheMenu = false;
    	this.takingTheOrder = false;
    	this.informingCompanion = false;
    	this.processingBill = false;
    	this.reposStub = reposStub;
    	this.nEntities = 0;
    	
    	//Initialization of the boolean arrays
    	studentsSeated = new boolean[ExecuteConst.N];
    	studentsReadMenu = new boolean[ExecuteConst.N];
    	studentsState = new int[ExecuteConst.N];
    	for(int i = 0; i < ExecuteConst.N; i++)
    	{
    		studentsSeated[i] = false;
    		studentsReadMenu[i] = false;
    		studentsState[i] = -1;
    	}
	}
	
	/**
     * Obtain id of the first student to arrive
     * @return id of the first student to arrive at the restaurant
     */
	@Override
    public int getFirstToArrive() throws RemoteException { return firstToArrive; }
    
    /**
     * Obtain id of the last student to arrive
     * @return id of the last student to finish eating a meal
     */
	@Override
    public int getLastToEat() throws RemoteException { return lastToEat; }
    
    /**
     * Set id of the first student to arrive
     * @param firstToArrive id of the first student to arrive
     */
	@Override
    public void setFirstToArrive(int firstToArrive) throws RemoteException { this.firstToArrive = firstToArrive; }
    
    /**
     * Set id of the last student to arrive
     * @param lastToArrive if of the last student to arrive to the restaurant
     */
	@Override
    public void setLastToArrive(int lastToArrive) throws RemoteException { this.lastToArrive = lastToArrive; }

	@Override
	public int saluteClient(int studentIdBeingAnswered) throws RemoteException
	{
		studentBeingAnswered = studentIdBeingAnswered;
		
    	//Update Waiter state
    	reposStub.setWaiterState(WaiterStates.PRESENTING_THE_MENU);
    	
    	presentingTheMenu = true;
    	
    	//Waiter must wait while student hasn't taken a seat
    	while(studentsSeated[studentBeingAnswered] == false)
    	{
			try {
				wait();
			} catch (InterruptedException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
    	}
    	
    	//Waiter wakes student that has just arrived in order to greet him
    	notifyAll();

    	//Block waiting for student to read the menu
    	while(studentsReadMenu[studentBeingAnswered] == false)
    	{
	    	try {
				wait();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}    
    	}
    	
    	//When student has finished reading the menu his request was completed
    	studentBeingAnswered = -1;
    	presentingTheMenu  = false;
    	
    	return WaiterStates.PRESENTING_THE_MENU;
	}

	@Override
	public int returnBar() throws RemoteException
	{
		//Update Waiter state
    	reposStub.setWaiterState(WaiterStates.APRAISING_SITUATION);
 
    	return WaiterStates.APRAISING_SITUATION;
	}

	@Override
	public int getThePad() throws RemoteException
	{
		//Update Waiter state
    	reposStub.setWaiterState(WaiterStates.TAKING_THE_ORDER);
    	
    	takingTheOrder = true;
    	
    	//notify student that he can describe the order 
    	notifyAll();
    	
    	//Waiter blocks waiting for first student to arrive to describe him the order
    	while(takingTheOrder)
    	{
	    	try {
				wait();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
    	}    
    	
    	return WaiterStates.TAKING_THE_ORDER;
	}

	@Override
	public boolean haveAllClientsBeenServed() throws RemoteException
	{
		//If all clients have been served they must be notified
    	if(numStudentsServed == ExecuteConst.N) {
    		//Reset lastToEat and number of students who woke up
    		lastToEat = -1;
    		numStudentsWokeUp = 0;
    		notifyAll();
    		return true;
    	}
    	return false;
	}

	@Override
	public void deliverPortion() throws RemoteException
	{
		//Update number of Students server after portion delivery
    	numStudentsServed++; 
	}

	@Override
	public int presentBill() throws RemoteException
	{
    	processingBill = true;
    	
    	//Signal student the he can pay
    	notifyAll();
    	
    	//Update Waiter state
    	reposStub.setWaiterState(WaiterStates.RECEIVING_PAYMENT);
    	//Block waiting for his payment
    	try {
			wait();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	
    	return WaiterStates.RECEIVING_PAYMENT;
	}

	@Override
	public void seatAtTable(int studentId) throws RemoteException
	{
		studentsState[studentId] = StudentStates.TAKING_A_SEAT_AT_THE_TABLE;
    	
    	//Register that student took a seat
    	studentsSeated[studentId] = true;
    	//notify waiter that student took a seat (waiter may be waiting)
    	notifyAll();
    	
    	//Block waiting for waiter to bring menu specifically to him
    	// Student also blocks if he wakes up when waiter is bringing the menu to another student
    	do
    	{
	    	try {
				wait();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	    }
    	while(studentId != studentBeingAnswered && presentingTheMenu == false);
	}

	@Override
	public int readMenu(int studentId) throws RemoteException
	{
    	//Update student state
    	studentsState[studentId] = StudentStates.SELECTING_THE_COURSES;
    	reposStub.updateStudentState(studentId, studentsState[studentId]);
    	
    	studentsReadMenu[studentId] = true;
    	//Signal waiter that menu was already read
    	notifyAll();
    	
    	return studentsState[studentId];
	}

	@Override
	public int prepareOrder() throws RemoteException
	{
    	//Register that first student to arrive already choose his own option
    	numOrders++;
    	
    	//Update student state
    	studentsState[firstToArrive] = StudentStates.ORGANIZING_THE_ORDER;
    	reposStub.updateStudentState(firstToArrive, studentsState[firstToArrive]);
    	
    	return studentsState[firstToArrive];
	}

	@Override
	public boolean everybodyHasChosen() throws RemoteException
	{
    	if(numOrders == ExecuteConst.N)
    		return true;
    	else {
	    	//Block if not everybody has chosen and while companions are not describing their choices
	    	while(informingCompanion == false)
	    	{
	    		try {
					wait();
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
	    	}
	    	return false;
    	}
	}

	@Override
	public void addUpOnesChoices() throws RemoteException
	{
    	numOrders++;
    	informingCompanion = false;
    	//Notify sleeping student threads that order was registered
    	notifyAll();
	}

	@Override
	public void describeOrder() throws RemoteException
	{
    	//After student just placed a request in the queue in the bar
    	// now it must block waiting for waiter to come with the pad
    	while(takingTheOrder == false) 
    	{
	    	try {
				wait();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
    	}
    	
    	takingTheOrder = false;
    	//Wake waiter to describe him the order
    	notifyAll();
	}

	@Override
	public int joinTalk() throws RemoteException
	{
		//Update student state
    	studentsState[firstToArrive] = StudentStates.CHATING_WITH_COMPANIONS;
    	reposStub.updateStudentState(firstToArrive, studentsState[firstToArrive]);
    	
    	return studentsState[firstToArrive];
	}

	@Override
	public int informCompanion(int studentId) throws RemoteException
	{
    	//If some other student is informing about his order then wait must be done
    	while(informingCompanion)
    	{
    		try {
				wait();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
    	}
    	
    	informingCompanion = true;
    	//notify first student to arrive, so that he can register ones preference
    	notifyAll();
    	
    	//Update student state
    	studentsState[studentId] = StudentStates.CHATING_WITH_COMPANIONS;
    	reposStub.updateStudentState(studentId, studentsState[studentId]);	
    	
    	return studentsState[studentId];
	}

	@Override
	public int startEating(int studentId) throws RemoteException
	{
		synchronized(this)
		{
	    	//Update student state
	    	studentsState[studentId] = StudentStates.ENJOYING_THE_MEAL;
	    	reposStub.updateStudentState(studentId, studentsState[studentId]);	
		}
    	
    	//Enjoy meal during random time
        try
        { Thread.sleep ((long) (1 + 100 * Math.random ()));
        }
        catch (InterruptedException e) {}
        
        return studentsState[studentId];
	}

	@Override
	public int endEating(int studentId) throws RemoteException
	{
    	//Update number of students that finished course
    	numStudentsFinishedCourse++;
    	
    	//If all students have finished means that one more course was eaten
    	if(numStudentsFinishedCourse == ExecuteConst.N)
    	{
    		numOfCoursesEaten++;
    		//register last to eat
    		lastToEat = studentId;
    	}
    	
    	//Update student state
    	studentsState[studentId] = StudentStates.CHATING_WITH_COMPANIONS;
    	reposStub.updateStudentState(studentId, studentsState[studentId]);
    	
    	return studentsState[studentId];
	}

	@Override
	public boolean hasEverybodyFinishedEating(int studentId) throws RemoteException
	{
    	//Notify all students that the last one to eat has already finished
    	if(studentId == lastToEat)
    	{
    		//Reset number of students that finished the course
    		numStudentsFinishedCourse = 0;
    		//Reset number of students served
    		numStudentsServed = 0;
    		numStudentsWokeUp++;
    		notifyAll();
    		
    		//Last student to eat must wait for every companion to wake up from waiting for everybody to finish eating
    		//before he can proceed to signal waiter
    		while(numStudentsWokeUp != ExecuteConst.N)
    		{
    			try {
					wait();
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
    		}
    	}
    	
    	//Wait while not all students have finished
    	while(numStudentsFinishedCourse != 0) {
    		try {
				wait();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
    	}
    	//Update that a student woke up
    	numStudentsWokeUp++;
    	//If all students have woken up from last to eat signal, then student that was last to eat
    	//must be notified
    	if(numStudentsWokeUp == ExecuteConst.N)
    		notifyAll();
    	
    	return true;
	}

	@Override
	public void honourBill() throws RemoteException
	{
		//Block waiting for waiter to present the bill
    	while(!processingBill)
    	{
    		try {
				wait();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
    	}
	    	
    	//After waiter presents the bill, student signals waiter so he can wake up and receive it
    	notifyAll();
	}

	@Override
	public boolean haveAllCoursesBeenEaten() throws RemoteException
	{
		if(numOfCoursesEaten == ExecuteConst.M)
    		return true;
		else {
    		//Student blocks waiting for all companions to be served
    		while(numStudentsServed != ExecuteConst.N)
    		{
	    		try {
					wait();
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
    		}
	    	return false;
    	}
	}

	@Override
	public ReturnBoolean shouldHaveArrivedEarlier(int studentId) throws RemoteException
	{	
    	if(studentId == lastToArrive) {
	    	//Update student state
	    	studentsState[studentId] = StudentStates.PAYING_THE_BILL;
	    	reposStub.updateStudentState(studentId, studentsState[studentId]);
	    	return new ReturnBoolean(true, studentsState[studentId]);
    	}
    	else
    		return new ReturnBoolean(false, studentsState[studentId]);
	}

	@Override
	public void shutdown() throws RemoteException
	{
		nEntities += 1;
		if(nEntities >= ExecuteConst.E)
			ServerRestaurantTable.shutdown();
		notifyAll(); // ?
		
	}

}

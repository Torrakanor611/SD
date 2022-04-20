package sharedRegions;

import entities.*;
import main.ExecuteConst;

/**
 * Table
 *	Synchronisation points include:
 *		Waiter waits for students to read the menu
 *		First student to arrive blocks waiting for others to arrive and describe him the order
 *		Waiter has to wait for first student to arrive to describe him the order
 *		Student blocks waiting for the course to be served
 *		Last student to arrive blocks waiting for bill to be presented
 *		Waiter blocks waiting for student to pay the bill
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
	 * Used to count number of students that woke up after last student to eat has signaled them to
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
	 * Reference to the student threads
	 */
	private final Student [] students;
	
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
    	this.studentBeingAnswered = 0;
    	this.numStudentsWokeUp = 0;
    	this.presentingTheMenu = false;
    	this.takingTheOrder = false;
    	this.informingCompanion = false;
    	this.processingBill = false;
    	this.repos = repos;
    	
    	studentsSeated = new boolean[ExecuteConst.N];
    	studentsReadMenu = new boolean[ExecuteConst.N];
    	
    	for(int i = 0; i < ExecuteConst.N; i++)
    	{
    		studentsSeated[i] = false;
    		studentsReadMenu[i] = false;
    	}
    	
		//Initizalization of students thread
		students = new Student[ExecuteConst.N];
		for(int i = 0; i < ExecuteConst.N; i++ ) 
			students[i] = null;
    	
    }
    
    
    
    
    /**
     * @return id of the first student to arrive at the restaurant
     */
    public int getFirstToArrive() { return firstToArrive; }
    
    /**
     * 
     * @return id of the last student to finish eating a meal
     */
    public int getLastToEat() { return lastToEat; }
    
    /**
     * 
     * @param firstToArrive id of the first student to arrive
     */
    public void setFirstToArrive(int firstToArrive) { this.firstToArrive = firstToArrive; }
    
    /**
     * 
     * @param lastToArrive if of the last student to arrive to the restaurant
     */
    public void setLastToArrive(int lastToArrive) { this.lastToArrive = lastToArrive; }
    
    
    
    
    
    
    /**
     * Operation salute the client
     * 
     * It is called by the waiter when a student enters the restaurant
     */
    public synchronized void saluteClient(int studentIdBeingAnswered)
    {
    	studentBeingAnswered = studentIdBeingAnswered;
    	
    	//Update Waiter state
    	((Waiter) Thread.currentThread()).setWaiterState(WaiterStates.PRESENTING_THE_MENU);
    	repos.setWaiterState(((Waiter) Thread.currentThread()).getWaiterState());
    	
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
    	System.out.println("Saluting");
    	
    	//Waiter wakes student that has just arrived in order to greet him
    	notifyAll();
    	System.out.println("Waiter Saluting student "+studentBeingAnswered+ " "+presentingTheMenu);
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
    }
    
    
    
    
    /**
     * Operation return to the bar
     * 
     * It is called by the waiter to change to return to the bar appraising situation
     */
    public synchronized void returnBar()
    {
    	//Update Waiter state
    	((Waiter) Thread.currentThread()).setWaiterState(WaiterStates.APRAISING_SITUATION);
    	repos.setWaiterState(((Waiter) Thread.currentThread()).getWaiterState());    	
    }
    
    
    
    
    /**
     * Operation get the pad
     * 
     * It is called by the waiter when an order is going to be described by the first student to arrive
     * Waiter Blocks waiting for student to describe him the order
     */
    public synchronized void getThePad()
    {
    	//Update Waiter state
    	((Waiter) Thread.currentThread()).setWaiterState(WaiterStates.TAKING_THE_ORDER);
    	repos.setWaiterState(((Waiter) Thread.currentThread()).getWaiterState());
    	
    	takingTheOrder = true;
    	
    	//notify student that he can describe the order 
    	notifyAll();
    	
    	System.out.println("Waiter is now wainting for order description");
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
    	
    	System.out.println("Waiter Got the order");
    	
    }
    
    
    
    
    /**
     * Operation have all clients been served
     * 
     * Called by the waiter to check if all clients have been served or not
     * @return true if all clients have been served, false otherwise
     */
    public synchronized boolean haveAllClientsBeenServed()
    {    	
    	//If all clients have been served they must be notified
    	if(numStudentsServed == ExecuteConst.N) {
    		System.out.println("I CHANGED numStudentsFinished");
    		lastToEat = -1;
    		numStudentsWokeUp = 0;
    		notifyAll();
    		return true;
    	}
    	return false;
    	
    }
    
    
    
    /**
     * Operation deliver portion
     * 
     * Called by the waiter, when a portion is delivered at the table
     */
    public synchronized void deliverPortion()
    {
    	//Update number of Students server after portion delivery
    	numStudentsServed++; 
    }
    
    
    
    
    
    /**
     * Operation present the bill
     * 
     * Called by the waiter to present the bill to the last student to arrive
     */
    public synchronized void presentBill()
    {
    	processingBill = true;
    	
    	//Signal student the he can pay
    	notifyAll();
    	
    	((Waiter) Thread.currentThread()).setWaiterState(WaiterStates.RECEIVING_PAYMENT);
    	repos.setWaiterState(((Waiter) Thread.currentThread()).getWaiterState());
    	//Block waiting for his payment
    	try {
			wait();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	
    }
    
    
    
    /**
     * Operation siting at the table
     * 
     * Student comes in the table and sits (blocks) waiting for waiter to bring him the menu
     * Called by the student (inside enter method in the bar)
     */
    public synchronized void seatAtTable()
    {
    	int studentId = ((Student) Thread.currentThread()).getStudentId();
    	
		students[studentId] = ((Student) Thread.currentThread());
		students[studentId].setStudentState(StudentStates.TAKING_A_SEAT_AT_THE_TABLE);
    	
    	System.out.println("Student"+ studentId+" took a seat and blocked");
    	//Register that student took a seat
    	studentsSeated[studentId] = true;
    	//notify waiter that student took a seat (waiter may be waiting)
    	notifyAll();
    	
    	//Block waiting for waiter to bring menu specifically to him
    	// Student also blocks if he wakes up when waiter is bringing the menu to another student
    	while (true)
    	{
	    	try {
				wait();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	    	System.out.println("I student "+studentId+"was waken up ("+studentBeingAnswered+", "+takingTheOrder+")");
	    	if (studentId == studentBeingAnswered && presentingTheMenu == true) {
	    		System.out.println("I student "+studentId+" Can Proceed");
	    		break;
	    	}
	    }
    	System.out.println("Student "+studentId+ " was presented with the menu ("+studentBeingAnswered+")");
    	
    	
    }
    
    
    
    /**
     * Operation read the menu
     * 
     * Called by the student to read a menu, wakes up waiter to signal that he already read the menu
     */
    public synchronized void readMenu()
    {
    	int studentId = ((Student) Thread.currentThread()).getStudentId();
    	
    	//Update student state
    	students[studentId].setStudentState(StudentStates.SELECTING_THE_COURSES);
    	repos.updateStudentState(studentId, ((Student) Thread.currentThread()).getStudentState());
    	
    	studentsReadMenu[studentId] = true;
    	//Signal waiter that menu was already read
    	notifyAll();
    	
    	System.out.println("Student "+studentId+ " read the menu ("+studentBeingAnswered+")");
    }    
    
    
    
    
    
    
    /**
     * Operation prepare the order
     * 
     * Called by the student to begin the preparation of the order, 
     */
    public synchronized void prepareOrder()
    {    	
    	//Register that first student to arrive already choose his own option
    	numOrders++;
    	
    	//Update student state
    	students[firstToArrive].setStudentState(StudentStates.ORGANIZING_THE_ORDER);
    	repos.updateStudentState(firstToArrive, ((Student) Thread.currentThread()).getStudentState());
    	
    }
    
    

    
    /**
     * Operation everybody has chosen
     * 
     * Called by the first student to arrive to check if all his companions have choose or not
     * Blocks if not.
     * @return true if has everybody choosen, false otherwise
     */
    public synchronized boolean everybodyHasChosen()
    {
    	if(numOrders == ExecuteConst.N)
    		return true;
    	else {
	    	//Block if not everybody has choosen and while companions are not describing their choices
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
    
    
    
    
    /**
     * Operation add up ones choices
     * 
     * Called by the first student to arrive to add up a companions choice to the order
     */
    public synchronized void addUpOnesChoices()
    {
    	numOrders++;
    	informingCompanion = false;
    	//Notify sleeping student threads that order was registered
    	notifyAll();
    }
    
    
    
    
    /**
     * Operation describe the order
     * 
     * Called by the first student to arrive to describe the order to the waiter
     * Blocks waiting for waiter to come with pad
     * Wake waiter up so he can take the order
     */
    public synchronized void describeOrder()
    {
    	System.out.println("I reached here");
    	//After student just putted a request in the queue in the bar, now it must block
    	// in the table waiting for waiter to come with the pad
    	while(takingTheOrder == false) 
    	{
	    	try {
				wait();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
    	}
    	
    	System.out.println("Student "+firstToArrive+" described the order");
    	takingTheOrder = false;
    	//Wake waiter to describe him the order
    	notifyAll();
    }
    
    
    
    
    
    /**
     * Operation join the talk
     * 
     * Called by the first student to arrive so he can join his companions while waiting for the courses 
     */
    public synchronized void joinTalk()
    {
    	//Update student state
    	students[firstToArrive].setStudentState(StudentStates.CHATING_WITH_COMPANIONS);
    	repos.updateStudentState(firstToArrive, ((Student) Thread.currentThread()).getStudentState());   
    }
    
    
    
    
    
    /**
     * Operation inform companion
     * 
     * Called by a student to inform the first student to arrive about his preferences 
     * Blocks waiting for courses
     */
    public synchronized void informCompanion()
    {
    	int studentId = ((Student) Thread.currentThread()).getStudentId();
    	
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
    	students[studentId].setStudentState(StudentStates.CHATING_WITH_COMPANIONS);
    	repos.updateStudentState(studentId, ((Student) Thread.currentThread()).getStudentState());
    	
    	
    }
    
    
    
    
    /**
     * Operation start eating
     * 
     * Called by the student to start eating the meal (During random time)
     */    
    public synchronized void startEating()
    {
    	int studentId = ((Student) Thread.currentThread()).getStudentId();
    	 
    	//Update student state
    	students[studentId].setStudentState(StudentStates.ENJOYING_THE_MEAL);
    	repos.updateStudentState(studentId, ((Student) Thread.currentThread()).getStudentState());
    	
    	//Enjoy meal during random time
        try
        { Thread.sleep ((long) (1 + 100 * Math.random ()));
        }
        catch (InterruptedException e) {}
    }



	/**
     * Operation end eating
     * 
     * Called by the student to signal that he has finished eating his meal
     */
    public synchronized void endEating()
    {
    	int studentId = ((Student) Thread.currentThread()).getStudentId();
    	
    	//Update numstudents finished course
    	numStudentsFinishedCourse++;
    	System.out.println("I "+studentId+" finished"+numStudentsFinishedCourse);
    	
    	//If all students have finished means that one more course was eaten
    	if(numStudentsFinishedCourse == ExecuteConst.N)
    	{
    		numOfCoursesEaten++;
    		//register last to eat
    		lastToEat = studentId;
    	}
    	
    	//Update student state
    	students[studentId].setStudentState(StudentStates.CHATING_WITH_COMPANIONS);
    	repos.updateStudentState(studentId, ((Student) Thread.currentThread()).getStudentState());
    }
    
    
    
    
    
    /**
     * Operation has everybody finished eating
     * 
     * Called by to student to wait for his companions to finish eating
     */
    public synchronized boolean hasEverybodyFinishedEating()
    {
    	int studentId = ((Student) Thread.currentThread()).getStudentId();
    	
    	//Notify all students that the last one to eat has already finished
    	if(studentId == lastToEat)
    	{
    		numStudentsFinishedCourse = 0;
    		numStudentsServed = 0;
    		numStudentsWokeUp++;
    		notifyAll();
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
    		System.out.println("Student "+studentId+" woke "+numStudentsFinishedCourse);
    	}
    	numStudentsWokeUp++;
    	if(numStudentsWokeUp == ExecuteConst.N)
    		notifyAll();
    	
    	return true;
    }
    
    
    
    
    
    /**
     * Operation honour the bill
     * 
     * Called by the student to pay the bill
     * Student blocks waiting for bill to be presented and signals waiter when it's time to pay it
     */
    public synchronized void honourBill()
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
    	System.out.println("I PAYED THE BILL");
	    	
    	//After waiter presents the bill, student signals waiter so he can wake up and receive it
    	notifyAll();
    }
    
    
    
    
    
    
    /**
     * Operation have all courses been eaten
     * 
     * Called by the student to check if there are more courses to be eaten
     * 	Student blocks waiting for the course to be served
     * 	@return true if all courses have been eaten, false otherwise
     */
    public synchronized boolean haveAllCoursesBeenEaten()
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
    		System.out.println("All served!!! Course "+ numOfCoursesEaten);
	    	return false;
    	}
    	
    }
    
    
    
    
    
    /**
     * Operation should have arrived earlier
     * 
     * Called by the student to check wich one was last to arrive
     * @return True if current student was the last to arrive, false otherwise
     */
    public synchronized boolean shouldHaveArrivedEarlier()
    {
    	int studentId = ((Student) Thread.currentThread()).getStudentId();

    	if(studentId == lastToArrive) {
	    	//Update student state
	    	students[studentId].setStudentState(StudentStates.PAYING_THE_BILL);
	    	repos.updateStudentState(studentId, ((Student) Thread.currentThread()).getStudentState());
	    	return true;
    	}
    	else
    		return false;
    }
    
    
    
    
    
    
}

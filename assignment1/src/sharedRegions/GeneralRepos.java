package sharedRegions;

import java.util.Objects;
import entities.*;
import main.*;
import genclass.GenericIO;
import genclass.TextFile;

/**
 *  General Repository.
 *
 *    It is responsible to keep the visible internal state of the problem and to provide means for it
 *    to be printed in the logging file.
 *    It is implemented as an implicit monitor.
 *    All public methods are executed in mutual exclusion.
 *    There are no internal synchronization points.
 */

public class GeneralRepos
{
	/**
	 *  Name of the logging file.
	 */

	private final String logFileName;

	/**
	 *  State of the Chef
	 */

	private int chefState;

	/**
	 *	State of the Waiter
	 */

	private int waiterState;

	/**
	 *  State of the Chef
	 */

	private int[] studentState;

	/**
	 *	Number of courses delivered (not sure)
	 */

	private int nCourses;

	/**
	 * 	Number of Portions delivered (not sure)
	 */

	private int nPortions;

	/**
	 * 	Seats at the table
	 */
	private int[] seatsAtTable;

	/**
	 *	Instantiation of a general repository object.
	 *
	 *	@param logFileName name of the logging file
	 */

	public GeneralRepos (String logFileName)
	{
		if ((logFileName == null) || Objects.equals (logFileName, ""))
			this.logFileName = "logger";
		else this.logFileName = logFileName;  		
		chefState = ChefStates.WAITING_FOR_AN_ORDER;
		waiterState = WaiterStates.APRAISING_SITUATION;
		studentState = new int[ExecuteConst.N];
		for (int i = 0; i < ExecuteConst.N; i++)
			studentState[i] = StudentStates.GOING_TO_THE_RESTAURANT;
		nCourses = 0;
		nPortions = 0;
		seatsAtTable = new int[ExecuteConst.N];
		reportInitialStatus ();
	}

	/**
	 *  Write the header to the logging file.
	 *
	 *  The chef and the waiter are sleeping and the students are going to the restaurant.
	 */

	private void reportInitialStatus ()
	{
		TextFile log = new TextFile ();                      // instantiation of a text file handler

		if (!log.openForWriting (".", logFileName))
		{ 
			GenericIO.writelnString ("The operation of creating the file " + logFileName + " failed!");
			System.exit (1);
		}
		log.writelnString ("                                        The Restaurant - Description of the internal state");
		log.writelnString (" Chef Waiter  Stu0  Stu1  Stu2  Stu3  Stu4  Stu5  Stu6  NCourse  NPortion                    Table");
		log.writelnString ("State State  State State State State State State State                     Seat0 Seat1 Seat2 Seat3 Seat4 Seat5 Seat6");
		if (!log.close ())
		{ 
			GenericIO.writelnString ("The operation of closing the file " + logFileName + " failed!");
			System.exit (1);
		}
		reportStatus ();
	}

	/**
	 *  Write a state line at the end of the logging file.
	 *
	 *  The current state of the barbers and the customers is organized in a line to be printed.
	 *  Internal operation.
	 */

	private void reportStatus ()
	{
		TextFile log = new TextFile ();                  	// instantiation of a text file handler

		String line = "";                              		// state line to be printed

		if (!log.openForAppending (".", logFileName))
		{ 
			GenericIO.writelnString ("The operation of opening for appending the file " + logFileName + " failed!");
			System.exit (1);
		}
		switch(chefState)
		{
		case ChefStates.WAITING_FOR_AN_ORDER: line += "WAFOR "; break;
		case ChefStates.PREPARING_THE_COURSE: line += "PRPCS "; break;
		case ChefStates.DISHING_THE_PORTIONS: line += "DSHPT "; break;
		case ChefStates.DELIVERING_THE_PORTIONS: line += "DLVPT "; break;
		case ChefStates.CLOSING_SERVICE: line += "CLSSV "; break;
		}

		switch(waiterState)
		{
		case WaiterStates.APRAISING_SITUATION: line += "APPST  "; break;
		case WaiterStates.PRESENTING_THE_MENU: line += "PRSMN  "; break;
		case WaiterStates.TAKING_THE_ORDER: line += "TKODR  "; break;
		case WaiterStates.WAITING_FOR_PORTION: line += "WTFPT "; break;
		case WaiterStates.PROCESSING_THE_BILL: line += "PRCBL "; break;
		case WaiterStates.RECEIVING_PAYMENT: line += "RECPM  "; break;
		}

		for(int i = 0; i < ExecuteConst.N; i++)
		{
			switch(studentState[i])
			{
			case StudentStates.GOING_TO_THE_RESTAURANT: line += "GGTRT "; break;
			case StudentStates.TAKING_A_SEAT_AT_THE_TABLE: line += "TKSTT "; break;
			case StudentStates.SELECTING_THE_COURSES: line += "SELCS "; break;
			case StudentStates.ORGANIZING_THE_ORDER: line += "OGODR "; break;
			case StudentStates.CHATING_WITH_COMPANIONS: line += "CHTWC "; break;
			case StudentStates.ENJOYING_THE_MEAL: line += "EJYML "; break;
			case StudentStates.PAYING_THE_BILL: line += "PYTBL "; break;
			case StudentStates.GOING_HOME: line += "GGHOM "; break;
			}
		}

		line += "    " + String.valueOf(nCourses);
		line += "        " + String.valueOf(nPortions);
		line += "        " + String.valueOf(seatsAtTable[0]);
		for(int i = 1; i < ExecuteConst.N; i++)
		{
			line += "     " + String.valueOf(seatsAtTable[i]);
		}

		log.writelnString (line);
		if (!log.close ())
		{ 
			GenericIO.writelnString ("The operation of closing the file " + logFileName + " failed!");
			System.exit (1);
		}
	}

	/**
	 * @param value chef state to set
	 */
	public synchronized void setChefState(int value) {
		this.chefState = value;
		reportStatus();
	}

	/**
	 * @param value waiter state to set
	 */
	public synchronized void setWaiterState(int value) {
		this.waiterState = value;
		reportStatus();
	}

	/**
	 * @param id student id
	 * @param value student state to set
	 */
	public synchronized void updateStudentState(int id, int value) {
		this.studentState[id] = value;
		reportStatus();
	}

	/**
	 * @param value nCourses value to set
	 */
	public synchronized void setnCourses(int value) {
		this.nCourses = value;
		reportStatus();
	}

	/**
	 * @param value nPortions value to set
	 */
	public synchronized void setnPortions(int value) {
		this.nPortions = value;
		reportStatus();
	}

	/**
	 * @param seat seat at the table
	 * @param id student id to sit
	 */
	public synchronized void updateSeatsAtTable(int seat, int id) {
		this.seatsAtTable[seat] = id;
		reportStatus();
	}
}










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

	private final int chefState;

	/**
	 *	State of the Waiter
	 */

	private final int waiterState;

	/**
	 *  State of the Chef
	 */

	private final int[] studentState;

	/**
	 *	Number of courses delivered (not sure)
	 */

	private final int nCourses;

	/**
	 * 	Number of Portions delivered (not sure)
	 */

	private final int nPortions;

	/**
	 * 	Seats at the table
	 */
	private final int[] seatsAtTable;

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
		log.writelnString ("                The Restaurant - Description of the internal state");
		log.writelnString (" Chef Waiter  St0  Stu1  Stu2  Stu3  Stu4  Stu5  Stu6   NCourse  NPortion					 Table");
		log.writelnString("State State  State State State State State State State 					   Seat0 Seat1 Seat2 Seat3 Seat4 Seat5 Seat6");
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
		case ChefStates.WAITING_FOR_AN_ORDER: line += "WAFOR ";
		case ChefStates.PREPARING_THE_COURSE: line += "PRPCS ";
		case ChefStates.DISHING_THE_PORTIONS: line += "DSHPT ";
		case ChefStates.DELIVERING_THE_PORTIONS: line += "DLVPT ";
		case ChefStates.CLOSING_SERVICE: line += "CLSSV ";
		}

		switch(waiterState)
		{
		case WaiterStates.APRAISING_SITUATION: line += "APPST  ";
		case WaiterStates.PRESENTING_THE_MENU: line += "PRSMN  ";
		case WaiterStates.TAKING_THE_ORDER: line += "TKODR ";
		case WaiterStates.WAITING_FOR_PORTION: line += "WTFPT ";
		case WaiterStates.PROCESSING_THE_BILL: line += "PRCBL ";
		case WaiterStates.RECEIVING_PAYMENT: line += "RECPM  ";
		}

		for(int i = 0; i < ExecuteConst.N; i++)
		{
			switch(studentState[i])
			{
			case StudentStates.GOING_TO_THE_RESTAURANT: line += "GGTRT ";
			case StudentStates.TAKING_A_SEAT_AT_THE_TABLE: line += "TKSTT ";
			case StudentStates.SELECTING_THE_COURSES: line += "SELCS ";
			case StudentStates.ORGANIZING_THE_ORDER: line += "OGODR ";
			case StudentStates.CHATING_WITH_COMPANIONS: line += "CHTWC ";
			case StudentStates.ENJOYING_THE_MEAL: line += "EJYML ";
			case StudentStates.PAYING_THE_MEAL: line += "PYTBL ";
			case StudentStates.GOING_HOME: line += "GGHOM ";
			}
		}

		line += "     " + String.valueOf(nCourses);
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
}










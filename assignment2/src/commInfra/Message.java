package commInfra;

import java.io.*;
import genclass.GenericIO;
import serverSide.main.ExecuteConst;

/**
 *   Internal structure of the exchanged messages.
 *
 *   Implementation of a client-server model of type 2 (server replication).
 *   Communication is based on a communication channel under the TCP protocol.
 */

public class Message implements Serializable
{
	/**
	 *  Serialization key.
	 */
	private static final long serialVersionUID = 2021L;

	/**
	 *  Message type.
	 */
	private int msgType = -1;

	/**
	 * Chef State
	 */
	private int chefState = -1;

	/**
	 * Waiter State
	 */
	private int waiterState = -1;

	/**
	 * Student State
	 */
	private int studentState = -1;

	/**
	 * Student Id
	 */
	private int studentId = -1;
	
	/**
	 * student Id Being Answered
	 */	
	private int studentIdBeingAnswered = -1;
	/**
	 * Boolean value to be transported that holds true if all portions have been delivered, false otherwise
	 */
	private boolean allPortionsDelivered;

	/**
	 * Boolean value to be transported that holds true if order has been completed, false otherwise
	 */
	private boolean orderCompleted;
	
	/**
	 * Holds true if there are no students at the restaurant, false otherwise
	 */
	private boolean studentsAtRestaurant;
	
	/**
	 * Holds the value of the type of request that must be answered by the waiter
	 */
	private char requestType;
	
	/**
	 * Holds the id of the student whose request is being answered by the waiter
	 */
	private int studentBeingAnswered;
	
	/**
	 * Holds true if all clients have been served, false otherwise
	 */
	private boolean allClientsBeenServed;
	
	/**
	 * Holds true if everybody has choose their preference, false otherwise
	 */
	private boolean everybodyHasChosen;
	
	/**
	 * Holds true if everybody has finished eating, false otherwise
	 */
	private boolean everybodyHasEaten;
	
	/**
	 * Holds true if all courses have been eaten, false otherwise
	 */
	private boolean haveAllCoursesBeenEaten;
	
	/**
	 * Used to check which student was the last to arrive in the Table
	 */
	private boolean shouldArrivedEarlier;
	
	/**
	 * Holds the id of the first student to arrive
	 */
	private int firstToArrive;
	
	/**
	 * Holds the id of the last student to eat
	 */
	private int lastToEat;
	
	/**
	 * Holds the id of the last student to arrive
	 */
	private int lastToArrive;
	

	/**
	 *  Message instantiation (form 1).
	 *
	 *     @param type message type
	 */
	public Message (int type)
	{
		msgType = type;
	}

	/**
	 *  Message instantiation (form 2).
	 *
	 *     @param type message type
	 *     @param stateOrId chef, waiter or student state, or student id or id of studentBeingAnswered
	 */
	public Message (int type, int stateOrId)
	{
		msgType = type;
		int entitie = getEntitieFromMessageType(type);

		if(entitie == 1) //Chef message
			chefState = stateOrId;
		else if (entitie == 2) //Waiter Message
			waiterState = stateOrId;
		else if (entitie == 3) { //Student message
			if(msgType == MessageType.REQCALLWAI || msgType == MessageType.REPCALLWAI)
				studentId = stateOrId;
			else if(msgType == MessageType.REQPREPORDER || msgType == MessageType.REQJOINTALK)
				studentState = stateOrId;
		}
		else if (entitie == 4) {
			if(msgType == MessageType.REQSALUTCLI)
				studentBeingAnswered = stateOrId;
			else if (msgType == MessageType.REPGETFRSTARR)
				firstToArrive = stateOrId;
			else if (msgType == MessageType.REPGETLSTEAT)
				lastToEat = stateOrId;
			else if (msgType == MessageType.REQSETFRSTARR)
				firstToArrive = stateOrId;
			else if (msgType == MessageType.REQSETLSTARR)
				lastToArrive = stateOrId;
		}
		else { 
			GenericIO.writelnString ("Message type = " + msgType + ": non-implemented instantiation!");
			System.exit (1);
		}

	}

	/**
	 * Message instantiation (form 3).
	 * 
	 * 	@param type message type
	 * 	@param bValue boolean that can have haveAllPortionsBeenDeliverd, hasOrderBeenCompleted, studentsAtRestaurant 
	 * 		or allBeenClientsServed value
	 */
	public Message(int type, boolean bValue)
	{
		msgType = type;
		if (msgType == MessageType.REPHVPRTDLVD)
			allPortionsDelivered = bValue;
		else if (msgType == MessageType.REPHORDCOMPL)
			orderCompleted = bValue;   
		else if (msgType == MessageType.REPSAYGDBYE)
			studentsAtRestaurant = bValue;
		else if (msgType == MessageType.REPALLCLISERVED)
			allClientsBeenServed = bValue;
		else if (msgType == MessageType.REPEVERYBDYCHO)
			everybodyHasChosen = bValue;
		else if (msgType == MessageType.REQALLCOURBEENEAT)
			haveAllCoursesBeenEaten = bValue;
			
	}

	/**
	 *  Message instantiation (form 4).
	 *
	 *     @param type message type
	 *     @param id student identification
	 *     @param state student state
	 */

	public Message (int type, int id, int state)
	{
		msgType = type;
		int entity = getEntitieFromMessageType(type);

		if (entity != 3) {	// Not a Student entity Type Message
			GenericIO.writelnString ("Message type = " + msgType + ": non-implemented instantiation on Student!");
			System.exit (1);
		}
		studentState = state;
		if ( id < 0 || id  >= ExecuteConst.N) {	// Not a valid Student id
			GenericIO.writelnString ("Invalid student id");
			System.exit (1);
		}
		studentId = id;
	}
	
	
	/**
	 * Message instantiation (form 5).
	 * 
	 * 		@param type message type
	 * 		@param id id of the student
	 * 		@param bValue can hold the value of everybodyHasFinished or shouldHaveArrivedEarlier
	 */
	public Message (int type, int id, boolean everybodyEaten)
	{
		msgType = type;
		studentId = id;
		everybodyHasEaten = everybodyEaten;		
	}
	
	
	/**
	 *  Message instantiation (form 6).
	 *
	 *     @param type message type
	 *     @param id of the student
	 *     @param state student state
	 *     @param shouldArrived shouldHaveArrived Earlier value
	 */

	public Message (int type, int id, int state, boolean shouldArrived)
	{
		msgType = type;
		studentId = id;
		studentState = state;
		shouldArrivedEarlier = shouldArrived;
	}
	
	
	/**
	 * 	Message instantiation (form 7).
	 * 		@param type message type
	 * 		@param c character that identifies which request should the waiter attend
	 */
	public Message(int type, char c)
	{
		msgType = type;
		requestType = c;		
	}
	
	
	/**
	 *  Getting message type.
	 *     @return message type
	 */
	public int getMsgType () { return (msgType); }

	/**
	 * Getting chef state
	 * 	@return chef state
	 */
	public int getChefState() { return (chefState); }

	/**
	 * Getting waiter state
	 * 	@return waiter state
	 */
	public int getWaiterState() { return (waiterState); }

	/**
	 * Getting student state
	 * 	@return student state
	 */
	public int getStudentState() { return (studentState); }

	/**
	 * Getting student id
	 * 	@return student id
	 */
	public int getStudentId() { return (studentId); }
	
	/**
	 * Getting student being answered id
	 * @return studentIdBeingAnswered
	 */
	public int getStudentIdBeingAnswered() {return (studentIdBeingAnswered); }

	/**
	 * Get have all portions been delivered
	 * @return true if all portions have been delivered, false otherwise
	 */
	public boolean getAllPortionsBeenDelivered() { return (allPortionsDelivered); }

	/**
	 * Get has the order been completed value
	 * @return true if order has been completed, false otherwise
	 */
	public boolean getHasOrderBeenCompleted() { return (orderCompleted); }

	/**
	 * Get request type
	 * @return character that represents request type
	 */
	public char getRequestType() { return (requestType); }
	
	/**
	 * Get if there students at restaurant or not
	 * @return true if there aren't students at the restaurant, false otherwise
	 */
	public boolean getStudentsAtRestaurant() { return (studentsAtRestaurant); }
	
	/**
	 * Get id of the student whose request is being answered by the waiter
	 * @return id of the student
	 */
	public int getStudentBeingAnswered() { return (studentBeingAnswered); }
	
	/**
	 * Get the value of have all clients been served
	 * @return true if all clients have been served, false otherwise
	 */
	public boolean getAllClientsBeenServed() { return (allClientsBeenServed); }
	
	/**
	 * Get the value of everybody has chosen
	 * @return true if everybody has chosen their preference, false otherwise
	 */
	public boolean getEverybodyHasChosen() { return (everybodyHasChosen); }
	
	/**
	 * Get the value of everybody has finished eating
	 * @return true if everybody has eaten, false otherwise
	 */
	public boolean getHasEverybodyFinishedEating() { return (everybodyHasEaten); }
	
	/**
	 * Get the value of have all courses been eaten
	 * @return true if all courses have been eaten, false otherwise
	 */
	public boolean getAllCoursesEaten() { return (haveAllCoursesBeenEaten); }
	
	/**
	 * Get the value of should have arrived earlier
	 * @return value of shouldArrivedEarlier
	 */
	public boolean getArrivedEarlier() { return (shouldArrivedEarlier); }
	
	/**
	 * Get id of the first student to arrive
	 * @return id of the student
	 */
	public int getFirstToArrive() { return (firstToArrive); }
	
	/**
	 * Get id of the last student to eat
	 * @return id of the student
	 */
	public int getLastToEat() { return (lastToEat); }
	
	/**
	 * Get id of the last student to arrive
	 * @return id of the student
	 */
	public int getLastToArrive() { return (lastToArrive); }
	
	/**
	 * For a given message type, get the entity that called it (chef, waiter or student) 
	 * @param messageType type of the message
	 * @return 1 if called by chef, 2 if called bye waiter and 3 if called by student
	 */
	public int getEntitieFromMessageType(int messageType)
	{
		///FALTAM AQUI MENSAGENS
		switch(messageType)
		{
		// Chef messages
		case MessageType.REQWATTNWS: 		case MessageType.REPWATTNWS:
		case MessageType.REQSTRPR: 			case MessageType.REPSTRPR:
		case MessageType.REQPROCPREP: 		case MessageType.REPPROCPREP:
		case MessageType.REQHVPRTDLVD: 		case MessageType.REPHVPRTDLVD:
		case MessageType.REQHORDCOMPL: 		case MessageType.REPHORDCOMPL:
		case MessageType.REQCONTPREP: 		case MessageType.REPCONTPREP :
		case MessageType.REQHAVNEXPORRD: 	case MessageType.REPHAVNEXPORRD:
		case MessageType.REQCLEANUP: 		case MessageType.REPCLEANUP:
			return 1;
		// Waiter messages
		case MessageType.REQALRTWAIT: 		case MessageType.REPALRTWAIT:
		case MessageType.REQLOOKARND:		case MessageType.REPLOOKARND:
		case MessageType.REQRETURNTOBAR: 	case MessageType.REPRETURNTOBAR:
		case MessageType.REQCOLLPORT: 		case MessageType.REPCOLLPORT:
		case MessageType.REQPRPREBILL: 		case MessageType.REPPRPREBILL:
		case MessageType.REQSAYGDBYE: 		case MessageType.REPSAYGDBYE:
		case MessageType.REQSALUTCLI: 		case MessageType.REPSALUTCLI:
			return 2;
		// Student messages
		case MessageType.REQENTER: 			case MessageType.REPENTER:
		case MessageType.REQCALLWAI: 		case MessageType.REPCALLWAI:
		case MessageType.REQSIGWAI: 		case MessageType.REPSIGWAI:
		case MessageType.REQEXIT: 			case MessageType.REPEXIT:
			return 3;
		//Aditional Messages
		case MessageType.REQGETSTDBEIANSW:	case MessageType.REPGETSTDBEIANSW:
			return 4;
		default:
			return -1;
		}
	}


	/**
	 *  Printing the values of the internal fields.
	 *
	 *  It is used for debugging purposes.
	 *  @return string containing, in separate lines, the pair field name - field value
	 */

	@Override
	public String toString ()
	{
		return ("Message type = " + msgType +
				"\nChef State = " + chefState +
				"\nAll Portions Been Delivered = " + allPortionsDelivered + 
				"\nHas the Order been completed = " + orderCompleted);
	}
}

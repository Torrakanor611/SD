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
	 *     @param state chef or waiter state
	 */
	public Message (int type, int state)
	{
		msgType = type;
		int entitie = getEntitieFromMessageType(type);

		if(entitie == 1) //Chef message
			chefState = state;
		else if (entitie == 2) //Waiter Message
			waiterState = state;
		else if (entitie == 3) //Student message
			System.exit(2);
		else { 
			GenericIO.writelnString ("Message type = " + msgType + ": non-implemented instantiation!");
			System.exit (1);
		}

	}

	/**
	 * Message instantiation (form 3).
	 * 
	 * 	@param type message type
	 * 	@param bValue boolean that can have haveAllPortionsBeenDeliverd, hasOrderBeenCompleted value
	 */
	public Message(int type, boolean bValue)
	{
		msgType = type;
		if (msgType == MessageType.REPHVPRTDLVD)
			allPortionsDelivered = bValue;
		else if (msgType == MessageType.REQHORDCOMPL)
			orderCompleted = bValue;    	
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
	 *  Message instantiation (form 5).
	 *
	 *     @param type message type
	 *     @param id student being answered identification
	 *     @param state waiter state
	 *     @param dif differ from form 4
	 */

	public Message (int type, int studentIdBeingAnswered, int state, boolean dif)
	{
		msgType = type;
		int entity = getEntitieFromMessageType(type);
		if (entity != 2) {	// Not a Student entity Type Message
			GenericIO.writelnString ("Message type = " + msgType + ": non-implemented instantiation on Student!");
			System.exit (1);
		}
		waiterState = state;
		if ( studentIdBeingAnswered < 0 || studentIdBeingAnswered  >= ExecuteConst.N) {	// Not a valid Student id
			GenericIO.writelnString ("Invalid student id");
			System.exit (1);
		}
		this.studentIdBeingAnswered = studentIdBeingAnswered;
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
	 * For a given message type, get the entity that called it (chef, waiter or student) 
	 * @param messageType type of the message
	 * @return 1 if called by chef, 2 if called bye waiter and 3 if called by student
	 */
	public int getEntitieFromMessageType(int messageType)
	{
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

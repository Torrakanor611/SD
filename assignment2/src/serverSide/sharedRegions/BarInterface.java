package serverSide.sharedRegions;

import clientSide.entities.ChefStates;
import clientSide.entities.StudentStates;
import clientSide.entities.WaiterStates;
import commInfra.Message;
import commInfra.MessageException;
import commInfra.MessageType;
import serverSide.entities.BarClientProxy;
import serverSide.entities.KitchenClientProxy;

public class BarInterface {

	/**
	 * Reference to the Bar
	 */
	private final Bar bar;


	/**
	 * Instantiation of an interface to the Bar.
	 * 	@param bar reference to the kitchen
	 */
	public BarInterface(Bar bar)
	{
		this.bar = bar;
	}


	/**
	 * Processing of the incoming messages
	 * Validation, execution of the corresponding method and generation of the outgoing message.
	 * 
	 * 	@param inMessage service request
	 * 	@return service reply
	 * 	@throws MessageException if incoming message was not valid
	 */
	public Message processAndReply (Message inMessage) throws MessageException
	{
		//outGoing message
		Message outMessage = null;

		/* Validation of the incoming message */

		switch(inMessage.getMsgType())
		{
		// Chef Messages that require type and state verification
		case MessageType.REQWATTNWS: 		// Alert the Waiter Request
			if (inMessage.getChefState() < ChefStates.WAITING_FOR_AN_ORDER || inMessage.getChefState() > ChefStates.CLOSING_SERVICE)
				throw new MessageException ("Invalid Chef state!", inMessage);
			break;
			// Waiter Messages that require type and state verification
		case MessageType.REQLOOKARND: 		// Look around Request
		case MessageType.REQPRPREBILL: 		// Prepare the bill Request
		case MessageType.REQSAYGDBYE: 		// Say goodbye Request
			if (inMessage.getWaiterState() < WaiterStates.APRAISING_SITUATION || inMessage.getWaiterState() > WaiterStates.RECEIVING_PAYMENT)
				throw new MessageException("Inavlid Waiter state!", inMessage);
			break;
			// Student Messages that require type, state and id verification (done in Message Constructor)
		case MessageType.REQENTER:			// Enter Request
		case MessageType.REQCALLWAI:		// Call the waiter Request
		case MessageType.REQSIGWAI:			// Signal the waiter Request
		case MessageType.REQEXIT:			// exit Request
			if (inMessage.getStudentState() < StudentStates.GOING_TO_THE_RESTAURANT || inMessage.getStudentState() > StudentStates.GOING_HOME)
				throw new MessageException("Inavlid Student state!", inMessage);
			break;
		default:
			throw new MessageException ("Invalid message type!", inMessage);
		}

		/* Processing of the incoming message */

		switch(inMessage.getMsgType())
		{
		case MessageType.REQWATTNWS:
			((BarClientProxy) Thread.currentThread()).setChefState(inMessage.getChefState());
			bar.alertWaiter();
			outMessage = new Message(MessageType.REPWATTNWS, ((BarClientProxy) Thread.currentThread()).getChefState());
			break;
		case MessageType.REQLOOKARND:
			((BarClientProxy) Thread.currentThread()).setWaiterState(inMessage.getWaiterState());
			bar.lookAround();
			outMessage = new Message(MessageType.REPLOOKARND, ((BarClientProxy) Thread.currentThread()).getWaiterState());
			break;
		case MessageType.REQPRPREBILL:
			((BarClientProxy) Thread.currentThread()).setWaiterState(inMessage.getWaiterState());
			bar.preprareBill();
			outMessage = new Message(MessageType.REPPRPREBILL, ((BarClientProxy) Thread.currentThread()).getWaiterState());
			break;
		case MessageType.REQSAYGDBYE:
			((BarClientProxy) Thread.currentThread()).setWaiterState(inMessage.getWaiterState());
			bar.sayGoodbye();
			outMessage = new Message(MessageType.REPSAYGDBYE, ((BarClientProxy) Thread.currentThread()).getWaiterState());
			break;
		case MessageType.REQENTER:
			((BarClientProxy) Thread.currentThread()).setStudentState(inMessage.getStudentState());
			((BarClientProxy) Thread.currentThread()).setStudentId(inMessage.getStudentId());
			bar.enter();
			outMessage = new Message(MessageType.REPENTER, ((BarClientProxy) Thread.currentThread()).getStudentId(), ((BarClientProxy) Thread.currentThread()).getStudentState());
			break;
		case MessageType.REQCALLWAI:
			((BarClientProxy) Thread.currentThread()).setStudentState(inMessage.getStudentState());
			((BarClientProxy) Thread.currentThread()).setStudentId(inMessage.getStudentId());
			bar.callWaiter();
			outMessage = new Message(MessageType.REPCALLWAI, ((BarClientProxy) Thread.currentThread()).getStudentId(), ((BarClientProxy) Thread.currentThread()).getStudentState());
			break;
		case MessageType.REQSIGWAI:
			((BarClientProxy) Thread.currentThread()).setStudentState(inMessage.getStudentState());
			((BarClientProxy) Thread.currentThread()).setStudentId(inMessage.getStudentId());
			bar.signalWaiter();
			outMessage = new Message(MessageType.REPSIGWAI, ((BarClientProxy) Thread.currentThread()).getStudentId(), ((BarClientProxy) Thread.currentThread()).getStudentState());
			break;
		case MessageType.REQEXIT:
			((BarClientProxy) Thread.currentThread()).setStudentState(inMessage.getStudentState());
			((BarClientProxy) Thread.currentThread()).setStudentId(inMessage.getStudentId());
			bar.exit();
			outMessage = new Message(MessageType.REPEXIT, ((BarClientProxy) Thread.currentThread()).getStudentId(), ((BarClientProxy) Thread.currentThread()).getStudentState());
			break;
		}

		return (outMessage);
	}
}

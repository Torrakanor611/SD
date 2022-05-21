package serverSide.sharedRegions;

import clientSide.entities.StudentStates;
import clientSide.entities.WaiterStates;
import commInfra.Message;
import commInfra.MessageException;
import commInfra.MessageType;
import serverSide.entities.KitchenClientProxy;
import serverSide.entities.TableClientProxy;

public class TableInterface {

	/**
	 * Reference to the Bar
	 */
	private final Table tab;


	/**
	 * Instantiation of an interface to the Bar.
	 * 	@param bar reference to the kitchen
	 */
	public TableInterface(Table tab)
	{
		this.tab = tab;
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
		// outGoing message
		Message outMessage = null;

		/* Validation of the incoming message */

		switch(inMessage.getMsgType())
		{
		// Waiter Messages that require type and state verification
		case MessageType.REQSALUTCLI:			// Salute the clients
		case MessageType.REQRTRNBAR:			// Return to the bar
		case MessageType.REQGETPAD:				// Get the pad
		case MessageType.REQALLCLISERVED:		// Have all clients been served
		case MessageType.REQDELPOR:				// Deliver portion
		case MessageType.REQPRESBILL:			// Present the bill
			if (inMessage.getWaiterState() < WaiterStates.APRAISING_SITUATION || inMessage.getWaiterState() > WaiterStates.RECEIVING_PAYMENT)
				throw new MessageException("Inavlid Waiter state!", inMessage);
			break;
		// Student Messages that require type, state and id verification (done in Message Constructor)
		case MessageType.REQSEATTABLE:			// Seat at table
		case MessageType.REQRDMENU:				// Read menu
		case MessageType.REQPREPORDER:			// Prepare the order
		case MessageType.REQEVERYBDYCHO:		// Has everybody chosen
		case MessageType.REQADDUP1CHOI:			// Add up ones choices
		case MessageType.REQDESCRORDER:			// Describe order
		case MessageType.REQJOINTALK:			// Join the talk
		case MessageType.REQINFORMCOMP:			// Inform companion
		case MessageType.REQSRTEATING:			// Start eating
		case MessageType.REQENDEATING:			// End eating
		case MessageType.REQEVERYBDFINISHEAT:	// Has everybody finished eating
		case MessageType.REQHONBILL:			// Honour the bill
		case MessageType.REQALLCOURBEENEAT:		// Have all courses been eaten
		case MessageType.REQSHOULDARREARLY:		// Should have arrived earlier
			if (inMessage.getStudentState() < StudentStates.GOING_TO_THE_RESTAURANT || inMessage.getStudentState() > StudentStates.GOING_HOME)
				throw new MessageException("Inavlid Student state!", inMessage);
			break;
		default:
			throw new MessageException ("Invalid message type!", inMessage);
		}

		/* Processing of the incoming message */
		
		switch(inMessage.getMsgType())
		{
		case MessageType.REQSALUTCLI:
			((TableClientProxy) Thread.currentThread()).setWaiterState(inMessage.getWaiterState());
			((TableClientProxy) Thread.currentThread()).setStudentBeingAnswered(inMessage.getStudentIdBeingAnswered());
			tab.saluteClient(inMessage.getStudentIdBeingAnswered());
			outMessage = new Message(MessageType.REPSALUTCLI, ((TableClientProxy) Thread.currentThread()).getStudentBeingAnswered(), ((TableClientProxy) Thread.currentThread()).getWaiterState(), true);
			break;
		case MessageType.REQRTRNBAR:
			((TableClientProxy) Thread.currentThread()).setWaiterState(inMessage.getWaiterState());
			tab.returnBar();
			outMessage = new Message(MessageType.REPRTRNBAR, ((TableClientProxy) Thread.currentThread()).getWaiterState());
			break;
		case MessageType.REQGETPAD:
			((TableClientProxy) Thread.currentThread()).setWaiterState(inMessage.getWaiterState());
			tab.getThePad();
			outMessage = new Message(MessageType.REPGETPAD, ((TableClientProxy) Thread.currentThread()).getWaiterState());
			break;
		case MessageType.REQALLCLISERVED:
			((TableClientProxy) Thread.currentThread()).setWaiterState(inMessage.getWaiterState());
			tab.haveAllClientsBeenServed();
			outMessage = new Message(MessageType.REPALLCLISERVED, ((TableClientProxy) Thread.currentThread()).getWaiterState());
			break;
		case MessageType.REQDELPOR:
			((TableClientProxy) Thread.currentThread()).setWaiterState(inMessage.getWaiterState());
			tab.deliverPortion();
			outMessage = new Message(MessageType.REPDELPOR, ((TableClientProxy) Thread.currentThread()).getWaiterState());
			break;
		case MessageType.REQPRESBILL:
			((TableClientProxy) Thread.currentThread()).setWaiterState(inMessage.getWaiterState());
			tab.presentBill();
			outMessage = new Message(MessageType.REPPRESBILL, ((TableClientProxy) Thread.currentThread()).getWaiterState());
			break;
		case MessageType.REQSEATTABLE:
			((TableClientProxy) Thread.currentThread()).setStudentState(inMessage.getStudentState());
			((TableClientProxy) Thread.currentThread()).setStudentId(inMessage.getStudentId());
			tab.seatAtTable();
			outMessage = new Message(MessageType.REPSEATTABLE, ((TableClientProxy) Thread.currentThread()).getStudentId(), ((TableClientProxy) Thread.currentThread()).getStudentState());
			break;
		case MessageType.REQRDMENU:
			((TableClientProxy) Thread.currentThread()).setStudentState(inMessage.getStudentState());
			((TableClientProxy) Thread.currentThread()).setStudentId(inMessage.getStudentId());
			tab.readMenu();
			outMessage = new Message(MessageType.REPRDMENU, ((TableClientProxy) Thread.currentThread()).getStudentId(), ((TableClientProxy) Thread.currentThread()).getStudentState());
			break;
		case MessageType.REQPREPORDER:
			((TableClientProxy) Thread.currentThread()).setStudentState(inMessage.getStudentState());
			((TableClientProxy) Thread.currentThread()).setStudentId(inMessage.getStudentId());
			tab.prepareOrder();
			outMessage = new Message(MessageType.REPPREPORDER, ((TableClientProxy) Thread.currentThread()).getStudentId(), ((TableClientProxy) Thread.currentThread()).getStudentState());
			break;
		case MessageType.REQEVERYBDYCHO:
			((TableClientProxy) Thread.currentThread()).setStudentState(inMessage.getStudentState());
			((TableClientProxy) Thread.currentThread()).setStudentId(inMessage.getStudentId());
			tab.everybodyHasChosen();
			outMessage = new Message(MessageType.REPEVERYBDYCHO, ((TableClientProxy) Thread.currentThread()).getStudentId(), ((TableClientProxy) Thread.currentThread()).getStudentState());
			break;
		case MessageType.REQADDUP1CHOI:
			((TableClientProxy) Thread.currentThread()).setStudentState(inMessage.getStudentState());
			((TableClientProxy) Thread.currentThread()).setStudentId(inMessage.getStudentId());
			tab.addUpOnesChoices();
			outMessage = new Message(MessageType.REPADDUP1CHOI, ((TableClientProxy) Thread.currentThread()).getStudentId(), ((TableClientProxy) Thread.currentThread()).getStudentState());
			break;
		case MessageType.REQDESCRORDER:
			((TableClientProxy) Thread.currentThread()).setStudentState(inMessage.getStudentState());
			((TableClientProxy) Thread.currentThread()).setStudentId(inMessage.getStudentId());
			tab.describeOrder();
			outMessage = new Message(MessageType.REPDESCRORDER, ((TableClientProxy) Thread.currentThread()).getStudentId(), ((TableClientProxy) Thread.currentThread()).getStudentState());
			break;
		case MessageType.REQJOINTALK:
			((TableClientProxy) Thread.currentThread()).setStudentState(inMessage.getStudentState());
			((TableClientProxy) Thread.currentThread()).setStudentId(inMessage.getStudentId());
			tab.joinTalk();
			outMessage = new Message(MessageType.REPJOINTALK, ((TableClientProxy) Thread.currentThread()).getStudentId(), ((TableClientProxy) Thread.currentThread()).getStudentState());
			break;
		case MessageType.REQINFORMCOMP:
			((TableClientProxy) Thread.currentThread()).setStudentState(inMessage.getStudentState());
			((TableClientProxy) Thread.currentThread()).setStudentId(inMessage.getStudentId());
			tab.informCompanion();
			outMessage = new Message(MessageType.REPINFORMCOMP, ((TableClientProxy) Thread.currentThread()).getStudentId(), ((TableClientProxy) Thread.currentThread()).getStudentState());
			break;
		case MessageType.REQSRTEATING:
			((TableClientProxy) Thread.currentThread()).setStudentState(inMessage.getStudentState());
			((TableClientProxy) Thread.currentThread()).setStudentId(inMessage.getStudentId());
			tab.startEating();
			outMessage = new Message(MessageType.REPSRTEATING, ((TableClientProxy) Thread.currentThread()).getStudentId(), ((TableClientProxy) Thread.currentThread()).getStudentState());
			break;
		case MessageType.REQENDEATING:
			((TableClientProxy) Thread.currentThread()).setStudentState(inMessage.getStudentState());
			((TableClientProxy) Thread.currentThread()).setStudentId(inMessage.getStudentId());
			tab.endEating();
			outMessage = new Message(MessageType.REPENDEATING, ((TableClientProxy) Thread.currentThread()).getStudentId(), ((TableClientProxy) Thread.currentThread()).getStudentState());
			break;
		case MessageType.REQEVERYBDFINISHEAT:
			((TableClientProxy) Thread.currentThread()).setStudentState(inMessage.getStudentState());
			((TableClientProxy) Thread.currentThread()).setStudentId(inMessage.getStudentId());
			tab.hasEverybodyFinishedEating();
			outMessage = new Message(MessageType.REPEVERYBDFINISHEAT, ((TableClientProxy) Thread.currentThread()).getStudentId(), ((TableClientProxy) Thread.currentThread()).getStudentState());
			break;
		case MessageType.REQHONBILL:
			((TableClientProxy) Thread.currentThread()).setStudentState(inMessage.getStudentState());
			((TableClientProxy) Thread.currentThread()).setStudentId(inMessage.getStudentId());
			tab.honourBill();
			outMessage = new Message(MessageType.REPHONBILL, ((TableClientProxy) Thread.currentThread()).getStudentId(), ((TableClientProxy) Thread.currentThread()).getStudentState());
			break;
		case MessageType.REQALLCOURBEENEAT:
			((TableClientProxy) Thread.currentThread()).setStudentState(inMessage.getStudentState());
			((TableClientProxy) Thread.currentThread()).setStudentId(inMessage.getStudentId());
			tab.haveAllCoursesBeenEaten();
			outMessage = new Message(MessageType.REPALLCOURBEENEAT, ((TableClientProxy) Thread.currentThread()).getStudentId(), ((TableClientProxy) Thread.currentThread()).getStudentState());
			break;
		case MessageType.REQSHOULDARREARLY:
			((TableClientProxy) Thread.currentThread()).setStudentState(inMessage.getStudentState());
			((TableClientProxy) Thread.currentThread()).setStudentId(inMessage.getStudentId());
			tab.shouldHaveArrivedEarlier();
			outMessage = new Message(MessageType.REPSHOULDARREARLY, ((TableClientProxy) Thread.currentThread()).getStudentId(), ((TableClientProxy) Thread.currentThread()).getStudentState());
			break;
		}
		
		return (outMessage);
	}
}

package serverSide.sharedRegions;

import clientSide.entities.ChefStates;
import clientSide.entities.StudentStates;
import clientSide.entities.WaiterStates;
import commInfra.Message;
import commInfra.MessageException;
import commInfra.MessageType;
import serverSide.entities.GeneralReposClientProxy;

public class GeneralReposInterface {
	/**
	 * Reference to the General Repos
	 */
	private final GeneralRepos repos;

	/**
	 * Instantiation of an interface to the General Repos.
	 * 	@param repos reference to the General Repository
	 */
	public GeneralReposInterface(GeneralRepos repos) {
		this.repos = repos;
	}
	
	/**
	 * Processing of the incoming messages
	 * Validation, execution of the corresponding method and generation of the outgoing message.
	 * 
	 * 	@param inMessage service request
	 * 	@return service reply
	 * 	@throws MessageException if incoming message was not valid
	 */
	public Message processAndReply(Message inMessage) throws MessageException {
		//outGoing message
		Message outMessage = null;

		/* Validation of the incoming message */
		
		switch(inMessage.getMsgType())
		{
		// verify Chef state
		case MessageType.REQSETCHST:
			if (inMessage.getChefState() < ChefStates.WAITING_FOR_AN_ORDER || inMessage.getChefState() > ChefStates.CLOSING_SERVICE)
				throw new MessageException ("Invalid Chef state!", inMessage);
			break;
		// verify Waiter state
		case MessageType.REQSETWAIST:
			if (inMessage.getWaiterState() < WaiterStates.APRAISING_SITUATION || inMessage.getWaiterState() > WaiterStates.RECEIVING_PAYMENT)
				throw new MessageException("Invalid Waiter state!", inMessage);
			break;
		// verify Student state
		case MessageType.REQUPDTSTUST1:
		case MessageType.REQUPDTSTUST2:
			if (inMessage.getStudentState() < StudentStates.GOING_TO_THE_RESTAURANT || inMessage.getStudentState() > StudentStates.GOING_HOME)
				throw new MessageException("Invalid Student state!", inMessage);
			break;
		// verify only message type
		case MessageType.REQRPTLEGEND:
		case MessageType.REQSETNCOURSES:
		case MessageType.REQSETNPORTIONS:
		case MessageType.REQUPDSEATSTABLE:
		case MessageType.REQUPDSEATSTABLELV:
		case MessageType.REQGENERALREPOSHUT:
			break;
		default:
			throw new MessageException ("Invalid message type!", inMessage);
		}
		
		/* Processing of the incoming message */

		switch(inMessage.getMsgType())
		{
		case MessageType.REQRPTLEGEND:
			repos.reportLegend();
			outMessage = new Message(MessageType.REPRPTLEGEND);
			break;
		case MessageType.REQSETCHST:
			((GeneralReposClientProxy) Thread.currentThread()).setChefState(inMessage.getChefState());
			repos.setChefState(((GeneralReposClientProxy) Thread.currentThread()).getChefState());
			outMessage = new Message(MessageType.REPSETCHST);
			break;
		case MessageType.REQSETWAIST:
			((GeneralReposClientProxy) Thread.currentThread()).setWaiterState(inMessage.getWaiterState());
			repos.setWaiterState(((GeneralReposClientProxy) Thread.currentThread()).getWaiterState());
			outMessage = new Message(MessageType.REPSETWAIST);
			break;
		case MessageType.REQUPDTSTUST1:
		case MessageType.REQUPDTSTUST2:
			((GeneralReposClientProxy) Thread.currentThread()).setStudentState(inMessage.getStudentState());
			((GeneralReposClientProxy) Thread.currentThread()).setStudentId(inMessage.getStudentId());
			if (inMessage.getMsgType() == MessageType.REQUPDTSTUST1) {
				repos.updateStudentState(((GeneralReposClientProxy) Thread.currentThread()).getStudentId(), ((GeneralReposClientProxy) Thread.currentThread()).getStudentState());
				outMessage = new Message(MessageType.REPUPDTSTUST1);
				break;
			}
			repos.updateStudentState(((GeneralReposClientProxy) Thread.currentThread()).getStudentId(), ((GeneralReposClientProxy) Thread.currentThread()).getStudentState(), inMessage.getHold());
			outMessage = new Message(MessageType.REPUPDTSTUST2);
			break;
		case MessageType.REQSETNCOURSES:
			((GeneralReposClientProxy) Thread.currentThread()).setValue(inMessage.getNCourses());
			repos.setnCourses(((GeneralReposClientProxy) Thread.currentThread()).getValue());
			outMessage = new Message(MessageType.REPSETNCOURSES);
			break;
		case MessageType.REQSETNPORTIONS:
			((GeneralReposClientProxy) Thread.currentThread()).setValue(inMessage.getNPortions());
			repos.setnPortions(((GeneralReposClientProxy) Thread.currentThread()).getValue());
			outMessage = new Message(MessageType.REPSETNPORTIONS);
			break;
		case MessageType.REQUPDSEATSTABLE:
			((GeneralReposClientProxy) Thread.currentThread()).setStudentId(inMessage.getStudentId());
			((GeneralReposClientProxy) Thread.currentThread()).setValue(inMessage.getSeatAtTable());
			repos.updateSeatsAtTable(((GeneralReposClientProxy) Thread.currentThread()).getValue(), ((GeneralReposClientProxy) Thread.currentThread()).getStudentId());
			outMessage = new Message(MessageType.REPUPDSEATSTABLE);
			break;
		case MessageType.REQUPDSEATSTABLELV:
			((GeneralReposClientProxy) Thread.currentThread()).setStudentId(inMessage.getStudentId());
			repos.updateSeatsAtTable(((GeneralReposClientProxy) Thread.currentThread()).getStudentId(), -1);
			outMessage = new Message(MessageType.REPUPDSEATSTABLELV);
			break;
		case MessageType.REQGENERALREPOSHUT:
			repos.shutdown();
			outMessage = new Message(MessageType.REPGENERALREPOSHUT);
			break;
		}
		return (outMessage);
	}

}

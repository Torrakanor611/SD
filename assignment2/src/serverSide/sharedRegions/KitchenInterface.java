package serverSide.sharedRegions;

import serverSide.sharedRegions.*;
import serverSide.entities.*;
import clientSide.entities.*;
import commInfra.*;

public class KitchenInterface {
	
	/**
	 * Reference to the kitchen
	 */
	private final Kitchen kit;
	
	
	/**
	 * Instantiation of an interface to the kitchen.
	 * 	@param kit reference to the kitchen
	 */
	public KitchenInterface(Kitchen kit)
	{
		this.kit = kit;
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
			case MessageType.REQWATTNWS: //Watching the news request
				if (inMessage.getBarbState() != ChefStates.WAITING_FOR_AN_ORDER)
					throw new MessageException ("Invalid Chef state!", inMessage);
				break;
			default:
				throw new MessageException ("Invalid message type!", inMessage);
		}
		
		/* Processing */
		switch(inMessage.getMsgType())
		{
			case MessageType.REQWATTNWS: //Watching the news request
				((KitchenClientProxy) Thread.currentThread()).setChefState(inMessage.getChefState());
		
		}
		
		return (outMessage);
	}

}

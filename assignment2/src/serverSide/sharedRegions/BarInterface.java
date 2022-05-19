package serverSide.sharedRegions;

import clientSide.entities.ChefStates;
import commInfra.Message;
import commInfra.MessageException;
import commInfra.MessageType;

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
			case MessageType.REQWATTNWS: // Alert the Waiter Request
				if (inMessage.getChefState() > ChefStates.WAITING_FOR_AN_ORDER && inMessage.getChefState() < ChefStates.CLOSING_SERVICE)
					throw new MessageException ("Invalid Chef state!", inMessage);
				break;
			case MessageType.REQLOOKARND: // look around Request
				if (inMessage.get)
		}
	}
}

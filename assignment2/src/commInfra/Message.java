package commInfra;

import java.io.*;
import genclass.GenericIO;

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
    * Students State
    */
   private int [] studentState;
   
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
    *     @param state chef or waiter or student state
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
   *  Message instantiation (form 2).
   *
   *     @param type message type
   *     @param id barber / customer identification
   *     @param state barber / customer state
   */

   public Message (int type, int id, int state)
   {
      msgType = type;
      if ((msgType == MessageType.STBST) || (msgType == MessageType.CALLCUST) || (msgType == MessageType.RPAYDONE))
         { barbId= id;
           barbState = state;
         }
         else if ((msgType == MessageType.STCST) || (msgType == MessageType.REQCUTH) || (msgType == MessageType.CUTHDONE) ||
                  (msgType == MessageType.BSHOPF))
                 { custId= id;
                   custState = state;
                 }
                 else { GenericIO.writelnString ("Message type = " + msgType + ": non-implemented instantiation!");
                        System.exit (1);
                      }
   }


  /**
   *  Message instantiation (form 5).
   *
   *     @param type message type
   *     @param barbId barber identification
   *     @param barbState barber state
   *     @param custId customer identification
   */

   public Message (int type, int barbId, int barbState, int custId)
   {
      msgType = type;
      this.barbId= barbId;
      this.barbState = barbState;
      this.custId= custId;
   }

  /**
   *  Message instantiation (form 6).
   *
   *     @param type message type
   *     @param barbId barber identification
   *     @param barbState barber state
   *     @param custId customer identification
   *     @param custState customer state
   */

   public Message (int type, int barbId, int barbState, int custId, int custState)
   {
      msgType = type;
      this.barbId= barbId;
      this.barbState = barbState;
      this.custId= custId;
      this.custState = custState;
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
   *  Getting end of operations flag (barber).
   *
   *     @return end of operations flag
   */

   public boolean getEndOp ()
   {
      return (endOp);
   }

  /**
   *  Getting name of logging file.
   *
   *     @return name of the logging file
   */

   public String getLogFName ()
   {
      return (fName);
   }

  /**
   *  Getting the number of iterations of the customer life cycle.
   *
   *     @return number of iterations of the customer life cycle
   */

   public int getNIter ()
   {
	   return (nIter);
   }

   /**
    * For a given message type, get the entity that called it (chef, waiter or student) 
    * @param messageType type of the message
    * @return 1 if called by chef, 2 if called bye waiter and 3 if called by student
    */
   public int getEntitieFromMessageType(int messageType)
   {
	   switch(messageType)
	   {
         //Chef messages
         case MessageType.REQWATTNWS: case MessageType.REPWATTNWS:
         case MessageType.REQSTRPR: case MessageType.REPSTRPR:
         case MessageType.REQPROCPREP: case MessageType.REPPROCPREP:
         case MessageType.REQHVPRTDLVD: case MessageType.REPHVPRTDLVD:
         case MessageType.REQHORDCOMPL: case MessageType.REPHORDCOMPL:
         case MessageType.REQCONTPREP: case MessageType.REPCONTPREP :
         case MessageType.REQHAVNEXPORRD: case MessageType.REPHAVNEXPORRD:
         case MessageType.REQCLEANUP: case MessageType.REPCLEANUP:
            return 1;
         //Waiter messages
         case MessageType.REQALRTWAIT: case MessageType.REPALRTWAIT:
	     case MessageType.REQLOOKARND:	case MessageType.REPLOOKARND:
	     case MessageType.REQRETURNTOBAR: case MessageType.REPRETURNTOBAR:
	     case MessageType.REQCOLLPORT: case MessageType.REPCOLLPORT:
            return 2;
         //Student messages
        default:
        	return -1;
	   }
   }
   
   
  /**
   *  Printing the values of the internal fields.
   *
   *  It is used for debugging purposes.
   *
   *     @return string containing, in separate lines, the pair field name - field value
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

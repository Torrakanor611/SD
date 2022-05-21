package commInfra;

/**
 *   Type of the exchanged messages.
 *the
 *   Implementation of a client-server model of type 2 (server replication).
 *   Communication is based on a communication channel under the TCP protocol.
 */

public class MessageType
{
	//---------KITCHEN INPUT/OUTPUT MESSAGES---------//
	
	/**
	 * Chef - Operation watching the news (request)
	 */
	public static final int REQWATTNWS = 1;
	/**
	 * Chef - Operation watching the news (reply)
	 */
	public static final int REPWATTNWS = 2;
	/**
	 * Chef - Operation start preparation of a course (request)
	 */
	public static final int REQSTRPR = 3;
	/**
	 * Chef - Operation start preparation of a course (reply)
	 */
	public static final int REPSTRPR = 4;
	/**
	 * Chef - Operation proceed to preparation (request)
	 */
	public static final int REQPROCPREP = 5;
	/**
	 * Chef - Operation proceed to preparation (reply)
	 */
	public static final int REPPROCPREP = 6;
	/**
	 * Chef - Operation have all portions been delivered (request)
	 */
	public static final int REQHVPRTDLVD = 7;
	/**
	 * Chef - Operation have all portions been delivered (reply)
	 */
	public static final int REPHVPRTDLVD = 8;
	/**
	 * Chef - Operation has the order been completed (request)
	 */
	public static final int REQHORDCOMPL = 9;
	/**
	 * Chef - Operation has the order been completed (reply)
	 */
	public static final int REPHORDCOMPL = 10;
	/**
	 * Chef - Operation continue to preparation (request)
	 */
	public static final int REQCONTPREP = 11;
	/**
	 * Chef - Operation continue to preparation (reply)
	 */
	public static final int REPCONTPREP = 12;
	/**
	 * Chef - Operation have next portion ready (request)
	 */
	public static final int REQHAVNEXPORRD = 13;
	/**
	 * Chef - Operation have next portion ready (reply)
	 */
	public static final int REPHAVNEXPORRD = 14;
	/**
	 * Chef - Operation clean up (request)
	 */
	public static final int REQCLEANUP = 15;
	/**
	 * Chef - Operation clean up (reply)
	 */
	public static final int REPCLEANUP = 16;
	/**
	 * Waiter - Operation hand note to chef (request)
	 */
	public static final int REQHNDNOTCHEF = 17;
	/**
	 * Waiter - Operation hand note to chef (reply)
	 */
	public static final int REPHNDNOTCHEF = 18;
	/**
	 * Waiter - Operation return to bar (request)
	 */
	public static final int REQRETURNTOBAR = 19;
	/**
	 * Waiter - Operation return to bar (reply)
	 */
	public static final int REPRETURNTOBAR = 20;
	/**
	 * Waiter - Operation collect portion (request)
	 */
	public static final int REQCOLLPORT = 21;
	/**
	 * Waiter - Operation collect portion (reply)
	 */
	public static final int REPCOLLPORT = 22;
	
	
	
	
	//---------BAR INPUT/OUTPUT MESSAGES---------//
	
	/**
	 * Operation watching the news (request)
	 */
	public static final int REQALRTWAIT= 30;
	
	/**
	 * Operation watching the news (reply)
	 */
	public static final int REPALRTWAIT = 31;
	
	/**
	 * Operation look around (request)
	 */
	public static final int REQLOOKARND= 32;
	
	/**
	 * Operation look around (reply)
	 */
	public static final int REPLOOKARND = 33;
	
	
}

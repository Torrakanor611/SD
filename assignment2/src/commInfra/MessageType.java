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
	 * Operation watching the news (request)
	 */
	public static final int REQWATTNWS = 1;
	/**
	 * Operation watching the news (reply)
	 */
	public static final int REPWATTNWS = 2;
	/**
	 * Operation start preparation of a course (request)
	 */
	public static final int REQSTRPR = 3;
	/**
	 * Operation start preparation of a course (reply)
	 */
	public static final int REPSTRPR = 4;
	/**
	 * Operation proceed to presentation (request)
	 */
	public static final int REQPRCPRST = 5;
	/**
	 * Operation proceed to presentation (reply)
	 */
	public static final int REPPRCPRST = 6;
	/**
	 * Operation have all portions been delivered (request)
	 */
	public static final int REQHVPRTDLVD = 7;
	/**
	 * Operation have all portions been delivered (reply)
	 */
	public static final int REPHVPRTDLVD = 8;
	
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

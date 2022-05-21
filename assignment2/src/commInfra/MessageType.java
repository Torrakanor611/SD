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
	public static final int REQALRTWAIT = 30;

	/**
	 * Operation watching the news (reply)
	 */
	public static final int REPALRTWAIT = 31;

	/**
	 * Operation look around (request)
	 */
	public static final int REQLOOKARND = 32;

	/**
	 * Operation look around (reply)
	 */
	public static final int REPLOOKARND = 33;

	/**
	 * Operation prepare Bill (request)
	 */
	public static final int REQPRPREBILL = 34;

	/**
	 * Operation prepare Bill (reply)
	 */
	public static final int REPPRPREBILL = 35;

	/**
	 * Operation say goodbye (request)
	 */
	public static final int REQSAYGDBYE = 36;

	/**
	 * Operation say goodbye (reply)
	 */
	public static final int REPSAYGDBYE = 37;

	/**
	 * Operation enter (request)
	 */
	public static final int REQENTER = 38;

	/**
	 * Operation enter (reply)
	 */
	public static final int REPENTER = 39;

	/**
	 * Operation call waiter (request)
	 */
	public static final int REQCALLWAI = 40;

	/**
	 * Operation call waiter (reply)
	 */
	public static final int REPCALLWAI = 41;

	/**
	 * Operation signal waiter (request)
	 */
	public static final int REQSIGWAI = 42;

	/**
	 * Operation signal waiter (reply)
	 */
	public static final int REPSIGWAI = 43;

	/**
	 * Operation exit (request)
	 */
	public static final int REQEXIT = 44;

	/**
	 * Operation exit (reply)
	 */
	public static final int REPEXIT = 45;


	//---------TABLE INPUT/OUTPUT MESSAGES---------//

	/**
	 * Operation salute the client (request)
	 */
	public static final int REQSALUTCLI = 60;

	/**
	 * Operation salute the client (reply)
	 */
	public static final int REPSALUTCLI = 61;

	/**
	 * Operation return to the bar (request)
	 */
	public static final int REQRTRNBAR = 62;

	/**
	 * Operation return to the bar (reply)
	 */
	public static final int REPRTRNBAR = 63;

	/**
	 * Operation get the pad (request)
	 */
	public static final int REQGETPAD = 64;

	/**
	 * Operation get the pad (reply)
	 */
	public static final int REPGETPAD = 65;

	/**
	 * Operation have all clients been served (request)
	 */
	public static final int REQALLCLISERVED = 66;

	/**
	 * Operation have all clients been served (reply)
	 */
	public static final int REPALLCLISERVED = 67;

	/**
	 * Operation deliver portion (request)
	 */
	public static final int REQDELPOR = 68;

	/**
	 * Operation deliver portion (reply)
	 */
	public static final int REPDELPOR = 69;

	/**
	 * Operation present the bill (request)
	 */
	public static final int REQPRESBILL = 70;

	/**
	 * Operation present the bill (reply)
	 */
	public static final int REPPRESBILL = 71;

	/**
	 * Operation seat at table (request)
	 */
	public static final int REQSEATTABLE = 72;

	/**
	 * Operation seat at table (reply)
	 */
	public static final int REPSEATTABLE = 73;

	/**
	 * Operation read the menu (request)
	 */
	public static final int REQRDMENU = 74;

	/**
	 * Operation read the menu (reply)
	 */
	public static final int REPRDMENU = 75;

	/**
	 * Operation present the bill (request)
	 */
	public static final int REQPREPORDER = 76;

	/**
	 * Operation present the bill (reply)
	 */
	public static final int REPPREPORDER = 77;

	/**
	 * Operation every body has chosen (request)
	 */
	public static final int REQEVERYBDYCHO = 78;

	/**
	 * Operation every body has chosen (reply)
	 */
	public static final int REPEVERYBDYCHO = 79;

	/**
	 * Operation add up one choices (request)
	 */
	public static final int REQADDUP1CHOI = 80;

	/**
	 * Operation add up ones choices (reply)
	 */
	public static final int REPADDUP1CHOI = 81;

	/**
	 * Operation describe order (request)
	 */
	public static final int REQDESCRORDER = 82;

	/**
	 * Operation describe order (reply)
	 */
	public static final int REPDESCORDER = 83;

	/**
	 * Operation join talk (request)
	 */
	public static final int REQJOINTALK = 84;

	/**
	 * Operation join talk (reply)
	 */
	public static final int REPJOINTALK = 85;

	/**
	 * Operation inform companion (request)
	 */
	public static final int REQINFORMCOMP = 86;

	/**
	 * Operation inform companion (reply)
	 */
	public static final int REPINFORMCOMP = 87;

	/**
	 * Operation start eating (request)
	 */
	public static final int REQSRTEATING = 88;

	/**
	 * Operation start eating (reply)
	 */
	public static final int REPSRTEATING = 89;

	/**
	 * Operation end eating (request)
	 */
	public static final int REQENDEATING = 90;

	/**
	 * Operation end eating (reply)
	 */
	public static final int REPENDEATING = 91;

	/**
	 * Operation has every body finished eating (request)
	 */
	public static final int REQEVERYBDFINISHEAT = 92;

	/**
	 * Operation has every body finished eating (reply)
	 */
	public static final int REPEVERYBDFINISHEAT = 93;

	/**
	 * Operation honour bill (request)
	 */
	public static final int REQHONBILL = 94;

	/**
	 * Operation honour bill (reply)
	 */
	public static final int REPHONBILL = 95;

	/**
	 * Operation have all courses been eaten (request)
	 */
	public static final int REQALLCOURBEENEAT = 96;

	/**
	 * Operation have all courses been eaten (reply)
	 */
	public static final int REPALLCOURBEENEAT = 97;

	/**
	 * Operation should have arrived earlier (request)
	 */
	public static final int REQSHOULDARREARLY = 98;

	/**
	 * Operation should have arrived earlier (reply)
	 */
	public static final int REPSHOULDARREARLY = 99;
}

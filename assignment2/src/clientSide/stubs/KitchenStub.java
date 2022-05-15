package clientSide.stubs;

/**
 *  Stub to the kitchen
 *
 *    It instantiates a remote reference to the barber shop.
 *    Implementation of a client-server model of type 2 (server replication).
 *    Communication is based on a communication channel under the TCP protocol.
 */
public class KitchenStub {
	/**
	 * Name of the platform where is located the kitchen server
	 */
	private String serverHostName;
	/**
	 * Port number for listening to service requests
	 */
	private int serverPortNumb;
	
	
	/**
	 * Instantiation of a stub to the kitchen 
	 * 
	 * @param serverHostName name of the platform where is located the kitchen server
	 * @param serverPortNumb port number for listening to service requests
	 */
	public KitchenStub(String serverHostName, int serverPortNumb)
	{
		this.serverHostName = serverHostName;
		this.serverPortNumb = serverPortNumb;
	}
	
}

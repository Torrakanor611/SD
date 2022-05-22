package clientSide.main;

import clientSide.stubs.*;
import clientSide.entities.*;

/**
 *  Client side of the Restaurant problem (chef).
 *
 *	Implementation of a client-server model of type 2 (server replication).
 *	Communication is based on a communication channel under the TCP protocol.
 */
public class ClientChef {
	
	/**
	 *  Main method.
	 *
	 *    @param args runtime arguments
	 *        args[0] - name of the platform where is located the Kitchen server
	 *        args[1] - port nunber for listening to service requests
     *		  args[2] - name of the platform where is located the Bar server
	 *        args[3] - port nunber for listening to service requests
	 *        args[4] - name of the platform where is located the General Repository server
	 *        args[5] - port number for listening to service requests
	 */
	public static void main(String[] args) {
		
		Chef chef;				//Chef thread
		KitchenStub kitStub;	//remote reference to the kitchen stub
		BarStub barStub;		//remote reference to the bar stub
		
		//Name of the platforms where kitchen and bar servers are located
		String kitServerHostName, barServerHostName;
		//Port numbers for listening to service requests
		int kitServerPortNumb, barServerPortNumb;
		
		/* Getting problem runtime parameters */
		
		chef = new Chef("chef",kitStub, barStub); 
	}

}

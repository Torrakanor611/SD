package interfaces;

import java.rmi.*;

/**
 *   Operational interface of a remote object of type GeneralRepos.
 *
 *     It provides the functionality to access the General Repository of Information.
 */
public interface GeneralReposInterface extends Remote
{
	/**
	 * Operation server shutdown.
	 * @throws RemoteException if either the invocation of the remote method, or the communication with the registry service fails
	 */
	public void shutdown () throws RemoteException;	
}

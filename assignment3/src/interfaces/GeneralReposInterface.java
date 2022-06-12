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
	 * Write in the logging file the new chef state
	 * @param value chef state to set
	 * @throws RemoteException if either the invocation of the remote method, or the communication with the registry service fails
	 */
	public void setChefState(int value) throws RemoteException;

	/**
	 * Write in the logging file the new waiter state
	 * @param value waiter state to set
	 * @throws RemoteException if either the invocation of the remote method, or the communication with the registry service fails
	 */
	public void setWaiterState(int value) throws RemoteException;

	/**
	 * Write in the logging file the updated student state
	 * @param id student id
	 * @param value student state to set
	 * @throws RemoteException if either the invocation of the remote method, or the communication with the registry service fails
	 */
	public void updateStudentState(int id, int value) throws RemoteException;
	
	/**
	 * Update student state
	 * @param id student id
	 * @param value student state to set
	 * @param hold specifies if prints line of report status
	 * @throws RemoteException if either the invocation of the remote method, or the communication with the registry service fails
	 */
	public void updateStudentState(int id, int value, boolean hold) throws RemoteException;

	/**
	 * Set variable nCourses and report status in the logging file
	 * @param value nCourses value to set
	 * @throws RemoteException if either the invocation of the remote method, or the communication with the registry service fails
	 */
	public void setnCourses(int value) throws RemoteException;

	/**
	 * Write the portion value in the logging file
	 * @param value nPortions value to set
	 * @throws RemoteException if either the invocation of the remote method, or the communication with the registry service fails
	 */
	public void setnPortions(int value) throws RemoteException;

	/**
	 * Write to the logging file the updated seats values at the table 
	 * 
	 * @param seat seat at the table
	 * @param id student id to sit
	 * @throws RemoteException if either the invocation of the remote method, or the communication with the registry service fails
	 */
	public void updateSeatsAtTable(int seat, int id) throws RemoteException;

	/**
	 * Update the leaving of a student in the seats of the table
	 * @param id student id to leave table
	 * @throws RemoteException if either the invocation of the remote method, or the communication with the registry service fails
	 */
	public void updateSeatsAtLeaving(int id) throws RemoteException;
	
	/**
	 * Operation server shutdown.
	 * @throws RemoteException if either the invocation of the remote method, or the communication with the registry service fails
	 */
	public void shutdown () throws RemoteException;	
}

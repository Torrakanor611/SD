package serverSide.entities;

import clientSide.entities.*;

public class TableClientProxy extends Thread implements WaiterCloning, StudentCloning {
	
	/**
	 * State of the waiter
	 */
	private int waiterState;
	
	/**
	 * State of the student
	 */
	private int studentState;
	
	/**
	 * Id of the student
	 */
	private int studentId;
	
	
	
	
	
	/**
	 * Set student Id
	 * 	@param id id of the student
	 */
	public void setStudentId(int id) {studentId = id; }

	/**
	 * Get student id
	 * 	@return id of the student
	 */
	public int getStudentId() {	return studentId; }

	/**
	 * Set student state
	 * 	@param state state of the student
	 */
	public void setStudentState(int state) { studentState = state; }

	/**
	 * Get student state
	 * 	@return student state
	 */
	public int getStudentState() { return studentState; }

	/**
	 * Set waiter state
	 * 	@param state state of the waiter
	 */
	public void setWaiterState(int state) {	waiterState = state; }

	/**
	 * Get waiter state
	 *	@return state of the waiter
	 */
	public int getWaiterState() { return studentState;	}

}

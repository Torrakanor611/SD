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
	 * Id of the student whose request the waiter is taking care of
	 */
	private int studentBeingAnswered;
	
	
	
	
	
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
	
	/**
	 * Set studentBeingAnswered Id
	 * 	@param id studentBeingAnswered ID
	 */
	public void setStudentBeingAnswered(int id) {	studentBeingAnswered = id; }

	/**
	 * Get studentBeingAnswered Id
	 *	@return id studentBeingAnswered
	 */
	public int getStudentBeingAnswered() { return studentBeingAnswered;	}

}

package serverSide.entities;

import clientSide.entities.*;

public class BarClientProxy extends Thread implements ChefCloning, WaiterCloning, StudentCloning{
	
	/**
	 * Chef State
	 */
	private int chefState;
	
	/**
	 * Waiter State
	 */
	private int waiterState;
	
	/**
	 * Student state
	 */
	private int studentState;
	
	/**
	 * Student id
	 */
	private int studentId;

	
	
	/**
	 * Set waiter state
	 * 	@param state state of the waiter
	 */
	public void setWaiterState(int state) { waiterState = state; }

	/**
	 * Get waiter state
	 * 	@return state of the waiter
	 */
	public int getWaiterState() { return waiterState; }

	/**
	 * Set chef state
	 * 	@param state chef state
	 */
	public void setChefState(int state) { chefState = state; }

	/**
	 * Get chef state
	 * 	@return state of the chef
	 */
	public int getChefState() {	return chefState; }
	
	
	/**
	 * Set student id
	 * 	@param id  id of the student
	 */
	public void setStudentId(int id) { studentId = id; }

	/**
	 * Get Student Id
	 * 	@return student id
	 */
	public int getStudentId() {	return studentId; }

	/**
	 * Set student state
	 * 	@param state of the student
	 */
	public void setStudentState(int state) {  studentState = state; }

	/**
	 * Get student state
	 * 	@return student state
	 */
	public int getStudentState() { return studentState; }
}

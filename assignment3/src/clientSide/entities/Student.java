package clientSide.entities;

import interfaces.*;

/**
 *    Barber thread.
 *
 *      It simulates the barber life cycle.
 *      Implementation of a client-server model of type 2 (server replication).
 *      Communication is based on remote calls under Java RMI.
 */
public class Student extends Thread{
	
	/**
	 * Student identification
	 */
	private int studentId;
	
	/**
	 * Student state
	 */
	private int studentState;
	
	/**
	 * Reference to the stub of the bar
	 */
	
	private final BarInterface barStub;
	
	/**
	 * Reference to the stub of the table
	 */
	private final TableInterface tabStub;
	
	
	
	public Student(String name, int studentId, BarInterface barStub, TableInterface tabStub)
	{
		super(name);
		studentState = StudentStates.GOING_TO_THE_RESTAURANT;
		this.studentId = studentId;
		this.barStub = barStub;
		this.tabStub = tabStub;
	}

}



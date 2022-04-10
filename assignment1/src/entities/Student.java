package entities;

// import sharedRegions.BarberShop;

/**
 *   Student thread.
 *
 *   It simulates the student life cycle.
 */

public class Student extends Thread{
	
	/**
	 * 	Student identification
	 */
	private int studentId;
	
	/**
	 * 	Student state
	 */
	private int studentState;
	
	
	/**
	 * 	@param student id
	 */
	
	public void setStudentId(int studentId) {
		this.studentId = studentId;
	}
	
	/**
	 * 	@return student id
	 */
	
	public int getStudentId() {
		return studentId;
	}

	/**
	 * 	@param student state
	 */
	
	public void setStudentState(int studentState) {
		this.studentState = studentState;
	}
	
	/**
	 * 	@return student state
	 */

	public int getStudentState() {
		return studentState;
	}

	/**
	 * Instatiation of a student thread
	 * 
	 * @param studentId student id
	 * @param studentState student state
	 */
	public Student(int studentId, int studentState) {
		super();
		this.studentId = studentId;
		this.studentState = studentState;
	}

	/**
	 *	Life cycle of the student
	 */

	@Override
	public void run ()
	{
		//
	}
	
	private void walkABit()
	{
		try
		{ sleep ((long) (1 + 100 * Math.random ()));
		}
		catch (InterruptedException e) {}
	}
	
}

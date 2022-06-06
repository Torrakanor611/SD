package serverSide.objects;

import java.rmi.RemoteException;

import interfaces.GeneralReposInterface;
import interfaces.ReturnBoolean;
import interfaces.TableInterface;

public class Table implements TableInterface{

	public Table(GeneralReposInterface reposStub) {
		// TODO Auto-generated constructor stub
	}

	@Override
	public int getFirstToArrive() throws RemoteException {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int getLastToEat() throws RemoteException {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void setFirstToArrive(int firstToArrive) throws RemoteException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setLastToArrive(int lastToArrive) throws RemoteException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public int saluteClient(int studentIdBeingAnswered) throws RemoteException {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int returnBar() throws RemoteException {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int getThePad() throws RemoteException {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public boolean haveAllClientsBeenServed() throws RemoteException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void deliverPortion() throws RemoteException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public int presentBill() throws RemoteException {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void seatAtTable() throws RemoteException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public int readMenu(int studentId) throws RemoteException {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int prepareOrder() throws RemoteException {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public boolean everybodyHasChosen() throws RemoteException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void addUpOnesChoices() throws RemoteException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void describeOrder() throws RemoteException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public int joinTalk() throws RemoteException {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int informCompanion(int studentId) throws RemoteException {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int startEating(int studentId) throws RemoteException {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int endEating(int studentId) throws RemoteException {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public boolean hasEverybodyFinishedEating(int studentId) throws RemoteException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void honourBill() throws RemoteException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean haveAllCoursesBeenEaten() throws RemoteException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public ReturnBoolean shouldHaveArrivedEarlier(int studentId) throws RemoteException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void shutdown() throws RemoteException {
		// TODO Auto-generated method stub
		
	}

}

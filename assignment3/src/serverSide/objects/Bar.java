package serverSide.objects;

import java.rmi.Remote;
import java.rmi.RemoteException;

import interfaces.*;

public class Bar implements BarInterface {

	public Bar(GeneralReposInterface reposStub) {
		// TODO Auto-generated constructor stub
	}

	@Override
	public int getStudentBeingAnswered() throws RemoteException {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int alertWaiter() throws RemoteException {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public char lookAround() throws RemoteException {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int prepareBill() throws RemoteException {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public boolean sayGoodbye() throws RemoteException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public int enter(int studentId) throws RemoteException {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void callWaiter(int studentId) throws RemoteException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void signalWaiter(int studentId) throws RemoteException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public int exit(int studentId) throws RemoteException {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void shutdown() throws RemoteException {
		// TODO Auto-generated method stub
		
	}

}

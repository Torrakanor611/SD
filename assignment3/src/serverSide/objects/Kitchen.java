package serverSide.objects;

import java.rmi.RemoteException;

import interfaces.GeneralReposInterface;
import interfaces.KitchenInterface;

public class Kitchen implements KitchenInterface{

	public Kitchen(GeneralReposInterface reposStub) {
		// TODO Auto-generated constructor stub
	}

	@Override
	public int watchTheNews() throws RemoteException {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int startPreparation() throws RemoteException {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int proceedPreparation() throws RemoteException {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public boolean haveAllPortionsBeenDelivered() throws RemoteException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean hasOrderBeenCompleted() throws RemoteException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public int continuePreparation() throws RemoteException {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int haveNextPortionReady() throws RemoteException {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int cleanUp() throws RemoteException {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int handNoteToChef() throws RemoteException {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int returnToBar() throws RemoteException {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int collectPortion() throws RemoteException {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void shutdown() throws RemoteException {
		// TODO Auto-generated method stub
		
	}

}

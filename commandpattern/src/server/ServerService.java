package server;

import java.rmi.RemoteException;

import remoteService.DoSomethingService;
import server.commands.Command;

public class ServerService implements DoSomethingService {

	@Override
	public <T> T executeTask(Command c) throws RemoteException {
		return (T) c.execute();

	}

}

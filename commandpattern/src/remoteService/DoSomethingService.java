package remoteService;

import java.math.BigDecimal;
import java.rmi.Remote;
import java.rmi.RemoteException;

import server.commands.Command;

/**
 * Interface als Vorlage fuer serverseitige Taskabfrage
 * 
 * @author mhaden
 *
 */
public interface DoSomethingService extends Remote {
	/**
	 * Aufgabe ausfuehren
	 * 
	 * @param c auszufuehrende aufgabe
	 * @throws RemoteException
	 */
	void executeTask(Command<BigDecimal> c) throws RemoteException;
}

package server;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

import remoteService.DoSomethingService;

/**
 * 
 * Serverprogramm mit stub zum Verbindungsaufbau
 * 
 * @author mhaden
 *
 */
public class Server {

	/**
	 * @param args argumente
	 */
	public static void main(String[] args) {
		// SecurityManager erstellen wenn nicht vorhanden
		if (System.getSecurityManager() == null) {
			System.setSecurityManager(new SecurityManager());
		}
		try {
			// Neuen ServerService erstellen
			ServerService uRemoteObject = new ServerService();
			// stub aus ServerService Onjekt erstellen
			DoSomethingService stub = (DoSomethingService) UnicastRemoteObject.exportObject(uRemoteObject, 0);
			// Neue Registry erstellen auf Port 1234
			Registry registry = LocateRegistry.createRegistry(1234);
			// Remote Objekt mit Registry verknuepfen unter dem Namen Service
			registry.rebind("Service", stub);
			// Ausgabe bei erfolgreichem verbinden
			System.out.println("Service bound! Press Enter to terminate ...");

			// Bei ENTER Verbindung schlieﬂen
			while (System.in.read() != '\n')
				;
			UnicastRemoteObject.unexportObject(uRemoteObject, true);

			System.out.println("Service unbound, System goes down ...");

		} catch (RemoteException re) {
			System.err.println("Service already bound?" + " Check your RMI-Registry settings!");
			re.printStackTrace();
			System.exit(1);
		} catch (Exception e) {
			System.err.println("Service exception:");
			e.printStackTrace();
			System.exit(1);
		}

	}

}

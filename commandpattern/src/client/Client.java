package client;

import java.math.BigDecimal;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

import callback.Callback;
import callback.PiCallback;
import remoteService.DoSomethingService;
import server.commands.Command;
import server.commands.Pi;

/**
 * Client
 * 
 * @author mhaden
 *
 */
public class Client {

	/**
	 * main
	 * 
	 * @param args
	 *            argumente
	 */
	public static void main(String[] args) {
		// Neuen SecurityManager erzeugen
		if (System.getSecurityManager() == null) {
			System.setSecurityManager(new SecurityManager());
		}
		try {
			// Registry abfragen
			Registry registry = LocateRegistry.getRegistry(1234);
			// Callback Objekt erstellen
			Callback cb = new PiCallback();
			// Stub erstellen
			Callback cbStub = (Callback) UnicastRemoteObject.exportObject(cb, 0);
			// Registry auslesen
			DoSomethingService uRemoteObject = (DoSomethingService) registry.lookup("Service");
			System.out.println("Service found");

			Command<BigDecimal> rc = new Pi(Integer.parseInt(args[0]), cbStub);
			uRemoteObject.executeTask(rc);

		} catch (RemoteException re) {
			System.err.println("Service not found?" + " Check your RMI-Registry!");
			System.exit(1);
		} catch (Exception e) {
			System.err.println("Service exception:");
			e.printStackTrace();
			System.exit(1);
		}
	}
}

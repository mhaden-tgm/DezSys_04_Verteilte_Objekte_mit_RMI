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
 * Clientprogramm aus Userseite zum verbinden zu dem Server und 
 * ausfuehren des Berechnung
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
		// Neuen SecurityManager erzeugen wenn keiner vorhanden ist
		if (System.getSecurityManager() == null) {
			System.setSecurityManager(new SecurityManager());
		}
		try {
			// Registry abfragen
			Registry registry = LocateRegistry.getRegistry(1234);
			// Callback Objekt erstellen
			Callback cb = new PiCallback();
			// Stub erstellen mit Callback Objekt
			Callback cbStub = (Callback) UnicastRemoteObject.exportObject(cb, 0);
			// ServerService Objekt unter dem Namen Service aus Registry auslesen
			DoSomethingService uRemoteObject = (DoSomethingService) registry.lookup("Service");
			// Nachricht bei Erfolg
			System.out.println("Service found");
			// Neues Objekt von Pi Berechnung erstellen
			Command<BigDecimal> rc = new Pi(Integer.parseInt(args[0]), cbStub);
			// Pi Obejekt uebergeben und berechnung auf Serverseite ausfuehren
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

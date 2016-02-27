package callback;

import java.math.BigDecimal;
import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 * Callback Interface
 * 
 * @author mhaden
 *
 */
public interface Callback extends Remote {
	/**
	 * Erhahlt das Ergbnis der Berechnung
	 * 
	 * @param pi ergebnis
	 * @throws RemoteException
	 */
	void receive(BigDecimal pi) throws RemoteException;
}

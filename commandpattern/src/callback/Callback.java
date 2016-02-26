package callback;

import java.math.BigDecimal;
import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 * 
 * @author mhaden
 *
 */
public interface Callback extends Remote {
	void receive(BigDecimal pi) throws RemoteException;
}

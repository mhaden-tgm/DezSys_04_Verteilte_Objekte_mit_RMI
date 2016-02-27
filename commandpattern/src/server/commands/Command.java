package server.commands;

import java.io.Serializable;

/**
 * Interface als Vorlage fuer Berechnung
 * 
 * @author mhaden
 *
 */
public interface Command<T> extends Serializable {
	/**
	 * Berechnung starten
	 */
	void execute();
}

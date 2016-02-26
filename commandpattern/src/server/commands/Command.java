package server.commands;

import java.io.Serializable;

/**
 * Interface als Vorlage fuer Berechnung
 * 
 * @author mhaden
 *
 */
public interface Command<T> extends Serializable {
	void execute();
}

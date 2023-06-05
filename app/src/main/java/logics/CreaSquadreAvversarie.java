package logics;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

import data.Squadra;

/**
 * The {@code CreaSquadreAvversarie} interface represents a contract for creating opposing teams.
 * It declares a method {@code getSquadre()} which returns a list of {@code Squadra} objects.
 */

public interface CreaSquadreAvversarie {
	
	/**
     * Returns a list of opposing teams.
     *
     * @return The list of opposing teams.
     * @throws FileNotFoundException if the file is not found.
     * @throws ClassNotFoundException if the class is not found.
     * @throws IOException if an I/O error occurs.
     */
    List<Squadra> getSquadre() throws FileNotFoundException, ClassNotFoundException, IOException;
}

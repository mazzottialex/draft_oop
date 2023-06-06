package logics;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

import data.Team;

/**
 * The {@code CreateOpponentTeams} interface represents a contract for creating opposing teams.
 * It declares a method {@code getTeams()} which returns a list of {@code Team} objects.
 */

public interface CreateOpponentTeams {

    /**
     * Returns a list of opposing teams.
     *
     * @return The list of opposing teams.
     * @throws FileNotFoundException if the file is not found.
     * @throws ClassNotFoundException if the class is not found.
     * @throws IOException if an I/O error occurs.
     */
    List<Team> getTeams() throws FileNotFoundException, ClassNotFoundException, IOException;
}

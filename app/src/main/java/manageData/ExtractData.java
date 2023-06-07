package manageData;
import java.io.Serializable;
import java.util.List;
import java.util.Optional;
import java.util.function.Function;
import data.Player;
import data.Module;

/**
 * This interface {@code ExtractData} provides methods for extracting data.
 */
public interface ExtractData extends Serializable {

    /**
     * Retrieves a list of players belonging to a specific team.
     *
     * @param name The name of the team
     * @return A list of players belonging to the specified team
     */
    List<Player> getPlayerByTeam(String name);

    /**
     * Retrieves a player by their name.
     *
     * @param name The name of the player
     * @return An optional containing the player if found, or an empty optional if not found.
     */
    Optional<Player> getPlayerByName(String name);

    /**
     * Retrieves a list of players based on their position.
     *
     * @param pos The position of the players
     * @return A list of players belonging to the specified position
     */
    List<Player> getListByPos(String pos);
 
    /**
     * Retrieves a random list of players based on their position.
     *
     * @param pos The position of the players
     * @param n The number of players to retrieve
     * @return A list of randomly selected players belonging to the specified position
     */
    List<Player> getRandomByPos(String pos, int n);

    /**
     * Retrieves a list of all players.
     *
     * @return A list of all players
     */
    List<Player> getLi();

    /**
     * Retrieves the highest value of a player attribute across all players.
     *
     * @param attr The function representing the player attribute
     * @return The highest value of the specified player attribute
     */
    int getTopByAttribute(Function<Player, Integer> attr);

    /**
     * Retrieves the highest value of two player attributes across all players.
     *
     * @param f1 The function representing the first player attribute
     * @param f2 The function representing the second player attribute
     * @return The highest value between the two specified player attributes
     */
    int getTopByAttribute(Function<Player, Integer> f1, Function<Player, Integer> f2);

    /**
     * Retrieves a list of players ordered by a specific attribute.
     *
     * @param attr The function representing the player attribute
     * @return A list of players ordered by the specified attribute
     */
    List<Player> getListOrdered(Function<Player, Integer> attr);

    /**
     * Retrieves a list of substitution players for a specific team and position within a module.
     *
     * @param team The team name
     * @param pos The position of the players
     * @param module The module of the team
     * @return A list of substitution players for the specified team, position, and module
     */
    List<Player> getStartingByTeamByPos(String team, String pos, Module module);

    /**
     * Retrieves a list of substitution players for a specific team and position within a module.
     *
     * @param team The team name
     * @param pos The position of the players
     * @param module The module of the team
     * @return A list of substitution players for the specified team, position, and module
     */
    List<Player> getSubstitutionByTeamByPos(String team, String pos, Module module);

    /**
     * Retrieves a list of starting players for a specific team and module.
     *
     * @param team The team name
     * @param module The module of the team
     * @return A list of starting players for the specified team and module
     */
    List<Player> getStarting(String team, Module module);

    /**
     * Retrieves a list of substitution players for a specific team and module.
     *
     * @param team The team name
     * @param module The module of the team
     * @return A list of substitution players for the specified team and module
     */
    List<Player> getSubstitution(String team, Module module);

    /**
     * Retrieves the names of players belonging to a specific team.
     *
     * @param team The team name
     * @return A list of player names belonging to the specified team
     */
    List<String> getPlayerName(String team);

    /**
     * Retrieves the names of starting players for a specific team and module.
     *
     * @param team The team name
     * @param module The module of the team
     * @return A list of player names for the starting lineup of the specified team and module
     */
    List<String> getNameStarting(String team, Module module);

    /**
     * Retrieves the names of substitution players for a specific team and module.
     *
     * @param team The team name
     * @param module The module of the team
     * @return A list of player names for the substitution players of the specified team and module
     */
    List<String> getNameSubstitution(String team, Module module);

    /**
     * Retrieves the starting players of a specified team in a given module
     * and converts them into a list of vectors with role, name and rating.
     *
     * @param team    the team for which to retrieve the starting players
     * @param module  the module specifying the players' attributes
     * @return        a list of vectors representing the starting players' information
     */
    List<?> getStartersRoleNameRating(String team, Module module);

    /**
     * Retrieves a random list of players based on the number of players required for each position.
     *
     * @param nA The number of players required for position A
     * @param nC The number of players required for position C
     * @param nD The number of players required for position D
     * @param nP The number of players required for position P
     * @return A random list of players satisfying the required number of players for each position
     */
    List<Player> getRandom(int nA, int nC, int nD, int nP);

    /**
     * Retrieves a player by their ID.
     *
     * @param id The ID of the player
     * @return An optional containing the player if found, or an empty optional if not found
     */
    Optional<Player> getPlayerById(int id);
}

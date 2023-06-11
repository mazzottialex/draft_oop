package model.data;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import utils.Pair;
import utils.Triple;

/**
 * Represents a {@code Player} in a football team.
 */
public final class Player implements Serializable {

    private static final long serialVersionUID = -557391519003956401L;
    private final int id;
    private final String name;
    private final String pos;
    private final String team;
    private final int matchesPlayed;
    private final int minutes;
    private final int goals;
    private final int shots;
    private final int dribbles;
    private final int assists;
    private final int passes;
    private final int keyPasses;
    private final int yellowCards;
    private final int redCards;
    private final int ballsRecovered;
    private final int tacklesWon;
    private final int cleanSheets;
    private final int saves;
    private Pair<Integer, Integer> cardRating;
    private Pair<Integer, Triple<Integer, Integer, Integer>> rating; //VAL, A-C-D

    /**
     * Constructs a Player object with the specified parameters.
     *
     * @param id              the player's ID
     * @param name            the player's name
     * @param pos             the player's position (role)
     * @param team            the player's team
     * @param matchesPlayed   the number of matches played by the player during the season
     * @param minutes         the total minutes played by the player during the season
     * @param goals           the number of goals scored by the player during the season
     * @param shots           the number of shots taken by the player during the season
     * @param dribbles        the number of successful dribbles by the player during the season
     * @param assists         the number of assists made by the player during the season
     * @param passes          the number of passes made by the player during the season
     * @param keyPasses       the number of key passes made by the player during the season
     * @param yellowCards     the number of yellow cards received by the player during the season
     * @param redCards        the number of red cards received by the player during the season
     * @param ballsRecovered  the number of balls recovered by the player during the season
     * @param tacklesWon      the number of tackles won by the player during the season
     * @param cleanSheets     the number of clean sheets achieved by the player (goalkeeper) during the season
     * @param saves           the number of saves made by the player (goalkeeper) during the season
     */
    public Player(final int id, final String name, final String pos, final String team, final int matchesPlayed,
            final int minutes, final int goals, final int shots, final int dribbles, final int assists, final int passes,
            final int keyPasses, final int yellowCards, final int redCards, final int ballsRecovered, final int tacklesWon,
            final int cleanSheets, final int saves) {
        this.id = id;
        this.name = name;
        this.pos = pos;
        this.team = team;
        this.matchesPlayed = matchesPlayed;
        this.minutes = minutes;
        this.goals = goals;
        this.shots = shots;
        this.dribbles = dribbles;
        this.assists = assists;
        this.passes = passes;
        this.keyPasses = keyPasses;
        this.yellowCards = yellowCards;
        this.redCards = redCards;
        this.ballsRecovered = ballsRecovered;
        this.tacklesWon = tacklesWon;
        this.cleanSheets = cleanSheets;
        this.saves = saves;
        this.rating = null;
        this.cardRating = null;
    }

    /**
     * Returns the ID of the player.
     *
     * @return the player's ID
     */
    public int getId() {
        return id;
    }

    /**
     * Returns the name of the player.
     *
     * @return the player's name
     */
    public String getName() {
        return name;
    }

    /**
     * Returns the position (role) of the player.
     *
     * @return the player's position
     */
    public String getPos() {
        return pos;
    }

    /**
     * Returns the team of the player.
     *
     * @return the player's team
     */
    public String getTeam() {
        return team;
    }

    /**
     * Returns the number of matches played by the player.
     *
     * @return the number of matches played
     */
    public int getMatchesPlayed() {
        return matchesPlayed;
    }

    /**
     * Returns the total minutes played by the player.
     *
     * @return the total minutes played
     */
    public int getMinutes() {
        return minutes;
    }

    /**
     * Returns the number of goals scored by the player.
     *
     * @return the number of goals scored
     */
    public int getGoals() {
        return goals;
    }

    /**
     * Returns the number of shots taken by the player.
     *
     * @return the number of shots taken
     */
    public int getShots() {
        return shots;
    }

    /**
     * Returns the number of successful dribbles by the player.
     *
     * @return the number of successful dribbles
     */
    public int getDribbles() {
        return dribbles;
    }

    /**
     * Returns the number of assists made by the player.
     *
     * @return the number of assists made
     */
    public int getAssists() {
        return assists;
    }

    /**
     * Returns the number of passes made by the player.
     *
     * @return the number of passes made
     */
    public int getPasses() {
        return passes;
    }

    /**
     * Returns the number of key passes made by the player.
     *
     * @return the number of key passes made
     */
    public int getKeyPasses() {
        return keyPasses;
    }

    /**
     * Returns the number of yellow cards received by the player.
     *
     * @return the number of yellow cards received
     */
    public int getYellowCards() {
        return yellowCards;
    }

    /**
     * Returns the number of red cards received by the player.
     *
     * @return the number of red cards received
     */
    public int getRedCards() {
        return redCards;
    }

    /**
     * Returns the number of balls recovered by the player.
     *
     * @return the number of balls recovered
     */
    public int getBallsRecovered() {
        return ballsRecovered;
    }

    /**
     * Returns the number of tackles won by the player.
     *
     * @return the number of tackles won
     */
    public int getTacklesWon() {
        return tacklesWon;
    }

    /**
     * Returns the number of clean sheets achieved by the player (goalkeeper).
     *
     * @return the number of clean sheets achieved
     */
    public int getCleanSheets() {
        return cleanSheets;
    }

    /**
     * Returns the number of saves made by the player (goalkeeper).
     *
     * @return the number of saves made
     */
    public int getSaves() {
        return saves;
    }

    /**
     * Returns the card rating of the player.
     *
     * @return the card rating of the player
     */
    public Pair<Integer, Integer> getCardRating() {
        return cardRating;
    }

    /**
     * Returns the overall rating of the player.
     *
     * @return the overall rating of the player
     */
    public Pair<Integer, Triple<Integer, Integer, Integer>> getRating() {
        return rating;
    }

    /**
     * Sets the card rating of the player.
     *
     * @param cardRating the card rating to set
     */
    public void setCardRating(final Pair<Integer, Integer> cardRating) {
        this.cardRating = cardRating;
    }

    /**
     * Sets the overall rating of the player.
     *
     * @param overallRating the overall rating to set
     */
    public void setRating(final Pair<Integer, Triple<Integer, Integer, Integer>> overallRating) {
        this.rating = overallRating;
    }

    /**
     * Converts the player object to list for table.
     *
     * @return a list containing the player's information
     */
    public List<String> toList() {
        List<String> list = new ArrayList<>();
        list.add(pos);
        list.add(name);
        list.add(rating.getX().toString());
        list.add(rating.getY().getX().toString());
        list.add(rating.getY().getY().toString());
        list.add(rating.getY().getZ().toString());
        return list;
    }

    @Override
    public int hashCode() {
        return Objects.hash(yellowCards, assists, cleanSheets, dribbles, redCards, goals, id, minutes, name, saves,
            passes, keyPasses, matchesPlayed, rating, cardRating, tacklesWon, shots, ballsRecovered);
    }

    @Override
    public boolean equals(final Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        Player other = (Player) obj;
        return yellowCards == other.yellowCards && assists == other.assists && cleanSheets == other.cleanSheets
            && dribbles == other.dribbles && redCards == other.redCards && goals == other.goals && id == other.id
            && minutes == other.minutes && Objects.equals(name, other.name) && saves == other.saves
            && passes == other.passes && keyPasses == other.keyPasses && matchesPlayed == other.matchesPlayed
            && Objects.equals(rating, other.rating) && Objects.equals(cardRating, other.cardRating)
            && tacklesWon == other.tacklesWon && shots == other.shots && ballsRecovered == other.ballsRecovered;
    }

    @Override
    public String toString() {
        return "" + id + ", \"" + name + "\", \"" + pos + "\", \"" + team + "\", "
                + matchesPlayed + ", " + minutes + ", " + goals + ", " + shots + ", "
                + dribbles + ", " + assists + ", " + passes + ", " + keyPasses
                + ", " + yellowCards + ", " + redCards + ", " + ballsRecovered
                + ", " + tacklesWon + ", " + cleanSheets + ", " + saves;
    }
}

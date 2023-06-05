package data;

import java.io.Serializable;
import java.util.Objects;
import java.util.Vector;

import utils.Pair;
import utils.Triple;

public class Player implements Serializable{

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

	public Player(int id, String name, String pos, String team, int matchesPlayed, int minutes, int goals, int shots,
			int dribbles, int assists, int passes, int keyPasses, int yellowCards, int redCards, int ballsRecovered, int tacklesWon,
			int cleanSheets, int saves) {
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

	public int getId() {
		return id;
	}
	
	public String getName() {
		return name;
	}
	
	public String getPos() {
		return pos;
	}

	public String getTeam() {
		return team;
	}

	public int getMatchesPlayed() {
		return matchesPlayed;
	}

	public int getMinutes() {
		return minutes;
	}

	public int getGoals() {
		return goals;
	}

	public int getShots() {
		return shots;
	}

	public int getDribbles() {
		return dribbles;
	}

	public int getAssists() {
		return assists;
	}

	public int getPasses() {
		return passes;
	}

	public int getKeyPasses() {
		return keyPasses;
	}

	public int getYellowCards() {
		return yellowCards;
	}

	public int getRedCards() {
		return redCards;
	}

	public int getBallsRecovered() {
		return ballsRecovered;
	}
	
	public int getTacklesWon() {
		return tacklesWon;
	}

	public int getCleanSheets() {
		return cleanSheets;
	}

	public int getSaves() {
		return saves;
	}

	public Pair<Integer, Integer> getCardRating(){
		return cardRating;
	}
	
	public Pair<Integer, Triple<Integer, Integer, Integer>> getRating() {
		return rating;
	}

	public void setCardRating(Pair<Integer, Integer> cardRating) {
		this.cardRating = cardRating;
	}
	
	public void setRating(Pair<Integer, Triple<Integer, Integer, Integer>> overallRating) {
		this.rating = overallRating;
	}

	
	public Vector<?> toVector() {
		Vector<Object> v = new Vector<>();
		v.add(pos);
		v.add(name);
		v.add(rating.getX());
		v.add(rating.getY().getX());
		v.add(rating.getY().getY());
		v.add(rating.getY().getZ());
		return v;
	}

	@Override
	public int hashCode() {
		return Objects.hash(yellowCards, assists, cleanSheets, dribbles, redCards, goals, id, minutes, name, saves,
				passes, keyPasses, matchesPlayed, rating, cardRating, tacklesWon, shots, ballsRecovered);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Player other = (Player) obj;
		return yellowCards == other.yellowCards && assists == other.assists && cleanSheets == other.cleanSheets
				&& dribbles == other.dribbles && redCards == other.redCards && goals == other.goals && id == other.id
				&& minutes == other.minutes && Objects.equals(name, other.name) && saves == other.saves
				&& passes == other.passes && keyPasses == other.keyPasses && matchesPlayed == other.matchesPlayed
				&& Objects.equals(rating, other.rating) && Objects.equals(cardRating, other.cardRating)
				&& tacklesWon == other.tacklesWon && shots == other.shots && ballsRecovered == other.ballsRecovered;
	}
}

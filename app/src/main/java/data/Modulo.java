package data;

/**
 * Represents different football formations (modules).
 */
public enum Modulo {
    M343(3, 4, 3),
    M352(3, 5, 2),
    M433(4, 3, 3),
    M442(4, 4, 2),
    M451(4, 5, 1),
    M532(5, 3, 2),
    M541(5, 4, 1);

    private int dif;
    private int cen;
    private int att;

    /**
     * Constructs a Modulo enum value with the specified number of defenders, midfielders, and attackers.
     *
     * @param dif The number of defenders.
     * @param cen The number of midfielders.
     * @param att The number of attackers.
     */
    private Modulo(int dif, int cen, int att) {
        this.dif = dif;
        this.cen = cen;
        this.att = att;
    }

    /**
     * Returns the number of defenders in the formation.
     *
     * @return The number of defenders.
     */
    public int getNumDif() {
        return dif;
    }

    /**
     * Returns the number of midfielders in the formation.
     *
     * @return The number of midfielders.
     */
    public int getNumCen() {
        return cen;
    }

    /**
     * Returns the number of attackers in the formation.
     *
     * @return The number of attackers.
     */
    public int getNumAtt() {
        return att;
    }

    /**
     * Returns the number of players in the formation based on the given role.
     *
     * @param ruolo The role of the player (e.g., "P", "D", "C", "A").
     * @return The number of players for the given role.
     */
    public int getN(String ruolo) {
        if (ruolo == "P")
            return 1;
        else if (ruolo == "D")
            return dif;
        else if (ruolo == "C")
            return cen;
        else if (ruolo == "A")
            return att;
        else return 0;
    }

    /**
     * Returns a string representation of the formation in the format "dif-cen-att".
     *
     * @return The string representation of the formation.
     */
    public String toString() {
        return dif + "-" + cen + "-" + att;
    }
}

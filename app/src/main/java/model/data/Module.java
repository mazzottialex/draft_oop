package model.data;

/**
 * Represents different football formations (modules).
 */
public enum Module {

    /**
     * 343 formation.
     */
    M343(3, 4, 3),

    /**
     * 352 formation.
     */
    M352(3, 5, 2),

    /**
     * 433 formation.
     */
    M433(4, 3, 3),

    /**
     * 442 formation.
     */
    M442(4, 4, 2),

    /**
     * 451 formation.
     */
    M451(4, 5, 1),

    /**
     * 532 formation.
     */
    M532(5, 3, 2),

    /**
     * 541 formation.
     */
    M541(5, 4, 1);

    private int dif;
    private int cen;
    private int att;

    /**
     * Constructs a {@code Module} enum value with the specified number of defenders, midfielders, and forwards.
     *
     * @param dif The number of defenders.
     * @param cen The number of midfielders.
     * @param att The number of attackers.
     */
    Module(final int dif, final int cen, final int att) {
        this.dif = dif;
        this.cen = cen;
        this.att = att;
    }

    /**
     * Returns the number of defenders in the formation.
     *
     * @return The number of defenders.
     */
    public int getNumDef() {
        return dif;
    }

    /**
     * Returns the number of midfielders in the formation.
     *
     * @return The number of midfielders.
     */
    public int getNumMid() {
        return cen;
    }

    /**
     * Returns the number of forwards in the formation.
     *
     * @return The number of forwards.
     */
    public int getNumFor() {
        return att;
    }

    /**
     * Returns the number of players in the formation based on the given role.
     *
     * @param ruolo The role of the player (e.g., "P", "D", "C", "A").
     * @return The number of players for the given role.
     */
    public int getPlayersNumberByRole(final String ruolo) {
        if ("P".equals(ruolo)) {
            return 1;
        } else if ("D".equals(ruolo)) {
            return dif;
        } else if ("C".equals(ruolo)) {
            return cen;
        } else if ("A".equals(ruolo)) {
            return att;
        } else {
            return 0;
        }
    }

    /**
     * Returns a string representation of the formation in the format "dif-cen-att".
     *
     * @return The string representation of the formation.
     */
    @Override
    public String toString() {
        return dif + "-" + cen + "-" + att;
    }
}

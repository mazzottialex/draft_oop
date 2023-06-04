package data;

public enum Modulo {
    M343(3, 4, 3),
    M352(3, 5, 2),
    M433(4, 3, 3),
    M442(4, 4, 2),
    M451(4, 5, 1),
    M532(5, 3, 2),
    M541(5, 4, 1);
    private final int dif;
    private final int cen;
    private final int att;
    private Modulo(final int dif, final int cen, final int att) {
        this.dif = dif;
        this.cen = cen;
        this.att = att;
    }
    public int getNumDif() {
        return dif;
    }
    public int getNumCen() {
        return cen;
    }
    public int getNumAtt() {
        return att;
    }
    public int getN(final String ruolo) {
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
    public String toString() {
        return dif + "-" + cen + "-" + att;
    }
}

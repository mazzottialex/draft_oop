package logics;
/**
 *Logics of the Gui Start.
 */
public interface LogicStart {
     /**
     * Returns the online status.
     *
     * @return the online status. True indicates online status, False offline status
     */
    Boolean getOnline();
    /**
     * Returns the first season from File backupSeason.txt.
     *
     * @return the first season as a String. 
     */
    String getFirstSeason();
}

/*
 * This Java source file was generated by the Gradle 'init' task.
 */
package it.unibo.draft;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.List;

import model.data.Player;
import model.managedata.ManageData;
import model.managedata.ManageDataImpl;
import controller.LogicsCreateTeam;
import controller.LogicsCreateTeamImpl;

/**
 * Test.
 *
 */
public class AppTest {
    /**
     * Test the method addPlayerInTeam.
     * @throws Exception
     */
    @Test
    public void testInsert() throws Exception {
        final ManageData md = new ManageDataImpl("2022-2023");
        md.loadData();
        final Player p1 = new Player(0, "n1", "P", "t", 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0);
        final List<Player> list = md.getLi();
        final LogicsCreateTeam lc = new LogicsCreateTeamImpl("myTeam", ClassLoader.getSystemResource("stemmi/bianco.png"), list);
        lc.addPlayerInTeam(p1);
        assertFalse(lc.teamComplete());
    }
    /**
     * Test the method GetRandom.
     * @throws Exception
     */
    @Test
    public void testGetRandom() throws Exception {
        final int dif = 4;
        final ManageData md = new ManageDataImpl("2022-2023");
        md.loadData();
        final List<Player> list = md.getLi();
        final LogicsCreateTeam lc = new LogicsCreateTeamImpl("myTeam", ClassLoader.getSystemResource("stemmi/bianco.png"), list);
        assertEquals(dif, lc.getRandom("D", dif).size());
    }
    /**
     * Test the method TeamComplete.
     * @throws Exception
     */
    @Test
    public void testTeamComplete() throws Exception {
        int i = 1;
        final ManageData md = new ManageDataImpl("2022-2023");
        md.loadData();
        List<Player> list = md.getLi();
        final LogicsCreateTeam lc = new LogicsCreateTeamImpl("myTeam", ClassLoader.getSystemResource("stemmi/bianco.png"), list);
        lc.addPlayerInTeam(new Player(i++, "n1", "P", "t", 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0));
        lc.addPlayerInTeam(new Player(i++, "n2", "D", "t", 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0));
        lc.addPlayerInTeam(new Player(i++, "n3", "D", "t", 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0));
        lc.addPlayerInTeam(new Player(i++, "n4", "D", "t", 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0));
        lc.addPlayerInTeam(new Player(i++, "n5", "D", "t", 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0));
        lc.addPlayerInTeam(new Player(i++, "n6", "C", "t", 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0));
        lc.addPlayerInTeam(new Player(i++, "n7", "C", "t", 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0));
        lc.addPlayerInTeam(new Player(i++, "n8", "C", "t", 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0));
        lc.addPlayerInTeam(new Player(i++, "n9", "A", "t", 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0));
        lc.addPlayerInTeam(new Player(i++, "n10", "A", "t", 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0));
        lc.addPlayerInTeam(new Player(i, "n11", "A", "t", 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0));
        assertTrue(lc.teamComplete());
    }
}

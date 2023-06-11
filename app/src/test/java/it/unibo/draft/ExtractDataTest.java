package it.unibo.draft;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.List;

import org.junit.Test;

import model.data.Module;
import model.data.Player;
import model.managedata.ExtractDataImpl;
import model.managedata.ManageData;
import model.managedata.ManageDataImpl;
import model.rating.AnalysisRating;
import model.rating.AnalysisRatingImpl;

/**
 * This class contains unit tests for the ExtractDataImpl class.
 */
public class ExtractDataTest {

    private final ExtractDataImpl ed;
    private static final String TEAM = "JUV";
    private static final Module MODULE = Module.M433;
    private static final int TEAM_SIZE = 20;
    private static final int STARTERSIZE = 11;

    /**
     * Construct the test.
     *
     * @throws Exception if an error occurs during setup.
     */
    public ExtractDataTest() {
        final ManageData md = new ManageDataImpl("2022-2023");
        md.loadData();
        List<Player> list = md.getLi();
        final AnalysisRating analysisRating = new AnalysisRatingImpl(list);
        list = analysisRating.updateRating();
        ed = new ExtractDataImpl(list);
    }

    /**
     * Tests the findTeams method of ExtractDataImpl.
     */
    @Test
    public void testFindTeams() {
        final List<String> teams = ed.findTeams();
        assertNotNull(teams);
        assertEquals(TEAM_SIZE, teams.size());
    }

    /**
     * Tests the getStarting method of ExtractDataImpl.
     */
    @Test
    public void testGetStarting() {
        final List<Player> starters = ed.getStarting(TEAM, MODULE);
        assertNotNull(starters);
        assertEquals(starters.size(), STARTERSIZE);
    }

    /**
     * Tests the getStartingByTeamByPos method of ExtractDataImpl.
     */
    @Test
    public void testGetStartingByTeamByPos() {
        final List<Player> def = ed.getStartingByTeamByPos(TEAM, "D", MODULE);
        final List<Player> midf = ed.getStartingByTeamByPos(TEAM, "C", MODULE);
        final List<Player> forw = ed.getStartingByTeamByPos(TEAM, "A", MODULE);
        assertNotNull(def);
        assertEquals(def.size(), MODULE.getNumDef());
        assertEquals(midf.size(), MODULE.getNumMid());
        assertEquals(forw.size(), MODULE.getNumFor());
    }
}

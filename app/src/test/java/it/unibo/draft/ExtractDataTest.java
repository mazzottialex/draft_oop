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
    private final ManageData md;
    private List<Player> list;
    private final String team = "JUV";
    private final Module module = Module.M433;
    private static final int TEAM_SIZE = 20;
    private static final int STARTERSIZE = 11;

    /**
     * Construct the test.
     *
     * @throws Exception if an error occurs during setup.
     */
    public ExtractDataTest() throws Exception {
        md = new ManageDataImpl("2022-2023");
        md.loadData();
        list = md.getLi();
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
        final List<Player> starters = ed.getStarting(team, module);
        assertNotNull(starters);
        assertEquals(starters.size(), STARTERSIZE);
    }

    /**
     * Tests the getStartingByTeamByPos method of ExtractDataImpl.
     */
    @Test
    public void testGetStartingByTeamByPos() {
        final List<Player> def = ed.getStartingByTeamByPos(team, "D", module);
        final List<Player> midf = ed.getStartingByTeamByPos(team, "C", module);
        final List<Player> forw = ed.getStartingByTeamByPos(team, "A", module);
        assertNotNull(def);
        assertEquals(def.size(), module.getNumDef());
        assertEquals(midf.size(), module.getNumMid());
        assertEquals(forw.size(), module.getNumFor());
    }
}

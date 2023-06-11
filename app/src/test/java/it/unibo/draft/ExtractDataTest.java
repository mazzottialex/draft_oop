package it.unibo.draft;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.List;

import org.junit.Before;
import org.junit.Test;

import model.data.Player;
import model.data.managedata.ExtractDataImpl;
import model.data.managedata.ManageData;
import model.data.managedata.ManageDataImpl;
import model.rating.AnalysisRating;
import model.rating.AnalysisRatingImpl;

/**
 * This class contains unit tests for the ExtractDataImpl class.
 */
public class ExtractDataTest {

    private ExtractDataImpl ed;
    private ManageData md;
    private List<Player> list;
    private static final int TEAM_SIZE = 20;
    private static final int STARTERSIZE = 11;

    /**
     * Sets up the test environment before each test method.
     *
     * @throws Exception if an error occurs during setup.
     */
    @Before
    public void setUp() throws Exception {
        md = new ManageDataImpl("2022-2023");
        md.loadData();
        list = md.getLi();
        AnalysisRating analysisRating = new AnalysisRatingImpl(list);
        list = analysisRating.updateRating();
        ed = new ExtractDataImpl(list);
    }

    /**
     * Tests the findTeams method of ExtractDataImpl.
     */
    @Test
    public void testFindTeams() {
        List<String> teams = ed.findTeams();
        assertNotNull(teams);
        assertEquals(TEAM_SIZE, teams.size(), 0);
    }

    /**
     * Tests the getStarting method of ExtractDataImpl.
     */
    @Test
    public void testGetStarting() {
        String team = "JUV";
        model.data.Module module = model.data.Module.M433;
        List<Player> starters = ed.getStarting(team, module);
        assertNotNull(starters);
        assertEquals(starters.size(), STARTERSIZE);
    }

    /**
     * Tests the getStartingByTeamByPos method of ExtractDataImpl.
     */
    @Test
    public void testGetStartingByTeamByPos() {
        String team = "JUV";
        model.data.Module module = model.data.Module.M433;
        List<Player> def = ed.getStartingByTeamByPos(team, "D", module);
        List<Player> midf = ed.getStartingByTeamByPos(team, "C", module);
        List<Player> forw = ed.getStartingByTeamByPos(team, "A", module);
        assertNotNull(def);
        assertEquals(def.size(), module.getNumDef());
        assertEquals(midf.size(), module.getNumMid());
        assertEquals(forw.size(), module.getNumFor());
    }
}

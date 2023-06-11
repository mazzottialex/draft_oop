package it.unibo.draft;

import static org.junit.Assert.assertEquals;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import model.data.Player;
import model.data.Team;
import model.data.managedata.CreateOpponentTeamsImpl;
import model.data.managedata.ManageData;
import model.data.managedata.ManageDataImpl;
import model.rating.AnalysisRating;
import model.rating.AnalysisRatingImpl;

/**
 * This class contains unit tests for the CreateOpponentTeamsImpl class.
 */
public class CreateOpponentTeamsTest {

    private CreateOpponentTeamsImpl createOpponentTeams;
    private List<Player> li;
    private ManageData md;
    private static final int N_TEAM = 5;

    /**
     * Sets up the test environment before each test method.
     *
     * @throws Exception if an error occurs during setup.
     */
    @Before
    public void setUp() throws Exception {
        md = new ManageDataImpl("2022-2023");
        md.loadData();
        li = md.getLi();
        AnalysisRating analysisRating = new AnalysisRatingImpl(li);
        li = analysisRating.updateRating();
        createOpponentTeams = new CreateOpponentTeamsImpl(li, N_TEAM);
    }

    /**
     * Tests the getTeams method of CreateOpponentTeamsImpl.
     *
     * @throws FileNotFoundException  if the file is not found.
     * @throws ClassNotFoundException if the class is not found.
     * @throws IOException            if an I/O error occurs.
     */
    @Test
    public void testGetTeams() throws FileNotFoundException, ClassNotFoundException, IOException {
        List<Team> result = createOpponentTeams.getTeams();
        assertEquals(N_TEAM, result.size());
    }
}


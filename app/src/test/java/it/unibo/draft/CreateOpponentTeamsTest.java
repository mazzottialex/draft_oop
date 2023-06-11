package it.unibo.draft;

import static org.junit.Assert.assertEquals;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import model.data.Player;
import model.data.Team;
import model.managedata.CreateOpponentTeamsImpl;
import model.managedata.ManageData;
import model.managedata.ManageDataImpl;
import model.rating.AnalysisRating;
import model.rating.AnalysisRatingImpl;

/**
 * This class contains unit tests for the CreateOpponentTeamsImpl class.
 */
public class CreateOpponentTeamsTest {

    private final CreateOpponentTeamsImpl createOpponentTeams;
    private static final int N_TEAM = 5;
    private static final Logger LOG = LoggerFactory.getLogger(CreateOpponentTeamsTest.class);

    /**
     * Construct the test.
     *
     * @throws Exception if an error occurs during setup.
     */
    public CreateOpponentTeamsTest() {
    	final ManageData md = new ManageDataImpl("2022-2023");
    	try {
            md.loadData();
        } catch (Exception e) {
            LOG.error("Error", e);
        }
        List<Player> li = md.getLi();
        final AnalysisRating analysisRating = new AnalysisRatingImpl(li);
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
        final List<Team> result = createOpponentTeams.getTeams();
        assertEquals(N_TEAM, result.size());
    }
}


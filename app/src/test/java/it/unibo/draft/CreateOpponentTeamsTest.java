package it.unibo.draft;

import static org.junit.Assert.assertEquals;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import managedata.CreateOpponentTeamsImpl;
import managedata.ManageData;
import managedata.ManageDataImpl;
import model.data.Player;
import model.data.Team;
import model.rating.AnalysisRating;
import model.rating.AnalysisRatingImpl;

public class CreateOpponentTeamsTest {
    private CreateOpponentTeamsImpl createOpponentTeams;
    private List<Player> li;
    private ManageData md;
    @Before
    public void setUp() throws Exception {
    	md = new ManageDataImpl("2022-2023");
        md.loadData();
        li = md.getLi();
        AnalysisRating analysisRating = new AnalysisRatingImpl(li);
        li = analysisRating.updateRating();
        createOpponentTeams = new CreateOpponentTeamsImpl(li, 5);
    }
    @Test
    public void testGetTeams() throws FileNotFoundException, ClassNotFoundException, IOException {
		List<Team> result = createOpponentTeams.getTeams();
        assertEquals(5, result.size());
    }
}

package it.unibo.draft;

import static org.junit.Assert.assertEquals;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import data.Module;
import data.Player;
import data.Team;
import managedata.CreateOpponentTeamsImpl;
import managedata.ExtractData;
import managedata.ExtractDataImpl;
import managedata.ManageData;
import managedata.ManageDataImpl;

public class CreateOpponentTeamsTest {

    private CreateOpponentTeamsImpl createOpponentTeams;
    private List<Player> li;
    private ManageData md;

    @Before
    public void setUp() throws Exception {
    	md = new ManageDataImpl("2022-2023");
        md.loadData();
        li = md.getLi();

        createOpponentTeams = new CreateOpponentTeamsImpl(li, 5);
    }

    @Test
    public void testGetTeams() throws FileNotFoundException, ClassNotFoundException, IOException {
        List<Team> result = createOpponentTeams.getTeams();

        assertEquals(2, result.size());
    }
}

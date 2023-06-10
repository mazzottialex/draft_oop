package it.unibo.draft;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.List;

import org.junit.Before;
import org.junit.Test;

import managedata.ExtractDataImpl;
import managedata.ManageData;
import managedata.ManageDataImpl;
import model.data.Player;

public class ExtractDataTest {

    private ExtractDataImpl ed;
    private ManageData md;
    private List<Player> list;

    @Before
    public void setUp() throws Exception {
    	md = new ManageDataImpl("2022-2023");
        md.loadData();
        list = md.getLi();
        ed = new ExtractDataImpl(list);
    }

    @Test
    public void testFindTeams() {
        List<String> teams = ed.findTeams();
        
        assertNotNull(teams);
        assertEquals(20, teams.size(), 0);
    }

    @Test
    public void testGetStarting() {
        String team = "JUV";
        model.data.Module module = model.data.Module.M433;

        List<Player> starters = ed.getStarting(team, module);

        assertNotNull(starters);
        assertEquals(starters.size(), 11);
    }

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

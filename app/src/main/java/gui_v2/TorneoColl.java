package gui_v2;
import java.util.ArrayList;
import java.util.List;

import data.Team;
import utils.Pair;
public class TorneoColl {
    private List<List<Team>> tabellone;
    private List<Team> lastLi;
    private List<Pair<Team, Integer>> liLastRisul = new ArrayList<>();
    public TorneoColl(List<Team> li) {
        tabellone = new ArrayList<>();
        tabellone.add(li);
        lastLi = li;
    }
    public void setLiLastRisul(List<Pair<Team, Integer>> liRis) {
        this.liLastRisul = liRis;
    }
    public void addLi(List<Team> li) {
        tabellone.add(li);
        lastLi = li;
    }
    public List<Team> getLastLi() {
        return lastLi;
    }
    public List<Pair<Team, Integer>> getLiLastRisul() {
        return liLastRisul;
    }
    public List<List<Team>> getTabellone() {
        return tabellone;
    }
    @Override
    public String toString() {
        return "TorneoColl [tabellone=" + tabellone + "]";
    }
}

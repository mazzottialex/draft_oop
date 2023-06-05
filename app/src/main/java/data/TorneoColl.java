package data;
import java.util.ArrayList;
import java.util.List;
import utils.Pair;
public class TorneoColl {
    private List<List<Squadra>> tabellone;
    private List<Squadra> lastLi;
    private List<Pair<Squadra, Integer>> liLastRisul = new ArrayList<>();
    public TorneoColl(List<Squadra> li) {
        tabellone = new ArrayList<>();
        tabellone.add(li);
        lastLi = li;
    }
    public void setLiLastRisul(List<Pair<Squadra, Integer>> liRis) {
        this.liLastRisul = liRis;
    }
    public void addLi(List<Squadra> li) {
        tabellone.add(li);
        lastLi = li;
    }
    public List<Squadra> getLastLi() {
        return lastLi;
    }
    public List<Pair<Squadra, Integer>> getLiLastRisul() {
        return liLastRisul;
    }
    public List<List<Squadra>> getTabellone() {
        return tabellone;
    }
    @Override
    public String toString() {
        return "TorneoColl [tabellone=" + tabellone + "]";
    }
}

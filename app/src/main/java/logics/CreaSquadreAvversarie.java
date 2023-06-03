package logics;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

import data.Squadra;

public interface CreaSquadreAvversarie {
    public List<Squadra> getSquadre() throws FileNotFoundException, ClassNotFoundException, IOException;
}

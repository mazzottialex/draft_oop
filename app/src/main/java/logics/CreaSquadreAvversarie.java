package logics;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

import data.SquadraAvversaria;

public interface CreaSquadreAvversarie {
	public List<SquadraAvversaria> getSquadre() throws FileNotFoundException, ClassNotFoundException, IOException;
}

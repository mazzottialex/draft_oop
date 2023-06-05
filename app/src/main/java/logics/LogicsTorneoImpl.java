package logics;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.*;

import data.Calciatore;
import data.Modulo;
import data.Squadra;
import data.SquadraAvversaria;
import data.SquadraUtente;
//import data.SquadraUtente;
import simulation.SimulatingMatchImpl;

/**
 * The class that manages the logic of the tournament.
 * @author Davide Braccini
 *
 */
public class LogicsTorneoImpl implements LogicsTorneo {

	private static final long serialVersionUID = 1L;
	//private ManageData md;
	//private ExtractData ex;
	private final Squadra miasquadra;
	private List<Squadra> listSquadre;
	//private List<Integer> golFatti;
	private int numSquadre;
	private final Map<String, Integer> risultati;
	//private final List<Calciatore> li;
	private boolean eliminated;
	private Squadra squadraAvv; // squadra al posto della squadra utente se vince 
	private final Map<String, Integer> risMatch; // ris della squadra al posto della squadra utente
	private String winner;
	private boolean elimThisTurn;
	
	/**
	 * Constructor of LogicsTorneoImpl.
	 * @param squadra the user's team 
	 * @param li the list of all the players in Serie A
	 * @throws FileNotFoundException 
	 * @throws ClassNotFoundException 
	 * @throws IOException 
	 */
	public LogicsTorneoImpl(final Squadra squadra, final List<Calciatore> li) 
			throws FileNotFoundException, ClassNotFoundException, IOException {

		this.listSquadre = new ArrayList<>();
		//this.golFatti = new ArrayList<>();
		//Creo la squadra dell'utente
		this.miasquadra = new SquadraUtente(squadra.getNomeSquadra(), squadra.getStemma(), 
				squadra.getModulo(), squadra.getTitolari(), squadra.getRiserve());
		//this.miasquadra = squadra;
		// Creo le squadre avversarie (quelle esistenti in serie A)
		/*
		this.listSquadre.add(new SquadraAvversaria(0, "NAP", Modulo.M442, li)); 
		this.listSquadre.add(new SquadraAvversaria(1, "LAZ", Modulo.M442, li)); 
		this.listSquadre.add(new SquadraAvversaria(2, "JUV", Modulo.M442, li)); 
		this.listSquadre.add(new SquadraAvversaria(3, "INT", Modulo.M442, li)); 
		this.listSquadre.add(new SquadraAvversaria(4, "MIL", Modulo.M442, li)); 
		this.listSquadre.add(new SquadraAvversaria(5, "ROM", Modulo.M442, li)); 
		this.listSquadre.add(new SquadraAvversaria(6, "ATA", Modulo.M442, li)); 
		this.listSquadre.add(new SquadraAvversaria(7, "FIO", Modulo.M442, li)); 
		this.listSquadre.add(new SquadraAvversaria(8, "UDI", Modulo.M442, li)); 
		this.listSquadre.add(new SquadraAvversaria(9, "MON", Modulo.M442, li)); 
		this.listSquadre.add(new SquadraAvversaria(10, "CRE", Modulo.M442, li)); 
		this.listSquadre.add(new SquadraAvversaria(11, "VER", Modulo.M442, li)); 
		this.listSquadre.add(new SquadraAvversaria(12, "BOL", Modulo.M442, li)); 
		this.listSquadre.add(new SquadraAvversaria(13, "TOR", Modulo.M442, li)); 
		this.listSquadre.add(new SquadraAvversaria(14, "SAL", Modulo.M442, li)); 
		*/
		//System.out.println(this.listSquadre);
		CreaSquadreAvversarieImpl cs = new CreaSquadreAvversarieImpl(li, 15);
		this.listSquadre.addAll(cs.getSquadre());
		
		this.numSquadre = 16;
		this.risultati = new HashMap<>();
		this.eliminated = false;
		this.squadraAvv = new SquadraAvversaria(1, "aaa", Modulo.M442, li);
		this.risMatch = new HashMap<>();
		this.elimThisTurn = false;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<Squadra> getListAvversari() {
		//return this.listSquadre;
		return List.copyOf(this.listSquadre);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Squadra getMiaSquadra() {
		//return this.miasquadra;
		final Squadra copy = new SquadraUtente(this.miasquadra.getNomeSquadra(), this.miasquadra.getStemma(),
				this.miasquadra.getModulo(), this.miasquadra.getTitolari(), this.miasquadra.getRiserve());
		return copy;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public int getNumSquadre() {
		return this.numSquadre;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void setNumSquadre(final int numSquadre) {
		this.numSquadre = numSquadre;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void setListAvversari(final List<Squadra> list) {
		this.listSquadre = new ArrayList<>(list);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void simulaMatch() {
		List<Squadra> newList = new ArrayList<>();
		final int numSquadre = this.getNumSquadre();
		Map<String, Integer> map = new HashMap<>(); //map per il risultato
		List<String> list = new ArrayList<>(); //lista per i nomi delle squadre che si sfidano
		String teamWin; //nome della squadra vincente
		String teamLose; //nome della squadra perdente
		Map<Squadra, Integer> map2;
		List<Squadra> l = new ArrayList<>(); // appoggio

		switch (numSquadre) {
		case 16: 
			
			for (int i = 1; i < numSquadre - 1; i = i + 2) {
				try {
					SimulatingMatchImpl s = new SimulatingMatchImpl(this.getListAvversari().get(i), 
							this.getListAvversari().get(i + 1));
					map.clear();
					map2 = s.risultato();
					l.clear();
					l.addAll(map2.keySet());
					map.put(l.get(0).getNomeSquadra(), map2.get(l.get(0)));
					map.put(l.get(1).getNomeSquadra(), map2.get(l.get(1)));
					l.clear();
					list.addAll(map.keySet());		
					if (map.get(list.get(0)).equals(map.get(list.get(1)))) { //== map.get(list.get(1))) {
						map.clear();
						map2 = s.risultatoSuppl();
						l.addAll(map2.keySet());
						map.put(l.get(0).getNomeSquadra(), map2.get(l.get(0)));
						map.put(l.get(1).getNomeSquadra(), map2.get(l.get(1)));
						l.clear();
						//map = s.risultatoSuppl2();
						list.clear();
					}			
					list.addAll(map.keySet());
					if (map.get(list.get(0)) > map.get(list.get(1))) {
						teamWin = list.get(0);
					} else if (map.get(list.get(0)) < map.get(list.get(1))) {
						teamWin = list.get(1);
					} else {
						LogicsRigoriImpl r = new LogicsRigoriImpl(this.getListAvversari().get(i), 
								this.getListAvversari().get(i + 1));
						teamWin = r.getWinner().getNomeSquadra();
						if (teamWin.equals(this.getListAvversari().get(i).getNomeSquadra())) {
							teamLose = this.getListAvversari().get(i + 1).getNomeSquadra();
						} else {
							teamLose = this.getListAvversari().get(i).getNomeSquadra();
						}
						map.clear();
						if (r.getGol1() > r.getGol2()) {
							map.put(teamWin, r.getGol1());
							map.put(teamLose, r.getGol2());
						} else {
							map.put(teamWin, r.getGol2());
							map.put(teamLose, r.getGol1());
						}
					}
					if (this.getListAvversari().get(i).getNomeSquadra().equals(teamWin)) {
						newList.add(this.getListAvversari().get(i));
					} else {
						newList.add(this.getListAvversari().get(i + 1));
					}
					list.clear();
					this.risultati.putAll(map);
				} catch (FileNotFoundException e) {
					e.printStackTrace();
				} catch (ClassNotFoundException e) {
					e.printStackTrace();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			this.setListAvversari(newList);
			this.setNumSquadre(8);
			break;
		case 8:
			this.risultati.clear();			
			if (this.getEliminated() && !this.getElimThisTurn()) {
				try {
					SimulatingMatchImpl s = new SimulatingMatchImpl(this.squadraAvv, this.getListAvversari().get(0));
					map2 = s.risultato();
					l.clear();
					l.addAll(map2.keySet());
					this.risMatch.put(l.get(0).getNomeSquadra(), map2.get(l.get(0)));
					this.risMatch.put(l.get(1).getNomeSquadra(), map2.get(l.get(1)));
					int r1 = map2.get(l.get(0));
					int r2 = map2.get(l.get(1));
					//l.clear();
					if (this.risMatch.get(l.get(0).getNomeSquadra()).equals(this.risMatch.get(l.get(1).getNomeSquadra()))) {
						map2 = s.risultatoSuppl();
						l.clear();
						l.addAll(map2.keySet());
						this.risMatch.clear();
						this.risMatch.put(l.get(0).getNomeSquadra(), map2.get(l.get(0)) + r1);
						this.risMatch.put(l.get(1).getNomeSquadra(), map2.get(l.get(1)) + r2);
						l.clear();
					}
					//this.risMatch = s.risultato2();	
				} catch (FileNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (ClassNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			for (int i = 1; i < numSquadre - 1; i = i + 2) {
				try {
					SimulatingMatchImpl s = new SimulatingMatchImpl(this.getListAvversari().get(i), 
							this.getListAvversari().get(i + 1));
					map.clear();
					map2 = s.risultato();
					l.clear();
					l.addAll(map2.keySet());
					map.put(l.get(0).getNomeSquadra(), map2.get(l.get(0)));
					map.put(l.get(1).getNomeSquadra(), map2.get(l.get(1)));
					l.clear();
					list.addAll(map.keySet());
					if (map.get(list.get(0)).equals(map.get(list.get(1)))) {
						map.clear();
						map2 = s.risultatoSuppl();
						l.addAll(map2.keySet());
						map.put(l.get(0).getNomeSquadra(), map2.get(l.get(0)));
						map.put(l.get(1).getNomeSquadra(), map2.get(l.get(1)));
						l.clear();
						list.clear();
					}
					list.addAll(map.keySet());
					if (map.get(list.get(0)) > map.get(list.get(1))) {
						teamWin = list.get(0);
					} else if (map.get(list.get(0)) < map.get(list.get(1))) {
						teamWin = list.get(1);
					} else {
						LogicsRigoriImpl r = new LogicsRigoriImpl(this.getListAvversari().get(i), 
								this.getListAvversari().get(i + 1));
						teamWin = r.getWinner().getNomeSquadra();
						if (teamWin.equals(this.getListAvversari().get(i).getNomeSquadra())) {
							teamLose = this.getListAvversari().get(i + 1).getNomeSquadra();
						} else {
							teamLose = this.getListAvversari().get(i).getNomeSquadra();
						}
						map.clear();
						if (r.getGol1() > r.getGol2()) {
							map.put(teamWin, r.getGol1());
							map.put(teamLose, r.getGol2());
						} else {
							map.put(teamWin, r.getGol2());
							map.put(teamLose, r.getGol1());
						}
					}
					if (this.getListAvversari().get(i).getNomeSquadra().equals(teamWin)) {
						newList.add(this.getListAvversari().get(i));
					} else {
						newList.add(this.getListAvversari().get(i + 1));
					}
					list.clear();
					this.risultati.putAll(map);
				} catch (FileNotFoundException e) {
					e.printStackTrace();
				} catch (ClassNotFoundException e) {
					e.printStackTrace();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			this.setListAvversari(newList);
			this.setNumSquadre(4);
			break;
		case 4:
			this.risultati.clear();
			
			if (this.getEliminated() && !this.getElimThisTurn()) {
				try {
					SimulatingMatchImpl s = new SimulatingMatchImpl(this.squadraAvv, 
							this.getListAvversari().get(0));
					//this.risMatch = s.risultato2();
					map2 = s.risultato();
					l.clear();
					l.addAll(map2.keySet());
					this.risMatch.put(l.get(0).getNomeSquadra(), map2.get(l.get(0)));
					this.risMatch.put(l.get(1).getNomeSquadra(), map2.get(l.get(1)));
					//l.clear();
					int r1 = map2.get(l.get(0));
					int r2 = map2.get(l.get(1));
					if (this.risMatch.get(l.get(0).getNomeSquadra()).equals(
							this.risMatch.get(l.get(1).getNomeSquadra()))) {
						map2 = s.risultatoSuppl();
						l.clear();
						l.addAll(map2.keySet());
						this.risMatch.clear();
						this.risMatch.put(l.get(0).getNomeSquadra(), map2.get(l.get(0)) + r1);
						this.risMatch.put(l.get(1).getNomeSquadra(), map2.get(l.get(1)) + r2);
						l.clear();
					}
				} catch (FileNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (ClassNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			try {
				SimulatingMatchImpl s  = new SimulatingMatchImpl(this.getListAvversari().get(1), 
						this.getListAvversari().get(2));
				map.clear();
				map2 = s.risultato();
				l.clear();
				l.addAll(map2.keySet());
				map.put(l.get(0).getNomeSquadra(), map2.get(l.get(0)));
				map.put(l.get(1).getNomeSquadra(), map2.get(l.get(1)));
				l.clear();
				list.addAll(map.keySet());
				if (map.get(list.get(0)).equals(map.get(list.get(1)))) {
					map.clear();
					map2 = s.risultatoSuppl();
					l.addAll(map2.keySet());
					map.put(l.get(0).getNomeSquadra(), map2.get(l.get(0)));
					map.put(l.get(1).getNomeSquadra(), map2.get(l.get(1)));
					l.clear();
					list.clear();
				}
				list.addAll(map.keySet());
				if (map.get(list.get(0)) > map.get(list.get(1))) {
					teamWin = list.get(0);
				} else if (map.get(list.get(0)) < map.get(list.get(1))) {
					teamWin = list.get(1);
				} else {
					LogicsRigoriImpl r = new LogicsRigoriImpl(this.getListAvversari().get(1), 
							this.getListAvversari().get(2));
					teamWin = r.getWinner().getNomeSquadra();
					if (teamWin.equals(this.getListAvversari().get(1).getNomeSquadra())) {
						teamLose = this.getListAvversari().get(2).getNomeSquadra();
					} else {
						teamLose = this.getListAvversari().get(1).getNomeSquadra();
					}
					map.clear();
					if (r.getGol1() > r.getGol2()) {
						map.put(teamWin, r.getGol1());
						map.put(teamLose, r.getGol2());
					} else {
						map.put(teamWin, r.getGol2());
						map.put(teamLose, r.getGol1());
					}
				}
				if (this.getListAvversari().get(1).getNomeSquadra().equals(teamWin)) {
					newList.add(this.getListAvversari().get(1));
				} else {
					newList.add(this.getListAvversari().get(2));
				}
				list.clear();
				this.risultati.putAll(map);
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}			
			
			this.setListAvversari(newList);	
			this.setNumSquadre(2);
			break;
		case 2:
			this.risultati.clear();
			
			if (this.getEliminated() && !this.getElimThisTurn()) {
				try {
					SimulatingMatchImpl s = new SimulatingMatchImpl(this.squadraAvv, 
							this.getListAvversari().get(0));
					map2 = s.risultato();
					l.clear();
					l.addAll(map2.keySet());
					this.risMatch.put(l.get(0).getNomeSquadra(), map2.get(l.get(0)));
					this.risMatch.put(l.get(1).getNomeSquadra(), map2.get(l.get(1)));
					//l.clear();
					int r1 = map2.get(l.get(0));
					int r2 = map2.get(l.get(1));
					if (r1 > r2) {
						this.winner = l.get(0).getNomeSquadra();
					} else if (r2 > r1) {
						this.winner = l.get(1).getNomeSquadra();
					} else {
						map2 = s.risultatoSuppl();
						l.clear();
						l.addAll(map2.keySet());
						this.risMatch.clear();
						this.risMatch.put(l.get(0).getNomeSquadra(), map2.get(l.get(0)) + r1);
						this.risMatch.put(l.get(1).getNomeSquadra(), map2.get(l.get(1)) + r2);
						
						int p1 = map2.get(l.get(0));
						int p2 = map2.get(l.get(1));
						if (p1 > p2) {
							this.winner = l.get(0).getNomeSquadra();
						} else if (p2 > p1) {
							this.winner = l.get(1).getNomeSquadra();
							//l.clear();
						} else {
							this.winner = new LogicsRigoriImpl(this.squadraAvv, 
									this.getListAvversari().get(0)).getWinner().getNomeSquadra();
						}
					}
				} catch (FileNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (ClassNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			
			this.setNumSquadre(1);
			break;
		default:
			break;
		}
		
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Map<String, Integer> getRisultati() {
		//return this.risultati;
		return Map.copyOf(this.risultati);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean getEliminated() {
		return this.eliminated;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void setEliminated(final boolean eliminated) {
		this.eliminated = eliminated;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Squadra getSquadraAvv() {
		//return this.squadraAvv;
		try {
			final SquadraAvversaria copy = new SquadraAvversaria(this.squadraAvv.getId(), this.squadraAvv.getNomeSquadra(),
					this.squadraAvv.getModulo(), this.squadraAvv.getLiCalciatori());
			return copy;
		} catch (ClassNotFoundException | IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void setSquadraAvv(final Squadra squadra) {
		//this.squadraAvv = squadra;
		try {
			this.squadraAvv = new SquadraAvversaria(squadra.getId(), squadra.getNomeSquadra(),
					squadra.getModulo(), squadra.getLiCalciatori());
		} catch (ClassNotFoundException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Map<String, Integer> getRisMatch() {
		//return this.risMatch;
		return Map.copyOf(this.risMatch);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public String getWinner() {
		return this.winner;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean getElimThisTurn() {
		return this.elimThisTurn;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void setElimThisTurn(final boolean b) {
		this.elimThisTurn = b;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void clearRisMatch() {
		this.risMatch.clear();
	}


}

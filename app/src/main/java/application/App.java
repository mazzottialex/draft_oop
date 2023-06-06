/*
 * This Java source file was generated by the Gradle 'init' task.
 */
package application;

import java.awt.EventQueue;
import java.awt.Frame;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

import javax.swing.SwingUtilities;

import data.Player;
import data.Module;
import data.Team;
import data.TeamOpponent;
import data.TeamUser;
import gui.Match;
import gui.Shootout;
import gui.Start;
import manageData.ManageData;
import manageData.ManageDataImpl;
import rating.CalcoloRating;
import rating.CalcoloRatingImpl;
//import v2.gui.TorneoV2;

public class App {
    public String getGreeting() {
        return "Hello World!";
    }

    public static void main(String[] args) throws FileNotFoundException, ClassNotFoundException, IOException {
    	
    	EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Start frame = new Start();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}

		});
    	
/*
    	ManageData md = new ManageDataImpl("2022-2023");
		md.LoadData();
		List<Calciatore> li = md.getLi();
		CalcoloRating cr = new CalcoloRatingImpl(li);
		li = cr.updateRating();
		Squadra nap = new SquadraAvversaria(0, "NAP", Modulo.M442, li);	
    	Torneo t= new Torneo(nap, li);
    	t.setVisible(true);

    	*//*
    	ManageData md = new ManageDataImpl("2022-2023");
		md.LoadData();
		List<Calciatore> li = md.getLi();
		CalcoloRating cr = new CalcoloRatingImpl(li);
		li = cr.updateRating();
		Squadra nap = new SquadraAvversaria(0, "NAP", Modulo.M442, li);	
		Squadra ut = new SquadraUtente(null, null, Modulo.M442, nap.getTitolari(), nap.getRiserve());
		Squadra laz = new SquadraAvversaria(0, "LAZ", Modulo.M442, li);

		SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
					try {
						Partita p = new Partita(ut, laz);
						p.createAndShowGUI();
					} catch (ClassNotFoundException | IOException e) {
						e.printStackTrace();
					}
//					Rigori r = new Rigori(nap, laz, null);
//					r.createAndShowGUI();
            }
		});
		*/
    }
}

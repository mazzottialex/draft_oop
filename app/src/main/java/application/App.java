/*
 * This Java source file was generated by the Gradle 'init' task.
 */
package application;

import java.awt.EventQueue;
import java.awt.Frame;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.util.ArrayList;
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
import logics.LogicsArchive;
import logics.LogicsArchiveImpl;
import managedata.ManageData;
import managedata.ManageDataImpl;
import rating.AnalysisRating;
import rating.AnalysisRatingImpl;
import simulation.SimulatingMatch;
//import v2.gui.TorneoV2;
import simulation.SimulatingMatchImpl;

public class App {
    public String getGreeting() {
        return "Hello World!";
    }

    @SuppressWarnings("unchecked")
	public static void main(String[] args) throws Exception {
   
/*
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
    	*/
/*
    	ManageData md = new ManageDataImpl("2022-2023");
		md.LoadData();
		List<Calciatore> li = md.getLi();
		CalcoloRating cr = new CalcoloRatingImpl(li);
		li = cr.updateRating();
		Squadra nap = new SquadraAvversaria(0, "NAP", Modulo.M442, li);	
    	Torneo t= new Torneo(nap, li);
    	t.setVisible(true);
*/
    	
    	ManageData md = new ManageDataImpl("2022-2023");
		md.loadData();
		List<Player> li = md.getLi();
		AnalysisRating cr = new AnalysisRatingImpl(li);
		li = cr.updateRating();
		Team nap = new TeamOpponent(0, "NAP", Module.M442, li);	
		Team ut = new TeamUser(null, null, Module.M442, nap.getStarting(), nap.getSubstitution());
		Team laz = new TeamOpponent(0, "LAZ", Module.M442, li);
		Team sam = new TeamOpponent(0, "SAM", Module.M442, li);
        System.out.println(new SimulatingMatchImpl(nap, laz).result(0));
        /*
		SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
					
						Match p;
						try {
							p = new Match(ut, nap);
							p.createAndShowGUI();
						} catch (ClassNotFoundException | IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						
					
//					Rigori r = new Rigori(nap, laz, null);
//					r.createAndShowGUI();
            }
		});
		*/
    	/*
    	LogicsArchive la = new LogicsArchiveImpl("2022-2023", true);
    	ManageData md = new ManageDataImpl("2022-2023");
		md.loadData();
		List<Player> li = md.getLi();
		AnalysisRating cr = new AnalysisRatingImpl(li);
		li = cr.updateRating();
    	la.liOrdered(li);*/
    	
    	Team t1;
        Team t2;
        SimulatingMatch sm;
    	Player gk = new Player(23, "I. Provedel", "P", "LAZ", 38, 3412, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 100);
        Player def1 = new Player(0, "Danilo", "D", "JUV", 37, 3182, 3, 32, 7, 3, 1764, 19, 0, 0, 109, 92, 20, 0);
        Player def2 = new Player(1, "G. Di Lorenzo", "D", "NAP", 37, 3257, 3, 31, 11, 4, 1966, 47, 0, 0, 59, 60, 18, 0);
        Player def3 = new Player(2, "Kim Min-Jae", "D", "NAP", 35, 3054, 2, 8, 4, 2, 2548, 10, 5, 0, 71, 55, 15, 0);
        Player def4 = new Player(11, "Carlos Augusto", "D", "MON", 35, 3087, 6, 42, 32, 5, 1169, 26, 4, 0, 62, 73, 9, 0);
        Player mid1 = new Player(6, "S. Milinkovic-Savic", "C", "LAZ", 36, 3028, 9, 64, 35, 8, 1447, 42, 0, 0, 63, 61, 18, 0);
        Player mid2 = new Player(7, "A. Zambo Anguissa", "C", "NAP", 36, 3059, 3, 50, 33, 5, 1977, 33, 0, 0, 82, 53, 15, 0);
        Player mid3 = new Player(14, "A. Rabiot", "C", "JUV", 32, 2751, 8, 48, 25, 4, 950, 31, 9, 0, 61, 65, 17, 0);
        Player for1 = new Player(4, "K. Kvaratskhelia", "A", "NAP", 34, 2539, 12, 85, 74, 10, 869, 54, 0, 0, 29, 43, 14, 0);
        Player for2 = new Player(3, "M. Lautaro", "A", "INT", 38, 2576, 21, 127, 28, 6, 498, 46, 0, 0, 27, 25, 9, 0);
        Player for3 = new Player(10, "R. Leao", "A", "MIL", 35, 2429, 15, 92, 70, 8, 552, 49, 5, 1, 22, 8, 9, 0);
        Player gk0 = new Player(524, "F. Marchetti", "P", "SPE", 1, 65, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 1);
        Player def01 = new Player(523, "L. Tonelli", "D", "EMP", 1, 18, 0, 0, 0, 0, 5, 0, 0, 1, 0, 0, 0, 0);
        Player def02 = new Player(521, "R. Nizet", "D", "LEC", 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0);
        Player def03 = new Player(520, "M. Lemmens", "D", "LEC", 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0);
        Player def04 = new Player(516, "D. Keramitsis", "D", "ROM", 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0);
        Player mid01 = new Player(519, "D. Samek", "C", "LEC", 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0);
        Player mid02 = new Player(476, "V. Carboni", "C", "INT", 5, 21, 0, 0, 1, 0, 12, 1, 0, 0, 1, 1, 0, 0);
        Player mid03 = new Player(511, "E. Darboe", "C", "ROM", 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0);
        Player for01 = new Player(517, "M. Maric", "A", "MON", 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0);
        Player for02 = new Player(515, "Kaio Jorge", "A", "JUV", 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0);
        Player for03 = new Player(514, "M. Ake", "A", "JUV", 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0);
        Player gks1 = new Player(51, "A. Meret", "P", "NAP", 34, 3060, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 69);
        Player defs1 = new Player(13, "A. Romagnoli", "D", "LAZ", 34, 2925, 2, 13, 1, 0, 1671, 2, 6, 0, 53, 33, 16, 0);
        Player defs2 = new Player(18, "R. Ibanez", "D", "ROM", 33, 2796, 3, 29, 12, 0, 1447, 6, 9, 1, 99, 67, 10, 0);
        Player mids1 = new Player(12, "S. Lobotka", "C", "NAP", 38, 3111, 1, 11, 18, 1, 2160, 17, 2, 0, 67, 84, 15, 0);
        Player mids2 = new Player(15, "M. Pessina", "C", "MON", 35, 2938, 5, 28, 33, 3, 1701, 22, 6, 0, 73, 57, 10, 0);
        Player fors1 = new Player(5, "V. Osimhen", "A", "NAP", 32, 2583, 26, 135, 24, 4, 263, 34, 4, 0, 12, 10, 16, 0);
        Player fors2 = new Player(9, "Felipe Anderson", "A", "LAZ", 38, 2967, 9, 41, 54, 2, 1064, 47, 3, 0, 81, 80, 19, 0);
        Player gks0 = new Player(522, "D. Vasquez", "P", "MIL", 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0);
        Player defs01 = new Player(488, "F. Romagna", "D", "SAS", 2, 46, 0, 0, 0, 0, 34, 0, 0, 0, 0, 0, 0, 0);
        Player defs02 = new Player(489, "M. Muldur", "D", "SAS", 2, 13, 0, 0, 0, 0, 3, 0, 0, 0, 0, 0, 0, 0);
        Player mids01 = new Player(485, "K. Zedadka", "C", "NAP", 3, 18, 0, 0, 0, 0, 8, 0, 0, 0, 0, 0, 0, 0);
        Player mids02 = new Player(466, "A. Hrustic", "C", "VER", 6, 226, 0, 3, 3, 0, 73, 3, 0, 0, 1, 2, 0, 0);
        Player fors01 = new Player(469, "D. Montevago", "A", "SAM", 6, 227, 0, 7, 0, 0, 23, 4, 1, 0, 0, 1, 0, 0);
        Player fors02 = new Player(463, "I. Pussetto", "A", "SAM", 5, 150, 0, 2, 1, 0, 34, 2, 1, 0, 3, 6, 0, 0);
        t1 = new TeamUser("t1", null, Module.M433, List.of(gk, def1, def2, def3, def4, mid1, mid2, mid3, for1, for2, for3), List.of(gks1, defs1, defs2, mids1, mids2, fors1, fors2));
        t2 = new TeamUser("t2", null, Module.M433, List.of(gk0, def01, def02, def03, def04, mid01, mid02, mid03, for01, for02, for03), List.of(gks0, defs01, defs02, mids01, mids02, fors01, fors02));
        sm = new SimulatingMatchImpl(t1, laz);
        System.out.println(sm.result(0));
    }
}

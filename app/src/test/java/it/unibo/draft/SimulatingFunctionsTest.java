package it.unibo.draft;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import data.Module;
import data.Player;
import data.Team;
import data.TeamUser;
import simulation.SimulatingFunctionsImpl;

class SimulatingFunctionsImplTest {

//    private SimulatingFunctionsImpl sf;
//    private Team team;
//    List<Player> liStarting;
//    List<Player> liSubstitution;
//    Map<Player, Double> fantasyRatings;
//    private static final double COST_VOTE_P = 0.0746835443037975; // costante calcolo voto portiere
//    private static final double COST_VOTE_D = 0.0721518987341772; // costante calcolo voto difensore
//    private static final double COST_VOTE_C = 0.0753086419753086; // costante calcolo voto centrocampista
//    private static final double COST_VOTE_A = 0.0729411764705882; // costante calcolo voto attaccante
//
//    @Before
//    public void setUp() {
//        sf = new SimulatingFunctionsImpl();
//        Player gk = new Player(23, "I. Provedel", "P", "LAZ", 38, 3412, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 100);
//        Player def1 = new Player(0, "Danilo", "D", "JUV", 37, 3182, 3, 32, 7, 3, 1764, 19, 0, 0, 109, 92, 20, 0);
//        Player def2 = new Player(1, "G. Di Lorenzo", "D", "NAP", 37, 3257, 3, 31, 11, 4, 1966, 47, 0, 0, 59, 60, 18, 0);
//        Player def3 = new Player(2, "Kim Min-Jae", "D", "NAP", 35, 3054, 2, 8, 4, 2, 2548, 10, 5, 0, 71, 55, 15, 0);
//        Player def4 = new Player(11, "Carlos Augusto", "D", "MON", 35, 3087, 6, 42, 32, 5, 1169, 26, 4, 0, 62, 73, 9, 0);
//        Player mid1 = new Player(6, "S. Milinkovic-Savic", "C", "LAZ", 36, 3028, 9, 64, 35, 8, 1447, 42, 0, 0, 63, 61, 18, 0);
//        Player mid2 = new Player(7, "A. Zambo Anguissa", "C", "NAP", 36, 3059, 3, 50, 33, 5, 1977, 33, 0, 0, 82, 53, 15, 0);
//        Player mid3 = new Player(14, "A. Rabiot", "C", "JUV", 32, 2751, 8, 48, 25, 4, 950, 31, 9, 0, 61, 65, 17, 0);
//        Player for1 = new Player(4, "K. Kvaratskhelia", "A", "NAP", 34, 2539, 12, 85, 74, 10, 869, 54, 0, 0, 29, 43, 14, 0);
//        Player for2 = new Player(3, "M. Lautaro", "A", "INT", 38, 2576, 21, 127, 28, 6, 498, 46, 0, 0, 27, 25, 9, 0);
//        Player for3 = new Player(10, "R. Leao", "A", "MIL", 35, 2429, 15, 92, 70, 8, 552, 49, 5, 1, 22, 8, 9, 0);
//        
//        Player gk0 = new Player(51, "A. Meret", "P", "NAP", 34, 3060, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 69);
//        Player def01 = new Player(13, "A. Romagnoli", "D", "LAZ", 34, 2925, 2, 13, 1, 0, 1671, 2, 6, 0, 53, 33, 16, 0);
//        Player def02 = new Player(18, "R. Ibanez", "D", "ROM", 33, 2796, 3, 29, 12, 0, 1447, 6, 9, 1, 99, 67, 10, 0);
//        Player mid01 = new Player(12, "S. Lobotka", "C", "NAP", 38, 3111, 1, 11, 18, 1, 2160, 17, 2, 0, 67, 84, 15, 0);
//        Player mid02 = new Player(15, "M. Pessina", "C", "MON", 35, 2938, 5, 28, 33, 3, 1701, 22, 6, 0, 73, 57, 10, 0);
//        Player for01 = new Player(5, "V. Osimhen", "A", "NAP", 32, 2583, 26, 135, 24, 4, 263, 34, 4, 0, 12, 10, 16, 0);
//        Player for02 = new Player(9, "Felipe Anderson", "A", "LAZ", 38, 2967, 9, 41, 54, 2, 1064, 47, 3, 0, 81, 80, 19, 0);
//        
//        liStarting.add(gk); liStarting.add(def1); liStarting.add(def2); liStarting.add(def3); liStarting.add(def4); liStarting.add(mid1);
//        liStarting.add(mid2); liStarting.add(mid3); liStarting.add(for1); liStarting.add(for2); liStarting.add(for3);
//        liSubstitution.add(gk0); liSubstitution.add(def01); liSubstitution.add(def02); liSubstitution.add(mid01); liSubstitution.add(mid02);
//        liSubstitution.add(for01); liSubstitution.add(for02);
////        liStarting = List.of(gk, def1, def2, def3, def4, mid1, mid2, mid3, for1, for2, for3);
////        liSubstitution = List.of(gk0, def01, def02, mid01, mid02, for01, for02);
//        team = new TeamUser("test", null, Module.M433, liStarting, liSubstitution);
//        fantasyRatings = new HashMap<>();
//        fantasyRatings.put(gk, /*COST_VOTE_P * gk.getRating().getX()*/6.5);
//        fantasyRatings.put(def1, /*COST_VOTE_D * def1.getRating().getX()*/7.0);
//        fantasyRatings.put(def2, /*COST_VOTE_D * def2.getRating().getX()*/6.0);
//        fantasyRatings.put(def3, /*COST_VOTE_D * def3.getRating().getX()*/8.0);
//        fantasyRatings.put(def4, /*COST_VOTE_D * def4.getRating().getX()*/6.5);
//        fantasyRatings.put(mid1, /*COST_VOTE_C * mid1.getRating().getX()*/5.5);
//        fantasyRatings.put(mid2, /*COST_VOTE_C * mid2.getRating().getX()*/5.0);
//        fantasyRatings.put(mid3, /*COST_VOTE_C * mid3.getRating().getX()*/7.5);
//        fantasyRatings.put(for1, /*COST_VOTE_A * for1.getRating().getX()*/9.0);
//        fantasyRatings.put(for2, /*COST_VOTE_A * for2.getRating().getX()*/8.5);
//        fantasyRatings.put(for3, /*COST_VOTE_A * for3.getRating().getX()*/5.5);
//    }
//
//    @Test
//    void testGetLockdownDefenseRating() {
//        double expected = 4.5; // Calcolo manuale: 1.0 + 7.0 + 6.0 + 8.0 + 6.5 - (4 * 6.0)
//        double actual = sf.getLockdownDefenseRating(team, fantasyRatings);
//
//        assertEquals(expected, actual, 0.001);
//    }
//
//    @Test
//    void testModifiedFantasyRatings() {
//        Map<String, Double> expected = new HashMap<>();
//        expected.put("P", 6.5); 
//        expected.put("D", 27.5); // 7.0 + 6.0 + 8.0 + 6.5
//        expected.put("C", 18.0); // 5.5 + 5.0 + 7.5
//        expected.put("A", 23.0); // 9.0 + 8.5 + 5.5
//        Map<String, Double> actual = sf.modifiedFantasyRatings(team, fantasyRatings);
//
//        assertEquals(expected, actual);
//    }
//
//    @Test
//    void testGetFantasyDefensiveRating() throws FileNotFoundException, ClassNotFoundException, IOException {
//        double expected = (3.0 * (6.5 + 27.5)) + (1.0 * 18.0) + (0.5 * 23.0);
//        double actual = sf.getFantasyDefensiveRating(team, sf.modifiedFantasyRatings(team, fantasyRatings));
//
//        assertEquals(expected, actual, 0.001);
//    }
//
//    @Test
//    void testGetFantasyOffensiveRating() throws FileNotFoundException, ClassNotFoundException, IOException {
//        double expected = (0.5 * 27.5) + (4.0 * 18.0) + (2.5 * 23.0);
//        double actual = sf.getFantasyOffensiveRating(team, sf.modifiedFantasyRatings(team, fantasyRatings));
//
//        assertEquals(expected, actual, 0.001);
//    }

}

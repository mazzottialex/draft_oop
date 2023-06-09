package it.unibo.draft;


import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import data.Player;
import managedata.ExtractData;
import managedata.ExtractDataImpl;
import rating.AnalysisRatingImpl;
import rating.Function;

public class AnalysisRatingTest {

    private AnalysisRatingImpl analysisRating;
    private final List<Player> li = new ArrayList<>();
    private Function fun = new Function();
    private ExtractData ex;
    
    @Before
    public void setUp() {
        li.add(new Player(0, "Danilo", "D", "JUV", 37, 3182, 3, 32, 7, 3, 1764, 19, 0, 0, 109, 92, 20, 0));
        li.add(new Player(1, "G. Di Lorenzo", "D", "NAP", 37, 3257, 3, 31, 11, 4, 1966, 47, 0, 0, 59, 60, 18, 0));
        li.add(new Player(2, "Kim Min-Jae", "D", "NAP", 35, 3054, 2, 8, 4, 2, 2548, 10, 0, 0, 71, 55, 15, 0));
        li.add(new Player(3, "M. Lautaro", "A", "INT", 38, 2576, 21, 127, 28, 6, 498, 46, 0, 0, 27, 25, 9, 0));
        li.add(new Player(4, "K. Kvaratskhelia", "A", "NAP", 34, 2539, 12, 85, 74, 10, 869, 54, 0, 0, 29, 43, 14, 0));
        li.add(new Player(5, "V. Osimhen", "A", "NAP", 32, 2583, 26, 135, 24, 4, 263, 34, 0, 0, 12, 10, 16, 0));
        li.add(new Player(6, "S. Milinkovic-Savic", "C", "LAZ", 36, 3028, 9, 64, 35, 8, 1447, 42, 0, 0, 63, 61, 18, 0));
        li.add(new Player(7, "A. Zambo Anguissa", "C", "NAP", 36, 3059, 3, 50, 33, 5, 1977, 33, 0, 0, 82, 53, 15, 0));
        li.add(new Player(8, "M. Zaccagni", "A", "LAZ", 35, 2782, 10, 57, 60, 6, 824, 35, 0, 0, 49, 43, 16, 0));
        li.add(new Player(9, "Felipe Anderson", "A", "LAZ", 38, 2967, 9, 41, 54, 2, 1064, 47, 0, 0, 81, 80, 19, 0));
        li.add(new Player(10, "R. Leao", "A", "MIL", 35, 2429, 15, 92, 70, 8, 552, 49, 0, 0, 22, 8, 9, 0));
        li.add(new Player(23, "I. Provedel", "P", "LAZ", 38, 3412, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 100));
        li.add(new Player(51, "A. Meret", "P", "NAP", 34, 3060, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 69));
        ex = new ExtractDataImpl(li);
    }

    @Test
    public void testUpdateRating() {
        assertNull(li.get(0).getRating());
        analysisRating = new AnalysisRatingImpl(li);
        analysisRating.updateRating();
        assertNotNull(li.get(0).getRating());
        for(Player p : li) {
        	assertTrue(p.getRating().getX() >= 50 && p.getRating().getX() <= 100);
        	assertTrue(p.getRating().getY().getX() >= 50 && p.getRating().getY().getX() <= 100);
        	assertTrue(p.getRating().getY().getY() >= 50 && p.getRating().getY().getY() <= 100);
        	assertTrue(p.getRating().getY().getZ() >= 50 && p.getRating().getY().getZ() <= 100);
        }
    }
    
    @Test
    public void testFunction() {
        assertEquals(fun.linear(50, 100, 0, 100), 50);
        
        assertTrue(fun.linear(0, 100, 0, 100) == fun.logarithmic(0, 100, 0, 100)  );
        
        assertTrue(fun.linear(100, 100, 0, 100) == fun.logarithmic(100, 100, 0, 100) );
        
        assertTrue(fun.linear(50, 100, 0, 100)<fun.logarithmic(50, 100, 0, 100));
    }
    
    @Test
    public void testExtract() {
    	assertEquals(ex.getTopByAttribute(c -> c.getGoals()), 26);
    	System.out.print(ex.getTopByAttribute(c -> c.getRedCards(), c -> c.getMinutes()));
    	assertEquals(ex.getTopByAttribute(c -> c.getGoals(), c -> c.getMinutes()), 0);
    }
}

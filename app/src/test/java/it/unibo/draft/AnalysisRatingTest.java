package it.unibo.draft;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import model.data.Player;
import model.managedata.ExtractData;
import model.managedata.ExtractDataImpl;
import model.rating.AnalysisRatingImpl;
import model.rating.Function;
/**
 * Test AnalysisRating.
 *
 */
public class AnalysisRatingTest {

    private AnalysisRatingImpl analysisRating;
    private List<Player> li = new ArrayList<>();
    private final Function fun = new Function();
    private ExtractData ex;
    private static final int MAX_RATING = 100;
    private static final int MIN_RATING = 50;
    private static final int MIN_RATING_T2 = 0;
    private static final int TEST1 = 50;
    private static final int TEST2 = 0;
    private static final int TEST3 = 100;
    private static final int TEST4 = 50;
    private static final int TOPGOL = 26;

    private final Player p1 = new Player(0, "Danilo", "D", "JUV",
            37, 3182, 3, 32, 7, 3, 1764, 19, 0, 0, 109, 92, 20, 0);
    private final Player p2 = new Player(1, "G. Di Lorenzo", "D", "NAP",
            37, 3257, 3, 31, 11, 4, 1966, 47, 0, 0, 59, 60, 18, 0);
    private final Player p3 = new Player(2, "Kim Min-Jae", "D", "NAP",
            35, 3054, 2, 8, 4, 2, 2548, 10, 0, 0, 71, 55, 15, 0);
    private final Player p4 = new Player(3, "M. Lautaro", "A", "INT",
            38, 2576, 21, 127, 28, 6, 498, 46, 0, 0, 27, 25, 9, 0);
    private final Player p5 = new Player(4, "K. Kvaratskhelia", "A", "NAP",
            34, 2539, 12, 85, 74, 10, 869, 54, 0, 0, 29, 43, 14, 0);
    private final Player p6 = new Player(5, "V. Osimhen", "A", "NAP",
            32, 2583, 26, 135, 24, 4, 263, 34, 0, 0, 12, 10, 16, 0);
    private final Player p7 = new Player(6, "S. Milinkovic-Savic", "C", "LAZ",
            36, 3028, 9, 64, 35, 8, 1447, 42, 0, 0, 63, 61, 18, 0);
    private final Player p8 = new Player(7, "A. Zambo Anguissa", "C", "NAP",
            36, 3059, 3, 50, 33, 5, 1977, 33, 0, 0, 82, 53, 15, 0);
    private final Player p9 = new Player(8, "M. Zaccagni", "A", "LAZ",
            35, 2782, 10, 57, 60, 6, 824, 35, 0, 0, 49, 43, 16, 0);
    private final Player p10 = new Player(9, "Felipe Anderson", "A", "LAZ",
            38, 2967, 9, 41, 54, 2, 1064, 47, 0, 0, 81, 80, 19, 0);
    private final Player p11 = new Player(10, "R. Leao", "A", "MIL",
            35, 2429, 15, 92, 70, 8, 552, 49, 0, 0, 22, 8, 9, 0);
    private final Player p12 = new Player(23, "I. Provedel", "P", "LAZ",
            38, 3412, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 100);
    private final Player p13 = new Player(51, "A. Meret", "P", "NAP",
            34, 3060, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 69);
    /**
     * Sets up the necessary objects and data for the tests.
     */
    @Before
    public void setUp() {
        li = List.of(p1, p2, p3, p4, p5, p6, p7, p8, p9, p10, p11, p12, p13);
        ex = new ExtractDataImpl(li);
    }
    /**
     * Tests the updateRating method of AnalysisRatingImpl class.
     */
    @Test
    public void testUpdateRating() {
        assertNull(li.get(0).getRating());
        analysisRating = new AnalysisRatingImpl(li);
        analysisRating.updateRating();
        assertNotNull(li.get(0).getRating());
        for (Player p : li) {
            assertTrue(p.getRating().getX() >= MIN_RATING && p.getRating().getX() <= MAX_RATING);
            assertTrue(p.getRating().getY().getX() >= MIN_RATING && p.getRating().getY().getX() <= MAX_RATING);
            assertTrue(p.getRating().getY().getY() >= MIN_RATING && p.getRating().getY().getY() <= MAX_RATING);
            assertTrue(p.getRating().getY().getZ() >= MIN_RATING && p.getRating().getY().getZ() <= MAX_RATING);
        }
    }
    /**
     * Tests the linear and logarithmic methods of the Function class.
     */
    @Test
    public void testFunction() {
        assertEquals(fun.linear(TEST1, MAX_RATING, MIN_RATING_T2, MAX_RATING), TEST1);
        assertTrue(fun.linear(TEST2, MAX_RATING, MIN_RATING_T2, MAX_RATING) 
                == fun.logarithmic(TEST2, MAX_RATING, MIN_RATING_T2, MAX_RATING));
        assertTrue(fun.linear(TEST3, MAX_RATING, MIN_RATING_T2, MAX_RATING) 
                == fun.logarithmic(TEST3, MAX_RATING, MIN_RATING_T2, MAX_RATING));
        assertTrue(fun.linear(TEST4, MAX_RATING, MIN_RATING_T2, MAX_RATING) 
                < fun.logarithmic(TEST4, MAX_RATING, MIN_RATING_T2, MAX_RATING));
    }
    /**
     * Tests the getTopByAttribute method of the ExtractData interface.
     */
    @Test
    public void testExtract() {
        assertEquals(ex.getTopByAttribute(c -> c.getGoals()), TOPGOL);
    }
}

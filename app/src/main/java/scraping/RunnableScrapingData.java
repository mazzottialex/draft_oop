package scraping;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import data.Player;

/**
 * A class that represents a thread for scraping player data.
 */
public class RunnableScrapingData implements Runnable {
    private final int myId;
    private final int nThread;
    private final List<Player> li = new ArrayList<>();
    private final String url;
    private Boolean flag = true;
    private Boolean flagChrome;
    private static final int PG = 7;
    private static final int MINUTES = 9;
    private static final int GOALS = 10;
    private static final int SHOOTS = 11;
    private static final int DRIBLINGS = 14;
    private static final int ASSISTS = 15;
    private static final int PASSES = 16;
    private static final int KEY_PASSES = 17;
    private static final int YELLOW_CARDS = 20;
    private static final int RED_CARDS = 21;
    private static final int STEALS = 22;
    private static final int TACKLE = 23;
    private static final int CLEAN_SHEET = 24;
    private static final int SAVEDS = 25;
    private static final int COST_ID = 15;
    private static final int SECONDS = 30;

    /**
     * Creates an instance of RunnableScrapingData.
     *
     * @param myId       The ID of the current thread
     * @param nThread    The total number of threads
     * @param season   The season to scrape data for
     * @param flagChrome A flag indicating whether to use the Chrome or Firefox driver
     */
    public RunnableScrapingData(final int myId, final int nThread, final String season, final Boolean flagChrome) {
        this.myId = myId;
        this.nThread = nThread;
        this.url =
            "https://www.kickest.it/it/serie-a/statistiche/giocatori/tabellone/" + season + "?iframe=yes";
        this.flagChrome = flagChrome;
    }

    /**
     * Returns the list of players.
     *
     * @return The list of players.
     */
    public List<Player> getLi() {
        return li;
    }

    /**
     * Check if it finishes correctly.
     *
     * @return True if the flag is correctly, False otherwise
     */
    public Boolean check() {
        return flag;
    }

    /**
     * Executes the thread.
     */
    public void run() {
        final WebDriver driver;
        if (flagChrome) {
            final ChromeOptions options = new ChromeOptions();
            options.addArguments("headless");
            //Oggetto per creare il collegamento
            driver = new ChromeDriver(options);
        } else {
            final FirefoxOptions options = new FirefoxOptions();
            options.addArguments("-headless");
            driver = new FirefoxDriver(options);
        }
        driver.get(url);
        //Oggetto per eseguire operazioni sulla pagina
        final JavascriptExecutor js = (JavascriptExecutor) driver;
        //Attende che la pagina carichi la tabella
        //aspetta 30 sec per caricare la tabella
        final WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(SECONDS).getSeconds());
        try {
            wait.until(ExpectedConditions.presenceOfElementLocated(By.tagName("tr")));
        } catch (org.openqa.selenium.TimeoutException e) {
            this.flag = false;
        }
        if (this.flag) {
            int last = Integer.parseInt(driver.findElement(By.className("paginationjs-last")).getText());
            final int pagine = (int) Math.ceil((last) / nThread) + 1;
            if (pagine * (myId + 1) < last) {
                last = pagine * (myId + 1);
            }
            for (Integer i = 1; i < last + 1; i++) {
                js.executeScript("document.querySelector('[data-num=\"" + i.toString() + "\"]').click()");
                if (i > myId * pagine) {
                    System.out.println(myId + "  i:" + i);
                    WebElement table = driver.findElement(By.tagName("tbody"));
                    final int nRighe = table.findElements(By.tagName("tr")).size();
                    for (int j = 1; j < nRighe + 1; j++) {
                        final List<WebElement> riga =
                            driver.findElements(By.tagName("tr")).get(j).findElements(By.tagName("td"));
                        final String ruolo = riga.get(2).getText().substring(0, 1); //ruolo
                        if (ruolo.equals("P")) {
                            li.add(new Player((i - 1) * COST_ID + (j - 1), //id 	Integer.parseInt(riga.get(0).getText())
                                riga.get(1).getText(), //nome
                                ruolo, //ruolo
                                riga.get(3).getText(), //squadra
                                Integer.parseInt(riga.get(PG).getText()), //pg
                                Integer.parseInt(riga.get(MINUTES).getText()), //minuti
                                0, //gol
                                0, //tiri
                                0, //dribling
                                0, //assist
                                0, //passaggi
                                0, //passaggiChiave
                                Integer.parseInt(riga.get(YELLOW_CARDS).getText()), //ammonizioni
                                Integer.parseInt(riga.get(RED_CARDS).getText()), //espulsioni
                                0, //rubati
                                0, //tackle
                                Integer.parseInt(riga.get(TACKLE).getText()), //cleanSheet
                                Integer.parseInt(riga.get(SAVEDS).getText()) //parate
                            ));
                        } else {
                            li.add(new Player(
                                (i - 1) * COST_ID + (j - 1), //id 	Integer.parseInt(riga.get(0).getText())
                                riga.get(1).getText(), //nome
                                ruolo, //ruolo
                                riga.get(3).getText(), //squadra
                                Integer.parseInt(riga.get(PG).getText()), //pg
                                Integer.parseInt(riga.get(MINUTES).getText()), //minuti
                                Integer.parseInt(riga.get(GOALS).getText()), //gol
                                Integer.parseInt(riga.get(SHOOTS).getText()), //tiri
                                Integer.parseInt(riga.get(DRIBLINGS).getText()), //dribling
                                Integer.parseInt(riga.get(ASSISTS).getText()), //assist
                                Integer.parseInt(riga.get(PASSES).getText()), //passaggi
                                Integer.parseInt(riga.get(KEY_PASSES).getText()), //passaggiChiave
                                Integer.parseInt(riga.get(YELLOW_CARDS).getText()), //ammonizioni
                                Integer.parseInt(riga.get(RED_CARDS).getText()), //espulsioni
                                Integer.parseInt(riga.get(STEALS).getText()), //rubati
                                Integer.parseInt(riga.get(TACKLE).getText()), //tackle
                                Integer.parseInt(riga.get(CLEAN_SHEET).getText()), //cleanSheet
                                0 //parate
                            ));
                        }
                    }
                }
            }
        }
        driver.quit();
    }
}

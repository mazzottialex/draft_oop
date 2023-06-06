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
 * A class that represents a thread for scraping player data
 */
public class RunnableScrapingData implements Runnable {
    private final int myId;
    private final int nThread;
    private final List<Player> li = new ArrayList<>();
    private final String url;
    private Boolean flag = true;
    private Boolean flagChrome;
    
    /**
     * Creates an instance of RunnableScrapingData
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
     * Check if it finishes correctly
     *
     * @return True if the flag is correctly, False otherwise
     */
    public Boolean check() {
        return flag;
    }
    
    /**
     * Executes the thread
     */
    public void run() {
    	final WebDriver driver;
    	if(flagChrome) {
	    	final ChromeOptions options = new ChromeOptions();
	        options.addArguments("headless");
	        //Oggetto per creare il collegamento
	        driver = new ChromeDriver(options);
    	}
    	else {
    		final FirefoxOptions options = new FirefoxOptions();
            options.addArguments("-headless");
            driver = new FirefoxDriver(options);
    	}
        driver.get(url);
        //Oggetto per eseguire operazioni sulla pagina
        final JavascriptExecutor js = (JavascriptExecutor) driver;
        //Attende che la pagina carichi la tabella
        final WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30).getSeconds()); //aspetta 30 sec per caricare la tabella
        try {
            wait.until(ExpectedConditions.presenceOfElementLocated(By.tagName("tr")));
        } catch (org.openqa.selenium.TimeoutException e) {
            this.flag = false;
        }
        if (this.flag) {
        	int last = Integer.parseInt(driver.findElement(By.className("paginationjs-last")).getText());
        	final int pagine = (int) Math.ceil((last) / nThread) + 1;
            if (pagine * (myId + 1) < last)
                last = pagine * (myId + 1);
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
                        if (ruolo.equals("P"))
                            li.add(new Player(
                                (i - 1) * 15 + (j - 1), //id 	Integer.parseInt(riga.get(0).getText())
                                riga.get(1).getText(), //nome
                                ruolo, //ruolo
                                riga.get(3).getText(), //squadra
                                Integer.parseInt(riga.get(7).getText()), //pg
                                Integer.parseInt(riga.get(9).getText()), //minuti
                                0, //gol
                                0, //tiri
                                0, //dribling
                                0, //assist
                                0, //passaggi
                                0, //passaggiChiave
                                Integer.parseInt(riga.get(20).getText()), //ammonizioni
                                Integer.parseInt(riga.get(21).getText()), //espulsioni
                                0, //rubati
                                0, //tackle
                                Integer.parseInt(riga.get(24).getText()), //cleanSheet
                                Integer.parseInt(riga.get(25).getText()) //parate
                            ));
                        else
                            li.add(new Player(
                                (i - 1) * 15 + (j - 1), //id 	Integer.parseInt(riga.get(0).getText())
                                riga.get(1).getText(), //nome
                                ruolo, //ruolo
                                riga.get(3).getText(), //squadra
                                Integer.parseInt(riga.get(7).getText()), //pg
                                Integer.parseInt(riga.get(9).getText()), //minuti
                                Integer.parseInt(riga.get(10).getText()), //gol
                                Integer.parseInt(riga.get(11).getText()), //tiri
                                Integer.parseInt(riga.get(14).getText()), //dribling
                                Integer.parseInt(riga.get(15).getText()), //assist
                                Integer.parseInt(riga.get(16).getText()), //passaggi
                                Integer.parseInt(riga.get(17).getText()), //passaggiChiave
                                Integer.parseInt(riga.get(20).getText()), //ammonizioni
                                Integer.parseInt(riga.get(21).getText()), //espulsioni
                                Integer.parseInt(riga.get(22).getText()), //rubati
                                Integer.parseInt(riga.get(23).getText()), //tackle
                                Integer.parseInt(riga.get(24).getText()), //cleanSheet
                                0 //parate
                            ));
                    }
                }
            }
        }
        driver.quit();
    }
}

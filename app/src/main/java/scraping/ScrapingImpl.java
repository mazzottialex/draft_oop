package scraping;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import data.Player;
import utils.Pair;

/**
 * Implementation of the Scraping interface for data scraping.
 */
public class ScrapingImpl implements Scraping {
    private final List<Player> li = new ArrayList<>();
    private List<String> stagioni = new ArrayList<>();
    private final String url = "https://www.kickest.it/it/serie-a/statistiche/giocatori/tabellone?iframe=yes";
    private final int nThread;
    private Boolean flagChrome = false;
    private Boolean flagFirefox = false;
    private static final int DEFAULT_THREAD = 7;
    private static final String BROWSER_CHROME = "google-chrome";
    private static final String BROWSER_FIREFOX = "firefox";

    /**
     * Constructs a ScrapingImpl object with the specified number of threads.
     *
     * @param nThread The number of threads to use for scraping
     */
    public ScrapingImpl(final int nThread) {
        this.nThread = nThread;
        if(checkBrowser(BROWSER_CHROME)) {
            flagChrome = true;
        }
        else if(checkBrowser(BROWSER_FIREFOX)) {
            flagFirefox = true;
        }
    }

    /**
     * Constructs a ScrapingImpl object with a default number of threads (7).
     */
    public ScrapingImpl() {
        this(DEFAULT_THREAD); //default
    }

    @Override
    public List<Player> getLiPlayer() {
        return li;
    }

    @Override
    public List<String> getLiSeason() {
        return stagioni;
    }

    @Override
    public Boolean readTable(final String season) {
        if (flagChrome || flagFirefox) {
            final List<Pair<RunnableScrapingData, Thread>> liThr = new ArrayList<>();
            for (int i = 0; i < nThread; i++) {
                final RunnableScrapingData runnable = new RunnableScrapingData(i, nThread, season, flagChrome);
                final Thread thr = new Thread(runnable);
                liThr.add(new Pair<>(runnable, thr));
                thr.start();
            }
            for (Pair<RunnableScrapingData, Thread> el: liThr) {
                try {
                    el.getY().join();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                    return false;
                }
                if (!el.getX().check()) {
                    return false;
                }
                li.addAll(el.getX().getLi());
            }
                return true;
        } else {
    	    return false;
        }
    }

    @Override
    public Boolean readTable() {
        final String defaultStagione = "2022-2023";
        return this.readTable(defaultStagione);
    }

    @Override
    public Boolean readSeason() {
    	if (flagChrome || flagFirefox) {
	    	final List<String> stagioni = new ArrayList<>();
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
	        driver.get(this.url);
	        //Oggetto per eseguire operazioni sulla pagina
	        final JavascriptExecutor js = (JavascriptExecutor) driver;
	        js.executeScript("document.querySelector('[data-id=\"selectPickerSeasons\"]').click()");
	        driver.findElement(By.tagName("ul")).findElements(By.tagName("li"))
	            .stream()
	            .map(el -> el.getText())
	            .map(str -> str.replace("/", "-"))
	            .forEach(el -> stagioni.add(el));
	        driver.quit();
	        this.stagioni = stagioni;
	        return true;
    	} else {
    		return false;
    	}
    }

    /**
     * Checks if the specified browser is available on the current operating system.
     *
     * @param browser the browser to check (e.g., "google-chrome" or "firefox")
     * @return {@code true} if the browser is available, {@code false} otherwise
     */
    private static boolean checkBrowser(final String browser) {
        String os = System.getProperty("os.name").toLowerCase();
        if (os.contains("win")) {
            String path1 = null;
            String path2 = null;
        	if (browser.equals(BROWSER_CHROME)) {
            	path1 = "C:\\Program Files (x86)\\Google\\Chrome\\Application\\Chrome.exe";
            	path2 = "C:\\Program Files\\Google\\Chrome\\Application\\Chrome.exe";
            } else if (browser.equals(BROWSER_FIREFOX)) {
        		path1 = "C:\\Program Files (x86)\\Mozilla Firefox\\firefox.exe";
            	path2 = "C:\\Program Files\\Mozilla Firefox\\firefox.exe";
        	}

            if (new File(path1).exists() || new File(path2).exists()) {
                return true;
            }
        } else if (os.contains("nix") || os.contains("nux") || os.contains("mac")) {
            // Linux
            String[] istruzione = { "which", browser }; //cerca browser tra i programmi
            try {
                Process process = Runtime.getRuntime().exec(istruzione);
                process.waitFor();
                return process.exitValue() == 0;
            } catch (Exception e) {
                return false;
            }
        }
        return false;
    }
}

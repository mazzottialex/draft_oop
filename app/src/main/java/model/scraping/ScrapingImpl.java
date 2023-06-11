package model.scraping;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;
import model.data.Player;
import utils.Pair;
/**
 * Implementation of the Scraping interface for data scraping.
 */
public final class ScrapingImpl implements Scraping {
    private final List<Player> li = new ArrayList<>();
    private List<String> stagioni = new ArrayList<>();
    private static final String URL = "https://www.kickest.it/it/serie-a/statistiche/giocatori/tabellone?iframe=yes";
    private Boolean flagChrome = false;
    private Boolean flagFirefox = false;
    private static final int DEFAULT_THREAD = 7;
    private static final String BROWSER_CHROME = "google-chrome";
    private static final String BROWSER_FIREFOX = "firefox";
    private static final String DEFAULT_STAGIONE = "2022-2023";
    private static final String CHROME1 = "C:\\Program Files (x86)\\Google\\Chrome\\Application\\Chrome.exe";
    private static final String CHROME2 = "C:\\Program Files\\Google\\Chrome\\Application\\Chrome.exe";
    private static final String FIREFOX1 = "C:\\Program Files (x86)\\Mozilla Firefox\\firefox.exe";
    private static final String FIREFOX2 = "C:\\Program Files\\Mozilla Firefox\\firefox.exe";
    /**
     * Constructs a ScrapingImpl object with the specified number of threads.
     */
    public ScrapingImpl() {
        if (checkBrowser(BROWSER_CHROME)) {
            flagChrome = true;
        } else if (checkBrowser(BROWSER_FIREFOX)) {
            flagFirefox = true;
        }
    }

    @Override
    public List<Player> getLiPlayer() {
        return new ArrayList<>(li);
    }

    @Override
    public List<String> getLiSeason() {
        return new ArrayList<>(stagioni);
    }

    @Override
    public Boolean readTable(final String season, final int nThread) {
        if (flagChrome || flagFirefox) {
            final List<Pair<RunnableScrapingData, Thread>> liThr = new ArrayList<>();
            for (int i = 0; i < nThread; i++) {
                final RunnableScrapingData runnable = new RunnableScrapingData(i, nThread, season, flagChrome);
                final Thread thr = new Thread(runnable);
                liThr.add(new Pair<>(runnable, thr));
                thr.start();
            }
            for (final Pair<RunnableScrapingData, Thread> el: liThr) {
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
        return this.readTable(DEFAULT_STAGIONE, DEFAULT_THREAD);
    }

    @Override
    public Boolean readSeason() {
        if (flagChrome || flagFirefox) {
            final List<String> stagioni = new ArrayList<>();
            final WebDriver driver;
            if (flagChrome) {
                final ChromeOptions options = new ChromeOptions();
                options.addArguments("headless");
                driver = new ChromeDriver(options);
            } else {
                final FirefoxOptions options = new FirefoxOptions();
                options.addArguments("-headless");
                driver = new FirefoxDriver(options);
            }
            driver.get(URL);
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

    @Override
    public Boolean checkBrowsers() {
        if (flagChrome || flagFirefox) {
            return true;
        }
        return false;
    }

    /**
     * Checks if the specified browser is available on the current operating system.
     *
     * @param browser the browser to check (e.g., "google-chrome" or "firefox")
     * @return {@code true} if the browser is available, {@code false} otherwise
     */
    @SuppressFBWarnings("DMI")
    private static boolean checkBrowser(final String browser) {
    	final String os = System.getProperty("os.name").toLowerCase(Locale.getDefault());
        if (os.contains("win")) {
            if (browser.equals(BROWSER_CHROME) && (new File(CHROME1).exists() || new File(CHROME2).exists())) {
                return true;
            } else if (browser.equals(BROWSER_FIREFOX) && (new File(FIREFOX1).exists() || new File(FIREFOX2).exists())) {
        	    return true;
            }
        } else if (os.contains("nix") || os.contains("nux") || os.contains("mac")) {
            // Linux
        	final String[] istruzione = { "which", browser }; //cerca browser tra i programmi
            try {
            	final Process process = Runtime.getRuntime().exec(istruzione);
                process.waitFor();
                return process.exitValue() == 0;
            } catch (final Exception e) {
                return false;
            }
        }
        return false;
    }
}

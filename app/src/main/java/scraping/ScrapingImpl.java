package scraping;
import java.util.ArrayList;
import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import data.Calciatore;
import utils.Pair;

public class ScrapingImpl implements Scraping {
    private final List<Calciatore> li = new ArrayList<>();
    private final String url = 
    		"https://www.kickest.it/it/serie-a/statistiche/giocatori/tabellone?iframe=yes";
    private final int nThread;
    public ScrapingImpl(final int nThread) {
        this.nThread = nThread;
    }
    public ScrapingImpl() {
        this(7); //default
    }
    public List<Calciatore> getLista() {
        return li;
    }
    public Boolean ReadTable(final String stagione) {
    	final List<Pair<RunnableScrapingData, Thread>> liThr = new ArrayList<>();
        for (int i = 0; i < nThread; i++) {
        	final RunnableScrapingData runnable = new RunnableScrapingData(i, nThread, stagione, false);
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
    }
    public Boolean ReadTable() {
    	final String defaultStagione = "2022-2023";
        return this.ReadTable(defaultStagione);
    }
    public List<String> getStagioni(final Boolean flagChrome) {
        //Nascondere pagine chrome
    	final List<String> stagioni = new ArrayList<>();
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
        return stagioni;
    }
}

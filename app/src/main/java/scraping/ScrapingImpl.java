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

public class ScrapingImpl implements Scraping {
    private final List<Player> li = new ArrayList<>();
    private List<String> stagioni = new ArrayList<>();
    private final String url = 
    		"https://www.kickest.it/it/serie-a/statistiche/giocatori/tabellone?iframe=yes";
    private final int nThread;
    private Boolean flagChrome=false;
    private Boolean flagFirefox=false;
    public ScrapingImpl(final int nThread) {
        this.nThread = nThread;
        if(checkBrowser("google-chrome")) {
        	flagChrome=true;
        }
        else if(checkBrowser("firefox")) {
        	flagFirefox=true;
        }

    }
    public ScrapingImpl() {
        this(7); //default
    }
    public List<Player> getLiCalciatori() {
        return li;
    }
    public List<String> getStagioni() {
        return stagioni;
    }
    public Boolean ReadTable(final String stagione) {
    	if(flagChrome || flagFirefox) {
	    	final List<Pair<RunnableScrapingData, Thread>> liThr = new ArrayList<>();
	        for (int i = 0; i < nThread; i++) {
	        	final RunnableScrapingData runnable = new RunnableScrapingData(i, nThread, stagione, flagChrome);
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
    	else
    		return false;
    }
    public Boolean ReadTable() {
    	final String defaultStagione = "2022-2023";
        return this.ReadTable(defaultStagione);
    }
    public Boolean readStagioni() {
    	if(flagChrome || flagFirefox) {
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
	        this.stagioni = stagioni;
	        return true;
    	}
    	else
    		return false;
    }
    private static boolean checkBrowser(final String browser) {
        String os = System.getProperty("os.name").toLowerCase();

        if (os.contains("win")) {
            // Windows
            String pathprogrammi = System.getenv("ProgramFiles");
            String pathBrowser = pathprogrammi + "\\" + browser + "\\" + browser + ".exe";
            return new File(pathBrowser).exists();
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

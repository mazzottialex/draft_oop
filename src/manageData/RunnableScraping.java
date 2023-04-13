package manageData;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class RunnableScraping implements Runnable{
	
	private final int myId;
	private final int nThread;
	private List<Calciatore> li=new ArrayList<>();
	private final String url="https://www.kickest.it/it/serie-a/statistiche/giocatori/tabellone?iframe=yes";
	
	public RunnableScraping(int myId, int nThread, String stagione) {
		this.myId = myId;
		this.nThread = nThread;
	}

	public List<Calciatore> getLi() {
		return li;
	}

	public void run() {
		//Nascondere pagine chrome
		ChromeOptions options=new ChromeOptions();
		options.addArguments("headless");

		//Oggetto per creare il collegamento
		WebDriver driver = new ChromeDriver(options);
		driver.get(url);

		//Oggetto per eseguire operazioni sulla pagina
		JavascriptExecutor js=(JavascriptExecutor) driver;

		//Attende che la pagina carichi la tabella
		WebDriverWait wait=new WebDriverWait(driver, Duration.ofSeconds(40)); //aspetta 40 sec per caricare la tabella
		wait.until(ExpectedConditions.presenceOfElementLocated(By.tagName("tr")));
		
		int last= Integer.parseInt(driver.findElement(By.className("paginationjs-last")).getText());

		int pagine=(int) Math.ceil((last)/nThread)+1;

		if(pagine*(myId+1)<last)
			last=pagine*(myId+1);

		for (Integer i=1;i<last+1;i++) {
			js.executeScript("document.querySelector('[data-num=\""+i.toString()+"\"]').click()");

			if(i>myId*pagine) {
				System.out.println(myId+"  i:"+ i);
	
				WebElement table=driver.findElement(By.tagName("tbody"));
				int nRighe=table.findElements(By.tagName("tr")).size();
				for(int j=1;j<nRighe+1;j++) {
					List<WebElement> riga=driver.findElements(By.tagName("tr")).get(j).findElements(By.tagName("td"));		
					li.add(new Calciatore(
							(i-1)*15+(j-1), //id 	Integer.parseInt(riga.get(0).getText())
							riga.get(1).getText(),	//nome
							riga.get(2).getText().substring(0, 1),	//ruolo
							riga.get(3).getText(),	//squadra
							Integer.parseInt(riga.get(7).getText()),	//pg
							Integer.parseInt(riga.get(9).getText()),	//minuti
							Integer.parseInt(riga.get(10).getText()),	//gol
							Integer.parseInt(riga.get(11).getText()),	//tiri
							Integer.parseInt(riga.get(14).getText()),	//dribling
							Integer.parseInt(riga.get(15).getText()),	//assist
							Integer.parseInt(riga.get(16).getText()),	//passaggi
							Integer.parseInt(riga.get(17).getText()),	//passaggiChiave
							Integer.parseInt(riga.get(20).getText()),	//ammonizioni
							Integer.parseInt(riga.get(21).getText()),	//espulsioni
							Integer.parseInt(riga.get(22).getText()),	//rubati
							Integer.parseInt(riga.get(23).getText()),	//tackle
							Integer.parseInt(riga.get(24).getText()),	//cleanSheet
							Integer.parseInt(riga.get(25).getText())	//parate
					));
				}
			}
		}	
		driver.quit();
	}
}

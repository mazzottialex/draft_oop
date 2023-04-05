package test;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import manageData.ManageData;
import manageData.ManageDataImpl;


public class Launch {

	public static void main(String[] args) throws FileNotFoundException, IOException, InterruptedException {
		//ManageData manage=new ManageDataImpl();
		//System.out.println(manage.getListaByRuolo("A"));
		//System.out.println(manage.getCalciatoreByName("De Luca M."));
		//System.out.println(manage.getCalciatoreBySquadra("inter"));
		//System.out.println(manage.getRandomByRuolo("A",5));
		
		//Nascondere pagine chrome
		ChromeOptions options=new ChromeOptions();
		options.addArguments("headless");
		
		//Oggetto per creare il collegamento
		WebDriver driver = new ChromeDriver(options);
        driver.get("https://www.kickest.it/it/serie-a/statistiche/giocatori/tabellone?iframe=yes");
        
        //Oggetto per eseguire operazioni sulla pagina
        JavascriptExecutor js=(JavascriptExecutor) driver;
        
        //Attende che la pagina carichi la tabella
		WebDriverWait wait=new WebDriverWait(driver, Duration.ofSeconds(20));
		wait.until(ExpectedConditions.presenceOfElementLocated(By.tagName("tr")));
		
		Integer last= Integer.parseInt(driver.findElement(By.className("paginationjs-last")).getText());

		for (Integer i=1;i<last+1;i++) {
			js.executeScript("document.querySelector('[data-num=\""+i.toString()+"\"]').click()");
			System.out.println(driver.findElement(By.tagName("tbody")).getText());
		}
		
        driver.quit();
	}
}

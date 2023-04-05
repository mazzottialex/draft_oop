package test;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
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
		WebDriver driver = new ChromeDriver();
        driver.get("https://www.kickest.it/it/serie-a/statistiche/giocatori/tabellone?iframe=yes");
		//driver.wait(1000);
		WebDriverWait wait=new WebDriverWait(driver, Duration.ofSeconds(20));
		wait.until(ExpectedConditions.presenceOfElementLocated(By.tagName("tr")));
		System.out.print(driver.findElement(By.tagName("table")).getText());
        driver.quit();
	}
}

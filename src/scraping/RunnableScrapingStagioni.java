package scraping;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class RunnableScrapingStagioni implements Runnable{
	private final String url;
	private List<String> stagioni;
	
	public RunnableScrapingStagioni(String url) {
		this.url=url;
		this.stagioni=new ArrayList<>();
	}
	
	public List<String> getStagioni(){
		return this.stagioni;
	}
	
	@Override
	public void run() {
		//Nascondere pagine chrome
		ChromeOptions options=new ChromeOptions();
		options.addArguments("headless");

		//Oggetto per creare il collegamento
		WebDriver driver = new ChromeDriver(options);
		driver.get(url);

		//Oggetto per eseguire operazioni sulla pagina
		JavascriptExecutor js=(JavascriptExecutor) driver;

		js.executeScript("document.querySelector('[data-id=\"selectPickerSeasons\"]').click()");
				
		driver.findElement(By.tagName("ul")).findElements(By.tagName("li"))
									.stream()
									.map(el->el.getText())
									.map(str->str.replace("/", "-"))
									.forEach(el->stagioni.add(el));
		driver.quit();
		
	}
	
}

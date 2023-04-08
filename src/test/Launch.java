package test;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.Duration;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
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
		ManageData manage=new ManageDataImpl();
		//System.out.println(manage.getListaByRuolo("A"));
		//System.out.println(manage.getCalciatoreByName("De Luca M."));
		//System.out.println(manage.getCalciatoreBySquadra("inter"));
		//System.out.println(manage.getRandomByRuolo("A",5));
		
		//System.out.println(manage.getLi().get(2));
		System.out.println(manage.getLi().get(100).toString());
	}
}

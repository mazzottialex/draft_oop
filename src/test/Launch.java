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

import manageData.ExtractData;
import manageData.ExtractDataImpl;


public class Launch {

	public static void main(String[] args) throws FileNotFoundException, IOException, InterruptedException, ClassNotFoundException {
		ExtractData ex=new ExtractDataImpl();
		
		ex.getLi().forEach(a -> System.out.println(a.getId()+" "+a.getNominativo()));
	}
}

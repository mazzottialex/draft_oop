package test;

import manageData.Scraping;

public class Launch {

	public static void main(String[] args) {
		Scraping doc= new Scraping();
		System.out.println(doc.getLi().get(20));

	}

}

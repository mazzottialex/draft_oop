package test;

import java.io.FileNotFoundException;
import java.io.IOException;
import manageData.ExtractData;
import manageData.ExtractDataImpl;



public class Launch {

	public static void main(String[] args) throws FileNotFoundException, IOException, ClassNotFoundException {
		ExtractData ex=new ExtractDataImpl();
		
		System.out.println(ex.getLi().get(10));
	}
}

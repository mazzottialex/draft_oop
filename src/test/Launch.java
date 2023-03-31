package test;

import java.io.FileNotFoundException;
import java.io.IOException;

import manageData.ManageData;
import manageData.ManageDataImpl;


public class Launch {

	public static void main(String[] args) throws FileNotFoundException, IOException {
		ManageData manage=new ManageDataImpl();
		System.out.println(manage.getListaByRuolo("A"));
		//System.out.println(manage.getCalciatoreByName("De Luca M."));
		//System.out.println(manage.getCalciatoreBySquadra("inter"));
		//System.out.println(manage.getRandomByRuolo("A",5));
	}

}

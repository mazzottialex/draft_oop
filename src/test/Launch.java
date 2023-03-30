package test;

import manageData.ManageData;
import manageData.ManageDataImpl;


public class Launch {

	public static void main(String[] args) {
		ManageData manage=new ManageDataImpl();
		System.out.println(manage.getListaByRuolo("A"));
		System.out.println(manage.getCalciatoreByName("De Luca M."));
		System.out.println(manage.getCalciatoreBySquadra("inter"));
	}

}

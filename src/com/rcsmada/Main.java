package com.rcsmada;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Main {
	public static void main(String[] args) {
		lire2();
	}
	public static void lire()
	{
		List<String> keys= new ArrayList<String>();
		List<String> values= new ArrayList<String>();
		 
		keys.add("TypeSociete");
		 
		keys.add("Greffe");
		
		keys.add("DateInscrit");
		
		keys.add("FormeJuridiq");
		 
		values.add("Null");
		 
		values.add("10");
		
		values.add("");
		
		values.add("Null");
		 
		String retourDeLaPage =null;
		try {
			retourDeLaPage=Rcsmada.post("http://www.rcsmada.mg/index.php?pgdown=liste2",keys,values);
			System.out.println(retourDeLaPage);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	public static void lire2()
	{
		List<String> keys= new ArrayList<String>();
		List<String> values= new ArrayList<String>();
		 
		keys.add("pgdown");
		 
		keys.add("page");
		
		keys.add("req");
		
		 
		values.add("liste2");
		 
		values.add("3");
		
		values.add("V1IgICBHUkZfSUQgPScxMCcgIA");
		
		String retourDeLaPage =null;
		try {
			retourDeLaPage=Rcsmada.post("http://www.rcsmada.mg/index.php?pgdown=liste2&page=3&req=V1IgICBHUkZfSUQgPScxMCcgIA==",keys,values);
			ArrayList<String> lst_lines = Rcsmada.getLine(retourDeLaPage);
			ArrayList<String> cols = Rcsmada.getColumns(lst_lines.get(0));
			System.out.println(cols.get(0));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}

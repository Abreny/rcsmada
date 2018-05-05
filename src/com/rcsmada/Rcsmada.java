package com.rcsmada;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

public class Rcsmada {
	public static String post(String adress ,List<String> keys,List<String> values) throws IOException{
		String result = "";
		OutputStreamWriter writer = null;
		BufferedReader reader = null;
		try {
			//encodage des paramètres de la requête
			String data="";
			for(int i=0;i<keys.size();i++){
			if (i!=0) data += "&";
			data +=URLEncoder.encode(keys.get(i), "UTF-8")+"="+URLEncoder.encode(values.get(i), "UTF-8");
		}
		//création de la connection
		URL url = new URL(adress);
		URLConnection conn = url.openConnection();
		conn.setDoOutput(true);


		//envoi de la requête
		writer = new OutputStreamWriter(conn.getOutputStream());
		writer.write(data);
		writer.flush();




		//lecture de la réponse
		reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
		String ligne;
		while ((ligne = reader.readLine()) != null) {
			result+=ligne;
		}
		}catch (Exception e) {
		e.printStackTrace();
		}finally{
		try{
			writer.close();
		}catch(Exception e){}
		
		try{
			reader.close();
		}catch(Exception e){}
		}
		return removeEspace(result);
	}
	
	public static String removeEspace(String result)
	{
		String table=null;
		int deb= result.indexOf("<tbody");
		int fin= result.lastIndexOf("</tbody>");
		table=result.substring(deb, fin);
		table = table.replaceAll("									", "");
		table = table.replaceAll("						", "");
		return table;
	}
	public static ArrayList<String> getLine(String table)
	{
		ArrayList<String> ret = new ArrayList<String>();
		int deb,fin,i=0,len;
		String tr="",new_table=table;
		while(new_table.indexOf("<tr>")>=0)
		{
			i++;
			deb=new_table.indexOf("<tr>")+"<tr>".length();
			fin=new_table.indexOf("</tr>");
			if(deb > 0 && fin > 0 )
			{
				tr = new_table.substring(deb, fin);
				ret.add(tr);
				len=new_table.length();
				new_table = new_table.substring(fin+"</tr>".length(),len).trim();
			}
			else
			{
				System.out.println(new_table +" : "+i);
				
				break;
			}
		}
		return ret;
	}
	public static ArrayList<String> getColumns(String line)
	{
		ArrayList<String> ret = new ArrayList<String>();
		int deb,fin,i=0,len;
		String tr="",new_line=line;
		while(new_line.indexOf("<td>")>=0)
		{
			i++;
			deb=new_line.indexOf("<td>")+"<td>".length();
			fin=new_line.indexOf("</td>");
			if(deb > 0 && fin > 0 )
			{
				tr = new_line.substring(deb, fin);
				ret.add(tr);
				len=new_line.length();
				new_line = new_line.substring(fin+"</td>".length(),len).trim();
				//System.out.println(tr);
			}
			else
			{
				System.out.println(new_line +" : "+i);
				
				break;
			}
			
			if(i==4)
			{
				deb=new_line.indexOf("soc=")+"soc=".length();
				fin = new_line.indexOf("\">+");
				tr = new_line.substring(deb,fin);
				//System.out.println(tr);
				break;
			}
			
		}
		return ret;
	}
}

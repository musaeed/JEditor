package Utility;

import java.util.ArrayList;
import java.util.StringTokenizer;

public class SmartWordAdder {
	
	public static void addWordsFromText(final ArrayList<String> list , final String text){
		
		new Thread(new Runnable() {
			
			@Override
			public void run() {
				
				addWords(list, text);
				
			}
		}).start();
		
	}
	
	static void addWords(ArrayList<String> list , String text){
		
		StringTokenizer st = new StringTokenizer(text , "(");
		
		while (st.hasMoreTokens()) {
			
			StringTokenizer su = new StringTokenizer(st.nextToken());
			
			while (su.hasMoreTokens()) {
				
				String token = su.nextToken();
				
				if(token.contains(")") || token.contains("{") || token.contains("}") || token.contains("\"") 
						|| token.contains(",") || token.contains("=") || token.contains("*") || token.contains("|") || token.contains("+")
						|| token.contains("-") || isNumeric(token)){
					
					continue;
				}
				
				if(!list.contains(token)){
					list.add(token);
				}
			}
		}
	}
	
	public static boolean isNumeric(String str)  
	{  
	  try  
	  {  
		  Double.parseDouble(str);  
	  }  
	  catch(NumberFormatException nfe)  
	  {  
	    return false;  
	  }  
	  return true;  
	}
	

}

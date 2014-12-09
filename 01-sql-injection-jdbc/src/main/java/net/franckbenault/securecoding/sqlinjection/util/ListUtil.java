package net.franckbenault.securecoding.sqlinjection.util;

import java.util.List;

public class ListUtil {

	public static String listToString(List<String> input) {
		String output ="'";
		for(String s: input) {
			if(output.equals("'")) 			
				output +=s;
			else
				output +="','"+s;
		}
		output +="'";
		
		return output;
	}
}

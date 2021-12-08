package org.java;

import java.util.ArrayList;
import java.util.List;

public class SplitTest {

	public static void main(String[] args) {
		
		String str = "1,2,3";
		List l = new ArrayList<String>();
		for(String s:str.split(",")) {
			System.out.println(s);
			l.add(s);
		}
		if(l.contains("10")) {
			System.out.println("Exists");
		}
	}

}

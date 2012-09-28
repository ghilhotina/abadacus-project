package com.schnell.generate.db;

import java.io.IOException;
import java.util.Properties;

public class TypeConversor {
	public TypeConversor(String dbName) {
		Properties props = new Properties();
	    try 
	    {
	    	props.load(TypeConversor.class.getResourceAsStream("/mysql.types"));
	    } catch (IOException e) 
	    {
	        e.printStackTrace();
	    }
	    String teste = props.getProperty("string");
	    System.out.println(teste);
	}
}

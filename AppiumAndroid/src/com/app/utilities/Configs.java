package com.app.utilities;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import com.sun.jna.platform.win32.OaIdl.VARKIND;

public class Configs

{

	public static String getConfig(String key)
	{
		Properties prop = new Properties();
		InputStream input = null;
		String value="";
		try {

			input = new FileInputStream("Resources\\config.properties");

			// load a properties file
			prop.load(input);

			// get the property value and print it out
			value = prop.getProperty(key);
			//System.out.println("Property Value " + value);

		} catch (IOException ex) {
			ex.printStackTrace();
		} finally {
			if (input != null) {
				try {
					input.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return value;
	}

}

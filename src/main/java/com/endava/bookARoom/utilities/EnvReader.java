package com.endava.bookARoom.utilities;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class EnvReader {

	public static Properties readProperties(String pathToFile ) {
		InputStream inputStream = EnvReader.class.getClassLoader().getResourceAsStream( pathToFile );
		Properties properties = new Properties();
		try {
			properties.load( inputStream );
		} catch ( IOException e ) {
			e.printStackTrace();
		}
		return properties;
	}

	public static String getBaseUrl() {
		Properties prop = readProperties( "env/qa.properties" );
		return prop.getProperty( "baseUrl" );
	}
}

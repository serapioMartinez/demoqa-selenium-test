package com.ipte.webapp;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * Hello world!
 *
 */
public class App 
{

    public static void main( String[] args ) throws IOException
    {
        InputStream is = App.class.getResourceAsStream("/data.properties");
        Properties prop = new Properties();
        prop.load(is);

        System.out.println(prop.getProperty("title"));
    }
}

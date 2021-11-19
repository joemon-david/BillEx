package com.billex.utils;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class PropertyReader {
    static String userDir = System.getProperty("user.dir");
    static String PROPERTY_FILE_PATH = userDir+"/src/main/resources/config.properties";

    public static String getProperty(String propertyName){
        String proValue="";
        try {
            InputStream input = new FileInputStream(PROPERTY_FILE_PATH);
            Properties properties = new Properties();
            properties.load(input);
            proValue =  properties.getProperty(propertyName);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
return proValue;
    }

    public static void main(String[] args) {
        PropertyReader reader = new PropertyReader();
        System.out.println(reader.getProperty("SECTION_CODE"));
    }
}

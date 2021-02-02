package com.shiftvision.spree.utils;

import org.openqa.selenium.By;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class PropertyFileObjectRepositoryManager implements  IObjectRepo{
    private static PropertyFileObjectRepositoryManager instance = null;

    private Properties properties = null;

    private PropertyFileObjectRepositoryManager(){
        properties = new Properties();
    }

    public static PropertyFileObjectRepositoryManager getInstance(){
        if(instance == null){
            instance = new PropertyFileObjectRepositoryManager();
        }
        return instance;
    }

    public void reset(){
        properties = new Properties();
    }

    public void load(String... fileNames){
        for(String fileName : fileNames) {
            if (new File(fileName).exists()) {
                InputStream input = null;
                try {
                    input = new FileInputStream(fileName);
                    properties.load(input);
                } catch (IOException io) {
                    io.printStackTrace();
                } finally {
                    if (input != null) {
                        try {
                            input.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }
            } else {
                throw new RuntimeException("File with the name " + fileName + " does not exist");
            }
        }
    }

    public By getLocator(String key){
        By by = null;

        String locatorBy = getLocatorByString(key);
        String locator = getLocatorString(key);

        if(locatorBy != null && locator != null) {
            if (locatorBy.equalsIgnoreCase("LINK_TEXT")) {
                by = By.linkText(locator);
            } else if (locatorBy.equalsIgnoreCase("PARTIAL_LINK_TEXT")) {
                by = By.partialLinkText(locator);
            } else if (locatorBy.equalsIgnoreCase("ID")) {
                by = By.id(locator);
            } else if (locatorBy.equalsIgnoreCase("NAME")) {
                by = By.name(locator);
            } else if (locatorBy.equalsIgnoreCase("CSS")) {
                by = By.cssSelector(locator);
            } else if (locatorBy.equalsIgnoreCase("TAG_NAME")) {
                by = By.tagName(locator);
            } else if (locatorBy.equalsIgnoreCase("XPATH")) {
                by = By.xpath(locator);
            } else if (locatorBy.equalsIgnoreCase("CLASS_NAME")) {
                by = By.className(locator);
            }
        }
        return  by;
    }


    protected String get(String key) {
        String value = properties.getProperty(key);
        return value;
    }
    protected String getLocatorByString(String key) {
        String locatorBy = null;
        String value = get(key);
        if(value != null) {
            String parts[] = value.split(":");
            locatorBy = parts[0];
        }
        return locatorBy;
    }
    protected String getLocatorString(String key) {
        String locator = null;
        String value = get(key);
        if(value != null) {
            String parts[] = value.split(":");
            if(parts != null && parts.length > 0) {
                locator = parts[1];
            }
            else{
                throw new RuntimeException("There is no value for the key " + key);
            }
        }
        else{
            throw new RuntimeException("There is no locator with the key " + key);
        }
        return locator;
    }

}

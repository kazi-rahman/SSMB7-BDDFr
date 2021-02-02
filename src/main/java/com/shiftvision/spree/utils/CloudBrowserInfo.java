package com.shiftvision.spree.utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class CloudBrowserInfo {

    private static CloudBrowserInfo instance = null;

    private String browser;
    private String browserVersion;
    private String os;
    private String osVersion;
    private String resolution;
    private String name;

    private CloudBrowserInfo(){
        load();
    }

    public static CloudBrowserInfo getInstance(){
        if(instance == null){
            instance = new CloudBrowserInfo();
        }
        return instance;
    }


    public String getBrowser() {
        return browser;
    }

    public void setBrowser(String browser) {
        this.browser = browser;
    }

    public String getBrowserVersion() {
        return browserVersion;
    }

    public void setBrowserVersion(String browserVersion) {
        this.browserVersion = browserVersion;
    }

    public String getOs() {
        return os;
    }

    public void setOs(String os) {
        this.os = os;
    }

    public String getOsVersion() {
        return osVersion;
    }

    public void setOsVersion(String osVersion) {
        this.osVersion = osVersion;
    }

    public String getResolution() {
        return resolution;
    }

    public void setResolution(String resolution) {
        this.resolution = resolution;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    private void load(){
        String fileName = System.getProperty("user.dir") + "/src/test/resources/cloud.properties";
        InputStream input = null;
        try {
            Properties properties = new Properties();
            input = new FileInputStream(fileName);
            properties.load(input);
            this.browser = properties.getProperty("browser");
            this.browserVersion = properties.getProperty("browser_version");
            this.os = properties.getProperty("os");
            this.osVersion = properties.getProperty("os_version");
            this.resolution = properties.getProperty("resolution");
            this.name = properties.getProperty("name");
        }
        catch (Exception ex){
            ex.printStackTrace();
        }
        finally {
            if (input != null) {
                try {
                    input.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

    }


    @Override
    public String toString() {
        return "CloudBrowserInfo{" +
                "browser='" + browser + '\'' +
                ", browserVersion='" + browserVersion + '\'' +
                ", os='" + os + '\'' +
                ", osVersion='" + osVersion + '\'' +
                ", resolution='" + resolution + '\'' +
                ", name='" + name + '\'' +
                '}';
    }
}

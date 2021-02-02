package com.shiftvision.spree.utils;

import io.github.bonigarcia.wdm.ChromeDriverManager;
import io.github.bonigarcia.wdm.FirefoxDriverManager;
import io.github.bonigarcia.wdm.InternetExplorerDriverManager;
import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.MalformedURLException;
import java.net.URL;

public class DriverFactory {

    private static final Logger logger = LoggerFactory.getLogger(DriverFactory.class);

    private static DriverFactory instance = null;
    private ThreadLocal<WebDriver> driverCollection = new ThreadLocal<WebDriver>();
    private static final String LOCAL_GRID_URL = "http://selenium-hub.shiftedtech.com/wd/hub";

    public static final String USERNAME = "azfarlodi1";
    public static final String AUTOMATE_KEY = "gaHiesWUTssKhwxGuGG7";
    public static final String CLOUD_GRID_URL = "https://" + USERNAME + ":" + AUTOMATE_KEY + "@hub-cloud.browserstack.com/wd/hub";

    private DriverFactory(){
    }

    public static DriverFactory getInstance(){
        String browser = System.getenv("browser");
        instance = getInstance(browser);
        return instance;
    }


    public static DriverFactory getInstance(String browser) {
        if (instance == null) {
            instance = new DriverFactory();
        }

        if (browser == null) {
            browser = "CH";
        }
        logger.debug("Using browser : " + browser);

        if(instance.driverCollection.get() == null) {
            WebDriver driver = null;
            if(browser.toUpperCase().contentEquals("FF")) {
                FirefoxDriverManager.firefoxdriver().arch64().setup();
                FirefoxOptions options = new FirefoxOptions();
                options.setCapability("marionette", true);

                driver = new FirefoxDriver(options);
            }
            else if(browser.toUpperCase().contentEquals("CH")) {
                ChromeDriverManager.chromedriver().setup();
                driver = new ChromeDriver();
            }
            else if(browser.toUpperCase().contentEquals("CH-HL")) {
                ChromeDriverManager.chromedriver().setup();
                //String chromeDriverPath = "/your/chromedriver/path" ;
                //System.setProperty("webdriver.chrome.driver", chromeDriverPath);

                ChromeOptions options = new ChromeOptions();
                options.addArguments("--headless", "--disable-gpu", "--window-size=1920,1200","--ignore-certificate-errors");
                //options.setBinary("/Path/to/specific/version/of/Google Chrome");
                driver = new ChromeDriver(options);
            }
            else if(browser.toUpperCase().contentEquals("IE")) {
                InternetExplorerDriverManager.iedriver().arch32().setup();
                driver = new InternetExplorerDriver();
            }
            else if(browser.toUpperCase().contentEquals("FF-GRID")) {
                DesiredCapabilities caps = new DesiredCapabilities();
                caps.setPlatform(Platform.ANY);
                caps.setBrowserName("firefox");
                try {
                    driver = new RemoteWebDriver(new URL(LOCAL_GRID_URL),caps);
                    instance.driverCollection.set(driver);
                } catch (MalformedURLException e) {
                    e.printStackTrace();
                }
                //FirefoxOptions options = new FirefoxOptions();
                //options.setCapability("marionette", true);
                //driver = new FirefoxDriver(options);
            }
            else if(browser.toUpperCase().contentEquals("CH-GRID")) {
                DesiredCapabilities caps = new DesiredCapabilities();
                caps.setPlatform(Platform.ANY);
                caps.setBrowserName("chrome");
                try {
                    driver = new RemoteWebDriver(new URL(LOCAL_GRID_URL),caps);
                    instance.driverCollection.set(driver);
                } catch (MalformedURLException e) {
                    e.printStackTrace();
                }
            }
            else if(browser.toUpperCase().contentEquals("CH-HL-GRID")) {
                DesiredCapabilities caps = DesiredCapabilities.chrome();
                //caps.setPlatform(Platform.ANY);
                //caps.setBrowserName("chrome");

                caps.setCapability("headless", true);
                caps.setCapability("platform", "LINUX");
                caps.setCapability("version", "latest");

                //   /usr/local/bin/chromedriver

                final ChromeOptions chromeOptions = new ChromeOptions();
                chromeOptions.setBinary("/usr/bin/google-chrome");
                chromeOptions.addArguments("--headless");
                caps.setCapability(ChromeOptions.CAPABILITY, chromeOptions);

                try {
                    driver = new RemoteWebDriver(new URL(LOCAL_GRID_URL),caps);
                    instance.driverCollection.set(driver);
                } catch (MalformedURLException e) {
                    e.printStackTrace();
                }
            }
            else if(browser.toUpperCase().contentEquals("CLOUD")){
                CloudBrowserInfo cloud = CloudBrowserInfo.getInstance();

                DesiredCapabilities caps = new DesiredCapabilities();
                caps.setCapability("browser", cloud.getBrowser());
                caps.setCapability("browser_version", cloud.getBrowserVersion());
                caps.setCapability("os", cloud.getOs());
                caps.setCapability("os_version", cloud.getOsVersion());
                caps.setCapability("resolution", cloud.getResolution());
                caps.setCapability("name", cloud.getName());
                try {
                    driver = new RemoteWebDriver(new URL(CLOUD_GRID_URL),caps);
                    instance.driverCollection.set(driver);
                } catch (MalformedURLException e) {
                    e.printStackTrace();
                }
            }
            else{
                throw new RuntimeException("Invalid browser name : " + browser);
            }

            instance.driverCollection.set(driver);

        }
        return instance;
    }

    public WebDriver getDriver() {
        return driverCollection.get();
    }

    public void removeDriver() {

        logger.debug("Removing driver");
        // Quits the driver and closes the browser
        try {
            driverCollection.get().close();
            driverCollection.get().quit();
        }
        catch(Exception ex){
            System.out.println(ex.getMessage());
        }
        finally {
            driverCollection.remove();
        }

    }


}

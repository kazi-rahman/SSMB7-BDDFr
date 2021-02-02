package com.shiftvision.spree.pages;

import org.openqa.selenium.WebDriver;

public class PageBase {
    protected WebDriver driver = null;

    public PageBase(WebDriver driver){

        this.driver = driver;

    }
}

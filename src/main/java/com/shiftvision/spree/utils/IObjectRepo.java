package com.shiftvision.spree.utils;

import org.openqa.selenium.By;

public interface IObjectRepo {
    void load(String... fileNames);
    void reset();
    By getLocator(String key);
}

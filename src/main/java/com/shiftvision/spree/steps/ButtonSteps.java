package com.shiftvision.spree.steps;

import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class ButtonSteps extends StepBase{


    //@When("user click {string} button")
    public void user_click_button(String locatorKey) {
        By by = objectRepo.getLocator(locatorKey);
        WebElement element = driver.findElement(by);
        element.click();
    }


}

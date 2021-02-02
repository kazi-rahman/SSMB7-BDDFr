package com.shiftvision.spree.steps;

import cucumber.api.java.en.When;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class TextboxSteps extends StepBase{

    @When("user enter {string} at {string} textbox")
    public void user_enter_at(String value, String locatorKey) {
        By by = objectRepo.getLocator(locatorKey);
        WebElement element = driver.findElement(by);
        element.sendKeys(value);
    }
}

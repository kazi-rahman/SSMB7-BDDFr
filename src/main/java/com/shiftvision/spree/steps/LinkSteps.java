package com.shiftvision.spree.steps;

import cucumber.api.java.en.When;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class LinkSteps extends StepBase{
    @When("user click {string} link/button")
    public void user_click_link(String locatorKey) {
        By by = objectRepo.getLocator(locatorKey);
        WebElement element = driver.findElement(by);
        element.click();
        delayFor(500);
    }
}

package com.shiftvision.spree.steps;

import cucumber.api.java.en.Then;
import org.junit.Assert;

public class PageSteps extends StepBase{
    @Then("home page display")
    public void home_page_display() {
        String title = driver.getTitle();
        Assert.assertEquals("Spree Demo Site",title);

    }


    @Then("login page display")
    public void login_page_display() {
        String title = driver.getTitle();
        Assert.assertEquals("Login - Spree Demo Site",title);
    }

}

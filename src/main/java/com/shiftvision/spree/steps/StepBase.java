package com.shiftvision.spree.steps;

import com.shiftvision.spree.pages.*;
import com.shiftvision.spree.utils.DriverFactory;
import com.shiftvision.spree.utils.IObjectRepo;
import com.shiftvision.spree.utils.PropertyFileObjectRepositoryManager;
import org.openqa.selenium.WebDriver;

public class StepBase {
    protected WebDriver driver = DriverFactory.getInstance().getDriver();
    protected static IObjectRepo objectRepo = PropertyFileObjectRepositoryManager.getInstance();
    protected PageFactory spree = PageFactory.getInstance();

    public void delayFor(int timeInMili){
        try {
            Thread.sleep(timeInMili);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

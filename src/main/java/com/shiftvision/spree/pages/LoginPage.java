package com.shiftvision.spree.pages;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage extends PageBase{

    @FindBy(id="spree_user_email")
    private WebElement userTextbox;
    @FindBy(id="spree_user_password")
    private WebElement passwordTextbox;
    @FindBy(name="commit")
    private WebElement loginButton;
    @FindBy(css = ".alert-error")
    private WebElement errorMessage;


    public LoginPage(WebDriver driver){
        super(driver);
        PageFactory.initElements(driver,this);
    }
    public void login(String user, String password){
        userTextbox.sendKeys(user);
        passwordTextbox.sendKeys(password);
        loginButton.click();
    }
    public void verifyLoginErrorMessage(String valueMsg) {
        String msg = errorMessage.getText();
        Assert.assertEquals(valueMsg,msg);
    }
}

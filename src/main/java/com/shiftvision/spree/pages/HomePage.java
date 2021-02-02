package com.shiftvision.spree.pages;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class HomePage extends PageBase{
    @FindBy(linkText = "Login")
    private WebElement loginLink;
    @FindBy(css = ".alert-success")
    private WebElement successMessage;
    @FindBy(xpath = "//div[@id='products']//div[starts-with(@id,'product')]")
    private List<WebElement> products;
    @FindBy(css = ".cart-info")
    private WebElement cartLink;

    public HomePage(WebDriver driver){
        super(driver);
        PageFactory.initElements(driver,this);
    }

    public void gotoLoginPage(){

        loginLink.click();
    }

    public void verifyLoginSuccess(String valueMsg) {
        String msg = successMessage.getText();
        Assert.assertEquals(valueMsg,msg);
    }

    public void selectProduct(String name){

        for(WebElement item : products){
            WebElement productInfo = item.findElement(By.cssSelector(".info"));
            if(productInfo.getText().equalsIgnoreCase(name)){
                productInfo.click();
                break;
            }
        }
    }

    public void navigateToCartPage(){
        cartLink.click();
        System.out.println("Click cart link");
    }

}

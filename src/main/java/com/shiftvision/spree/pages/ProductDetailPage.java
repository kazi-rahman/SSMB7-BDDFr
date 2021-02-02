package com.shiftvision.spree.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ProductDetailPage extends PageBase{

    @FindBy(id = "add-to-cart-button")
    private WebElement addToCartButton;

    public ProductDetailPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver,this);
    }

    public void addToCart(){
        addToCartButton.click();
    }


}

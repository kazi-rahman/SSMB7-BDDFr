package com.shiftvision.spree.pages;

import com.shiftvision.spree.steps.Hooks;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

public class CartPage extends PageBase {

    private static final Logger logger = LoggerFactory.getLogger(CartPage.class);

    @FindBy(linkText = "Home")
    private WebElement homePageLink;
    @FindBy(id = "cart-detail")
    private WebElement cartDetail;
    @FindBy(css = "#clear_cart_link input")
    private WebElement clearCart;
    @FindBy(css = "#line_items .line-item")
    private List<WebElement> lineItems;

    public CartPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver,this);
    }

    public void navigateToHomePage(){
        logger.info("Click home page link");
        homePageLink.click();
    }

    public void verifyNumberOfProduct(int count){

        List<WebElement> productList = cartDetail.findElements(By.xpath("//td[@class='cart-item-description']/h4/a"));
        int actualCount = productList.size();

        Assert.assertEquals(count,actualCount);

    }

    public void clearCart(){
        clearCart.click();
    }

    public String getLineItemProductName(int index){
        WebElement lineItem = lineItems.get(index);

        WebElement productName = lineItem.findElement(By.cssSelector(".cart-item-description h4"));
        String text = productName.getText();
        return  text;
    }
    public String getLineItemProductPrice(int index){
        WebElement lineItem = lineItems.get(index);
        WebElement productPrice = lineItem.findElement(By.cssSelector(".cart-item-price"));
        String text = productPrice.getText();
        return  text;
    }
    public String getLineItemProductQuantity(int index){
        WebElement lineItem = lineItems.get(index);

        WebElement productQuantity = lineItem.findElement(By.cssSelector(".line_item_quantity"));
        String text = productQuantity.getAttribute("value");
        return  text;
    }
    public String getLineItemProductTotal(int index){
        WebElement lineItem = lineItems.get(index);

        WebElement productTotal = lineItem.findElement(By.cssSelector(".cart-item-total"));
        String text = productTotal.getText();
        return  text;
    }
}

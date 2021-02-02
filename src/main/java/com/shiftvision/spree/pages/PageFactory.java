package com.shiftvision.spree.pages;

import com.shiftvision.spree.utils.DriverFactory;
import org.openqa.selenium.WebDriver;

public class PageFactory {

    private static PageFactory instance = null;

    private ThreadLocal<HomePage> homePage = new ThreadLocal<>();
    private ThreadLocal<LoginPage> loginPage = new ThreadLocal<>();
    private ThreadLocal<ProductDetailPage> productDetailPage = new ThreadLocal<>();
    private ThreadLocal<CartPage> cartPage = new ThreadLocal<>();

    private PageFactory(){
    }

    public static PageFactory getInstance(){
        if(instance == null){
            instance = new PageFactory();
        }
        return instance;
    }

    public HomePage homePage(){
        if(homePage.get() == null){
            homePage.set(new HomePage(DriverFactory.getInstance().getDriver()));
        }
        return homePage.get();
    }

    public LoginPage loginPage(){
        if(loginPage.get() == null){
            loginPage.set(new LoginPage(DriverFactory.getInstance().getDriver()));
        }
        return  loginPage.get();
    }

    public ProductDetailPage productDetailPage(){
        if(productDetailPage.get() == null){
            productDetailPage.set(new ProductDetailPage(DriverFactory.getInstance().getDriver()));
        }
        return productDetailPage.get();
    }

    public CartPage cartPage(){
        if(cartPage.get() == null){
            cartPage.set(new CartPage(DriverFactory.getInstance().getDriver()));
        }
        return cartPage.get();
    }

    public void reSet(){
        homePage.remove();
        loginPage.remove();
        productDetailPage.remove();
        cartPage.remove();
    }
}

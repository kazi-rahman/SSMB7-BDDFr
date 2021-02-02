package com.shiftvision.spree.steps;

import com.shiftvision.spree.utils.IObjectRepo;
import com.shiftvision.spree.utils.PropertyFileObjectRepositoryManager;
import cucumber.api.PendingException;

import cucumber.api.java.After;
import cucumber.api.java.Before;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

public class SpreeSteps extends StepBase{

    private static final Logger logger = LoggerFactory.getLogger(SpreeSteps.class);



    @Given("not a validated user")
    public void not_a_validated_user() {
        logger.info("not a validated user");
        driver.manage().deleteAllCookies();
    }

    @When("user browse to the {string}")
    public void user_browse_to_the(String url) {
       driver.navigate().to(url);
    }


    @Then("login success/error message display in {string} as {string}")
    public void login_success_message_display_in_as(String locatorKey, String valueMsg) {
        By by = objectRepo.getLocator(locatorKey);
        WebElement element = driver.findElement(by);
        String msg = element.getText();
        Assert.assertEquals(valueMsg,msg);
    }

    //@Then("login error message display in {string} as {string}")
    public void login_error_message_display_in_as(String locatorKey, String valueMsg) {
        By by = objectRepo.getLocator(locatorKey);
        WebElement element = driver.findElement(by);
        String msg = element.getText();
        Assert.assertEquals(valueMsg,msg);
    }

    @When("user navigate to login page")
    public void user_navigate_to_login_page() {
        spree.homePage().gotoLoginPage();
    }

    @When("user login with user {string} and password {string}")
    public void user_login_with_user_and_password(String user, String password) {
        spree.loginPage().login(user,password);
    }

    @Then("login success message display as {string}")
    public void login_success_message_display_as(String msg) {
        spree.homePage().verifyLoginSuccess(msg);
    }

    @Then("login error message display as {string}")
    public void login_error_message_display_as(String msg) {
       spree.loginPage().verifyLoginErrorMessage(msg);
    }

    @When("user user select a product name {string}")
    public void user_user_select_a_product_name(String name) {
       spree.homePage().selectProduct(name);
    }

    @When("add the product to shopping cart")
    public void add_the_product_to_shopping_cart() {
        spree.productDetailPage().addToCart();
    }

    @When("navigate to home page")
    public void navigate_to_home_page() {
        spree.cartPage().navigateToHomePage();
    }

    @When("navigate to the shopping cart page")
    public void navigate_to_the_shopping_cart_page() {
        spree.homePage().navigateToCartPage();
    }


    @Then("clear the cart")
    public void clear_the_cart() {
        spree.cartPage().clearCart();
    }

    @Then("{int} product should be visible in the cart")
    public void product_should_be_visible_in_the_cart(Integer count) {
        spree.cartPage().verifyNumberOfProduct(count);
    }

    @When("user select following products")
    public void user_select_following_products(io.cucumber.datatable.DataTable dataTable) {
        // Write code here that turns the phrase above into concrete actions
        // For automatic transformation, change DataTable to one of
        // E, List<E>, List<List<E>>, List<Map<K,V>>, Map<K,V> or
        // Map<K, List<V>>. E,K,V must be a String, Integer, Float,
        // Double, Byte, Short, Long, BigInteger or BigDecimal.
        //
        // For other transformations you can register a DataTableType.
        List<String> names = dataTable.asList();
        for(String name : names){
            spree.homePage().selectProduct(name);
            spree.productDetailPage().addToCart();
            spree.cartPage().navigateToHomePage();
        }

    }

    @Then("following product should be visible in the cart")
    public void following_product_should_be_visible_in_the_cart(io.cucumber.datatable.DataTable dataTable) {
        // Write code here that turns the phrase above into concrete actions
        // For automatic transformation, change DataTable to one of
        // E, List<E>, List<List<E>>, List<Map<K,V>>, Map<K,V> or
        // Map<K, List<V>>. E,K,V must be a String, Integer, Float,
        // Double, Byte, Short, Long, BigInteger or BigDecimal.
        //
        // For other transformations you can register a DataTableType.
        List<List<String>> lineItems = dataTable.asLists();
        for (int i = 0; i < lineItems.size(); i++) {
            logger.info("Validation line item : " + i);
            String expectedName = lineItems.get(i).get(0);
            String actualName = spree.cartPage().getLineItemProductName(i);
            Assert.assertEquals(expectedName, actualName);

            String expectedPrice = lineItems.get(i).get(1);
            String actualPrice = spree.cartPage().getLineItemProductPrice(i);
            Assert.assertEquals(expectedPrice, actualPrice);

            String expectedQuantity = lineItems.get(i).get(2);
            String actualQuantity = spree.cartPage().getLineItemProductQuantity(i);
            Assert.assertEquals(expectedQuantity, actualQuantity);

            String expectedTotal = lineItems.get(i).get(3);
            String actualTotal = spree.cartPage().getLineItemProductTotal(i);
            Assert.assertEquals(expectedTotal, actualTotal);
        }
    }

        @Then("following product should be visible in the cart page")
        public void following_product_should_be_visible_in_the_cart_page(io.cucumber.datatable.DataTable dataTable) {
            // Write code here that turns the phrase above into concrete actions
            // For automatic transformation, change DataTable to one of
            // E, List<E>, List<List<E>>, List<Map<K,V>>, Map<K,V> or
            // Map<K, List<V>>. E,K,V must be a String, Integer, Float,
            // Double, Byte, Short, Long, BigInteger or BigDecimal.
            //
            // For other transformations you can register a DataTableType.
            List<Map<String,String>> lineItems = dataTable.asMaps();
            for (int i = 0; i < lineItems.size(); i++) {
                System.out.println("Validation line item : " + i);
                String expectedName = lineItems.get(i).get("Name");
                String actualName = spree.cartPage().getLineItemProductName(i);
                Assert.assertEquals(expectedName, actualName);

                String expectedPrice = lineItems.get(i).get("Price");
                String actualPrice = spree.cartPage().getLineItemProductPrice(i);
                Assert.assertEquals(expectedPrice, actualPrice);

                String expectedQuantity = lineItems.get(i).get("Quantity");
                String actualQuantity = spree.cartPage().getLineItemProductQuantity(i);
                Assert.assertEquals(expectedQuantity, actualQuantity);

                String expectedTotal = lineItems.get(i).get("Total");
                String actualTotal = spree.cartPage().getLineItemProductTotal(i);
                Assert.assertEquals(expectedTotal, actualTotal);
            }
        }

}

package stepDefinitions;

import io.cucumber.java.en.*;
import org.openqa.selenium.WebElement;
import org.pageObjects.*;
import org.prueba.TestComponenst.BaseTest;
import org.testng.Assert;

import java.io.IOException;
import java.util.List;

public class StepDefinitions extends BaseTest {

    public LandingPage landingPage;
    public ProductCatalogue productCatalogue;
    public ChekoutPage chekoutPage;
    public ConfirmationPage confirmationPage;
    String country = "Colombia";

     @Given("I landed on Ecommerce Page")
    public void I_landed_on_Ecommerce_Page() throws IOException {
            landingPage = launchApplication();
     }

    @Given("^Logged in with username (.+) and password (.+)$")
    public void Logged_in_with_username_and_password(String userName, String password)
    {
        productCatalogue = landingPage.loginAplication(userName, password);
    }

    @When("^I add product (.+) from cart$")
    public void I_add_product_from_cart(String productName)
    {
        List<WebElement> products = productCatalogue.getProductList();
        productCatalogue.addProductCart(productName);
    }

    @When("^Checkout (.+) and submit the order$")
    public void Checkout_and_submit_the_order(String productName)
    {
        CartPage cartPage = productCatalogue.gotoCartPage();
        Boolean match = cartPage.VerifyProductDisplay(productName);
        Assert.assertTrue(match);
        chekoutPage = cartPage.goToCheckout();
        chekoutPage.selectCountry(country);
        confirmationPage = chekoutPage.submitOrder();
    }


    @Then("{string} message is displayed on ConfirmationPage")
    public void message_is_displayed_on_ConfirmationPage(String string)
    {
        String confirmMessage = confirmationPage.getConfirmationMessage();
        Assert.assertTrue(confirmMessage.equalsIgnoreCase(string));
        driver.close();
    }


    @Then("{string} message is displayed")
    public void message_is_displayed(String string) {
        Assert.assertEquals(string,landingPage.getErrorMesssage());
        driver.close();
    }



}

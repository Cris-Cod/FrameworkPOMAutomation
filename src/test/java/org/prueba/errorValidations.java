package org.prueba;


import org.openqa.selenium.WebElement;
import org.pageObjects.*;
import org.prueba.TestComponenst.BaseTest;
import org.prueba.TestComponenst.Retry;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.List;

public class errorValidations extends BaseTest {



    @Test(groups = {"ErrorHandling"}, retryAnalyzer = Retry.class)
    public void LoginErrorValidation(){

        String productName = "ADIDAS ORIGINAL";
        landingPage.loginAplication("cristian@prueba2.com","Robot182");
        Assert.assertEquals("Incorrect email or password.",landingPage.getErrorMesssage());
    }

    @Test
    public void ProductErrorValidation() throws IOException {


        String productName = "ADIDAS ORIGINAL";

        ProductCatalogue productCatalogue = landingPage.loginAplication("cristian@prueba.com","Robot182#");
        List<WebElement>products = productCatalogue.getProductList();
        productCatalogue.addProductCart(productName);
        CartPage cartPage = productCatalogue.gotoCartPage();
        Boolean match = cartPage.VerifyProductDisplay("ADIDAS ORIGINAL V3");
        Assert.assertFalse(match);

    }

}

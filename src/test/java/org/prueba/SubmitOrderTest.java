package org.prueba;


import org.openqa.selenium.WebElement;
import org.pageObjects.*;
import org.prueba.TestComponenst.BaseTest;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

public class SubmitOrderTest extends BaseTest {
    String productName = "ADIDAS ORIGINAL";

   //
    String country = "Colombia";

        @Test(dataProvider = "getData", groups = {"Purchase"})
         public void submitOrder(HashMap<String, String>input) throws IOException {


            ProductCatalogue productCatalogue = landingPage.loginAplication(input.get("email"),input.get("password"));


            List<WebElement>products = productCatalogue.getProductList();
            productCatalogue.addProductCart(input.get("product"));
            CartPage cartPage = productCatalogue.gotoCartPage();



            Boolean match = cartPage.VerifyProductDisplay(input.get("product"));
            Assert.assertTrue(match);
            ChekoutPage chekoutPage = cartPage.goToCheckout();

            chekoutPage.selectCountry(country);
            ConfirmationPage confirmationPage = chekoutPage.submitOrder();

            String confirmMessage = confirmationPage.getConfirmationMessage();
            Assert.assertTrue(confirmMessage.equalsIgnoreCase("THANKYOU FOR THE ORDER."));



        }

        @Test(dependsOnMethods = {"submitOrder"})
        public void OrderHistoryTest(){
            ProductCatalogue productCatalogue = landingPage.loginAplication("cristian@prueba.com","Robot182#");
           OrderPage orderPage =  productCatalogue.gotoOrdersPage();
           Assert.assertTrue(orderPage.VerifyProductDisplay(productName));
        }



        @DataProvider
        public Object[][] getData() throws IOException {


            List<HashMap<String,String>> data = getJsdonDataToMap("src/test/java/org/prueba/data/PurchaseOrder.json");

            return new Object[][]{{data.get(0)},{data.get(1)}};
        }



            /*HashMap<String, String>map = new HashMap<String, String>();
            map.put("email", "cristian@prueba.com");
            map.put("password", "Robot182#");
            map.put("product","ADIDAS ORIGINAL");

            HashMap<String, String>map1 = new HashMap<String, String>();
            map1.put("email", "cristian@prueba.com");
            map1.put("password", "Robot182#");
            map1.put("product","IPHONE 13 PRO");*/

}

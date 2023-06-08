package org.pageObjects;

import org.AbstractComponets.AbstracComponent;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class CartPage extends AbstracComponent {

    WebDriver driver;

    @FindBy(css= ".totalRow button")
    WebElement checkoutEle;

    @FindBy(css= ".cartSection h3")
    private List<WebElement> productTitles;

    public CartPage(WebDriver driver){
        super(driver);
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }



    public Boolean VerifyProductDisplay(String productName){

        Boolean match = productTitles.stream().anyMatch(cartProduct->cartProduct.getText().equalsIgnoreCase(productName));

        return match;
    }

   public ChekoutPage goToCheckout(){
        checkoutEle.click();
        return new ChekoutPage(driver);
   }


}

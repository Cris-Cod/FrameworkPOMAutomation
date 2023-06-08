package org.pageObjects;

import org.AbstractComponets.AbstracComponent;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class ProductCatalogue extends AbstracComponent {

    WebDriver driver;

    public ProductCatalogue(WebDriver driver){
        super(driver);
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }


    //PageFactory
   @FindBy(css=".mb-3")
    List<WebElement> products;

    @FindBy(css=".ng-animating")
    WebElement spinnner;

    By productsBy = By.cssSelector(".mb-3");
    By addToCart = By.cssSelector(".card-body button:last-of-type");
    By toastMessage = By.cssSelector("#toast-container");


    public List<WebElement> getProductList(){

        waitForElementToAppear(productsBy);

        return products;
    }

    public WebElement getProductByName(String productName){
        WebElement prod = getProductList().stream().filter(product->
                        product.findElement(By.cssSelector("b")).getText().equals(productName))
                .findFirst().orElse(null);

        return prod;
    }

    public void addProductCart(String productName){
        WebElement prod = getProductByName(productName);
        prod.findElement(addToCart).click();
        waitForElementToAppear(toastMessage);
        waitForElementToDisappear(spinnner);
    }



}

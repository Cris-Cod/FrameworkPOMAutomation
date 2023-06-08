package org.pageObjects;

import org.AbstractComponets.AbstracComponent;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;


public class ChekoutPage extends AbstracComponent {

    WebDriver driver;
    public ChekoutPage(WebDriver driver) {
        super(driver);
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(css="[placeholder='Select Country']")
    WebElement country;

    @FindBy(xpath="//a[contains(@Class,'btnn')]")
    WebElement submit;

    @FindBy(xpath="//button[contains(@class,'ta-item')]")
    WebElement selectCountry;

    By results = By.cssSelector(".ta-results");

    public void selectCountry(String countryName){
        Actions a = new Actions(driver);
        a.sendKeys(country, countryName).build().perform();
        waitForElementToAppear(results);
        selectCountry.click();
    }

    public ConfirmationPage submitOrder(){
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("window.scrollBy(0,1000)");
        submit.click();
        return new  ConfirmationPage(driver);
    }


}

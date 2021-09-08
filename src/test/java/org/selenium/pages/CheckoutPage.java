package org.selenium.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.selenium.base.BasePage;
import org.selenium.objects.BillingAddress;
import org.selenium.objects.User;


public class CheckoutPage extends BasePage {
    private By firstNameFld = By.id("billing_first_name");
    private By lastNameFld = By.id("billing_last_name");
    private By addressLineOneFld = By.id("billing_address_1");
    private By billingCityFld = By.id("billing_city");
    private By billingPostcodeFld = By.id("billing_postcode");
    private By billingEmailFld = By.id("billing_email");
    private By placeOrderBtn = By.id("place_order");
    private By successNotice = By.cssSelector(".woocommerce-notice");

    private By clickHereToLoginLink = By.className("showlogin");
    private By usernameFld = By.id("username");
    private By passwordFld = By.id("password");
    private By loginBtn = By.name("login");
    private By overlay = By.cssSelector(".blockUI.blockOverlay");

    private By countryDropDown = By.id("billing_country");
    private By stateDropDown = By.id("billing_state");

    public CheckoutPage(WebDriver driver) {
        super(driver);
    }

    public CheckoutPage enterFirstName(String firsName){
        WebElement e = wait.until(ExpectedConditions.visibilityOfElementLocated(firstNameFld));
        e.clear();
        e.sendKeys(firsName);
        return this;
    }

    public CheckoutPage enterLastName(String lastName){
        WebElement e = wait.until(ExpectedConditions.visibilityOfElementLocated(lastNameFld));
        e.clear();
        e.sendKeys(lastName);
        return this;
    }

    public CheckoutPage selectCountry(String countryName){
        Select select = new Select(driver.findElement(countryDropDown));
        select.selectByVisibleText(countryName);
        return this;
    }

    public CheckoutPage enterAddressLineOne(String addressLineOne){
        WebElement e = wait.until(ExpectedConditions.visibilityOfElementLocated(addressLineOneFld));
        e.clear();
        e.sendKeys(addressLineOne);
        return this;
    }

    public CheckoutPage enterCity(String city){
        WebElement e = wait.until(ExpectedConditions.visibilityOfElementLocated(billingCityFld));
        e.clear();
        e.sendKeys(city);
        return this;
    }

    public CheckoutPage selectState(String stateName){
        wait.until(ExpectedConditions.visibilityOfElementLocated(stateDropDown)).sendKeys(stateName);
        return this;
    }

    public CheckoutPage enterPostCode(String postCode){
        WebElement e = wait.until(ExpectedConditions.visibilityOfElementLocated(billingPostcodeFld));
        e.clear();
        e.sendKeys(postCode);
        return this;
    }

    public CheckoutPage enterEmail(String email){
        WebElement e = wait.until(ExpectedConditions.visibilityOfElementLocated(billingEmailFld));
        e.clear();
        e.sendKeys(email);
        return this;
    }

    public CheckoutPage setBillingAddress(BillingAddress billingAddress){
        return  enterFirstName(billingAddress.getFirstName()).
                enterLastName(billingAddress.getLastName()).
                selectCountry(billingAddress.getCountry()).
                enterAddressLineOne(billingAddress.getAddressLineOne()).
                enterCity(billingAddress.getCity()).
                selectState(billingAddress.getState()).
                enterPostCode(billingAddress.getPostalCode()).
                enterEmail(billingAddress.getEmail());
    }

    public CheckoutPage placeOrder(){
        waitForOverlaysToDisappear(overlay);
        driver.findElement(placeOrderBtn).click();
        return this;
    }

    public String getNotice(){
        return  wait.until(ExpectedConditions.visibilityOfElementLocated(successNotice)).getText();
    }

    public CheckoutPage clickHereToLoginLink(){
        wait.until(ExpectedConditions.elementToBeClickable(clickHereToLoginLink)).click();
        return this;
    }

    public CheckoutPage enterUserName(String username){
        wait.until(ExpectedConditions.visibilityOfElementLocated(usernameFld)).sendKeys(username);
        return this;
    }

    public CheckoutPage enterPassword(String password){
        wait.until(ExpectedConditions.visibilityOfElementLocated(passwordFld)).sendKeys(password);
        return this;
    }

    public CheckoutPage clickLoginBtn(){
        wait.until(ExpectedConditions.elementToBeClickable(loginBtn)).click();
        return this;
    }

    public CheckoutPage login(User user){
        return enterUserName(user.getUsername()).
               enterPassword(user.getPassword()).
               clickLoginBtn();
    }
}

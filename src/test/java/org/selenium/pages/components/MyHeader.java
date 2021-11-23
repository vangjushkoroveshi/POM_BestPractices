package org.selenium.pages.components;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.selenium.base.BasePage;
import org.selenium.pages.StorePage;

public class MyHeader extends BasePage {
    private By storeMenuLink = By.xpath("//li[@id='menu-item-1227']/a");

    public MyHeader(WebDriver driver) {
        super(driver);
    }

    public StorePage navigateToStoreUsingMenu(){
        wait.until(ExpectedConditions.elementToBeClickable(storeMenuLink)).click();
        return new StorePage(driver);
    }
}

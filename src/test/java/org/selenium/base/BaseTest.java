package org.selenium.base;

import org.openqa.selenium.WebDriver;
import org.selenium.factory.DriverManager;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

public class BaseTest {

    protected WebDriver driver;

    @BeforeMethod
    public void setUp(){
        driver = new DriverManager().initializeDriver();
    }

    @AfterMethod
    public void tearDown(){
        driver.quit();
    }
}

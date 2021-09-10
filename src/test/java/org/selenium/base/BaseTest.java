package org.selenium.base;

import org.openqa.selenium.WebDriver;
import org.selenium.factory.DriverManager;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;

public class BaseTest {

    protected WebDriver driver;

    @Parameters("browser")
    @BeforeMethod
    public void setUp(String browser){
        driver = new DriverManager().initializeDriver(browser);
    }

    @AfterMethod
    public void tearDown(){
        driver.quit();
    }
}

package com.ubs.components;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class PageObject {

    private WebDriver driver;

    public WebDriver getDriver() {
        return driver;
    }

    public PageObject(WebDriver driver) {
        this.driver = driver;
    }

    public boolean checkItemVisibility (WebElement webElement) {
        return webElement.isDisplayed();
    }
}

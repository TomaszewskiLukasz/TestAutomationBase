package com.ubs.pages;

import com.ubs.components.PageObject;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class UBSHomePage extends PageObject {

    @FindBy (linkText = "Global topics")
    private WebElement globalTopicHeader;

    public UBSHomePage(WebDriver driver) {
        super(driver);
    }

    public UBSHomePage goToHomePage() {
        getDriver().get("https://www.ubs.com/global/en.html");
        return PageFactory.initElements(getDriver(), UBSHomePage.class);
    }

    public boolean checkHeaderVisibility() {
        return checkItemVisibility(globalTopicHeader);
    }
}

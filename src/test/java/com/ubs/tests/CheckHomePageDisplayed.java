package com.ubs.tests;

import com.ubs.components.TestBase;

import com.ubs.pages.UBSHomePage;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.annotations.Test;

public class CheckHomePageDisplayed extends TestBase {

    @Test
    public void checkHomePageDisplayed() {

        //given
        UBSHomePage ubsHomePage = PageFactory.initElements(getDriver(), UBSHomePage.class);

        //when
        ubsHomePage.goToHomePage();

        //then
        Assert.assertTrue(ubsHomePage.checkHeaderVisibility());
    }
}

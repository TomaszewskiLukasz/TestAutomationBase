package com.ubs.components;

import com.ubs.pages.UBSHomePage;
import org.openqa.selenium.UnsupportedCommandException;
import org.openqa.selenium.support.PageFactory;
import org.testng.ITestResult;
import org.testng.annotations.*;

import java.io.IOException;
import java.lang.reflect.Method;

public class TestBase extends Utils {

    public static TestLog logger = new TestLog();
    protected static ThreadLocal<UBSHomePage> homePage = new ThreadLocal<>();

    @BeforeSuite(alwaysRun = true)
    public void startTestExecutionTestRun() {
        Utils.beforeConfiguration();
    }

    @BeforeClass(alwaysRun = true)
    public void startTestExecutionBeforeClass() {
        logger.logStartTestClassExecution(getClass().getSimpleName());
    }

    @BeforeMethod(alwaysRun = true)
    public void startTestExecutionBeforeMethod(Method testMethod) {
        logger.logStartTestExecution(testMethod.getName());
        homePage.set(PageFactory.initElements(chooseAndLaunchBrowser(), UBSHomePage.class));
    }

    @AfterMethod(alwaysRun = true)
    public void logEndTestExecutionAfterMethod(ITestResult result, Method testMethod) throws UnsupportedCommandException {
        setTestResults(result, testMethod);
        driver.get().quit();
    }

    @AfterClass(alwaysRun = true)
    public void endTestExecutionAfterClass() throws IOException, InterruptedException {
        logger.logEndTestClassExecution(getClass().getSimpleName());
        killDrivers();
    }

    private void setTestResults(ITestResult result, Method testMethod) {
        if (result.getStatus() == ITestResult.SUCCESS) {
            logger.info("Test PASSED: " + testMethod.getName());
        }
        if (result.getStatus() == ITestResult.SKIP) {
            logger.warn("Test SKIPPED: " + testMethod.getName());
        }
        if (result.getStatus() == ITestResult.FAILURE) {
            logger.error("Test FAILED: " + testMethod.getName());
        }

        logger.logEndTestExecution(testMethod.getName(), result.getStatus());
    }
}

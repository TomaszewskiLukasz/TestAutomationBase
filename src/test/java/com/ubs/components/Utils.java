package com.ubs.components;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;
import java.util.concurrent.TimeUnit;

import static com.ubs.components.TestBase.*;

public class Utils {

    private static final int SLEEP_INTERVAL = 5000;

    private static final String IE_BROWSER = "internet explorer";
    private static final String CHROME_BROWSER = "chrome";
    private static final String FIREFOX_BROWSER = "firefox";

    private static String BROWSER_TYPE = System.getProperty("BROWSER_TYPE");

    protected static ThreadLocal<WebDriver> driver = new ThreadLocal<>();
    protected static String startTimestamp = "";


    public static String currentTimeStamp() {
        String timestamp;
        SimpleDateFormat simpleDateFormat = new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
        simpleDateFormat.setTimeZone(TimeZone.getTimeZone("GMT"));
        timestamp = simpleDateFormat.format(new Date());
        return timestamp;
    }

    public static void killDrivers() throws IOException, InterruptedException {
        if (BROWSER_TYPE.equals(IE_BROWSER)) {
            Runtime.getRuntime().exec("taskkill /F /IM IEdriverServer.exe");
            Runtime.getRuntime().exec("taskkill /F /IM iexplore.exe");
        }
        if (BROWSER_TYPE.equals(CHROME_BROWSER)) {
            Runtime.getRuntime().exec("taskkill /F /IM chromedriver.exe");
            Runtime.getRuntime().exec("taskkill /F /IM chrome.exe");
            Runtime.getRuntime().exec("taskkill /F /IM chromedriver_v2.2.exe");
            Runtime.getRuntime().exec("taskkill /F /IM chromedriver_win_32.exe");
        }
        if (BROWSER_TYPE.equals(FIREFOX_BROWSER)) {
            Runtime.getRuntime().exec("taskkill /F /IM firefox.exe");
        }

        Thread.sleep(SLEEP_INTERVAL);
    }

    public static synchronized WebDriver chooseAndLaunchBrowser() {
        if (BROWSER_TYPE == null) {
            BROWSER_TYPE = "chrome";
        }
        if ((FIREFOX_BROWSER).equals(BROWSER_TYPE)) {
            driver.set(new FirefoxDriver());
            driver.get().manage().window().maximize();
            driver.get().manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
        }
        if ((CHROME_BROWSER).equals(BROWSER_TYPE)) {
            ChromeOptions chromeOptions = new ChromeOptions();
            chromeOptions.setCapability(CapabilityType.ForSeleniumServer.ENSURING_CLEAN_SESSION, true);
            driver.set(new ChromeDriver(chromeOptions));
            driver.get().manage().deleteAllCookies();
            driver.get().manage().window().maximize();
            driver.get().manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
        }
        if ((IE_BROWSER).equals(BROWSER_TYPE)) {
            DesiredCapabilities capabilities = DesiredCapabilities.internetExplorer();
            capabilities.setCapability(InternetExplorerDriver.INTRODUCE_FLAKINESS_BY_IGNORING_SECURITY_DOMAINS, true);
            capabilities.setCapability("ignoreProtectedModeSettings", true);
            capabilities.setCapability(InternetExplorerDriver.IE_ENSURE_CLEAN_SESSION, true);
            driver.set(new InternetExplorerDriver(capabilities));
            driver.get().manage().window().maximize();
            driver.get().manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
        }
        return driver.get();
    }

    public static void beforeConfiguration() {
        startTimestamp = currentTimeStamp();

        if (System.getProperty("webdriver.ie.driver") == null) {
            System.setProperty("webdriver.ie.driver", "src\\resources\\drivers\\IEdriverServer.exe");
        }
        if (System.getProperty("webdriver.edge.driver") == null) {
            System.setProperty("webdriver.edge.driver", "src\\resources\\drivers\\MicrosoftWebDriver.exe");
        }
        if (System.getProperty("webdriver.chrome.driver") == null) {
            System.setProperty("webdriver.chrome.driver", "src/resources/drivers/chromedriver.exe");
        }
        if (System.getProperty("webdriver.gecko.driver") == null) {
            System.setProperty("webdriver.gecko.driver", "src\\resources\\drivers\\geckodriver.exe");
        }
    }

    public static synchronized WebDriver getDriver() {
        return driver.get();
    }

//    public static synchronized void closeDriver() {
//        if (driver.get() != null) {
//            driver.get().quit();
//            driver.set(null);
//            logger.info("Browser has been closed");
//        }
//    }
}

package sergii.kryvenko.selenium.testng;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Parameters;

import io.github.bonigarcia.wdm.WebDriverManager;

public abstract class OpenCartTestNGRunner {
    
    private final Long ONE_SECOND_DELAY = 1000L;

    protected WebDriver driver;
    protected WebDriverWait wait;

    /**
     * Checking webdriver and download it if needed.
     */
    @BeforeSuite
    public void beforeSuite() {

        // check and download chromedriver
        WebDriverManager.chromedriver().setup();

    }

    /**
     * Instant ChromeDriver, activate BrowserStack/SauceLab capabilities,
     * logging
     * @param waitTime
     * @throws MalformedURLException
     */
    @BeforeClass
    @Parameters(value = "waitTime")
    public void beforeClass(int waitTime) throws MalformedURLException {

        //
        // BrowserStack
//        String browserStackUsername = System.getenv("BROWSERSTACK_USERNAME");
//        String browserStackAccess_key = System
//                .getenv("BROWSERSTACK_ACCESS_KEY");
//        String URL = "https://" + browserStackUsername + ":"
//                + browserStackAccess_key + "@hub-cloud.browserstack.com/wd/hub";
//        DesiredCapabilities caps = new DesiredCapabilities();
//        caps.setCapability("browser", "Chrome");
//        caps.setCapability("browser_version", "80.0");
//        caps.setCapability("os", "Windows");
//        caps.setCapability("os_version", "10");
//        caps.setCapability("resolution", "1024x768");
//        caps.setCapability("name", "Bstack-[Java] Open Cart Test_14.04.20");
//        caps.setCapability("browserstack.local", "true");

        // SauceLab
        String sauceUserName = System.getenv("SAUCE_USERNAME");
        String sauceAccessKey = System.getenv("SAUCE_ACCESS_KEY");

        DesiredCapabilities caps = new DesiredCapabilities();
        caps.setCapability("username", sauceUserName);
        caps.setCapability("accessKey", sauceAccessKey);
        caps.setCapability("browserName", "Chrome");
        caps.setCapability("version", "80.0");
        caps.setCapability("platformName", "Windows");
        caps.setCapability("platformVersion", "10");
        caps.setCapability("name", "SauceLab: Open Cart Test3_21.04.20");
        String URL = "https://ondemand.eu-central-1.saucelabs.com:443/wd/hub";
//

        // BrowserStack, SauceLab
//        driver = new RemoteWebDriver(new URL(URL), caps);

        //
        driver = new ChromeDriver();
        wait = new WebDriverWait(driver, waitTime);
        //
        driver.manage().timeouts().implicitlyWait(waitTime, TimeUnit.SECONDS);
        String siteURL = System.getProperty("surefire.application.url");
        driver.get(siteURL);
        driver.manage().window().maximize();
        driver.findElement(
                By.cssSelector("#top-links a[href*='route=account/account']"))
                .click();

        // logging
        String login = System.getenv("OPENCART_LOGIN");
        String password = System.getenv("OPENCART_PASSWORD");

        driver.findElement(
                By.cssSelector("#top-links a[href*='route=account/login']"))
                .click();

        driver.findElement(By.id("input-email")).click();
        driver.findElement(By.id("input-email")).clear();
        driver.findElement(By.id("input-email")).sendKeys(login);

        driver.findElement(By.id("input-password")).click();
        driver.findElement(By.id("input-password")).clear();
        driver.findElement(By.id("input-password")).sendKeys(password);

        driver.findElement(By.id("input-password")).submit();
        System.out.println("@Logging");

    }

    /**
     * Logout and close webdriver.
     * @param waitTime
     */
    @AfterClass(alwaysRun = true)
    @Parameters(value = "waitTime")
    public void afterClass(int waitTime) {

        driver.manage().timeouts().implicitlyWait(0, TimeUnit.SECONDS);
        wait.until(ExpectedConditions.elementToBeClickable(
                By.cssSelector("#top-links a[class='dropdown-toggle']")));
        driver.manage().timeouts().implicitlyWait(waitTime, TimeUnit.SECONDS);

        // logout
        driver.findElement(
                By.cssSelector("#top-links a[class='dropdown-toggle']"))
                .click();
        driver.findElement(
                By.cssSelector("#top-links a[href*='route=account/logout']"))
                .click();
        System.out.println("@Logout");
        //
        driver.quit();
    }

    /**
     * Return to begin page.
     * @param waitTime
     */
    @BeforeMethod
    @Parameters(value = "waitTime")
    public void beforeMethod(int waitTime) {

        // return to begin page
        driver.manage().timeouts().implicitlyWait(0, TimeUnit.SECONDS);
        wait.until(ExpectedConditions.elementToBeClickable(
                By.cssSelector("img[title='Your Store']")));
        driver.manage().timeouts().implicitlyWait(waitTime, TimeUnit.SECONDS);
        //
        driver.findElement(By.cssSelector("img[title='Your Store']")).click();
        System.out.println("@Open begins page");
    }

    /**
     * Return to begin page and activate script SauceLabs.
     * @param result
     * @param waitTime
     */
    @AfterMethod
    @Parameters(value = "waitTime")
    public void afterMethod(ITestResult result, int waitTime) {

        // return to begin page
        driver.manage().timeouts().implicitlyWait(0, TimeUnit.SECONDS);
        wait.until(ExpectedConditions.elementToBeClickable(
                By.cssSelector("img[title='Your Store']")));
        driver.manage().timeouts().implicitlyWait(waitTime, TimeUnit.SECONDS);
        //
        driver.findElement(By.cssSelector("img[title='Your Store']")).click();
        System.out.println("@Open begins page");

        // Quitting the driver and passing the test result to Sauce Labs user
        // interface.
//        ((JavascriptExecutor) driver).executeScript("sauce:job-result="
//                + (result.isSuccess() ? "passed" : "failed"));
    }
    
    protected void presentationSleep() {
        presentationSleep(1);
    }
    
    protected void presentationSleep(int seconds) {
        try {
            Thread.sleep(seconds * ONE_SECOND_DELAY); // For Presentation ONLY
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

}

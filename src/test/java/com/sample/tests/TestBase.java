package test.java.com.sample.tests;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

public class TestBase {

    private static WebDriver driver;

    public WebDriver getWebDriver() {
        System.setProperty("webdriver.chrome.driver","./src/test/resources/drivers/chromedriver.exe");
       //uncomment below for Windows - Chrome
        ChromeOptions options = new ChromeOptions();
        options.addArguments("start-maximized","--disable-extensions","chrome.switches");
        driver = new ChromeDriver(options);

        System.setProperty("webdriver.gecko.driver","./src/test/resources/drivers/geckodriver");
        //uncomment below for Mac - Firefox
        /*FirefoxOptions options = new FirefoxOptions();
        options.addArguments("start-maximized","--disable-extensions");
        driver = new FirefoxDriver(options);*/

        driver.manage().window().maximize();
        return driver;
    }

    public void closePage(){
        driver.quit();
    }
}

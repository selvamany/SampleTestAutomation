package main.java.com.sample.pages;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;
import test.java.com.sample.tests.TestBase;

public class GoogleSearchPage extends TestBase {
    private static WebDriver driver;
    WebDriverWait wait;

    @FindBy(xpath = "//input[contains(@title, 'Search')]")
    private WebElement searchInput;
    @FindBy(xpath = "//input[@value='Google Search'][1]")
    private WebElement searchSubmit;
    @FindBy(xpath = "(//h3)[1]")
    private WebElement searchResults;

    public GoogleSearchPage(){
        driver = getWebDriver();
        PageFactory.initElements(driver, this);
    }

    public void accessGooglePage(){
        driver.get("https://google.com");
    }

    public void submitSearchText(String text) throws Exception{
        searchInput.sendKeys(text);
        searchInput.sendKeys(Keys.ENTER);
        Thread.sleep(5000);
    }

    public String getSearchResults(){
       return searchResults.getText();
    }
}

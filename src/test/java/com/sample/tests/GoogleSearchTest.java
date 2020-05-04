package test.java.com.sample.tests;

import main.java.com.sample.pages.BorrowCalcPage;
import main.java.com.sample.pages.GoogleSearchPage;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class GoogleSearchTest {

    private GoogleSearchPage googleSearchPage;
    @BeforeTest
    public void startBrowser() throws Exception{
        googleSearchPage = new GoogleSearchPage();
    }

    @AfterTest
    public void quitBrowser() throws Exception{
     googleSearchPage.closePage();
    }

    @Parameters({ "search-text" })
    @Test
    public void googleSearchTest(String searchString ) throws Exception{
        googleSearchPage.accessGooglePage();
        googleSearchPage.submitSearchText(searchString);
        Assert.assertTrue(googleSearchPage.getSearchResults().contains(searchString),"Search Results not containing - "+searchString);
    }
}

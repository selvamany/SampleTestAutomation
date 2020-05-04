package test.java.com.sample.tests;

import java.util.HashMap;
import java.util.Map;
import main.java.com.sample.pages.BorrowCalcPage;
import org.testng.Assert;
import org.testng.annotations.*;


public class BorrowClacTest {
	
	private BorrowCalcPage borrowCalcPage;

	@BeforeTest
	public void startBrowser() throws Exception{
	        borrowCalcPage = new BorrowCalcPage();
		 borrowCalcPage.loadBorrowCalcPage();
	    }

     @AfterTest
	    public void quitBrowser() throws Exception{
	    	borrowCalcPage.closePage();
  	    }
	

	@Test(dataProvider = "data-provider")
	public void user_enters_below_details_for_borrow_Calc(Map<String,String> inputMap) throws Exception{
		borrowCalcPage.enterFormDetails(inputMap);
		borrowCalcPage.submitBorrowCalc();
		System.out.print(borrowCalcPage.getBorrowResultAmount());
		System.out.print(inputMap.get("expectedLoadAmount"));
		Assert.assertTrue(borrowCalcPage.getBorrowResultAmount().equals(inputMap.get("expectedLoanAmount")),"Estimated Borrow Amount NOT matching...");
	}

	@DataProvider(name = "data-provider")
	public Object[][] dataProviderMethod() {

		Map<Object, Object> datamap = new HashMap<>();
			datamap.put("income", "80000");
		datamap.put("otherIncome", "10000");
		datamap.put("livingExp", "500");
		datamap.put("otherLoan", "100");
		datamap.put("totalCCLimits", "10000");
		datamap.put("expectedLoanAmount", "$488,000");
		 return new Object[][]{{datamap}};

	}

}



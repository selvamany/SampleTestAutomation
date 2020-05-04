package main.java.com.sample.pages;

import java.util.List;
import java.util.Map;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import test.java.com.sample.tests.TestBase;

/**
 * 
 * Page Object class for the Borrow Calculation Page
 *
 */
public class BorrowCalcPage extends TestBase {

	private static WebDriver driver;
	WebDriverWait wait;

	@FindBy(id = "application_type_single")
	private WebElement singleAppTypeToggle;
	@FindBy(id = "application_type_joint")
	private WebElement jointAppTypeToggle;
	@FindBy(id = "borrow_type_home")
	private WebElement borrowTypeHomeToggle;
	@FindBy(id = "borrow_type_investment")
	private WebElement borrowTypeInvestToggle;

	@FindBy(xpath = "//label[contains(text(),'Your income')]//following::input[1]") 
	private WebElement yourIncomeInput;
	@FindBy(xpath = "//label[contains(text(),'Your other income')]//following::input[1]")
	private WebElement otherIncomeInput;
	@FindBy(xpath =  "//label[contains(text(),'Living expenses')]//following::input[1]")
	private WebElement livingExpenseInput;
	@FindBy(xpath =  "//label[contains(text(),'credit card limits')]//following::input[1]")
	private WebElement creditCardLimitInput;
	@FindBy(xpath ="//label[contains(text(),'Other loan repayments')]//following::input[1]")
	private WebElement otherLoadnRepayInput;
	@FindBy(xpath ="//span[@class='borrow__result__text__amount']")
	private WebElement borrowAmount;
	@FindBy(xpath ="//span[@class='borrow__error__text']")
	private WebElement borrowErrorText;
	@FindBy(xpath ="//span[contains(text(),'Tools to help you get on top')]")
	private WebElement helpText;

	@FindBy(xpath =  "//button[contains(text(),'Work out how much')]")
	private WebElement borrowCalcButton;
	@FindBy(xpath = "//span[@class='borrow__result__text__amount']//following::button[1]")
	private WebElement startOverButton;

	@FindBy(xpath ="//span[@class='currency']//following::input")
	private List<WebElement> currencyInputList;

	private Boolean clear;

	public BorrowCalcPage() {
		driver = getWebDriver();
		wait=new WebDriverWait(driver,20);
		PageFactory.initElements(driver, this);
	}

	public BorrowCalcPage loadBorrowCalcPage() {
		try {
			driver.get("https://www.anz.com.au/personal/home-loans/calculators-tools/much-borrow/");
			wait.until(driver -> ((JavascriptExecutor) driver).executeScript("return document.readyState").equals("complete"));
			if(!helpText.isDisplayed()) {
				driver.navigate().refresh();
			}
		} catch (NoSuchElementException e) {
			driver.navigate().refresh();
		}
		return this;
	}

	public BorrowCalcPage setYourIncome(String income) {
		yourIncomeInput.sendKeys(income);
		return this;
	}

	public BorrowCalcPage setOtherIncome(String income) {
		otherIncomeInput.sendKeys(income);
		return this;
	}

	public BorrowCalcPage setLivingExp(String exp) {
		livingExpenseInput.sendKeys(exp);
		return this;
	}

	public BorrowCalcPage setCreditCardLimit(String ccLimit) {
		creditCardLimitInput.sendKeys(ccLimit);
		return this;
	}

	public BorrowCalcPage setOtherLoanRepayment(String amount) {
		otherLoadnRepayInput.sendKeys(amount);
		return this;
	}

	public String getBorrowResultAmount() {
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		wait.until(ExpectedConditions.visibilityOf(borrowAmount));
		return borrowAmount.getText();
	}

	public String getBorrowErrorMessage() {
		wait.until(ExpectedConditions.visibilityOf(borrowErrorText));
		return borrowErrorText.getText();
	}
	public BorrowCalcPage submitBorrowCalc()throws Exception{
		wait.until(ExpectedConditions.elementToBeClickable(borrowCalcButton));
		JavascriptExecutor executor = (JavascriptExecutor)driver;
		executor.executeScript("arguments[0].scrollIntoView(true);", otherLoadnRepayInput);
		executor.executeScript("arguments[0].click();", borrowCalcButton);
		Thread.sleep(3000);
		return this;
	}

	public BorrowCalcPage startOver() {
		startOverButton.click();
		return this;
	}

	public Boolean isFormInputsCleared() {
		clear=true;
		for(WebElement webElement : currencyInputList){
			if(!webElement.getAttribute("value").contentEquals("0")) {
				clear=false;
				break;
			}
		} 
		return clear;
	}

	public void enterFormDetails(Map<String,String> inputDetails) throws Exception{
		setYourIncome(inputDetails.get("income"));
		setOtherIncome(inputDetails.get("otherIncome"));
		setLivingExp(inputDetails.get("livingExp"));
		setOtherLoanRepayment(inputDetails.get("otherLoan"));
		setCreditCardLimit(inputDetails.get("totalCCLimits"));
		Thread.sleep(3000);
	}

}

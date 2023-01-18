package TestcaseScripts;

import org.testng.annotations.Test;

import io.qameta.allure.Description;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Story;

//import com.aventstack.extentreports.Status;

import pagelements.LoginPage;
import utilities.BrowserLaunch;
import utilities.ExcelUtility;
import utilities.PageUtilities;
import utilities.ReadConfigProperties;
import utilities.Screenshot;
import utilities.WaitConditions;

import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.ITestListener;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;

public class LoginTest extends BrowserLaunch {
	WebDriver driver;
	WaitConditions objWaitConditions = new WaitConditions();
	ExcelUtility objExcelUtility;
	ReadConfigProperties objReadConfigProperties = new ReadConfigProperties();
	Screenshot objScreenshot = new Screenshot();
	PageUtilities objPageUtilities = new PageUtilities();
	LoginPage objLogin;

	@Severity(SeverityLevel.NORMAL)
	@Description("Login scenario with invalid credentials")
	@Story("Verify Login with invalid username{0}, password {1}")
	@Test(priority = 1, enabled = true, groups = {
			"Smoke" }, description = "Invalid Login scenario with wrong Username ")
	public void verifyInvalidLoginfromExcel() throws IOException {
		this.driver = super.driver;
		objLogin = new LoginPage(driver);
		objLogin.clearCredentials();
		objExcelUtility = new ExcelUtility();
		String usr = objExcelUtility.readData(0, 1);
		String pass = objExcelUtility.readData(1, 1);
		objLogin.enterCredentials(usr, pass);
		objPageUtilities.clickOnElement(objLogin.LloginButton);
		objWaitConditions.waitForElementTobeVisible(driver, objLogin.LErrormsg, 5);
		String actualMsg = objLogin.LErrormsg.getText();
		String expectedMsg = "These credentials do not match our records.";
		Assert.assertEquals(actualMsg, expectedMsg, "Warning message comparison failed");
//		Assert.assertTrue(false);
	}

	@Test(priority = 2, enabled = true, groups = {
			"Smoke" }, dataProvider = "data-provider", description = "Login scenario with Valid credentials.")
	public void verifyLogin1(String state1, String state2) throws InterruptedException, IOException {
		objLogin.clearCredentials();
		objLogin.enterCredentials(state1, state2);
		objPageUtilities.clickOnElement(objLogin.LRememberChkBox);
		objPageUtilities.clickOnElement(objLogin.LloginButton);
		Assert.assertTrue(objLogin.isHomePageLoaded());
	}

	@DataProvider(name = "data-provider")
	public Object[][] dpMethod() {
		return new Object[][] { { "admin", "123456" } };
	}
}









//	@BeforeTest(alwaysRun = true)
////  @Parameters ({"Url","Browser"})
////  public void beforeTest(String Url, String Browser) throws IOException {
//	public void beforeTest() {
//		String url = "https://qalegend.com/billing/public/login";
//		launchingBrowser(url, "Chrome");
////	    launchingBrowser(Url, Browser);
//		this.driver = super.driver;
//		objLogin = new LoginPage(driver);
//	}
//
//	@AfterTest(alwaysRun = true)
//	public void afterTest() {
//		closeBrowser();
//	}

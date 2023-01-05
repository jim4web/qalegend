package TestcaseScripts;

import org.testng.annotations.Test;

//import com.aventstack.extentreports.Status;

import pagelements.LoginPage;
import utilities.BrowserLaunch;
import utilities.ExcelUtility;
import utilities.ReadConfigProperties;
import utilities.Screenshot;
import utilities.WaitConditions;

import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Parameters;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.ITestListener;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
		

public class LoginTest extends BaseClass {
	WebDriver driver;
	LoginPage objLogin;
	BrowserLaunch objLaunch=new BrowserLaunch();
	WaitConditions objWaitConditions= new WaitConditions();
	ExcelUtility objExcelUtility;
	ReadConfigProperties objReadConfigProperties= new ReadConfigProperties();
	Screenshot objScreenshot = new Screenshot();
	
	@Test (priority = 1,enabled = true, groups = {"Smoke"}, description = "Invalid Login scenario with wrong Username ")
  public void verifyInvalidLoginfromExcel() throws IOException 
	{
	  objLogin.clearCredentials();	
	  objExcelUtility=new ExcelUtility();
	  String usr= objExcelUtility.readData(0, 1);
	  String pass= objExcelUtility.readData(1, 1);
	  System.out.println(usr);
	  System.out.println(pass);
	  objLogin.enterCredentials(usr, pass);
	  objLogin.loginClick(); 
	  objWaitConditions.waitS(3000);
	  objLogin.comparingMessage();
  }
	
	@Test (priority = 2,enabled = true, groups = {"Smoke"}, dataProvider = "data-provider", description = "Login scenario with Valid credentials.")
	  public void verifyLogin1(String state1, String state2) throws InterruptedException, IOException 
	{
		  objLogin.clearCredentials();
		  objLogin.enterCredentials(state1, state2);
		  objLogin.tickChkBox();
		  objLogin.loginClick();
		  objWaitConditions.waitS(2000);
		  if (objLogin.isHomePageLoaded())
		  {
			  Assert.assertTrue(true);
		  } else
		  {
			  Assert.assertTrue(false);
		  }
	}
  
  @DataProvider (name = "data-provider")
  public Object[][] dpMethod() 
  {
      return new Object [][] { 
    	  {"admin","123456"}};
  }
  
//  @AfterMethod
//  public void afterMethod(ITestResult iTestResult) throws IOException {
//      if (iTestResult.getStatus() == iTestResult.FAILURE) {
//    	  objScreenshot.failureScreenshot(driver,iTestResult.getName()); 
//    	  driver.quit();
//      }  
//  }  

 
  @BeforeTest (alwaysRun = true)
  @Parameters ({"Url","Browser"})
  public void beforeTest(String Url, String Browser) 
//  public void beforeTest()
  {
//	  String url="https://qalegend.com/billing/public/login";
//	  objLaunch.launchingBrowser(url, "Chrome");
	  objLaunch.launchingBrowser(Url, Browser);
	  this.driver=objLaunch.driver;
	  objLogin=new LoginPage(driver);
  }

  @AfterTest (alwaysRun = true)
  public void afterTest()
  {
	  objLaunch.closeBrowser();
  }
  
}
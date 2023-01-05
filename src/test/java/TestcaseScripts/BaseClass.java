package TestcaseScripts;

import org.testng.annotations.Test;

import pagelements.LoginPage;
import utilities.BrowserLaunch;
import utilities.Screenshot;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;

public class BaseClass {
	WebDriver driver;
	BrowserLaunch objLaunch=new BrowserLaunch();
	Screenshot objScreenshot = new Screenshot();
	LoginPage objLogin;

//  @Test
//  public void f() {
//	  System.out.println("I am from base class...");
//  }
  @AfterMethod (alwaysRun = true)
  public void afterMethod (ITestResult iTestResult) throws IOException {
	      this.driver=objLaunch.driver;
	      objLogin= new LoginPage(driver);
	      if (iTestResult.getStatus() == iTestResult.FAILURE) {
	    	  objScreenshot.failureScreenshot(driver,iTestResult.getName()); 
	      }  
  }
}
	



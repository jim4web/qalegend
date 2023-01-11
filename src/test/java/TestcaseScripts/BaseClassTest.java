package TestcaseScripts;

import org.testng.annotations.Test;

import io.qameta.allure.Attachment;
import pagelements.LoginPage;
import utilities.BrowserLaunch;
import utilities.Screenshot;

import java.io.IOException;
import java.util.Random;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;

public class BaseClassTest {
	WebDriver driver;
	BrowserLaunch objLaunch=new BrowserLaunch();
	Screenshot objScreenshot = new Screenshot();
	LoginPage objLogin;

  @AfterMethod (alwaysRun = true)
  public void afterMethod (ITestResult iTestResult) throws IOException {
	      this.driver=objLaunch.driver;
	      objLogin= new LoginPage(driver);
	      if (iTestResult.getStatus() == iTestResult.FAILURE) {
	    	  objScreenshot.failureScreenshot(driver,iTestResult.getName()); 
	      }    
//attach screenshots to report
		saveFailureScreenShot(driver);
	}
	
	@Attachment (value = "Screenshot", type = "jpg")
	public byte[] saveFailureScreenShot(WebDriver driver) {
		return ((TakesScreenshot)driver).getScreenshotAs(OutputType.BYTES);
	}

  
  public int randomNumber() {
	  Random random= new Random();
	  int limit=1000;
	  int randomvalue=random.nextInt(limit);
	  return randomvalue;
  }
  
}
	



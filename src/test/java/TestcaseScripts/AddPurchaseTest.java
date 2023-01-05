package TestcaseScripts;

import org.testng.annotations.Test;

import pagelements.AddPurchasePage;
import pagelements.LoginPage;
import pagelements.ProductPage;
import utilities.BrowserLaunch;
import utilities.ExcelUtility;
import utilities.ReadConfigProperties;
import utilities.Screenshot;
import utilities.WaitConditions;

import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterTest;

public class AddPurchaseTest {
	WebDriver driver;
	LoginPage objLogin;
	Screenshot objScreenshot;
	ExcelUtility objExcelUtility;
	AddPurchasePage objAddPurchasePage;
	ProductPage objProductPage;
	BrowserLaunch objLaunch=new BrowserLaunch();
	WaitConditions objwaitconditions=new WaitConditions();
	ReadConfigProperties objReadConfigProperties= new ReadConfigProperties();
	
	@Test (priority = 1,enabled = true, groups = {"Regression"}, description = "Adding new Supplier details in Add Purchase.")
	  public void addSupplierDetails() throws InterruptedException, IOException 
	  {
		  driver.navigate().refresh();
		  objwaitconditions.waitS(5000);
		  objAddPurchasePage.addSupplier();
		  objwaitconditions.waitS(5000);
	  }
	
	 @Test (priority = 2,enabled = true, groups = {"Regression"}, description = "Validate the File Uploading in Add Purchase.")
	  public void attachDocInAddPurchase() throws InterruptedException, IOException 
	  {
		  objwaitconditions.waitS(5000);
		  objAddPurchasePage.uploadAddPurchaseFile(objAddPurchasePage.BrowsePurchaseDoc, "C:\\Users\\RS\\Desktop\\testfile.txt");
		  objwaitconditions.waitS(5000);
		  objAddPurchasePage.checkUploading(objAddPurchasePage.AttachspacePurchaseDoc);
	  }
	  
	  @Test (priority = 3,enabled = true, groups = {"Regression"}, description = "Validate the Removal of Uploaded File in Add Purchase.")
	  public void RemoveDocInAddPurchase() throws InterruptedException, IOException 
	  {
		  objwaitconditions.waitS(5000);
		  objAddPurchasePage.removeAddPurchaseFile(objAddPurchasePage.RemovePurchaseDoc);
		  objwaitconditions.waitS(5000);
		  objAddPurchasePage.checkUploadRemoving(objAddPurchasePage.AttachspacePurchaseDoc);
	  }
 
	  @Test (priority = 4,enabled = true, groups = {"Regression"}, description = "Adding Purchase without Supplier field")
	  public void addPurchaseWithoutSupplier() throws InterruptedException, IOException {
		  driver.navigate().refresh();
		  objAddPurchasePage.enterPurchaseData();
		  objwaitconditions.waitS(5000);
		  objAddPurchasePage.savePurchase();
		  objwaitconditions.waitS(5000);
		  objProductPage = new ProductPage(driver);
		  objProductPage.comparingErrorMsg(objAddPurchasePage.SupplierWarningMsg);  	  
	  }
	
	  @Test (priority = 5,enabled = true, groups = {"Regression"}, description = "Adding Purchase without Purchase Status")
	  public void addPurchaseWithoutStatus() throws InterruptedException, IOException {
		  driver.navigate().refresh();
		  objwaitconditions.waitS(5000);
		  objAddPurchasePage.addSupplier();
		  objAddPurchasePage.enterDataWithoutStatus();
		  objwaitconditions.waitS(5000);
		  objAddPurchasePage.savePurchase();
		  objwaitconditions.waitS(5000);
		  objProductPage = new ProductPage(driver);
		  objProductPage.comparingErrorMsg(objAddPurchasePage.StatusWarningMsg);  	  
	  }
  
  @Test (priority = 6,enabled = true, groups = {"Regression"}, description = "Adding Purchase without Business Location")
  public void addPurchaseWithoutLocation() throws InterruptedException, IOException {
//	  objAddPurchasePage.clearAllPurchasefields();
	  driver.navigate().refresh();
	  objwaitconditions.waitS(5000);
	  objAddPurchasePage.addSupplier();
	  objAddPurchasePage.enterDataWithoutLocation();
	  objwaitconditions.waitS(5000);
	  objAddPurchasePage.savePurchase();
	  objwaitconditions.waitS(5000);
	  objProductPage = new ProductPage(driver);
	  objProductPage.comparingErrorMsg(objAddPurchasePage.LocationWarningMsg);  	  
  }

  @Test (priority = 7,enabled = true, groups = {"Regression"}, description = "Adding Purchase without Produts.")
  public void addPurchaseWithoutProduct() throws InterruptedException, IOException {
	  driver.navigate().refresh();
	  objwaitconditions.waitS(5000);
	  objAddPurchasePage.addSupplier();
	  objAddPurchasePage.enterDataWithoutProduct();
	  objwaitconditions.waitS(5000);  	  
  }

  @Test (priority = 8,enabled = true, groups = {"Regression"}, description = "Adding new Purchase details.")
  public void addNewPurchase() throws InterruptedException, IOException 
  {
//	  driver.navigate().refresh();
	  objwaitconditions.waitS(5000);
	  objAddPurchasePage.addSupplier();
	  objAddPurchasePage.enterPurchaseData();
	  objwaitconditions.waitS(5000);
	  objAddPurchasePage.savePurchase();
	  objwaitconditions.waitS(5000);
	  objProductPage = new ProductPage(driver);
	  objProductPage.comparingInfoMsg(objProductPage.PurchaseSaveMsg, "Purchase");
  }
  
  @Test (priority = 9,enabled = true, groups = {"Regression"}, description = "Checking the Supplier field added correctly.")
  public void checkWithSupplierAdded() throws InterruptedException, IOException 
  {
	  objwaitconditions.waitS(5000);
	  objAddPurchasePage.checkAddedPurchasefields("ABC_Company", objAddPurchasePage.ChkAddedPurchseSupplier);
  } 
  
  @Test (priority = 10,enabled = true, groups = {"Regression"}, description = "Checking the Location field added correctly.")
  public void checkWithLocationAdded() throws InterruptedException, IOException 
  {
	  objwaitconditions.waitS(5000);
	  objAddPurchasePage.checkAddedPurchasefields("DemoLocation2433", objAddPurchasePage.ChkAddedPurchseLocation);
  } 
  
  @Test (priority = 11,enabled = true, groups = {"Regression"}, description = "Checking the Purchase Status field added correctly.")
  public void checkWithPurchaseStatusAdded() throws InterruptedException, IOException 
  {
	  objwaitconditions.waitS(5000);
	  objAddPurchasePage.checkAddedPurchasefields("Pending", objAddPurchasePage.ChkAddedPurchseStatus);
  }
  
  @Test (priority = 12,enabled = true, groups = {"Regression"}, description = "Checking the Grand Total field added correctly.")
  public void checkWithGrandTotalAdded() throws InterruptedException, IOException 
  {
	  objwaitconditions.waitS(5000);
	  objAddPurchasePage.checkAddedPurchasefields("1.500,00 €", objAddPurchasePage.ChkAddedPurchseGrandTotal);
  } 
 
  @BeforeTest (alwaysRun = true)
  @Parameters ({"Url","Browser"})
  public void beforeTest(String Url, String Browser) 
  {
	  objLaunch.launchingBrowser(Url, Browser);
//	  String url="https://qalegend.com/billing/public/login";
//	  objLaunch.launchingBrowser(url, "Chrome");
	  this.driver=objLaunch.driver;
	  objLogin=new LoginPage(driver);
	  objAddPurchasePage=new AddPurchasePage(driver);
	  objLogin.loginapp();
	  objAddPurchasePage.navToAddPurchase();
  }

  @AfterTest (alwaysRun = true)
  public void afterTest() {
	  objLaunch.closeBrowser();
  }

}

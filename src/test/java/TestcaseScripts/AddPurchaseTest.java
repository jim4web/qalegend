package TestcaseScripts;

import org.testng.annotations.Test;

import constants.Constants;
import pagelements.AddPurchasePage;
import pagelements.LoginPage;
import pagelements.ProductPage;
import utilities.BrowserLaunch;
import utilities.ExcelUtility;
import utilities.PageUtilities;
import utilities.ReadConfigProperties;
import utilities.Screenshot;
import utilities.WaitConditions;

import org.testng.annotations.BeforeTest;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.AfterTest;

public class AddPurchaseTest extends BrowserLaunch {
	WebDriver driver;
	LoginPage objLogin;
	Screenshot objScreenshot;
	ExcelUtility objExcelUtility;
	AddPurchasePage objAddPurchasePage;
	ProductPage objProductPage;
	WaitConditions objwaitconditions = new WaitConditions();
	ReadConfigProperties objReadConfigProperties = new ReadConfigProperties();
	PageUtilities objPageUtilities = new PageUtilities();
	Constants objConstants= new Constants();

	@Test(priority = 1, enabled = true, groups = {"Regression" }, description = "Adding new Supplier details in Add Purchase.")
	public void verifyAddSupplierDetails() throws InterruptedException, IOException {	
		this.driver = super.driver;
		objLogin = new LoginPage(driver);
		objAddPurchasePage = new AddPurchasePage(driver);
		objAddPurchasePage.navToAddPurchase();
		
		objPageUtilities.pageRefresh(driver);
		objAddPurchasePage.addSupplier();
		String actualMsg= objPageUtilities.getElementText(objAddPurchasePage.Supplier);
		String expectedMsg = "ABC_Company";
		Assert.assertEquals(actualMsg,expectedMsg, "Wrong name displayed");
	}

	@Test(priority = 2, enabled = true, groups = {"Regression" }, description = "Validate the File Uploading in Add Purchase.")
	public void verifyAttachDocInAddPurchase() throws InterruptedException, IOException {
		objAddPurchasePage.uploadAddPurchaseFile(objAddPurchasePage.BrowsePurchaseDoc,objConstants.uploadFilePath);
		String attach = objAddPurchasePage.AttachspacePurchaseDoc.getAccessibleName();
		Assert.assertFalse(attach.isEmpty(),"Document Not uploaded.");
	}

	@Test(priority = 3, enabled = true, groups = {"Regression" }, description = "Validate the Removal of Uploaded File in Add Purchase.")
	public void verifyRemoveDocInAddPurchase() throws InterruptedException, IOException {
		objAddPurchasePage.removeAddPurchaseFile(objAddPurchasePage.RemovePurchaseDoc);
		String attach = objAddPurchasePage.AttachspacePurchaseDoc.getAccessibleName();
		Assert.assertTrue(attach.isEmpty(),"Document Not uploaded.");
	}

	@Test(priority = 4, enabled = true, groups = {"Regression" }, description = "Adding Purchase without Supplier field")
	public void verifyAddPurchaseWithoutSupplier() throws InterruptedException, IOException {
		objPageUtilities.pageRefresh(driver);
		objAddPurchasePage.enterPurchaseData();
		objAddPurchasePage.savePurchase();
		objProductPage = new ProductPage(driver);
		Assert.assertTrue(objProductPage.comparingErrorMsg(objAddPurchasePage.SupplierWarningMsg), "Warning message comparison failed");
	}

	@Test(priority = 5, enabled = true, groups = {"Regression" }, description = "Adding Purchase without Purchase Status")
	public void verifyAddPurchaseWithoutStatus() throws InterruptedException, IOException {
		objPageUtilities.pageRefresh(driver);
		objAddPurchasePage.addSupplier();
		objAddPurchasePage.enterDataWithoutStatus();
		objAddPurchasePage.savePurchase();
		objProductPage = new ProductPage(driver);
		Assert.assertTrue(objProductPage.comparingErrorMsg(objAddPurchasePage.StatusWarningMsg), "Warning message comparison failed");
	}

	@Test(priority = 6, enabled = true, groups = {"Regression" }, description = "Adding Purchase without Business Location")
	public void verifyAddPurchaseWithoutLocation() throws InterruptedException, IOException {
		objPageUtilities.pageRefresh(driver);
		objAddPurchasePage.addSupplier();
		objAddPurchasePage.enterDataWithoutLocation();
		objAddPurchasePage.savePurchase();
		objProductPage = new ProductPage(driver);
		Assert.assertTrue(objProductPage.comparingErrorMsg(objAddPurchasePage.LocationWarningMsg), "Warning message comparison failed");
		
	}

	@Test(priority = 7, enabled = true, groups = {"Regression" }, description = "Adding Purchase without Produts.")
	public void verifyAddPurchaseWithoutProduct() throws InterruptedException, IOException {
		objPageUtilities.pageRefresh(driver);
		objAddPurchasePage.addSupplier();
		objAddPurchasePage.enterDataWithoutProduct();
		String actualMsg= objPageUtilities.getElementText(objAddPurchasePage.ProductCodeWarningMsg);
		String expectedMsg = "No Products added, add some products first";
		Assert.assertEquals(actualMsg,expectedMsg, "Warning message comparison failed");
	}

	@Test(priority = 8, enabled = true, groups = { "Regression" }, description = "Adding new Purchase details.")
	public void verifyAddNewPurchase() throws InterruptedException, IOException {
		objAddPurchasePage.addSupplier();
		objAddPurchasePage.enterPurchaseData();
		objAddPurchasePage.savePurchase();
		objProductPage = new ProductPage(driver);
		Assert.assertTrue(objProductPage.comparingInfoMsg(objProductPage.PurchaseSaveMsg, "Purchase"));
	}

	@Test(priority = 9, enabled = true, groups = {"Regression" }, description = "Checking the Supplier field added correctly.")
	public void verifyCheckWithSupplierAdded() throws InterruptedException, IOException {
		objwaitconditions.fluentWait(driver, objAddPurchasePage.ChkAddedPurchseSupplier);
			String listPurchaseByName = objPageUtilities.getElementText(objAddPurchasePage.ChkAddedPurchseSupplier);
			Assert.assertEquals(listPurchaseByName, "ABC_Company");
		}
	

	@Test(priority = 10, enabled = true, groups = {"Regression" }, description = "Checking the Location field added correctly.")
	public void verifyCheckWithLocationAdded() throws InterruptedException, IOException {
		objwaitconditions.fluentWait(driver, objAddPurchasePage.ChkAddedPurchseLocation);
		String listPurchaseByName = objPageUtilities.getElementText(objAddPurchasePage.ChkAddedPurchseLocation);
		Assert.assertEquals(listPurchaseByName, "DemoLocation2433");
	}

	@Test(priority = 11, enabled = true, groups = {"Regression" }, description = "Checking the Purchase Status field added correctly.")
	public void verifyCheckWithPurchaseStatusAdded() throws InterruptedException, IOException {
		objwaitconditions.fluentWait(driver, objAddPurchasePage.ChkAddedPurchseStatus);
		String listPurchaseByName = objPageUtilities.getElementText(objAddPurchasePage.ChkAddedPurchseStatus);
		Assert.assertEquals(listPurchaseByName, "Pending");
	}

	@Test(priority = 12, enabled = true, groups = {"Regression" }, description = "Checking the Grand Total field added correctly.")
	public void verifyCheckWithGrandTotalAdded() throws InterruptedException, IOException {
		objwaitconditions.fluentWait(driver, objAddPurchasePage.ChkAddedPurchseGrandTotal);
		String listPurchaseByName = objPageUtilities.getElementText(objAddPurchasePage.ChkAddedPurchseGrandTotal);
		Assert.assertEquals(listPurchaseByName, "1.500,00 €");
	}
	
	
	
	
	
	
	
	
	
	

//	@BeforeTest(alwaysRun = true)
////	@Parameters({ "Url", "Browser" })
////	public void beforeTest(String Url, String Browser) throws IOException
//  public void beforeTest()
//	{
//	  String url="https://qalegend.com/billing/public/login";
//	    launchingBrowser(url, "Chrome");
////		launchingBrowser(Url, Browser);
//		this.driver = super.driver;
//		objLogin = new LoginPage(driver);
//		objAddPurchasePage = new AddPurchasePage(driver);
//		objLogin.loginapp();
//		objAddPurchasePage.navToAddPurchase();
//	}
//
//	@AfterTest(alwaysRun = true)
//	public void afterTest() {
//		closeBrowser();
//	}

}

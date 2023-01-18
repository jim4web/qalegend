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
import utilities.ScrollinWindow;
import utilities.WaitConditions;

import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;

import java.io.IOException;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.testng.Assert;
import org.testng.annotations.AfterTest;

public class ProductsTest extends BrowserLaunch {
	WebDriver driver;
	LoginPage objLogin;
	ProductPage objProductPage;
	AddPurchasePage objAddPurchasePage;
	WaitConditions objwaitconditions = new WaitConditions();
	ReadConfigProperties objReadConfigProperties = new ReadConfigProperties();
	PageUtilities objPageUtilities = new PageUtilities();
	Screenshot objScreenshot = new Screenshot();
	Constants objConstants = new Constants();
	ScrollinWindow objScrollinWindow=new ScrollinWindow();

	@Test(priority = 1, enabled = true, groups = {
			"Regression" }, description = "Checking for the Display of Current Date in menubar")
	public void verifyCurrentDateDisplay() throws InterruptedException, IOException {
		
		this.driver = super.driver;
		objLogin = new LoginPage(driver);
		objProductPage = new ProductPage(driver);
		objAddPurchasePage = new AddPurchasePage(driver);
		objProductPage.navToAddProduct();
		
		String actualdate = objPageUtilities.getElementText(objProductPage.DateMenubar);
		String expecteddate = objScreenshot.datetoday();
		Assert.assertEquals(actualdate, expecteddate, "Failed date display");
	}

	@Test(priority = 2, enabled = true, groups = { "Regression" }, description = "Adding new Brand to the BrandList")
	public void verifyAddNewBrandToList() throws InterruptedException, IOException {
		objPageUtilities.pageRefresh(driver);
		objProductPage.addNewBrand();
		Assert.assertTrue(objProductPage.comparingInfoMsg(objProductPage.BrandSaveMsg, "Brand"),
				"Failed adding new brand");
	}

	@Test(priority = 3, enabled = true, groups = { "Regression" }, description = "Adding Products without Unit")
	public void verifyAddProductWithoutUnit() throws InterruptedException, IOException {
		objPageUtilities.pageRefresh(driver);
		objProductPage.enterDataWithoutUnit();
		objProductPage.saveProducts();
		Assert.assertTrue(objProductPage.comparingErrorMsg(objProductPage.UnitWarningMsg),
				"Warning message comparison failed");
	}

	@Test(priority = 4, enabled = true, groups = {
			"Regression" }, description = "Checking for the Manage Stock field can be disabled")
	public void verifyDisableManageStock() throws InterruptedException, IOException {
		objPageUtilities.pageRefresh(driver);
		objProductPage.SelectManageStock();
		Assert.assertFalse(objProductPage.ManageStock.isSelected(), "Manage Stock field is not disabled");
	}

	@Test(priority = 5, enabled = true, groups = {
			"Regression" }, description = "Checking for the Manage Stock field enable property")
	public void verifyEnableManageStock() throws InterruptedException, IOException {
		objProductPage.SelectManageStock();
		Assert.assertTrue(objProductPage.AlertQuantity.isDisplayed(),
				"Manage Stock field is not enabled and not added new field");
	}

	@Test(priority = 6, enabled = true, groups = { "Regression" }, description = "Adding new Unit to the UnitList")
	public void verifyAddNewUnitToList() throws InterruptedException, IOException {
		objPageUtilities.pageRefresh(driver);
		objProductPage.addNewUnit();
		Assert.assertTrue(objProductPage.comparingInfoMsg(objProductPage.UnitSaveMsg, "Unit"),
				"Failed adding new Unit");
	}

	@Test(priority = 7, enabled = true, groups = { "Regression" }, description = "Adding Products without Product Name")
	public void verifyAddProductWithoutProductName() throws InterruptedException, IOException {
		objPageUtilities.pageRefresh(driver);
		objProductPage.enterDataWithoutPNmae();
		objScrollinWindow.scrolldown(driver);
		objProductPage.saveProducts();
		Assert.assertTrue(objProductPage.comparingErrorMsg(objProductPage.WarningMsg),
				"Warning message comparison failed");
	}

	@Test(priority = 8, enabled = true, groups = {
			"Regression" }, description = "Adding Products without Alert Quantity")
	public void verifyAddProductWithoutAlertQty() throws InterruptedException, IOException {
		objPageUtilities.pageRefresh(driver);
		objProductPage.enterDataWithoutAlertQty();
		objProductPage.saveProducts();
		Assert.assertTrue(objProductPage.comparingErrorMsg(objProductPage.AlerQtyWarningMsg),
				"Warning message comparison failed");
	}

	@Test(priority = 9, enabled = true, groups = { "Regression" }, description = "Adding Products without Exc.Tax")
	public void verifyAddProductWithoutTax() throws InterruptedException, IOException {
		objPageUtilities.pageRefresh(driver);
		objProductPage.enterDataWithoutTax();
		objProductPage.saveProducts();
		Assert.assertTrue(objProductPage.comparingErrorMsg(objProductPage.TaxWarningMsg),
				"Warning message comparison failed");
	}

	@Test(priority = 10, enabled = true, groups = {
			"Regression" }, description = "Validate the Image Uploading in Add Products.")
	public void verifyAttachDocInAddPurchase() throws InterruptedException, IOException {
		objwaitconditions.waitS(3000);
		objAddPurchasePage.uploadAddPurchaseFile(objProductPage.ImageBrowse, objConstants.uploadImgPath);
		String attach = objProductPage.ImagetextArea.getAccessibleName();
		Assert.assertFalse(attach.isEmpty(), "Document Not uploaded.");
	}

	@Test(priority = 11, enabled = true, groups = {
			"Regression" }, description = "Validate the Removal of Uploaded Image in Add Products.")
	public void verifyRemoveDocInAddPurchase() throws InterruptedException, IOException {
		objwaitconditions.waitS(3000);// error:element click intercepted
		objAddPurchasePage.removeAddPurchaseFile(objProductPage.ImageRemove);
		objwaitconditions.waitS(3000);
		String attach = objProductPage.ImagetextArea.getAccessibleName();
		Assert.assertTrue(attach.isEmpty(), "Document Not uploaded.");
	}

	@Test(priority = 12, enabled = true, groups = { "Regression" }, description = "Adding new Product successfully")
	public void verifyAddProducts() throws InterruptedException, IOException {
		objPageUtilities.pageRefresh(driver);
		objProductPage.enterData();
		objProductPage.saveProducts();
		Assert.assertTrue(objProductPage.comparingInfoMsg(objProductPage.SaveMsg, "Product"),
				"Failed adding new Product");
	}

	@Test(priority = 13, enabled = true, groups = {
			"Regression" }, description = "Checking the Product Name added correctly.")
	public void verifyCheckWithProductName() throws InterruptedException, IOException {
		objProductPage.checkAddedfields(objProductPage.SearchListProductName1);
		String actualName = objPageUtilities.getElementText(objProductPage.SearchListProductName1);
		String expectedName = "Chocolate";
		Assert.assertEquals(actualName, expectedName, "Product name added wrongly");
	}

	@Test(priority = 14, enabled = true, groups = { "Regression" }, description = "Checking the Brand added correctly.")
	public void verifyCheckWithBrand() throws InterruptedException, IOException {
		objPageUtilities.pageRefresh(driver);
		objProductPage.checkAddedfields(objProductPage.SearchListBrand1);
		String actualName = objPageUtilities.getElementText(objProductPage.SearchListBrand1);
		String expectedName = "new";
		Assert.assertEquals(actualName, expectedName, "Brand added wrongly");
	}

	@Test(priority = 15, enabled = true, groups = {
			"Regression" }, description = "Checking the Category added correctly.")
	public void verifyCheckWithCategory() throws InterruptedException, IOException {
		objProductPage.checkAddedfields(objProductPage.SearchListCategory1);
		String actualName = objPageUtilities.getElementText(objProductPage.SearchListCategory1);
		String expectedName = "Groceries";
		Assert.assertEquals(actualName, expectedName, "Category added wrongly");
	}

	@Test(priority = 16, enabled = true, groups = {
			"Regression" }, description = "Checking the Product Type added correctly.")
	public void verifyCheckWithProductType() throws InterruptedException, IOException {
		objProductPage.checkAddedfields(objProductPage.SearchListProductType1);
		String actualName = objPageUtilities.getElementText(objProductPage.SearchListProductType1);
		String expectedName = "Single";
		Assert.assertEquals(actualName, expectedName, "Product Type added wrongly");
	}
//
//	@BeforeTest(alwaysRun = true)
////	@Parameters({ "Url", "Browser" })
////	public void beforeTest(String Url, String Browser) throws IOException
//	public void beforeTest() {
//		launchingBrowser(objReadConfigProperties.getUrl(), objReadConfigProperties.getBrowser());
////		launchingBrowser(Url, Browser);
//		this.driver = super.driver;
//		objLogin = new LoginPage(driver);
//		objProductPage = new ProductPage(driver);
//		objAddPurchasePage = new AddPurchasePage(driver);
//		objLogin.loginapp();
//		objProductPage.navToAddProduct();
//	}
//
//	@AfterTest(alwaysRun = true)
//	public void afterTest() {
//		closeBrowser();
//	}

}

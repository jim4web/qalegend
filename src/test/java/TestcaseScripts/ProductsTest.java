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
import org.testng.annotations.DataProvider;
import org.testng.annotations.Parameters;

import java.io.IOException;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.testng.annotations.AfterTest;

public class ProductsTest {
	WebDriver driver;
	LoginPage objLogin;
	Screenshot objScreenshot;
	ExcelUtility objExcelUtility;
	ProductPage objProductPage;
	AddPurchasePage objAddPurchasePage;
	BrowserLaunch objLaunch=new BrowserLaunch();
	WaitConditions objwaitconditions=new WaitConditions();
	ReadConfigProperties objReadConfigProperties= new ReadConfigProperties();
  
  @Test (priority = 1,enabled = true, groups = {"Regression"}, description = "Checking for the Display of Current Date in menubar")
	  public void CurrentDateDisplay() throws InterruptedException, IOException {
		  objProductPage.ChkDisplayDate();
	  }
	
  @Test (priority = 2,enabled = true, groups = {"Regression"}, description = "Adding new Brand to the BrandList")
  public void addNewBrandToList() throws InterruptedException, IOException {
	  driver.navigate().refresh();
	  objwaitconditions.waitS(5000);
	  objProductPage.addNewBrand();
	  objProductPage.comparingInfoMsg(objProductPage.BrandSaveMsg, "Brand");
  }

  @Test (priority = 3,enabled = true, groups = {"Regression"}, description = "Adding Products without Unit")
  public void addProductWithoutUnit() throws InterruptedException, IOException {
	  driver.navigate().refresh();
	  objProductPage.enterDataWithoutUnit();
	  objProductPage.saveProducts();
	  objwaitconditions.waitS(5000);
	  objProductPage.comparingErrorMsg(objProductPage.UnitWarningMsg);    
  }
  
  @Test (priority = 4,enabled = true, groups = {"Regression"}, description = "Checking for the Manage Stock field can be disabled")
  public void DisableManageStock() throws InterruptedException, IOException {
	  driver.navigate().refresh();
	  objwaitconditions.waitS(5000);
	  objProductPage.SelectManageStock();
	  objProductPage.ChkManageStockDisabled();
  }

  @Test (priority = 5,enabled = true, groups = {"Regression"}, description = "Checking for the Manage Stock field enable property")
  public void EnableManageStock() throws InterruptedException, IOException {
	  objwaitconditions.waitS(5000);
	  objProductPage.SelectManageStock();
	  objProductPage.ChkAddedNewOption();
	  objwaitconditions.waitS(5000);
  }

  @Test (priority = 6,enabled = true, groups = {"Regression"}, description = "Adding new Unit to the UnitList")
  public void addNewUnitToList() throws InterruptedException, IOException {
	  driver.navigate().refresh();
	  objwaitconditions.waitS(5000);
	  objProductPage.addNewUnit();
	  objProductPage.comparingInfoMsg(objProductPage.UnitSaveMsg, "Unit");
  }

  @Test (priority = 7,enabled = true, groups = {"Regression"}, description = "Adding Products without Product Name")
  public void addProductWithoutProductName() throws InterruptedException, IOException {
	  driver.navigate().refresh();
//	  objProductPage.clearAllfields();
	  objProductPage.enterDataWithoutPNmae();
	  objProductPage.saveProducts();
	  objwaitconditions.waitS(5000);
	  objProductPage.comparingErrorMsg(objProductPage.WarningMsg);    
  }

  @Test (priority = 8,enabled = true, groups = {"Regression"}, description = "Adding Products without Alert Quantity")
  public void addProductWithoutAlertQty() throws InterruptedException, IOException {
	  driver.navigate().refresh();
//	  objProductPage.clearAllfields();
	  objProductPage.enterDataWithoutAlertQty();
	  objProductPage.saveProducts();
	  objwaitconditions.waitS(5000);
	  objProductPage.comparingErrorMsg(objProductPage.AlerQtyWarningMsg);    
  }
  
  @Test (priority = 9,enabled = true, groups = {"Regression"}, description = "Adding Products without Exc.Tax")
  public void addProductWithoutTax() throws InterruptedException, IOException {
	  driver.navigate().refresh();
//	  objProductPage.clearAllfields();
	  objProductPage.enterDataWithoutTax();
	  objProductPage.saveProducts();
	  objwaitconditions.waitS(5000);
	  objProductPage.comparingErrorMsg(objProductPage.TaxWarningMsg);    
  }
  
  @Test (priority = 10,enabled = true, groups = {"Regression"}, description = "Validate the Image Uploading in Add Products.")
  public void attachDocInAddPurchase() throws InterruptedException, IOException 
  {
	  objwaitconditions.waitS(5000);
	  objAddPurchasePage.uploadAddPurchaseFile(objProductPage.ImageBrowse, "C:\\Users\\RS\\Pictures\\Museum_of_the_future,_Dubai.jpeg");
	  objwaitconditions.waitS(5000);
	  objAddPurchasePage.checkUploading(objProductPage.ImagetextArea);
  }
  
  @Test (priority = 11,enabled = true, groups = {"Regression"}, description = "Validate the Removal of Uploaded Image in Add Products.")
  public void RemoveDocInAddPurchase() throws InterruptedException, IOException 
  {
	  objwaitconditions.waitS(5000);
	  objAddPurchasePage.removeAddPurchaseFile(objProductPage.ImageRemove);
	  objwaitconditions.waitS(5000);
	  objAddPurchasePage.checkUploadRemoving(objProductPage.ImagetextArea);
  }
  
  @Test (priority = 12,enabled = true, groups = {"Regression"}, description = "Adding new Product successfully")
  public void addProducts() throws InterruptedException, IOException 
  {
	  driver.navigate().refresh();
	  objwaitconditions.waitS(5000);
	  objProductPage.enterData();
	  objwaitconditions.waitS(5000);
	  objProductPage.saveProducts();
	  objProductPage.comparingInfoMsg(objProductPage.SaveMsg, "Product");
  }
  
  @Test (priority = 13,enabled = true, groups = {"Regression"}, description = "Checking the Product Name added correctly.")
  public void checkWithProductName() throws InterruptedException, IOException 
  {
	  objwaitconditions.waitS(5000);
	  objProductPage.checkAddedfields("Chocolate",objProductPage.SearchListProductName1);
  } 
  
  @Test (priority = 14,enabled = true, groups = {"Regression"}, description = "Checking the Brand added correctly.")
  public void checkWithBrand() throws InterruptedException, IOException 
  {
	  objwaitconditions.waitS(5000);
	  objProductPage.checkAddedfields("new",objProductPage.SearchListBrand1);
  } 
 
  @Test (priority = 15,enabled = true, groups = {"Regression"}, description = "Checking the Category added correctly.")
  public void checkWithCategory() throws InterruptedException, IOException 
  {
	  objwaitconditions.waitS(7000);
	  objProductPage.checkAddedfields("Groceries",objProductPage.SearchListCategory1);
  } 
  
  @Test (priority = 16,enabled = true, groups = {"Regression"}, description = "Checking the Product Type added correctly.")
  public void checkWithProductType() throws InterruptedException, IOException 
  {
	  objwaitconditions.waitS(5000);
	  objProductPage.checkAddedfields("Single",objProductPage.SearchListProductType1);
  } 

  
    
  @BeforeTest (alwaysRun = true)
  @Parameters ({"Url","Browser"})
   public void beforeTest(String Url, String Browser) 
	  {
		  objLaunch.launchingBrowser(Url, Browser);
//	  objLaunch.launchingBrowser(objReadConfigProperties.getUrl(), objReadConfigProperties.getBrowser());
	  this.driver=objLaunch.driver;
	  objLogin=new LoginPage(driver);
	  objProductPage=new ProductPage(driver);
	  objAddPurchasePage = new AddPurchasePage(driver);
	  objLogin.loginapp();
	  objProductPage.navToAddProduct();
  }

  @AfterTest (alwaysRun = true)
  public void afterTest() 
  {
	  objLaunch.closeBrowser();
  }

}

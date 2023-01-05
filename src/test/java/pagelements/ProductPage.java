package pagelements;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.openqa.selenium.Alert;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;

import utilities.BrowserLaunch;
import utilities.ReadConfigProperties;
import utilities.Screenshot;
import utilities.WaitConditions;

public class ProductPage {
	public WebDriver driver;
	BrowserLaunch objLaunch=new BrowserLaunch();
	WaitConditions objwaitconditions=new WaitConditions();
	Screenshot objScreenshot= new Screenshot();
	ReadConfigProperties objReadConfigProperties= new ReadConfigProperties();
	
	public ProductPage(WebDriver driver) {
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(xpath="/html/body/div[2]/header/nav/div/div/strong")
	public WebElement DateMenubar;
	
	@FindBy(xpath="//span[text()='Products']")
	public WebElement Products;
	
	@FindBy(css="li#tour_step5>ul>li:nth-of-type(2)>a")
	public WebElement AddProduct;
		
	@FindBy(id="name")
	public WebElement ProductName;
	
	@FindBy(xpath="//*[@id=\"select2-brand_id-container\"]")
	public WebElement Brand;
	@FindBy(xpath="//*[@id=\"product_add_form\"]/div[1]/div/div/div[2]/div/div/span[2]/button/i")
	public WebElement AddBrand;	
	@FindBy(xpath="(//input[@name='name'])[2]")
	public WebElement BrandName;	
	@FindBy(xpath="//*[@id=\"quick_add_brand_form\"]/div[3]/button[1]")
	public WebElement BrandSave;
	
	@FindBy(xpath="//span[@class='select2-selection__rendered']")
	public WebElement RightNow;
	
	@FindBy(xpath="//*[@id=\"select2-unit_id-container\"]")
	public WebElement Unit;
	@FindBy(xpath="//*[@id=\"product_add_form\"]/div[1]/div/div/div[3]/div/div/span[2]/button/i")
	public WebElement AddUnit;
	@FindBy(id="actual_name")
	public WebElement UnitActualName;
	@FindBy(id="short_name")
	public WebElement UnitShortName;
	@FindBy(id="allow_decimal")
	public WebElement UnitAllowDecimal;
	@FindBy(xpath="//*[@id=\"quick_add_unit_form\"]/div[3]/button[1]")
	public WebElement UnitSave;
	
	
	@FindBy(xpath="(//span[@class='select2-selection__arrow'])[3]")
	public WebElement Category;
	
	@FindBy(id="select2-barcode_type-container")
	public WebElement BarcodeType;
	
	@FindBy(xpath="//*[@id=\"product_add_form\"]/div[1]/div/div/div[10]/div/label/div/ins")
	public WebElement ManageStock;
	@FindBy(id="alert_quantity")
	public WebElement AlertQuantity;

	@FindBy(id="select2-type-container")
	public WebElement ProductType;
	
	@FindBy(id="select2-tax_type-container")
	public WebElement SellingPriceTaxType;
	
	@FindBy(id="sku")
	public WebElement Sku;
	
	@FindBy(id="single_dpp")
	public WebElement ExcTax;
	
	@FindBy(xpath="//*[@id=\"upload_image\"]")
	public WebElement ImageBrowse;
	@FindBy(xpath="//*[@id=\"product_add_form\"]/div[1]/div/div/div[14]/div/div/div[3]/div[2]/button[1]/span")
	public WebElement ImageRemove;
	@FindBy(xpath="//*[@id=\"product_add_form\"]/div[1]/div/div/div[14]/div/div/div[3]/div[1]/div")
	public WebElement ImagetextArea;	

	@FindBy(css="form#product_add_form>div:nth-of-type(4)>div>div>div>button:nth-of-type(4)")
	public WebElement Save;
	@FindBy(xpath="//*[@id=\"toast-container\"]/div/div")
	public WebElement SaveMsg;
	@FindBy(xpath="//*[@id=\"toast-container\"]/div/div")
	public WebElement UnitSaveMsg;
	@FindBy(xpath="//*[@id=\"toast-container\"]/div")
	public WebElement BrandSaveMsg;
	@FindBy(xpath="//*[@id=\"toast-container\"]/div")
	public WebElement PurchaseSaveMsg;
	
	
	
	@FindBy(xpath=("//*[@id=\"name-error\"]"))
	public WebElement WarningMsg;
	@FindBy(xpath=("//*[@id=\"alert_quantity-error\"]"))
	public WebElement AlerQtyWarningMsg;
	@FindBy(xpath=("//*[@id=\"unit_id-error\"]"))
	public WebElement UnitWarningMsg;
	@FindBy(xpath=("//*[@id=\"single_dpp-error\"]"))
	public WebElement TaxWarningMsg;
	
	@FindBy(xpath=("//*[@id=\"product_table_filter\"]/label/input"))
	public WebElement SearchListProducts;
	@FindBy(xpath=("//td[@class='sorting_1']"))
	public WebElement SearchListProductName1;
	@FindBy(xpath=("//*[@id=\"product_table\"]/tbody/tr[1]/td[9]"))
	public WebElement SearchListBrand1;
	@FindBy(xpath=("//*[@id=\"product_table\"]/tbody/tr[1]/td[7]"))
	public WebElement SearchListCategory1;
	@FindBy(xpath=("//*[@id=\"product_table\"]/tbody/tr[1]/td[6]"))
	public WebElement SearchListProductType1;

	
	public void navToAddProduct() {
		objwaitconditions.waitForElementTobeVisible(driver, Products, 8);
		Products.click();
		objwaitconditions.waitForElementTobeVisible(driver, AddProduct, 6);
		AddProduct.click();		
	}
	
	public void brandSelection() {
		Actions objAction=new Actions(driver);
		objAction.moveToElement(Brand).click().keyDown(Keys.ARROW_DOWN).keyDown(Keys.ARROW_DOWN).keyDown(Keys.ARROW_DOWN).keyDown(Keys.ARROW_DOWN).keyDown(Keys.ENTER).build().perform();
	}
	public void unitSelection() {
		Actions objAction=new Actions(driver);
		objAction.moveToElement(Unit).click().keyDown(Keys.ARROW_DOWN).keyDown(Keys.ARROW_DOWN).keyDown(Keys.ENTER).build().perform();
	}
	public void categorySelection() {
		Actions objAction=new Actions(driver);
		objAction.moveToElement(Category).click().keyDown(Keys.ARROW_DOWN).keyDown(Keys.ENTER).build().perform();
	}
	public void enterData() throws InterruptedException, IOException {
		objwaitconditions.waitForElementTobeVisible(driver, ProductName, 6);
		ProductName.sendKeys(objReadConfigProperties.getProductName());
		objwaitconditions.waitS(3000);
		brandSelection();
		unitSelection();
		categorySelection();
		objwaitconditions.waitS(3000);
		AlertQuantity.sendKeys(objReadConfigProperties.getAlertQty());	
		ExcTax.sendKeys(objReadConfigProperties.getExcTax());
	}
		
	public void enterDataWithoutPNmae() throws InterruptedException {
		objwaitconditions.waitForElementTobeVisible(driver, ProductName, 6);
		brandSelection();
		unitSelection();
		categorySelection();
		objwaitconditions.waitS(3000);
		AlertQuantity.sendKeys(objReadConfigProperties.getAlertQty());	
		ExcTax.sendKeys(objReadConfigProperties.getExcTax());
	}
	
	public void enterDataWithoutAlertQty() throws InterruptedException {
		objwaitconditions.waitForElementTobeVisible(driver, ProductName, 6);
		ProductName.sendKeys(objReadConfigProperties.getProductName());
		brandSelection();
		unitSelection();
		categorySelection();
		objwaitconditions.waitS(3000);	
		ExcTax.sendKeys(objReadConfigProperties.getExcTax());
	}
	
	public void enterDataWithoutUnit() throws InterruptedException {
		objwaitconditions.waitForElementTobeVisible(driver, ProductName, 6);
		ProductName.sendKeys(objReadConfigProperties.getProductName());
		brandSelection();
		categorySelection();
		objwaitconditions.waitS(3000);
		AlertQuantity.sendKeys(objReadConfigProperties.getAlertQty());	
		ExcTax.sendKeys(objReadConfigProperties.getExcTax());
	}
	
	public void enterDataWithoutTax() throws InterruptedException {
		objwaitconditions.waitForElementTobeVisible(driver, ProductName, 6);
		ProductName.sendKeys(objReadConfigProperties.getProductName());
		brandSelection();
		unitSelection();
		categorySelection();
		objwaitconditions.waitS(3000);
		AlertQuantity.sendKeys(objReadConfigProperties.getAlertQty());			
	}
	
	public void saveProducts() throws IOException {
		Save.click();
	}
	
	public void addNewUnit() throws IOException, InterruptedException {
		objwaitconditions.waitForElementTobeVisible(driver, ProductName, 6);
		AddUnit.click();
		UnitActualName.sendKeys("Dozen");
		UnitShortName.sendKeys("12");
		Select objSelect=new Select(UnitAllowDecimal);
		objSelect.selectByIndex(1);
		UnitSave.click();
		objwaitconditions.waitS(3000);
	}
	
	public void addNewBrand() throws IOException, InterruptedException {
		objwaitconditions.waitForElementTobeVisible(driver, ProductName, 6);
		AddBrand.click();
		objwaitconditions.waitForElementTobeVisible(driver, BrandName, 10);
		BrandName.sendKeys("Bosch");
		BrandSave.click();
		objwaitconditions.waitS(3000);
	}
	
	public void comparingInfoMsg(WebElement WarningMsg, String name) throws IOException {
		objwaitconditions.waitForElementTobeVisible(driver, SaveMsg, 20);
//		objScreenshot= new Screenshot();
//		objScreenshot.screenshots(driver);
		  String displaymsgFromAppli=WarningMsg.getText();
			if(displaymsgFromAppli.contains(name+" added successfully")) {		
				Assert.assertTrue(true);
			}else {
				Assert.assertTrue(false, "Warning message comparison failed");	
			}
	  }
		
	
	public void comparingErrorMsg(WebElement WarningMsg) {
		  
		  objwaitconditions.waitForElementTobeVisible(driver, WarningMsg, 20);
		  String displaymsgFromAppli=WarningMsg.getText();
			if(displaymsgFromAppli.contains("This field is required.")) {		
				Assert.assertTrue(true);
			}else {
				Assert.assertTrue(false, "Warning message comparison failed");	
			}
	  }
	
	public void checkAddedfields(String text,WebElement addedelement) throws InterruptedException {
		objwaitconditions.waitForElementTobeVisible(driver, SearchListProducts, 8);
		SearchListProducts.sendKeys(objReadConfigProperties.getProductName());
		SearchListProducts.click();
		objwaitconditions.waitForElementTobeVisible(driver, addedelement, 25);
		objwaitconditions.waitS(9000);
		String listProductByName=addedelement.getText();
		Assert.assertEquals(listProductByName, text);					
	}
	
	public void ChkDisplayDate() {
    	String todaysdate = objScreenshot.datetoday();
		String displaydate=DateMenubar.getText();
		Assert.assertTrue(displaydate.equals(todaysdate), "Failed date display");
	}
	
	public void SelectManageStock() throws InterruptedException {
		objwaitconditions.waitS(5000);
		ManageStock.click();
	}
	
	public void ChkManageStockDisabled() {
		if(ManageStock.isSelected()) {		
			Assert.assertTrue(false, "Failed");
		}else {
			Assert.assertTrue(true);	
		}
	}
	
	public void ChkAddedNewOption() {
		if(AlertQuantity.isDisplayed()) {		
			Assert.assertTrue(true);
		}else {
			Assert.assertTrue(false, "Failed");	
		}
	}
	
	public void clearAllfields() {
		ProductName.clear();	
		AlertQuantity.clear();
		ExcTax.clear();	
		objwaitconditions.waitS(3000);
	}
}

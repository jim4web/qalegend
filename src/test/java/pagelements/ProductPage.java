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
import utilities.PageUtilities;
import utilities.RandomDataUtility;
import utilities.ReadConfigProperties;
import utilities.Screenshot;
import utilities.WaitConditions;

public class ProductPage extends BrowserLaunch {
	public WebDriver driver;
	PageUtilities objPageUtilities = new PageUtilities();
	WaitConditions objwaitconditions = new WaitConditions();
	Screenshot objScreenshot = new Screenshot();
	ReadConfigProperties objReadConfigProperties = new ReadConfigProperties();
	RandomDataUtility objRandomDataUtility = new RandomDataUtility();

	public ProductPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(xpath = "//span[text()='Products']")
	private WebElement Products;
	@FindBy(css = "li#tour_step5>ul>li:nth-of-type(2)>a")
	private WebElement AddProduct;
	@FindBy(id = "name")
	private WebElement ProductName;
	// Brand
	@FindBy(xpath = "//*[@id=\"select2-brand_id-container\"]")
	private WebElement Brand;
	@FindBy(xpath = "//button[@title='Add brand']//i")
	private WebElement AddBrand;
	@FindBy(xpath = "(//input[@name='name'])[2]")
	private WebElement BrandName;
	@FindBy(xpath = "//*[@id=\"quick_add_brand_form\"]/div[3]/button[1]")
	private WebElement BrandSave;

	@FindBy(xpath = "//span[@class='select2-selection__rendered']")
	private WebElement RightNow;
	// unit
	@FindBy(xpath = "//*[@id=\"select2-unit_id-container\"]")
	private WebElement Unit;
	@FindBy(xpath = "//*[@id=\"product_add_form\"]/div[1]/div/div/div[3]/div/div/span[2]/button/i")
	private WebElement AddUnit;
	@FindBy(id = "actual_name")
	private WebElement UnitActualName;
	@FindBy(id = "short_name")
	private WebElement UnitShortName;
	@FindBy(id = "allow_decimal")
	private WebElement UnitAllowDecimal;
	@FindBy(xpath = "//*[@id=\"quick_add_unit_form\"]/div[3]/button[1]")
	private WebElement UnitSave;

	@FindBy(xpath = "(//span[@class='select2-selection__arrow'])[3]")
	private WebElement Category;
	@FindBy(id = "select2-barcode_type-container")
	private WebElement BarcodeType;
	@FindBy(id = "select2-type-container")
	private WebElement ProductType;
	@FindBy(id = "select2-tax_type-container")
	private WebElement SellingPriceTaxType;
	@FindBy(id = "sku")
	private WebElement Sku;
	@FindBy(id = "single_dpp")
	private WebElement ExcTax;
	@FindBy(xpath = "//button[@type='submit' and @class='btn btn-primary submit_product_form']")
	private WebElement Save;
	@FindBy(xpath = ("//*[@id=\"product_table_filter\"]/label/input"))
	private WebElement SearchListProducts;

	@FindBy(xpath = "//div[@class='m-8 pull-left mt-15 hidden-xs']//child::strong")
	public WebElement DateMenubar;
	@FindBy(xpath = "//input[@id='enable_stock']//following::ins")
	public WebElement ManageStock;
	@FindBy(id = "alert_quantity")
	public WebElement AlertQuantity;
	// Product image
	@FindBy(xpath = "//*[@id=\"upload_image\"]")
	public WebElement ImageBrowse;
	@FindBy(xpath = "//div[@class='input-group-btn']//descendant::button[1]")
	public WebElement ImageRemove;
	@FindBy(xpath = "//div[@class='input-group file-caption-main']//child::div")
	public WebElement ImagetextArea;

	@FindBy(xpath = "//*[@id=\"toast-container\"]/div/div")
	public WebElement SaveMsg;
	@FindBy(xpath = "//*[@id=\"toast-container\"]/div/div")
	public WebElement UnitSaveMsg;
	@FindBy(xpath = "//*[@id=\"toast-container\"]/div")
	public WebElement BrandSaveMsg;
	@FindBy(xpath = "//*[@id=\"toast-container\"]/div")
	public WebElement PurchaseSaveMsg;

	@FindBy(xpath = ("//*[@id=\"name-error\"]"))
	public WebElement WarningMsg;
	@FindBy(xpath = ("//*[@id=\"alert_quantity-error\"]"))
	public WebElement AlerQtyWarningMsg;
	@FindBy(xpath = ("//*[@id=\"unit_id-error\"]"))
	public WebElement UnitWarningMsg;
	@FindBy(xpath = ("//*[@id=\"single_dpp-error\"]"))
	public WebElement TaxWarningMsg;

	@FindBy(xpath = ("//td[@class='sorting_1']"))
	public WebElement SearchListProductName1;
	@FindBy(xpath = ("//*[@id=\"product_table\"]/tbody/tr[1]/td[9]"))
	public WebElement SearchListBrand1;
	@FindBy(xpath = ("//*[@id=\"product_table\"]/tbody/tr[1]/td[7]"))
	public WebElement SearchListCategory1;
	@FindBy(xpath = ("//*[@id=\"product_table\"]/tbody/tr[1]/td[6]"))
	public WebElement SearchListProductType1;

	public void enterSKU() {
		objPageUtilities.sendText(Sku, "346" + objRandomDataUtility.randomNumber());
	}

	public void navToAddProduct() {
		objwaitconditions.waitForElementTobeVisible(driver, Products, 8);
		objPageUtilities.clickOnElement(Products);
		objwaitconditions.waitForElementTobeVisible(driver, AddProduct, 6);
		objPageUtilities.clickOnElement(AddProduct);
	}

	public void brandSelection() {
		Actions objAction = new Actions(driver);
		objAction.moveToElement(Brand).click().keyDown(Keys.ARROW_DOWN).keyDown(Keys.ARROW_DOWN)
				.keyDown(Keys.ARROW_DOWN).keyDown(Keys.ARROW_DOWN).keyDown(Keys.ENTER).build().perform();
	}

	public void unitSelection() {
		Actions objAction = new Actions(driver);
		objAction.moveToElement(Unit).click().keyDown(Keys.ARROW_DOWN).keyDown(Keys.ARROW_DOWN).keyDown(Keys.ENTER)
				.build().perform();
	}

	public void categorySelection() {
		Actions objAction = new Actions(driver);
		objAction.moveToElement(Category).click().keyDown(Keys.ARROW_DOWN).keyDown(Keys.ENTER).build().perform();
	}

	public void enterData() throws InterruptedException, IOException {
		objwaitconditions.waitForElementTobeVisible(driver, ProductName, 20);
		objPageUtilities.sendText(ProductName, objReadConfigProperties.getProductName());
		brandSelection();
		unitSelection();
		categorySelection();
		enterSKU();
		objwaitconditions.waitS(3000);
		objPageUtilities.sendText(AlertQuantity, objReadConfigProperties.getAlertQty());
		objPageUtilities.sendText(ExcTax, objReadConfigProperties.getExcTax());
	}

	public void enterDataWithoutPNmae() throws InterruptedException {
		objwaitconditions.waitForElementTobeVisible(driver, ProductName, 20);
		brandSelection();
		unitSelection();
		categorySelection();
		enterSKU();
		objwaitconditions.waitS(3000);
		objPageUtilities.sendText(AlertQuantity, objReadConfigProperties.getAlertQty());
		objPageUtilities.sendText(ExcTax, objReadConfigProperties.getExcTax());
	}

	public void enterDataWithoutAlertQty() throws InterruptedException {
		objwaitconditions.waitForElementTobeVisible(driver, ProductName, 20);
		objPageUtilities.sendText(ProductName, objReadConfigProperties.getProductName());
		brandSelection();
		unitSelection();
		categorySelection();
		enterSKU();
		objwaitconditions.waitS(3000);
		objPageUtilities.sendText(ExcTax, objReadConfigProperties.getExcTax());
	}

	public void enterDataWithoutUnit() throws InterruptedException {
		objwaitconditions.waitForElementTobeVisible(driver, ProductName, 6);
		objPageUtilities.sendText(ProductName, objReadConfigProperties.getProductName());
		brandSelection();
		categorySelection();
		enterSKU();
		objwaitconditions.waitS(3000);
		objPageUtilities.sendText(AlertQuantity, objReadConfigProperties.getAlertQty());
		objPageUtilities.sendText(ExcTax, objReadConfigProperties.getExcTax());
	}

	public void enterDataWithoutTax() throws InterruptedException {
		objwaitconditions.waitForElementTobeVisible(driver, ProductName, 6);
		objPageUtilities.sendText(ProductName, objReadConfigProperties.getProductName());
		brandSelection();
		unitSelection();
		categorySelection();
		enterSKU();
		objwaitconditions.waitS(3000);
		objPageUtilities.sendText(AlertQuantity, objReadConfigProperties.getAlertQty());
	}

	public void saveProducts() throws IOException {
		objPageUtilities.clickOnElement(Save);
	}

	public void addNewUnit() throws IOException, InterruptedException {
		objwaitconditions.waitForElementTobeVisible(driver, ProductName, 20);
		objPageUtilities.clickOnElement(AddUnit);
		objPageUtilities.sendText(UnitActualName, "Dozen");
		objPageUtilities.sendText(UnitShortName, "12");
		objPageUtilities.selectFuncbyindex(UnitAllowDecimal, 1);
		objPageUtilities.clickOnElement(UnitSave);
		objwaitconditions.waitS(3000);
	}

	public void addNewBrand() throws IOException, InterruptedException {
		objwaitconditions.waitForElementTobeVisible(driver, ProductName, 15);
		objPageUtilities.clickOnElement(AddBrand);
		objwaitconditions.waitForElementTobeVisible(driver, BrandName, 10);
		objPageUtilities.sendText(BrandName, "Bosch");
		objPageUtilities.clickOnElement(BrandSave);
		BrandSave.click();
		objwaitconditions.waitS(3000);
	}

	public boolean comparingInfoMsg(WebElement WarningMsg, String name) throws IOException {
		objwaitconditions.waitForElementTobeVisible(driver, WarningMsg, 20);
		String actualMsg = WarningMsg.getText();
		String expectedMsg = name + " added successfully";
		if (actualMsg.equals(expectedMsg)) {
			return true;
		} else {
			return false;
		}
	}

	public boolean comparingErrorMsg(WebElement WarningMsg) {
		objwaitconditions.waitForElementTobeVisible(driver, WarningMsg, 25);
		String actualMsg = WarningMsg.getText();
		String expectedMsg = "This field is required.";
		if (actualMsg.equals(expectedMsg)) {
			return true;
		} else {
			return false;
		}
	}

	public void checkAddedfields(WebElement addedelement) throws InterruptedException {
		objwaitconditions.waitS(6000);
		objwaitconditions.waitForElementTobeVisible(driver, SearchListProducts, 40);
		objPageUtilities.sendText(SearchListProducts, objReadConfigProperties.getProductName());
		objPageUtilities.clickOnElement(SearchListProducts);
		objwaitconditions.waitForElementTobeVisible(driver, addedelement, 35);
		objwaitconditions.waitS(9000);
	}

	public void SelectManageStock() throws InterruptedException {
		objwaitconditions.waitForElementTobeVisible(driver, ProductName, 30);
		objPageUtilities.clickOnElement(ManageStock);
		objwaitconditions.waitS(5000);
	}

	public void clearAllfields() {
		objPageUtilities.clearText(ProductName);
		objPageUtilities.clearText(AlertQuantity);
		objPageUtilities.clearText(ExcTax);
		objwaitconditions.waitS(3000);
	}
}

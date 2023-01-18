package pagelements;

import java.io.IOException;

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
import utilities.Screenshot;
import utilities.WaitConditions;

public class AddPurchasePage {
	public WebDriver driver;
	Screenshot objScreenshot;
	WaitConditions objwaitconditions = new WaitConditions();
	PageUtilities objPageUtilities = new PageUtilities();

	public AddPurchasePage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(id = "tour_step6_menu")
	private WebElement Purchase;

	@FindBy(xpath = "//*[@id=\"tour_step6\"]/ul/li[2]/a")
	private WebElement AddPurchase;

	@FindBy(xpath = "//span[@class='input-group-btn']//button//i")
	private WebElement AddSupplier;

	// Supplier field
	@FindBy(xpath = "//form/div[1]//descendant::div[4]//label")
	private WebElement SupplierLabel;

	@FindBy(id = "name")
	private WebElement Supplier_Name;
	@FindBy(id = "supplier_business_name")
	private WebElement BusinessName;
	@FindBy(id = "mobile")
	private WebElement Mobile;
	@FindBy(xpath = "//button[@class='btn btn-primary']")
	private WebElement SaveSupplier;
	@FindBy(xpath = "//*[@id=\"toast-container\"]/div")
	private WebElement SaveSupplierInfoMsg;

	@FindBy(xpath = "//*[@id=\"select2-status-container\"]")
	private WebElement PurchaseStatus;
	@FindBy(xpath = "//*[@id=\"select2-location_id-container\"]")
	private WebElement BusinessLocation;
	@FindBy(xpath = "//*[@id=\"amount_0\"]")
	private WebElement PaymentAmount;
	@FindBy(xpath = "//*[@id=\"method_0\"]")
	private WebElement PaymentMethod;
	@FindBy(xpath = "//*[@id=\"search_product\"]")
	private WebElement SearchProductFromPurchase;
	@FindBy(xpath = "//*[@id=\"ui-id-1\"]/li[3]")
	private WebElement ChocolateSelection;
	@FindBy(xpath = "//*[@id=\"purchase_entry_table\"]/tbody/tr/td[14]/i")
	private WebElement SearchProductDeleteOption;

	@FindBy(id = "submit_purchase_form")
	private WebElement SubmitPurchase;

	@FindBy(xpath = "/html/body/div[2]/div[1]/section[2]/div[2]/div[1]/h3")
	private WebElement AllPurchasesLabel;

	@FindBy(xpath = "//span[@id='select2-supplier_id-container']")
	public WebElement Supplier;
	// Upload Document
	@FindBy(id = "upload_document")
	public WebElement BrowsePurchaseDoc;
	@FindBy(xpath = "//div[@class='file-caption-name']")
	public WebElement AttachspacePurchaseDoc;
	@FindBy(xpath = "//button[@title='Clear selected files']//i//following::span")
	public WebElement RemovePurchaseDoc;
	@FindBy(xpath = "//*[@id=\"purchase_table\"]/tbody/tr[1]/td[3]")
	public WebElement ChkAddedPurchseLocation;
	@FindBy(xpath = "//table[@id='purchase_table']/tbody[1]/tr[1]/td[4]")
	public WebElement ChkAddedPurchseSupplier;
	@FindBy(xpath = "//*[@id=\"purchase_table\"]/tbody/tr[1]/td[5]/span[1]")
	public WebElement ChkAddedPurchseStatus;
	@FindBy(xpath = "//*[@id=\"purchase_table\"]/tbody/tr[1]/td[7]/span")
	public WebElement ChkAddedPurchseGrandTotal;

	@FindBy(id = "supplier_id-error")
	public WebElement SupplierWarningMsg;
	@FindBy(id = "status-error")
	public WebElement StatusWarningMsg;
	@FindBy(id = "location_id-error")
	public WebElement LocationWarningMsg;
	@FindBy(xpath = "//*[@id=\"toast-container\"]/div/div")
	public WebElement ProductCodeWarningMsg;

	public void navToAddPurchase() {
		objwaitconditions.waitForElementTobeVisible(driver, Purchase, 8);
		objPageUtilities.clickOnElement(Purchase);
		objwaitconditions.waitForElementTobeVisible(driver, AddPurchase, 10);
		objPageUtilities.clickOnElement(AddPurchase);
	}

	public void clearAllPurchasefields() {
		objPageUtilities.clearText(PurchaseStatus);
		objPageUtilities.clearText(BusinessLocation);
		objPageUtilities.clearText(Supplier);
		if (SearchProductDeleteOption.isDisplayed()) {
			objPageUtilities.clickOnElement(SearchProductDeleteOption);
		}
	}

	public void addSupplier() throws InterruptedException {
		objwaitconditions.waitForElementTobeVisible(driver, SupplierLabel, 10);
		objPageUtilities.clickOnElement(AddSupplier);
		objwaitconditions.waitForElementTobeVisible(driver, Supplier_Name, 6);
		objPageUtilities.sendText(Supplier_Name, "ABC_Company");
		objPageUtilities.sendText(BusinessName, "Trading");
		objPageUtilities.sendText(Mobile, "9356238756");
		objPageUtilities.clickOnElement(SaveSupplier);
		objwaitconditions.waitS(4000);
	}

	public void uploadAddPurchaseFile(WebElement browse, String path) throws InterruptedException {
		objPageUtilities.sendText(browse, path);
	}

	public void removeAddPurchaseFile(WebElement removefile) throws InterruptedException {
		objPageUtilities.clickOnElement(removefile);
		objwaitconditions.waitS(2000);
	}

	public void enterPurchaseStatus() {
		Actions objAction = new Actions(driver);
		objAction.moveToElement(PurchaseStatus).click().keyDown(Keys.ARROW_DOWN).keyDown(Keys.ENTER).build().perform();
	}

	public void enterBusinesssLocation() {
		Actions objAction = new Actions(driver);
		objAction.moveToElement(BusinessLocation).click().keyDown(Keys.ARROW_DOWN).keyDown(Keys.ARROW_DOWN)
				.keyDown(Keys.ARROW_DOWN).keyDown(Keys.ENTER).build().perform();
	}

	public void enterProductSearch() {
		Actions objAction = new Actions(driver);
		objAction.moveToElement(SearchProductFromPurchase).click().sendKeys("Chocolate").build().perform();
	}

	public void enterPurchaseData() throws InterruptedException {
		enterPurchaseStatus();
		objwaitconditions.fluentWait(driver, PurchaseStatus);
		enterBusinesssLocation();
		enterProductSearch();
		objPageUtilities.clickOnElement(ChocolateSelection);
		objwaitconditions.waitForElementTobeVisible(driver, SearchProductDeleteOption, 30);
		objPageUtilities.clearText(PaymentAmount);
		objPageUtilities.sendText(PaymentAmount, "1500");
		objwaitconditions.fluentWait(driver, PaymentAmount, "class", "form-control payment-amount input_number");
		objPageUtilities.selectFuncbyValue(PaymentMethod, "cash");
	}

	public void savePurchase() throws IOException {
		objPageUtilities.clickOnElement(SubmitPurchase);
	}

	public void enterDataWithoutStatus() throws InterruptedException {
		enterBusinesssLocation();
		enterProductSearch();
		objPageUtilities.clickOnElement(ChocolateSelection);
		objwaitconditions.waitForElementTobeVisible(driver, SearchProductDeleteOption, 30);
		objPageUtilities.clearText(PaymentAmount);
		objPageUtilities.sendText(PaymentAmount, "1500");
		objwaitconditions.fluentWait(driver, PaymentAmount, "class", "form-control payment-amount input_number");
		objPageUtilities.selectFuncbyValue(PaymentMethod, "cash");
	}

	public void enterDataWithoutLocation() throws InterruptedException {
		enterPurchaseStatus();
		objwaitconditions.fluentWait(driver, PurchaseStatus, "title", "Received");
		enterProductSearch();
		objPageUtilities.clickOnElement(ChocolateSelection);
		objwaitconditions.waitForElementTobeVisible(driver, SearchProductDeleteOption, 30);
		objPageUtilities.clearText(PaymentAmount);
		objPageUtilities.sendText(PaymentAmount, "1500");
		objwaitconditions.fluentWait(driver, PaymentAmount, "class", "form-control payment-amount input_number");
		objPageUtilities.selectFuncbyValue(PaymentMethod, "cash");
	}

	public void enterDataWithoutProduct() throws InterruptedException {
		enterPurchaseStatus();
		objwaitconditions.fluentWait(driver, PurchaseStatus, "title", "Received");
		enterBusinesssLocation();
		objPageUtilities.clearText(PaymentAmount);
		objPageUtilities.sendText(PaymentAmount, "1500");
		objwaitconditions.fluentWait(driver, PaymentAmount, "class", "form-control payment-amount input_number");
		objPageUtilities.selectFuncbyValue(PaymentMethod, "cash");
		;
		objPageUtilities.clickOnElement(SubmitPurchase);
		objwaitconditions.waitForElementTobeVisible(driver, ProductCodeWarningMsg, 3);
	}
}

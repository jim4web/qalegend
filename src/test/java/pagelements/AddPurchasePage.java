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
import utilities.Screenshot;
import utilities.WaitConditions;

public class AddPurchasePage {
	public WebDriver driver;
	Screenshot objScreenshot;
	BrowserLaunch objLaunch=new BrowserLaunch();
	WaitConditions objwaitconditions=new WaitConditions();
	
	public AddPurchasePage(WebDriver driver) {
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(id="tour_step6_menu")
	public WebElement Purchase;

	@FindBy(xpath="//*[@id=\"tour_step6\"]/ul/li[2]/a")
	public WebElement AddPurchase;
	
	@FindBy(xpath="//*[@id=\"add_purchase_form\"]/div[1]/div/div/div[1]/div/div/span[3]/button/i")
	public WebElement AddSupplier;
		
	@FindBy(xpath="//*[@id=\"add_purchase_form\"]/div[1]/div/div/div[1]/div/label")
	public WebElement SupplierLabel;
	@FindBy(xpath="//*[@id=\"select2-supplier_id-container\"]")
	public WebElement Supplier; 

	@FindBy(id="name")
	public WebElement Supplier_Name; 
	@FindBy(id="supplier_business_name")
	public WebElement BusinessName; 
	@FindBy(id="mobile")
	public WebElement Mobile; 
	@FindBy(xpath="//button[@class='btn btn-primary']")
	public WebElement SaveSupplier; 
	@FindBy(xpath="//*[@id=\"toast-container\"]/div")
	public WebElement SaveSupplierInfoMsg; 
	
	@FindBy(id="upload_document")
	public WebElement BrowsePurchaseDoc;
	@FindBy(xpath="//*[@id=\"add_purchase_form\"]/div[1]/div/div/div[8]/div/div/div[2]/div[1]")
	public WebElement AttachspacePurchaseDoc;
	@FindBy(xpath="//*[@id=\"add_purchase_form\"]/div[1]/div/div/div[8]/div/div/div[2]/div[2]/button[1]/span")
	public WebElement RemovePurchaseDoc;
	
	@FindBy(xpath="//*[@id=\"select2-status-container\"]")
	public WebElement PurchaseStatus; 
	@FindBy(xpath="//*[@id=\"select2-location_id-container\"]")
	public WebElement BusinessLocation; 
	@FindBy(xpath="//*[@id=\"amount_0\"]")
	public WebElement PaymentAmount; 
	@FindBy(xpath="//*[@id=\"method_0\"]")
	public WebElement PaymentMethod;
	@FindBy(xpath="//*[@id=\"search_product\"]")
	public WebElement SearchProductFromPurchase;
	@FindBy(xpath="//*[@id=\"ui-id-1\"]/li[3]")
	public WebElement ChocolateSelection;
	@FindBy(xpath="//*[@id=\"purchase_entry_table\"]/tbody/tr/td[14]/i")
	public WebElement SearchProductDeleteOption;
	
	
	@FindBy(id="submit_purchase_form")
	public WebElement SubmitPurchase; 
	
	@FindBy(xpath="/html/body/div[2]/div[1]/section[2]/div[2]/div[1]/h3")
	public WebElement AllPurchasesLabel;
	@FindBy(xpath="//*[@id=\"purchase_table\"]/tbody/tr[1]/td[3]")
	public WebElement ChkAddedPurchseLocation;
	@FindBy(xpath="//table[@id='purchase_table']/tbody[1]/tr[1]/td[4]")
	public WebElement ChkAddedPurchseSupplier;
	@FindBy(xpath="//*[@id=\"purchase_table\"]/tbody/tr[1]/td[5]/span[1]")
	public WebElement ChkAddedPurchseStatus;
	@FindBy(xpath="//*[@id=\"purchase_table\"]/tbody/tr[1]/td[7]/span")
	public WebElement ChkAddedPurchseGrandTotal;
	
	@FindBy(id="supplier_id-error")
	public WebElement SupplierWarningMsg; 
	@FindBy(id="status-error")
	public WebElement StatusWarningMsg; 
	@FindBy(id="location_id-error")
	public WebElement LocationWarningMsg; 
	@FindBy(xpath="//*[@id=\"toast-container\"]/div/div")
	public WebElement ProductCodeWarningMsg;
	
	
	public void navToAddPurchase() {
		objwaitconditions.waitForElementTobeVisible(driver, Purchase, 8);
		Purchase.click();
		objwaitconditions.waitForElementTobeVisible(driver, AddPurchase, 6);
		AddPurchase.click();		
	}
	
	public void clearAllPurchasefields() {
		PurchaseStatus.clear();
		BusinessLocation.clear();
		Supplier.clear();
		if (SearchProductDeleteOption.isDisplayed()) {
		SearchProductDeleteOption.click();
		}
	}
	
	public void addSupplier() throws InterruptedException {
		objwaitconditions.waitForElementTobeVisible(driver, SupplierLabel, 8);
		AddSupplier.click();
		objwaitconditions.waitForElementTobeVisible(driver, Supplier_Name, 6);
		Supplier_Name.sendKeys("ABC_Company");
		BusinessName.sendKeys("Trading");
		Mobile.sendKeys("9356238756");
		SaveSupplier.click();
		objwaitconditions.waitForElementTobeVisible(driver, SupplierLabel, 20);
		objwaitconditions.waitS(4000);  
		String displaymsgFromAppli=Supplier.getText();
			if(displaymsgFromAppli.contains("ABC_Company")) {		
				Assert.assertTrue(true);
			}else {
				Assert.assertTrue(false, "Wrong name displayed");	
			}
	}
	
	public void uploadAddPurchaseFile(WebElement browse, String path) throws InterruptedException {
		objwaitconditions.waitS(3000);
		browse.sendKeys(path);
	}
	public void checkUploading(WebElement attacharea) throws InterruptedException {
		objwaitconditions.waitS(3000);
		String attach = attacharea.getAccessibleName();
		if(attach.isEmpty()) {		
			Assert.assertTrue(false, "Document Not uploaded.");
		}else {
			Assert.assertTrue(true);	
		}		
	}
	
	public void removeAddPurchaseFile(WebElement removefile) throws InterruptedException {
		objwaitconditions.waitS(3000);
		removefile.click();		
	}
	public void checkUploadRemoving(WebElement attacharea) throws InterruptedException {
		objwaitconditions.waitS(3000);
		String attach = attacharea.getAccessibleName();
		if(attach.isEmpty()) {		
			Assert.assertTrue(true, "Document Not uploaded.");
		}else {
			Assert.assertTrue(false);	
		}		
	}
	
	public void enterPurchaseData() throws InterruptedException {
		Actions objAction=new Actions(driver);
		objAction.moveToElement(PurchaseStatus).click().keyDown(Keys.ARROW_DOWN).keyDown(Keys.ENTER).build().perform();
		objwaitconditions.waitS(3000);
		objAction.moveToElement(BusinessLocation).click().keyDown(Keys.ARROW_DOWN).keyDown(Keys.ARROW_DOWN).keyDown(Keys.ARROW_DOWN).keyDown(Keys.ENTER).build().perform();
		objAction.moveToElement(SearchProductFromPurchase).click().sendKeys("Chocolate").build().perform();
		ChocolateSelection.click();
		objwaitconditions.waitS(9000);
		PaymentAmount.clear();
		PaymentAmount.sendKeys("1500");
		objwaitconditions.waitS(4000);
		Select objSelect = new Select(PaymentMethod);
		objSelect.selectByValue("cash");	
	}
	
	public void savePurchase() throws IOException {
		SubmitPurchase.click();
	}
	
	public void checkAddedPurchasefields(String text,WebElement addedelement) throws InterruptedException {
		objwaitconditions.waitForElementTobeVisible(driver, addedelement, 25);
		objwaitconditions.waitS(9000);
		String listPurchaseByName=addedelement.getText();
		Assert.assertEquals(listPurchaseByName, text);			
	}
	
	public void enterDataWithoutStatus() throws InterruptedException {
		Actions objAction=new Actions(driver);
		objAction.moveToElement(BusinessLocation).click().keyDown(Keys.ARROW_DOWN).keyDown(Keys.ARROW_DOWN).keyDown(Keys.ARROW_DOWN).keyDown(Keys.ENTER).build().perform();
		objAction.moveToElement(SearchProductFromPurchase).click().sendKeys("Chocolate").build().perform();
		ChocolateSelection.click();
		objwaitconditions.waitS(9000);
		PaymentAmount.clear();
		PaymentAmount.sendKeys("1500");
		objwaitconditions.waitS(4000);
		Select objSelect = new Select(PaymentMethod);
		objSelect.selectByValue("cash");
	}
	
	
	public void enterDataWithoutLocation() throws InterruptedException {
		Actions objAction=new Actions(driver);
		objAction.moveToElement(PurchaseStatus).click().keyDown(Keys.ARROW_DOWN).keyDown(Keys.ENTER).build().perform();
		objwaitconditions.waitS(3000);
		objAction.moveToElement(SearchProductFromPurchase).click().sendKeys("Chocolate").build().perform();
		ChocolateSelection.click();
		objwaitconditions.waitS(9000);
		PaymentAmount.clear();
		PaymentAmount.sendKeys("1500");
		objwaitconditions.waitS(4000);
		Select objSelect = new Select(PaymentMethod);
		objSelect.selectByValue("cash");	
	}
	
	public void enterDataWithoutProduct() throws InterruptedException {
		Actions objAction=new Actions(driver);
		objAction.moveToElement(PurchaseStatus).click().keyDown(Keys.ARROW_DOWN).keyDown(Keys.ENTER).build().perform();
		objwaitconditions.waitS(3000);
		objAction.moveToElement(BusinessLocation).click().keyDown(Keys.ARROW_DOWN).keyDown(Keys.ARROW_DOWN).keyDown(Keys.ARROW_DOWN).keyDown(Keys.ENTER).build().perform();
		PaymentAmount.clear();
		PaymentAmount.sendKeys("1500");
		objwaitconditions.waitS(4000);
		Select objSelect = new Select(PaymentMethod);
		objSelect.selectByValue("cash");
		SubmitPurchase.click();
		objwaitconditions.waitS(3000);
		  objwaitconditions.waitForElementTobeVisible(driver, ProductCodeWarningMsg, 20);
		  String displaymsgFromAppli=ProductCodeWarningMsg.getText();
			if(displaymsgFromAppli.contains("No Products added, add some products first")) {		
				Assert.assertTrue(true);
			}else {
				Assert.assertTrue(false, "Warning message comparison failed");	
			}
	  }	
	
}

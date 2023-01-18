package pagelements;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;

import utilities.BrowserLaunch;
import utilities.PageUtilities;
import utilities.WaitConditions;

public class LoginPage {

	public WebDriver driver;
	BrowserLaunch objLaunch = new BrowserLaunch();
	WaitConditions objwaitconditions = new WaitConditions();
	PageUtilities objPageUtilities = new PageUtilities();

	public LoginPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(id = "username")
	private WebElement LUserName;
	@FindBy(id = "password")
	private WebElement LPassword;
	@FindBy(xpath = "/html/body/div[2]/div[1]/section[1]/h1")
	private WebElement LHeader;
	@FindBy(xpath = "//*[@id=\"step-0\"]/div[3]/button[3]")
	private WebElement LEndTour;
	
	@FindBy(name = "remember")
	public WebElement LRememberChkBox;
	@FindBy(xpath = "//button[@type='submit']")
	public WebElement LloginButton;
	@FindBy(xpath = "//*[@class='help-block']//following::strong")
	public WebElement LErrormsg;

	public void enterCredentials(String state1, String state2) {
		objPageUtilities.sendText(LUserName, state1);
		objPageUtilities.sendText(LPassword, state2);
	}

	public void clearCredentials() {
		objPageUtilities.clearText(LUserName);
		objPageUtilities.clearText(LPassword);
	}

	public void endtourClick() {
		objPageUtilities.clickOnElement(LEndTour);
	}

	public void loginapp() {
		enterCredentials("admin", "123456");
		objPageUtilities.clickOnElement(LloginButton);
		objwaitconditions.waitForElementTobeVisible(driver, LEndTour, 8);
		endtourClick();
	}

	public Boolean isHomePageLoaded() {
		objwaitconditions.waitForElementTobeVisible(driver, LHeader, 6);
		endtourClick();
		return LHeader.isDisplayed();
	}
}

package pagelements;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;

import utilities.BrowserLaunch;
import utilities.WaitConditions;

public class LoginPage {

	public WebDriver driver;
	BrowserLaunch objLaunch=new BrowserLaunch();
	WaitConditions objwaitconditions=new WaitConditions();
	
	public LoginPage(WebDriver driver) {
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(id="username")
	public WebElement UserName;
	
	@FindBy(id="password")
	public WebElement Password;
	
	@FindBy(name="remember")
	public WebElement RememberChkBox;
	
	@FindBy(xpath="/html/body/div[3]/div/div/div/div[2]/form/div[4]/div/button")
	public WebElement LoginButton;
	
	@FindBy(xpath="/html/body/div[3]/div/div/div/div[2]/form/div[4]/div/a")
	public WebElement ForgotButton;
	
	@FindBy(xpath="/html/body/div[2]/div[1]/section[1]/h1")
	public WebElement Header;
	
	@FindBy(xpath="//*[@id=\"step-0\"]/div[3]/button[3]")
	public WebElement EndTour;
	
	@FindBy(xpath="/html/body/div[3]/div/div/div/div[2]/form/div[1]/div/span/strong")
	public WebElement Errormsg;
	
	public void enterCredentials(String state1, String state2) {
		UserName.sendKeys(state1);
		Password.sendKeys(state2);
	}
	
	public void clearCredentials() {
		UserName.clear();
		Password.clear();
	}
	
	public void tickChkBox() {
		RememberChkBox.click();	
	}
	
	public void loginClick() {
		LoginButton.click();	
	}
	
	public void forgotClick() {
		ForgotButton.click();	
	}
	
	public void endtourClick() {
		EndTour.click();	
	}
	
	public void loginapp() {
		enterCredentials("admin","123456");
		  loginClick();
		  WaitConditions objwaitconditions=new WaitConditions();
		  objwaitconditions.waitForElementTobeVisible(driver, EndTour, 8);
		  endtourClick();
	}
	
	  public Boolean isHomePageLoaded() {
	        //example of using wait utility methods
		  objwaitconditions.waitForElementTobeVisible(driver, Header, 2);
		    endtourClick();
	        return Header.isDisplayed();
	    }
	  
	  public void comparingMessage() {
		  
		  objwaitconditions.waitForElementTobeVisible(driver, Errormsg, 2);
		  String displaymsgFromAppli=Errormsg.getText();
			if(displaymsgFromAppli.contains("These credentials do not match our records.")) {		
				Assert.assertTrue(true);
			}else {
				Assert.assertTrue(false, "Warning message comparison failed");	
			}
	  }
	  
	  
}

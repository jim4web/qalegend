package utilities;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;

import io.github.bonigarcia.wdm.WebDriverManager;
import io.qameta.allure.Allure;
import io.qameta.allure.Attachment;

public class BrowserLaunch {

	public static WebDriver driver;
	Screenshot objScreenshot = new Screenshot();
	WaitConditions objwaitconditions = new WaitConditions();

	public void launchingBrowser(String url, String browser) {
		if (browser.equalsIgnoreCase("Chrome")) {
			driver = WebDriverManager.chromedriver().create();
//Choosing Incognito mode
//					ChromeOptions option = new ChromeOptions();
//					option.addArguments("incognito");
//					WebDriverManager.chromedriver().setup();
//					driver = new ChromeDriver(option);
		} else if (browser.equalsIgnoreCase("Firefox")) {
			driver = WebDriverManager.firefoxdriver().create();
		} else if (browser.equalsIgnoreCase("Edge")) {
			driver = WebDriverManager.edgedriver().create();
		} else {
			System.out.println("No option for this Browser");
		}
		driver.manage().window().maximize();
		driver.manage().deleteAllCookies();
		driver.get(url);
		objwaitconditions.implicitWait(driver);
	}

	public void closeBrowser() {
		driver.close();
	}

	@AfterMethod(alwaysRun = true)
	@Attachment(value = "Screenshot", type = "image/png")
	public void afterMethod(ITestResult iTestResult) throws IOException {
		if (iTestResult.getStatus() == iTestResult.FAILURE) {
			objScreenshot.failureScreenshot(driver, iTestResult.getName());
		}
	}

	@BeforeTest(alwaysRun = true)
	@Parameters({ "Url", "Browser" })
	public WebDriver beforeTest(String Url, String Browser) throws IOException {
		launchingBrowser(Url, Browser);
		this.driver = driver;
		return driver;
	}

	@AfterTest(alwaysRun = true)
	public void afterTest() {
		closeBrowser();
	}

}

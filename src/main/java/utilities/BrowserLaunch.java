package utilities;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class BrowserLaunch {
	
public static WebDriver driver;
	
	public void launchingBrowser(String url, String browser) {
		if(browser.equals("Chrome")) 
		{
		System.setProperty("webdriver.chrome.driver", "C:\\Users\\RS\\Desktop\\Testing\\chromedriver_win32\\chromedriver.exe");
		  driver = new ChromeDriver();
		} 
		else if (browser.equals("Firefox")) {
			System.setProperty("webdriver.gecko.driver", "C:\\Users\\RS\\Desktop\\Testing\\geckodriver-v0.32.0-win64\\geckodriver.exe");
			  driver = new FirefoxDriver();
		} 
		else if (browser.equals("Edge")) {
			System.setProperty("webdriver.edge.driver", "C:\\Users\\RS\\Desktop\\Testing\\edgedriver_win64\\msedgedriver.exe");
			  driver = new EdgeDriver();
		}
		else {
			System.out.println("Failed launching Browser");
		}
		
		driver.manage().window().maximize();
		driver.get(url);
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	}
	
	public void closeBrowser() {
		driver.close();
	}


}

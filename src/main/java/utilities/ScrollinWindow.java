package utilities;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class ScrollinWindow {
	public WebDriver driver;

	public void scrollToElement(WebElement element) throws InterruptedException {
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);
		
	}
	public void scrollpixel(int a, int b, WebDriver driver) throws InterruptedException {
		((JavascriptExecutor) driver).executeScript("javascript:window.scrollBy(" + a + "," + b + ")");
	}
	public void scrolldown(WebDriver driver) throws InterruptedException {
		((JavascriptExecutor) driver).executeScript("window.scrollBy(0,2000)","");
	}
	public void scrollTop(WebDriver driver) throws InterruptedException {
		((JavascriptExecutor) driver).executeScript("window.scrollTo(document.body.scrollHeight,0)");
	}
}

package utilities;

import java.time.Duration;
import java.util.NoSuchElementException;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

public class WaitConditions {
	// Implicit wait
	public void implicitWait(WebDriver driver) {
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	}

	// Explicit wait for element to be visible
	public void waitForElementTobeVisible(WebDriver driver, WebElement element, long seconds) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(seconds));
		wait.until(ExpectedConditions.visibilityOf(element));
	}

	// Explicit wait for element to be clickable
	public void waitForElemntTobeClickable(WebDriver driver, WebElement element, Duration i) {
		WebDriverWait wait = new WebDriverWait(driver, i);
		wait.until(ExpectedConditions.elementToBeClickable(element));
	}

	public void waitS(long timeout) {
		try {
			Thread.sleep(timeout);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	// Fluent Wait
	public void fluentWait(WebDriver driver, WebElement element, String attribute, String attributeValue) {
		Wait<WebDriver> fwait = new FluentWait<WebDriver>(driver).withTimeout(Duration.ofMillis(4000))
				.pollingEvery(Duration.ofMillis(500)).ignoring(NoSuchElementException.class);
		fwait.until(ExpectedConditions.attributeToBe(element, attribute, attributeValue));
	}

	public void fluentWait(WebDriver driver, WebElement element) {
		Wait<WebDriver> fwait = new FluentWait<WebDriver>(driver).withTimeout(Duration.ofMillis(5000))
				.pollingEvery(Duration.ofMillis(250)).ignoring(NoSuchElementException.class);
		fwait.until(ExpectedConditions.visibilityOf(element));
	}
}

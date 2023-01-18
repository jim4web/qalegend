package utilities;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

import com.google.common.io.Files;

import io.qameta.allure.Allure;

public class Screenshot {

	public WebDriver driver;
	public static String syspath = System.getProperty("user.dir");

	public void screenshots(WebDriver driver) throws IOException {
		Date currentDate = new Date();
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd-HH-mm-ss-SSS");
		String dateAndTime = dateFormat.format(currentDate);
		File screenshotFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		String screenshotDestination = syspath + "\\src\\test\\resources\\Screenshots\\imagename_" + dateAndTime
				+ ".jpg";
		File path = new File(screenshotDestination);
		Files.copy(screenshotFile, path);
	}

	public void failureScreenshot(WebDriver driver, String tagname) throws IOException {
		Date currentDate = new Date();
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd-HH-mm-ss-SSS");
		String dateAndTime = dateFormat.format(currentDate);
		System.out.println(tagname);
		File screenshotFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		String fileDestination = syspath + "\\src\\test\\resources\\Screenshots\\imagename_" + tagname + dateAndTime
				+ ".jpg";
		File path = new File(fileDestination);
		Files.copy(screenshotFile, path);
		Allure.addAttachment(tagname,
				new ByteArrayInputStream(((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES)));

	}

	public String datetoday() {
		Date currentDate = new Date();
		SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
		String todaysdate = dateFormat.format(currentDate);
		return todaysdate;
	}
}

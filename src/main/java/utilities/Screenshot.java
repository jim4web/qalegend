package utilities;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

import com.google.common.io.Files;

public class Screenshot {

public WebDriver driver;

	public void screenshots(WebDriver driver) throws IOException
	{
		Date currentDate = new Date();
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd-HH-mm-ss-SSS");
		String dateAndTime = dateFormat.format(currentDate);
		System.out.println(dateAndTime);
		File screenshotFile=((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		File path= new File("C:\\Users\\RS\\eclipse-workspace\\qalegend\\src\\test\\resources\\Screenshots\\imagename_" + dateAndTime + ".jpg");
		Files.copy(screenshotFile, path);
	}
	
	public void failureScreenshot(WebDriver driver, String tagname) throws IOException
	{   
		Date currentDate = new Date();
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd-HH-mm-ss-SSS");
		String dateAndTime = dateFormat.format(currentDate);
		System.out.println(dateAndTime);
		File screenshotFile=((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		File path= new File("C:\\Users\\RS\\eclipse-workspace\\qalegend\\src\\test\\resources\\Screenshots\\imagename_" +tagname+ dateAndTime + ".jpg");
		Files.copy(screenshotFile, path);
	}

	public String datetoday() {
		Date currentDate = new Date();
		SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
		String todaysdate = dateFormat.format(currentDate);
		return todaysdate;
	}
	
}

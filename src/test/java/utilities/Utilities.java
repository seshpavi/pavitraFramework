package utilities;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;
import java.util.NoSuchElementException;
import java.util.function.Function;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

import constants.WaitConstants;




public class Utilities {
	
	public static boolean waitForElement(WebDriver driver , WebElement element) {
		
		Boolean isElementClickable = false;
		WebDriverWait wait = new WebDriverWait(driver, WaitConstants.EXPLICIT_WAIT_CONSTANT);
		
		try {	
			wait.until(ExpectedConditions.elementToBeClickable(element));
			isElementClickable = true;
		}
		
		catch(Exception e){
			e.printStackTrace();
		}
		return isElementClickable;
	}

	public static String captureScreenShot(WebDriver driver) throws IOException  {
		TakesScreenshot screenshot = (TakesScreenshot)driver;
		File sourceFile = screenshot.getScreenshotAs(OutputType.FILE);
		String dateformat = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());
		String dstpath = System.getProperty("user.dir")+"\\sfdcautomationframework\\src\\test\\java\\screenshots"+ dateformat +"_sfdc.PNG";
		File destFile = new File(dstpath);
		FileUtils.copyFile(sourceFile, destFile);
		return dstpath;
	}
	
	public static void moveToElement(WebDriver driver, WebElement element) {
		
		Actions action = new Actions(driver);
		action.moveToElement(element).build().perform();
		
	}
	
	public static void jsClick(WebDriver driver, WebElement element) {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].click();", element);
	}


	public static WebElement fluentlyWait(final WebElement element, WebDriver driver) {
		
		Wait<WebDriver> wait  = new FluentWait<WebDriver>(driver)
				.withTimeout(Duration.ofSeconds(30))
				.pollingEvery(Duration.ofSeconds(30))
				.ignoring(NoSuchElementException.class);
		
		WebElement ele = wait.until(new Function<WebDriver, WebElement>() {
			public WebElement apply(WebDriver driver) {
				return element;
			}
		});
		
		return ele;
		
	}

}

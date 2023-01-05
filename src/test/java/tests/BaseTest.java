package tests;


import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

import io.github.bonigarcia.wdm.WebDriverManager;
import pages.BasePage;

public class BaseTest {
	
	protected static ThreadLocal<WebDriver> threadlocal = new ThreadLocal<WebDriver>();
	public static ExtentReports extent = null;
	protected static Logger logger = LogManager.getLogger(BasePage.class.getName());
	
	@BeforeMethod
	public void setDriver() {
	logger.info("setDriver initiated ");
	WebDriver driver = BaseTest.getBrowserType("chrome", false);
	logger.info("driver started ");
	threadlocal.set(driver);
	}
	public static WebDriver getDriver() {
		return threadlocal.get();
			}
	
	@AfterMethod
	public static void removeDriver() {
		getDriver().quit();
		threadlocal.remove();
		logger.info("remove sucess ");
		
	}
	@BeforeSuite
	public void setUp() {
		configureReports();
	}
	
	@AfterSuite
	public void tearDown() {
		extent.flush();
	}
	
	public void configureReports() {
		logger.info("configure reports initiated");
		String dateformat = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());
		String reportpath = System.getProperty("user.dir")+ "\\src\\test\\java\\reportssfdc\\"+ dateformat +"_sfdcr1.html";
		extent = new ExtentReports();
		ExtentSparkReporter spark = new ExtentSparkReporter(reportpath);
		extent.attachReporter(spark);
		logger.info("configure extent report sucess");
	}
	public  static WebDriver getBrowserType(String browsername, boolean headless) {
		
		String browser = browsername.toLowerCase();
		WebDriver driver = null;
		switch (browser){
		
		case "chrome" :
		
			WebDriverManager.chromedriver().setup();
			if(headless) {
				ChromeOptions co = new ChromeOptions();
				co.addArguments("--headless");
				driver = new ChromeDriver(co);
						}
			else {
				driver = new ChromeDriver();
			}
		
			break;
			
		case "edge" :
		
			WebDriverManager.edgedriver().setup();
			if(headless) {
				EdgeOptions eo = new EdgeOptions();
			//	eo.addArguments("--headless");
				driver = new EdgeDriver(eo);
						}
			else {
				driver = new EdgeDriver();
			}
			
			break;
			
			default :
				System.out.println("provide the right browser name");
		}
		
		
		return driver;
	}
	
}

package pages;



import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

import utilities.Datautils;
import utilities.Utilities;

public class Loginpage extends BasePage{
	
	public Loginpage(WebDriver driver,  ExtentTest test) {
		PageFactory.initElements(driver, this);
		this.test = test;
	}
	
	@FindBy(id = "username")
	public WebElement username;
	

	@FindBy(name = "pw")
	public WebElement password;
	

	@FindBy(id = "Login")
	public WebElement loginbutton;
	
	@FindBy(xpath = "//*[@id = 'rememberUn']")
	public WebElement rememberMe;
	
	@FindBy(css ="#error")
	public WebElement loginerrorMsg;
	
	@FindBy(id="un")
	public WebElement forgotUserName;
	
	@FindBy(partialLinkText="Forgot Your")
	public WebElement forgotpassword;
	

	@FindBy(id="idcard-identity")
	public WebElement savedUsername;
	
	@FindBy(xpath = "//a[@id='forgot_password_link']")
	public WebElement forgotpasswordLink;
	
	@FindBy(xpath = "//input[@id='un']")
	public WebElement forgotpasswordUsername;
	
	@FindBy(xpath = "//input[@name='continue']")
	public WebElement continuebutton;
	
	public boolean launchapp(WebDriver driver) throws IOException {
		
		driver.get(Datautils.appLaunchUrl("prod.sfdc"));
		test.info("app launched");
		return true;
		
	}
	
	public boolean enterUsername(String usrnme, WebDriver driver) throws IOException {
		
		if(username.isDisplayed()) {
			username.sendKeys(usrnme);
			logger.info("username entered");
			return true;
		}
		else {
			test.fail("usrname not enetered");
			logger.info("username element not found");
			test.addScreenCaptureFromPath(Utilities.captureScreenShot(driver));
			logger.error("username error");
		return false;
		}
	}
	public String getUsername( ) {
		
		return username.getText();
		
		}
	
	public boolean enterPassword(String pwd, WebDriver driver) throws IOException {
		
		if(password.isDisplayed()) {
			password.sendKeys(pwd);
			test.info("password entered");
			return true;
		}
		else {
			test.fail("password not enetered");
			test.addScreenCaptureFromPath(Utilities.captureScreenShot(driver), "password not entered");
		return false;
		}
	}
	
	public void clearPassword() {
		test.info("password cleared");
		password.clear();
	}
	
	public boolean selectRememberme(WebDriver driver) throws IOException {
		boolean checkboxstatus = false;
		if(rememberMe.isSelected()) {
			test.info("remeber me check box is already selected");
			checkboxstatus = true;			
		}
		else {		
		rememberMe.click();
		test.log(Status.PASS, "remeber me button clicked now");
		test.addScreenCaptureFromPath(Utilities.captureScreenShot(driver), "remeberme selected");
		checkboxstatus = true;
		}
		return checkboxstatus;
	}
	public boolean clickLogin(WebDriver driver) throws IOException {
	
	if(loginbutton.isDisplayed()) {
		loginbutton.click();
		test.info("login button clicked");
		return true;
	}
	else {
		test.fail("login button not clicked");
		test.addScreenCaptureFromPath(Utilities.captureScreenShot(driver), "login failure");
		return false;
	}
}
	
	public boolean freeTrial(WebDriver driver) throws IOException {
		test.addScreenCaptureFromPath(Utilities.captureScreenShot(driver), "free trial not seen");
		return false;
	}
	public boolean savedUsername() {
		if(savedUsername.isDisplayed()) {
			test.info("saved username is seen");
			return true;
		}
		else {
			test.info("saved username is not seen");
			return false;
		}
	}
	public String getsavedusername() {
		return savedUsername.getText();
		
	}
	
	public Boolean LoginSFDC(WebDriver driver) throws IOException {
		boolean applaunchstatus = launchapp(driver);
		boolean isloginsfdc = false;
		if(applaunchstatus) {
			enterUsername(Datautils.readAccounts("valid.username"), driver);
			enterPassword(Datautils.readAccounts("valid.password"), driver);
			clickLogin(driver);
			
			isloginsfdc = true;
			test.pass("login passed");
		}
		else {
			test.fail("login button not clicked");
			test.addScreenCaptureFromPath(Utilities.captureScreenShot(driver), "login failure");
		}
		return isloginsfdc;
		
	}
	
	public boolean forgotPassword() throws IOException {
		forgotpasswordLink.click();
		if(forgotpasswordUsername.isDisplayed()) {
			forgotpasswordUsername.click();
			forgotpasswordUsername.sendKeys(Datautils.readAccounts("valid.username"));
			continuebutton.click();
		}
		
		return true;
	}
	
	
}

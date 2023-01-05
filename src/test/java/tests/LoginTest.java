package tests;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

import pages.Loginpage;
import pages.UserMenu;
import utilities.Datautils;
import utilities.Utilities;

public class LoginTest extends BaseTest {
	
	@Test
	public void loginErrorMsgTC01() throws IOException{
		ExtentTest test = extent.createTest("loginErrorMsgTC01");
		WebDriver driver = BaseTest.getDriver();
		Loginpage lp = new Loginpage(driver,test);
		Assert.assertTrue((lp.launchapp(driver)),"should launch the app");
		test.log(Status.PASS, "APP IS LAUNCHD");
		Assert.assertTrue((lp.enterUsername(Datautils.readAccounts("valid.username"), driver)),"should enter the username");
		test.log(Status.PASS, "USERNAME ENTERED");
		lp.clearPassword();
		Assert.assertTrue((lp.clickLogin(driver)),"should enter the loginbutton");
		
		Assert.assertEquals(lp.loginerrorMsg.getText(), Datautils.readErrorMsgs("login.error.message"));
		
	}
	
	
	@Test
	public void loginErrorMsgTC02() throws IOException{
		ExtentTest test = extent.createTest("loginErrorMsgTC02");
		WebDriver driver = BaseTest.getDriver();
		Loginpage lp = new Loginpage(driver, test);
		Assert.assertTrue((lp.launchapp(driver)),"should launch the app");
		
		Assert.assertTrue((lp.enterUsername(Datautils.readAccounts("valid.username"),driver)),"should enter the username");
	
		Assert.assertTrue((lp.enterPassword(Datautils.readAccounts("valid.password"),driver)),"should enter the pasword");
	
		Assert.assertTrue((lp.clickLogin(driver)),"should enter the loginbutton");
		
		Assert.assertTrue(lp.freeTrial(driver), "free trial seen");
		
	}
	
	@Test
	public void loginErrorMsgTC03() throws InterruptedException, IOException{
		ExtentTest test = extent.createTest("loginErrorMsgTC03");
		WebDriver driver = BaseTest.getDriver();
		Loginpage lp = new Loginpage(driver, test);
		UserMenu ump  = new UserMenu(driver, test);
		
		//driver.manage().timeouts().implicitlyWait(15,TimeUnit.SECONDS);
		Assert.assertTrue((lp.launchapp(driver)),"should launch the app");
		
		Assert.assertTrue((lp.enterUsername(Datautils.readAccounts("valid.username"),driver)),"should enter the username");
		
		Thread.sleep(5000);
		Assert.assertTrue((lp.enterPassword(Datautils.readAccounts("valid.password"),driver)), "should enter the pasword");
		
		Thread.sleep(5000);
		Assert.assertTrue(lp.selectRememberme(driver), "should click remmeber me");
		
		Thread.sleep(5000);
		Assert.assertTrue((lp.clickLogin(driver)),"should enter the loginbutton");
		
		Thread.sleep(5000);
		//Utilities.waitForElement(driver, ump.userMenu);
		Assert.assertTrue(ump.clickUserMenuOption(), "should click usermenu");
		
		Thread.sleep(5000);
		Assert.assertTrue(ump.selectUserMenuOption(("Logout"), driver), "should select logout");
		
		Thread.sleep(5000);
		Utilities.waitForElement(driver, lp.savedUsername);
		Assert.assertEquals(lp.getsavedusername(), Datautils.readAccounts("valid.username"));
		
	}
	
	@Test
	
	public void forgotPasswordtc_4a() throws IOException {
		ExtentTest test = extent.createTest("loginErrorMsgTC03");
		WebDriver driver = BaseTest.getDriver();
		Loginpage lp = new Loginpage(driver, test);
		UserMenu ump  = new UserMenu(driver, test);
		
		//driver.manage().timeouts().implicitlyWait(15,TimeUnit.SECONDS);
		Assert.assertTrue((lp.launchapp(driver)),"should launch the app");
		
		Assert.assertTrue((lp.enterUsername(Datautils.readAccounts("valid.username"),driver)),"should enter the username");
		Assert.assertTrue(lp.forgotPassword(), "forgotpasswordlink clicked");
		
		
	}
@Test
	
	public void forgotPasswordtc_4b() throws IOException, InterruptedException {
		ExtentTest test = extent.createTest("loginErrorMsgTC03");
		WebDriver driver = BaseTest.getDriver();
		Loginpage lp = new Loginpage(driver, test);
		UserMenu ump  = new UserMenu(driver, test);
		
		//driver.manage().timeouts().implicitlyWait(15,TimeUnit.SECONDS);
		Assert.assertTrue((lp.launchapp(driver)),"should launch the app");
		
		Assert.assertTrue((lp.enterUsername(Datautils.readAccounts("invalid.username"),driver)),"should enter the username");
		Thread.sleep(5000);
		Assert.assertTrue((lp.enterPassword(Datautils.readAccounts("invalid.password"),driver)), "should enter the pasword");
		

		Assert.assertTrue((lp.clickLogin(driver)),"should enter the loginbutton");
		
		Assert.assertEquals(lp.loginerrorMsg.getText(), Datautils.readErrorMsgs("login.error.message2"));
		
	}
}

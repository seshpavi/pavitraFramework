package tests;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentTest;

import constants.FileNameConstants;
import pages.Loginpage;
import pages.UserMenu;

public class UserMenuTest extends BaseTest{
	@Test
	public void myprofile_tc06() throws IOException, InterruptedException{
		ExtentTest test = extent.createTest("loginErrorMsgTC06");
		WebDriver driver = BaseTest.getDriver();
		Loginpage lp = new Loginpage(driver,test);
		UserMenu ump = new UserMenu(driver, test);
		Assert.assertTrue((lp.LoginSFDC(driver)),"should launch the app");
		Thread.sleep(5000);
		Assert.assertTrue(ump.verifyUserMenuOption(), "should match usermenu option");
		Thread.sleep(2000);
		Assert.assertTrue(ump.selectUserMenuOption("My Profile", driver));
		Thread.sleep(2000);
		Assert.assertTrue(ump.openEditProfileModel(driver), "should open edit pop up window");
		Thread.sleep(2000);
		Assert.assertTrue(ump.editlastNameinAboutTab(driver,"my new last name" ), "enter the last name");
		Thread.sleep(2000);
		Assert.assertTrue(ump.createPost(driver, "my new last name"));
		Thread.sleep(5000);
		Assert.assertTrue(ump.uploadFile(driver,FileNameConstants.FILE_PATH_UPLOAD));
		
	}
	
	@Test
	public void myprofile_tc07() throws IOException, InterruptedException{
		ExtentTest test = extent.createTest("loginErrorMsgTC07");
		logger.info("test started+++++++++++");
		WebDriver driver = BaseTest.getDriver();
		Loginpage lp = new Loginpage(driver,test);
		Thread.sleep(2000);
		UserMenu ump = new UserMenu(driver, test);
		Assert.assertTrue((lp.LoginSFDC(driver)),"should launch the app");
		Thread.sleep(5000);
		Assert.assertTrue(ump.clickUserMenuOption(), "usr menu clicked");
		Assert.assertTrue(ump.selectUserMenuOption("My Settings", driver));
		Thread.sleep(2000);
		//Assert.assertTrue(ump.checkLoginHistory(driver), "login history true");
		//Assert.assertTrue(ump.displayLayout(),"display and layout passed");
		Assert.assertTrue(ump.emailLink(driver), "email link uploaded");
		//Assert.assertTrue(ump.calenderReminder(driver),"calenderreminder");
		
		logger.info("test done+++++++++++");

}
	@Test
	public void myprofile_tc08() throws IOException, InterruptedException{
		ExtentTest test = extent.createTest("loginErrorMsgTC08");
		logger.info("test started+++++++++++");
		WebDriver driver = BaseTest.getDriver();
		Loginpage lp = new Loginpage(driver,test);
		Thread.sleep(2000);
		UserMenu ump = new UserMenu(driver, test);
		Assert.assertTrue((lp.LoginSFDC(driver)),"should launch the app");
		
		Thread.sleep(5000);
		Assert.assertTrue(ump.clickUserMenuOption(), "usr menu clicked");
		//Assert.assertTrue(ump.selectUserMenuOption("Developer Console", driver), "user menu option clicked");
		Thread.sleep(2000);
		Assert.assertTrue(ump.devConsole(driver), "devconsole open and close");
}
	@Test
	public void myprofile_tc09() throws IOException, InterruptedException{
		ExtentTest test = extent.createTest("loginErrorMsgTC09");
		logger.info("test started+++++++++++");
		WebDriver driver = BaseTest.getDriver();
		Loginpage lp = new Loginpage(driver,test);
		Thread.sleep(2000);
		UserMenu ump = new UserMenu(driver, test);
		Assert.assertTrue((lp.LoginSFDC(driver)),"should launch the app");
		
		Thread.sleep(5000);
		Assert.assertTrue(ump.clickUserMenuOption(), "usr menu clicked");
		Assert.assertTrue(ump.selectUserMenuOption("Logout", driver));
		Thread.sleep(2000);
}
}

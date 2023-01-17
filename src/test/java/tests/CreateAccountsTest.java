package tests;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentTest;

import pages.Loginpage;
import pages.UserMenu;
import pages.createAccountsPage;

public class CreateAccountsTest extends BaseTest{
	@Test
	public void myprofile_tc010() throws IOException, InterruptedException{
		ExtentTest test = extent.createTest("loginErrorMsgTC06");
		WebDriver driver = BaseTest.getDriver();
		Loginpage lp = new Loginpage(driver,test);
		UserMenu ump = new UserMenu(driver, test);
		createAccountsPage cap = new createAccountsPage(driver, test);
		Assert.assertTrue((lp.LoginSFDC(driver)),"should launch the app");
		Thread.sleep(5000);
		//Assert.assertTrue(cap.accountsLink(driver),"acountslink open");
		Assert.assertTrue(cap.createnewAccount(driver) ,"create new account");
	
}
	
	
	@Test
	public void myprofile_tc011() throws IOException, InterruptedException{
		ExtentTest test = extent.createTest("loginErrorMsgTC011");
		WebDriver driver = BaseTest.getDriver();
		Loginpage lp = new Loginpage(driver,test);
		UserMenu ump = new UserMenu(driver, test);
		createAccountsPage cap = new createAccountsPage(driver, test);
		Assert.assertTrue((lp.LoginSFDC(driver)),"should launch the app");
		Thread.sleep(5000);
		Assert.assertTrue(cap.createNewViewLink(driver),"new name n uniquename done");
}
	@Test
	public void myprofile_tc012() throws IOException, InterruptedException{
		ExtentTest test = extent.createTest("loginErrorMsgTC012");
		WebDriver driver = BaseTest.getDriver();
		Loginpage lp = new Loginpage(driver,test);
		UserMenu ump = new UserMenu(driver, test);
		createAccountsPage cap = new createAccountsPage(driver, test);
		Assert.assertTrue((lp.LoginSFDC(driver)),"should launch the app");
		Assert.assertTrue(cap.editviewname(driver) , "createeditview link");
		
}
}
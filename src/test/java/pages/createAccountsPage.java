package pages;

import java.io.IOException;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

import com.aventstack.extentreports.ExtentTest;

import utilities.Datautils;
import utilities.Utilities;

public class createAccountsPage extends BasePage {
	public WebDriver driver;
	
	public createAccountsPage(WebDriver driver, ExtentTest test) {
		PageFactory.initElements(driver, this);
		this.test = test;
		this.driver = driver;
	}
	
	@FindBy(xpath = "//*[@title='Accounts Tab']")
	public WebElement accountsLink;
	
	@FindBy(xpath="(//th[@class=' dataCell  '])[1]")
	public WebElement usernameverification;
	
	@FindBy(xpath="//*[@title='New']")
	public WebElement editnew;
	
	@FindBy(xpath = "//input[@id='acc2']")
	public WebElement accountnametab;
	
	@FindBy(xpath = "//*[@value=' Save ']")
	public WebElement savebtn;
	
	@FindBy(xpath="//h2[@class='topName']")
	public WebElement titlename;
	
	@FindBy(xpath="//a[text()='Create New View']")
	public WebElement newViewLink;
	
	@FindBy(id="fname")
	public WebElement viewname;
	
	@FindBy(id="devname")
	public WebElement uniquename;
	
	@FindBy(xpath="//td[@class='x-grid3-col x-grid3-cell x-grid3-td-Name ']//div/a/span")
	public List<WebElement> options;
	
	//tc12
	
	@FindBy(id="fcf")
	public WebElement selectclassid;
	
	@FindBy(xpath="//a[text()='Edit']")
	public WebElement editbtn;
	
	@FindBy(xpath="//select[@id='fcol1']")
	public WebElement selectclassid1;
	
	@FindBy(id="fop1")
	public WebElement selectclassid2;
	
	@FindBy(xpath="//input[@id='fval1']")
	public WebElement valuefield;
	
	@FindBy(id="colselector_select_0")
	public WebElement selectclassid3;
	
	@FindBy(xpath="//img[@class='rightArrowIcon']")
	public WebElement rightarrowbtn;
	
	@FindBy(xpath="//select[@class='colselector_select_1']")
	public List<WebElement> options1;
	
	public boolean accountsLink(WebDriver driver) throws IOException {
		boolean accountname = false;
		Utilities.waitForElement(driver, accountsLink);
		accountsLink.click();
		Utilities.waitForElement(driver, usernameverification);
		String verifyname = usernameverification.getText();
		System.out.println(verifyname);
		if(verifyname.equalsIgnoreCase(Datautils.readAccounts("accountsname"))) {
			System.out.println("account name match");
			accountname = true;
			}
		return accountname;
		}
		
	public boolean createnewAccount(WebDriver driver) throws IOException, InterruptedException {
		boolean newAccountcreated = false;
		accountsLink.click();
		Utilities.waitForElement(driver, editnew);
		editnew.click();
		Thread.sleep(2000);
		Utilities.waitForElement(driver, accountnametab);
		accountnametab.sendKeys(Datautils.readAccounts("newaccountname"));
		Thread.sleep(5000);
		Utilities.waitForElement(driver, savebtn);
		savebtn.click();	
		Thread.sleep(5000);
		String title = titlename.getText();
		if(title.equalsIgnoreCase(Datautils.readAccounts("newaccountname"))) {
			System.out.println("the "+ title +" matches the "+ Datautils.readAccounts("newaccountname"));
		newAccountcreated = true;
		
	}
		return newAccountcreated;

}
	public boolean createNewViewLink(WebDriver driver) throws IOException, InterruptedException {
		boolean createnewviewlink = false;
		accountsLink.click();
		logger.info("Accounts link clicked");
		Utilities.waitForElement(driver, newViewLink);
		newViewLink.click();
		logger.info("newview link clicked");
		Utilities.waitForElement(driver, viewname);
		viewname.sendKeys(Datautils.readAccounts("viewname"));
		logger.info("view name entered");
		Utilities.waitForElement(driver, uniquename);
		uniquename.sendKeys(Datautils.readAccounts("uniquename"));
		logger.info("uniqueview name entered");
		Thread.sleep(5000);
		savebtn.click();
		logger.info("save btn clicked");
		Thread.sleep(5000);
		int size = options.size();
		for(int i = 0;i<size;i++) {
			
			String opt = options.get(i).getText();
			System.out.println(opt);
			if(opt.equalsIgnoreCase(Datautils.readAccounts("checkname"))) {
				System.out.println("present");
				createnewviewlink = true;
			}
			else {
				System.out.println("not present");
			}
		}
		
		
		return createnewviewlink;
		
	}
	
	public boolean editviewname(WebDriver driver) throws InterruptedException {
		boolean editviewname= false;
		accountsLink.click();
		Thread.sleep(2000);
		Select s = new Select(selectclassid);
			s.selectByVisibleText("kitty");
		Utilities.waitForElement(driver, editbtn);
		editbtn.click();
		Utilities.waitForElement(driver, viewname);
		viewname.clear();
		Thread.sleep(2000);
		viewname.sendKeys("newviewname");
		Thread.sleep(2000);
		Select s1 = new Select(selectclassid1);
			s1.selectByVisibleText("Account Name");
			Thread.sleep(2000);	
		Select s2 = new Select(selectclassid2);
			s2.selectByVisibleText("contains");
			Thread.sleep(2000);
			valuefield.sendKeys("a");
		Select s3 = new Select(selectclassid3);
		Thread.sleep(2000);
			s3.selectByValue("ACCOUNT.LAST_ACTIVITY");
			Thread.sleep(2000);
		rightarrowbtn.click();
		Thread.sleep(2000);
		
		for(int i = 0;i<options1.size();i++) {
			
			String text = options1.get(i).getText();
			System.out.println(text);
			if(text.contains("Last Activity")) {
				System.out.println("present");
				editviewname=true;
			}
			else {
				System.out.println("not available");
			}
			
		}
		
		savebtn.click();
		
		
		return editviewname;
		
	}
}

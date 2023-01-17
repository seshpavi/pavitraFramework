package pages;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import org.openqa.selenium.support.ui.Select;

import com.aventstack.extentreports.ExtentTest;

import constants.FileNameConstants;
import utilities.Datautils;
import utilities.Utilities;

public class UserMenu extends BasePage{
	
	private WebDriver driver;

	public UserMenu(WebDriver driver, ExtentTest test) {
	
			PageFactory.initElements(driver, this);
			this.test=test;
			this.driver =driver;
	}

	@FindBy(xpath="//span[@id='userNavLabel']")
	public WebElement userMenu;
	
	@FindBy(xpath = "//div[@id='userNav-menuItems']/a")
	public List<WebElement> usermenuoptions;
	
	@FindBy(xpath="(//*[@title='Edit Profile'])[2]")
	public WebElement EditProfile;
	
	@FindBy(xpath = "//*[@title = 'Logout']")
	public WebElement logout;
	
	@FindBy(xpath="//div/h2[@id='contactInfoTitle']")
	public WebElement EditProfilePopupWindow;
	
	@FindBy(xpath = "//*[@class='zen-tabControl']//child::li[1]")
	public WebElement AboutTab;
	
	@FindBy(id = "lastName")
	public WebElement AboutTabLastName;
	
	@FindBy(xpath = "//*[@value='Save All']")
	public WebElement saveAll;
	
	@FindBy(xpath="//span[@class ='publisherattachtext ']")
	public WebElement postLink;
	
	@FindBy(xpath = "/html/body")
	public WebElement postArea;
	
	@FindBy(id="publishersharebutton")
	public WebElement sharebutton;
	
	@FindBy(xpath = "//a[@id='publisherAttachContentPost']")
	public WebElement filelink;
	
	@FindBy(css= "#chatterUploadFileAction")
	public WebElement uploadFile;
	
	@FindBy(css = "#chatterFile")
	public WebElement fileopen;
	
	@FindBy(xpath="//a[@class='header setupFolder']")
	public WebElement personallink;
	
	@FindBy (xpath="//span[@id='LoginHistory_font']")
	public WebElement loginhistory;
	
	@FindBy(xpath="//div[@class='pShowMore']")
	public WebElement downloadlink;
	
	@FindBy(xpath="//span[@id='DisplayAndLayout_font']")
	public WebElement displayandlayout;
	
	@FindBy (xpath= "//span[@id='CustomizeTabs_font']")
	public WebElement customizetabs;
	
	@FindBy(xpath= "//*[@id='p4']")
	public WebElement customizeapp;
	
	@FindBy(xpath = "//*[@id='duel_select_0']/option[73]")
	public WebElement Availableapp;
	
	@FindBy (xpath = "//img[@class='rightArrowIcon']")
	public WebElement rightarrow;
	
	@FindBy(xpath ="//input[@value=' Save ']")
	public WebElement save;
	
	@FindBy(xpath ="//a[@class='setupSection']/following::div[20]")
	public WebElement emailLink;
	
	@FindBy(xpath = "//*[contains(text(), 'My Email Settings')]")
	public WebElement emailSettings;
	
	@FindBy(xpath="//input[@maxlength=80 and @id= 'sender_name' ]")
	public WebElement emailName;
	
	@FindBy(xpath ="(//input[@name='auto_bcc'])[2]")
	public WebElement nobtn;
	
	
	@FindBy(xpath = "//a[@class='setupSection']/following::div[29]")
	public WebElement calender1;
	
	@FindBy(xpath="//a[@class='setupSection']/following::div[29]/child::div/child::div[2]/child::a/child::span")
	public WebElement calender2;
	
	@FindBy(xpath="//input[@value='Open a Test Reminder']")
	public WebElement reminder;
	
	@FindBy(xpath="//input[@id='ids0']")
	public WebElement reminder2;
	
	@FindBy(xpath="//input[@value='Dismiss All']")
	public WebElement dismiss;
	
	//devconsole
	
	@FindBy(xpath = "//*[@title='Developer Console (New Window)']")
	public WebElement devConsole;
	
	
	
	
	public boolean clickUserMenuOption() {
	
		userMenu.click();		
		return true;
		
	}
	public boolean verifyUserMenuOption() throws IOException {
		boolean isOptionVerified = true;
		logger.info("verifyusermenuoption() : verify usr menu items ");
		String[] definedusermenuoptions = Datautils.readErrorMsgs("usermenu.items").split(",");
		userMenu.click();
		for(int i = 0 ; i<usermenuoptions.size();i++) {
			String actualText = usermenuoptions.get(i).getText();
			System.out.println(actualText);
			if(actualText.equals(definedusermenuoptions[i])) {
				System.out.println("option "+ actualText+" verified");
			}
			else {
				System.out.println("option "+ actualText+" not available");
				isOptionVerified = false;
			}
		}
		return isOptionVerified;
	}


	public boolean selectUserMenuOption(String option, WebDriver driver) {
		
		WebElement optionselected = driver.findElement(By.xpath("//*[@title='"+option+"']"));
		
		if(optionselected.isDisplayed()) {
			optionselected.click();
			return true;
		}
		
		return false;
	}
	
	public boolean openEditProfileModel(WebDriver driver) throws IOException {
		boolean isEditProfileSeen = false;
		if(EditProfile.isDisplayed()) {
			EditProfile.click();
			test.info("edit profile button clicked");
			if(EditProfilePopupWindow.isDisplayed()) {
				isEditProfileSeen = true;
			}
			else {
				test.fail("profile edit button not seen");
				test.addScreenCaptureFromPath(Utilities.captureScreenShot(driver), "edit profile window not seen");
			}
		}
		
		return isEditProfileSeen;
		
	}
	
	public boolean editlastNameinAboutTab(WebDriver driver, String lastName) throws IOException {
	
		driver.switchTo().frame(driver.findElement(By.id("contactInfoContentId")));
		boolean isLastNamechanged = false;
		if(AboutTab.isDisplayed()) {
			AboutTab.click();
			test.info("abou tab is seen");
			if(AboutTabLastName.isDisplayed()) {
				AboutTabLastName.clear();
				AboutTabLastName.sendKeys(lastName);
				saveAll.click();
				test.info("last name changed");
				isLastNamechanged = true;
			}
			else {
				test.fail("about tab name not seen");
				test.addScreenCaptureFromPath(Utilities.captureScreenShot(driver), "about tab name not seen");
			}
		}
			else {
				test.fail("about tab not seen");
				test.addScreenCaptureFromPath(Utilities.captureScreenShot(driver), "about tab not seen");
			}
			
			driver.switchTo().defaultContent();
		
		return isLastNamechanged;
	}
	
	public boolean createPost(WebDriver driver, String message) throws IOException {
		
		boolean ispostCreated = false;
		
		if(postLink.isDisplayed()) {
			postLink.click();
			test.info("post created");
			driver.switchTo().frame(0);
			postArea.sendKeys(message);
			driver.switchTo().defaultContent();
			if(sharebutton.isDisplayed()) {
				sharebutton.click();
				test.info("post created and shareed");
				ispostCreated = true;
			}
			else {
				test.fail("share not done");
				test.addScreenCaptureFromPath(Utilities.captureScreenShot(driver), "share not done after post");
			}}
			else {
				test.fail("about tab name not seen");
				test.addScreenCaptureFromPath(Utilities.captureScreenShot(driver), "post text not done");
			}
		return ispostCreated;
	}
	
	public boolean uploadFile(WebDriver driver , String filepath) throws InterruptedException {
			boolean fileupload = false;
			if(Utilities.waitForElement(driver, filelink)) {
				Utilities.jsClick(driver, filelink);
			}
					
			if(Utilities.waitForElement(driver, uploadFile)) {
				uploadFile.click();
				if(Utilities.waitForElement(driver, fileopen)) {
					fileopen.click();
					fileopen.sendKeys(filepath);
					
					if(Utilities.waitForElement(driver, sharebutton)) {
						sharebutton.click();
						fileupload = true;
					}
				}
			}
					
		return fileupload;		
		
	}

	
	public boolean checkLoginHistory(WebDriver driver) throws InterruptedException, IOException {
	boolean isfileformattrue = false;
		if(Utilities.waitForElement(driver, personallink)){
			personallink.click();
			Thread.sleep(2000);
			if(loginhistory.isDisplayed()) {
				loginhistory.click();
				Thread.sleep(2000);
				String text = downloadlink.getText();
				System.out.println(text);
				if(text.contains(Datautils.readAccounts("filedownloadformat"))){
					System.out.println("present");
					isfileformattrue = true;
					
				}
			}
		}return isfileformattrue;
		
		
	}
	
	public boolean displayLayout() throws InterruptedException {
		boolean isdisplaylayout=false;
		if(displayandlayout.isDisplayed()) {
			displayandlayout.click();
			Thread.sleep(2000);
			if(customizetabs.isDisplayed()) {
				customizetabs.click();
				Thread.sleep(2000);
				Select option = new Select(customizeapp);
				customizeapp.click();
				Thread.sleep(2000);
				option.selectByValue("02uDn000001RmIN");
				
				Availableapp.click();
				Thread.sleep(2000);
				rightarrow.click();
				Thread.sleep(2000);
				save.click();
				Thread.sleep(2000);
				isdisplaylayout =true;
			}
		}
		
		return isdisplaylayout;
		
	}
	
	public boolean emailLink(WebDriver driver) {
		boolean isEmailupload = false;
		if(Utilities.waitForElement(driver, emailLink)) {
			emailLink.click();
		if(Utilities.waitForElement(driver, emailSettings)) {
			emailSettings.click();
			if(Utilities.waitForElement(driver, emailName)) {
				emailName.sendKeys("ring");
				nobtn.click();
				save.click();
				isEmailupload = true;
			}
		}
		}
		return isEmailupload;
		
	}
	
	public boolean calenderReminder(WebDriver driver) throws InterruptedException {
		
		boolean calenderReminder = false;
		
		if(Utilities.waitForElement(driver, calender1)) {
			calender1.click();
			Thread.sleep(5000);
			if(Utilities.waitForElement(driver, calender2)) {
				calender2.click();
				reminder.click();
			
			Thread.sleep(5000);
			String parenthandle = driver.getWindowHandle();
			System.out.println(parenthandle);
			Thread.sleep(5000);
			
		ArrayList<String> handles = new ArrayList<String>(driver.getWindowHandles());
		for(String handle:handles) {
			System.out.println(handles);
		}
		driver.switchTo().window(handles.get(0));
		
		Utilities.waitForElement(driver, dismiss);
			}
	//	reminder2.click();
		dismiss.click();
		
		
		
	}
		return false;
		}
	
	
	
	public boolean devConsole(WebDriver driver) {
		boolean isdevwindowopen = false;
		if(Utilities.waitForElement(driver, devConsole)) {
			logger.info("devConsole() : dev console clicked ");
			devConsole.click();
			ArrayList<String> handles = new ArrayList<String>(driver.getWindowHandles());
			for(String handle:handles) {
				System.out.println(handles);
			}
			driver.switchTo().window(handles.get(1));
			driver.close();
			isdevwindowopen=true;
			logger.info("dev console passed");
		}
		
		return isdevwindowopen;
	}
	
}


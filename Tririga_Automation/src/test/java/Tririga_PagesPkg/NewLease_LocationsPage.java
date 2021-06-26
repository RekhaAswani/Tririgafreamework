package Tririga_PagesPkg;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.relevantcodes.extentreports.ExtentTest;
import com.webElementPkg.MouseOperations;
import com.webElementPkg.WebBrowser;
import com.webElementPkg.WebButton;
import com.webElementPkg.WebCheckBox;
import com.webElementPkg.WebFrame;
import com.webElementPkg.WebLink;
import com.webElementPkg.WebPage;
import com.webElementPkg.WebRadiobutton;
import com.webElementPkg.WebTextbox;
import com.webElementPkg.WebUtilities;

import java.util.List;

public class NewLease_LocationsPage {
	
	private WebDriver driver=null;
	private ExtentTest logger=null;
	public WebElement leaseType;
	
	public static String LocationMainFrame="//iframe[@id='contentFrame']";	
	public static String LocationsMainFrame="//iframe[@id='childFrame']";
	public static String LocationsChld1Frame="//frame[@id='frameMain']";
	public static String LocationsChld2Frame="//iframe[@id='contentFrame']";
	
	@FindBy(xpath="//span[text()='Locations']")
	public WebElement Locationstab;
	
	@FindBy(xpath="//span[@class='actionButtonText' and contains(text(),'Find')]")
	public WebElement Location_Findbtn;	
	
	@FindBy(xpath="//input[@type='checkbox' and @name='sNo']")
	public WebElement LocationsTableRowChkbox;
	
	@FindBy(xpath="//table[@id='actionsTable']//span[@class='actionButtonText' and text()='OK']")
	public WebElement LocationsOKbtn;
	
	@FindBy(xpath="//span[@class='actionButtonText' and contains(text(),'Add')]")
	public WebElement LocationAddbtn;
	
	@FindBy(xpath="//div[@id='Save' and @class='brandable-button-center']")
	public WebElement Location_Savebtn;
		
	
	public NewLease_LocationsPage(WebDriver driver,ExtentTest logger){
		
		this.driver=driver;
		this.logger=logger;
		PageFactory.initElements(driver, this);

	}
	
	public boolean SelectLocationDetails(){		
				
		boolean LocationPgStatus=false;		
		try{	
			WebFrame.switchToMainPageFromIFrame(driver);
			WebButton.activeElementPressKeyBoardKeys(driver, "TAB", Locationstab);
			WebLink.clickOnWebLink(driver, Locationstab, logger);
			WebFrame.switchOnMultipleFramesByXpath(driver, LocationMainFrame);
			WebButton.webButtonClick(driver, logger,Location_Findbtn);
			WebFrame.switchOnMultipleFramesByXpath(driver, LocationsMainFrame,LocationsChld1Frame,LocationsChld2Frame);
			WebUtilities.waitForElementToAppear(driver, LocationsTableRowChkbox, logger);
			WebCheckBox.webCheckBoxClick(driver, logger, LocationsTableRowChkbox);
			WebButton.webButtonClick(driver, logger, LocationsOKbtn);
			
			WebFrame.switchToMainPageFromIFrame(driver);
			WebFrame.switchOnMultipleFramesByXpath(driver, LocationMainFrame);
			WebButton.webButtonClick(driver, logger, LocationAddbtn);
			WebFrame.switchOnMultipleFramesByXpath(driver, LocationsMainFrame,LocationsChld1Frame,LocationsChld2Frame);
			WebUtilities.waitForElementToAppear(driver, LocationsTableRowChkbox, logger);
			WebCheckBox.webCheckBoxClick(driver, logger, LocationsTableRowChkbox);
			WebButton.webButtonClick(driver, logger, LocationsOKbtn);
			
			WebButton.webButtonClick(driver, logger, Location_Savebtn);	
			WebPage.waitForPageLoad(driver);
			WebUtilities.waitForElementToAppear(driver, Location_Savebtn, logger);
			Thread.sleep(5000);
			LocationPgStatus=true;
		}catch(Exception e){
			LocationPgStatus=false;
		}

		return LocationPgStatus;
		
	}
		

}

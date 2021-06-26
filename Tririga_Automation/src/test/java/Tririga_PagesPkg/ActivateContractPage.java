package Tririga_PagesPkg;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.relevantcodes.extentreports.ExtentTest;
import com.webElementPkg.WebButton;
import com.webElementPkg.WebCheckBox;
import com.webElementPkg.WebFrame;
import com.webElementPkg.WebLink;

public class ActivateContractPage {
	
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
		
	
	public ActivateContractPage(WebDriver driver,ExtentTest logger){
		
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
			WebCheckBox.webCheckBoxClick(driver, logger, LocationsTableRowChkbox);
			WebButton.webButtonClick(driver, logger, LocationsOKbtn);
			
			WebFrame.switchToMainPageFromIFrame(driver);
			WebFrame.switchOnMultipleFramesByXpath(driver, LocationMainFrame);
			WebButton.webButtonClick(driver, logger, LocationAddbtn);
			WebFrame.switchOnMultipleFramesByXpath(driver, LocationsMainFrame,LocationsChld1Frame,LocationsChld2Frame);
			WebCheckBox.webCheckBoxClick(driver, logger, LocationsTableRowChkbox);
			WebButton.webButtonClick(driver, logger, LocationsOKbtn);
			
			WebButton.webButtonClick(driver, logger, Location_Savebtn);	
			
			LocationPgStatus=true;
		}catch(Exception e){
			LocationPgStatus=false;
		}

		return LocationPgStatus;
		
	}
		

}

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
import com.webElementPkg.WebFrame;
import com.webElementPkg.WebLink;
import com.webElementPkg.WebPage;
import com.webElementPkg.WebRadiobutton;
import com.webElementPkg.WebTextbox;
import com.webElementPkg.WebUtilities;

import java.util.List;

public class NewLease_ContactDetailsPage {
	
	private WebDriver driver=null;
	private ExtentTest logger=null;
	public WebElement leaseType;
	
	public static String ContactsMainFrame="//iframe[@id='contentFrame']";
	public static String ContactsTableFrame="//iframe[@id='innerFrame87']";
	public static String ContactsRoleMainFrame="//iframe[@id='childFrame']";
	public static String ContactsRoleChld1Frame="//frame[@id='frameMain']";
	public static String ContactsRoleChld2Frame="//iframe[@id='contentFrame']";
	
	@FindBy(xpath="//span[text()='Contact Details']")
	public WebElement ContactDetailstab;
	
	@FindBy(xpath="//span[text()='General']")
	public WebElement Generaltab;
	
	@FindBy(xpath="//a[text()='Data Central Hub - Validate']")
	public WebElement DataCentralHublnk;
	
	@FindBy(xpath="//div[@id='Find_3']")
	public WebElement Contact_Findbtn;
	
	@FindBy(xpath="//input[@type='radio' and @name='sNo']")
	public WebElement Contact_Role_NameRadioBtn;
	
	@FindBy(xpath="//table[@id='actionsTable']//span[@class='actionButtonText' and text()='OK']")
	public WebElement Contact_Role_OKBtn;
	
	@FindBy(xpath="//div[@id='xBtn']")
	public WebElement Contact_Role_CloseBtn;
	
	@FindBy(xpath="//a[text()='Portfolio Administrator']")
	public WebElement PortfolioAdministrator;
	
	@FindBy(xpath="//a[text()='Portfolio Administrator - Validate']")
	public WebElement PortfolioAdministratorValidate;
	
	@FindBy(xpath="//a[text()='Property Manager']")
	public WebElement PropertyManager;
	
	@FindBy(xpath="//img[@src='/getCompanyFile.jsp?fileLoc=//images/icons/list-drop-down.png']")
	public WebElement RoleFilterDropDownbox;
	
	@FindBy(xpath="//input[@name='filterValue4']")
	public WebElement Contact_Role_NameFilter;
	
	@FindBy(xpath="//input[@id='attr_seq_1916']")
	public WebElement TenantOrgLookup;
	
	@FindBy(xpath="//input[@id='attr_seq_1934']")
	public WebElement LandLordOrgLookup;
	
	@FindBy(xpath="//input[@name='attr_seq_1987']")
	public WebElement RemitLookup;
	
	@FindBy(xpath="//span[text()='Create Draft']")
	public WebElement CreateDraftbtn;
	
	
	public NewLease_ContactDetailsPage(WebDriver driver,ExtentTest logger){
		
		this.driver=driver;
		this.logger=logger;
		PageFactory.initElements(driver, this);

	}
	
	public void SelectUserForRoles(WebElement element,String PersonName){
		
		WebFrame.switchToMainPageFromIFrame(driver);
		WebFrame.switchOnMultipleFramesByXpath(driver, ContactsMainFrame,ContactsTableFrame);
		WebLink.clickOnWebLink(driver, element, logger);
		
		WebBrowser.switchToChildWindow2(driver);
		
		WebFrame.switchToMainPageFromIFrame(driver);
		WebFrame.switchOnMultipleFramesByXpath(driver, ContactsRoleChld2Frame);
		
		WebLink.clickOnWebLink(driver, Contact_Findbtn, logger);
		
		WebFrame.switchOnMultipleFramesByXpath(driver, ContactsRoleMainFrame,ContactsRoleChld1Frame,ContactsRoleChld2Frame);
		
		WebLink.clickOnWebLink(driver, RoleFilterDropDownbox, logger);
		
		List<WebElement> DropDownValues = driver.findElements(By.xpath("//table[@id='subRepLayerTable']//span[@class='bodyText']"));
		System.out.println("DropDown values="+DropDownValues.size());  

		WebElement SecondValue =driver.findElements(By.xpath("//table[@id='subRepLayerTable']//span[@class='bodyText']")).get(1);
		MouseOperations.ActionMoveToElement(driver, logger, SecondValue);
		
		WebLink.clickOnWebLink(driver, SecondValue, logger);
		
		WebTextbox.sendTextToWebInput(Contact_Role_NameFilter, PersonName, logger);
		MouseOperations.PerformKeywordActions(driver, logger, "ENTER");
		WebRadiobutton.selectRadioButton(driver, logger, Contact_Role_NameRadioBtn);
		WebButton.webButtonClick(driver, logger, Contact_Role_OKBtn);
		WebButton.webButtonClick(driver, logger, Contact_Role_CloseBtn);			
		
	}
	
	public boolean SelectUsersWithDifferentRoles(String ResourceName){
		
				
		boolean ContactDetailsPgStatus=false;		
		try{
			
			WebFrame.switchToMainPageFromIFrame(driver);
			WebButton.activeElementPressKeyBoardKeys(driver, "TAB", ContactDetailstab);
			WebLink.clickOnWebLink(driver, ContactDetailstab, logger);
			String contactsWindow = driver.getWindowHandle();
			System.out.println("Window Name="+contactsWindow.toString());
			SelectUserForRoles(DataCentralHublnk,ResourceName);
			driver.switchTo().window(contactsWindow);
			SelectUserForRoles(PortfolioAdministrator,ResourceName);
			driver.switchTo().window(contactsWindow);
			SelectUserForRoles(PortfolioAdministratorValidate,ResourceName);
			driver.switchTo().window(contactsWindow);
			SelectUserForRoles(PropertyManager,ResourceName);		
			driver.switchTo().window(contactsWindow);
			
			ContactDetailsPgStatus=true;
		}catch(Exception e){
			ContactDetailsPgStatus=false;
		}

		return ContactDetailsPgStatus;
		
	}
	
	public boolean SelectLookupData(String TenantOrg,String LandlordOrg,String RemitTo){
		
		
		boolean ContactDetailsPgStatus=false;		
		try{
			
			WebFrame.switchToMainPageFromIFrame(driver);
			WebFrame.switchOnMultipleFramesByXpath(driver, ContactsMainFrame);
			WebTextbox.sendTextToWebInput(TenantOrgLookup, TenantOrg, logger);
			MouseOperations.PerformKeywordActions(driver, logger, "TAB");
			
			WebPage.scrollingToBottomOfAPage(driver);
			WebLink.clickOnWebLink(driver, LandLordOrgLookup, logger);
			WebLink.clickOnWebLink(driver, LandLordOrgLookup, logger);
			WebTextbox.sendTextToWebInput(LandLordOrgLookup, LandlordOrg, logger);
			Thread.sleep(5000);
			MouseOperations.PerformKeywordActions(driver, logger, "TAB");
			
			WebPage.scrollingToBottomOfAPage(driver);
			WebLink.clickOnWebLink(driver, RemitLookup, logger);
			WebLink.clickOnWebLink(driver, RemitLookup, logger);
			WebTextbox.sendTextToWebInput(RemitLookup, RemitTo, logger);
			Thread.sleep(5000);
			MouseOperations.PerformKeywordActions(driver, logger, "TAB");
			
			
			ContactDetailsPgStatus=true;
		}catch(Exception e){
			ContactDetailsPgStatus=false;
		}

		return ContactDetailsPgStatus;
		
	}
	
	public boolean ClickOnCreateDraft(){
		
		boolean ContactDetailsPgStatus;
		
		try{			
			WebFrame.switchToMainPageFromIFrame(driver);
			WebButton.webButtonClick(driver, logger, CreateDraftbtn);
			Thread.sleep(6000);
			ContactDetailsPgStatus=true;
		}catch(Exception e){
			ContactDetailsPgStatus=false;
		}
		
		return ContactDetailsPgStatus;
	}
	

}

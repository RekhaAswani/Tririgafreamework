package Tririga_PagesPkg;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;
import com.webElementPkg.WebButton;
import com.webElementPkg.WebFrame;
import com.webElementPkg.WebLink;
import com.webElementPkg.WebUtilities;


public class HomePage {
	
	private WebDriver driver=null;
	private ExtentTest logger=null;
	
	public static final String AllLeaseContracts_MainFrame="//iframe[@class='gwt-Frame GDNOS4QBO-']";
	public static final String AllLeaseContracts_TableFrame="//iframe[@id='objectsFrame']";
		
	@FindBy(xpath="//a[text()='Home']")
	public static WebElement HomeTab;
	
	@FindBy(xpath="//a[text()='Portfolio']")
	public static WebElement PortfolioTab;
	
	@FindBy(xpath="//a[text()='Locations']")
	public static WebElement LocationsTab;
	
	@FindBy(xpath="//*[@id='large-quick-link-4312']")
	public static WebElement AllLeaseContracts;
	
	@FindBy(xpath="//span[text()='Add']")
	public static WebElement LeaseContract_Add;
	
	@FindBy(xpath="//div[@aria-label='Shell Specific Reports']")
	public static WebElement ShellSpecificReportsbtn;
	
	@FindBy(xpath="//div[@aria-label='Available Options']")
	public static WebElement AvailableOptionsbtn;
	
	@FindBy(xpath="//div[@aria-label='Index Adjustments']")
	public static WebElement IndexAdjustmentsbtn;
	
	@FindBy(xpath="//div[@aria-label='All Data Reports']")
	public static WebElement AllDataReportsbtn;
	
	@FindBy(xpath="//a[text()='Critical Dates Report']")
	public static WebElement CriticalDatesReportlnk;
	
	@FindBy(xpath="//a[text()='Index Report']")
	public static WebElement IndexReportlnk;
	
	@FindBy(xpath="//a[text()='Manage Index Items']")
	public static WebElement ManageIndexItemslnk;
	
	@FindBy(xpath="//a[text()='Contract Portfolio Overview']")
	public static WebElement ContractPortfolioOverviewlnk;
	
	@FindBy(xpath="//a[text()='Approval Reviews']")
	public static WebElement ApprovalReviewlnk;
	
	@FindBy(xpath="//a[text()='Process Lease Approvals']")
	public static WebElement ProcessLeaseApprovallnk;
	
	@FindBy(xpath="//a[text()='Modification History']")
	public static WebElement ModificationHistorylnk;
	
	@FindBy(xpath="//a[@title='Options Available in the Next 30 Days']")
	public static WebElement OptionsAvailableIn30dayslnk;
	
	@FindBy(xpath="//a[@title='Expired Overdue Options']")
	public static WebElement ExpiredOverDueOptionslnk;
	
	@FindBy(xpath = "//*[@title='Process Payments']")
	public static WebElement process_Payments;
	
	@FindBy(xpath = "//table[@id='mainTable']//tr[contains(@id,'query')]")
	List<WebElement> allElements;
	
	@FindBy(xpath = "//*[contains(@id,'queryResultRow')]")
	public List<WebElement> search_Results;
	
	@FindBy(xpath="//textarea[@id='attr_seq_1050']")
	public static WebElement ReviewcCommentstxt;
	
	@FindBy(xpath="//a[@id='attr_seq_1051_selector']")
	public static WebElement ApprovalRoleSelector;
	
	@FindBy(xpath="//div[@id='Approve']")
	public static WebElement Approvebtn;
	
	@FindBy(xpath="//div[@id='Get Approvals_23']")
	public static WebElement GetApprovalbtn;
	 
	//check this 
	@FindBy(xpath = "//*[contains(@id,'queryResultRow')][1]//td//following-sibling::input[contains(@type,'checkbox')]")
	public WebElement first_Result_Checkbox_Ele;
	
	@FindBy(xpath = "//a[@id='auto-sign-out-button']")
	public static WebElement sing_Out;

	@FindBy(xpath = "//a[text()='Process Lease Approvals']")
	public static WebElement process_Lease_Approvals;
	
	
	public HomePage(WebDriver driver,ExtentTest logger){
		this.driver=driver;
		this.logger=logger;
		PageFactory.initElements(driver, this);
	}
	
	public void clickOnSingOut() {

		WebElement element = sing_Out;
		try {
			WebFrame.switchToMainPageFromIFrame(driver);
			WebButton.webButtonClick(driver, logger, element);
			logger.log(LogStatus.PASS, "'Singout' clicked successfully");
		} catch (Exception e) {
			logger.log(LogStatus.FAIL, "Failed to click on Singout Button - " + e.getStackTrace());
			e.printStackTrace();
		}
	}

	public void clickOnProcessLeaseApprovals() {

		WebElement element = process_Lease_Approvals;
		try {
			WebFrame.switchToMainPageFromIFrame(driver);
			WebButton.webButtonClick(driver, logger, element);
			logger.log(LogStatus.PASS, "'ProcessLeaseApprovals' clicked successfully");
		} catch (Exception e) {
			logger.log(LogStatus.FAIL, "Failed to click on ProcessLeaseApprovals link - " + e.getStackTrace());
			e.printStackTrace();
		}
	}
	
	public void clickOnAllLeaseContractslnk() {
		
		WebFrame.switchToMainPageFromIFrame(driver);
		WebElement element=AllLeaseContracts;
		
		try {
			if (element != null) {
				//MouseOperations.hoverMouseOnWebElement(driver, logger, element);
				WebButton.webButtonClick(driver, logger, element);
				logger.log(LogStatus.PASS, "'All Lease Contracts' clicked successfully");
			}
		} catch (Exception e) {
			logger.log(LogStatus.FAIL, "Failed to find the 'All Lease Contracts'" + e.getStackTrace().toString());
			e.printStackTrace();
		}
	} 
	
	public void ClickOnAddContract(){
		WebElement element=LeaseContract_Add;
		try {
			if (element != null) {
				WebFrame.switchOnMultipleFramesByXpath(driver, AllLeaseContracts_MainFrame,AllLeaseContracts_TableFrame);
				WebUtilities.waitForElementToAppear(driver, element, logger);
				WebButton.webButtonClick(driver, logger, element);
				logger.log(LogStatus.PASS, "'Add Contracts' clicked successfully");
			}
		} catch (Exception e) {
			logger.log(LogStatus.FAIL, "Failed to find the 'Add Contracts'" + e.getStackTrace().toString());
			e.printStackTrace();
		}	
		
	}
	public void NavigateToPortfolio_LocationsPg(){
		
		WebUtilities.waitForElementToAppear(driver, AllLeaseContracts, logger);
		WebLink.clickOnWebLink(driver, PortfolioTab, logger);
		WebLink.clickOnWebLink(driver, LocationsTab, logger);
	
	}
	
	public void NavigateToCriticalDatesReport(){
		
		WebUtilities.waitForElementToAppear(driver, AllLeaseContracts, logger);
		
		String Expand=ShellSpecificReportsbtn.getAttribute("aria-expanded");
		if(Expand.equalsIgnoreCase("false")){
			WebButton.webButtonClick(driver, logger, ShellSpecificReportsbtn);
		}		
		WebLink.clickOnWebLink(driver, CriticalDatesReportlnk, logger);
	
	}
	
	public void NavigateToIndexReportsPg(){
		
		WebUtilities.waitForElementToAppear(driver, AllLeaseContracts, logger);
		
		String Expand=ShellSpecificReportsbtn.getAttribute("aria-expanded");
		if(Expand.equalsIgnoreCase("false")){
			WebButton.webButtonClick(driver, logger, ShellSpecificReportsbtn);
		}		
		WebLink.clickOnWebLink(driver, IndexReportlnk, logger);
	
	}
	
	public void NavigateToContractPortfolioOverview(){
		
		WebUtilities.waitForElementToAppear(driver, AllLeaseContracts, logger);
		
		String Expand=ShellSpecificReportsbtn.getAttribute("aria-expanded");
		if(Expand.equalsIgnoreCase("false")){
			WebButton.webButtonClick(driver, logger, ShellSpecificReportsbtn);
		}		
		WebLink.clickOnWebLink(driver, ContractPortfolioOverviewlnk, logger);
	
	}
	
	public void NavigateToApprovalReviewsPg(){
		
		WebUtilities.waitForElementToAppear(driver, AllLeaseContracts, logger);
		WebLink.clickOnWebLink(driver, ApprovalReviewlnk, logger);
	
	}
	
	public void NavigateToModificationHistoryPg(){
		
		WebUtilities.waitForElementToAppear(driver, AllLeaseContracts, logger);
		
		String Expand=AllDataReportsbtn.getAttribute("aria-expanded");
		if(Expand.equalsIgnoreCase("false")){
			WebButton.webButtonClick(driver, logger, AllDataReportsbtn);
		}		
		WebLink.clickOnWebLink(driver, ModificationHistorylnk, logger);
	
	}
	
	public void NavigateToOptionsAvailableIn30daysPg(){
		
		WebUtilities.waitForElementToAppear(driver, AllLeaseContracts, logger);
		
		String Expand=AvailableOptionsbtn.getAttribute("aria-expanded");
		if(Expand.equalsIgnoreCase("false")){
			WebButton.webButtonClick(driver, logger, AvailableOptionsbtn);
		}		
		WebLink.clickOnWebLink(driver, OptionsAvailableIn30dayslnk, logger);
	
	}
	
	public void NavigateToExpiredOverDueOptionsPg(){
		
		WebUtilities.waitForElementToAppear(driver, AllLeaseContracts, logger);
		
		String Expand=AvailableOptionsbtn.getAttribute("aria-expanded");
		if(Expand.equalsIgnoreCase("false")){
			WebButton.webButtonClick(driver, logger, AvailableOptionsbtn);
		}		
		WebLink.clickOnWebLink(driver, ExpiredOverDueOptionslnk, logger);
	
	}
	
	public void NavigateToManageIndexItems(){
		
		WebUtilities.waitForElementToAppear(driver, AllLeaseContracts, logger);
		
		String Expand=IndexAdjustmentsbtn.getAttribute("aria-expanded");
		if(Expand.equalsIgnoreCase("false")){
			WebButton.webButtonClick(driver, logger, IndexAdjustmentsbtn);
		}		
		WebLink.clickOnWebLink(driver,ManageIndexItemslnk, logger);
	
	}
	
	public void NavigateToHomePg(){
		
		WebFrame.switchToMainPageFromIFrame(driver);
		WebLink.clickOnWebLink(driver, HomeTab, logger);
		WebUtilities.waitForElementToAppear(driver, AllLeaseContracts, logger);	
		
	
	}
	
	public void ClickOnProcessLeaseApprovals(){
		
		WebFrame.switchToMainPageFromIFrame(driver);
		WebLink.clickOnWebLink(driver, ProcessLeaseApprovallnk, logger);
		try {
			Thread.sleep(4000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void clickOnProcessPayments() {

		WebElement element = process_Payments;
		try {
			WebFrame.switchToMainPageFromIFrame(driver);
			WebButton.webButtonClick(driver, logger, element);
			logger.log(LogStatus.PASS, "'Process Payments' clicked successfully");
		} catch (Exception e) {
			logger.log(LogStatus.FAIL, "Failed to click on Process Payments Button - " + e.getStackTrace().toString());
			e.printStackTrace();
		}
	}
	
}

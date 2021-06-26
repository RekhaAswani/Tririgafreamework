package Tririga_PagesPkg;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;
import com.webElementPkg.WebButton;
import com.webElementPkg.WebFrame;
import com.webElementPkg.WebLink;
import com.webElementPkg.WebTextbox;
import com.webElementPkg.WebUtilities;



public class ProcessLeaseApprovalsPage {
	
	private WebDriver driver=null;
	private ExtentTest logger=null;
	
	public static final String MainFrame="//iframe[@class='gwt-Frame GDNOS4QBO-']";
	public static final String TableFrame="//iframe[@id='innerFrame23']";
	public static final String ContentFrame="//iframe[@id='contentFrame']";
	public static String SelectorFrame="unMovableLayerFrame";
		
	@FindBy(xpath="//*[@id='large-quick-link-4312']")
	public static WebElement AllLeaseContracts;
		
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
	
	@FindBy(xpath = "//*[contains(@id,'queryResultRow')][1]//td//following-sibling::input[contains(@type,'checkbox')]")
	public WebElement first_Result_Checkbox_Ele;
	
	@FindBy(xpath = "(//*[@class='wizardSectionLabel'])[1]")
	public WebElement wizard_Title;
	
	
	public ProcessLeaseApprovalsPage(WebDriver driver,ExtentTest logger){
		this.driver=driver;
		this.logger=logger;
		PageFactory.initElements(driver, this);
	}
	
	public void SelectApprovalRole(String RoleType) throws InterruptedException{
		
		boolean FoundFlag=false;
		try{
			
		WebButton.webButtonClick(driver, logger, ApprovalRoleSelector);
		Thread.sleep(3000);
		WebFrame.switchOnFrameById(driver, SelectorFrame);
		List<WebElement> DropDownValues = driver.findElements(By.xpath("//table[@id='listValueTable']//span[@class='bodyText']"));
		System.out.println("DropDown values="+DropDownValues.size());  
		for(int i=0;i<=DropDownValues.size();i++){
			//WebElement Value =driver.findElements(By.xpath("//table[@id='listValueTable']//span[@class='bodyText']")).get(i);
			WebElement Value =driver.findElements(By.xpath("//table[@id='listValueTable']//span[@class='bodyText']")).get(i);
			System.out.println("UsageType="+Value.getText());
			if(Value.getText().contains(RoleType)){
				//MouseOperations.ActionMoveToElement(driver, logger, Value);
				Value.click();
				//WebButton.webButtonClick(driver, logger, Value);
				//Actions act1=new Actions(driver);
				//act1.sendKeys(Keys.TAB).build().perform();
				logger.log(LogStatus.INFO, "List Values- '" +  RoleType + "' successfully selected");
				FoundFlag=true;
				Thread.sleep(2000);
				WebFrame.switchToMainPageFromIFrame(driver);
				break;
			}
		}
		if (FoundFlag=false){
			logger.log(LogStatus.ERROR,"Failed to select '" + RoleType);
		}
		}catch(Exception e){
			logger.log(LogStatus.ERROR,"Failed to select Space Usage Type '" + e.getMessage());
		}
		
		
	}
	public boolean ApproveTheContract(String ApproverRole) {
		
		String Datestamp = new SimpleDateFormat("MM/dd/yyyy").format(new Date());
		boolean Status=true;
		
		try {
			WebFrame.switchToMainPageFromIFrame(driver);
			WebFrame.switchOnMultipleFramesByXpath(driver, MainFrame,ContentFrame);
			WebUtilities.waitForElementToAppear(driver, ApprovalRoleSelector, logger);
			WebButton.webButtonClick(driver, logger, ApprovalRoleSelector);
			SelectApprovalRole(ApproverRole);
			
			WebFrame.switchToMainPageFromIFrame(driver);
			WebFrame.switchOnMultipleFramesByXpath(driver, MainFrame,ContentFrame);
			WebTextbox.sendTextToWebInput(ReviewcCommentstxt, "Approve_"+Datestamp, logger);
			
			WebFrame.switchToMainPageFromIFrame(driver);
			WebFrame.switchOnMultipleFramesByXpath(driver, MainFrame,ContentFrame);
			WebButton.webButtonClick(driver, logger, GetApprovalbtn);
			
			WebFrame.switchToMainPageFromIFrame(driver);
			WebFrame.switchOnMultipleFramesByXpath(driver, MainFrame,ContentFrame,TableFrame);
			WebUtilities.waitForElementToAppear(driver, first_Result_Checkbox_Ele, logger);
			WebButton.webButtonClick(driver, logger, first_Result_Checkbox_Ele);
			
			WebFrame.switchToMainPageFromIFrame(driver);
			WebFrame.switchOnMultipleFramesByXpath(driver, MainFrame,ContentFrame);
			WebButton.webButtonClick(driver, logger, Approvebtn);
			
			WebFrame.switchToMainPageFromIFrame(driver);
			WebFrame.switchOnMultipleFramesByXpath(driver, MainFrame,ContentFrame);
			if (wizard_Title.getText().contains("Attention!")) {
				Status=false;
				logger.log(LogStatus.FAIL, "CONTRACT is not approved by "+ApproverRole);
			} else{
				logger.log(LogStatus.PASS, "CONTRACT is approved by "+ApproverRole);
				Status=true;
			}
			
			
		} catch (Exception e) {
			Status=false;
			logger.log(LogStatus.FAIL, "Failed to click on Process Payments Button - " + e.getStackTrace().toString());
			e.printStackTrace();
		}
		return Status;
	}
	
}

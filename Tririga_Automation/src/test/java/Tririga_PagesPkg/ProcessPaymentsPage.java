package Tririga_PagesPkg;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;
import com.webElementPkg.MouseOperations;
import com.webElementPkg.WebBrowser;
import com.webElementPkg.WebButton;
import com.webElementPkg.WebFrame;
import com.webElementPkg.WebTextbox;
import com.webElementPkg.WebUtilities;

public class ProcessPaymentsPage {

	private WebDriver driver = null;
	private ExtentTest logger = null;

	public static final String processPayments_MainFrame = "//iframe[@class='gwt-Frame GDNOS4QBO-']";
	public static final String processPayments_TableFrame = "//iframe[@id='objectsFrame']";
	public static final String processPayments_ContentFrame = "//iframe[@id='contentFrame']";
	public static final String processPayments_paymentLine_Results = "//iframe[@id='innerFrame651']";

	public static final String processLine_ChildFrame = "//iframe[@name='childFrame']";
	public static final String processLine_MainFrame = "//*[@name='frameMain']";
	public static final String processLine_ContentFrame = "//iframe[@name='contentFrame']";

	@FindBy(xpath = "//*[text()='Add']")
	public WebElement add_Button;

	@FindBy(xpath = "//*[@id='attr_seq_1096']")
	public WebElement processPayments_Name;

	@FindBy(xpath = "//*[@id='attr_seq_1169']")
	public WebElement processPayments_Desc;

	@FindBy(xpath = "//*[@id='attr_seq_1168']")
	public WebElement processPayments_PlannedDate;

	@FindBy(xpath = "//*[@id='attr_seq_1253']")
	public WebElement processPayments_ProcessType;

	@FindBy(xpath = "//*[@id='attr_seq_1250']")
	public WebElement processPayments_DueBefore;

	@FindBy(xpath = "//span[contains(text(),'Find')]")
	public WebElement paymentLine_Find_Button;

	@FindBy(xpath = "//*[@name='filterValue1' and contains(@title,'ID')]")
	public WebElement lease_ID_Filter;

	@FindBy(xpath = "//*[@name='filterValue2' and contains(@title,'Name')]")
	public WebElement lease_Name_Filter;

	@FindBy(xpath = "//*[@name='filterValue9' and contains(@title,'Status')]")
	public WebElement lease_Status_Filter;

	@FindBy(xpath = "//*[@id='applyFiltersLink']")
	public WebElement apply_Filter;

	@FindBy(xpath = "//*[@id='clearFiltersLink']")
	public WebElement clear_Filter;

	@FindBy(xpath = "//*[@id='upperPageNav']//*[contains(text(),'Create Draft')]")
	public WebElement create_Draft_Btn;

	@FindBy(xpath = "//*[@id='upperPageNav']//*[contains(text(),'Issue')]")
	public WebElement issue_Btn;

	@FindBy(xpath = "//*[contains(@id,'queryResultRow')]")
	public List<WebElement> search_Results;

	@FindBy(xpath = "//*[@id='actionsTable']//*[text()='OK']")
	public WebElement ok_Btn;

	public ProcessPaymentsPage(WebDriver driver, ExtentTest logger) {
		this.driver = driver;
		this.logger = logger;
		PageFactory.initElements(driver, this);
	}

	public void PaymentLine_ApplyFilters(String leaseID, String leaseName, String status) {

		try {
			WebFrame.switchOnMultipleFramesByXpath(driver, processLine_ChildFrame, processLine_MainFrame,
					processLine_ContentFrame);
			WebUtilities.waitForElementToAppear(driver, lease_ID_Filter, logger);
			WebTextbox.sendTextToWebInput(lease_ID_Filter, leaseID, logger);
			WebUtilities.waitForElementToAppear(driver, lease_Name_Filter, logger);
			WebTextbox.sendTextToWebInput(lease_Name_Filter, leaseName, logger);
			WebUtilities.waitForElementToAppear(driver, lease_Status_Filter, logger);
			WebTextbox.sendTextToWebInput(lease_Status_Filter, status, logger);
			WebButton.webButtonClick(driver, logger, apply_Filter);
			logger.log(LogStatus.PASS, "Applied Filter in Process Payments  ->  Payment Line Page");

		} catch (Exception e) {
			logger.log(LogStatus.FAIL, "Failed to apply filters in Process Payments  ->  Payment Line Page - "
					+ e.getStackTrace().toString());
			e.printStackTrace();
		}
	}

	public void Select_PaymentLine_From_Result(int rowID) {

		try {
			WebFrame.switchToMainPageFromIFrame(driver);
			WebFrame.switchOnMultipleFramesByXpath(driver, processPayments_ContentFrame);
			WebFrame.switchOnMultipleFramesByXpath(driver, processLine_ChildFrame, processLine_MainFrame,
					processLine_ContentFrame);
			if (search_Results.size() > 0) {
				WebElement rowEle = search_Results.get(rowID - 1);
				WebElement ele = rowEle.findElement(By.xpath(".//td[1]//input"));
				WebButton.webButtonClick(driver, logger, ele);
				//WebUtilities.waitForElementToAppear(driver, ok_Btn, logger);
				Thread.sleep(3000);
				WebButton.webButtonClick(driver, logger, ok_Btn);
				logger.log(LogStatus.PASS, "Selected the payment line from search results");
			} else {
				logger.log(LogStatus.FAIL,
						"Failed to select the payment line from search results.No Results are found.");
			}
		} catch (Exception e) {
			logger.log(LogStatus.FAIL,
					"Failed to select the payment line from search results. " + e.getStackTrace().toString());
			e.printStackTrace();
		}
	}

	public boolean Verify_PaymentLineItems() {

		boolean available = false;
		try {
			WebFrame.switchToMainPageFromIFrame(driver);
			WebFrame.switchOnMultipleFramesByXpath(driver, processPayments_ContentFrame,
					processPayments_paymentLine_Results);
			if (search_Results.size() > 0) {
				logger.log(LogStatus.PASS, "Payment Line added successfully.");
				available = true;
			} else {
				logger.log(LogStatus.FAIL, "Failed to add Payment Line.");
			}
		} catch (Exception e) {
			logger.log(LogStatus.FAIL, "Failed to verify payment line addition. " + e.getStackTrace().toString());
			e.printStackTrace();
		}
		return available;
	}

	public void clickOnAddButton() {

		WebElement element = add_Button;
		try {
			WebFrame.switchOnMultipleFramesByXpath(driver, processPayments_MainFrame, processPayments_TableFrame);
			WebButton.webButtonClick(driver, logger, element);
			logger.log(LogStatus.PASS, "Clicked on Add Button successfully");
		} catch (Exception e) {
			logger.log(LogStatus.FAIL, "Failed to click on add Button - " + e.getStackTrace().toString());
			e.printStackTrace();
		}
	}

	public void clickOnDraftButton() {

		WebElement element = create_Draft_Btn;
		try {
			WebFrame.switchToMainPageFromIFrame(driver);
			WebButton.webButtonClick(driver, logger, element);
			logger.log(LogStatus.PASS, "Clicked on Draft Button successfully");
		} catch (Exception e) {
			logger.log(LogStatus.FAIL, "Failed to click on draft Button - " + e.getStackTrace().toString());
			e.printStackTrace();
		}
	}

	public void clickOnIssueButton() {

		WebElement element = issue_Btn;
		try {
			WebFrame.switchToMainPageFromIFrame(driver);
			WebButton.webButtonClick(driver, logger, element);
			logger.log(LogStatus.PASS, "Clicked on Issue Button successfully");
		} catch (Exception e) {
			logger.log(LogStatus.FAIL, "Failed to click on issue Button - " + e.getStackTrace().toString());
			e.printStackTrace();
		}
	}

	public void clickOnPaymentLineFindBtn() {

		WebElement element = paymentLine_Find_Button;
		try {
			WebFrame.switchToMainPageFromIFrame(driver);
			WebFrame.switchOnMultipleFramesByXpath(driver, processPayments_ContentFrame);
			WebButton.webButtonClick(driver, logger, element);
			logger.log(LogStatus.PASS, "Clicked on PaymentLine Find Button successfully");
		} catch (Exception e) {
			logger.log(LogStatus.FAIL, "Failed to click on PaymentLine Find Button - " + e.getStackTrace().toString());
			e.printStackTrace();
		}
	}

	public void ProcessPayments_General_Details(String name, String plannedDate, String description) {

		try {
			WebBrowser.switchToChildWindow(driver);
			driver.manage().window().maximize();
			WebFrame.switchOnMultipleFramesByXpath(driver, processPayments_ContentFrame);

			WebUtilities.waitForElementToAppear(driver, processPayments_Name, logger);
			WebTextbox.sendTextToWebInput(processPayments_Name, name, logger);

			WebUtilities.waitForElementToAppear(driver, processPayments_Desc, logger);
			WebTextbox.sendTextToWebInput(processPayments_Desc, description, logger);

			WebUtilities.waitForElementToAppear(driver, processPayments_PlannedDate, logger);
			WebTextbox.clearTextBox(processPayments_PlannedDate);
			WebTextbox.sendTextToWebInput(processPayments_PlannedDate, plannedDate, logger);
			MouseOperations.PerformKeywordActions(driver, logger, "TAB");
			Thread.sleep(4000);
			logger.log(LogStatus.PASS, "General Details are entered successfully in Process Payments Page");

		} catch (Exception e) {
			logger.log(LogStatus.FAIL,
					"Failed to fill general details in Process Payments Page - " + e.getStackTrace());
			e.printStackTrace();
		}
	}

	public void ProcessPayments_Parameters_Details(String processType, String dueBefore) {

		try {
			WebFrame.switchToMainPageFromIFrame(driver);
			WebFrame.switchOnMultipleFramesByXpath(driver, processPayments_ContentFrame);

			WebUtilities.waitForElementToAppear(driver, processPayments_ProcessType, logger);
			WebTextbox.clearTextBox(processPayments_ProcessType);
			WebTextbox.sendTextToWebInput(processPayments_ProcessType, processType, logger);
			MouseOperations.PerformKeywordActions(driver, logger, "TAB");
			Thread.sleep(4000);

			WebUtilities.waitForElementToAppear(driver, processPayments_DueBefore, logger);
			WebTextbox.clearTextBox(processPayments_DueBefore);
			WebTextbox.sendTextToWebInput(processPayments_DueBefore, dueBefore, logger);
			MouseOperations.PerformKeywordActions(driver, logger, "TAB");
			Thread.sleep(4000);

			logger.log(LogStatus.PASS, "Parametes Details are entered successfully in Process Payments Page");

		} catch (Exception e) {
			logger.log(LogStatus.FAIL,
					"Failed to fill Parametes details in Process Payments Page - " + e.getStackTrace().toString());
			e.printStackTrace();
		}
	}

}

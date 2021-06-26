package Tririga_PagesPkg;

import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;
import com.webElementPkg.MouseOperations;
import com.webElementPkg.WebButton;
import com.webElementPkg.WebFrame;
import com.webElementPkg.WebTextbox;
import com.webElementPkg.WebUtilities;

public class PaymentSchedules {

	private WebDriver driver = null;
	private ExtentTest logger = null;

	public static String content_Frame = "//iframe[@id='contentFrame']";
	public static String child_Frame = "//iframe[@id='childFrame']";

	@FindBy(xpath = "//*[@id='upperPageNav']//span[contains(text(),'Void')]")
	public WebElement void_Btn;

	@FindBy(xpath = "//*[@id='upperPageNav']//span[text()='Save']")
	public WebElement save_Btn;

	@FindBy(xpath = "//*[@id='upperPageNav']//span[text()='Save & Close']")
	public WebElement save_Close_Btn;

	@FindBy(xpath = "//*[@id='upperPageNav']//span[contains(text(),'Adjust Payment')]")
	public WebElement adjust_Payment_Btn;

	@FindBy(xpath = "//input[@name='attr_seq_1068']")
	public WebElement upate_Action_Input;

	@FindBy(xpath = "//input[@name='attr_seq_1042']")
	public WebElement from_Date;

	@FindBy(xpath = "//input[@name='attr_seq_1043']")
	public WebElement to_Date;

	@FindBy(xpath = "//input[@name='attr_seq_1099']")
	public WebElement end_Date;

	@FindBy(xpath = "//input[@name='attr_seq_1087']")
	public WebElement adjust_Method;

	@FindBy(xpath = "//*[@name='attr_seq_1021']")
	public WebElement adjust_Reason;

	@FindBy(xpath = "//*[@name='attr_seq_1097']")
	public WebElement adjust_Payment_Schedule_ChckBox;

	@FindBy(xpath = "//*[@id='upperPageNav']//*[contains(text(),'Process Adjustment')]	")
	public WebElement process_AdjustmentBtn;

	@FindBy(xpath = "//*[@name='attr_seq_1095']")
	public WebElement adjust_Due_Day;

	@FindBy(xpath = "//*[@name='attr_seq_1096']")
	public WebElement adjust_Due_Month;

	@FindBy(xpath = "//*[@name='attr_seq_1089']")
	public WebElement doc_Header_Text;

	@FindBy(xpath = "//*[@name='attr_seq_1098']")
	public WebElement invoice_Type;

	@FindBy(xpath = "//*[@name='attr_seq_1094']")
	public WebElement block_Payment_ChckBox;

	@FindBy(xpath = "//*[@name='attr_seq_1088']")
	public WebElement percentage_Change;

	@FindBy(xpath = "//*[@name='attr_seq_1090']")
	public WebElement process_Type;

	@FindBy(xpath = "//*[@name='attr_seq_1093']")
	public WebElement withholding_Tax;

	@FindBy(xpath = "//*[@name='attr_seq_1092']")
	public WebElement tax_Group;

	@FindBy(xpath = "//*[@name='attr_seq_1045']")
	public WebElement adjustment_Amount;

	public PaymentSchedules(WebDriver driver, ExtentTest logger) {
		this.driver = driver;
		this.logger = logger;
		PageFactory.initElements(driver, this);
	}

	public void switchToWindow(int windowCount) {

		Set<String> s = driver.getWindowHandles();
		Iterator<String> ite = s.iterator();
		int i = 1;
		while (ite.hasNext() && i < 10) {
			String popupHandle = ite.next().toString();
			driver.switchTo().window(popupHandle);
			System.out.println("Window title is : " + driver.getTitle());
			if (i == windowCount) {
				driver.manage().window().maximize();
				break;
			}
			i++;
		}
	}

	public void clickonVoidBtn() {

		try {
			WebUtilities.waitForElementToAppear(driver, void_Btn, logger);
			WebButton.webButtonClick(driver, logger, void_Btn);
			Thread.sleep(2000);
			logger.log(LogStatus.PASS, "Clicked on Void Button Successfully. ");
		} catch (Exception e) {
			logger.log(LogStatus.FAIL, "Failed to click on void button. " + e.getStackTrace());
			e.printStackTrace();
		}
	}

	public void clickonSaveBtn() {

		try {
			WebUtilities.waitForElementToAppear(driver, save_Btn, logger);
			WebButton.webButtonClick(driver, logger, save_Btn);
			Thread.sleep(2000);
			logger.log(LogStatus.PASS, "Clicked on Save Button Successfully. ");
		} catch (Exception e) {
			logger.log(LogStatus.FAIL, "Failed to click on save button. " + e.getStackTrace());
			e.printStackTrace();
		}
	}

	public void clickonSaveCloseBtn() {

		try {
			WebUtilities.waitForElementToAppear(driver, save_Close_Btn, logger);
			WebButton.webButtonClick(driver, logger, save_Close_Btn);
			Thread.sleep(1000);
			logger.log(LogStatus.PASS, "Clicked on Save & Close Button Successfully. ");
		} catch (Exception e) {
			logger.log(LogStatus.FAIL, "Failed to click on save & close button. " + e.getStackTrace());
			e.printStackTrace();
		}
	}

	public void clickonAdjustmentPaymentBtn() {

		try {
			WebUtilities.waitForElementToAppear(driver, adjust_Payment_Btn, logger);
			WebButton.webButtonClick(driver, logger, adjust_Payment_Btn);
			Thread.sleep(1000);
			logger.log(LogStatus.PASS, "Clicked on Adjustment Payment Button Successfully. ");
		} catch (Exception e) {
			logger.log(LogStatus.FAIL, "Failed to click on Adjustment Payment button. " + e.getStackTrace());
			e.printStackTrace();
		}
	}

	public void clickonProcessAdjustmentBtn() {

		try {
			WebFrame.switchToMainPageFromIFrame(driver);
			WebFrame.switchOnMultipleFramesByXpath(driver, content_Frame, child_Frame);
			WebUtilities.waitForElementToAppear(driver, process_AdjustmentBtn, logger);
			WebButton.webButtonClick(driver, logger, process_AdjustmentBtn);
			Thread.sleep(1000);
			logger.log(LogStatus.PASS, "Clicked on Process Adjustment Button Successfully. ");
		} catch (Exception e) {
			logger.log(LogStatus.FAIL, "Failed to click on Process Adjustment button. " + e.getStackTrace());
			e.printStackTrace();
		}
	}

	public void enter_PaymentAdjust_Details(String updateAction, Map<String, String> details) {

		try {

			WebFrame.switchToMainPageFromIFrame(driver);
			WebFrame.switchOnMultipleFramesByXpath(driver, content_Frame, child_Frame, content_Frame);

			WebUtilities.waitForElementToAppear(driver, upate_Action_Input, logger);
			WebTextbox.clearTextBox(upate_Action_Input);
			WebTextbox.sendTextToWebInput(upate_Action_Input, updateAction, logger);
			MouseOperations.PerformKeywordActions(driver, logger, "TAB");
			Thread.sleep(4000);

			if (updateAction.contains("Update Payment Line Items")) {
				if (details.get("Adjust Payment Schedule").equalsIgnoreCase("false")) {
					WebUtilities.waitForElementToAppear(driver, adjust_Payment_Schedule_ChckBox, logger);
					WebButton.webButtonClick(driver, logger, adjust_Payment_Schedule_ChckBox);
				}
				WebUtilities.waitForElementToAppear(driver, from_Date, logger);
				WebTextbox.clearTextBox(from_Date);
				WebTextbox.sendTextToWebInput(from_Date, details.get("From Date"), logger);

				WebUtilities.waitForElementToAppear(driver, to_Date, logger);
				WebTextbox.clearTextBox(to_Date);
				WebTextbox.sendTextToWebInput(to_Date, details.get("To Date"), logger);
				selectAdjustment_Method(details);
			} else if (updateAction.contains("Update Payment Schedule")) {
				selectAdjustment_Method(details);
			} else if (updateAction.contains("Early Schedule Termination")) {
				WebUtilities.waitForElementToAppear(driver, end_Date, logger);
				WebTextbox.clearTextBox(end_Date);
				WebTextbox.sendTextToWebInput(end_Date, details.get("End_Date"), logger);
			}
			WebUtilities.waitForElementToAppear(driver, adjust_Reason, logger);
			WebTextbox.sendTextToWebInput(adjust_Reason, details.get("Adjustment Reason"), logger);
			logger.log(LogStatus.PASS, "Payment Adjustment Details are entered successfully.");
		} catch (Exception e) {
			logger.log(LogStatus.FAIL, "Failed to enter Payment Adjustment Details - " + e.getStackTrace());
			e.printStackTrace();
		}
	}

	public void selectAdjustment_Method(Map<String, String> data) {

		String adj_Method = data.get("Adjustment Method").trim();

		try {
			WebUtilities.waitForElementToAppear(driver, adjust_Method, logger);
			WebTextbox.clearTextBox(adjust_Method);
			WebTextbox.sendTextToWebInput(adjust_Method, adj_Method, logger);
			MouseOperations.PerformKeywordActions(driver, logger, "TAB");
			Thread.sleep(2000);
			switch (adj_Method) {

			case "Date":
				WebUtilities.waitForElementToAppear(driver, adjust_Due_Day, logger);
				WebTextbox.sendTextToWebInput(adjust_Due_Day, data.get("Adjust Due Day"), logger);
				WebUtilities.waitForElementToAppear(driver, adjust_Due_Month, logger);
				WebTextbox.sendTextToWebInput(adjust_Due_Month, data.get("Adjust Due Month"), logger);
				break;
			case "Document Header":
				WebUtilities.waitForElementToAppear(driver, doc_Header_Text, logger);
				WebTextbox.sendTextToWebInput(doc_Header_Text, data.get("Document Header Text"), logger);
				break;
			case "Invoice Type":
				WebUtilities.waitForElementToAppear(driver, invoice_Type, logger);
				WebTextbox.clearTextBox(invoice_Type);
				WebTextbox.sendTextToWebInput(invoice_Type, data.get("Invoice Type"), logger);
				MouseOperations.PerformKeywordActions(driver, logger, "TAB");
				Thread.sleep(500);
				break;
			case "Payment Block":
				if (data.get("Block Payment").equalsIgnoreCase("true")) {
					WebUtilities.waitForElementToAppear(driver, block_Payment_ChckBox, logger);
					WebButton.webButtonClick(driver, logger, block_Payment_ChckBox);
				}
				break;
			case "Percentage":
				WebUtilities.waitForElementToAppear(driver, percentage_Change, logger);
				WebTextbox.clearTextBox(percentage_Change);
				WebTextbox.sendTextToWebInput(percentage_Change, data.get("Percentage Change"), logger);
				break;
			case "Process Type":
				WebUtilities.waitForElementToAppear(driver, process_Type, logger);
				WebTextbox.clearTextBox(process_Type);
				WebTextbox.sendTextToWebInput(process_Type, data.get("Process Type"), logger);
				MouseOperations.PerformKeywordActions(driver, logger, "TAB");
				Thread.sleep(500);
				break;
			case "Tax":
				WebUtilities.waitForElementToAppear(driver, withholding_Tax, logger);
				WebTextbox.sendTextToWebInput(withholding_Tax, data.get("Withholding Tax"), logger);
				WebUtilities.waitForElementToAppear(driver, tax_Group, logger);
				WebTextbox.sendTextToWebInput(tax_Group, data.get("Tax Group"), logger);
				break;
			case "Value":
				WebUtilities.waitForElementToAppear(driver, adjustment_Amount, logger);
				WebTextbox.sendTextToWebInput(adjustment_Amount, data.get("Adjust Amount To"), logger);
				break;
			}
			logger.log(LogStatus.PASS, "Payment Adjustment Method Details are entered successfully.");
		} catch (Exception e) {
			logger.log(LogStatus.FAIL, "Failed to enter Payment Adjustment Method Details - " + e.getStackTrace());
			e.printStackTrace();
		}
	}
}

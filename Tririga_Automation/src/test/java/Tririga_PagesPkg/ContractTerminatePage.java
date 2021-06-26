package Tririga_PagesPkg;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.relevantcodes.extentreports.ExtentTest;
import com.webElementPkg.MouseOperations;
import com.webElementPkg.WebButton;
import com.webElementPkg.WebFrame;
import com.webElementPkg.WebTextbox;

public class ContractTerminatePage {

	private WebDriver driver = null;
	private ExtentTest logger = null;

	public static String ContractMainFrame = "//iframe[@id='contentFrame']";
	public static String ContractTerminateFrame = "//iframe[@id='childFrame']";
	public static String ContractTerminateContentFrame = "//iframe[@id='contentFrame']";

	@FindBy(xpath = "//*[@id='attr_seq_1079']")
	public WebElement Termination_Effective_Date;

	@FindBy(xpath = "//*[@id='attr_seq_1071']")
	public WebElement Termination_Comment_Type;

	@FindBy(xpath = "//*[@id='attr_seq_1039']")
	public WebElement Termination_Comment;

	@FindBy(xpath = "//*[@id='attr_seq_1067']")
	public WebElement Void_Payments_After_Termination;

	@FindBy(xpath = "//*[@id='attr_seq_1080']")
	public WebElement Contract_Status;

	@FindBy(xpath = "//*[@id='attr_seq_1081']")
	public WebElement Calc_Recovery_Amount;

	@FindBy(xpath = "//div[@id='BottomPageNav']//span[text()='Continue']")
	public WebElement ContinueBtn;

	public ContractTerminatePage(WebDriver driver, ExtentTest logger) {
		this.driver = driver;
		this.logger = logger;
		PageFactory.initElements(driver, this);
	}

	public boolean ContractTerminate_General_Details(String terminatDate, String commentType, String terminateComment,
			String voidPayments, String calRecoveryAmt) {

		boolean LeaseTerminateStatus = false;
		try {

			WebFrame.switchOnMultipleFramesByXpath(driver, ContractMainFrame, ContractTerminateFrame,ContractTerminateContentFrame);

			WebTextbox.clearTextBox(Termination_Effective_Date);
			Thread.sleep(3000);
			WebTextbox.sendTextToWebInput(Termination_Effective_Date, terminatDate, logger);
			//WebButton.activeElementPressKeyBoardKeys(driver, "TAB", Termination_Effective_Date);

			WebTextbox.clearTextBox(Termination_Comment_Type);
			WebTextbox.sendTextToWebInput(Termination_Comment_Type, commentType, logger);
			Thread.sleep(4000);
			MouseOperations.PerformKeywordActions(driver, logger, "TAB");
			Thread.sleep(4000);

			WebTextbox.sendTextToWebInput(Termination_Comment, terminateComment, logger);
			Thread.sleep(2000);

			WebTextbox.clearTextBox(Void_Payments_After_Termination);
			WebTextbox.sendTextToWebInput(Void_Payments_After_Termination, voidPayments, logger);
			MouseOperations.PerformKeywordActions(driver, logger, "TAB");
			Thread.sleep(2000);

			WebTextbox.clearTextBox(Calc_Recovery_Amount);
			WebTextbox.sendTextToWebInput(Calc_Recovery_Amount, calRecoveryAmt, logger);
			MouseOperations.PerformKeywordActions(driver, logger, "TAB");
			Thread.sleep(4000);

			WebButton.webButtonClick(driver, logger, ContinueBtn);
			Thread.sleep(4000);

			LeaseTerminateStatus = true;

		} catch (Exception e) {
			LeaseTerminateStatus = false;
		}

		return LeaseTerminateStatus;

	}

}

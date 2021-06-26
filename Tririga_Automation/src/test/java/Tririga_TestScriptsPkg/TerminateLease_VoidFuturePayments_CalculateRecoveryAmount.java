package Tririga_TestScriptsPkg;

import java.io.IOException;
import java.lang.reflect.Method;
import java.sql.Timestamp;
import java.util.Date;
import java.util.Hashtable;

import org.openqa.selenium.WebDriver;
import org.testng.SkipException;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

import Tririga_PagesPkg.AllLeaseContractsPage;
import Tririga_PagesPkg.ContractTerminatePage;
import Tririga_PagesPkg.HomePage;
import Tririga_PagesPkg.LoginPage;
import Tririga_PagesPkg.NewLease_GeneralPage;
import Tririga_PagesPkg.NewLease_PaymentsPage;
import Tririga_PagesPkg.ProcessPaymentsPage;
import corePkg.BaseClass;
import fileReadingPkg.Constants;
import fileReadingPkg.DataUtil;
import com.webElementPkg.WebBrowser;
import com.webElementPkg.WebFrame;

public class TerminateLease_VoidFuturePayments_CalculateRecoveryAmount extends BaseClass {

	String testCaseName = "TerminateLease_VoidFuturePayments_CalculateRecoveryAmount";

	private WebDriver driver = null;
	private ExtentTest logger = null;

	public static LoginPage TririgaLoginCls = null;
	public static HomePage TririgaHomePage = null;
	public static AllLeaseContractsPage AllLeaseContractspg = null;
	public static NewLease_GeneralPage LeaseGeneralPg = null;
	public static ContractTerminatePage contractTerminatePage = null;
	public static NewLease_PaymentsPage LeasePaymentsPg = null;
	public static ProcessPaymentsPage ProcessPaymentsPg = null;

	private static final String testCategory = "Terminate an existing lease, voiding future payments and calculating recovery amount";

	private void intialize() {
		driver = BaseClass.driver;
		logger = BaseClass.logger;
		TririgaLoginCls = new LoginPage(driver, logger);
		TririgaHomePage = new HomePage(driver, logger);
		AllLeaseContractspg = new AllLeaseContractsPage(driver, logger);
		LeaseGeneralPg = new NewLease_GeneralPage(driver, logger);
		contractTerminatePage = new ContractTerminatePage(driver, logger);
		LeasePaymentsPg = new NewLease_PaymentsPage(driver, logger);
		ProcessPaymentsPg = new ProcessPaymentsPage(driver, logger);
		logger.assignCategory(testCategory);
	}

	@Test(dataProvider = "getData", description = testCategory)
	public void TerminateLeaseVoidFuturePaymentsCalculateRecoveryAmount(Hashtable<String, String> data, Method method)
			throws InterruptedException, IOException {

		intialize();
		boolean testStatus = false;
		if (!DataUtil.isTestExecutable(xls, testCaseName) || data.get(Constants.RUNMODE_COL).equals("N")) {
			logger.log(LogStatus.SKIP, "Skipping the test as Rnumode is N");
			throw new SkipException("Skipping the test as Rnumode is N");
		}
		try {
			testStatus = TririgaLoginCls.Tririga_Login(data.get("Username"), data.get("Password"));
			if (testStatus) {
				BaseClass.reportTestCaseStatus(driver, logger, method.getName() + addTimeStamp(), testStatus);
				String parentWindow = WebBrowser.retrieveCurrentWindowHandle(driver, logger);
				TririgaHomePage.clickOnAllLeaseContractslnk();
				AllLeaseContractspg.apply_LeaseContractFilters(data.get("LEASEID"), data.get("LEASETYPE"), "Active");
				AllLeaseContractspg.Select_Lease_From_Result(1);
				testStatus = LeaseGeneralPg.ClickOnTerminate();
				BaseClass.reportTestCaseStatus(driver, logger, method.getName() + addTimeStamp(), testStatus);
				testStatus = contractTerminatePage.ContractTerminate_General_Details(data.get("LEASE_TERMINATE_DATE"),
						data.get("LEASE_COMMENT_TYPE"), data.get("LEASE_TERMINATE_COMMENT"),
						data.get("VOID_PAYMENTS_AFTER_TERMINATE"), data.get("CALC_RECOVERT_AMNT"));
				WebBrowser.switchToWindow(driver, logger, parentWindow);
				WebFrame.switchToMainPageFromIFrame(driver);
				String status = AllLeaseContractspg.getExisitingLeaseStatus(data.get("LEASEID"), data.get("LEASETYPE"));
				if (status.equalsIgnoreCase(data.get("TERMINATION_STATUS"))) {
					System.out.println("Status verified");
					BaseClass.reportTestCaseStatus(driver, logger, method.getName() + addTimeStamp(), testStatus);
					WebFrame.switchToMainPageFromIFrame(driver);
					AllLeaseContractspg.apply_LeaseContractFilters(data.get("LEASEID"), data.get("LEASETYPE"), "");
					AllLeaseContractspg.Select_Lease_From_Result(1);
					LeasePaymentsPg.clickOnPaymentsTab();
					LeasePaymentsPg.selectRelatedReportOptions(data.get("RELATED_REPORT_OPTION"));
					driver.close();
					WebBrowser.switchToWindow(driver, logger, parentWindow);
					TririgaHomePage.NavigateToHomePg();
					TririgaHomePage.clickOnProcessPayments();
					ProcessPaymentsPg.clickOnAddButton();
					ProcessPaymentsPg.ProcessPayments_General_Details(data.get("PROCESSPAYMET_NAME"),
							data.get("PROCESSPAYMET_PLANNEDDATE"), data.get("PROCESSPAYMET_DESC"));
					ProcessPaymentsPg.ProcessPayments_Parameters_Details(data.get("PROCESS_TYPE"),
							data.get("DUE_BEFORE"));
					ProcessPaymentsPg.clickOnPaymentLineFindBtn();
					ProcessPaymentsPg.PaymentLine_ApplyFilters(data.get("LEASEID"), "", "");
					BaseClass.reportTestCaseStatus(driver, logger, method.getName() + addTimeStamp(), true);
					ProcessPaymentsPg.Select_PaymentLine_From_Result(1);
					testStatus = ProcessPaymentsPg.Verify_PaymentLineItems();
					BaseClass.reportTestCaseStatus(driver, logger, method.getName() + addTimeStamp(), testStatus);
					ProcessPaymentsPg.clickOnDraftButton();
					ProcessPaymentsPg.clickOnIssueButton();
					WebBrowser.switchToWindow(driver, logger, parentWindow);
					TririgaHomePage.NavigateToHomePg();
					TririgaHomePage.clickOnAllLeaseContractslnk();
					AllLeaseContractspg.apply_LeaseContractFilters(data.get("LEASEID"), data.get("LEASETYPE"), "");
					AllLeaseContractspg.Select_Lease_From_Result(1);
					LeasePaymentsPg.clickOnPaymentsTab();
					LeasePaymentsPg.selectRelatedReportOptions(data.get("RELATED_REPORT_OPTION_1"));
					testStatus = LeasePaymentsPg.Verify_Payment_Status("Paid");
					BaseClass.reportTestCaseStatus(driver, logger, method.getName() + addTimeStamp(), testStatus);
					WebBrowser.closeWindow(driver, logger);
					WebBrowser.switchToWindow(driver, logger, parentWindow);
					
				} else {
					BaseClass.reportTestCaseStatus(driver, logger, method.getName() + addTimeStamp(), false);
				}

			} else {
				BaseClass.reportTestCaseStatus(driver, logger, method.getName() + addTimeStamp(), false);
			}
		} catch (Exception e) {
			BaseClass.reportTestCaseStatus(driver, logger, method.getName() + addTimeStamp(), false);
		}
	}

	@DataProvider
	public Object[][] getData() {
		return DataUtil.getData(xls, testCaseName);
	}

	/**
	 * This method will return the current date time
	 * 
	 * @return String
	 */
	public String addTimeStamp() {
		return (new Timestamp(new Date().getTime())).toString().replaceAll("[^0-9]", "_");
	}
}

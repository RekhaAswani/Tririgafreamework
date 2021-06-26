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
import com.webElementPkg.WebBrowser;

import Tririga_PagesPkg.AllLeaseContractsPage;
import Tririga_PagesPkg.ContractTerminatePage;
import Tririga_PagesPkg.HomePage;
import Tririga_PagesPkg.LoginPage;
import Tririga_PagesPkg.NewLease_GeneralPage;
import Tririga_PagesPkg.NewLease_PaymentsPage;
import Tririga_PagesPkg.PaymentSchedules;
import Tririga_PagesPkg.ProcessPaymentsPage;
import corePkg.BaseClass;
import fileReadingPkg.Constants;
import fileReadingPkg.DataUtil;

public class Payment_Reversal_Reposting extends BaseClass {

	String testCaseName = "Payment_Reversal_Reposting";

	private WebDriver driver = null;
	private ExtentTest logger = null;

	public static LoginPage TririgaLoginCls = null;
	public static HomePage TririgaHomePage = null;
	public static AllLeaseContractsPage AllLeaseContractspg = null;
	public static NewLease_GeneralPage LeaseGeneralPg = null;
	public static ContractTerminatePage contractTerminatePage = null;
	public static NewLease_PaymentsPage LeasePaymentsPg = null;
	public static ProcessPaymentsPage ProcessPaymentsPg = null;
	public static PaymentSchedules PaymentSchedulesPg = null;

	private static final String testCategory = "Payment Reversal and Reposting";

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
		PaymentSchedulesPg = new PaymentSchedules(driver, logger);
		logger.assignCategory(testCategory);
	}

	@Test(dataProvider = "getData", description = testCategory)
	public void PaymentReversalReposting(Hashtable<String, String> data, Method method)
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
				testStatus=AllLeaseContractspg.CreateContractReviseLease(data.get("LEASEID"), data.get("LEASETYPE"),data.get("CHANGETYPE"));
				BaseClass.reportTestCaseStatus(driver, logger, method.getName() + addTimeStamp(), testStatus);				
				System.out.println("Static variable Lease ID="+LeaseGeneralPg.LeaseNo);
				
				WebBrowser.switchToWindow(driver, logger, parentWindow);
				LeasePaymentsPg.clickOnMainPaymentsTab();
				//LeasePaymentsPg.applyFilterinPayments(data);
				String childWindow = WebBrowser.retrieveCurrentWindowHandle(driver, logger);
				LeasePaymentsPg.selectPaymentSchedulesLink();
				
				PaymentSchedulesPg.switchToWindow(3);
				PaymentSchedulesPg.clickonAdjustmentPaymentBtn();
				PaymentSchedulesPg.enter_PaymentAdjust_Details("Early Schedule Termination", data);
				PaymentSchedulesPg.clickonProcessAdjustmentBtn();
				PaymentSchedulesPg.clickonSaveCloseBtn();
				
				WebBrowser.switchToWindow(driver, logger, parentWindow);
				LeasePaymentsPg.clickOnPaymentsTab();
				LeasePaymentsPg.selectRelatedReportOptions("Payments - Cancelled");
				//LeasePaymentsPg.applyFilterinPayments(data);
				testStatus = LeasePaymentsPg.Verify_Payment_Status("Void");
				LeasePaymentsPg.clickOnPaymentSchedulesTab();
				LeasePaymentsPg.GeneratePaymentsSchedules(data.get("PAYMENTTYPE1"), data.get("INVOICETYPE"),
						data.get("FREQUENCY"), data.get("PAYMENTDUEON"), data.get("FULLPAYMENTSTARTDATE"),
						data.get("NUMBEROFSCHEDULES"),data.get("MONTHSPERSCHEDULE"), data.get("TAXGROUP"), data.get("PAYMENTMETHOD"),data.get("AMOUNTPERBASIS"));
				LeasePaymentsPg.clickonSubmitBtn();
				
				WebBrowser.switchToWindow(driver, logger, parentWindow);
				TririgaHomePage.NavigateToHomePg();
				TririgaHomePage.clickOnAllLeaseContractslnk();
				String LeaseNo;
				if(data.get("LEASEID").length()>0){
					LeaseNo=data.get("LEASEID");
				}else{
					LeaseNo=LeaseGeneralPg.LeaseNo;
				}
				String Status = AllLeaseContractspg.getExisitingLeaseStatus(LeaseNo, "");
				if (Status.trim().equalsIgnoreCase("Revision Accounting In Review")) {
					BaseClass.reportTestCaseStatus(driver, logger, method.getName() + addTimeStamp(), true);
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

package Tririga_TestScriptsPkg;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Method;
import java.util.Hashtable;

import org.openqa.selenium.WebDriver;

import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;
import com.webElementPkg.WebFrame;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import org.testng.SkipException;

import Tririga_PagesPkg.*;
import corePkg.BaseClass;
import fileReadingPkg.*;

public class CreateNew_ExpenseLeaseContract_ActivateContract extends BaseClass {

	String testCaseName = "CreateNew_ExpenseLeaseContract_ActivateContract";
	private WebDriver driver = null;
	private ExtentTest logger = null;
	public static LoginPage TririgaLoginCls = null;
	public static HomePage TririgaHomePage = null;
	public static NewLease_GeneralPage LeaseGeneralPg = null;
	public static NewLease_ContactDetailsPage LeaseContactsPg = null;
	public static NewLease_LocationsPage LeaseLocationsPg = null;
	public static NewLease_PaymentsPage LeasePaymentsPg = null;
	public static AllLeaseContractsPage AllLeasePg = null;

	private static String testCategory = "Create New Expense Lease Contract and Activate Contract";

	private void intialize() {
		driver = BaseClass.driver;
		logger = BaseClass.logger;
		TririgaLoginCls = new LoginPage(driver, logger);
		TririgaHomePage = new HomePage(driver, logger);
		LeaseGeneralPg = new NewLease_GeneralPage(driver, logger);
		LeaseContactsPg = new NewLease_ContactDetailsPage(driver, logger);
		LeaseLocationsPg = new NewLease_LocationsPage(driver, logger);
		LeasePaymentsPg = new NewLease_PaymentsPage(driver, logger);
		AllLeasePg = new AllLeaseContractsPage(driver, logger);
		logger.assignCategory(testCategory);
	}

	@Test(dataProvider = "getData", description = "Create New Expense Lease Contract and Activate Contract")
	public void CreateNewExpenseLeaseContractActivateContract(Hashtable<String, String> data, Method method)
			throws InterruptedException, IOException {

		intialize();
		// teststatus=TririgaLoginCls.Tririga_Login(data.get("Username"),
		// data.get("Password"));
		boolean testStatus = false;
		if (!DataUtil.isTestExecutable(xls, testCaseName) || data.get(Constants.RUNMODE_COL).equals("N")) {
			logger.log(LogStatus.SKIP, "Skipping the test as Rnumode is N");
			throw new SkipException("Skipping the test as Rnumode is N");
		}
		try {
			testStatus = TririgaLoginCls.Tririga_Login(data.get("Username"), data.get("Password"));
			if (testStatus) {
				BaseClass.reportTestCaseStatus(driver, logger, method.getName(), testStatus);
				TririgaHomePage.clickOnAllLeaseContractslnk();
				TririgaHomePage.ClickOnAddContract();
			} else {
				BaseClass.reportTestCaseStatus(driver, logger, method.getName(), false);
			}

			testStatus = LeaseGeneralPg.EnterExpenseLease_ActiveContract_Details(testCaseName, data.get("LEASETYPE"),
					data.get("SECONDARYLEASETYPE"), data.get("CONTRACTSTATUS"), data.get("COMMENCEMENTDATE"),
					data.get("LEASEEXPIRATIONDATE"), data.get("BUSINESSUNIT"));
			if (testStatus) {
				BaseClass.reportTestCaseStatus(driver, logger, method.getName(), testStatus);
			} else {
				BaseClass.reportTestCaseStatus(driver, logger, method.getName(), false);
			}

			testStatus = LeaseContactsPg.SelectUsersWithDifferentRoles(data.get("DCHUSER"));
			if (testStatus) {
				BaseClass.reportTestCaseStatus(driver, logger, method.getName(), testStatus);
			} else {
				BaseClass.reportTestCaseStatus(driver, logger, method.getName(), false);
			}

			testStatus = LeaseContactsPg.SelectLookupData(data.get("TENANTORGANISATION"),
					data.get("LANDLORDORGANISATION"), data.get("REMITTO"));
			if (testStatus) {
				BaseClass.reportTestCaseStatus(driver, logger, method.getName(), testStatus);
			} else {
				BaseClass.reportTestCaseStatus(driver, logger, method.getName(), false);
			}

			testStatus = LeaseContactsPg.ClickOnCreateDraft();
			if (testStatus) {
				BaseClass.reportTestCaseStatus(driver, logger, method.getName(), testStatus);
			} else {
				BaseClass.reportTestCaseStatus(driver, logger, method.getName(), false);
			}
			String LeaseID = LeaseGeneralPg.GetLeaseID();
			System.out.println("Lease return=" + LeaseID);
			System.out.println("Lease=" + LeaseGeneralPg.LeaseNo);

			testStatus = LeaseLocationsPg.SelectLocationDetails();
			if (testStatus) {
				BaseClass.reportTestCaseStatus(driver, logger, method.getName(), testStatus);
			} else {
				BaseClass.reportTestCaseStatus(driver, logger, method.getName(), false);
			}
			testStatus = LeasePaymentsPg.GeneratePaymentsSchedules(data.get("PAYMENTTYPE1"), data.get("INVOICETYPE"),
					data.get("FREQUENCY"), data.get("PAYMENTDUEON"), data.get("FULLPAYMENTSTARTDATE"),
					data.get("NUMBEROFSCHEDULES"), data.get("MONTHSPERSCHEDULE"), data.get("TAXGROUP"),
					data.get("AMOUNTPERBASIS"), data.get("PAYMENTMETHOD"));
			if (testStatus) {
				BaseClass.reportTestCaseStatus(driver, logger, method.getName(), testStatus);
			} else {
				BaseClass.reportTestCaseStatus(driver, logger, method.getName(), false);
			}

			testStatus = AllLeasePg.ActivateLeaseContract(LeaseID);
			if (testStatus) {
				BaseClass.reportTestCaseStatus(driver, logger, method.getName(), testStatus);
			} else {
				BaseClass.reportTestCaseStatus(driver, logger, method.getName(), false);
			}
		} catch (Exception e) {
			BaseClass.reportTestCaseStatus(driver, logger, method.getName(), false);
		}

	}

	@DataProvider
	public Object[][] getData() {
		return DataUtil.getData(xls, testCaseName);
	}
}

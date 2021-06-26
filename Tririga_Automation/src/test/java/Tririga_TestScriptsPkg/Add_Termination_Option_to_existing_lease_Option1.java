package Tririga_TestScriptsPkg;

import java.io.IOException;
import java.lang.reflect.Method;
import java.util.Hashtable;

import org.openqa.selenium.WebDriver;

import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;
import com.webElementPkg.WebBrowser;
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


public class Add_Termination_Option_to_existing_lease_Option1 extends BaseClass {
	
	String testCaseName="Add_Termination_Option_to_existing_lease_Option1";
	private WebDriver driver = null;
	private ExtentTest logger = null;
	
	public static LoginPage TririgaLoginCls=null;
	public static HomePage TririgaHomePage=null;
	public static AllLeaseContractsPage AllLeaseContractsPg=null;
	public static OptionsAvailableIn30DaysPage OptionsAvailableIn30Dayspg=null;
	public static ExpiredOverDueOptionsPage ExpiredOverDueOptionsPg=null;
	public static NewLease_GeneralPage NewLease_GeneralPg=null;
	public static NewLease_OptionsAndClausePage NewLease_OptionsAndClausePg=null;

	
	private static String testCategory = "Options Management";
	
	private void intialize() {
		driver = BaseClass.driver;
		logger = BaseClass.logger;
		TririgaLoginCls = new LoginPage(driver, logger);
		TririgaHomePage=new HomePage(driver,logger);
		AllLeaseContractsPg=new AllLeaseContractsPage(driver,logger);
		OptionsAvailableIn30Dayspg=new OptionsAvailableIn30DaysPage(driver,logger);
		ExpiredOverDueOptionsPg=new ExpiredOverDueOptionsPage(driver,logger);
		NewLease_OptionsAndClausePg=new NewLease_OptionsAndClausePage(driver,logger);
		NewLease_GeneralPg=new NewLease_GeneralPage(driver,logger);
		
		logger.assignCategory(testCategory);
	}
	
	
	@Test(dataProvider="getData",description = "Add Termination Option to existing lease Option1")
	public void AddTerminationOptiontoexistingleaseOption1(Hashtable<String,String> data,Method method) throws InterruptedException, IOException{

		intialize();
		
		boolean testStatus = false;
		if(!DataUtil.isTestExecutable(xls, testCaseName) ||  data.get(Constants.RUNMODE_COL).equals("N")){
			logger.log(LogStatus.SKIP, "Skipping the test as Rnumode is N");
			throw new SkipException("Skipping the test as Rnumode is N");
		}
		try {
			testStatus = TririgaLoginCls.Tririga_Login(data.get("Username"), data.get("Password"));
			if(testStatus){
				BaseClass.reportTestCaseStatus(driver, logger, method.getName(), testStatus);
				TririgaHomePage.clickOnAllLeaseContractslnk();		
			}else{
				BaseClass.reportTestCaseStatus(driver, logger, method.getName(), false);
			}
			
			testStatus=AllLeaseContractsPg.CreateContractReviseLease(data.get("LEASEID"), data.get("LEASETYPE"),data.get("CHANGETYPE"));
			if(testStatus){
				BaseClass.reportTestCaseStatus(driver, logger, method.getName(), testStatus);			
			}else{
				BaseClass.reportTestCaseStatus(driver, logger, method.getName(), false);
			}
			
			testStatus=NewLease_OptionsAndClausePg.AddOptionDetails("Termination");
			if(testStatus){
				BaseClass.reportTestCaseStatus(driver, logger, method.getName(), testStatus);			
			}else{
				BaseClass.reportTestCaseStatus(driver, logger, method.getName(), false);
			}
			
			testStatus=NewLease_OptionsAndClausePg.AddOptionDetails("Renewal");
			if(testStatus){
				BaseClass.reportTestCaseStatus(driver, logger, method.getName(), testStatus);
				NewLease_GeneralPg.ClickOnLeaseSubmitbtn();
				WebBrowser.switchToParentWindow(driver);
				TririgaHomePage.NavigateToHomePg();
			}else{
				BaseClass.reportTestCaseStatus(driver, logger, method.getName(), false);
			}
		
			TririgaHomePage.NavigateToOptionsAvailableIn30daysPg();
			System.out.println("Static variable Lease ID="+NewLease_GeneralPg.LeaseNo);
			OptionsAvailableIn30Dayspg.VerifyRecordsInTable(NewLease_GeneralPg.LeaseNo, "Renewal");
			if(testStatus){
				BaseClass.reportTestCaseStatus(driver, logger, method.getName(), testStatus);
				TririgaHomePage.NavigateToHomePg();
			}else{
				BaseClass.reportTestCaseStatus(driver, logger, method.getName(), false);
			}
			
			TririgaHomePage.NavigateToOptionsAvailableIn30daysPg();
			System.out.println("Static variable Lease ID="+NewLease_GeneralPg.LeaseNo);
			OptionsAvailableIn30Dayspg.VerifyRecordsInTable(NewLease_GeneralPg.LeaseNo, "Termination");
			if(testStatus){
				BaseClass.reportTestCaseStatus(driver, logger, method.getName(), testStatus);
				TririgaHomePage.NavigateToHomePg();
			}else{
				BaseClass.reportTestCaseStatus(driver, logger, method.getName(), false);
			}
			
//			TririgaHomePage.NavigateToExpiredOverDueOptionsPg();
//			//System.out.println("Static variable Lease ID="+NewLease_GeneralPg.LeaseNo);
//			//ExpiredOverDueOptionsPg.VerifyRecordsInTable(NewLease_GeneralPg.LeaseNo, "Termination");
//			ExpiredOverDueOptionsPg.VerifyRecordsInTable(Lease, "Termination");
//			if(testStatus){
//				BaseClass.reportTestCaseStatus(driver, logger, method.getName(), testStatus);
//				TririgaHomePage.NavigateToHomePg();
//			}else{
//				BaseClass.reportTestCaseStatus(driver, logger, method.getName(), false);
//			}
			
			
			
			
		} catch (Exception e) {
			BaseClass.reportTestCaseStatus(driver, logger, method.getName(), false);
		}
		
	}
		
	@DataProvider
	public Object[][] getData(){
		return DataUtil.getData(xls, testCaseName);
	}
}

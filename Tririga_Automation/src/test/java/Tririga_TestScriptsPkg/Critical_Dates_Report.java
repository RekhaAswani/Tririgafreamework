package Tririga_TestScriptsPkg;

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


public class Critical_Dates_Report extends BaseClass {
	
	String testCaseName="Critical_Dates_Report";
	private WebDriver driver = null;
	private ExtentTest logger = null;
	
	public static LoginPage TririgaLoginCls=null;
	public static HomePage TririgaHomePage=null;
	public static CriticalDatesReportPage CriticalDatesReportPg=null;

	
	private static String testCategory = "Critical Dates Report";
	
	private void intialize() {
		driver = BaseClass.driver;
		logger = BaseClass.logger;
		TririgaLoginCls = new LoginPage(driver, logger);
		TririgaHomePage=new HomePage(driver,logger);
		CriticalDatesReportPg=new CriticalDatesReportPage(driver,logger);
		
		logger.assignCategory(testCategory);
	}
	
	
	@Test(dataProvider="getData",description = "Critical Dates Report")
	public void CriticalDatesReport(Hashtable<String,String> data,Method method) throws InterruptedException, IOException{

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
				TririgaHomePage.NavigateToCriticalDatesReport();			
			}else{
				BaseClass.reportTestCaseStatus(driver, logger, method.getName(), false);
			}
			
			testStatus=CriticalDatesReportPg.VerifyCriticalDatesReportColumnNames();
			if(testStatus){
				BaseClass.reportTestCaseStatus(driver, logger, method.getName(), testStatus);			
			}else{
				BaseClass.reportTestCaseStatus(driver, logger, method.getName(), false);
			}
			
			
		} catch (Exception e) {
			BaseClass.reportTestCaseStatus(driver, logger, method.getName(), false);
		}
		
	}
		
	@DataProvider
	public Object[][] getData(){
		return DataUtil.getData(xls, testCaseName);
	}
}

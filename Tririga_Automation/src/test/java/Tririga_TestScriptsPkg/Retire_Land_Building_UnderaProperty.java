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


public class Retire_Land_Building_UnderaProperty extends BaseClass {
	
	String testCaseName="Retire_Land_Building_UnderaProperty";
	private WebDriver driver = null;
	private ExtentTest logger = null;
	
	public static LoginPage TririgaLoginCls=null;
	public static HomePage TririgaHomePage=null;
	public static Portfolio_LocationsPage PortfolioLocationPg=null;

	
	private static String testCategory = "Retire Land, building under a property";
	
	private void intialize() {
		driver = BaseClass.driver;
		logger = BaseClass.logger;
		TririgaLoginCls = new LoginPage(driver, logger);
		TririgaHomePage=new HomePage(driver,logger);
		PortfolioLocationPg=new Portfolio_LocationsPage(driver,logger);
		
		logger.assignCategory(testCategory);
	}
	
	
	@Test(dataProvider="getData",description = "Retire Land, building under a property")
	public void AddLandBuildingUnderBuilding(Hashtable<String,String> data,Method method) throws InterruptedException, IOException{

		intialize();
		//teststatus=TririgaLoginCls.Tririga_Login(data.get("Username"), data.get("Password"));
		boolean testStatus = false;
		if(!DataUtil.isTestExecutable(xls, testCaseName) ||  data.get(Constants.RUNMODE_COL).equals("N")){
			logger.log(LogStatus.SKIP, "Skipping the test as Rnumode is N");
			throw new SkipException("Skipping the test as Rnumode is N");
		}
		try {
			testStatus = TririgaLoginCls.Tririga_Login(data.get("Username"), data.get("Password"));
			if(testStatus){
				BaseClass.reportTestCaseStatus(driver, logger, method.getName(), testStatus);
				TririgaHomePage.NavigateToPortfolio_LocationsPg();				
			}else{
				BaseClass.reportTestCaseStatus(driver, logger, method.getName(), false);
			}
			
			testStatus=PortfolioLocationPg.RetireBuilding(data.get("BuildingName"),data.get("DispositionMethod"));
			if(testStatus){
				BaseClass.reportTestCaseStatus(driver, logger, method.getName(), testStatus);			
			}else{
				BaseClass.reportTestCaseStatus(driver, logger, method.getName(), false);
			}
			
			testStatus=PortfolioLocationPg.RetireLand(data.get("LandName"));
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

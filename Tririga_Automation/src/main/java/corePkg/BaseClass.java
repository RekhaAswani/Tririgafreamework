package corePkg;

import java.io.File;
import java.io.FileWriter;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.WebDriver;
import org.testng.TestNG;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import fileReadingPkg.Constants;
import fileReadingPkg.Xls_Reader;
import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;
import com.webElementPkg.WebUtilities;
import com.webfactorypkg.LocalWebDriverFactory;


import emailPkg.EmailSender;
import fileReadingPkg.ReadPropertyFile;
import reportingPkg.ReportManager;

public class BaseClass {

	public static WebDriver driver = null;
	public static ExtentReports reporter = null;
	public static ExtentTest logger = null;

	// Config File path...Required to initialize the framework...
	private static String baseURL = null;
	private static String browserName = null;
	public static String testMethodName = null;
	public static String reportFile = "";
	public static String emailConfigFile = null;

	public static ReadPropertyFile rpr = null;
	private static String sendEmail = null;
	
	public ExtentTest test;
	static FileWriter fos;
	public static Xls_Reader xls = new Xls_Reader(Constants.DATA_XLS_PATH);
	
	
	@Parameters({"baseURL"})
	@BeforeSuite
	public void beforeSuite(String baseURL) {
		try {
			
			//CreateTestNgXML(xls);
			// this method creating an instance of read property file and get Instance is calling parameterize constructor
			rpr = ReadPropertyFile.getInstance("./Resources/TestConfig/test.properties"); 

			reportFile = rpr.getKey("reportFile")+"testReporter_"+WebUtilities.GetTimeStamp()+".html";
			emailConfigFile = rpr.getKey("emailConfigFile");
			sendEmail = rpr.getKey("sendEmail");
			
			// If the we are testing single Web Application. Mention the same in 
			//test.properties file and uncomment below line.
			
			//baseURL = rpr.getKey("baseURL");
			
			// Commnet this line if previous line is uncommented
			BaseClass.baseURL = baseURL;
			browserName = rpr.getKey("browserName");
			reporter = ReportManager.getReporter(reportFile, true);
			reporter.config().reportHeadline("TRIRIGA AUTOMATION REGRESSION");
			reporter.config().reportName("");
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Error occured in @BeforeSuite");
		}
	}

	@BeforeMethod
	public void beforeMethod(Method method) {
		try {
			testMethodName = method.getAnnotation(Test.class).description();
			logger = reporter.startTest(browserName.toUpperCase() + " - "+ testMethodName);
			driver = LocalWebDriverFactory.openWebUrl(browserName, baseURL);
		} catch (Exception e) {
			System.out.println("Error ocurred in @BeforeMethod BaseClass..." + e.getMessage());
			e.printStackTrace();
		}
	}

	@AfterMethod
	public void aftreMethod() {
		try {
			reporter.endTest(logger);
			//driver.quit();
			//driver.close();
			Constants logoutCls=new Constants();
			try {
				//logoutCls.Tririga_Logout(driver, logger);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} catch (Exception e) {
			System.out.println("Error in @AfterTest method in BaseClass \n" + e.getMessage());
			reporter.endTest(logger);
			//driver.quit();
			//driver.close();
			//System.exit(1);
		}
	}

	@AfterSuite
	public void afterSuite() {
		Constants logoutCls=new Constants();
		try {
			//logoutCls.Tririga_Logout(driver, logger);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		reporter.flush();
		// EmailSender.sendEmail(sendEmail, emailConfigFile);
	}
	
//	@AfterTest
//	public void afterTest(){
//		Constants logoutCls=new Constants();
//		try {
//			logoutCls.Tririga_Logout(driver, logger);
//		} catch (Exception e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//	}
	public static void reportTestCaseStatus(WebDriver driver, ExtentTest logger, String methodName,
			boolean testStatus) {
		String screenshotPath = rpr.getKey("screenshotPath");
	
		try {
			if (testStatus) {
				String passMessage = "Verified '" + methodName + "'. Test case PASSED.";

				logger.log(LogStatus.PASS, passMessage,
						ReportManager.addLocalScreenshotToReport(driver, screenshotPath, methodName, logger));
			} else {
				String failMessage = "Verified '" + methodName + "'. Test case FAILED.";
				logger.log(LogStatus.FAIL, failMessage,
						ReportManager.addLocalScreenshotToReport(driver, screenshotPath, methodName, logger));
			}
		} catch (Exception e) {
			System.out.println("Error closing the Test Suite in @AfterSuite method \n" + e.getMessage());
			e.printStackTrace();
		}
	}
	
	public static void CreateTestNgXML(Xls_Reader xls){
		String TestNGxml_FilePath=System.getProperty("user.dir")+"\\testng.xml";
		
		try {
			if (new File(TestNGxml_FilePath).exists()) {
				System.out.println("Test suite exists :- "+ TestNGxml_FilePath);
				new File(TestNGxml_FilePath).delete();
			}
			fos = new FileWriter(TestNGxml_FilePath);
			fos.write("<?xml version='1.0' encoding='UTF-8'?>\n");
			fos.write("<!DOCTYPE suite SYSTEM 'http://testng.org/testng-1.0.dtd'>\n");
			fos.write("<suite name='Tririga Automation Regression'>\n\n");
			fos.write("<parameter name=\"baseURL\" value=\"https://shell-test.tririga.com/index.html\"></parameter>\n");
			fos.write("\t<test name=\"TEST\">\n");
			fos.write("\t\t <classes>\n");			
				int rows = xls.getRowCount(Constants.TESTCASES_SHEET);
				for(int rNum=2;rNum<=rows;rNum++){
					String runmode = xls.getCellData(Constants.TESTCASES_SHEET, "Runmode", rNum);
					if(runmode.equals("Y")){
						String ClassName = xls.getCellData(Constants.TESTCASES_SHEET, "TCID", rNum);
						fos.write("\t\t\t<class name=\"" + ClassName +"\" />\n");
					}			
				}
			fos.write("\t\t</classes>\n");
			fos.write("\t\t</test>\n");
			fos.write("\n");
			
			fos.write("</suite>");
			fos.flush();
			if (new File(TestNGxml_FilePath).exists()) {
				System.out.println("Test suite successfully generated :- " + TestNGxml_FilePath);
			}			
			}catch(Exception e){
				e.printStackTrace();
			}
	}
	public static void main(String[] args) throws Exception
	{
		try
		{
			CreateTestNgXML(xls);
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
		}
	}
}
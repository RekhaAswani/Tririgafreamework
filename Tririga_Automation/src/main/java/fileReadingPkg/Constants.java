package fileReadingPkg;

import java.util.Hashtable;
import java.util.Iterator;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;
import com.webElementPkg.WebBrowser;
import com.webElementPkg.WebButton;
import com.webElementPkg.WebFrame;
import com.webElementPkg.WebLink;
import com.webElementPkg.WebUtilities;

public class Constants {
	
	
	// locators
	public static final String Home_Tab = "//a[text()='Home']";
	
	@FindBy(xpath="//a[text()='Sign Out']")
	public WebElement lnkLogout;

	//paths
	public static final String REPORTS_PATH = "D:\\reports\\";
	public static final String DATA_XLS_PATH = System.getProperty("user.dir")+"\\Resources\\TestData\\Data.xlsx";
	public static String TESTDATA_SHEET = "TestData";
	//public static String TESTDATA_SHEET=null;
	public static final Object RUNMODE_COL = "Runmode";
	public static final String TESTCASES_SHEET = "TestCases";
	
	public static Hashtable<String,String> table;
	
	
	public void Tririga_Logout(WebDriver driver,ExtentTest logger)throws Exception
	{	
	   //Check how many windows are open
	   Set<String> allWindows=driver.getWindowHandles();
	   
	   if(allWindows.size()==2){
		   Iterator<String> it=allWindows.iterator();
		   String main=it.next();
		   String sub=it.next();
		   System.out.println(main);
		   System.out.println(sub);
		   
		   driver.switchTo().window(sub);
		   driver.quit();
		   Thread.sleep(1000);
		   driver.switchTo().window(main);	
	   } 	    
	   
	   WebBrowser.switchToParentWindow(driver);
	   WebFrame.switchToMainPageFromIFrame(driver);
	   WebElement Logoutlnk=driver.findElement(By.xpath("//a[text()='Sign Out']"));
	   WebLink.clickOnWebLink(driver, Logoutlnk, logger);
	   WebUtilities.waitForElementToDisappear(driver, Logoutlnk, logger);
	   
	   //if(lnkLogout.isDisplayed()){
		//   test.fail("Logout unsuccesfull");		   
	   //}else{
		//   test.pass("Logout Successfully");
	   //}
		   
	   logger.log(LogStatus.INFO, Logoutlnk.toString() + "' successfully clicked");
	   driver.close();
	}
	
	/*
	public static Hashtable<String,String> getEnvDetails(){
		if(table==null){
			table = new Hashtable<String,String>();
			if(ENV.equals("PROD")){
				table.put("url", PROD_HOMEPAGE_URL);
				table.put("username", PROD_USERNAME);
				table.put("password", PROD_PASSWORD);
			}else if(ENV.equals("UAT")){
				table.put("url", UAT_HOMEPAGE_URL);
				table.put("username", UAT_USERNAME);
				table.put("password", UAT_PASSWORD);
			}
			
		}
		return table;
		 
	}*/
	

}

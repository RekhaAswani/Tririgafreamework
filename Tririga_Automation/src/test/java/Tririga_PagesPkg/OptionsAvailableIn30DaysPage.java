package Tririga_PagesPkg;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;
import com.webElementPkg.MouseOperations;
import com.webElementPkg.WebBrowser;
import com.webElementPkg.WebButton;
import com.webElementPkg.WebFrame;
import com.webElementPkg.WebLink;
import com.webElementPkg.WebTextbox;
import com.webElementPkg.WebUtilities;



public class OptionsAvailableIn30DaysPage {
	
	private WebDriver driver=null;
	private ExtentTest logger=null;
	
	public static final String OptionsAvailable_MainFrame="//iframe[@class='gwt-Frame GDNOS4QBO-']";
	public static final String OptionsAvailable_ObjectsFrame="//iframe[@id='objectsFrame']";
	public static final String OptionsAvailable_ListFrame="//iframe[@id='listFrame']";
	
	public static String ContentFrame="//iframe[@id='contentFrame']";	
	public static String ChildFrame="//iframe[@id='childFrame']";
	public static String FrameMainFrame="//frame[@id='frameMain']";
		
	@FindBy(xpath="//thead[@id='fh-header']//th[@name='th-parent']//div[@name='th-child']")
	public static WebElement TableColumnHeaders;
	
	@FindBy(xpath="//input[@id='filterValue4']")
	public static WebElement LeaseID_Filter;
	
	@FindBy(xpath="//input[@id='filterValue6']")
	public static WebElement OptionType_Filter;
	
	@FindBy(xpath="//div[@id='Exercise']")
	public WebElement Option_Exercisebtn;
	
	@FindBy(xpath="//span[text()='x']")
	public WebElement Closebtn;
	
	@FindBy(xpath="//div[@id='Continue']")
	public WebElement Continuebtn;
	
	@FindBy(xpath="//img[@alt='Refresh']")
	public WebElement Refreshbtn;
	
	
	public OptionsAvailableIn30DaysPage(WebDriver driver,ExtentTest logger){
		this.driver=driver;
		this.logger=logger;
		PageFactory.initElements(driver, this);
	}
	
	public boolean VerifyRecordsInTable(String LeaseID,String LeaseStatus) {
		
		boolean Status=false;
		boolean NotFoundStatus=true;
		String statustext=null;
		
		try{
			
			WebFrame.switchOnMultipleFramesByXpath(driver, OptionsAvailable_MainFrame,OptionsAvailable_ObjectsFrame,OptionsAvailable_ListFrame);
			WebUtilities.waitForElementToAppear(driver, TableColumnHeaders, logger);
			WebTextbox.sendTextToWebInput(LeaseID_Filter, LeaseID, logger);
			WebTextbox.sendTextToWebInput(OptionType_Filter, LeaseStatus, logger);
			MouseOperations.PerformKeywordActions(driver, logger, "ENTER");
			Thread.sleep(3000);
			WebFrame.switchToMainPageFromIFrame(driver);
			WebFrame.switchOnMultipleFramesByXpath(driver, OptionsAvailable_MainFrame,OptionsAvailable_ObjectsFrame,OptionsAvailable_ListFrame);
			 List<WebElement>allelements = driver.findElements(By.xpath("//table[@id='dataTab']//tbody//tr[@id='queryResultRow_0']//span[@class='bodyText']//a"));
			 Thread.sleep(2000);
			 System.out.println(allelements.size()); 	     
			    for(int i=0;i<allelements.size();i++)
			    { 
			    	 WebElement LeaseColItem = driver.findElements(By.xpath("//table[@id='dataTab']//tbody//tr[@id='queryResultRow_0']//span[@class='bodyText']//a")).get(i);
			    	 System.out.println(LeaseColItem.getText());	
			    	 statustext= LeaseColItem.getText();
				    	
				   if(statustext.equalsIgnoreCase(LeaseStatus))
				   {
					   logger.log(LogStatus.PASS, "verified value= "+statustext );
					   Status=true;
					   NotFoundStatus=true;
					   break;
				    }else{
				    	NotFoundStatus=false;
				    }
				   
				    	
			    }
			    if(!NotFoundStatus){
			    	logger.log(LogStatus.FAIL, "Not expected value= "+statustext );
			    	Status=false;
			    }
		}
			    catch(Exception e){
			    	logger.log(LogStatus.FAIL, e.getMessage());
			    	Status=false;
			    }
		return Status;
	} 
	
	public boolean VerifyNoRecordsInTable(String LeaseID,String LeaseStatus) {
		
		boolean Status=false;
		
		try{
			
			WebFrame.switchOnMultipleFramesByXpath(driver, OptionsAvailable_MainFrame,OptionsAvailable_ObjectsFrame,OptionsAvailable_ListFrame);
			WebUtilities.waitForElementToAppear(driver, TableColumnHeaders, logger);
			WebTextbox.sendTextToWebInput(LeaseID_Filter, LeaseID, logger);
			WebTextbox.sendTextToWebInput(OptionType_Filter, LeaseStatus, logger);
			MouseOperations.PerformKeywordActions(driver, logger, "ENTER");
			Thread.sleep(3000);
			WebFrame.switchToMainPageFromIFrame(driver);
			WebFrame.switchOnMultipleFramesByXpath(driver, OptionsAvailable_MainFrame,OptionsAvailable_ObjectsFrame,OptionsAvailable_ListFrame);
			List<WebElement>allelements = driver.findElements(By.xpath("//table[@id='dataTab']//tbody//tr[@id='queryResultRow_0']//span[@class='bodyText']//a"));
			Thread.sleep(2000);
			System.out.println(allelements.size()); 
			
			if(allelements.size()==0){
				logger.log(LogStatus.PASS, "No Records Found in the table");
				Status=true;
			}else{
				logger.log(LogStatus.PASS, "Records Found in the table");
				Status=false;
			}
			   
			}
		    catch(Exception e){
		    	logger.log(LogStatus.FAIL, e.getMessage());
		    	Status=false;
		    }
		return Status;
	} 
	
	public boolean ExceriseOptionFromOptionsAvailableIn30DaysPg(String LeaseStatus) {
		
		boolean Status=false;
		String ColValue=null;
		try{
			
			WebFrame.switchOnMultipleFramesByXpath(driver, OptionsAvailable_MainFrame,OptionsAvailable_ObjectsFrame,OptionsAvailable_ListFrame);
			WebUtilities.waitForElementToAppear(driver, TableColumnHeaders, logger);
			//WebTextbox.sendTextToWebInput(LeaseID_Filter, LeaseID, logger);
			WebTextbox.sendTextToWebInput(OptionType_Filter, LeaseStatus, logger);
			MouseOperations.PerformKeywordActions(driver, logger, "ENTER");
			Thread.sleep(3000);
			WebFrame.switchToMainPageFromIFrame(driver);
			WebFrame.switchOnMultipleFramesByXpath(driver, OptionsAvailable_MainFrame,OptionsAvailable_ObjectsFrame,OptionsAvailable_ListFrame);
			
			List<WebElement> StatusEle=driver.findElements(By.xpath("//tr[contains(@id,'queryResultRow')]//td[contains(@headers,'columnOption Type')]//span[@class='bodyText']"));
			int TotalBefore=StatusEle.size();
			System.out.println("Total rows in Options tbl="+TotalBefore);
			 for(int i=0;i<TotalBefore;i++)
			    { 
			    	 WebElement Option = driver.findElements(By.xpath("//tr[contains(@id,'queryResultRow')]//td[contains(@headers,'columnOption Type')]//span[@class='bodyText']")).get(i);
			    	 System.out.println(Option.getText());	
			    	 String statustext= Option.getText();
				    	
				   if(statustext.contains("Termination"))
				   {
						WebFrame.switchToMainPageFromIFrame(driver);
						WebFrame.switchOnMultipleFramesByXpath(driver, OptionsAvailable_MainFrame,OptionsAvailable_ObjectsFrame,OptionsAvailable_ListFrame);
						Option.click();				    
						logger.log(LogStatus.PASS, "Clicked successfully on "+LeaseStatus);
						Status=true;
						break;
				    }
				   
				    	
			    }
			if(!Status){
				logger.log(LogStatus.FAIL, "Option status is not as expected"+ColValue);
				Status=false;
			}
			
			WebBrowser.switchToChildWindow2(driver);
			WebFrame.switchToMainPageFromIFrame(driver);
			WebButton.webButtonClick(driver, logger, Option_Exercisebtn);	
			
			WebElement CntFrame=driver.findElement(By.xpath("//iframe[@id='contentFrame']"));
			WebUtilities.waitForElementToAppear(driver, CntFrame, logger);
			
			WebFrame.switchToMainPageFromIFrame(driver);
			WebFrame.switchOnMultipleFramesByXpath(driver, ContentFrame,ChildFrame);
			WebButton.webButtonClick(driver, logger, Continuebtn);
			Thread.sleep(2000);
			
			WebFrame.switchToMainPageFromIFrame(driver);
			//WebUtilities.waitForElementToDisappear(driver, Option_Exercisebtn, logger);
			WebButton.webButtonClick(driver, logger, Closebtn);
			Thread.sleep(2000);
			
			WebBrowser.switchToParentWindow(driver);
			WebFrame.switchToMainPageFromIFrame(driver);
			WebFrame.switchOnMultipleFramesByXpath(driver, OptionsAvailable_MainFrame,OptionsAvailable_ObjectsFrame,OptionsAvailable_ListFrame);
			WebButton.webButtonClick(driver, logger, Refreshbtn);
			
			List<WebElement> StatusEle1=driver.findElements(By.xpath("//tr[contains(@id,'queryResultRow')]//td[contains(@headers,'columnOption Type')]//span[@class='bodyText']"));
			int TotalAfter=StatusEle1.size();
			if(TotalAfter<TotalBefore){
				logger.log(LogStatus.PASS, "Options stop appearing");
				Status=true;
			}else{
				logger.log(LogStatus.FAIL, "Options are still appearing");
				Status=false;
			}
			
			   
			}
		    catch(Exception e){
		    	logger.log(LogStatus.FAIL, e.getMessage());
		    	Status=false;
		    }
		return Status;
	} 
	
}

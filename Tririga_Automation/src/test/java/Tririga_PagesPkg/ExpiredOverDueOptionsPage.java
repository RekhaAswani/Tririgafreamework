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
import com.webElementPkg.WebCheckBox;
import com.webElementPkg.WebFrame;
import com.webElementPkg.WebLink;
import com.webElementPkg.WebTextbox;
import com.webElementPkg.WebUtilities;



public class ExpiredOverDueOptionsPage {
	
	private WebDriver driver=null;
	private ExtentTest logger=null;
	
	public static final String OptionsAvailable_MainFrame="//iframe[@class='gwt-Frame GDNOS4QBO-']";
	public static final String OptionsAvailable_ObjectsFrame="//iframe[@id='objectsFrame']";
	public static final String OptionsAvailable_ListFrame="//iframe[@id='listFrame']";
	
	public static String ContentFrame="//iframe[@id='contentFrame']";	
	public static String ChildFrame="//iframe[@id='childFrame']";
		
	@FindBy(xpath="//thead[@id='fh-header']//th[@name='th-parent']//div[@name='th-child']")
	public static WebElement TableColumnHeaders;
	
	@FindBy(xpath="//input[@id='filterValue6']")
	public static WebElement OptionType_Filter;
	
	@FindBy(xpath="//input[@id='filterValue4']")
	public static WebElement LeaseID_Filter;
	
	@FindBy(xpath="//span[text()='Expire']")
	public WebElement Option_Expirebtn;
	
	@FindBy(xpath="//span[text()='x']")
	public WebElement Closebtn;
	
	@FindBy(xpath="//img[@alt='Refresh']")
	public WebElement Refreshbtn;
	
	@FindBy(xpath="//td[@id='row_id_0']")
	public WebElement CheckBox_FirstRow;
	
	@FindBy(xpath="//span[contains(text(),'total found')]")
	public WebElement TotalRecordslbl;
	
	public ExpiredOverDueOptionsPage(WebDriver driver,ExtentTest logger){
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
	
	public boolean ExpireOptionFromExpireDueOptionsPg(String OptionType) {
		
		boolean Status=false;
		String ColValue=null;
		try{
			
			WebFrame.switchOnMultipleFramesByXpath(driver, OptionsAvailable_MainFrame,OptionsAvailable_ObjectsFrame,OptionsAvailable_ListFrame);
			WebUtilities.waitForElementToAppear(driver, TableColumnHeaders, logger);
			//WebTextbox.sendTextToWebInput(LeaseID_Filter, LeaseID, logger);
			WebTextbox.sendTextToWebInput(OptionType_Filter, OptionType, logger);
			MouseOperations.PerformKeywordActions(driver, logger, "ENTER");
			Thread.sleep(3000);
			WebFrame.switchToMainPageFromIFrame(driver);
			WebFrame.switchOnMultipleFramesByXpath(driver, OptionsAvailable_MainFrame,OptionsAvailable_ObjectsFrame,OptionsAvailable_ListFrame);
			
			String TotalRecordstxt=TotalRecordslbl.getText();
			String[] splitvalues=TotalRecordstxt.split(" ");
			//List<WebElement> StatusEle=driver.findElements(By.xpath("//tr[contains(@id,'queryResultRow')]//td[contains(@headers,'columnOption Type')]//span[@class='bodyText']"));
			//int TotalBefore=StatusEle.size();
			int TotalBefore=Integer.parseInt(splitvalues[0]);
			System.out.println("Total rows in Options tbl="+TotalBefore);			
			WebCheckBox.webCheckBoxClick(driver, logger, CheckBox_FirstRow);
			
			WebFrame.switchToMainPageFromIFrame(driver);
			WebFrame.switchOnMultipleFramesByXpath(driver, OptionsAvailable_MainFrame,OptionsAvailable_ObjectsFrame);
			WebButton.webButtonClick(driver, logger, Option_Expirebtn);	
			Thread.sleep(2000);
			
			WebFrame.switchToMainPageFromIFrame(driver);
			WebFrame.switchOnMultipleFramesByXpath(driver, OptionsAvailable_MainFrame,OptionsAvailable_ObjectsFrame,OptionsAvailable_ListFrame);
			WebButton.webButtonClick(driver, logger, Refreshbtn);
			
			TotalRecordstxt=TotalRecordslbl.getText();
			splitvalues=TotalRecordstxt.split(" ");
			int TotalAfter=Integer.parseInt(splitvalues[0]);
			//List<WebElement> StatusEle1=driver.findElements(By.xpath("//tr[contains(@id,'queryResultRow')]//td[contains(@headers,'columnOption Type')]//span[@class='bodyText']"));
			//int TotalAfter=StatusEle1.size();
			System.out.println("Total rows in Options tbl="+TotalAfter);	
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

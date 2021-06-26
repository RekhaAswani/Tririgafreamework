package Tririga_PagesPkg;

import java.text.SimpleDateFormat;
import java.util.Date;
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



public class ManageIndexItemsPage {
	
	private WebDriver driver=null;
	private ExtentTest logger=null;
	
	public static final String MainFrame="//iframe[@class='gwt-Frame GDNOS4QBO-']";
	public static final String ObjectFrame="//iframe[@id='objectsFrame']";
	public static final String ContentFrame="//iframe[@id='contentFrame']";	
	public static final String ListFrame="//iframe[@id='listFrame']";	
	public static String SelectorFrame="unMovableLayerFrame";
	
	@FindBy(xpath="//span[text()='Add']")
	public static WebElement Addbtn;
	
	@FindBy(xpath="//span[text()='Create']")
	public static WebElement Createbtn;
	
	@FindBy(xpath="//thead[@id='fh-header']//th[@name='th-parent']//div[@name='th-child']")
	public static WebElement TableColumnHeaders;
	
	@FindBy(xpath="//input[@id='filterValue1']")
	public static WebElement ID_filter;
	
	@FindBy(xpath="//input[@id='attr_seq_1022']")
	public static WebElement BaseYeartxt;
	
	@FindBy(xpath="//input[@id='attr_seq_1023']")
	public static WebElement IndexDatetxt;
	
	@FindBy(xpath="//input[@id='attr_seq_1024']")
	public static WebElement BaseIndextxt;
	
	@FindBy(xpath="//a[@id='attr_seq_1021_selector']")
	public static WebElement CostIndex_Selector;
	
	@FindBy(xpath="//a[@id='attr_seq_1025_selector']")
	public static WebElement IndexSource_Selector;
	
	@FindBy(xpath="//span[contains(text(),'total found')]")
	public WebElement TotalRecordslbl;
		
	public ManageIndexItemsPage(WebDriver driver,ExtentTest logger){
		this.driver=driver;
		this.logger=logger;
		PageFactory.initElements(driver, this);
	}
	
	public void SelectCostIndex(String CostIndex) throws InterruptedException{
		
		boolean FoundFlag=false;
		
		WebButton.webButtonClick(driver, logger, CostIndex_Selector);
		WebFrame.switchOnFrameById(driver, SelectorFrame);
		List<WebElement> DropDownValues = driver.findElements(By.xpath("//ul[@id='hierarcyTree_2731373']//span[contains(@id,'CONTENT')]"));
		System.out.println("DropDown values="+DropDownValues.size());  
		for(int i=0;i<=DropDownValues.size();i++){
			WebElement Value =driver.findElements(By.xpath("//ul[@id='hierarcyTree_2731373']//span[contains(@id,'CONTENT')]")).get(i);
			System.out.println("PaymentType="+Value.getText());
			if(Value.getText().equals(CostIndex)){
				MouseOperations.ActionMoveToElement(driver, logger, Value);
				Value.click();
				logger.log(LogStatus.INFO, "List Values- '" +  Value.getText() + "' successfully selected");
				FoundFlag=true;
				Thread.sleep(2000);
				break;
			}
		}
		if (FoundFlag=false){
			logger.log(LogStatus.ERROR,"Failed to select '" + CostIndex);
		}
	}
	
	public void SelectIndexSource(String IndexSource) throws InterruptedException{
		
		boolean FoundFlag=false;
		
		WebButton.webButtonClick(driver, logger, IndexSource_Selector);
		WebFrame.switchOnFrameById(driver, SelectorFrame);
		List<WebElement> DropDownValues = driver.findElements(By.xpath("//ul[@id='hierarcyTree_129209299']//span[contains(@id,'CONTENT')]"));
		System.out.println("DropDown values="+DropDownValues.size());  
		for(int i=0;i<=DropDownValues.size();i++){
			WebElement Value =driver.findElements(By.xpath("//ul[@id='hierarcyTree_129209299']//span[contains(@id,'CONTENT')]")).get(i);
			System.out.println("PaymentType="+Value.getText());
			if(Value.getText().equals(IndexSource)){
				MouseOperations.ActionMoveToElement(driver, logger, Value);
				Value.click();
				logger.log(LogStatus.INFO, "List Values- '" +  Value.getText() + "' successfully selected");
				FoundFlag=true;
				Thread.sleep(2000);
				break;
			}
		}
		if (FoundFlag=false){
			logger.log(LogStatus.ERROR,"Failed to select '" + IndexSource);
		}
	}
	
	public boolean CreateIndexItem(String CostIndex,String IndexSource,String BaseIndex,String BaseYear) {
		
		boolean Status=false;
		boolean NotFoundStatus=true;
		String Datestamp = new SimpleDateFormat("MM/dd/yyyy").format(new Date());
		
		try{
			
			WebFrame.switchToMainPageFromIFrame(driver);
			WebFrame.switchOnMultipleFramesByXpath(driver, MainFrame,ObjectFrame);
			WebUtilities.waitForElementToAppear(driver, Addbtn, logger);
			
			WebFrame.switchToMainPageFromIFrame(driver);
			WebFrame.switchOnMultipleFramesByXpath(driver, MainFrame,ObjectFrame,ListFrame);

			String TotalRecordstxt=TotalRecordslbl.getText();
			String[] splitvalues=TotalRecordstxt.split(" ");
			int TotalBefore=Integer.parseInt(splitvalues[0]);
			
			WebFrame.switchToMainPageFromIFrame(driver);
			WebFrame.switchOnMultipleFramesByXpath(driver, MainFrame,ObjectFrame);
			WebButton.webButtonClick(driver, logger, Addbtn);
			
			WebBrowser.switchToChildWindow2(driver);
			WebFrame.switchToMainPageFromIFrame(driver);
			WebFrame.switchOnMultipleFramesByXpath(driver, ContentFrame);
			SelectCostIndex(CostIndex);
			
			WebFrame.switchToMainPageFromIFrame(driver);
			WebFrame.switchOnMultipleFramesByXpath(driver, ContentFrame);
			SelectIndexSource(IndexSource);
			
			WebFrame.switchToMainPageFromIFrame(driver);
			WebFrame.switchOnMultipleFramesByXpath(driver, ContentFrame);
			WebTextbox.sendTextToWebInput(BaseYeartxt, BaseYear, logger);
			WebTextbox.sendTextToWebInput(BaseIndextxt, BaseIndex, logger);
			WebTextbox.sendTextToWebInput(IndexDatetxt, Datestamp, logger);
			
			WebBrowser.switchToParentWindow(driver);
			WebBrowser.switchToChildWindow2(driver);
			WebFrame.switchToMainPageFromIFrame(driver);
			WebButton.webButtonClick(driver, logger, Createbtn);
			Thread.sleep(3000);
			
			WebBrowser.switchToParentWindow(driver);
			WebFrame.switchToMainPageFromIFrame(driver);
			WebFrame.switchOnMultipleFramesByXpath(driver, MainFrame,ObjectFrame,ListFrame);
			TotalRecordstxt=TotalRecordslbl.getText();
			splitvalues=TotalRecordstxt.split(" ");
			int TotalAfter=Integer.parseInt(splitvalues[0]);
			System.out.println("Total rows in Options tbl="+TotalAfter);	
			if(TotalAfter>TotalBefore){
				logger.log(LogStatus.PASS, "Index created");
				Status=true;
			}else{
				logger.log(LogStatus.FAIL, "Index is not created");
				Status=false;
			}
			
		
		}catch(Exception e){
			logger.log(LogStatus.FAIL, "Failed to find the 'All Lease Contracts'" + e.getMessage());
			Status=false;
			e.printStackTrace();
		}
		return Status;
	} 
	
	
}
